package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.annotations.Where;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionEnt;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionesRps;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.IAsignacionesRps;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorEnt;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Component
public class IniciativaDAO {

	@Autowired
	private IIniciativasRps iIniciativasRps;

	@Autowired
	private IniciativaRps iniciativasRps;

	@Autowired
	private AsignacionesRps asignacionesRps;

	@Autowired
	private IAsignacionesRps iAsignacionesRps;
	
	@Autowired
	private EntityManager entityManager;
	

	public IniciativaDTO consultar(RequestDTO request) {
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		List<IniciativaEnt> listaIniciativaEnt = new ArrayList<>();
		IniciativaEnt iniciativaEnt = new IniciativaEnt();
		Optional<IniciativaEnt> oIniciativaEnt = null;
		iniciativaDto = (IniciativaDTO) request.getParametrosPath();

		iniciativaEnt = mapearEntidad(iniciativaDto);

		listaIniciativaEnt = iIniciativasRps.findByFolio(iniciativaEnt.getFolio());
		iniciativaEnt = listaIniciativaEnt.get(0);

		iniciativaDto = mapearDTO(iniciativaEnt);

		return iniciativaDto;
	}

	public IniciativaDTO consultarFiltro(RequestDTO request) {
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		IniciativaEnt iniciativaEnt = new IniciativaEnt();
		Optional<IniciativaEnt> oIniciativaEnt = null;
		iniciativaDto = (IniciativaDTO) request.getParametrosPath();

		iniciativaEnt = mapearEntidad(iniciativaDto);

		oIniciativaEnt = iIniciativasRps.findById(iniciativaEnt.getIdIniciativa());

		iniciativaDto = mapearDTO(oIniciativaEnt.get());

		return iniciativaDto;
	}

	public IniciativaDTO registar(RequestDTO request) {
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		IniciativaEnt iniciativaEnt = new IniciativaEnt();
		iniciativaDto = (IniciativaDTO) request.getParametrosBody();
		iniciativaEnt = mapearEntidad(iniciativaDto);

		iniciativaEnt = iIniciativasRps.save(iniciativaEnt);

		iniciativaDto = mapearDTO(iniciativaEnt);

		return iniciativaDto;
	}

	public IniciativaDTO editar(RequestDTO request) {
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		IniciativaEnt iniciativaEnt = new IniciativaEnt();
		iniciativaDto = (IniciativaDTO) request.getParametrosBody();
		iniciativaEnt = mapearEntidad(iniciativaDto);

		// iniciativaEnt = iIniciativasRsp.save(iniciativaEnt);

		iniciativaDto = mapearDTO(iniciativaEnt);

		return iniciativaDto;
	}

	public IniciativaDTO eliminar(RequestDTO request) {
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		IniciativaEnt iniciativaEnt = new IniciativaEnt();
		iniciativaDto = (IniciativaDTO) request.getParametrosBody();

		iniciativaEnt.setIdIniciativa(iniciativaDto.getIdIniciativa());

		iniciativaEnt = mapearEntidad(iniciativaDto);

		// Se debe eliminar primero la relacion iniciativa colaboradores en la
		// asugnaciones
		iAsignacionesRps.deleteByIdIniciativa(iniciativaEnt.getIdIniciativa());
		iIniciativasRps.delete(iniciativaEnt);

		return iniciativaDto;
	}

