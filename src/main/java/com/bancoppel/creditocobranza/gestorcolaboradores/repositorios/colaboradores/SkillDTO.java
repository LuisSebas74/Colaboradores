package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores;

import javax.persistence.Column;

import lombok.Data;

@Data
public class SkillDTO {

    private Long idSkill;
    private String tituloSkill;
    private String descripcionSkill;

}
