package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignacionDTO {

	private Integer idAsignacion;
	private Integer idIniciativa;
	private Integer idColaborador;
	private Double porcentajeAsignacion;
	private Integer contador;
	public AsignacionDTO buildAsignacionIdIniciativaColaboradorporcentajeAsignacion(Integer idIniciativa,
			Integer idColaborador, Double porcentajeasignacion) {
		
		this.idIniciativa = idIniciativa;
		this.idColaborador = idColaborador;
		this.porcentajeAsignacion = porcentajeasignacion;
		
		return this;
	}
	
}