package com.example.demo.builder;

import com.example.demo.model.ReporteBasico;
import com.example.demo.model.Transaccion;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteBuilderTest {

    @Test
    void testReporteBasico() {
        ReporteBasico reporte = new ReporteBuilder()
                .titulo("Reporte Básico")
                .fechaGeneracion(LocalDate.now())
                .autor("Karol")
                .contenido("Contenido simple")
                .build();

        assertEquals("Reporte Básico", reporte.getTitulo());
        assertEquals("Karol", reporte.getAutor());
        assertTrue(reporte.getContenido().contains("Contenido simple"));
    }

    @Test
    void testReporteConGraficas() {
        ReporteBasico reporte = new ReporteBuilder()
                .titulo("Reporte con Gráficas")
                .fechaGeneracion(LocalDate.now())
                .autor("Karol")
                .contenido("Contenido inicial")
                .conGraficas()
                .build();

        assertTrue(reporte.getContenido().contains("Contenido inicial"));
        assertTrue(reporte.getContenido().contains("Gráfica de Barras"));
        assertTrue(reporte.getContenido().contains("Gráfica de Líneas"));
    }

    @Test
    void testReporteConEstadisticas() {
        ReporteBasico reporte = new ReporteBuilder()
                .titulo("Reporte con Estadísticas")
                .fechaGeneracion(LocalDate.now())
                .autor("Karol")
                .contenido("Contenido inicial")
                .conEstadisticas()
                .build();

        assertTrue(reporte.getContenido().contains("Contenido inicial"));
        assertTrue(reporte.getContenido().contains("Agregando estadísticas"));
    }

    @Test
    void testReporteConMarcaAgua() {
        ReporteBasico reporte = new ReporteBuilder()
                .titulo("Reporte Confidencial")
                .fechaGeneracion(LocalDate.now())
                .autor("Karol")
                .contenido("Contenido sensible")
                .conMarcaAgua()
                .build();

        assertTrue(reporte.getContenido().contains("CONFIDENCIAL"));
        assertTrue(reporte.getContenido().contains("Contenido sensible"));
    }

    @Test
    void testReporteExportablePDF() {
        ReporteBasico reporte = new ReporteBuilder()
                .titulo("Reporte Exportable")
                .fechaGeneracion(LocalDate.now())
                .autor("Karol")
                .contenido("Contenido PDF")
                .exportableAPDF()
                .build();

        assertTrue(reporte.getContenido().contains("Contenido PDF"));
        assertTrue(reporte.getContenido().contains("Exportando reporte a PDF"));
    }

    @Test
    void testReporteCompleto() {
        ReporteBasico reporte = new ReporteBuilder()
                .titulo("Reporte Completo")
                .fechaGeneracion(LocalDate.now())
                .autor("Karol")
                .contenido("Reporte con todo")
                .conGraficas()
                .conEstadisticas()
                .conMarcaAgua()
                .exportableAPDF()
                .build();

        String contenido = reporte.getContenido();

        assertTrue(contenido.contains("Reporte con todo"));
        assertTrue(contenido.contains("Gráfica de Barras"));
        assertTrue(contenido.contains("Gráfica de Líneas"));
        assertTrue(contenido.contains("Agregando estadísticas"));
        assertTrue(contenido.contains("CONFIDENCIAL"));
        assertTrue(contenido.contains("Exportando reporte a PDF"));
    }


}
