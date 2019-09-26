package com.app.logap.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDaoAPI<T, ID extends Serializable> {
	void save(T object);
	void update(T object);
	void delete(T object);
	T findById(ID id);
	List<T> findAll();
}
