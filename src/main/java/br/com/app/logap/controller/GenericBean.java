package br.com.app.logap.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import br.com.app.logap.util.SessaoContexto;

@Component(value = "genericBean")
@Join(path = "/sair", to = "pages/login.xhtml")
public class GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public GenericBean() {
	}

	public void verificarUsuarioSessao() {
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
				.getResponse();
		if (SessaoContexto.recuperarObjeto("usuarioLogado") == null) {
			try {
				response.sendRedirect("/login");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String sair() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		sessao.invalidate();
		return "login.xhtml";
	}

	public void addMessageError(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERRO!", msg));
	}

	public void addMessageSuccess(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "SUCESSO!", msg));
	}
}
