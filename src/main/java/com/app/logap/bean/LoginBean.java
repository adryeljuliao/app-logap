package com.app.logap.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.app.logap.model.Usuario;
import com.app.logap.utils.sessao.SessaoContexto;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 8946009153982006623L;
	private Usuario usuario;

	public LoginBean() {
		usuario = new Usuario();
	}

	public String entrar() {
		if (usuario.getNomeUsuario().equals("admin") && usuario.getSenha().equals("admin")) {
			SessaoContexto.atribuirObjeto("usuarioLogado", usuario);
			limparCampo();
			return redirecionarHome();
		}
		return "";
	}

	public String sair() {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		sessao.invalidate();
		return "login.xhtml?faces-redirect=true";
	}

	public void limparCampo() {
		usuario = new Usuario();
	}

	public String redirecionarHome() {
		return "home.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
