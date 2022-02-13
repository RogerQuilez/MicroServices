package com.formacionbdi.microservicios.app.examenes.repository;

import com.formacionbdi.microservicios.commons.examenes.entity.Examen;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IExamenRepository extends CrudRepository<Examen, Long> {

    List<Examen> findByNombre(String nombre);
}
