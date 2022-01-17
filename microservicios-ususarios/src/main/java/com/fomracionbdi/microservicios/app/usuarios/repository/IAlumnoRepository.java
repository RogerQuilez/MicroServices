package com.fomracionbdi.microservicios.app.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fomracionbdi.microservicios.app.usuarios.model.Alumno;

public interface IAlumnoRepository extends JpaRepository<Alumno, Long> {

}
