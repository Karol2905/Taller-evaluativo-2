package com.example.demo.Decorator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReporteTest {

    @Test
    void testReporteEstadistico() {
        Reporte reporte = () -> "Reporte simple";
        Reporte decorado = new ReporteEstadistico(reporte);

        String resultado = decorado.generar();

        assertTrue(resultado.contains("Reporte simple"));
        assertTrue(resultado.contains("Agregando estadísticas"));
    }

    @Test
    void testReporteExportablePDF() {
        Reporte reporte = () -> "Reporte simple";
        Reporte decorado = new ReporteExportablePDF(reporte);

        String resultado = decorado.generar();

        assertTrue(resultado.contains("Reporte simple"));
        assertTrue(resultado.contains("Exportando reporte a PDF"));
    }

    @Test
    void testReporteGraficas() {
        Reporte reporte = () -> "Reporte simple";
        Reporte decorado = new ReporteGraficas(reporte);

        String resultado = decorado.generar();

        assertTrue(resultado.contains("Reporte simple"));
        assertTrue(resultado.contains("Gráfica de Barras"));
        assertTrue(resultado.contains("Gráfica de Líneas"));
    }

    @Test
    void testReporteMarcaAgua() {
        Reporte reporte = () -> "Reporte simple";
        Reporte decorado = new ReporteMarcaAgua(reporte);

        String resultado = decorado.generar();

        assertTrue(resultado.contains("CONFIDENCIAL"));
        assertTrue(resultado.contains("Reporte simple"));
    }

    @Test
    void testCombinacionDecoradores() {
        Reporte reporte = new ReporteExportablePDF(
                new ReporteEstadistico(
                        new ReporteGraficas(
                                new ReporteMarcaAgua(
                                        () -> "Reporte simple"
                                )
                        )
                )
        );

        String resultado = reporte.generar();

        assertTrue(resultado.contains("Reporte simple"));
        assertTrue(resultado.contains("Agregando estadísticas"));
        assertTrue(resultado.contains("Exportando reporte a PDF"));
        assertTrue(resultado.contains("Gráfica de Barras"));
        assertTrue(resultado.contains("CONFIDENCIAL"));
    }
}
