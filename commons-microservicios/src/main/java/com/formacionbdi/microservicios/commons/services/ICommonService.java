package com.formacionbdi.microservicios.commons.services;

import java.util.Optional;

public interface ICommonService<E> {
	
	public Iterable<E> findAll();
	public Optional<E> findById(Long id);
	public E save(E entity);
	public void remove(Long id);
	
}
