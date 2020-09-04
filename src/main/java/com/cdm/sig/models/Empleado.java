package com.cdm.sig.models;

import com.cdm.sig.models.integrations.Afp;
import com.cdm.sig.models.integrations.Arl;
import com.cdm.sig.models.integrations.CajaComFamiliar;
import com.cdm.sig.models.integrations.Eps;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "empleados")
public class Empleado {

    @Id
    @NotEmpty
    private String cedula;

    @Column
    @NotEmpty
    private String nombres;

    @Column
    @NotEmpty
    private String apellidos;

    @Column
    @NotEmpty
    private String genero;

    @Column
    @NotEmpty
    private String fechaNacimiento;

    @Column
    @NotEmpty
    private String tipoSangre;

    @Column
    @NotEmpty
    private String direccion;

    @Column
    @NotEmpty
    private String municipio;

    @Column
    @NotEmpty
    private String telefono;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Eps eps;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Afp afp;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private Arl arl;

    @OneToOne(fetch = FetchType.LAZY)
    private CajaComFamiliar cajaComFamiliar;

    @Column
    private String alergia;

    @Column
    private String medicamentos;

    @Column
    private String enCasoEmergencia;

    @Column
    private String parentesco;

    @Column
    private String telEmergencia;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Eps getEps() {
        return eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    public Afp getAfp() {
        return afp;
    }

    public void setAfp(Afp afp) {
        this.afp = afp;
    }

    public Arl getArl() {
        return arl;
    }

    public void setArl(Arl arl) {
        this.arl = arl;
    }

    public CajaComFamiliar getCajaComFamiliar() {
        return cajaComFamiliar;
    }

    public void setCajaComFamiliar(CajaComFamiliar cajaComFamiliar) {
        this.cajaComFamiliar = cajaComFamiliar;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        this.medicamentos = medicamentos;
    }

    public String getEnCasoEmergencia() {
        return enCasoEmergencia;
    }

    public void setEnCasoEmergencia(String enCasoEmergencia) {
        this.enCasoEmergencia = enCasoEmergencia;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getTelEmergencia() {
        return telEmergencia;
    }

    public void setTelEmergencia(String telEmergencia) {
        this.telEmergencia = telEmergencia;
    }
}
