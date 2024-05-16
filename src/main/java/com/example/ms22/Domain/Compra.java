package com.example.ms22.Domain;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

import java.util.List;
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }, allowSetters = true)
@Table(name = "compra")


public class Compra{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Date fecha_compra;
    private Float monto_Total;
    private String metodo_de_pago;


    public Compra() {}

    public Compra(Long id, Date fecha_compra, Float monto_Total, String metodo_de_pago) {
        this.id = id;
        this.fecha_compra = fecha_compra;
        this.monto_Total = monto_Total;
        this.metodo_de_pago = metodo_de_pago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public Float getMonto_Total() {
        return monto_Total;
    }

    public void setMonto_Total(Float monto_Total) {
        this.monto_Total = monto_Total;
    }

    public String getMetodo_de_pago() {
        return metodo_de_pago;
    }

    public void setMetodo_de_pago(String metodo_de_pago) {
        this.metodo_de_pago = metodo_de_pago;
    }

}