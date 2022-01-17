package com.fomracionbdi.microservicios.app.usuarios.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fomracionbdi.microservicios.app.usuarios.model.Alumno;
import com.fomracionbdi.microservicios.app.usuarios.repository.IAlumnoRepository;
import com.fomracionbdi.microservicios.app.usuarios.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService {
	
	@Autowired
	public IAlumnoRepository alumnoRepo;

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {
		return alumnoRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long id) {
		return alumnoRepo.findById(id);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return alumnoRepo.save(alumno);
	}

	@Override
	@Transactional
	public void remove(Long id) {
		alumnoRepo.deleteById(id);
	}
	
}
