package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class IniciativaDTO {

	private Integer idIniciativa;
	private String folioIniciativaAsociada;
	private String iniciativaEstrategica;
	private String descripcion;
	private String folio;
	private String numIniciativa;
	private String tituloIniciativa;
	private Integer idSolicitante;
	private String tituloSolicitante;
	private Integer idEstatus;
	private String tituloEstatus;
	private Integer idModOp;
	private String tituloModOp;
	private Integer porcentajeAvance;
	private String fechaInicio;
	private String fechaFin;
	private String timeReport;
	private String numIntegrantes;
	private Integer idTipoProyecto;
	private String tituloTipoProyecto;
	private Integer idDireccion;
	private String tituloDireccion;
	private String iniciativaAsociada;
	private Integer contador;
	public IniciativaDTO  buildIniciativa(Integer idIniciativa)
	{
		this.idIniciativa = idIniciativa;
		return this;
	}
	
}