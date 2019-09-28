package com.app.logap.utils.sessao;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessaoContexto {

	public static Object recuperarObjeto(String key) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		HttpSession session = (HttpSession) request.getSession();
		return session.getAttribute(key);
	}

	public static void atribuirObjeto(String key, Object value) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute(key, value);
	}

}
