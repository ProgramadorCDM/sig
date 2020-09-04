package com.cdm.sig.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "capacitaciones")
public class Capacitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCapacitacion;

    @Column
    @NotBlank
    private String tema;

    @Column
    @NotNull
    private LocalDate fecha;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "capacitaciones_empleados",
            joinColumns = @JoinColumn(name = "capacitacion_id_capacitacion"),
            inverseJoinColumns = @JoinColumn(name = "empleados_cedula"))
    private List<Empleado> empleados;


    public Capacitacion() {
        empleados = new ArrayList<>();
    }

    public void addEmpleado(Empleado empleado) {
        this.empleados.add(empleado);
    }

    public void removeEmpleado(Empleado empleado) {
        this.empleados.remove(empleado);
    }

    public Long getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(Long idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}
