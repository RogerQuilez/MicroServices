package com.formacionbdi.microservicios.app.examenes.service.impl;

import com.formacionbdi.microservicios.app.examenes.repository.IAsignaturaRepository;
import com.formacionbdi.microservicios.app.examenes.repository.IExamenRepository;
import com.formacionbdi.microservicios.app.examenes.service.IExamenService;
import com.formacionbdi.microservicios.commons.examenes.entity.Asignatura;
import com.formacionbdi.microservicios.commons.examenes.entity.Examen;
import com.formacionbdi.microservicios.commons.services.impl.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, IExamenRepository>
    implements IExamenService {

    @Autowired
    private IAsignaturaRepository asignaturaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Examen> findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Asignatura> findAllAsignaturas() {
        return asignaturaRepository.findAll();
    }
}
