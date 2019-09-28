package com.app.logap.controllers.parqueeolico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.app.logap.controllers.GenericBean;
import com.app.logap.models.ComplexoEolico;
import com.app.logap.models.ParqueEolico;
import com.app.logap.proxys.ComplexoEolicoProxy;
import com.app.logap.proxys.ParqueEolicoProxy;

@ManagedBean
@ViewScoped
public class ParqueEolicoBean extends GenericBean {

	private ParqueEolicoProxy parqueEolicoProxy;

	private ComplexoEolicoProxy complexoEolicoProxy;

	private String complexoEolicoSelecionado;

	private ParqueEolico parqueEolico;
	private List<ComplexoEolico> listaComplexoEolico;

	@PostConstruct
	public void init() {
		verificarUsuarioSessao();
		complexoEolicoProxy = new ComplexoEolicoProxy();
		parqueEolicoProxy = new ParqueEolicoProxy();
		parqueEolico = new ParqueEolico();
		carregarListas();
	}

	public void cadastrar() {
		if (validarParqueEolico(parqueEolico)) {
			ComplexoEolico complexoEolico = buscarComplexoEolico(complexoEolicoSelecionado);
			complexoEolico.adicionarParqueEolico(parqueEolico);
			parqueEolicoProxy.salvar(parqueEolico);
			limparFormulario();
		} else {
			addMessageError("Por favor, preencha os campos obrigatórios!");
		}

	}

	private boolean validarParqueEolico(ParqueEolico parqueEolico) {
		if (!"null".equals(complexoEolicoSelecionado) && parqueEolico.getNome() != null
				&& parqueEolico.getPotenciaInstalada() != null) {
			return true;
		}
		return false;
	}

	public ComplexoEolico buscarComplexoEolico(String nome) {
		List<ComplexoEolico> complexoEolico = complexoEolicoProxy.buscar(nome);
		return !complexoEolico.isEmpty() ? complexoEolico.get(0) : null;
	}

	public void limparFormulario() {
		complexoEolicoSelecionado = null;
		parqueEolico = new ParqueEolico();
	}

	public void carregarListas() {
		listaComplexoEolico = complexoEolicoProxy.buscarTodos();
	}

	public ParqueEolico getParqueEolico() {
		return parqueEolico;
	}

	public void setParqueEolico(ParqueEolico parqueEolico) {
		this.parqueEolico = parqueEolico;
	}

	public List<ComplexoEolico> getListaComplexoEolico() {
		return listaComplexoEolico;
	}

	public void setListaComplexoEolico(List<ComplexoEolico> listaComplexoEolico) {
		this.listaComplexoEolico = listaComplexoEolico;
	}

	public String getComplexoEolicoSelecionado() {
		return complexoEolicoSelecionado;
	}

	public void setComplexoEolicoSelecionado(String complexoEolicoSelecionado) {
		this.complexoEolicoSelecionado = complexoEolicoSelecionado;
	}

}
