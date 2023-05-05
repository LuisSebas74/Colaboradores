package com.bancoppel.creditocobranza.gestorcolaboradores.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancoppel.creditocobranza.gestorcolaboradores.controladores.CatalogoCnt;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.catalogos.CatalogoDAO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.catalogos.ItemCatalogoDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Service
public class CatalogoSrv {

	@Autowired
	private CatalogoDAO catalogoDao;
	
	ResponseDTO response;
	
	public ResponseDTO listar(RequestDTO request) {
		response = new ResponseDTO();
		List<ItemCatalogoDTO> listaItemsCatalogo = new ArrayList<>();

		listaItemsCatalogo = catalogoDao.listar(request);
		
		response.setResultados(listaItemsCatalogo);
		return response;
	}
}
