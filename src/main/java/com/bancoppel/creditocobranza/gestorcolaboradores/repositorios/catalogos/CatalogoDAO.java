package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.catalogos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorEnt;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;

@Component
public class CatalogoDAO {
	
	@Autowired
	private CatalogoRps catalogoRps;
	
	public List<ItemCatalogoDTO> listar(RequestDTO request)
	{
		List<ItemCatalogoDTO> listaItemsCatalogo = new ArrayList<>();	
		listaItemsCatalogo = catalogoRps.listar(request);		
		return listaItemsCatalogo;
	}

}
