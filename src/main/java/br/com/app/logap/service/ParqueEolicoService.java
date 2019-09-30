package br.com.app.logap.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.app.logap.modelo.ParqueEolico;
import br.com.app.logap.repository.ParqueEolicoRepository;

@Service
public class ParqueEolicoService {

	@Autowired
	private ParqueEolicoRepository repository;

	public ParqueEolico save(ParqueEolico parqueEolico) {
		return repository.save(parqueEolico);
	}

	public void delete(ParqueEolico parqueEolico) {
		repository.delete(parqueEolico);
	}

	public Optional<ParqueEolico> findById(Long id) {
		return repository.findById(id);
	}

	public List<ParqueEolico> listAll() {
		return repository.findAll();
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public ParqueEolico findByName(String nome) {
		return repository.findByNome(nome);
	}

	public void deleteAll(List<ParqueEolico> lista) {
		repository.deleteAll(lista);

	}
}
