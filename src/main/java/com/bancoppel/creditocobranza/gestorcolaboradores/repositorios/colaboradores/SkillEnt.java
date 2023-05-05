package com.bancoppel.creditocobranza.gestorcolaboradores.repositorios.colaboradores;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cSkills")
public class SkillEnt {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idSkill")
    private Long idSkill;
    
    @Column(name="tituloSkill")
    private String tituloSkill;
    
    @Column(name="descripcionSkill")
    private String descripcionSkill;

	public SkillEnt() {}

	public SkillEnt(Long idSkill, String tituloSkill, String descripcionSkill) {
		this.idSkill = idSkill;
		this.tituloSkill = tituloSkill;
		this.descripcionSkill = descripcionSkill;
	}

	public Long getIdSkill() {
		return idSkill;
	}

	public void setIdSkill(Long idSkill) {
		this.idSkill = idSkill;
	}

	public String getTituloSkill() {
		return tituloSkill;
	}

	public void setTituloSkill(String tituloSkill) {
		this.tituloSkill = tituloSkill;
	}

	public String getDescripcionSkill() {
		return descripcionSkill;
	}

	public void setDescripcionSkill(String descripcionSkill) {
		this.descripcionSkill = descripcionSkill;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcionSkill, idSkill, tituloSkill);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkillEnt other = (SkillEnt) obj;
		return Objects.equals(descripcionSkill, other.descripcionSkill) && Objects.equals(idSkill, other.idSkill)
				&& Objects.equals(tituloSkill, other.tituloSkill);
	}

	@Override
	public String toString() {
		return "SkillDTO [idSkill=" + idSkill + ", tituloSkill=" + tituloSkill + ", descripcionSkill="
				+ descripcionSkill + "]";
	}

}
