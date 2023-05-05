package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.RequestDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;
import com.bancoppel.creditocobranza.gestorcolaboradores.utilerias.ConstantesEstaticas;

@Service
public class ColaboradorSkillsSrv {

	@Autowired
	private ColaboradorSkillsDAO colaboradorSkillsDAO;
	
	public ResponseDTO guardarColaboradorSkills(RequestDTO<RelacionColaboradorSkillsRequestDTO> request) {
		RelacionColaboradorSkillsRequestDTO relacion = request.getParametrosBody() ;
		ResponseDTO response = new ResponseDTO();
		if(colaboradorSkillsDAO.guardarColaboradorSkills(relacion)){
			response.setDescripcion(ConstantesEstaticas.MENSAJE_AGREGAR_SKILLS_EXITOSO);
		    response.setCodigoEstatus(ConstantesEstaticas.CODIGO_CORRECTO);
		    return response;
		}else {
			response.setDescripcion(ConstantesEstaticas.MENSAJE_AGREGAR_SKILLS_ERROR);
		    response.setCodigoEstatus(ConstantesEstaticas.CODIGO_ERROR);
		    return response;
		}
	}
}
