package com.formacionbdi.microservicios.app.examenes.controller;

import com.formacionbdi.microservicios.app.examenes.service.IExamenService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.examenes.entity.Examen;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ExamenController extends CommonController<Examen, IExamenService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Examen examen, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) return validar(result);

        Optional<Examen> examenOptional = service.findById(id);

        if (examenOptional.isEmpty())
            return ResponseEntity.notFound().build();

        Examen examenDb = examenOptional.get();

        examenDb.setNombre(examen.getNombre());

        examenDb.getPreguntas()
            .stream()
            .filter(p -> !examen.getPreguntas().contains(p))
            .forEach(examenDb::removePregunta);

        examenDb.setPreguntas(examen.getPreguntas());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));

    }

    @GetMapping("/filtrar/{nombre}")
    public ResponseEntity<?> filtrar(@PathVariable String nombre) {
        return ResponseEntity.ok(service.findByNombre(nombre));
    }

    @GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas() {
        return ResponseEntity.ok(service.findAllAsignaturas());
    }

}
