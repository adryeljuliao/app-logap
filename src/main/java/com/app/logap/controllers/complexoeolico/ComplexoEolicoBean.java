package com.app.logap.controllers.complexoeolico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.app.logap.controllers.GenericBean;
import com.app.logap.models.ComplexoEolico;
import com.app.logap.proxys.ComplexoEolicoProxy;
import com.app.logap.utils.exeptions.ExceptionCustom;

@ManagedBean
@ViewScoped
public class ComplexoEolicoBean extends GenericBean {

	private static final long serialVersionUID = -3293457316488251119L;

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
		if (complexoEolicoProxy.buscar(complexoEolico.getNome()).isEmpty()) {
			complexoEolicoProxy.salvar(complexoEolico);
			carregarListaComplexoEolico();
			addMessageSuccess("Complexo Eólico cadastrado com sucesso");
			limparFormulario();
		} else {
			addMessageError("Complexo Eólico já cadastrado, por favor, escolha outro nome!");
		}
		
	}

	public void atualizar() {
		try {
			verificarNomeIgualComplexoEolico(complexoEolicoSelecionado);
			complexoEolicoProxy.atualizar(complexoEolicoSelecionado);
			carregarListaComplexoEolico();
		} catch (Exception e) {
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

	private void verificarNomeIgualComplexoEolico(ComplexoEolico complexoEolico) {
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
