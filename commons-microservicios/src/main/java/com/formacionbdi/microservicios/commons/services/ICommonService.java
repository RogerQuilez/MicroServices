package com.formacionbdi.microservicios.commons.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICommonService<E> {
	
	Iterable<E> findAll();
	Page<E> findAll(Pageable pageable);
	Optional<E> findById(Long id);
	E save(E entity);
	void remove(Long id);
	
}
