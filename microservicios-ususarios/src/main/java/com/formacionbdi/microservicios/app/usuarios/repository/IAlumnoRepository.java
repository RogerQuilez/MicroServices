package com.formacionbdi.microservicios.app.usuarios.repository;

import com.formacionbdi.microservicios.commons.alumnos.entity.Alumno;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IAlumnoRepository extends CrudRepository<Alumno, Long> {
    public Optional<List<Alumno>> findByNombre(String nombreOApellido);

}
