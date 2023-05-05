package com.bancoppel.creditocobranza.gestorcolaboradores.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionCapturaDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionDTOEntrega;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionesRps;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDAO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.AsignacionesSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.ColaboradoresSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.IniciativasSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Controller
@RequestMapping(value = "/asignaciones/vistas")
public class PaginasAsignacionesCnt {
	
	@Autowired
	private AsignacionesSrv servicio;

	@Autowired
	private ColaboradoresSrv servicioColaboradores;
	
	@Autowired
	private IniciativasSrv servicioInciativas;
	
	@Autowired
	private AsignacionesSrv asignacionesSrv;
	
	
	
	private ResponseDTO response;
	private RequestDTO request;
	
	@GetMapping(value = "/consultar")
	public String consultar()
	{
		return "asignaciones/index";
	}

	@GetMapping(value = "/registrar")
	public String registrar(Model model)
	{
		response = new ResponseDTO();
		request = new RequestDTO();

		List<IniciativaDTO> listaIniciativas = new ArrayList<>();
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		iniciativaDto.setTituloIniciativa("");

		iniciativaDto.setFolio("");

		iniciativaDto.setIdEstatus(0);
		iniciativaDto.setIdTipoProyecto(0);

		request.setParametrosQuery(iniciativaDto);
		
		response = servicioInciativas.listar(request);
		
		listaIniciativas = (List<IniciativaDTO>) response.getResultados();
		
		response = new ResponseDTO();
		request = new RequestDTO();
		
		List<ColaboradorDTO> listaColaboradores = new ArrayList<>();
		ColaboradorDTO colaboradorDto = new ColaboradorDTO();
		colaboradorDto.setNombre("");
		
		request.setParametrosQuery(colaboradorDto);
		
		response = servicioColaboradores.listar(request);
		
		listaColaboradores = (List<ColaboradorDTO>) response.getResultados();
		
		model.addAttribute("listainiciativas", listaIniciativas);
		model.addAttribute("listaColaboradores", listaColaboradores);
		model.addAttribute("asignacionDto", new AsignacionDTO());
		return "asignaciones/Tasignaciones";
	}
	
	@PostMapping(value = "/registro")
	public String registrarPost(AsignacionCapturaDTO asignacionDTO)
	{
		response = new ResponseDTO();
		request = new RequestDTO();
		
		request.setParametrosBody(asignacionDTO);
		
		servicio.registrar(request);
		
		return "redirect:/asignaciones/vistas/registrar";
	}

	@PutMapping(value = "/editar")
	public String editar()
	{
		return "asignaciones/index";
	}

	 @GetMapping(value = "/eliminar") 
	 public String eliminar( 
			 @RequestParam(name = "idIniciativa", required = true) Integer idIniciativa,
			 @RequestParam(name = "idColaborador", required = true) Integer idColaborador,
	         @RequestParam(name="porcentajeasignacion", required=true) Double porcentajeasignacion)
	 {	 
		    
			response = new ResponseDTO();
			servicio.eliminar(
		    		new RequestDTO<AsignacionDTO>().
		    		buildRequestParamretroBody(new AsignacionDTO().
		    	    buildAsignacionIdIniciativaColaboradorporcentajeAsignacion(idIniciativa, idColaborador,porcentajeasignacion))
		    		);
			return "redirect:/asignaciones/vistas/listar";
	 }
	
	@GetMapping(value = "/listar")
	public String listar(
			Model model,
			@RequestParam(name = "tituloIniciativa", required = false, defaultValue = "") String tituloIniciativa)
	{
		response = new ResponseDTO();
		request = new RequestDTO();
		List<AsignacionDTO> listaAsignaciones = new ArrayList<>();
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		iniciativaDto.setFolio("");
		iniciativaDto.setTituloIniciativa(tituloIniciativa.toUpperCase());
		List<AsignacionDTOEntrega> listaRelacionAsignacion = new ArrayList<>();
		
		request.setParametrosQuery(iniciativaDto);
		
		response = servicio.listar(request);
		
		listaRelacionAsignacion = (List<AsignacionDTOEntrega>) response.getResultados();
		
		model.addAttribute("listaAsignaciones", listaRelacionAsignacion);
		
		return "asignaciones/listaAsignaciones";
	}	
		
}
