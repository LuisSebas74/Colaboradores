package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tcolaboradores")
public class ColaboradorEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcolaborador")
	private Integer idColaborador;

	@Column(name = "idequipo")
	private Integer idEquipo;

	@Column(name = "idespecialidad")
	private Integer idEspecialidad;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "idgerenciasr")
	private Integer idGerenciaSR;

	@Column(name = "comentarionegocio", columnDefinition = "TEXT")
	private String comentarioNegocio;

	@Column(name = "idpuesto")
	private Integer idPuesto;

	@Column(name = "fulltime", columnDefinition = "")
	private Double fulltime;

	@Column(name = "tipocolaborador", columnDefinition = "CHAR")
	private String tipoColaborador;

	@Column(name = "idempresa")
	private Integer idEmpresa;

	@Column(name = "numcolaborador")
	private Integer numColaborador;

	@Column(name = "idcentro")
	private Integer idCentro;

	@Column(name = "correo")
	private String correo;

	@Column(name = "idlider")
	private Integer idLider;

	@Column(name = "idubicacion")
	private Integer idUbicacion;

	@Column(name = "idesquema")
	private Integer idEsquema;

	@Column(name = "ipbanco")
	private String ipBanco;

	@Column(name = "ipvpn")
	private String ipVPN;
	
	

	@Column(name = "solicitante", columnDefinition = "CHAR")
	private String solicitante;
	

	public ColaboradorEnt() {

	}

	public ColaboradorEnt(Integer idColaborador, Integer idEquipo, Integer idEspecialidad, String nombre,
			Integer idGerenciaSR, String comentarioNegocio, Integer idPuesto, Double fulltime, String tipoColaborador,
			Integer idEmpresa, Integer numColaborador, Integer idCentro, String correo, Integer idLider,
			Integer idUbicacion, Integer idEsquema, String ipBanco, String ipVPN, String solicitante) {
		this.idColaborador = idColaborador;
		this.idEquipo = idEquipo;
		this.idEspecialidad = idEspecialidad;
		this.nombre = nombre;
		this.idGerenciaSR = idGerenciaSR;
		this.comentarioNegocio = comentarioNegocio;
		this.idPuesto = idPuesto;
		this.fulltime = fulltime;
		this.tipoColaborador = tipoColaborador;
		this.idEmpresa = idEmpresa;
		this.numColaborador = numColaborador;
		this.idCentro = idCentro;
		this.correo = correo;
		this.idLider = idLider;
		this.idUbicacion = idUbicacion;
		this.idEsquema = idEsquema;
		this.ipBanco = ipBanco;
		this.ipVPN = ipVPN;
		this.solicitante = solicitante;
	}

	public Integer getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(Integer idColaborador) {
		this.idColaborador = idColaborador;
	}

	public Integer getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(Integer idEquipo) {
		this.idEquipo = idEquipo;
	}

	public Integer getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdGerenciaSR() {
		return idGerenciaSR;
	}

	public void setIdGerenciaSR(Integer idGerenciaSR) {
		this.idGerenciaSR = idGerenciaSR;
	}

	public String getComentarioNegocio() {
		return comentarioNegocio;
	}

	public void setComentarioNegocio(String comentarioNegocio) {
		this.comentarioNegocio = comentarioNegocio;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public Double getFulltime() {
		return fulltime;
	}

	public void setFulltime(Double fulltime) {
		this.fulltime = fulltime;
	}

	public String getTipoColaborador() {
		return tipoColaborador;
	}

	public void setTipoColaborador(String tipoColaborador) {
		this.tipoColaborador = tipoColaborador;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getNumColaborador() {
		return numColaborador;
	}

	public void setNumColaborador(Integer numColaborador) {
		this.numColaborador = numColaborador;
	}

	public Integer getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(Integer idCentro) {
		this.idCentro = idCentro;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getIdLider() {
		return idLider;
	}

	public void setIdLider(Integer idLider) {
		this.idLider = idLider;
	}

	public Integer getIdUbicacion() {
		return idUbicacion;
	}

	public void setIdUbicacion(Integer idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public Integer getIdEsquema() {
		return idEsquema;
	}

	public void setIdEsquema(Integer idEsquema) {
		this.idEsquema = idEsquema;
	}

	public String getIpBanco() {
		return ipBanco;
	}

	public void setIpBanco(String ipBanco) {
		this.ipBanco = ipBanco;
	}

	public String getIpVPN() {
		return ipVPN;
	}

	public void setIpVPN(String ipVPN) {
		this.ipVPN = ipVPN;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comentarioNegocio, correo, fulltime, idCentro, idColaborador, idEmpresa, idEquipo,
				idEspecialidad, idEsquema, idGerenciaSR, idLider, idPuesto, idUbicacion, ipBanco, ipVPN, nombre,
				numColaborador, solicitante, tipoColaborador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColaboradorEnt other = (ColaboradorEnt) obj;
		return Objects.equals(comentarioNegocio, other.comentarioNegocio) && Objects.equals(correo, other.correo)
				&& Objects.equals(fulltime, other.fulltime) && Objects.equals(idCentro, other.idCentro)
				&& Objects.equals(idColaborador, other.idColaborador) && Objects.equals(idEmpresa, other.idEmpresa)
				&& Objects.equals(idEquipo, other.idEquipo) && Objects.equals(idEspecialidad, other.idEspecialidad)
				&& Objects.equals(idEsquema, other.idEsquema) && Objects.equals(idGerenciaSR, other.idGerenciaSR)
				&& Objects.equals(idLider, other.idLider) && Objects.equals(idPuesto, other.idPuesto)
				&& Objects.equals(idUbicacion, other.idUbicacion) && Objects.equals(ipBanco, other.ipBanco)
				&& Objects.equals(ipVPN, other.ipVPN) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(numColaborador, other.numColaborador)
				&& Objects.equals(solicitante, other.solicitante)
				&& Objects.equals(tipoColaborador, other.tipoColaborador);
	}

}