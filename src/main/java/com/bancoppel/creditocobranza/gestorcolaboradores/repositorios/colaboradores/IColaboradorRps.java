package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IColaboradorRps extends JpaRepository<ColaboradorEnt, Integer> {

	//@Query("SELECT p FROM ColaboradorEnt p WHERE UPPER(p.nombre) LIKE UPPER(CONCAT('%', :nombreColaborador, '%'))")
	List<ColaboradorEnt> findByNombreContaining(String nombreColaborador);
	
	ColaboradorEnt findByNombre(String nombreColaborador); 
	
	ColaboradorEnt findByIdColaborador(Integer idColaborador);
	
	ColaboradorEnt findByNumColaborador(Integer numColaborador);
	
}