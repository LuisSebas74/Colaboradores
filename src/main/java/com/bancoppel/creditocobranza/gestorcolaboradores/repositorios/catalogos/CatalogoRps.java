package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.catalogos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;

@Repository
public class CatalogoRps {
	
	@PersistenceContext
	EntityManager entityManager;

	public List<ItemCatalogoDTO> listar(RequestDTO request)
	{
		List<ItemCatalogoDTO> listaItemsCatalogo = new ArrayList<>();
		String nombreCatalogo = "";
		StringBuilder sentenciaSql= new StringBuilder();
		StringBuilder campos= new StringBuilder();
		StringBuilder where= new StringBuilder();
		Query nativeQuery = null;
		List<Object[]> listaObjects = new ArrayList<>();
		
		nombreCatalogo = (String) request.getParametrosPath();
		campos.append("* ");
		where.append("");
		
		sentenciaSql.append("SELECT ");
		sentenciaSql.append(campos);
		sentenciaSql.append("FROM c").append(nombreCatalogo).append(" ");
		sentenciaSql.append(where);
		
		nativeQuery = entityManager.createNativeQuery(sentenciaSql.toString());
		listaObjects = nativeQuery.getResultList();
		
		listaItemsCatalogo = transformar(listaObjects);
		
		return listaItemsCatalogo;
	}
	
	private List<ItemCatalogoDTO> transformar(List<Object[]> lista)
	{
		List<ItemCatalogoDTO> listaItemsCatalogo = new ArrayList<>();
		ItemCatalogoDTO dto;
		
		for(Object[] itemLista: lista)
		{
			dto  = new ItemCatalogoDTO();
			dto.setId(Integer.parseInt(itemLista[0].toString()));
			dto.setTitulo(itemLista[1].toString().toUpperCase());
			if(itemLista.length >=3)
			{
				dto.setDescripcion((itemLista[2] == null) ? "" : itemLista[2].toString());
			}
			else
			{
				dto.setDescripcion("");
			}
			listaItemsCatalogo.add(dto);
		}
		
		return listaItemsCatalogo;
	}
}
