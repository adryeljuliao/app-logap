package br.com.app.logap.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface IGenericDao<T, ID extends Serializable> {

	T save(T object);

	void delete(T object);

	Optional<T> findById(Long id);

	List<T> listAll();
	
	void deleteById(Long id);
	
	void deleteAll(List<T> lista);


}
