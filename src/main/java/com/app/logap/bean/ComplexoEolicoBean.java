package com.app.logap.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.app.logap.model.ComplexoEolico;
import com.app.logap.proxy.ComplexoEolicoProxy;
import com.app.logap.utils.exeptions.ExceptionCustom;

@ManagedBean
@ViewScoped
public class ComplexoEolicoBean extends GenericBean {

	private ComplexoEolicoProxy complexoEolicoProxy;

	private ComplexoEolico complexoEolico;

	private ComplexoEolico complexoEolicoSelecionado;

	private List<ComplexoEolico> listaComplexoEolico;

	@PostConstruct
	public void init() {
		verificarUsuarioSessao();
		complexoEolicoProxy = new ComplexoEolicoProxy();
		complexoEolico = new ComplexoEolico();
		complexoEolicoSelecionado = new ComplexoEolico();
		carregarListaComplexoEolico();
	}

	public void cadastrar() {
		if (complexoEolicoProxy.buscar(complexoEolico).isEmpty()) {
			complexoEolicoProxy.salvar(complexoEolico);
			carregarListaComplexoEolico();
			addMessageSuccess("Complexo Eólico cadastrado com sucesso");
		} else {
			addMessageError("Complexo Eólico já cadastrado, por favor, escolha outro nome!");
		}
		limparFormulario();
	}

	public void atualizar() {
		try {
			verificarComplexoEolicoNomeIgual(complexoEolicoSelecionado);
			complexoEolicoProxy.atualizar(complexoEolicoSelecionado);
			carregarListaComplexoEolico();
		} catch (Exception e) {
			limparFormulario();
			addMessageError(e.getMessage() + "");
		}
		limparFormulario();
	}

	public void remover() {
		try {
			complexoEolicoProxy.remover(complexoEolicoSelecionado);
			carregarListaComplexoEolico();
		} catch (Exception e) {
			addMessageError(e.getMessage() + "");
		}
		limparFormulario();
	}

	private void verificarComplexoEolicoNomeIgual(ComplexoEolico complexoEolico) {
		listaComplexoEolico.stream().forEach(complexo -> {
			if (complexo.getNome().equalsIgnoreCase(complexoEolico.getNome()) && !complexo.equals(complexoEolico)) {
				throw new ExceptionCustom("Nome do complexo eólico já existe, por favor, escolha outro nome!");
			}
		});
	}

	public void limparFormulario() {
		complexoEolico = new ComplexoEolico();
		complexoEolicoSelecionado = new ComplexoEolico();
	}

	public void carregarListaComplexoEolico() {
		listaComplexoEolico = complexoEolicoProxy.buscarTodos();
	}

	public ComplexoEolico getComplexoEolico() {
		return complexoEolico;
	}

	public void setComplexoEolico(ComplexoEolico complexoEolico) {
		this.complexoEolico = complexoEolico;
	}

	public List<ComplexoEolico> getListaComplexoEolico() {
		return listaComplexoEolico;
	}

	public void setListaComplexoEolico(List<ComplexoEolico> listaComplexoEolico) {
		this.listaComplexoEolico = listaComplexoEolico;
	}

	public ComplexoEolico getComplexoEolicoSelecionado() {
		return complexoEolicoSelecionado;
	}

	public void setComplexoEolicoSelecionado(ComplexoEolico complexoEolicoSelecionado) {
		this.complexoEolicoSelecionado = complexoEolicoSelecionado;
	}

	public void linhaSelecionada(SelectEvent event) {
		complexoEolicoSelecionado = (ComplexoEolico) event.getObject();
	}

}
