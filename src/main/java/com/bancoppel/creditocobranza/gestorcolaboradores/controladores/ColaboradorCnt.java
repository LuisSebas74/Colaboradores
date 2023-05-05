package com.bancoppel.creditocobranza.gestorcolaboradores.controladores;

import java.util.ArrayList;
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

import com.bancoppel.creditocobranza.gestorcolaboradores.interfaces.CREEL;
import com.bancoppel.creditocobranza.gestorcolaboradores.interfaces.CREELControladores;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorSkillsSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.RelacionColaboradorSkillsRequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.ColaboradoresSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;


@RestController
@RequestMapping(value = "/colaboradores")
public class ColaboradorCnt {
	
	@Autowired
	private ColaboradoresSrv colaboradoresSrv;
	
	  @Autowired
	    private ColaboradorSkillsSrv colaboradorSkillsSrv;
    
    @RequestMapping(value = "/consultar/{numeroColaborador}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public ResponseEntity<ResponseDTO> consultar(
    		@PathVariable(name = "numeroColaborador", required = true) String numeroColaborador){
    	ResponseDTO responseDto = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	ColaboradorDTO colaborador = new ColaboradorDTO();
    	colaborador.setIdColaborador(Integer.parseInt(numeroColaborador));
    
    	request.setParametrosPath(colaborador);
    	
    	responseDto = colaboradoresSrv.consultar(request);
    	
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    


    @PostMapping(value="/registrarSkills")
    public ResponseEntity<ResponseDTO> registrarskill(@RequestBody RelacionColaboradorSkillsRequestDTO relacion) {     
        RequestDTO<RelacionColaboradorSkillsRequestDTO> request = new RequestDTO<>(); 
        request.setParametrosBody(relacion);
        return new ResponseEntity<ResponseDTO>(colaboradorSkillsSrv.guardarColaboradorSkills(request),HttpStatus.OK );
      
    }
    
    
    @PostMapping(value = "/registrar")
    public ResponseEntity<ResponseDTO> registrar(@RequestBody ColaboradorDTO colaboradorDto){
    	ResponseDTO response = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	request.setParametrosBody(colaboradorDto);
    	response = colaboradoresSrv.registrar(request);
    	
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PutMapping(value = "/editar")
    public ResponseEntity<Object> editar(@RequestBody ColaboradorDTO colaboradorDto){
    	ResponseDTO response = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	request.setParametrosBody(colaboradorDto);
    	
    	response = colaboradoresSrv.editar(request);
    	
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping(value = "/eliminar")
    public ResponseEntity<ResponseDTO> eliminar(
    		@RequestParam(name = "idColaborador", required = true) String idColaborador){
    	ResponseDTO response = new ResponseDTO();
    	RequestDTO request = new RequestDTO();
    	ColaboradorDTO colaboradorDto = new ColaboradorDTO();
    	colaboradorDto.setIdColaborador(Integer.parseInt(idColaborador));
    	request.setParametrosQuery(colaboradorDto);
    	
    	response = colaboradoresSrv.eliminar(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping(value = "/listar")
    public ResponseEntity<ResponseDTO> listar(
            @RequestParam(name = "datoColaborador", defaultValue = "") String datoColaborador
            ){
        
        ResponseDTO response = new ResponseDTO();
        RequestDTO request = new RequestDTO();
        
        List<ColaboradorDTO> listaColaboradores = new ArrayList<>();
        ColaboradorDTO colaboradorDto = new ColaboradorDTO();
        colaboradorDto.setNombre(datoColaborador.toUpperCase());
        request.setParametrosQuery(colaboradorDto);
        response = colaboradoresSrv.listar(request);
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    
}
