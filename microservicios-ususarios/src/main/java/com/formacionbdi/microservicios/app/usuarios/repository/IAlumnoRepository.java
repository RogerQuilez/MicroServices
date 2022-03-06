package com.formacionbdi.microservicios.app.usuarios.repository;

import com.formacionbdi.microservicios.commons.alumnos.entity.Alumno;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface IAlumnoRepository extends PagingAndSortingRepository<Alumno, Long> {

    Optional<List<Alumno>> findByNombre(String nombreOApellido);

}
