package br.com.app.logap.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.app.logap.modelo.Usuario;
import br.com.app.logap.util.SessaoContexto;

@Scope(value = "session")
@Component(value = "loginBean")
@Join(path = "/", to = "pages/login.xhtml")
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
			return "complexo-eolico.xhtml"; 
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
		return "teste.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String getTeste() {
		return "JSF TESTE";
	}
}
