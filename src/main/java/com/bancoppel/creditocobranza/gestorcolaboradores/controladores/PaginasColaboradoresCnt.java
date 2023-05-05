package com.bancoppel.creditocobranza.gestorcolaboradores.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.catalogos.ItemCatalogoDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.CatalogoSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.servicios.ColaboradoresSrv;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Controller
@RequestMapping(value = "/colaboradores/vistas")
public class PaginasColaboradoresCnt {

	@Autowired
	private ColaboradoresSrv servicio;

	@Autowired
	private CatalogoSrv catalogo;

	ResponseDTO response;
	RequestDTO request;

	@GetMapping(value = "/listar")
	public String listar(Model model,
			@RequestParam(name = "datoColaborador", required = false, defaultValue = "") String datoColaborador) {
		response = new ResponseDTO();
		request = new RequestDTO();

		List<ColaboradorDTO> listaColaboradores = new ArrayList<>();
		ColaboradorDTO colaboradorDto = new ColaboradorDTO();
		colaboradorDto.setNombre(datoColaborador.toUpperCase());
		request.setParametrosQuery(colaboradorDto);
		response = servicio.listar(request);
		listaColaboradores = (List<ColaboradorDTO>) response.getResultados();

		model.addAttribute("listaColaboradores", listaColaboradores);

		return "colaboradores/listaColaboradores";
	}

	@GetMapping(value = "/registrar")
	public String registrar(Model model,
			@RequestParam(name = "idColaborador", required = false, defaultValue = "") String idColaborador) {

		response = new ResponseDTO();
		request = new RequestDTO();

		// consulta a tabla equipo
		request.setParametrosPath("equipos");
		List<ItemCatalogoDTO> catalogoEquipoList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoEquipoList = (List<ItemCatalogoDTO>) response.getResultados();

		// consulta a tabla especialidades
		request.setParametrosPath("especialidades");
		List<ItemCatalogoDTO> catalogoEspecialidadList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoEspecialidadList = (List<ItemCatalogoDTO>) response.getResultados();

		// consulta a tabla gerenciasSR
		request.setParametrosPath("gerenciassr");
		List<ItemCatalogoDTO> catalogoGerenciaSRList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoGerenciaSRList = (List<ItemCatalogoDTO>) response.getResultados();

		// consulta a tabla puestos
		request.setParametrosPath("puestos");
		List<ItemCatalogoDTO> catalogoPuestoList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoPuestoList = (List<ItemCatalogoDTO>) response.getResultados();

		/*
		 * PARA CONSULTA A CATALOGOS FULLTIME consulta a tabla fulltime
		 * request.setParametrosPath("puestos"); List<ItemCatalogoDTO>
		 * catalogoPuestoList = new ArrayList<>(); response = catalogo.listar(request);
		 * catalogoPuestoList = (List<ItemCatalogoDTO>) response.getResultados();
		 */

		/*
		 * PARA CONSULTA A CATALOGOS TIPO COLABORADOR consulta a tabla tipoColaborador
		 * request.setParametrosPath("puestos"); List<ItemCatalogoDTO>
		 * catalogoPuestoList = new ArrayList<>(); response = catalogo.listar(request);
		 * catalogoPuestoList = (List<ItemCatalogoDTO>) response.getResultados();
		 */

		request.setParametrosPath("empresas");
		List<ItemCatalogoDTO> catalogoTipoEmpresaList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoTipoEmpresaList = (List<ItemCatalogoDTO>) response.getResultados();

		request.setParametrosPath("centros");
		List<ItemCatalogoDTO> catalogoCentroList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoCentroList = (List<ItemCatalogoDTO>) response.getResultados();

		request.setParametrosPath("ubicaciones");
		List<ItemCatalogoDTO> catalogoUbicacionList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoUbicacionList = (List<ItemCatalogoDTO>) response.getResultados();

		request.setParametrosPath("esquemas");
		List<ItemCatalogoDTO> catalogoEsquemaList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoEsquemaList = (List<ItemCatalogoDTO>) response.getResultados();

		request.setParametrosPath("solicitantes");
		List<ItemCatalogoDTO> catalogoSolicitanteList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoSolicitanteList = (List<ItemCatalogoDTO>) response.getResultados();

		request.setParametrosPath("gerentesresponsables");
		List<ItemCatalogoDTO> catalogoLiderList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoLiderList = (List<ItemCatalogoDTO>) response.getResultados();
		
		request.setParametrosPath("skills");
		List<ItemCatalogoDTO> catalogoSkillList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoSkillList = (List<ItemCatalogoDTO>) response.getResultados();
		
		model.addAttribute("catalogoSkillList", catalogoSkillList);
		model.addAttribute("catalogoEquipoList", catalogoEquipoList);
		model.addAttribute("catalogoEspecialidadList", catalogoEspecialidadList);
		model.addAttribute("catalogoGerenciaSRList", catalogoGerenciaSRList);
		model.addAttribute("catalogoPuestoList", catalogoPuestoList);
		// falta fulltime HARDCODE
		// falta tipocolaborador HARDCODE
		model.addAttribute("catalogoTipoEmpresaList", catalogoTipoEmpresaList);
		model.addAttribute("catalogoCentroList", catalogoCentroList);
		model.addAttribute("catalogoLiderList", catalogoLiderList);
		model.addAttribute("catalogoLiderList", catalogoLiderList);
		model.addAttribute("catalogoUbicacionList", catalogoUbicacionList);
		model.addAttribute("catalogoEsquemaList", catalogoEsquemaList);
		model.addAttribute("catalogoSolicitanteList", catalogoSolicitanteList);
		model.addAttribute("colaboradorDTO", new ColaboradorDTO());

		model.addAttribute("tituloPagina", new String("REGISTRAR NUEVO COLABORADOR"));

		return "colaboradores/fcolaborador";
	}

