package br.com.app.logap.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.ocpsoft.rewrite.annotation.Join;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.app.logap.modelo.ComplexoEolico;
import br.com.app.logap.modelo.ParqueEolico;
import br.com.app.logap.service.ComplexoEolicoService;
import br.com.app.logap.service.ParqueEolicoService;

@Scope(value = "session")
@Component(value = "parqueEolicoBean")
@Join(path = "/parque-eolico", to = "pages/parque-eolico.xhtml")
public class ParqueEolicoBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = -4179521049397457722L;

	@Autowired
	private ParqueEolicoService parqueEolicoProxy;
	@Autowired
	private ComplexoEolicoService complexoEolicoProxy;

	private String complexoEolico;
	private String complexoEolicoSelecionado;

	private ParqueEolico parqueEolico;
	private ParqueEolico parqueEolicoSelecionado;

	private List<ComplexoEolico> listaComplexoEolico;
	private List<ParqueEolico> listaParqueEolico;

	public ParqueEolicoBean() {
		verificarUsuarioSessao();
	}

	@PostConstruct
	public void init() {
		parqueEolico = new ParqueEolico();
		parqueEolicoSelecionado = new ParqueEolico();
		carregarListas();
	}

	public void cadastrar() {
		if (validarParqueEolico(parqueEolico)) {
			ComplexoEolico complexoEolico = complexoEolicoProxy.findByName(this.complexoEolico);
			complexoEolico.adicionarParqueEolico(parqueEolico);
			parqueEolicoProxy.save(parqueEolico);
			carregarListaParqueEolico();
			limparFormulario();
		} else {
			addMessageError("Parque Eólico já existente, por favor, escolha outro nome!");

		}

	}

	public void atualizar() throws Exception {

		try {
			verificarNomeIgualParqueEolico(parqueEolicoSelecionado);
			ComplexoEolico novoComplexo = complexoEolicoProxy.findByName(complexoEolicoSelecionado);
			ComplexoEolico velhoComplexo = parqueEolicoSelecionado.getComplexoEolico();
			velhoComplexo.removerParqueEolico(parqueEolicoSelecionado);
			novoComplexo.adicionarParqueEolico(parqueEolicoSelecionado);
			parqueEolicoProxy.save(parqueEolicoSelecionado);
			carregarListaComplexoEolico();
			limparFormulario();
		} catch (Exception e) {
			addMessageError("Aerogerador já existente, por favor, escolha outro nome!");
		}

	}

	public void remover() {
		ComplexoEolico complexo = complexoEolicoProxy.findByName(parqueEolicoSelecionado.getComplexoEolico().getNome());

		for (ParqueEolico parque : complexo.getParquesEolicos()) {
			if (parque.getId().equals(parqueEolicoSelecionado.getId())) {
				complexo.removerParqueEolico(parque);
				complexoEolicoProxy.save(complexo);
				parqueEolicoProxy.deleteById(parque.getId());
			}
		}
		carregarListaComplexoEolico();
		limparFormulario();

	}

	public void cancelar() {
		limparFormulario();
	}

	private boolean validarParqueEolico(ParqueEolico parqueEolico) {
		if (!"null".equals(complexoEolico) && parqueEolico.getNome() != null
				&& parqueEolico.getPotenciaInstalada() != null) {
			return true;
		}
		return false;
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

	private void verificarNomeIgualParqueEolico(ParqueEolico parqueEolico) throws Exception {
		if (parqueEolico.getId() != null) {
			for (ParqueEolico parque : listaParqueEolico) {
				if (parque.getNome().equalsIgnoreCase(parqueEolico.getNome()) && !parque.equals(parqueEolico)) {
					carregarListaComplexoEolico();
					throw new Exception("Complexo Eólico já existente, por favor, escolha outro nome!");
				}
			}
		} else {
			throw new Exception("Erro ao atualizar objeto no banco de dados!");
		}

	}

	public void carregarListaParqueEolico() {
		listaParqueEolico = parqueEolicoProxy.listAll();
	}

	public void carregarListaComplexoEolico() {
		listaComplexoEolico = complexoEolicoProxy.listAll();
	}

	public void linhaSelecionada(SelectEvent event) {
		parqueEolicoSelecionado = (ParqueEolico) event.getObject();
		complexoEolicoSelecionado = parqueEolicoSelecionado.getComplexoEolico().getNome();
	}

	public boolean renderizarPainelEdicao() {
		if (complexoEolicoSelecionado != null) {
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
