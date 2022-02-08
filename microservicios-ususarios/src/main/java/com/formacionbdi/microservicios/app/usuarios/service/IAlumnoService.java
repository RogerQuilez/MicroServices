package com.formacionbdi.microservicios.app.usuarios.service;

import com.formacionbdi.microservicios.commons.alumnos.entity.Alumno;
import com.formacionbdi.microservicios.commons.services.ICommonService;

import java.util.List;
import java.util.Optional;

public interface IAlumnoService extends ICommonService<Alumno> {

    public Optional<List<Alumno>> findByNombre(String nombre);
	
}
