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


}
