package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.asignaciones;

import java.util.Objects;

import javax.persistence.Column;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;

import javax.persistence.Table;

@Entity

@Table(name = "tasignaciones")
public class AsignacionEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idasignacion")
	private Integer idAsignacion;
	@Column(name = "idiniciativa")
	private Integer idIniciativa;
	@Column(name = "idcolaborador")
	private Integer idColaborador;
	@Column(name = "porcentajeasignacion")
	private Double porcentajeAsignacion;

	public AsignacionEnt() {
	}

	public AsignacionEnt(Integer idAsignacion, Integer idIniciativa, Integer idColaborador,

			Double porcentajeAsignacion) {

		this.idAsignacion = idAsignacion;

		this.idIniciativa = idIniciativa;

		this.idColaborador = idColaborador;

		this.porcentajeAsignacion = porcentajeAsignacion;

	}

	public Integer getIdAsignacion() {

		return idAsignacion;

	}

	public void setIdAsignacion(Integer idAsignacion) {

		this.idAsignacion = idAsignacion;

	}

	public Integer getIdIniciativa() {

		return idIniciativa;

	}

	public void setIdIniciativa(Integer idIniciativa) {

		this.idIniciativa = idIniciativa;

	}

	public Integer getIdColaborador() {

		return idColaborador;

	}

	public void setIdColaborador(Integer idColaborador) {

		this.idColaborador = idColaborador;

	}

	public Double getPorcentajeAsignacion() {

		return porcentajeAsignacion;

	}

	public void setPorcentajeAsignacion(Double porcentajeAsignacion) {

		this.porcentajeAsignacion = porcentajeAsignacion;

	}

	@Override

	public int hashCode() {

		return Objects.hash(idAsignacion, idColaborador, idIniciativa, porcentajeAsignacion);

	}

	@Override

	public boolean equals(Object obj) {

		if (this == obj)

			return true;

		if (obj == null)

			return false;

		if (getClass() != obj.getClass())

			return false;

		AsignacionEnt other = (AsignacionEnt) obj;

		return Objects.equals(idAsignacion, other.idAsignacion) && Objects.equals(idColaborador, other.idColaborador)

				&& Objects.equals(idIniciativa, other.idIniciativa)

				&& Objects.equals(porcentajeAsignacion, other.porcentajeAsignacion);

	}

}