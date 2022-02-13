package com.formacionbdi.microservicios.app.examenes.repository;

import com.formacionbdi.microservicios.commons.examenes.entity.Asignatura;
import org.springframework.data.repository.CrudRepository;

public interface IAsignaturaRepository extends CrudRepository<Asignatura, Long> {
}
