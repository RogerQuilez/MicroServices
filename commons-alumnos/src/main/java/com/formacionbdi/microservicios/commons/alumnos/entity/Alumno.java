package com.formacionbdi.microservicios.commons.alumnos.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Alumno {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String apellido;
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	private byte[] foto;
	
	@PrePersist
	public void prePersist() {
		this.createAt = new Date();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Alumno alumno = (Alumno) o;
		return Objects.equals(id, alumno.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
