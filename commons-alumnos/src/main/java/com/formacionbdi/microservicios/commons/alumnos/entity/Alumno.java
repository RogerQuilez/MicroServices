package com.formacionbdi.microservicios.commons.alumnos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Alumno {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellido;

	@NotEmpty
	@Email
	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@Lob
	@JsonIgnore
	private byte[] foto;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	public Integer getFotoHashCode() {
		return !Objects.equals(foto, null) ? Arrays.hashCode(foto) : null;
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
