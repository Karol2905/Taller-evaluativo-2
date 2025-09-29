package com.example.demo.builder;

import com.example.demo.model.ReporteBasico;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ReporteBuilderTest {

    @Test
    void testConstructorBasico() {
        ReporteBuilder builder = new ReporteBuilder();
        LocalDate fecha = LocalDate.now();

        ReporteBasico reporte = builder
            .titulo("Reporte Test")
            .autor("Autor Test")
            .contenido("Contenido Test")
            .fechaGeneracion(fecha)
            .build();

        assertEquals("Reporte Test", reporte.getTitulo());
        assertEquals("Autor Test", reporte.getAutor());
        assertEquals("Contenido Test", reporte.getContenido());
        assertEquals(fecha, reporte.getFechaGeneracion());
    }

    @Test
    void testReporteConGraficas() {
        ReporteBuilder builder = new ReporteBuilder();

        ReporteBasico reporte = builder
            .titulo("Reporte Test")
            .autor("Autor Test")
            .contenido("Contenido Test")
            .fechaGeneracion(LocalDate.now())
            .conGraficas()
            .build();

        String contenido = reporte.getContenido();
        assertTrue(contenido.contains("Gráfica de Barras"));
        assertTrue(contenido.contains("Gráfica de Líneas"));
    }

    @Test
    void testReporteConMarcaAgua() {
        ReporteBuilder builder = new ReporteBuilder();

        ReporteBasico reporte = builder
            .titulo("Reporte Test")
            .autor("Autor Test")
            .contenido("Contenido Test")
            .fechaGeneracion(LocalDate.now())
            .conMarcaAgua()
            .build();

        String contenido = reporte.getContenido();
        assertTrue(contenido.contains("CONFIDENCIAL"));
        assertTrue(contenido.contains("******************"));
    }

    @Test
    void testReporteConMultiplesDecoradores() {
        ReporteBuilder builder = new ReporteBuilder();

        ReporteBasico reporte = builder
            .titulo("Reporte Test")
            .autor("Autor Test")
            .contenido("Contenido Test")
            .fechaGeneracion(LocalDate.now())
            .conGraficas()
            .conMarcaAgua()
            .build();

        String contenido = reporte.getContenido();
        assertTrue(contenido.contains("CONFIDENCIAL"));
        assertTrue(contenido.contains("Gráfica de Barras"));
        assertTrue(contenido.contains("Gráfica de Líneas"));
        assertTrue(contenido.contains("******************"));
    }
}
