package com.app.logap.repositories;

import java.io.Serializable;
import java.util.List;

public interface GenericDaoAPI<T, ID extends Serializable> {
	void save(T object);
	void update(T object);
	void remove(T object);
	T findById(ID id);
	List<T> findAll();
}
