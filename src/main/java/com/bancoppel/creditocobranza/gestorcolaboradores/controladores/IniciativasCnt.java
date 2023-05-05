package com.bancoppel.creditocobranza.gestorcolaboradores.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.FiltrosDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.IniciativasSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value = "/iniciativas")
public class IniciativasCnt {
	
	@Autowired
	private IniciativasSrv iniciativasSrv;

    @PostMapping("/consultar")
    public ResponseEntity<ResponseDTO> listarIniciativasPorFiltro(@RequestBody FiltrosDTO filtros) {
    	RequestDTO request = new RequestDTO<>();
    	request.setParametrosBody(filtros);
    	return new ResponseEntity<ResponseDTO>(iniciativasSrv.listarIniciativasPorFiltro(request),HttpStatus.OK);
    }
    
    @PostMapping(value = "/registrar")
    public ResponseEntity<ResponseDTO> registrar(
    		@RequestBody IniciativaDTO iniciativaDTO){
    	ResponseDTO response = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	
    	request.setParametrosBody(iniciativaDTO);
    	
    	response = iniciativasSrv.registrar(request);
    	
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(value = "/editar")
    public ResponseEntity<Object> editar(@RequestBody IniciativaDTO iniciativaDTO){
    	ResponseDTO response = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	request.setParametrosBody(iniciativaDTO);
    	
    	response = iniciativasSrv.editar(request);
    	
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/eliminar")
    public ResponseEntity<ResponseDTO> eliminar(
    		@RequestParam(name = "numeroIniciativa", required = true) String numeroIniciativa){
    	ResponseDTO response = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	IniciativaDTO iniciativaDto = new IniciativaDTO();
    	iniciativaDto.setIdIniciativa(Integer.parseInt(numeroIniciativa));
    	request.setParametrosQuery(iniciativaDto);
    	
    	response = iniciativasSrv.eliminar(request);
    	
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/consultar/{numeroIniciativa}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDTO> consultar(
    		@PathVariable(name = "numeroIniciativa", required = true) String numeroIniciativa){
    	ResponseDTO responseDto = new ResponseDTO();
    	RequestDTO requestDto = new RequestDTO();
    	IniciativaDTO iniciativaDto = new IniciativaDTO();
    	iniciativaDto.setIdIniciativa(Integer.parseInt(numeroIniciativa));
    	
    	requestDto.setParametrosPath(iniciativaDto);
    	
    	responseDto = iniciativasSrv.consultar(requestDto);
    	
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    
    
    @GetMapping(value = "/listar")
    public ResponseEntity<ResponseDTO> listar(){
    	ResponseDTO responseDto = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	
    	responseDto = iniciativasSrv.listar(request);
    	
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
      
}

