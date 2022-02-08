package com.formacionbdi.microservicios.app.cursos.service.impl;

import com.formacionbdi.microservicios.app.cursos.entity.Curso;
import com.formacionbdi.microservicios.app.cursos.repository.ICursoRepository;
import com.formacionbdi.microservicios.app.cursos.service.ICursoService;
import com.formacionbdi.microservicios.commons.services.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, ICursoRepository> implements ICursoService {

    @Override
    @Transactional(readOnly = true)
    public Curso findCursoByAlumnoId(Long id) {
        return repository.findCursoByAlumnoId(id);
    }
}
