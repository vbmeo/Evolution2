package com.vbmeo.evolution2.service;

import java.util.List;


public interface BaseSql<T> {
	List<T> getAll();
	public T getById(Integer id);
	void insert(T object);
	public void update(T object);
	public void delete(Integer id);
	
}
