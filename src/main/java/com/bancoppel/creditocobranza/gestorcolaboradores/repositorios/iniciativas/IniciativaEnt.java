package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.iniciativas;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tiniciativas")
public class IniciativaEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idiniciativa")
	private Integer idIniciativa;

	@Column(name = "folioiniciativaasociada")
	private String folioIniciativaAsociada;

	@Column(name = "iniciativaestrategica", columnDefinition = "TEXT")
	private String iniciativaEstrategica;

	@Column(name = "descripcion", columnDefinition = "TEXT")
	private String descripcion;

	@Column(name = "folio")
	private String folio;

	@Column(name = "numiniciativa")
	private String numIniciativa;

	@Column(name = "tituloiniciativa", columnDefinition = "TEXT")
	private String tituloIniciativa;

	@Column(name = "idsolicitante")
	private Integer idSolicitante;

	@Column(name = "idestatus")
	private Integer idEstatus;

	@Column(name = "idmodop")
	private Integer idModOp;

	@Column(name = "porcentajeavance")
	private Integer porcentajeAvance;

	@Column(name = "fechainicio")
	private String fechaInicio;

	@Column(name = "fechafin")
	private String fechaFin;

	@Column(name = "timereport", columnDefinition = "CHAR")
	private String timeReport;

	@Column(name = "numintegrantes", columnDefinition = "CHAR")
	private String numIntegrantes;

	@Column(name = "idtipoproyecto")
	private Integer idTipoProyecto;

	@Column(name = "iddireccion")
	private Integer idDireccion;

	@Column(name = "iniciativaasociada", columnDefinition = "TEXT")
	private String iniciativaAsociada;

	public IniciativaEnt() {}

	public IniciativaEnt(Integer idIniciativa, String folioIniciativaAsociada, String iniciativaEstrategica,
			String descripcion, String folio, String numIniciativa, String tituloIniciativa, Integer idSolicitante,
			Integer idEstatus, Integer idModOp, Integer porcentajeAvance, String fechaInicio, String fechaFin,
			String timeReport, String numIntegrantes, Integer idTipoProyecto, Integer idDireccion,
			String iniciativaAsociada) {
		super();
		this.idIniciativa = idIniciativa;
		this.folioIniciativaAsociada = folioIniciativaAsociada;
		this.iniciativaEstrategica = iniciativaEstrategica;
		this.descripcion = descripcion;
		this.folio = folio;
		this.numIniciativa = numIniciativa;
		this.tituloIniciativa = tituloIniciativa;
		this.idSolicitante = idSolicitante;
		this.idEstatus = idEstatus;
		this.idModOp = idModOp;
		this.porcentajeAvance = porcentajeAvance;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.timeReport = timeReport;
		this.numIntegrantes = numIntegrantes;
		this.idTipoProyecto = idTipoProyecto;
		this.idDireccion = idDireccion;
		this.iniciativaAsociada = iniciativaAsociada;
	}

	public Integer getIdIniciativa() {
		return idIniciativa;
	}

	public void setIdIniciativa(Integer idIniciativa) {
		this.idIniciativa = idIniciativa;
	}

	public String getFolioIniciativaAsociada() {
		return folioIniciativaAsociada;
	}

	public void setFolioIniciativaAsociada(String folioIniciativaAsociada) {
		this.folioIniciativaAsociada = folioIniciativaAsociada;
	}

	public String getIniciativaEstrategica() {
		return iniciativaEstrategica;
	}

	public void setIniciativaEstrategica(String iniciativaEstrategica) {
		this.iniciativaEstrategica = iniciativaEstrategica;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getNumIniciativa() {
		return numIniciativa;
	}

	public void setNumIniciativa(String numIniciativa) {
		this.numIniciativa = numIniciativa;
	}

	public String getTituloIniciativa() {
		return tituloIniciativa;
	}

	public void setTituloIniciativa(String tituloIniciativa) {
		this.tituloIniciativa = tituloIniciativa;
	}

	public Integer getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(Integer idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	public Integer getIdModOp() {
		return idModOp;
	}

	public void setIdModOp(Integer idModOp) {
		this.idModOp = idModOp;
	}

	public Integer getPorcentajeAvance() {
		return porcentajeAvance;
	}

	public void setPorcentajeAvance(Integer porcentajeAvance) {
		this.porcentajeAvance = porcentajeAvance;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getTimeReport() {
		return timeReport;
	}

	public void setTimeReport(String timeReport) {
		this.timeReport = timeReport;
	}

	public String getNumIntegrantes() {
		return numIntegrantes;
	}

	public void setNumIntegrantes(String numIntegrantes) {
		this.numIntegrantes = numIntegrantes;
	}

	public Integer getIdTipoProyecto() {
		return idTipoProyecto;
	}

	public void setIdTipoProyecto(Integer idTipoProyecto) {
		this.idTipoProyecto = idTipoProyecto;
	}

	public Integer getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Integer idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getIniciativaAsociada() {
		return iniciativaAsociada;
	}

	public void setIniciativaAsociada(String iniciativaAsociada) {
		this.iniciativaAsociada = iniciativaAsociada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, fechaFin, fechaInicio, folio, folioIniciativaAsociada, idDireccion, idEstatus,
				idIniciativa, idModOp, idSolicitante, idTipoProyecto, iniciativaAsociada, iniciativaEstrategica,
				numIniciativa, numIntegrantes, porcentajeAvance, timeReport, tituloIniciativa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IniciativaEnt other = (IniciativaEnt) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(fechaFin, other.fechaFin)
				&& Objects.equals(fechaInicio, other.fechaInicio) && Objects.equals(folio, other.folio)
				&& Objects.equals(folioIniciativaAsociada, other.folioIniciativaAsociada)
				&& Objects.equals(idDireccion, other.idDireccion) && Objects.equals(idEstatus, other.idEstatus)
				&& Objects.equals(idIniciativa, other.idIniciativa) && Objects.equals(idModOp, other.idModOp)
				&& Objects.equals(idSolicitante, other.idSolicitante)
				&& Objects.equals(idTipoProyecto, other.idTipoProyecto)
				&& Objects.equals(iniciativaAsociada, other.iniciativaAsociada)
				&& Objects.equals(iniciativaEstrategica, other.iniciativaEstrategica)
				&& Objects.equals(numIniciativa, other.numIniciativa)
				&& Objects.equals(numIntegrantes, other.numIntegrantes)
				&& Objects.equals(porcentajeAvance, other.porcentajeAvance)
				&& Objects.equals(timeReport, other.timeReport)
				&& Objects.equals(tituloIniciativa, other.tituloIniciativa);
	}

	@Override
	public String toString() {
		return "IniciativaEnt [idIniciativa=" + idIniciativa + ", folioIniciativaAsociada=" + folioIniciativaAsociada
				+ ", iniciativaEstrategica=" + iniciativaEstrategica + ", descripcion=" + descripcion + ", folio="
				+ folio + ", numIniciativa=" + numIniciativa + ", tituloIniciativa=" + tituloIniciativa
				+ ", idSolicitante=" + idSolicitante + ", idEstatus=" + idEstatus + ", idModOp=" + idModOp
				+ ", porcentajeAvance=" + porcentajeAvance + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", timeReport=" + timeReport + ", numIntegrantes=" + numIntegrantes + ", idTipoProyecto="
				+ idTipoProyecto + ", idDireccion=" + idDireccion + ", iniciativaAsociada=" + iniciativaAsociada + "]";
	}

	
}