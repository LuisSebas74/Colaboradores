package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;

@Repository
public class IniciativaRps {
	@PersistenceContext
	EntityManager entityManager;

	public List<IniciativaDTO> listar(RequestDTO endpointDatos)
	{
		Query nativeQuery = null;
		
		StringBuilder campos = new StringBuilder();
		
		List<Object[]> listaIniciativasObject = new ArrayList<>();
		List<IniciativaDTO> listaIniciativas = new ArrayList<>();
		

		
		String sql = "SELECT "
				+ "* "
				+ "FROM public.tiniciativas "
				+ "WHERE idiniciativa in ("
				+ "select distinct idiniciativa from public.tasignaciones"
				+ ")"
				;

		nativeQuery = entityManager.createNativeQuery(sql);
		listaIniciativasObject = nativeQuery.getResultList();
		
		listaIniciativas = transformar(listaIniciativasObject);
		return listaIniciativas;
	}
	
	
	private List<IniciativaDTO> transformar(List<Object[]> lista)
	{
		List<IniciativaDTO> listaIniciativas = new ArrayList<>();
		IniciativaDTO dto;
		
		for(Object[] itemLista: lista)
		{
			dto  = new IniciativaDTO();
			dto.setIdIniciativa(Integer.parseInt(itemLista[0].toString()));
			dto.setFolioIniciativaAsociada(itemLista[1].toString());
			dto.setIniciativaEstrategica(itemLista[2].toString());
			dto.setDescripcion(itemLista[3].toString());
			dto.setFolio(itemLista[4].toString());
			dto.setNumIniciativa(itemLista[5].toString());
			dto.setTituloIniciativa(itemLista[6].toString());
			dto.setIdSolicitante(Integer.parseInt(itemLista[7].toString()));
			dto.setIdEstatus(Integer.parseInt(itemLista[8].toString()));
			dto.setIdModOp(Integer.parseInt(itemLista[9].toString()));
			dto.setPorcentajeAvance(Integer.parseInt(itemLista[10].toString()));
			dto.setFechaInicio(itemLista[11].toString());
			dto.setFechaFin(itemLista[12].toString());
			dto.setTimeReport(itemLista[13].toString());
			dto.setNumIntegrantes(itemLista[5].toString());
			dto.setIdTipoProyecto(Integer.parseInt(itemLista[15].toString()));
			dto.setIdDireccion(Integer.parseInt(itemLista[16].toString()));
			dto.setIniciativaAsociada(itemLista[5].toString());
			
			listaIniciativas.add(dto);
		}
		
		return listaIniciativas;
	}
}