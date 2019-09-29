package br.com.app.logap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.logap.modelo.ComplexoEolico;
import br.com.app.logap.repository.ComplexoEolicoRepository;

@Service
public class ComplexoEolicoService {

	@Autowired
	private ComplexoEolicoRepository repository;
	
	public ComplexoEolico save(ComplexoEolico complexoEolico) {
		return repository.save(complexoEolico);
	}
	
	public ComplexoEolico update(ComplexoEolico complexoEolico) {
		return repository.save(complexoEolico);
	}

	public void delete(ComplexoEolico tarefa) {
		repository.delete(tarefa);
	}

	public Optional<ComplexoEolico> findById(Long id) {
		return repository.findById(id);
	}

	public List<ComplexoEolico> listAll() {
		return repository.findAll();
	}

	public void deleteById(Long id) {
		 repository.deleteById(id);
	}

	public void deleteAll(List<ComplexoEolico> lista) {
		repository.deleteAll(lista);
	}
	
	public ComplexoEolico findByName(String nome) {
		return repository.findByNome(nome);
	}
}
