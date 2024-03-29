package br.com.app.logap.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_aerogerador", schema = "public")
public class Aerogerador implements Serializable {

	private static final long serialVersionUID = -4520877008690990114L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true, length = 45, nullable = false)
	private String nome;
	private Double latitude;
	private Double longitude;
	private Double alturaTorre;
	private Double diametroVarredura;
	@Column(length = 45, nullable = false)
	private String modelo;

	@ManyToOne
	@JoinColumn(name = "id_parque_eolico")
	@JsonIgnore
	private ParqueEolico parqueEolico;

	public Aerogerador() {
	}

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

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAlturaTorre() {
		return alturaTorre;
	}

	public void setAlturaTorre(Double alturaTorre) {
		this.alturaTorre = alturaTorre;
	}

	public Double getDiametroVarredura() {
		return diametroVarredura;
	}

	public void setDiametroVarredura(Double diametroVarredura) {
		this.diametroVarredura = diametroVarredura;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public ParqueEolico getParqueEolico() {
		return parqueEolico;
	}

	public void setParqueEolico(ParqueEolico parqueEolico) {
		this.parqueEolico = parqueEolico;
	}

}
