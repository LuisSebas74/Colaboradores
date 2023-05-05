package com.bancoppel.creditocobranza.gestorcolaboradores.utilerias;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class Fecha {

	private LocalDateTime fechaHora;
	
	public LocalDateTime hoy()
	{
		fechaHora = LocalDateTime.now();
		return fechaHora;
	}
}