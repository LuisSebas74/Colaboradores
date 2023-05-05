package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.bancoppel.creditocobranza.gestorcolaboradores.interfaces.CREEL;
import com.bancoppel.creditocobranza.gestorcolaboradores.interfaces.CREELDAO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionEnt;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionesRps;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.IAsignacionesRps;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ColaboradorDAO {

	@Autowired
	private IColaboradorRps iColaboradorRps;

	@Autowired
	private ColaboradorRps colaboradorRps;

	@Autowired
	private IAsignacionesRps iAsignacionesRps;

	public ColaboradorDTO consultar(RequestDTO request) {
		ColaboradorDTO colaboradorDto = new ColaboradorDTO();
		ColaboradorEnt colaboradorEnt = new ColaboradorEnt();
		Optional<ColaboradorEnt> oColaboradorEnt = null;
		colaboradorDto = (ColaboradorDTO) request.getParametrosPath();

		colaboradorEnt = mapearEntidad(colaboradorDto);

		oColaboradorEnt = iColaboradorRps.findById(colaboradorEnt.getIdColaborador());

		colaboradorDto = mapearDTO(oColaboradorEnt.get());

		colaboradorDto.setSkills(
				colaboradorRps.consultarSkills(colaboradorDto.getIdColaborador()).replace("[", "").replace("]", ""));

		return colaboradorDto;
	}

	public ColaboradorDTO registar(RequestDTO request) {
		ColaboradorDTO colaboradorDto = new ColaboradorDTO();
		ColaboradorEnt colaboradorEnt = new ColaboradorEnt();
		colaboradorDto = (ColaboradorDTO) request.getParametrosBody();

		colaboradorDto.setIdCentro(colaboradorDto.getIdCentro());

		colaboradorEnt = mapearEntidad(colaboradorDto);

		colaboradorEnt = iColaboradorRps.save(colaboradorEnt);

		colaboradorDto = mapearDTO(colaboradorEnt);

		return colaboradorDto;
	}

	public ColaboradorDTO editar(RequestDTO request) {
		ColaboradorDTO colaboradorDto = new ColaboradorDTO();
		ColaboradorEnt colaboradorEnt = new ColaboradorEnt();
		colaboradorDto = (ColaboradorDTO) request.getParametrosBody();
		colaboradorEnt = mapearEntidad(colaboradorDto);
	
		colaboradorEnt = iColaboradorRps.save(colaboradorEnt);

		colaboradorDto = mapearDTO(colaboradorEnt);

		return colaboradorDto;
	}

	public void editarPorcentaje(Integer idColaborador, Double porcentajeAsignado) {
		ColaboradorEnt colaboradorEnt = new ColaboradorEnt();
		Optional<ColaboradorEnt> oColaboradorEnt = null;
		Double porcentajeDisponible = new Double(0);

		colaboradorEnt.setIdColaborador(idColaborador);

		oColaboradorEnt = iColaboradorRps.findById(colaboradorEnt.getIdColaborador());

		colaboradorEnt = oColaboradorEnt.get();

		porcentajeDisponible = colaboradorEnt.getFulltime();

		if (porcentajeAsignado <= porcentajeDisponible) {
			colaboradorEnt.setFulltime(colaboradorEnt.getFulltime() - porcentajeAsignado);
		} else {
			colaboradorEnt.setFulltime(porcentajeDisponible);
		}

		iColaboradorRps.save(colaboradorEnt);

	}

	public void aumentarPorjetajeColaborador(Integer idColaborador, Double porcentajeAsignado) {
		ColaboradorEnt colaboradorEnt = new ColaboradorEnt();
		Optional<ColaboradorEnt> oColaboradorEnt = null;
		Double porcentajeDisponible = new Double(0);

		colaboradorEnt.setIdColaborador(idColaborador);

		oColaboradorEnt = iColaboradorRps.findById(colaboradorEnt.getIdColaborador());

		colaboradorEnt = oColaboradorEnt.get();

		porcentajeDisponible = colaboradorEnt.getFulltime();

		if ((porcentajeDisponible + porcentajeAsignado) >= new Double(100)) {
			oColaboradorEnt.get().setFulltime(new Double(100));
		} else {
			oColaboradorEnt.get().setFulltime(porcentajeDisponible + porcentajeAsignado);
		}

		iColaboradorRps.save(colaboradorEnt);

	}

	public ColaboradorDTO eliminar(RequestDTO request) {
		ColaboradorDTO colaboradorDto = new ColaboradorDTO();

		colaboradorDto = (ColaboradorDTO) request.getParametrosQuery();
		// colaboradorEnt = mapearEntidad(colaboradorDto.get());

		Optional<ColaboradorEnt> colaboradorEnt = iColaboradorRps.findById(colaboradorDto.getIdColaborador());

		iAsignacionesRps.deleteByIdColaborador(colaboradorEnt.get().getIdColaborador());
		iColaboradorRps.delete(colaboradorEnt.get());

		return colaboradorDto;
	}

	public List<ColaboradorDTO> listar(RequestDTO request) {
		List<ColaboradorEnt> listaColaboradorEnt = new ArrayList<>();
		List<ColaboradorDTO> listaColaboradorDto = new ArrayList<>();
		ColaboradorDTO colaboradorDto;
		int contador = 0;

		colaboradorDto = (ColaboradorDTO) request.getParametrosQuery();

		listaColaboradorDto = colaboradorRps.consultar(request);

		while (contador < listaColaboradorDto.size()) {
			listaColaboradorDto.get(contador)
					.setSkills(colaboradorRps.consultarSkills(listaColaboradorDto.get(contador).getIdColaborador())
							.replace("[", "").replace("]", ""));
			contador++;
		}

		return listaColaboradorDto;
	}

	private ColaboradorDTO mapearDTO(ColaboradorEnt objetoEntrada) {
		ColaboradorDTO objetoSalida = new ColaboradorDTO();
		objetoSalida.setIdColaborador(objetoEntrada.getIdColaborador());
		objetoSalida.setIdEquipo(objetoEntrada.getIdEquipo());
		objetoSalida.setIdEspecialidad(objetoEntrada.getIdEspecialidad());
		objetoSalida.setNombre(objetoEntrada.getNombre());
		objetoSalida.setIdGerenciaSR(objetoEntrada.getIdGerenciaSR());
		objetoSalida.setComentarioNegocio(objetoEntrada.getComentarioNegocio());
		objetoSalida.setIdPuesto(objetoEntrada.getIdPuesto());
		objetoSalida.setFulltime(objetoEntrada.getFulltime());
		objetoSalida.setTipoColaborador(objetoEntrada.getTipoColaborador().trim());
		objetoSalida.setIdEmpresa(objetoEntrada.getIdEmpresa());
		objetoSalida.setNumColaborador(objetoEntrada.getNumColaborador());
		objetoSalida.setIdCentro(objetoEntrada.getIdCentro());
		objetoSalida.setCorreo(objetoEntrada.getCorreo());
		objetoSalida.setIdLider(objetoEntrada.getIdLider());
		objetoSalida.setIdUbicacion(objetoEntrada.getIdUbicacion());
		objetoSalida.setIdEsquema(objetoEntrada.getIdEsquema());
		objetoSalida.setIpBanco(objetoEntrada.getIpBanco());
		objetoSalida.setIpVPN(objetoEntrada.getIpVPN());
		objetoSalida.setSolicitante(objetoEntrada.getSolicitante());
		return objetoSalida;
	}

//Convierte de DTO a Entidad
	private ColaboradorEnt mapearEntidad(ColaboradorDTO objetoEntrada) {
		ColaboradorEnt objetoSalida = new ColaboradorEnt();
		objetoSalida.setIdColaborador(objetoEntrada.getIdColaborador());
		objetoSalida.setIdEquipo(objetoEntrada.getIdEquipo());
		objetoSalida.setIdEspecialidad(objetoEntrada.getIdEspecialidad());
		objetoSalida.setNombre(objetoEntrada.getNombre());
		objetoSalida.setIdGerenciaSR(objetoEntrada.getIdGerenciaSR());
		objetoSalida.setComentarioNegocio(objetoEntrada.getComentarioNegocio());
		objetoSalida.setIdPuesto(objetoEntrada.getIdPuesto());
		objetoSalida.setFulltime(objetoEntrada.getFulltime());
		objetoSalida.setTipoColaborador(objetoEntrada.getTipoColaborador());
		objetoSalida.setIdEmpresa(objetoEntrada.getIdEmpresa());
		objetoSalida.setNumColaborador(objetoEntrada.getNumColaborador());
		objetoSalida.setIdCentro(objetoEntrada.getIdCentro());
		objetoSalida.setCorreo(objetoEntrada.getCorreo());
		objetoSalida.setIdLider(objetoEntrada.getIdLider());
		objetoSalida.setIdUbicacion(objetoEntrada.getIdUbicacion());
		objetoSalida.setIdEsquema(objetoEntrada.getIdEsquema());
		objetoSalida.setIpBanco(objetoEntrada.getIpBanco());
		objetoSalida.setIpVPN(objetoEntrada.getIpVPN());
		objetoSalida.setSolicitante(objetoEntrada.getSolicitante());
		return objetoSalida;
	}

	public ColaboradorDTO buscarColaborador(Integer numColaborador) {
		try {
			return new ModelMapper().map(iColaboradorRps.findByNumColaborador(numColaborador), ColaboradorDTO.class);
			
			
		} catch (Exception e) {
			System.out.println("no existe numero de colaborador, procede con registro");
			return null;
		}
		// iColaboradorRps.findByNumColaborador(numeroColaborador);
	}
	
	  public ColaboradorDTO buscarNombre(String nombreColaborador) {
	  
	  try { 
		  String nombreBusqueda = nombreColaborador.toUpperCase();
		  System.out.println("YA EXISTE USUARIO");
		  return new ModelMapper().map(iColaboradorRps.findByNombre(nombreBusqueda), ColaboradorDTO.class);
		  
		  
	  } catch (Exception a) {
	       System.out.println("No existe nombre similar, registro nuevo"); 
	  return null;
	  
	   } 
	  }
	  
}