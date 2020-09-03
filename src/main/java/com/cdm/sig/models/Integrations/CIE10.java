package com.cdm.sig.models.Integrations;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cie10")
public class CIE10 {
    @Id
    private String idCIE10;

    @Column
    private String descripcion;

    @Column
    private String capitulo;

    public String getIdCIE10() {
        return idCIE10;
    }

    public void setIdCIE10(String idCIE10) {
        this.idCIE10 = idCIE10;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCapitulo() {
        return capitulo;
    }

    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }
}
