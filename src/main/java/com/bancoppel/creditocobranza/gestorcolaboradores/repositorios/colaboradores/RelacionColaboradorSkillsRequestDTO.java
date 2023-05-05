package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RelacionColaboradorSkillsRequestDTO {

	private Integer idColaborador;
	private List<Integer> listaSkill;
}
