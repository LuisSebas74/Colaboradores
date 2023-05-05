package com.bancoppel.creditocobranza.gestorcolaboradores.interfaces;

import org.springframework.stereotype.Service;

import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Service
public interface CREEL {

	ResponseDTO consultar(RequestDTO requestDTO);
	ResponseDTO registrar(RequestDTO requestDTO);
	ResponseDTO editar(RequestDTO requestDTO);
	ResponseDTO eliminar(RequestDTO requestDTO);
	ResponseDTO listar(RequestDTO requestDTO);

}
