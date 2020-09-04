package com.cdm.sig.models;

import com.cdm.sig.models.integrations.Items;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class EntregaDyE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntregaDyE;

    @Column
    @NotNull
    private LocalDate fechaEntregaDyE;

    @Column
    @NotEmpty
    private String descripcion;

    @Column
    @NotEmpty
    private String tipo;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Empleado empleado;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "entrega_dye_items",
            joinColumns = @JoinColumn(name = "entrega_dye_id_entrega_dye"),
            inverseJoinColumns = @JoinColumn(name = "items_id_item"))
    private final List<Items> items;

    public EntregaDyE() {
        this.items = new ArrayList<>();
    }

    public void addItem(Items items) {
        this.items.add(items);
    }

    public void removeItem(Items items) {
        this.items.remove(items);
    }

    public Long getIdEntregaDyE() {
        return idEntregaDyE;
    }

    public void setIdEntregaDyE(Long idEntregaDyE) {
        this.idEntregaDyE = idEntregaDyE;
    }

    public LocalDate getFechaEntregaDyE() {
        return fechaEntregaDyE;
    }

    public void setFechaEntregaDyE(LocalDate fechaEntregaDyE) {
        this.fechaEntregaDyE = fechaEntregaDyE;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public List<Items> getItems() {
        return items;
    }
}
