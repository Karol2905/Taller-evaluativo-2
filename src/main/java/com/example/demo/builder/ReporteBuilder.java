package com.example.demo.builder;

import com.example.demo.model.ReporteBasico;
import com.example.demo.model.Transaccion;

import java.util.ArrayList;
import java.util.Date;

public class ReporteBuilder {
    private String titulo;
    private Date fechaGeneracion;
    private String autor;
    private ArrayList<Transaccion> transacciones = new ArrayList<>();
    private String contenido;


    public ReporteBuilder titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public ReporteBuilder fechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
        return this;
    }

    public ReporteBuilder autor(String autor) {
        this.autor = autor;
        return this;
    }

    public ReporteBuilder contenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public ReporteBasico build() {
        ReporteBasico reporte = new ReporteBasico(titulo, fechaGeneracion, autor, contenido);
        return reporte;
    }
}








