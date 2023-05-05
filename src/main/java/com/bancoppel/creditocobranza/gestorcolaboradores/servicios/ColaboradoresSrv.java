package com.bancoppel.creditocobranza.gestorcolaboradores.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancoppel.creditocobranza.gestorcolaboradores.interfaces.CREEL;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDAO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Service
public class ColaboradoresSrv implements CREEL {

	@Autowired
	private ColaboradorDAO colaboradorDao;
	
	ResponseDTO response;
	
	@Override
	public ResponseDTO consultar(RequestDTO request) {
		response = new ResponseDTO();
		ColaboradorDTO colaboradorDto = new ColaboradorDTO();

		colaboradorDto = colaboradorDao.consultar(request);
		
		response.setResultados(colaboradorDto);
		return response;
	}

	@Override
	public ResponseDTO registrar(RequestDTO request) {
		ColaboradorDTO data=(ColaboradorDTO) request.getParametrosBody();
		ColaboradorDTO dto= colaboradorDao.buscarColaborador(data.getNumColaborador());
		ColaboradorDTO dta= colaboradorDao.buscarNombre(data.getNombre());
		response = new ResponseDTO();
		if(dto==null && dta==null) {			
			
			ColaboradorDTO colaboradorDto = new ColaboradorDTO();
			
			colaboradorDto = colaboradorDao.registar(request);
			
			response.setResultados(colaboradorDto);
			return response;
		}else {
			return response;
		}
	}

	@Override
	public ResponseDTO editar(RequestDTO request) {
		response = new ResponseDTO();
		ColaboradorDTO colaboradorDto = new ColaboradorDTO();
		colaboradorDto = colaboradorDao.editar(request);
		
		response.setResultados(colaboradorDto);
		return response;
	}

	@Override
	public ResponseDTO eliminar(RequestDTO request) {
		response = new ResponseDTO();
		ColaboradorDTO colaboradorDto = new ColaboradorDTO();
		
		colaboradorDto = colaboradorDao.eliminar(request);
		
		response.setResultados(colaboradorDto);
		
		return response;
	}

	@Override
	public ResponseDTO listar(RequestDTO request) {
		response = new ResponseDTO();
		List<ColaboradorDTO> listaColaboradores = new ArrayList<>();
		
		listaColaboradores = colaboradorDao.listar(request);
		
		response.setResultados(listaColaboradores);
		return response;
	}

}