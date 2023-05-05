package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones;

import java.util.List;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDTO;

import lombok.Data;

@Data
public class AsignacionDTOEntrega {

	private IniciativaDTO iniciativa;
	private String listaColaboradore;
}
