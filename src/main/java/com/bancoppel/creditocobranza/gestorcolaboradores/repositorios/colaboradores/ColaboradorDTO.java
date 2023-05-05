package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas.IniciativaDTO;

import lombok.Data;

@Data
public class ColaboradorDTO {

	private Integer idColaborador;
	private Integer idEquipo;
	private String tituloEquipo;
	private Integer idEspecialidad;
	private String tituloEspecialidad;
	private String nombre;
	private Integer idGerenciaSR;
	private String tituloGenrenciaSR;
	private String comentarioNegocio;
	private Integer idPuesto;
	private String tituloPuesto;
	private Double fulltime;
	private String tipoColaborador;
	private String tituloTipoColaborador;
	private Integer idEmpresa;
	private String tituloEmpresa;
	private Integer numColaborador;
	private Integer idCentro;
	private String tituloCentro;
	private String correo;
	private Integer idLider;
	private String lider;
	private Integer idUbicacion;
	private String tituloUbicacion;
	private Integer idEsquema;
	private String tituloEsquema;
	private String ipBanco;
	private String ipVPN;
	private String solicitante;
	private Integer contador;
	private String skills;
	public ColaboradorDTO  buildColaborador(Integer idColaborador)
	{
		this.idColaborador = idColaborador;
		return this;
	}

}