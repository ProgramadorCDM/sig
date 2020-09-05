package com.cdm.sig.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vacaciones")
public class Vacaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long idVacaciones;

    @Column
    @NotNull
    public LocalDate fechaInicio;

    @Column
    @NotNull
    public LocalDate fechaFin;

    @OneToOne
    public Contrato contrato;

    public Long getIdVacaciones() {
        return idVacaciones;
    }

    public void setIdVacaciones(Long idVacaciones) {
        this.idVacaciones = idVacaciones;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
