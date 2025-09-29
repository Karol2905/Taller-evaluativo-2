package com.example.demo.decorator;

import com.example.demo.Decorator.ReporteGraficas;
import com.example.demo.Decorator.ReporteMarcaAgua;
import com.example.demo.Decorator.Reporte;
import com.example.demo.model.ReporteBasico;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class ReporteDecoradorTest {

    @Test
    void testReporteConMarcaAgua() {
        ReporteBasico reporteBase = new ReporteBasico("Test", LocalDate.now(), "Autor", "Contenido de prueba");
        Reporte reporteConMarca = new ReporteMarcaAgua(reporteBase);

        String resultado = reporteConMarca.generar();

        assertTrue(resultado.contains("CONFIDENCIAL"));
        assertTrue(resultado.contains("Contenido de prueba"));
        assertTrue(resultado.contains("******************"));
    }

    @Test
    void testReporteConGraficas() {
        ReporteBasico reporteBase = new ReporteBasico("Test", LocalDate.now(), "Autor", "Contenido de prueba");
        Reporte reporteConGraficas = new ReporteGraficas(reporteBase);

        String resultado = reporteConGraficas.generar();

        assertTrue(resultado.contains("Gráfica de Barras"));
        assertTrue(resultado.contains("Gráfica de Líneas"));
        assertTrue(resultado.contains("Contenido de prueba"));
    }

    @Test
    void testCombinacionDecoradoresGraficasYMarcaAgua() {
        ReporteBasico reporteBase = new ReporteBasico("Test", LocalDate.now(), "Autor", "Contenido de prueba");
        Reporte reporteDecorado = new ReporteMarcaAgua(new ReporteGraficas(reporteBase));

        String resultado = reporteDecorado.generar();

        assertTrue(resultado.contains("CONFIDENCIAL"));
        assertTrue(resultado.contains("Gráfica de Barras"));
        assertTrue(resultado.contains("Gráfica de Líneas"));
        assertTrue(resultado.contains("Contenido de prueba"));
    }
}
