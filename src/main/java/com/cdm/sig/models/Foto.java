package com.cdm.sig.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "fotos")
public class Foto {

    @Id
    private String idFoto;

    @Lob
    @JsonIgnore
    private byte[] foto;

    public Integer getFotoHashCode() {
        return (this.foto != null) ? this.hashCode() : null;
    }

    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String idFoto) {
        this.idFoto = idFoto;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
