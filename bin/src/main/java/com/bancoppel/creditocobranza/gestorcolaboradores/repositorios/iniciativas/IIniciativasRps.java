package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IIniciativasRps extends JpaRepository<IniciativaEnt, Integer>, JpaSpecificationExecutor<IniciativaEnt>{

	@Query("SELECT p FROM IniciativaEnt p WHERE UPPER(p.tituloIniciativa) LIKE UPPER(CONCAT('%', :tituloIniciativa, '%'))")
	List<IniciativaEnt> findByTituloIniciativa(String tituloIniciativa);
	
	List<IniciativaEnt> findByIdEstatus(Integer idEstatus);
	
	List<IniciativaEnt> findByIdTipoProyecto(Integer idTipoProyecto);

	@Query("SELECT p FROM IniciativaEnt p WHERE p.folio = :folio")
	IniciativaEnt listarPorFolio(String folio);
	
	List<IniciativaEnt> findByFolio(String folio);

}