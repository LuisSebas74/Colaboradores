package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ColaboradorAsignacionDTO {
	private Integer idColaborador;
	private Integer porcentajeAsignacion;
}
