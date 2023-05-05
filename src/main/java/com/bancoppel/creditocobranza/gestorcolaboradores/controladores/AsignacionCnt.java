package com.bancoppel.creditocobranza.gestorcolaboradores.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionActualizarDto;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionProcesoDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.AsignacionesSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.IniciativasSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Controller
@RequestMapping(value = "/asignaciones")
public class AsignacionCnt {

	@Autowired
	private AsignacionesSrv asignacionesSrv;
	
    @RequestMapping(value = "/consultar/{idAsignacion}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResponseDTO> consultar(
    		@PathVariable(name = "idAsignacion", required = true) Integer idAsignacion){
    	ResponseDTO responseDto = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	AsignacionDTO asignacion = new AsignacionDTO();
    	asignacion.setIdAsignacion(idAsignacion);
    
    	request.setParametrosBody(asignacion);
    	
    	responseDto = asignacionesSrv.consultar(request);
    	
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    	
    @PostMapping(value = "/registrar")
    public ResponseEntity<ResponseDTO> registrar(
    		@RequestBody AsignacionDTO asignacionDTO){
    	ResponseDTO responseDto = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	
    	request.setParametrosBody(asignacionDTO);
    	
    	responseDto = asignacionesSrv.registrar(request);
    	
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    @PutMapping(value = "/editar")
    public ResponseEntity<Object> editar(
    		@RequestBody AsignacionDTO asignacionDTO){
    	ResponseDTO response = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
 
    	response = asignacionesSrv.editar(request);
    	
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/eliminar")
    public ResponseEntity<ResponseDTO> eliminar(
    		@RequestParam(name = "numeroIniciativa", required = true) String numeroIniciativa,
    		@RequestParam(name = "numeroColaborador", required = true) String numeroColaborador){
    	ResponseDTO response = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	AsignacionDTO asignacionDto = new AsignacionDTO();
    	asignacionDto.setIdIniciativa(Integer.parseInt(numeroIniciativa));
    	asignacionDto.setIdColaborador(Integer.parseInt(numeroColaborador));
    	request.setParametrosQuery(asignacionDto);
    	
    	response = asignacionesSrv.eliminar(request);
    	
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping(value = "/listar/{numeroIniciativa}")
    public ResponseEntity<ResponseDTO> listar(
    		@PathVariable(name = "numeroIniciativa", required = true) String numeroIniciativa){
    	ResponseDTO responseDto = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	AsignacionDTO asignacion = new AsignacionDTO();
    	asignacion.setIdIniciativa(Integer.parseInt(numeroIniciativa));
    
    	request.setParametrosQuery(asignacion);
    	
    	responseDto = asignacionesSrv.listar(request);
    	
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    public ResponseEntity<ResponseDTO> otro(){
    	ResponseDTO responseDto = new ResponseDTO();
    	
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    @PostMapping("/guardar/asignaciones")
    public ResponseEntity<ResponseDTO> guardarAsignaciones(@RequestBody AsignacionProcesoDTO asignacion){
    	return new ResponseEntity<ResponseDTO>(asignacionesSrv.guardarAsignaciones(asignacion),HttpStatus.OK);
    }
    
    
    @PutMapping("/actualizar/asignaciones")
    public  ResponseEntity<ResponseDTO>  actualizarAsignaciones(@RequestBody AsignacionActualizarDto asignacion)
    {
    	return new ResponseEntity<ResponseDTO>(asignacionesSrv.actualizarAsignaciones(asignacion),HttpStatus.OK);
    }
    
}
