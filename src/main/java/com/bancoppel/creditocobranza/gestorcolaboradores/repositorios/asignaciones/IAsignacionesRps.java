package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IAsignacionesRps extends JpaRepository<AsignacionEnt, Integer> {

	List<AsignacionEnt> findByIdIniciativa(Integer idIniciativa);
	
	Optional<AsignacionEnt> findByIdIniciativaAndIdColaborador(Integer idIniciativa, Integer idColaborador);
	
	@Modifying
	@Transactional
	void deleteByIdIniciativa(Integer idIniciativa);
	
	@Modifying
	@Transactional
	void deleteByIdColaboradorAndIdIniciativa(Integer idcolaborador, Integer idIniciativa);
	
	@Modifying
	@Transactional
	void deleteByIdColaborador(Integer idColaborador);
}