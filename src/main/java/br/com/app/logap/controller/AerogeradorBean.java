package br.com.app.logap.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.ocpsoft.rewrite.annotation.Join;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.app.logap.modelo.Aerogerador;
import br.com.app.logap.modelo.ParqueEolico;
import br.com.app.logap.service.AerogeradorService;
import br.com.app.logap.service.ParqueEolicoService;


@Scope(value = "session")
@Component(value = "aerogeradorBean")
@Join(path = "/aerogerador", to = "pages/aerogerador.xhtml") 
public class AerogeradorBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = -4179521049397457722L;
	
	@Autowired
	private AerogeradorService aerogeradorProxy;
	@Autowired
	private ParqueEolicoService parqueEolicoProxy;
	
	private String parqueEolico;
	private String parqueEolicoSelecionado;

	private Aerogerador aerogerador; 
	private Aerogerador aerogeradorSelecionado;

	private List<ParqueEolico> listaParqueEolico;
	private List<Aerogerador> listaAerogerador;

	public AerogeradorBean() {
		verificarUsuarioSessao();
	}

	@PostConstruct  
	public void init() {
		aerogerador = new Aerogerador(); 
		aerogeradorSelecionado = new Aerogerador();
		carregarListas();    
	}
	
	public void cadastrar() {
		if (validarAerogerador(aerogerador)) {
			ParqueEolico parqueEolico = parqueEolicoProxy.findByName(this.parqueEolico);
			parqueEolico.adicionarAerogerador(aerogerador);
			aerogeradorProxy.save(aerogerador);
			carregarListaParqueEolico();
			limparFormulario(); 
		}else {
			addMessageError("Aerogerador já existente, por favor, escolha outro nome!");
		}
 
	}

	public void atualizar() throws Exception {

		try {
			verificarNomeIgualParqueEolico(aerogeradorSelecionado);
			ParqueEolico novoParqueEolico = parqueEolicoProxy.findByName(parqueEolicoSelecionado);
			ParqueEolico velhoComplexo = aerogeradorSelecionado.getParqueEolico();
			velhoComplexo.removerAerogerador(aerogeradorSelecionado);
			novoParqueEolico.adicionarAerogerador(aerogeradorSelecionado);
			aerogeradorProxy.save(aerogeradorSelecionado);
			carregarListaComplexoEolico();
			limparFormulario();
		} catch (Exception e) {
			addMessageError("Aerogerador já existente, por favor, escolha outro nome!");
		}
		
	} 
 
	public void remover() { 
		ParqueEolico parque = parqueEolicoProxy.findByName(aerogeradorSelecionado.getParqueEolico().getNome());
		for (Aerogerador aerogerador : parque.getAerogeradores()) {
			if(aerogerador.getId().equals(aerogeradorSelecionado.getId())) {
				parque.removerAerogerador(aerogerador);
				parqueEolicoProxy.save(parque);
				aerogeradorProxy.delete(aerogerador);
				break;
			}
		} 
		
		limparFormulario();
		carregarListaComplexoEolico();
		
		
	} 
	
	public void cancelar() {
		limparFormulario();
	}

	private boolean validarAerogerador(Aerogerador aerogerador) {
		if (!"null".equals(parqueEolico) && aerogerador.getNome() != null
				&& aerogerador.getModelo() != null) {
			return true;
		}
		return false;
	}

	public void limparFormulario() {
		parqueEolicoSelecionado = null;
		parqueEolico = null;
		aerogeradorSelecionado = new Aerogerador();
		aerogerador = new Aerogerador();
	} 

	public void carregarListas() {
		carregarListaComplexoEolico();
		carregarListaParqueEolico();
	} 

	private void verificarNomeIgualParqueEolico(Aerogerador aerogerador) throws Exception {
		if (aerogerador.getId() != null) {
			for (Aerogerador agerador : listaAerogerador) {
				if (agerador.getNome().equalsIgnoreCase(aerogerador.getNome()) && !agerador.equals(aerogerador)) {
					carregarListaComplexoEolico();
					throw new Exception();
				}
			}
		}else {
			throw new Exception();
		}

	}

	public void carregarListaParqueEolico() {
		listaAerogerador = aerogeradorProxy.listAll();
	}
       
	public void carregarListaComplexoEolico() {
		listaParqueEolico = parqueEolicoProxy.listAll();
	}           

	public void linhaSelecionada(SelectEvent event) {
		aerogeradorSelecionado = (Aerogerador) event.getObject(); 
		parqueEolicoSelecionado = aerogeradorSelecionado.getParqueEolico().getNome();
	}

	public boolean renderizarPainelEdicao() {
		if (parqueEolicoSelecionado != null) {
			return true;
		}
		return false;
	}

	public Aerogerador getAerogerador() {
		return aerogerador;
	}

	public void setAerogerador(Aerogerador aerogerador) {
		this.aerogerador = aerogerador;
	}

	public List<ParqueEolico> getListaParqueEolico() {
		return listaParqueEolico;
	}

	public String getParqueEolicoSelecionado() {
		return parqueEolicoSelecionado;
	}

	public void setParqueEolicoSelecionado(String parqueEolicoSelecionado) {
		this.parqueEolicoSelecionado = parqueEolicoSelecionado;
	}

	public List<Aerogerador> getListaAerogerador() {
		return listaAerogerador;
	}

	public Aerogerador getAerogeradorSelecionado() {
		return aerogeradorSelecionado;
	}

	public void setAerogeradorSelecionado(Aerogerador aerogerador) {
		this.aerogeradorSelecionado = aerogerador;
	}

	public String getParqueEolico() {
		return parqueEolico;
	}

	public void setParqueEolico(String complexoEolico) {
		this.parqueEolico = complexoEolico;
	}

 
}
