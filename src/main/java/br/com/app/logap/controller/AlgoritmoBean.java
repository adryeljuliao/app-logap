package br.com.app.logap.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "algoritmoBean")
@Join(path = "/algoritmo", to = "pages/algoritmo.xhtml")
public class AlgoritmoBean {

	private List<Integer> numerosAdicionados;
	private List<String> arraySoma;
	private Integer numero;
	private Integer numSoma;

	@PostConstruct
	private void init() {
		numerosAdicionados = new ArrayList<>();
		arraySoma = new ArrayList<>();
	}

	public void addNumero() {
		numerosAdicionados.add(numero);
		numero = null;
	} 

	public void algoritmo() {
		for (int i = 0; i < numerosAdicionados.size(); i++) {
			for (int j = i + 1; j <= numerosAdicionados.size() - 1; j++) {
				if (numerosAdicionados.get(i) + numerosAdicionados.get(j) == numSoma) {
					String numeros = "[ " + numerosAdicionados.get(i) + ", " + numerosAdicionados.get(j) + " ]";
					arraySoma.add(numeros);
				}
			}
		}
	}  
	
	public void limparCampos() {
		numSoma = null;
	}

	public String getSaida () {
		return numSoma != null ? "Números encontrados que quando somados dão " + numSoma.toString() + ": \n" + arraySoma.toString() : "Nenhum";
	}
	public Integer getNumSoma() {
		return numSoma;
	}

	public void setNumSoma(Integer numSoma) {
		this.numSoma = numSoma;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<Integer> getNumerosAdicionados() {
		return numerosAdicionados;
	}

	public void setNumerosAdicionados(List<Integer> numerosAdicionados) {
		this.numerosAdicionados = numerosAdicionados;
	}


}
