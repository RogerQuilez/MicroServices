package com.fomracionbdi.microservicios.app.usuarios.service;

import java.util.Optional;

import com.fomracionbdi.microservicios.app.usuarios.model.Alumno;

public interface IAlumnoService {
	
	public Iterable<Alumno> findAll();
	public Optional<Alumno> findById(Long id);
	public Alumno save(Alumno alumno);
	public void remove(Long id);
	
}
