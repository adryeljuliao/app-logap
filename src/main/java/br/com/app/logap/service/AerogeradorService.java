package br.com.app.logap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.logap.modelo.Aerogerador;
import br.com.app.logap.modelo.ComplexoEolico;
import br.com.app.logap.modelo.ParqueEolico;
import br.com.app.logap.repository.AerogeradorRespository;
import br.com.app.logap.repository.ComplexoEolicoRepository;
import br.com.app.logap.repository.ParqueEolicoRepository;

@Service
public class AerogeradorService {

	@Autowired
	private AerogeradorRespository repository;
	
	public Aerogerador save(Aerogerador parqueEolico) {
		return repository.save(parqueEolico);
	}

	public void delete(Aerogerador tarefa) {
		repository.delete(tarefa);
	}

	public Optional<Aerogerador> findById(Long id) {
		return repository.findById(id);
	}
	
	public Aerogerador findByName(String nome) {
		return repository.findByNome(nome);
	}

	public List<Aerogerador> listAll() {
		return repository.findAll();
	}

	public void deleteById(Long id) {
		 repository.deleteById(id);
	}

	public void deleteAll(List<Aerogerador> lista) {
		repository.deleteAll(lista);
		
	}
}
