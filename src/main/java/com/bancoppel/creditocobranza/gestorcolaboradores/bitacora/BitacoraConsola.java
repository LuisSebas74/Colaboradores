package com.bancoppel.creditocobranza.gestorcolaboradores.bitacora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Component
public class BitacoraConsola {

	private static Logger logger = LoggerFactory.getLogger(BitacoraConsola.class);

	public void escribir(RequestDTO endpointDatos) {
		logger.info(endpointDatos.toString());
	}
	
	public void escribir(ResponseDTO endpointResponse)
	{
		if(endpointResponse.getCodigoEstatusHttp() >= 200 && endpointResponse.getCodigoEstatusHttp() < 300)
		{
			logger.info(endpointResponse.toString());	
		}
		else if(endpointResponse.getCodigoEstatusHttp() >= 400 && endpointResponse.getCodigoEstatusHttp() < 500)
		{
			logger.warn(endpointResponse.toString());
		}
		else if(endpointResponse.getCodigoEstatusHttp() >= 500 && endpointResponse.getCodigoEstatusHttp() < 600)
		{
			logger.error(endpointResponse.toString());
		}	
		else if(endpointResponse.getCodigoEstatusHttp().equals(0))
		{
			logger.info(endpointResponse.toString());
		}
		else
		{
			logger.error("Error inesperado");
		}
	}
	
	public void escribir(String mensaje)
	{
		logger.info(mensaje);
	}

	
}
