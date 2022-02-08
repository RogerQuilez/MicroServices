package com.formacionbdi.microservicios.app.cursos.repository;

import com.formacionbdi.microservicios.app.cursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ICursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c from Curso c join fetch c.alumnos a WHERE a.id=?1")
    Curso findCursoByAlumnoId(Long id);
}
