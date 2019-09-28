package com.app.logap.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_parque_eolico", schema = "public")
public class ParqueEolico implements Serializable {
	private static final long serialVersionUID = -3254825614666388585L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, length = 45, nullable = false)
	private String nome;
	private Integer latitude;
	private Integer longitude;
	@Column(nullable = false)
	private Double potenciaInstalada;

	@ManyToOne
	@JoinColumn(name = "complexo_eolico_id")
	private ComplexoEolico complexoEolico;

	@OneToMany(mappedBy = "parqueEolico", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Aerogerador> aerogeradores;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

	public Double getPotenciaInstalada() {
		return potenciaInstalada;
	}

	public void setPotenciaInstalada(Double potenciaInstalada) {
		this.potenciaInstalada = potenciaInstalada;
	}

	public List<Aerogerador> getAerogeradores() {
		return aerogeradores;
	}

	public void setAerogeradores(List<Aerogerador> aerogeradores) {
		this.aerogeradores = aerogeradores;
	}

	public ComplexoEolico getComplexoEolico() {
		return complexoEolico;
	}

	public void setComplexoEolico(ComplexoEolico complexoEolico) {
		this.complexoEolico = complexoEolico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}