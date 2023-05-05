package com.bancoppel.creditocobranza.gestorcolaboradores.utilerias;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class Identificador {

	public String obtener(LocalDateTime fechaHora ,String formato)
	{
		String fecha = "";
		DateTimeFormatter patron;
		patron = DateTimeFormatter.ofPattern(formato);
		fecha = fechaHora.format(patron);
		return fecha;
	}
}