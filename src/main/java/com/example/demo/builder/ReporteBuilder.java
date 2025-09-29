package com.example.demo.builder;

import com.example.demo.Decorator.*;
import com.example.demo.model.ReporteBasico;
import com.example.demo.model.Transaccion;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReporteBuilder {
    private String titulo;
    private LocalDate fechaGeneracion;
    private String autor;
    private ArrayList<Transaccion> transacciones;
    private String contenido;
    private boolean incluirGraficas;
    private boolean incluirEstadisticas;
    private boolean incluirMarcaAgua;
    private boolean hacerExportablePDF;

    public ReporteBuilder() {
        this.transacciones = new ArrayList<>();
        this.incluirGraficas = false;
        this.incluirEstadisticas = false;
        this.incluirMarcaAgua = false;
        this.hacerExportablePDF = false;
    }

    public ReporteBuilder titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public ReporteBuilder fechaGeneracion(LocalDate fechaGeneracion) {
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

    public ReporteBuilder agregarTransaccion(Transaccion transaccion) {
        this.transacciones.add(transaccion);
        return this;
    }

    public ReporteBuilder conGraficas() {
        this.incluirGraficas = true;
        return this;
    }

    public ReporteBuilder conEstadisticas() {
        this.incluirEstadisticas = true;
        return this;
    }

    public ReporteBuilder conMarcaAgua() {
        this.incluirMarcaAgua = true;
        return this;
    }

    public ReporteBuilder exportableAPDF() {
        this.hacerExportablePDF = true;
        return this;
    }

    public ReporteBasico build() {
        ReporteBasico reporteBase = new ReporteBasico(titulo, fechaGeneracion, autor, contenido);

        Reporte reporteDecorado = reporteBase;

        if (incluirGraficas) {
            reporteDecorado = new ReporteGraficas(reporteDecorado);
        }
        if (incluirEstadisticas) {
            reporteDecorado = new ReporteEstadistico(reporteDecorado);
        }
        if (incluirMarcaAgua) {
            reporteDecorado = new ReporteMarcaAgua(reporteDecorado);
        }
        if (hacerExportablePDF) {
            reporteDecorado = new ReporteExportablePDF(reporteDecorado);
        }

        reporteBase.setContenido(reporteDecorado.generar());

        return reporteBase;
    }
}
