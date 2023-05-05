package com.bancoppel.creditocobranza.gestorcolaboradores.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bancoppel.creditocobranza.gestorcolaboradores.excepciones.ExcepcionColaborador;
import com.bancoppel.creditocobranza.gestorcolaboradores.excepciones.GestorColaboradoresResponseApiEnum;
import com.bancoppel.creditocobranza.gestorcolaboradores.interfaces.CREEL;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDAO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.FiltrosDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDAO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.utilerias.ConstantesEstaticas;

@Service
public class IniciativasSrv implements CREEL{

	@Autowired
	private IniciativaDAO iniciativaDao;
	
	ResponseDTO response;
	
	@Override
	public ResponseDTO consultar(RequestDTO request) {
		response = new ResponseDTO();
		IniciativaDTO iniciativaDto = new IniciativaDTO();

		iniciativaDto = iniciativaDao.consultar(request);
		
		response.setResultados(iniciativaDto);
		return response;
	}

	@Override
	public ResponseDTO registrar(RequestDTO request){
		IniciativaDTO data=(IniciativaDTO) request.getParametrosBody();
		response = new ResponseDTO();
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		IniciativaDTO iniciativaExistente = iniciativaDao.listarPorFolio(data.getFolio());
		if(iniciativaExistente==null) {
			iniciativaDto = iniciativaDao.registar(request);
			response.setResultados(iniciativaDto);
			return response;
		}else {
			throw new ExcepcionColaborador(GestorColaboradoresResponseApiEnum.ERROR_FOLIO_REPETIDO,HttpStatus.BAD_REQUEST, ConstantesEstaticas.MENSAJE_ERROR_FOLIO_REPETIDO);
		}
	}

	@Override
	public ResponseDTO editar(RequestDTO request) {
		response = new ResponseDTO();
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		
		iniciativaDto = iniciativaDao.editar(request);
		
		response.setResultados(iniciativaDto);
		return response;
	}

	@Override
	public ResponseDTO eliminar(RequestDTO request) {
		response = new ResponseDTO();
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		
		iniciativaDto = iniciativaDao.eliminar(request);
		response.setResultados(iniciativaDto);
		
		return response;
	}

	@Override
	public ResponseDTO listar(RequestDTO request) {
		response = new ResponseDTO();
		List<IniciativaDTO> listaColaboradores = new ArrayList<>();
		
		listaColaboradores = iniciativaDao.listar(request);
		
		response.setResultados(listaColaboradores);
		return response;
	}
	
	public ResponseDTO listarIniciativasPorFiltro(RequestDTO<FiltrosDTO> request) {
		return iniciativaDao.listarIniciativasPorFiltros(request);
	}
}