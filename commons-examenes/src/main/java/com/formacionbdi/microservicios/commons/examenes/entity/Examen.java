package com.formacionbdi.microservicios.commons.examenes.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "examenes")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min = 4, max = 30)
    private String nombre;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "crate_at")
    private Date createAt;

    @JsonIgnoreProperties(value = {"examen"}) //Evitamos la relación inversa y así no se genera un loop infinito
    @OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> preguntas;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Asignatura asignatura;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Examen() {
        this.preguntas = new ArrayList<>();
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas.clear();
        preguntas.forEach(this::addPregunta);
    }

    public void addPregunta(Pregunta pregunta) {
        this.preguntas.add(pregunta);
        pregunta.setExamen(this);
    }

    public void removePregunta(Pregunta pregunta) {
        this.preguntas.remove(pregunta);
        pregunta.setExamen(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Examen examen = (Examen) o;
        return Objects.equals(id, examen.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
