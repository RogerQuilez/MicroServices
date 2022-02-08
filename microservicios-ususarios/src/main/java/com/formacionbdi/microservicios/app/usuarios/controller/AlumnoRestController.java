package com.formacionbdi.microservicios.app.usuarios.controller;

import java.util.List;
import java.util.Optional;


import com.formacionbdi.microservicios.app.usuarios.service.IAlumnoService;
import com.formacionbdi.microservicios.commons.alumnos.entity.Alumno;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlumnoRestController extends CommonController<Alumno, IAlumnoService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Alumno> alumnoOptional = service.findById(id);
		
		if (alumnoOptional.isEmpty())
			return ResponseEntity.notFound().build();
		
		Alumno alumnoDb = alumnoOptional.get();
		
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
		
	}

	@GetMapping("/filtrar/{nombre}")
	public ResponseEntity<?> filtrar(@PathVariable String nombre) {
		Optional<List<Alumno>> alumnosOptional = service.findByNombre(nombre);
		if (alumnosOptional.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(alumnosOptional.get());
	}
}
