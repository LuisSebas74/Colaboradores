package com.bancoppel.creditocobranza.gestorcolaboradores.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Controller
public interface CREELControladores {

	ResponseEntity<ResponseDTO> consultar();
	

}