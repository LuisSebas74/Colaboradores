package com.bancoppel.creditocobranza.gestorcolaboradores.controladores;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bancoppel.creditocobranza.gestorcolaboradores.bitacora.BitacoraConsola;
import com.bancoppel.creditocobranza.gestorcolaboradores.excepciones.ExcepcionColaborador;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.utilerias.ConstantesEstaticas;
import com.bancoppel.creditocobranza.gestorcolaboradores.utilerias.Fecha;
import com.bancoppel.creditocobranza.gestorcolaboradores.utilerias.Identificador;

@RestControllerAdvice
public class ExcepcionesControlador extends ResponseEntityExceptionHandler {

	@Value("${spring.application.name}")
	private String MICROSERVICIO;
	
	private String TIEMPO_EJECUCION = "0 minutos";
	private String MEMORIA_CONSUMIDA = "0 MB";
	
	@Autowired
	private BitacoraConsola bitacoraConsola;
	
	@Autowired
	private Identificador identificador;
	
	@Autowired
	private Fecha fecha;

	private  static final  Logger LOGGER =  LoggerFactory.getLogger(ExcepcionesControlador.class);
	
	@ExceptionHandler(ExcepcionColaborador.class)
	public ResponseEntity<ResponseDTO> exepcionColaborador(ExcepcionColaborador excepcion){
		LOGGER.error(ConstantesEstaticas.MENSAJE_ERROR_TIEMPO_EJECUCION + excepcion.getMensaje());
		return new ResponseEntity<ResponseDTO>(new ResponseDTO(excepcion.getHttpStatus().value(), excepcion.getHttpStatus().getReasonPhrase(), excepcion.getApiEnum().toString()),excepcion.getHttpStatus());
		
	}

