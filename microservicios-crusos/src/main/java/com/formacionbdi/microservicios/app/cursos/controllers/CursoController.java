package com.formacionbdi.microservicios.app.cursos.controllers;

import com.formacionbdi.microservicios.app.cursos.entity.Curso;
import com.formacionbdi.microservicios.app.cursos.service.ICursoService;
import com.formacionbdi.microservicios.commons.alumnos.entity.Alumno;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CursoController extends CommonController<Curso, ICursoService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Curso curso) {
        Optional<Curso> cursoOptional = this.service.findById(id);
        if (cursoOptional.isEmpty()) return ResponseEntity.notFound().build();

        Curso dbCurso = cursoOptional.get();
        dbCurso.setNombre(curso.getNombre());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{idCurso}/asignar-alumnos")
    public ResponseEntity<?> asignarAlumnos(@PathVariable Long idCurso, @RequestBody List<Alumno> alumnoList) {
        Optional<Curso> cursoOptional = this.service.findById(idCurso);
        if (cursoOptional.isEmpty()) return ResponseEntity.notFound().build();
        Curso dbCurso = cursoOptional.get();
        alumnoList.forEach(dbCurso::addAlumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @PutMapping("/{idCurso}/eliminar-alumno")
    public ResponseEntity<?> aliminarAlumnos(@PathVariable Long idCurso, @RequestBody Alumno alumno) {
        Optional<Curso> cursoOptional = this.service.findById(idCurso);
        if (cursoOptional.isEmpty()) return ResponseEntity.notFound().build();
        Curso dbCurso = cursoOptional.get();
        dbCurso.removeAlumno(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
    }

    @GetMapping("/alumno/{id}")
    public ResponseEntity<?> findByAlumnoId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findCursoByAlumnoId(id));
    }
}
