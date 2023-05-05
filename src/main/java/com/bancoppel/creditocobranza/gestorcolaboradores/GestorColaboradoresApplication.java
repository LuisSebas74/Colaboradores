package com.bancoppel.creditocobranza.gestorcolaboradores;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.bancoppel.creditocobranza.gestorcolaboradores.utilerias.Fecha;

@SpringBootApplication
@EnableAsync
public class GestorColaboradoresApplication {

	@Value("${time-zone}")
	String zonaHoraria;
	
	@Autowired
	private Fecha fecha;
	
	public static void main(String[] args) {
		SpringApplication.run(GestorColaboradoresApplication.class, args);
	}

	@PostConstruct
	public void init () {
		fecha.hoy();
		TimeZone.setDefault(TimeZone.getTimeZone (zonaHoraria));
	}
}

