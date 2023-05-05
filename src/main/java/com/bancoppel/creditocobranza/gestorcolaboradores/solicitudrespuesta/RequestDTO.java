package com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RequestDTO<T> {

	private String microservicio;
	private String endpoint;
	private String url;
	private String metodo;
	private String identificacion;
	private Object parametrosPath;
	private Object parametrosQuery;
	private T parametrosBody;
	
	public RequestDTO buildRequestParamretroBody(T parametrosBody)
	{
		
		this.parametrosBody =  parametrosBody;
		return this;	
	}
	
	@Override
	public String toString() {
		
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("[ENTRADA]");
        stringBuilder.append("[MICROSERVICIO: ").append(microservicio).append("] ");
        stringBuilder.append("[ENDPOINT: ").append(endpoint).append("] ");
        stringBuilder.append("[URL: ").append(url).append("] ");
        stringBuilder.append("[MÉTODO: ").append(metodo).append("] ");
        stringBuilder.append("[IDENTIFICACIÓN: ").append(identificacion).append("] ");
        stringBuilder.append("[PARÁMETROS PATH: ").append(parametrosPath).append("] ");
        stringBuilder.append("[PARÁMETROS QUERY: ").append(parametrosQuery).append("] ");
        stringBuilder.append("[PARÁMETROS BODY: ").append(parametrosBody).append("] ");
        
        return stringBuilder.toString();
	}
}