package com.cdm.sig.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "examenes")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExamen;

    @NotNull
    @Column
    private LocalDate fecha;

    @NotNull
    @Column
    private boolean concepto;


    @Column(columnDefinition = "varchar(255)  default 'Sin Restricción'")
    private String restriccion;

    @Column
    @NotBlank
    private String tipoExamen;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Contrato contrato;

    public Long getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Long idExamen) {
        this.idExamen = idExamen;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isConcepto() {
        return concepto;
    }

    public void setConcepto(boolean concepto) {
        this.concepto = concepto;
    }

    public String getRestriccion() {
        return restriccion;
    }

    public void setRestriccion(String restriccion) {
        this.restriccion = restriccion;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
}
