package com.formacionbdi.microservicios.app.cursos.controllers;

import com.formacionbdi.microservicios.app.cursos.entity.Curso;
import com.formacionbdi.microservicios.app.cursos.service.ICursoService;
import com.formacionbdi.microservicios.commons.alumnos.entity.Alumno;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.examenes.entity.Examen;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController extends CommonController<Curso, ICursoService> {

    @PutMapping("/{idCurso}")
    public ResponseEntity<?> update(@PathVariable Long idCurso, @RequestBody Curso curso) {
        Curso dbCurso = getCurso(idCurso);
        if (curso == null) return ResponseEntity.notFound().build();
        dbCurso.setNombre(curso.getNombre());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @GetMapping("/alumno/{idCurso}")
    public ResponseEntity<?> findByAlumnoId(@PathVariable Long idCurso) {
        return ResponseEntity.ok(service.findCursoByAlumnoId(idCurso));
    }

    @PutMapping("/{idCurso}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@PathVariable Long idCurso, @RequestBody List<Alumno> alumnoList) {
        Curso curso = getCurso(idCurso);
        if (curso == null) return ResponseEntity.notFound().build();
        alumnoList.forEach(curso::addAlumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(curso));
    }

    @PutMapping("/{idCurso}/eliminar-alumno")
    public ResponseEntity<?> aliminarAlumnos(@PathVariable Long idCurso, @RequestBody Alumno alumno) {
        Curso curso = getCurso(idCurso);
        if (curso == null) return ResponseEntity.notFound().build();
        curso.removeAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(curso));
    }

    @PutMapping("/{idCurso}/asignar-examenes")
    public ResponseEntity<?> asignarExamenes(@PathVariable Long idCurso, @RequestBody List<Examen> examenList) {
        Curso curso = getCurso(idCurso);
        if (curso == null) return ResponseEntity.notFound().build();
        examenList.forEach(curso::addExamen);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(curso));
    }

    @PutMapping("/{idCurso}/eliminar-examen")
    public ResponseEntity<?> aliminarExamen(@PathVariable Long idCurso, @RequestBody Examen examen) {
        Curso curso = getCurso(idCurso);
        if (curso == null) return ResponseEntity.notFound().build();
        curso.removeExamen(examen);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(curso));
    }

    public Curso getCurso(Long idCurso) {
        Optional<Curso> cursoOptional = this.service.findById(idCurso);
        if (cursoOptional.isEmpty()) return null;
        return cursoOptional.get();
    }

}
