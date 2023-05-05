package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AsignacionProcesoDTO {
	private Integer idIniciativa;
	List<ColaboradorAsignacionDTO> colaboradoresAsignacionDTO;
}
