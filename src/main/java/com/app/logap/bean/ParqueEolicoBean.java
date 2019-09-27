package com.app.logap.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.app.logap.model.ComplexoEolico;
import com.app.logap.model.ParqueEolico;
import com.app.logap.proxy.ComplexoEolicoProxy;
import com.app.logap.proxy.ParqueEolicoProxy;

@ManagedBean
@ViewScoped
public class ParqueEolicoBean {

	private ParqueEolicoProxy parqueEolicoProxy;
	private ComplexoEolicoProxy complexoEolicoProxy;
	private ComplexoEolico complexoEolico;
	private ParqueEolico parqueEolico;

	public ParqueEolicoBean() {
		complexoEolicoProxy = new ComplexoEolicoProxy();
		parqueEolico = new ParqueEolico();
	}

	public void cadastrar() {
		parqueEolicoProxy.salvar(parqueEolico);
		limparFormulario();
	}
	
	public void limparFormulario() {
		parqueEolico = new ParqueEolico();
	}

	public ParqueEolico getParqueEolico() {
		return parqueEolico;
	}

	public void setParqueEolico(ParqueEolico parqueEolico) {
		this.parqueEolico = parqueEolico;
	}
	
//	public List<ComplexoEolico> getListaComplexoEolico() {
//		return complexoEolicoProxy.buscarTodos();
//	}
	
	public ComplexoEolico getComplexoEolico() {
		return complexoEolico;
	}
	
	public void setComplexoEolico(ComplexoEolico complexoEolico) {
		this.complexoEolico = complexoEolico;
	}

	
}
