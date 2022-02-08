package com.formacionbdi.microservicios.app.usuarios.service.impl;

import com.formacionbdi.microservicios.app.usuarios.service.IAlumnoService;
import com.formacionbdi.microservicios.commons.alumnos.entity.Alumno;
import com.formacionbdi.microservicios.commons.services.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;

import com.formacionbdi.microservicios.app.usuarios.repository.IAlumnoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, IAlumnoRepository>
		implements IAlumnoService {

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Alumno>> findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
}