	/*	
	//Excepciones para headers
	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseDTO response = armarResponse(request, status, Mensajes.ESTATUS_HTTP_CODE_400003, Mensajes.ESTATUS_HTTP_CODE_400003_TEXTO);
		bitacoraConsola.escribir(response);
		return new ResponseEntity<>(response, HttpStatus.valueOf(status.value()));
	}
		
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseDTO response = armarResponse(request, status, Mensajes.ESTATUS_HTTP_CODE_400001, Mensajes.ESTATUS_HTTP_CODE_400001_TEXTO);
		bitacoraConsola.escribir(response);
		return new ResponseEntity<>(response, HttpStatus.valueOf(status.value()));
	}
		
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseDTO response = armarResponse(request, status, Mensajes.ESTATUS_HTTP_CODE_400004, Mensajes.ESTATUS_HTTP_CODE_400004_TEXTO);
		bitacoraConsola.escribir(response);
		return new ResponseEntity<>(response, HttpStatus.valueOf(status.value()));
	}

	//Excepcion para los query params
	@Override
	public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseDTO response = armarResponse(request, status, Mensajes.ESTATUS_HTTP_CODE_400005, Mensajes.ESTATUS_HTTP_CODE_400005_TEXTO + ": " +ex.getParameterName());
		bitacoraConsola.escribir(response);
		return new ResponseEntity<>(response, HttpStatus.valueOf(status.value()));
	}
			
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    ResponseDTO response = armarResponse(request, status, Mensajes.ESTATUS_HTTP_CODE_400006, Mensajes.ESTATUS_HTTP_CODE_400006_TEXTO);
	    bitacoraConsola.escribir(response);
		return new ResponseEntity<>(response, HttpStatus.valueOf(status.value()));
	}
		
	//Excepcion para los path variable params
	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseDTO response = armarResponse(request, status, Mensajes.ESTATUS_HTTP_CODE_400007, Mensajes.ESTATUS_HTTP_CODE_400007_TEXTO);
		bitacoraConsola.escribir(response);
		return new ResponseEntity<Object>(response, HttpStatus.valueOf(response.getCodigoEstatusHttp()));
	}
			
	//Excepción para body param
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseDTO response = armarResponse(request, status, Mensajes.ESTATUS_HTTP_CODE_400008, Mensajes.ESTATUS_HTTP_CODE_400008_TEXTO);
		bitacoraConsola.escribir(response);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCodigoEstatusHttp()));
	}

	@ExceptionHandler(IOException.class)
	public ResponseEntity<ResponseDTO> handleFileNotFoundException(IOException exception, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		ResponseDTO response = armarResponse(request, status, Mensajes.ESTATUS_HTTP_CODE_400011, Mensajes.ESTATUS_HTTP_CODE_400011_TEXTO);
		bitacoraConsola.escribir(response);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCodigoEstatusHttp()));	
	}
	
	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<ResponseDTO> handleMultipartException(MultipartException ex)
	{
		ResponseDTO response = armarResponse("Cargar Archivo","/codigos-postales/archivo-sepomex/cargar-archivo", "POST", Mensajes.ESTATUS_HTTP_CODE_400005, Mensajes.ESTATUS_HTTP_CODE_400005_TEXTO);
		bitacoraConsola.escribir(response);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCodigoEstatusHttp()));	
	}
	
	/*@ExceptionHandler(MensajesExcepcion.class)
	public ResponseEntity<ResponseDTO> handleMensajesException(MensajesExcepcion ex)
	{
		ResponseDTO response = armarResponse(ex.getRequest(), ex.getClaveMensaje(), ex.getMensaje());
		bitacoraConsola.escribir(response);
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCodigoEstatusHttp()));	
	}*/
	/*
	private ResponseDTO armarResponse(WebRequest request, HttpStatus status, Integer codigoEstatus, String descripcion)
	{
		return ResponseDTO.builder()
		.microservicio(MICROSERVICIO)
		.endpoint(request.getDescription(false).replace("uri=", "").substring(request.getDescription(false).replace("uri=", "").lastIndexOf("/")+1, request.getDescription(false).replace("uri=", "").length()))
		.url(request.getDescription(false).replace("uri=", ""))
		.metodo("POST")
		.identificacion(identificador.obtener(LocalDateTime.now(), "yyyyMMddhhmmssSSS"))
		.codigoEstatusHttp(status.value())
		.estatusHttp(status.name().replace("_", " "))
		.codigoEstatus(codigoEstatus)
		.descripcion(descripcion)
		.resultados(new HashMap<>())
		.tiempoEjecucion("")
		.memoriaConsumida("")
		.build();
	}
	
	private ResponseDTO armarResponse(RequestDTO request, Integer codigoEstatus, String descripcion)
	{
		return ResponseDTO.builder()
		.microservicio(MICROSERVICIO)
		.endpoint(request.getEndpoint())
		.url(request.getUrl())
		.metodo(request.getMetodo())
		.identificacion(request.getIdentificacion())
		.codigoEstatusHttp(Integer.parseInt(codigoEstatus.toString().substring(0, 3)))
		.estatusHttp(HttpStatus.valueOf(Integer.parseInt(codigoEstatus.toString().substring(0, 3))).name().replace("_", " "))
		.codigoEstatus(codigoEstatus)
		.descripcion(descripcion)
		.resultados(new HashMap<>())
		.tiempoEjecucion("")
		.memoriaConsumida("").build();
	}
	
	private ResponseDTO armarResponse(String endpoint, String url, String metodo,  Integer codigoEstatus, String descripcion)
	{
		return ResponseDTO.builder()
		.microservicio(MICROSERVICIO)
		.endpoint(endpoint)
		.url(url)
		.metodo(metodo)
		.identificacion(identificador.obtener(fecha.hoy(), "yyyyMMddhhmmssSSS"))
		.codigoEstatusHttp(Integer.parseInt(codigoEstatus.toString().substring(0, 3)))
		.estatusHttp(HttpStatus.valueOf(Integer.parseInt(codigoEstatus.toString().substring(0, 3))).name().replace("_", " "))
		.codigoEstatus(codigoEstatus)
		.descripcion(descripcion)
		.resultados(new HashMap<>())
		.tiempoEjecucion("")
		.memoriaConsumida("").build();
	}	*/

}