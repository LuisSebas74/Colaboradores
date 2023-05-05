package com.bancoppel.creditocobranza.gestorcolaboradores.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bancoppel.creditocobranza.gestorcolaboradores.interfaces.CREEL;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionActualizarDto;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionCapturaDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionDAO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionDTOEntrega;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones.AsignacionProcesoDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDAO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores.ColaboradorDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDAO;
import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Service
public class AsignacionesSrv implements CREEL{
	
	@Autowired
	private AsignacionDAO asignacionDao;

	@Autowired
	private IniciativaDAO iniciativaDao;
	
	@Autowired
	private ColaboradorDAO colaboradorDao;
	
	@Autowired
	private ColaboradorDAO colaboradorDAO;
	
	ResponseDTO response;
	
	@Override
	public ResponseDTO consultar(RequestDTO request) {
		response = new ResponseDTO();
		AsignacionDTO AsignacionDTO = new AsignacionDTO();

		AsignacionDTO = asignacionDao.consultar(request);
		
		response.setResultados(AsignacionDTO);
		return response;
	}

	@Override
	public ResponseDTO registrar(RequestDTO request) {
		response = new ResponseDTO();
		AsignacionDTO asignacionDto;
		AsignacionCapturaDTO asignacionCapturaDTO = new AsignacionCapturaDTO();
		Integer idIniciativa =0;
		String idColaboradoresPorcentajes = "";
		String[] itemsCP = {};
		
		asignacionCapturaDTO = (AsignacionCapturaDTO) request.getParametrosBody();
		
		idIniciativa = asignacionCapturaDTO.getIdIniciativa();
		idColaboradoresPorcentajes = asignacionCapturaDTO.getColaboradoresPorcentajes();
		
		itemsCP = idColaboradoresPorcentajes.split(",");
		
		for(String itemCP: itemsCP)
		{
			asignacionDto = new AsignacionDTO();
			String[] datosCP = itemCP.split("-");
			asignacionDto.setIdIniciativa(idIniciativa);
			asignacionDto.setIdColaborador(Integer.parseInt(datosCP[0]));
			asignacionDto.setPorcentajeAsignacion(Double.valueOf(datosCP[1]));
			request.setParametrosBody(asignacionDto);
			request.setParametrosPath(asignacionDto);
			
			//Se valida que el colaborador no se asigne doble
			asignacionDto = asignacionDao.consultar(request);
			
			if(asignacionDto.getIdAsignacion()==null)
			
			{
				asignacionDto = asignacionDao.registar(request);
				colaboradorDao.editarPorcentaje(asignacionDto.getIdColaborador(), asignacionDto.getPorcentajeAsignacion());
			}
			
			System.out.println(asignacionDto.toString());
		}
		
		response.setResultados("");
		return response;
	}

	@Override
	public ResponseDTO editar(RequestDTO request) {
		response = new ResponseDTO();
		AsignacionDTO asignacionDto = new AsignacionDTO();
		
		asignacionDto = asignacionDao.editar(request);
		
		response.setResultados(asignacionDto);
		return response;
	}

	@Override
	public ResponseDTO eliminar(RequestDTO request) {
		response = new ResponseDTO();
		/*AsignacionDTO asignacionDto = new AsignacionDTO();
		
		asignacionDto = asignacionDao.eliminar(request);
		
		response.setResultados(asignacionDto);*/
		
		
		AsignacionDTO concreteAsignacionDto = (AsignacionDTO) request.getParametrosBody();
		asignacionDao.eliminar(request);
		if(concreteAsignacionDto.getPorcentajeAsignacion() == null){
			concreteAsignacionDto.setPorcentajeAsignacion(0.00);
		}
		colaboradorDAO.aumentarPorjetajeColaborador(concreteAsignacionDto.getIdColaborador(), concreteAsignacionDto.getPorcentajeAsignacion());
		response.setResultados(request.getParametrosBody());
		return response;
	}

