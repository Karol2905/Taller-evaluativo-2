package com.example.demo;
import com.example.demo.Decorator.Reporte;
import java.util.ArrayList;
import java.util.Date;

public class ReporteBasico implements Reporte {
    private String titulo;
    private Date fechaGeneracion;
    private String autor;
    private ArrayList<Transaccion> transacciones;
    private String contenido;

    public ReporteBasico(String titulo, Date fechaGeneracion, String autor, String contenido) {
        this.titulo = titulo;
        this.fechaGeneracion = fechaGeneracion;
        this.autor = autor;
        this.contenido = contenido;
        this.transacciones = new ArrayList<>();
    }

    public void addTransaccion(Transaccion transaccion) {
        this.transacciones.add(transaccion);
    }

    @Override
    public String generar() {
        return "";
    }

}

