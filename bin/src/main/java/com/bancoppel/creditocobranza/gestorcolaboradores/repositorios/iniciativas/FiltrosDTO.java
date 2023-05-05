package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiltrosDTO {

	private String tituloIniciativa;
	private Integer idTipoProyecto;
	private Integer idEstatus;
	
}
