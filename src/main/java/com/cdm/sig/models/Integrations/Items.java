package com.cdm.sig.models.Integrations;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "items")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    @Column
    @NotEmpty(message = "El Nombre es obligatorio")
    @NotNull
    @Size(min = 2, max = 50)
    private String nombre;

    @Column
    @NotEmpty(message = "El Referencia es obligatorio")
    @NotNull
    @Size(min = 2, max = 50)
    private String referencia;

    @Column
    @NotEmpty(message = "El Tipo es obligatorio")
    @NotNull
    @Size(min = 2, max = 50)
    private String tipo;


    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
