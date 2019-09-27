package com.app.logap.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.app.logap.model.ComplexoEolico;
import com.app.logap.proxy.ComplexoEolicoProxy;
import com.app.logap.utils.exeptions.ExceptionCustom;

@ManagedBean
@ViewScoped
public class ComplexoEolicoBean {

	private ComplexoEolicoProxy complexoEolicoProxy;

	private ComplexoEolico complexoEolico;
	private ComplexoEolico complexoEolicoEscolhido;

	public ComplexoEolicoBean() {
		complexoEolicoProxy = new ComplexoEolicoProxy();
		complexoEolico = new ComplexoEolico();
		complexoEolicoEscolhido = new ComplexoEolico();
	}

	public void cadastrar() throws ExceptionCustom {
		try {
			complexoEolicoProxy.salvar(complexoEolico);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", "System Error"));
		}
		limparFormulario();
	}

	public void limparFormulario() {
		complexoEolico = new ComplexoEolico();
	}

	public ComplexoEolico getComplexoEolico() {
		return complexoEolico;
	}

	public void setComplexoEolico(ComplexoEolico complexoEolico) {
		this.complexoEolico = complexoEolico;
	}

	public List<ComplexoEolico> getListaComplexos() {
		return complexoEolicoProxy.buscarTodos();
	}

	public ComplexoEolico getComplexoEolicoEscolhido() {
		return complexoEolicoEscolhido;
	}

	public void setComplexoEolicoEscolhido(ComplexoEolico complexoEolicoEscolhido) {
		this.complexoEolicoEscolhido = complexoEolicoEscolhido;
	}

	

}
