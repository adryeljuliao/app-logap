package com.app.logap.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.app.logap.utils.sessao.SessaoContexto;

public class GenericBean {
	public void verificarUsuarioSessao() {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		if (SessaoContexto.recuperarObjeto("usuarioLogado") == null) {
			try {
				response.sendRedirect("login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void addMessageError(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", msg));
	}
	public void addMessageSuccess(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", msg));
	}
}
