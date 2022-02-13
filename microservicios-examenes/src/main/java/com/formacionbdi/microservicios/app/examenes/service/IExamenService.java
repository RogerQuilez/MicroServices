package com.formacionbdi.microservicios.app.examenes.service;

import com.formacionbdi.microservicios.commons.examenes.entity.Asignatura;
import com.formacionbdi.microservicios.commons.examenes.entity.Examen;
import com.formacionbdi.microservicios.commons.services.ICommonService;

import java.util.List;

public interface IExamenService extends ICommonService<Examen> {

    List<Examen> findByNombre(String nombre);
    Iterable<Asignatura> findAllAsignaturas();
}