	public List<IniciativaDTO> listar(RequestDTO request) {
		List<IniciativaEnt> listaIniciativaEnt = new ArrayList<>();
		List<IniciativaDTO> listaInicitivaDto = new ArrayList<>();
		IniciativaDTO iniciativaDTO;
		int contador = 0;

		iniciativaDTO = (IniciativaDTO) request.getParametrosQuery();
		if (request.getParametrosQuery() == null)
		// if(iniciativaDTO.getFolio().equals("")
		// &&iniciativaDTO.getTituloIniciativa().equals("")
		// &&iniciativaDTO.getIdEstatus().equals(0)&&iniciativaDTO.getIdTipoProyecto().equals(0))

		{
			// Sin filtros
			listaIniciativaEnt = iIniciativasRps.findAll();
		} else {
			if ((iniciativaDTO.getIdEstatus() == null
					|| iniciativaDTO.getIdEstatus().equals(0) && iniciativaDTO.getIdTipoProyecto() == null
					|| iniciativaDTO.getIdTipoProyecto().equals(0))
					&& (iniciativaDTO.getTituloIniciativa() != null
							&& !iniciativaDTO.getTituloIniciativa().equals(""))) {
				// Por Titulo ofolio
				listaIniciativaEnt = iIniciativasRps.findByFolio(iniciativaDTO.getFolio());

				if (listaIniciativaEnt.size() == 0) {
					listaIniciativaEnt = iIniciativasRps.findByTituloIniciativa(iniciativaDTO.getTituloIniciativa());
				}
			}

			if ((iniciativaDTO.getTituloIniciativa() == null
					|| iniciativaDTO.getTituloIniciativa().equals("") && iniciativaDTO.getIdTipoProyecto() == null
					|| iniciativaDTO.getIdTipoProyecto().equals(0))
					&& (iniciativaDTO.getIdEstatus() != null && !iniciativaDTO.getIdEstatus().equals(0))) {
				// Por estatus
				listaIniciativaEnt = iIniciativasRps.findByIdEstatus(iniciativaDTO.getIdEstatus());
			}

			if ((iniciativaDTO.getTituloIniciativa() == null
					|| iniciativaDTO.getTituloIniciativa().equals("") && iniciativaDTO.getIdEstatus() == null
					|| iniciativaDTO.getIdEstatus().equals(0))
					&& (iniciativaDTO.getIdTipoProyecto() != null && !iniciativaDTO.getIdTipoProyecto().equals(0))) {
				// Todos nulos o vacios
				listaIniciativaEnt = iIniciativasRps.findByIdTipoProyecto(iniciativaDTO.getIdTipoProyecto());
			}

		}

		for (IniciativaEnt item : listaIniciativaEnt) {
			iniciativaDTO = new IniciativaDTO();

			iniciativaDTO = mapearDTO(item);
			iniciativaDTO.setContador(++contador);
			listaInicitivaDto.add(iniciativaDTO);
		}

		return listaInicitivaDto;
	}

	public List<IniciativaDTO> listarFiltro(RequestDTO request) {
		List<IniciativaEnt> listaIniciativaEnt = new ArrayList<>();
		List<IniciativaDTO> listaInicitivaDto = new ArrayList<>();
		IniciativaDTO iniciativaDTO;
		int contador = 0;

		iniciativaDTO = (IniciativaDTO) request.getParametrosQuery();

		if (iniciativaDTO.getTituloIniciativa().equals("")) {
			listaInicitivaDto = iniciativasRps.listar(request);
		} else {
			listaIniciativaEnt = iIniciativasRps.findByTituloIniciativa(iniciativaDTO.getTituloIniciativa());
			for (IniciativaEnt item : listaIniciativaEnt) {
				iniciativaDTO = new IniciativaDTO();

				iniciativaDTO = mapearDTO(item);
				iniciativaDTO.setContador(++contador);
				listaInicitivaDto.add(iniciativaDTO);
			}
		}

		return listaInicitivaDto;
	}

