package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDAO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorRps;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.IColaboradorRps;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IIniciativasRps;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.utilerias.Utils;

@Component
public class AsignacionDAO {

	@Autowired
	private IAsignacionesRps iAsignacionesRps;

	@Autowired
	private IColaboradorRps colaboradorRepository;
	
	@Autowired
	private IIniciativasRps iIniciativasRps;
	
	@Autowired
	private ColaboradorDAO colaboradorDAO;
	
	
	
	
	public AsignacionDTO consultar(RequestDTO request) {
		AsignacionDTO data = (AsignacionDTO)request.getParametrosBody();
		
		return new ModelMapper().map(iAsignacionesRps.findById(data.getIdAsignacion()), AsignacionDTO.class);
	}

	public AsignacionDTO registar(RequestDTO request) {
		AsignacionDTO asignacionDto = new AsignacionDTO();
		AsignacionEnt asignacionEnt = new AsignacionEnt();
		asignacionDto = (AsignacionDTO) request.getParametrosBody();

		asignacionEnt = mapearEntidad(asignacionDto);

		asignacionEnt = iAsignacionesRps.save(asignacionEnt);

		asignacionDto = mapearDTO(asignacionEnt);

		return asignacionDto;
	}

	public AsignacionDTO editar(RequestDTO request) {
		AsignacionDTO asignacionDto = new AsignacionDTO();
		AsignacionEnt asignacionEnt = new AsignacionEnt();
		asignacionDto = (AsignacionDTO) request.getParametrosBody();

		asignacionEnt = mapearEntidad(asignacionDto);

		// asignacionEnt = iAsignacionesRps.save(asignacionEnt);

		asignacionDto = mapearDTO(asignacionEnt);

		return asignacionDto;
	}

	public AsignacionDTO eliminar(RequestDTO request) {
		AsignacionDTO asignacionDto = new AsignacionDTO();
		AsignacionEnt asignacionEnt = new AsignacionEnt();
		asignacionDto = (AsignacionDTO) request.getParametrosBody();
		asignacionEnt = mapearEntidad(asignacionDto);
		iAsignacionesRps.deleteByIdColaboradorAndIdIniciativa(asignacionEnt.getIdColaborador(),
				asignacionEnt.getIdIniciativa());

		// iAsignacionesRps.delete(asignacionEnt);
		return asignacionDto;
	}

	public List<AsignacionDTO> listar(RequestDTO request) {
		List<AsignacionEnt> listaAsignacionEnt = new ArrayList<>();
		List<AsignacionDTO> listaasignacionDto = new ArrayList<>();
		AsignacionDTO asignacionDto;
		int contador = 0;

		asignacionDto = (AsignacionDTO) request.getParametrosQuery();

		if (asignacionDto.getIdIniciativa().equals(0)) {
			listaAsignacionEnt = iAsignacionesRps.findAll();
		} else {
			listaAsignacionEnt = iAsignacionesRps.findByIdIniciativa(asignacionDto.getIdIniciativa());
		}

		for (AsignacionEnt item : listaAsignacionEnt) {
			asignacionDto = new AsignacionDTO();

			asignacionDto = mapearDTO(item);
			asignacionDto.setContador(++contador);

			listaasignacionDto.add(asignacionDto);
		}

		return listaasignacionDto;
	}

	private AsignacionDTO mapearDTO(AsignacionEnt objetoEntrada) {
		AsignacionDTO objetoSalida = new AsignacionDTO();
		objetoSalida.setIdAsignacion(objetoEntrada.getIdAsignacion());
		objetoSalida.setIdIniciativa(objetoEntrada.getIdIniciativa());
		objetoSalida.setIdColaborador(objetoEntrada.getIdColaborador());
		objetoSalida.setPorcentajeAsignacion(objetoEntrada.getPorcentajeAsignacion());
		return objetoSalida;
	}

	private AsignacionEnt mapearEntidad(AsignacionDTO objetoEntrada) {
		AsignacionEnt objetoSalida = new AsignacionEnt();
		objetoSalida.setIdAsignacion((objetoEntrada.getIdAsignacion() == null) ? 0 : objetoEntrada.getIdAsignacion());
		objetoSalida.setIdIniciativa(objetoEntrada.getIdIniciativa());
		objetoSalida.setIdColaborador(objetoEntrada.getIdColaborador());
		objetoSalida.setPorcentajeAsignacion(objetoEntrada.getPorcentajeAsignacion());
		return objetoSalida;
	}

	public List<AsignacionDTO> guardarAsignaciones(AsignacionProcesoDTO asignacion) {
		try {
			for (ColaboradorAsignacionDTO colaboradorAsignacion : asignacion.colaboradoresAsignacionDTO) {

				ColaboradorDTO colaborador = new ModelMapper().map(
						colaboradorRepository.findByIdColaborador(colaboradorAsignacion.getIdColaborador()),
						ColaboradorDTO.class);
				if (colaborador != null) {
					AsignacionDTO dto = new AsignacionDTO();
					dto.setIdIniciativa(asignacion.getIdIniciativa());
					dto.setIdColaborador(colaboradorAsignacion.getIdColaborador());
					dto.setPorcentajeAsignacion(colaboradorAsignacion.getPorcentajeAsignacion().doubleValue());
					iAsignacionesRps.save(asignacionDtoToEntity(dto));

				} else {
					return null;
				}
			}
			return listaAsignacionEntityToDto(iAsignacionesRps.findByIdIniciativa(asignacion.getIdIniciativa()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	public List<AsignacionDTO> actualizarAsignacion(AsignacionActualizarDto asignacion)
	{
	
		List<AsignacionDTO> asignacionResultado =  new ArrayList<>();
		
		List<AsignacionDTO> asignacionDTOs = listaAsignacionEntityToDto(iAsignacionesRps.
				findByIdIniciativa(asignacion.getIdIniciativa()));
		
		if(!asignacionDTOs.isEmpty())
		{
			for(AsignacionDTO itemAsignacion :asignacionDTOs )
			{
				
				ColaboradorDTO  colaboradorDTO = new ModelMapper().map(colaboradorRepository.
								 findByIdColaborador(itemAsignacion.getIdColaborador()), ColaboradorDTO.class);
				 if(colaboradorDTO != null)
				 {
					 iAsignacionesRps.deleteByIdColaboradorAndIdIniciativa(itemAsignacion.getIdIniciativa(),colaboradorDTO.getIdColaborador() );
					 colaboradorDAO.aumentarPorjetajeColaborador(colaboradorDTO.getIdColaborador(),itemAsignacion.getPorcentajeAsignacion() );
					 
					 asignacionResultado.add(itemAsignacion);
				 }
			}
			
		}
				
		return asignacionResultado;
	}

	private AsignacionEnt asignacionDtoToEntity(AsignacionDTO dto) {
		return new ModelMapper().map(dto, AsignacionEnt.class);
	}

	private List<AsignacionDTO> listaAsignacionEntityToDto(List<AsignacionEnt> lista) {
		return new ModelMapper().map(lista, new TypeToken<List<AsignacionDTO>>() {
		}.getRawType());
	}
}