package com.example.demo.model;
import com.example.demo.Decorator.Reporte;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;



@Document(collection = "Reportes")
public class ReporteBasico implements Reporte {
    @Id
    private String id;

    private String titulo;
    private LocalDate fechaGeneracion;
    private String autor;
    private ArrayList<Transaccion> transacciones;
    private String contenido;

    public ReporteBasico(String titulo, LocalDate fechaGeneracion, String autor, String contenido) {
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
        return this.contenido;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }
    public void setFechaGeneracion(LocalDate fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }
    public void setTransacciones(ArrayList<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
