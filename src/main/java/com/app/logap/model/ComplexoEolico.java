package com.app.logap.model;

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

@Entity
@Table(name = "tbl_complexo_eolico", schema = "public")
public class ComplexoEolico implements Serializable {
	private static final long serialVersionUID = 772037773835319000L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, length = 45)
	private String nome;
	private String uf;
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

}
