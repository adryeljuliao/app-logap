package br.com.app.logap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.logap.modelo.ParqueEolico;

public interface ParqueEolicoRepository extends JpaRepository<ParqueEolico, Long>{
	ParqueEolico findByNome(String nome);
}
