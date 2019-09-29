package br.com.app.logap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.app.logap.modelo.ComplexoEolico;

public interface ComplexoEolicoRepository extends JpaRepository<ComplexoEolico, Long>{
	ComplexoEolico findByNome(String nome);
}
