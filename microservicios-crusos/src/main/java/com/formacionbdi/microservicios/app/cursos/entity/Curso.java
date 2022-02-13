package com.formacionbdi.microservicios.app.cursos.entity;

import com.formacionbdi.microservicios.commons.alumnos.entity.Alumno;
import com.formacionbdi.microservicios.commons.examenes.entity.Examen;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Table(name="cursos")
@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name="create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date crateAt;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Alumno> alumnos;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Examen> examenes;

    @PrePersist
    public void prePersist() {
        this.crateAt = new Date();
    }

    public Curso() {
        this.alumnos = new ArrayList<>();
        this.examenes = new ArrayList<>();
    }

    public void addAlumno(Alumno alumno) {
        alumnos.add(alumno);
    }

    public void removeAlumno(Alumno alumno) {
        alumnos.remove(alumno);
    }

    public void addExamen(Examen examen) { examenes.add(examen); }

    public void removeExamen(Examen examen) { examenes.remove(examen); }

}
