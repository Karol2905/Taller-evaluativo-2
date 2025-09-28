package com.example.demo.model;

import java.util.Date;

public class Transaccion {
    private String id;
    private double monto;
    private String tipo;
    private String descripcion;
    private Date fecha;

    public Transaccion(String id, double monto, String tipo, String descripcion, Date fecha) {
        this.id = id;
        this.monto = monto;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFecha() {
        return fecha;
    }
}
