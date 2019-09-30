package br.com.app.logap.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.ocpsoft.rewrite.annotation.Join;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.app.logap.modelo.ComplexoEolico;
import br.com.app.logap.service.ComplexoEolicoService;

@Scope(value = "session")
@Component(value = "complexoEolicoBean")
@Join(path = "/complexo-eolico", to = "pages/complexo-eolico.xhtml")
public class ComplexoEolicoBean extends GenericBean implements Serializable {

	private static final long serialVersionUID = -3293457316488251119L;

	@Autowired
	private ComplexoEolicoService complexoEolicoProxy;

	private ComplexoEolico complexoEolico;

	private ComplexoEolico complexoEolicoSelecionado;

	private List<ComplexoEolico> listaComplexoEolico;

	public ComplexoEolicoBean() {
		verificarUsuarioSessao();
	}

	@PostConstruct
	public void init() {
		complexoEolico = new ComplexoEolico();
		complexoEolicoSelecionado = new ComplexoEolico();
		carregarListaComplexoEolico();
	}

	public void cadastrar() {
		try {
			complexoEolicoProxy.save(complexoEolico);
			limparFormulario();
			carregarListaComplexoEolico();

		} catch (Exception e) {
			addMessageError("Complexo Eólico já existente, por favor, escolha outro nome!");

		}
	}

	public void atualizar() {
		try {
			verificarNomeIgualComplexoEolico(complexoEolicoSelecionado);
			complexoEolicoProxy.update(complexoEolicoSelecionado);
			limparFormulario();
			carregarListaComplexoEolico();
		} catch (Exception e) {
			addMessageError("Complexo Eólico já existente, por favor, escolha outro nome!");
		}

	}

	public void remover() {
		complexoEolicoProxy.delete(complexoEolicoSelecionado);
		limparFormulario();
		carregarListaComplexoEolico();
	}

	public void cancelar() {
		limparFormulario();
	}

	private void verificarNomeIgualComplexoEolico(ComplexoEolico complexoEolico) throws Exception {
		if (complexoEolico.getId() != null) {
			for (ComplexoEolico complexo : listaComplexoEolico) {
				if (complexo.getNome().equalsIgnoreCase(complexoEolico.getNome()) && !complexo.equals(complexoEolico)) {
					carregarListaComplexoEolico();
					throw new Exception("Complexo Eólico já existente, por favor, escolha outro nome!");
				}
			}
		} else {
			throw new Exception("Erro ao atualizar objeto no banco de dados!");
		}

	}

	public void limparFormulario() {
		complexoEolico = new ComplexoEolico();
		complexoEolicoSelecionado = new ComplexoEolico();
	}

	public void carregarListaComplexoEolico() {
		listaComplexoEolico = complexoEolicoProxy.listAll();
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

	public ComplexoEolico getComplexoEolicoSelecionado() {
		return complexoEolicoSelecionado;
	}

	public void setComplexoEolicoSelecionado(ComplexoEolico complexoEolicoSelecionado) {
		this.complexoEolicoSelecionado = complexoEolicoSelecionado;
	}

	public void linhaSelecionada(SelectEvent event) {
		complexoEolicoSelecionado = (ComplexoEolico) event.getObject();
	}

	public void linhaDeselecionada(UnselectEvent event) {
		complexoEolicoSelecionado = new ComplexoEolico();
	}

}
