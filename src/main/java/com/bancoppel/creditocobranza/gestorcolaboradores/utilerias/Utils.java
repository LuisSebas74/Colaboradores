package com.bancoppel.creditocobranza.gestorcolaboradores.utilerias;


import java.lang.reflect.Type;

import org.modelmapper.ModelMapper;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionEnt;

public class Utils 
{
	
	private static final Logger LOGGER =  LoggerFactory.getLogger(Utils.class);
	
	public static <T> T getClassEntityAsDto(Object entityValue , Class<T> value)
	{
		//return null;
		try {
		LOGGER.info("  Mapper : {} {}" ,entityValue.getClass() , value.getClass() );
		return (T) new ModelMapper().map(entityValue, value);		
		}
		catch (Exception e){
			return null;
		}
		//ModelMapper().map(entityValue, vale);
		/*LOGGER.info("  Mapper : " ,entityValue.getClass() , type.getClass() );
		return new ModelMapper().map(entityValue, type.class);*/
	}
	
	/*public static Type getClassEntityAsDto(Object entityValue , Type value)
	{
		
	}*/
}
