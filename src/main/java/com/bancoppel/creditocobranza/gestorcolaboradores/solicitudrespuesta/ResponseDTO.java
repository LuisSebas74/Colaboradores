package com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta;

import java.io.Serializable;

import com.bancoppel.creditocobranza.gestorcolaboradores.excepciones.GestorColaboradoresResponseApiEnum;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String microservicio;
	private String endpoint;
	private String url;
	private String metodo;
	private String identificacion;
	private Integer codigoEstatusHttp; 
	private String estatusHttp;
	private Integer codigoEstatus; 
	private String descripcion;
	private String tiempoEjecucion;
	private String memoriaConsumida;
	private Object resultados;
	
	public ResponseDTO(Integer codigoEstatusHttp, String estatusHttp, String descripcion) {
		this.estatusHttp = estatusHttp;
		this.codigoEstatusHttp = codigoEstatusHttp;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("[SALIDA]");
		stringBuilder.append("[MICROSERVICIO: ").append(microservicio).append("] ");
        stringBuilder.append("[ENDPOINT: ").append(endpoint).append("] ");
        stringBuilder.append("[URL: ").append(url).append("] ");
        stringBuilder.append("[MÉTODO: ").append(metodo).append("] ");
        stringBuilder.append("[IDENTIFICACION: ").append(identificacion).append("] ");
        stringBuilder.append("[ESTATUS HTTP: ").append(codigoEstatusHttp).append("] ");
        stringBuilder.append("[CÓDIGO ESTATUS: ").append(codigoEstatus).append("] ");
        stringBuilder.append("[TIEMPO EJECUCION: ").append(tiempoEjecucion).append("] ");
        stringBuilder.append("[MEMORIA CONSUMIDA: ").append(memoriaConsumida).append("] ");
        stringBuilder.append("[MENSAJE: ").append(descripcion).append("] ");
        stringBuilder.append("[DATOS SALIDA: ").append(resultados).append("] ");
        
        return stringBuilder.toString();
	}
}