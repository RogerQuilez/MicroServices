package com.formacionbdi.microservicios.app.usuarios.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import com.formacionbdi.microservicios.app.usuarios.service.IAlumnoService;
import com.formacionbdi.microservicios.commons.alumnos.entity.Alumno;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
public class AlumnoRestController extends CommonController<Alumno, IAlumnoService> {

	@GetMapping("/upload/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id) {
		Optional<Alumno> alumnoOptional = service.findById(id);
		if (alumnoOptional.isEmpty() || Objects.equals(alumnoOptional.get().getFoto(), null))
			return ResponseEntity.notFound().build();

		Resource imagen = new ByteArrayResource(alumnoOptional.get().getFoto());

		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(imagen);
	}

	/* Cuando recibimos una foto no viene un JSON, viene un multipartForm*/
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> createConFoto(@Valid Alumno alumno, BindingResult result,
										   @RequestParam MultipartFile archivo) throws IOException {

		if (!archivo.isEmpty()) alumno.setFoto(archivo.getBytes());
		return super.create(alumno, result);
	}

	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editConFoto(@Valid Alumno alumno, BindingResult result, @PathVariable Long id,
										 @RequestParam MultipartFile archivo) throws IOException {

		if (result.hasErrors()) return validar(result);

		Optional<Alumno> alumnoOptional = service.findById(id);

		Alumno alumnoDb = getAlumno(alumno, alumnoOptional);

		if (Objects.equals(alumnoDb, null))
			return ResponseEntity.notFound().build();

		if (!archivo.isEmpty()) alumnoDb.setFoto(archivo.getBytes());

		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);

	}

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@Valid @RequestBody Alumno alumno, BindingResult result, @PathVariable Long id) {

		if (result.hasErrors()) return validar(result);

		Optional<Alumno> alumnoOptional = service.findById(id);
		
		Alumno alumnoDb = getAlumno(alumno, alumnoOptional);

		if (Objects.equals(alumnoDb, null))
			return ResponseEntity.notFound().build();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
		
	}

	@GetMapping("/filtrar/{nombre}")
	public ResponseEntity<?> filtrar(@PathVariable String nombre) {
		Optional<List<Alumno>> alumnosOptional = service.findByNombre(nombre);
		if (alumnosOptional.isEmpty())
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(alumnosOptional.get());
	}

	private Alumno getAlumno(Alumno alumno, Optional<Alumno> alumnoOptional) {

		if (alumnoOptional.isEmpty()) return null;

		Alumno alumnoDb = alumnoOptional.get();

		alumnoDb.setNombre(alumno.getNombre());
		alumnoDb.setApellido(alumno.getApellido());
		alumnoDb.setEmail(alumno.getEmail());
		return alumnoDb;

	}
}
