package com.bancoppel.creditocobranza.gestorcolaboradores.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.catalogos.ItemCatalogoDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.AsignacionesSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.CatalogoSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.ColaboradoresSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.IniciativasSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Controller
@RequestMapping(value = "/iniciativas/vistas")
public class PaginasIniciativas {

	@Autowired
	private AsignacionesSrv servicioAsignaciones;

	@Autowired
	private ColaboradoresSrv servicioColaboradores;

	@Autowired
	private IniciativasSrv servicioIniciativas;
	
	@Autowired
	private CatalogoSrv catalogo;
	
	ResponseDTO response;
	RequestDTO request;
	
	@GetMapping(value = "/consultar")
	public String consultar(Model model, @RequestParam(name="folio") String folio){
		
		response = new ResponseDTO();
		request = new RequestDTO();

		List<ItemCatalogoDTO> catalogoSolicitanteList = new ArrayList<>();
		request.setParametrosPath("solicitantes");
		response = catalogo.listar(request);
		catalogoSolicitanteList = (List<ItemCatalogoDTO>) response.getResultados();
		
		List<ItemCatalogoDTO> catalogoTipoList = new ArrayList<>();
		request.setParametrosPath("tiposproyecto");
		response = catalogo.listar(request);
		catalogoTipoList = (List<ItemCatalogoDTO>) response.getResultados();
		
		List<ItemCatalogoDTO> catalogoEstatusList = new ArrayList<>();
		request.setParametrosPath("estatus");
		response = catalogo.listar(request);
		catalogoEstatusList = (List<ItemCatalogoDTO>) response.getResultados();
		
		List<ItemCatalogoDTO> catalogoMOperativoList = new ArrayList<>();
		request.setParametrosPath("modelosoperativos"); 
		response = catalogo.listar(request);
		catalogoMOperativoList = (List<ItemCatalogoDTO>) response.getResultados();
		
		List<ItemCatalogoDTO> catalogoDireccionList = new ArrayList<>();
		request.setParametrosPath("direcciones");
		response = catalogo.listar(request);
		catalogoDireccionList = (List<ItemCatalogoDTO>) response.getResultados();
		
		model.addAttribute("catalogoDireccionList", catalogoDireccionList);
		model.addAttribute("catalogoSolicitanteList", catalogoSolicitanteList);
		model.addAttribute("catalogoTipoList", catalogoTipoList);
		model.addAttribute("catalogoEstatusList", catalogoEstatusList);
		model.addAttribute("catalogoMOperativoList", catalogoMOperativoList);
		
		
		request = new RequestDTO();
		
		IniciativaDTO iniciativaDTO = new IniciativaDTO();
		iniciativaDTO.setFolio(folio);
		request.setParametrosPath(iniciativaDTO);
		response = servicioIniciativas.consultar(request);
		iniciativaDTO = (IniciativaDTO) response.getResultados();
		
		model.addAttribute("iniciativaDTO", iniciativaDTO);
		
		return "iniciativas/registroIniciativa";
	}

