package com.bancoppel.creditocobranza.gestorcolaboradores.excepciones;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExcepcionColaborador extends RuntimeException {
	private GestorColaboradoresResponseApiEnum apiEnum;
	private HttpStatus httpStatus;
	private String mensaje;
}
