package com.cdm.sig.models;

import com.cdm.sig.models.integrations.CIE10;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Entity
@Table(name = "incapacidades")
public class Incapacidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIncapacidad;

    @Column
    @NotNull
    private LocalDate fechaInicio;

    @Column
    @NotNull
    private LocalDate fechaFin;

    @Column
    @NotBlank
    @NotEmpty
    private String entidad;

    @Column
    @NotBlank
    @NotEmpty
    private String enfermedad;

    @OneToOne
    private CIE10 cie10;

    @NotNull
    @OneToOne
    private Empleado empleado;

    @Column
    @NotBlank
    @NotEmpty
    private String estado;


    public Long getIdIncapacidad() {
        return idIncapacidad;
    }

    public void setIdIncapacidad(Long idIncapacidad) {
        this.idIncapacidad = idIncapacidad;
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

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public CIE10 getCie10() {
        return cie10;
    }

    public void setCie10(CIE10 cie10) {
        this.cie10 = cie10;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
