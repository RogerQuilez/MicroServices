package com.formacionbdi.microservicios.app.cursos.service;

import com.formacionbdi.microservicios.app.cursos.entity.Curso;
import com.formacionbdi.microservicios.commons.services.ICommonService;

public interface ICursoService extends ICommonService<Curso> {

    Curso findCursoByAlumnoId(Long id);
}
