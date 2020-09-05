package com.cdm.sig.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "recomendaciones")
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecomendaciones;

    @NotNull
    @OneToOne
    private Examen examen;

    @NotBlank
    @Column
    private String recommendation;

    @NotBlank
    @Column
    private String tipoSeguimiento;

    @Column
    private String primeraSeguimiento;

    @Column
    private String segundaSeguimiento;

    @Column
    private String terceraSeguimiento;

    @CreationTimestamp
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    public void CreatedAt() {
        createAt = new Date();
    }

    public Long getIdRecomendaciones() {
        return idRecomendaciones;
    }

    public void setIdRecomendaciones(Long idRecomendaciones) {
        this.idRecomendaciones = idRecomendaciones;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getTipoSeguimiento() {
        return tipoSeguimiento;
    }

    public void setTipoSeguimiento(String tipoSeguimiento) {
        this.tipoSeguimiento = tipoSeguimiento;
    }

    public String getPrimeraSeguimiento() {
        return primeraSeguimiento;
    }

    public void setPrimeraSeguimiento(String primeraSeguimiento) {
        this.primeraSeguimiento = primeraSeguimiento;
    }

    public String getSegundaSeguimiento() {
        return segundaSeguimiento;
    }

    public void setSegundaSeguimiento(String segundaSeguimiento) {
        this.segundaSeguimiento = segundaSeguimiento;
    }

    public String getTerceraSeguimiento() {
        return terceraSeguimiento;
    }

    public void setTerceraSeguimiento(String terceraSeguimiento) {
        this.terceraSeguimiento = terceraSeguimiento;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