	@Override
	public ResponseDTO listar(RequestDTO request) {
		response = new ResponseDTO();
		List<AsignacionDTO> listaAsignaciones = new ArrayList<>();
		List<IniciativaDTO> listaIniciativas = new ArrayList<>();
		List<ColaboradorDTO> listaColaboradores = new ArrayList<>();
		List<AsignacionDTOEntrega> listaRelacionAsignacion = new ArrayList<>();
		
		IniciativaDTO iniciativaDto = new IniciativaDTO();
		
		ColaboradorDTO colaboradorDto = new ColaboradorDTO();
		
		AsignacionDTOEntrega resultado;
		
		listaIniciativas = iniciativaDao.listarFiltro(request);
		
		int contador=0;
		double fulltime=0;
		for(IniciativaDTO itemI: listaIniciativas)
		{
			resultado = new AsignacionDTOEntrega();
			RequestDTO requestIniciativa = new RequestDTO();
			RequestDTO requestColaborador;
			RequestDTO requestAsignacion = new RequestDTO();
			
			iniciativaDto.setIdIniciativa(itemI.getIdIniciativa());
			requestIniciativa.setParametrosPath(iniciativaDto);
			IniciativaDTO iniciativaDtoC = new IniciativaDTO();
			iniciativaDtoC = iniciativaDao.consultarFiltro(requestIniciativa);
			
			
			AsignacionDTO asignacionDto = new AsignacionDTO();
			asignacionDto.setIdIniciativa(iniciativaDto.getIdIniciativa());
			requestAsignacion.setParametrosQuery(asignacionDto);
			
			listaAsignaciones = asignacionDao.listar(requestAsignacion);
			listaColaboradores = new ArrayList<>();
			for(AsignacionDTO item: listaAsignaciones)
			{
				requestColaborador = new RequestDTO();
				
				colaboradorDto.setIdColaborador(item.getIdColaborador());
				
				requestColaborador.setParametrosPath(colaboradorDto);
				
				colaboradorDto = colaboradorDao.consultar(requestColaborador);
				fulltime = colaboradorDto.getFulltime() - Double.parseDouble(item.getPorcentajeAsignacion().toString());
				colaboradorDto.setFulltime(Double.parseDouble(item.getPorcentajeAsignacion().toString()));
				listaColaboradores.add(colaboradorDto);
				
				//requestColaborador.setParametrosBody(colaboradorDto);
				//colaboradorDao.editar(request);
			}
			iniciativaDtoC.setContador(++contador);
			resultado.setIniciativa(iniciativaDtoC);
			resultado.setListaColaboradore(armarHtml(listaColaboradores, itemI.getIdIniciativa()));
			
			listaRelacionAsignacion.add(resultado);
		}
		
		response.setResultados(listaRelacionAsignacion);
		return response;
	}
	
	private String armarHtml(List<ColaboradorDTO> lista, Integer idIniciativa)
	{
		StringBuilder htmlCadena = new StringBuilder();
		
		for(ColaboradorDTO item: lista)
		{
			htmlCadena.append("<tr data-id=\"{{item.id}}\">");
			htmlCadena.append("<th scope=\"row\">").append("").append("</th>");
			htmlCadena.append("<td>").append(item.getNumColaborador()).append("</td>");
			htmlCadena.append("<td>").append(item.getNombre()).append("</td>");
			htmlCadena.append("<td>").append(item.getIdCentro()).append("</td>");
			htmlCadena.append("<td>").append(item.getSkills()).append("</td>");
			htmlCadena.append("<td>").append(item.getFulltime()).append("%</td>");
			htmlCadena.append("<td><a  class=\"btn btn-danger delete-button\"  onclick=\"location='/asignaciones/vistas/eliminar?idIniciativa=").append(idIniciativa).append("&idColaborador=").append(item.getIdColaborador()).append("&porcentajeasignacion=").append(item.getFulltime()).append("'\" >Eliminar</a>");
			//htmlCadena.append("<td><a  class=\"btn btn-danger delete-button\"  onclick=\"requestDeleteAsignacionesColaborador(" +idIniciativa +  ","+item.getNumColaborador() + " )\" >Eliminar</a>");
			htmlCadena.append("</tr>");
		}
		return htmlCadena.toString();
	}
	
	public ResponseDTO guardarAsignaciones(AsignacionProcesoDTO asignacion) {
		try {
			ResponseDTO response = new ResponseDTO();
			response.setResultados(asignacionDao.guardarAsignaciones(asignacion));
			return response;
		}catch(Exception e) {
			
			return null;
		}
	}
	
	
	public ResponseDTO actualizarAsignaciones(AsignacionActualizarDto asignacion) {
		try 
		{
			ResponseDTO response = new ResponseDTO();
			response.setResultados(asignacionDao.actualizarAsignacion(asignacion));
			return response;
		}catch(Exception e) {
			return null;
		}
	}
}