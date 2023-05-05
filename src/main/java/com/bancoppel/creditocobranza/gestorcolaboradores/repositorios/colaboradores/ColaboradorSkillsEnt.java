package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores;

import java.util.Objects;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tcolaboradorskills")
public class ColaboradorSkillsEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcolaboradorskill")
	private Integer idColaboradorSkill;

	@Column(name = "idcolaborador")
	private Integer idColaborador;
	
	@Column(name = "idskill")
	private Integer idSkill;

	

}