	@PostMapping(value = "/registro")
	public String registrarPost(ColaboradorDTO colaboradorDTO) {
		response = new ResponseDTO();
		request = new RequestDTO();

		colaboradorDTO.setFulltime(100.0);
		colaboradorDTO.setSolicitante("0");

		request.setParametrosBody(colaboradorDTO);
		System.out.println(colaboradorDTO.toString());

		servicio.registrar(request);

		return "redirect:/colaboradores/vistas/listar";
	}

	
	@GetMapping(value = "/eliminar/{idColaborador}")
	public String eliminar(@PathVariable(name="idColaborador", required = true) Integer idColaborador)
	{
		response=new ResponseDTO();
		//request = new RequestDTO<IniciativaDTO>();		
		servicio.eliminar(new RequestDTO<ColaboradorDTO>().
				buildRequestParamretroBody(new ColaboradorDTO().
						buildColaborador(idColaborador)));
		
		return "redirect:/colaboradores/vistas/listar";
	}

	@GetMapping(value = "/consultar")
	public String consultar(Model model, @RequestParam(name = "idColaborador") Integer idColaborador) {

		response = new ResponseDTO();
		request = new RequestDTO();

		// consulta a tabla equipo
		request.setParametrosPath("equipos");
		List<ItemCatalogoDTO> catalogoEquipoList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoEquipoList = (List<ItemCatalogoDTO>) response.getResultados();

		// consulta a tabla especialidades
		request.setParametrosPath("especialidades");
		List<ItemCatalogoDTO> catalogoEspecialidadList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoEspecialidadList = (List<ItemCatalogoDTO>) response.getResultados();

		// consulta a tabla gerenciasSR
		request.setParametrosPath("gerenciassr");
		List<ItemCatalogoDTO> catalogoGerenciaSRList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoGerenciaSRList = (List<ItemCatalogoDTO>) response.getResultados();

		// consulta a tabla puestos
		request.setParametrosPath("puestos");
		List<ItemCatalogoDTO> catalogoPuestoList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoPuestoList = (List<ItemCatalogoDTO>) response.getResultados();

		/*
		 * PARA CONSULTA A CATALOGOS FULLTIME consulta a tabla fulltime
		 * request.setParametrosPath("puestos"); List<ItemCatalogoDTO>
		 * catalogoPuestoList = new ArrayList<>(); response = catalogo.listar(request);
		 * catalogoPuestoList = (List<ItemCatalogoDTO>) response.getResultados();
		 */

		/*
		 * PARA CONSULTA A CATALOGOS TIPO COLABORADOR consulta a tabla tipoColaborador
		 * request.setParametrosPath("puestos"); List<ItemCatalogoDTO>
		 * catalogoPuestoList = new ArrayList<>(); response = catalogo.listar(request);
		 * catalogoPuestoList = (List<ItemCatalogoDTO>) response.getResultados();
		 */

		request.setParametrosPath("empresas");
		List<ItemCatalogoDTO> catalogoTipoEmpresaList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoTipoEmpresaList = (List<ItemCatalogoDTO>) response.getResultados();

		request.setParametrosPath("centros");
		List<ItemCatalogoDTO> catalogoCentroList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoCentroList = (List<ItemCatalogoDTO>) response.getResultados();

		request.setParametrosPath("ubicaciones");
		List<ItemCatalogoDTO> catalogoUbicacionList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoUbicacionList = (List<ItemCatalogoDTO>) response.getResultados();

		request.setParametrosPath("esquemas");
		List<ItemCatalogoDTO> catalogoEsquemaList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoEsquemaList = (List<ItemCatalogoDTO>) response.getResultados();

		request.setParametrosPath("solicitantes");
		List<ItemCatalogoDTO> catalogoSolicitanteList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoSolicitanteList = (List<ItemCatalogoDTO>) response.getResultados();

		request.setParametrosPath("gerentesresponsables");
		List<ItemCatalogoDTO> catalogoLiderList = new ArrayList<>();
		response = catalogo.listar(request);
		catalogoLiderList = (List<ItemCatalogoDTO>) response.getResultados();

		model.addAttribute("catalogoEquipoList", catalogoEquipoList);
		model.addAttribute("catalogoEspecialidadList", catalogoEspecialidadList);
		model.addAttribute("catalogoGerenciaSRList", catalogoGerenciaSRList);
		model.addAttribute("catalogoPuestoList", catalogoPuestoList);
		// falta fulltime HARDCODE
		// falta tipocolaborador HARDCODE
		model.addAttribute("catalogoTipoEmpresaList", catalogoTipoEmpresaList);
		model.addAttribute("catalogoCentroList", catalogoCentroList);
		model.addAttribute("catalogoLiderList", catalogoLiderList);
		model.addAttribute("catalogoLiderList", catalogoLiderList);
		model.addAttribute("catalogoUbicacionList", catalogoUbicacionList);
		model.addAttribute("catalogoEsquemaList", catalogoEsquemaList);
		model.addAttribute("catalogoSolicitanteList", catalogoSolicitanteList);

		ColaboradorDTO colaboradorDTO = new ColaboradorDTO();
		colaboradorDTO.setIdColaborador(idColaborador);
		request.setParametrosPath(colaboradorDTO);
		response = servicio.consultar(request);
		colaboradorDTO = (ColaboradorDTO) response.getResultados();

		String button = "<input type=\"button\" value=\"EDITAR\" class=\"btn btn-secondary\" onclick=\"activarInputs('12')\" ></input>";

		

		model.addAttribute("colaboradorDTO", colaboradorDTO);
		model.addAttribute("tituloPagina", new String("EDITAR COLABORADOR -> " + colaboradorDTO.getNombre()));
		model.addAttribute("button", button);
		model.addAttribute("desactivaCampos", " ");
		

		return "colaboradores/fcolaborador";
	}

}