	private IniciativaDTO mapearDTO(IniciativaEnt objetoEntrada) {
		IniciativaDTO objetoSalida = new IniciativaDTO();
		objetoSalida.setIdIniciativa(objetoEntrada.getIdIniciativa());
		objetoSalida.setFolioIniciativaAsociada(objetoEntrada.getFolioIniciativaAsociada());
		objetoSalida.setIniciativaEstrategica(objetoEntrada.getIniciativaEstrategica());
		objetoSalida.setDescripcion(objetoEntrada.getDescripcion());
		objetoSalida.setFolio(objetoEntrada.getFolio());
		objetoSalida.setNumIniciativa(objetoEntrada.getNumIniciativa());
		objetoSalida.setTituloIniciativa(objetoEntrada.getTituloIniciativa());
		objetoSalida.setIdSolicitante(objetoEntrada.getIdSolicitante());
		objetoSalida.setIdEstatus(objetoEntrada.getIdEstatus());
		objetoSalida.setIdModOp(objetoEntrada.getIdModOp());
		objetoSalida.setPorcentajeAvance(objetoEntrada.getPorcentajeAvance());
		objetoSalida.setFechaInicio(objetoEntrada.getFechaInicio());
		objetoSalida.setFechaFin(objetoEntrada.getFechaFin());
		objetoSalida.setTimeReport(objetoEntrada.getTimeReport());
		objetoSalida.setNumIntegrantes(objetoEntrada.getNumIntegrantes().trim());
		objetoSalida.setIdTipoProyecto(objetoEntrada.getIdTipoProyecto());
		objetoSalida.setIdDireccion(objetoEntrada.getIdDireccion());
		objetoSalida.setIniciativaAsociada(objetoEntrada.getIniciativaAsociada());

		return objetoSalida;
	}

//Convierte de DTO a Entidad
	private IniciativaEnt mapearEntidad(IniciativaDTO objetoEntrada) {
		IniciativaEnt objetoSalida = new IniciativaEnt();
		objetoSalida.setIdIniciativa(objetoEntrada.getIdIniciativa());
		objetoSalida.setFolioIniciativaAsociada(objetoEntrada.getFolioIniciativaAsociada());
		objetoSalida.setIniciativaEstrategica(objetoEntrada.getIniciativaEstrategica());
		objetoSalida.setDescripcion(objetoEntrada.getDescripcion());
		objetoSalida.setFolio(objetoEntrada.getFolio());
		objetoSalida.setNumIniciativa(objetoEntrada.getNumIniciativa());
		objetoSalida.setTituloIniciativa(objetoEntrada.getTituloIniciativa());
		objetoSalida.setIdSolicitante(objetoEntrada.getIdSolicitante());
		objetoSalida.setIdEstatus(objetoEntrada.getIdEstatus());
		objetoSalida.setIdModOp(objetoEntrada.getIdModOp());
		objetoSalida.setPorcentajeAvance(objetoEntrada.getPorcentajeAvance());
		objetoSalida.setFechaInicio(objetoEntrada.getFechaInicio());
		objetoSalida.setFechaFin(objetoEntrada.getFechaFin());
		objetoSalida.setTimeReport(objetoEntrada.getTimeReport());
		objetoSalida.setNumIntegrantes(objetoEntrada.getNumIntegrantes());
		objetoSalida.setIdTipoProyecto(objetoEntrada.getIdTipoProyecto());
		objetoSalida.setIdDireccion(objetoEntrada.getIdDireccion());
		objetoSalida.setIniciativaAsociada(objetoEntrada.getIniciativaAsociada());

		return objetoSalida;

	}

	public IniciativaDTO listarPorFolio(String folio) {
		try {
			System.out.println(iIniciativasRps.listarPorFolio(folio).getTituloIniciativa());
			return new ModelMapper().map(iIniciativasRps.listarPorFolio(folio), IniciativaDTO.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public ResponseDTO listarIniciativasPorFiltros(RequestDTO<FiltrosDTO> request) {

		FiltrosDTO filtros = request.getParametrosBody();
		ResponseDTO response = new ResponseDTO();
		response.setResultados(new ModelMapper().map(ejecutarConsultaConFiltros(filtros), new TypeToken<List<IniciativaDTO>>() {}.getType()));
		return response;
	}
    
    private List<IniciativaEnt> ejecutarConsultaConFiltros(FiltrosDTO filtros){

    	CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    	CriteriaQuery<IniciativaEnt> query = builder.createQuery(IniciativaEnt.class);
    	Root<IniciativaEnt> iniciativa = query.from(IniciativaEnt.class);
    	List<Predicate> predicates = new ArrayList<>();
    
    	if (filtros.getTituloIniciativa() != null && !filtros.getTituloIniciativa().isEmpty()) {
    		
    		predicates.add(builder.like(builder.lower(iniciativa.get("tituloIniciativa")),"%" + filtros.getTituloIniciativa().toLowerCase()+"%"));
    	}
    	if(filtros.getIdTipoProyecto() != null && !filtros.getIdTipoProyecto().equals(0)) {
    		
    		predicates.add(builder.equal(iniciativa.get("idTipoProyecto"),filtros.getIdTipoProyecto()));
    	}
    	if(filtros.getIdEstatus() != null && !filtros.getIdEstatus().equals(0)) {
    		
    		predicates.add(builder.equal(iniciativa.get("idEstatus"),filtros.getIdEstatus()));
    	}
    	
    	query.where(predicates.toArray(new Predicate[predicates.size()]));
    	
    	TypedQuery<IniciativaEnt> typedQuery = entityManager.createQuery(query);
    	
    	return typedQuery.getResultList();
    	
    	
    }
    
}

