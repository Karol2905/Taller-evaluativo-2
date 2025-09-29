package com.example.demo.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class modelTest {

    @Test
    void testCrearReporteBasico() {
        LocalDate fecha = LocalDate.now();
        ReporteBasico reporte = new ReporteBasico(
                "Reporte de ventas",
                fecha,
                "Karol",
                "Contenido inicial del reporte"
        );

        assertEquals("Reporte de ventas", reporte.getTitulo());
        assertEquals("Karol", reporte.getAutor());
        assertEquals(fecha, reporte.getFechaGeneracion());
        assertEquals("Contenido inicial del reporte", reporte.getContenido());
        assertNotNull(reporte.getTransacciones());
        assertTrue(reporte.getTransacciones().isEmpty());
    }

    @Test
    void testAgregarTransaccion() {
        ReporteBasico reporte = new ReporteBasico(
                "Reporte financiero",
                LocalDate.now(),
                "Karol",
                "Contenido del reporte"
        );

        Transaccion transaccion = new Transaccion(
                "T1",
                1500.0,
                "Ingreso",
                "Pago recibido",
                new Date()
        );

        reporte.addTransaccion(transaccion);

        assertEquals(1, reporte.getTransacciones().size());
        assertEquals("T1", reporte.getTransacciones().get(0).getId());
        assertEquals(1500.0, reporte.getTransacciones().get(0).getMonto());
    }

    @Test
    void testGenerarReporte() {
        ReporteBasico reporte = new ReporteBasico(
                "Reporte inventario",
                LocalDate.now(),
                "Admin",
                "Este es el contenido del reporte"
        );

        String resultado = reporte.generar();

        assertEquals("Este es el contenido del reporte", resultado);
    }

    @Test
    void testSettersYGetters() {
        ReporteBasico reporte = new ReporteBasico(
                "Titulo",
                LocalDate.now(),
                "Autor",
                "Contenido"
        );

        reporte.setId("R1");
        reporte.setTitulo("Nuevo título");
        reporte.setAutor("Nuevo autor");
        reporte.setContenido("Nuevo contenido");

        assertEquals("R1", reporte.getId());
        assertEquals("Nuevo título", reporte.getTitulo());
        assertEquals("Nuevo autor", reporte.getAutor());
        assertEquals("Nuevo contenido", reporte.getContenido());
    }
}