	@GetMapping(value = "/registrar")
	public String registrar(Model model){
		response = new ResponseDTO();
		request = new RequestDTO();

		List<ItemCatalogoDTO> catalogoSolicitanteList = new ArrayList<>();
		request.setParametrosPath("solicitantes");
		response = catalogo.listar(request);
		catalogoSolicitanteList = (List<ItemCatalogoDTO>) response.getResultados();
		
		List<ItemCatalogoDTO> catalogoTipoList = new ArrayList<>();
		request.setParametrosPath("tiposproyecto");
		response = catalogo.listar(request);
		catalogoTipoList = (List<ItemCatalogoDTO>) response.getResultados();
		
		List<ItemCatalogoDTO> catalogoEstatusList = new ArrayList<>();
		request.setParametrosPath("estatus");
		response = catalogo.listar(request);
		catalogoEstatusList = (List<ItemCatalogoDTO>) response.getResultados();
		
		List<ItemCatalogoDTO> catalogoMOperativoList = new ArrayList<>();
		request.setParametrosPath("modelosoperativos"); 
		response = catalogo.listar(request);
		catalogoMOperativoList = (List<ItemCatalogoDTO>) response.getResultados();
		
		List<ItemCatalogoDTO> catalogoDireccionList = new ArrayList<>();
		request.setParametrosPath("direcciones");
		response = catalogo.listar(request);
		catalogoDireccionList = (List<ItemCatalogoDTO>) response.getResultados();
		
		model.addAttribute("catalogoDireccionList", catalogoDireccionList);
		model.addAttribute("catalogoSolicitanteList", catalogoSolicitanteList);
		model.addAttribute("catalogoTipoList", catalogoTipoList);
		model.addAttribute("catalogoEstatusList", catalogoEstatusList);
		model.addAttribute("catalogoMOperativoList", catalogoMOperativoList);
		model.addAttribute("iniciativaDTO", new IniciativaDTO());
		
		
		return "iniciativas/registroIniciativa";
	}

	@PostMapping(value = "/registro")
	public String registrarPost(IniciativaDTO iniciativaDTO){
		response = new ResponseDTO();
		request = new RequestDTO();

		request.setParametrosBody(iniciativaDTO);
		
		servicioIniciativas.registrar(request);
		
		return "redirect:/iniciativas/vistas/listar";
	}
	
	

	@PutMapping(value = "/editar")
	public String editar()
	{
		return "asignaciones/index";
	}

	@GetMapping(value = "/eliminar/{idIniciativa}")
	public String eliminar(@PathVariable(name="idIniciativa", required = true) Integer idIniciativa)
	{
		response=new ResponseDTO();
		//request = new RequestDTO<IniciativaDTO>();		
		servicioIniciativas.eliminar(new RequestDTO<IniciativaDTO>().
				buildRequestParamretroBody(new IniciativaDTO().
						buildIniciativa(idIniciativa)));
		
		return "redirect:/iniciativas/vistas/listar";
	}

	@GetMapping(value = "/listar")
	public String listar(
			Model model,
			@RequestParam(name = "tituloIniciativa", required = false, defaultValue = "") String tituloIniciativa,
			@RequestParam(name = "idTipoProyecto", required = false, defaultValue = "0") String idTipoProyecto,
			@RequestParam(name = "idEstatus", required = false, defaultValue = "0") String idEstatus
			)
	{
		response = new ResponseDTO();
		request = new RequestDTO();
		List<IniciativaDTO> listaIniciativas = new ArrayList<>();
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		
		List<ItemCatalogoDTO> catalogoTipoList = new ArrayList<>();
		request.setParametrosPath("tiposproyecto");
		response = catalogo.listar(request);
		catalogoTipoList = (List<ItemCatalogoDTO>) response.getResultados();
		
		List<ItemCatalogoDTO> catalogoEstatusList = new ArrayList<>();
		request.setParametrosPath("estatus");
		response = catalogo.listar(request);
		catalogoEstatusList = (List<ItemCatalogoDTO>) response.getResultados();
		

		model.addAttribute("catalogoTipoList", catalogoTipoList);
		model.addAttribute("catalogoEstatusList", catalogoEstatusList);
		model.addAttribute("iniciativaDTO", new IniciativaDTO());
		
		iniciativaDto.setIdTipoProyecto(Integer.parseInt(idTipoProyecto));
		iniciativaDto.setIdEstatus(Integer.parseInt(idEstatus));
		iniciativaDto.setTituloIniciativa(tituloIniciativa.toUpperCase());
		iniciativaDto.setFolio(tituloIniciativa.toUpperCase());
		
		request.setParametrosQuery(iniciativaDto);
		
		response = servicioIniciativas.listar(request);
		
		listaIniciativas = (List<IniciativaDTO>) response.getResultados();
		
		model.addAttribute("listaIniciativas", listaIniciativas);
		
		return "iniciativas/listaIniciativas";
	}	
		
	
	
}