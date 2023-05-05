package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bancoppel.creditocobranza.gestorcolaboradores.solicitudrespuesta.ResponseDTO;

@Component
public class ColaboradorSkillsDAO {
   @Autowired
   private IColaboradorSkillsRps colaboradorSkillsRps;
   @Autowired
   private IColaboradorRps colaboradorRps;
   
   public Boolean guardarColaboradorSkills(RelacionColaboradorSkillsRequestDTO relacion) {
	   
	   try {
		  
		   ColaboradorDTO colaborador = new ModelMapper().map(colaboradorRps.findById(relacion.getIdColaborador()), ColaboradorDTO.class);
		   if(colaborador!=null){
			   for(Integer idSkill : relacion.getListaSkill()){
				   ColaboradorSkillsEnt dto = new ColaboradorSkillsEnt(); 
				   dto.setIdColaborador(relacion.getIdColaborador());
				   dto.setIdSkill(idSkill);
			       colaboradorSkillsRps.save(dto);				   
			   }
			   
			   return true;
			   
		   }else {
			   return false;
		   }
		  
	   }catch(Exception e){
		   e.printStackTrace();
		   return null;
	   }
   }
     
   private ColaboradorSkillsDTO entityToDto(ColaboradorSkillsEnt ent) {
	   return new ModelMapper().map(ent, ColaboradorSkillsDTO.class);
   }
}

