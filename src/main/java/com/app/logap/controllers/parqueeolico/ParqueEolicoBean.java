package com.app.logap.controllers.parqueeolico;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.app.logap.controllers.GenericBean;
import com.app.logap.models.ComplexoEolico;
import com.app.logap.models.ParqueEolico;
import com.app.logap.proxys.ComplexoEolicoProxy;
import com.app.logap.proxys.ParqueEolicoProxy;
import com.app.logap.utils.exeptions.ExceptionCustom;

@ManagedBean
@ViewScoped
public class ParqueEolicoBean extends GenericBean {

	private static final long serialVersionUID = 2753206097440632076L;

	private ParqueEolicoProxy parqueEolicoProxy;
	private ComplexoEolicoProxy complexoEolicoProxy;

	private String complexoEolico;
	private String complexoEolicoSelecionado;

	private ParqueEolico parqueEolico;
	private ParqueEolico parqueEolicoSelecionado;

	private List<ComplexoEolico> listaComplexoEolico;
	private List<ParqueEolico> listaParqueEolico;

	@PostConstruct
	public void init() {
		verificarUsuarioSessao();
		complexoEolicoProxy = new ComplexoEolicoProxy();
		parqueEolicoProxy = new ParqueEolicoProxy();
		parqueEolico = new ParqueEolico();
		parqueEolicoSelecionado = new ParqueEolico();
		carregarListas();
	}

	public void cadastrar() {
		if (validarParqueEolico(parqueEolico)) {
			ComplexoEolico complexoEolico = buscarComplexoEolico(this.complexoEolico);
			complexoEolico.adicionarParqueEolico(parqueEolico);
			parqueEolicoProxy.salvar(parqueEolico);
			carregarListaParqueEolico();
			limparFormulario();
		} else {
			addMessageError("Por favor, preencha os campos obrigatórios!");
		}

	}

	public void atualizar() {
		
		try {
			verificarNomeIgualParqueEolico(parqueEolicoSelecionado);
			ComplexoEolico novoComplexo = complexoEolicoProxy.buscar(complexoEolicoSelecionado).get(0);
			ComplexoEolico velhoComplexo = parqueEolicoSelecionado.getComplexoEolico();
			velhoComplexo.removerParqueEolico(parqueEolicoSelecionado);
			novoComplexo.adicionarParqueEolico(parqueEolicoSelecionado);
			parqueEolicoProxy.atualizar(parqueEolicoSelecionado);
			carregarListaComplexoEolico();
		} catch (Exception e) {
			addMessageError(e.getMessage() + "");
		}
		limparFormulario();
	}

	public void remover() {
		try {
			parqueEolicoProxy.remover(parqueEolicoSelecionado);
			carregarListaParqueEolico();
		} catch (Exception e) {
			addMessageError(e.getMessage() + "");
		}
	}

	private boolean validarParqueEolico(ParqueEolico parqueEolico) {
		if (!"null".equals(complexoEolico) && parqueEolico.getNome() != null
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
		complexoEolico = null;
		parqueEolicoSelecionado = new ParqueEolico();
		parqueEolico = new ParqueEolico();
	}

	public void carregarListas() {
		carregarListaComplexoEolico();
		carregarListaParqueEolico();
	}

	private void verificarNomeIgualParqueEolico(ParqueEolico parqueEolico) {
		listaParqueEolico.stream().forEach(parque -> {
			if (parque.getNome().equalsIgnoreCase(parqueEolico.getNome()) && !parque.equals(parqueEolico)) {
				throw new ExceptionCustom("Nome do parque eólico já existe, por favor, escolha outro nome!");
			}
		});

	}

	public void carregarListaParqueEolico() {
		listaParqueEolico = parqueEolicoProxy.buscarTodos();
	}

	public void carregarListaComplexoEolico() {
		listaComplexoEolico = complexoEolicoProxy.buscarTodos();
	}

	public void linhaSelecionada(SelectEvent event) {
		parqueEolicoSelecionado = (ParqueEolico) event.getObject();
		complexoEolicoSelecionado = parqueEolicoSelecionado.getComplexoEolico().getNome();
	}
	
	public boolean renderizarPainelEdicao() {
		if(complexoEolicoSelecionado != null) {
			return true;
		}
		return false;
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

	public String getComplexoEolicoSelecionado() {
		return complexoEolicoSelecionado;
	}

	public void setComplexoEolicoSelecionado(String complexoEolicoSelecionado) {
		this.complexoEolicoSelecionado = complexoEolicoSelecionado;
	}

	public List<ParqueEolico> getListaParqueEolico() {
		return listaParqueEolico;
	}

	public ParqueEolico getParqueEolicoSelecionado() {
		return parqueEolicoSelecionado;
	}

	public void setParqueEolicoSelecionado(ParqueEolico parqueEolicoSelecionado) {
		this.parqueEolicoSelecionado = parqueEolicoSelecionado;
	}

	public String getComplexoEolico() {
		return complexoEolico;
	}

	public void setComplexoEolico(String complexoEolico) {
		this.complexoEolico = complexoEolico;
	}
}
