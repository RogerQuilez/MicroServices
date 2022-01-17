package com.fomracionbdi.microservicios.app.usuarios.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fomracionbdi.microservicios.app.usuarios.model.Alumno;
import com.fomracionbdi.microservicios.app.usuarios.service.IAlumnoService;

@RestController
public class AlumnoRestController {
	
	@Autowired
	private IAlumnoService alumnoService;

	@GetMapping
	public ResponseEntity<?> findAll() {
		return ResponseEntity.ok().body(alumnoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Optional<Alumno> alumno = alumnoService.findById(id);
		if (alumno.isEmpty()) {
			return ResponseEntity.notFound().build();			
		}
		
		return ResponseEntity.ok(alumno.get());
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Alumno alumno) {
		Alumno alumnoDb = alumnoService.save(alumno);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@RequestBody Alumno alumno, @PathVariable Long id) {
		Optional<Alumno> alumnoOptional = alumnoService.findById(id);
		
		if (alumnoOptional.isEmpty())
			return ResponseEntity.notFound().build();
		
		Alumno alumnoDb = alumnoOptional.get();
		
		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		alumnoService.remove(id);
		return ResponseEntity.noContent().build();
	}
}