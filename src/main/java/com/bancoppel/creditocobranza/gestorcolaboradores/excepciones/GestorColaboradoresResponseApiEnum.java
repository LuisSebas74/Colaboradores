package com.bancoppel.creditocobranza.gestorcolaboradores.excepciones;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GestorColaboradoresResponseApiEnum {

	ERROR_NUMERO_COLABORADOR_REPETIDO("GES-COLABODOR-01"),
    ERROR_FOLIO_REPETIDO("GES-INICIATIVA-01"),
    ERROR_ASIGNACION_REPETIDA("GES-ASIGNACION-01"),
    ERROR_SIN_REGISTROS("GES-ASIGNACION-02"),
    ERROR_EN_TIEMPO_DE_EJECUCION("GES-EJECUCION-100");
	
	private final Object value;

}
