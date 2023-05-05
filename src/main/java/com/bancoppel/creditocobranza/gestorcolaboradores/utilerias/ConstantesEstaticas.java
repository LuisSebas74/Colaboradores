package com.bancoppel.creditocobranza.gestorcolaboradores.utilerias;

public class ConstantesEstaticas {
	/*
	 * Codigos de operacion
	 */
	
	public static final Integer CODIGO_CORRECTO = 0;
	
	public static final Integer CODIGO_ERROR = 1;
	
	public static final Integer CODIGO_ADVERTENCIA = 2;
	
	/*
	 * Constantes de errores para API de Colaboradores
	 */
	
	public static final String MENSAJE_ERROR_NUMERO_COLABORDOR_REPETIDO = "El numero de colabodor que intenta registrar ya existe";
	
	/*
	 * Constantes de errores para API de Iniciativas
	 */
	
	
	public static final String MENSAJE_ERROR_FOLIO_REPETIDO = "El numero de folio que intenta registrarse ya existe ";
	public static final String MENSAJE_ERROR_SIN_REGISTROS = "El folio que intenta consultar no existe";

	/*
	 * Constantes de errores de Asignaciones
	 */
	
	public static final String MENSAJE_ERROR_ASIGNACION_REPETIDA = "El numero de asignacion que intenta registrarse ya existe ";
	
	/*
	 * Constantes de errores en tiempo de ejecucion
	 */

	public static final String MENSAJE_ERROR_TIEMPO_EJECUCION = "Error en tiempo de ejecucion: ";
	
	/*
	 * Constantes para mensajes de skills
	 */
	
	public static final String MENSAJE_AGREGAR_SKILLS_EXITOSO = "El proceso de adicion de skills fue realizado de manera correcta";

	public static final String MENSAJE_AGREGAR_SKILLS_ERROR ="El proceso de adiccion de skills no se llevo a cabo correctamente";

}
