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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tbl_complexo_eolico", schema = "public")
public class ComplexoEolico implements Serializable {
	private static final long serialVersionUID = 772037773835319000L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Column(unique = true, length = 45, nullable = false)
	private String nome;
	@Column(length = 45)
	private String uf;
	@Column(length = 45)
	private String identificador;

	@OneToMany(mappedBy = "complexoEolico", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ParqueEolico> parquesEolicos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public List<ParqueEolico> getParquesEolicos() {
		return parquesEolicos;
	}

	public void setParquesEolicos(List<ParqueEolico> parquesEolicos) {
		this.parquesEolicos = parquesEolicos;
	}
	
	public void adicionarParqueEolico(ParqueEolico parqueEolico) {
		parquesEolicos.add(parqueEolico);
		parqueEolico.setComplexoEolico(this);
	}
	
	public void removerParqueEolico(ParqueEolico parqueEolico) {
		parquesEolicos.remove(parqueEolico);
		parqueEolico.setComplexoEolico(null);
	}
	
}
