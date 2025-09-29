package com.example.demo.controller;

import com.example.demo.model.ReporteBasico;
import com.example.demo.service.ReporteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReporteController.class)
public class ReporteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReporteService reporteService;

    private ReporteBasico reporte;

    @BeforeEach
    void setUp() {
        reporte = new ReporteBasico();
        reporte.setId("123");
        reporte.setTitulo("Reporte de prueba");
        reporte.setAutor("Julian");
        reporte.setContenido("Contenido de prueba");
        reporte.setFechaGeneracion(LocalDate.now());
    }

    @Test
    void testCrearReporte() throws Exception {
        Mockito.when(reporteService.guardar(any(ReporteBasico.class))).thenReturn(reporte);

        mockMvc.perform(post("/api/reportes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Reporte de prueba\",\"autor\":\"Julian\",\"contenido\":\"Contenido de prueba\",\"fechaGeneracion\":\"2025-09-28\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.autor").value("Julian"));
    }

    @Test
    void testListarReportes() throws Exception {
        Mockito.when(reporteService.obtenerTodos()).thenReturn(Collections.singletonList(reporte));

        mockMvc.perform(get("/api/reportes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Reporte de prueba"));
    }

    @Test
    void testObtenerPorId_Existe() throws Exception {
        Mockito.when(reporteService.obtenerPorId("123")).thenReturn(reporte);

        mockMvc.perform(get("/api/reportes/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Reporte de prueba"));
    }

    @Test
    void testObtenerPorId_NoExiste() throws Exception {
        Mockito.when(reporteService.obtenerPorId("999")).thenReturn(null);

        mockMvc.perform(get("/api/reportes/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testActualizarReporte() throws Exception {
        Mockito.when(reporteService.obtenerPorId("123")).thenReturn(reporte);
        Mockito.when(reporteService.actualizar(any(ReporteBasico.class))).thenReturn(reporte);

        mockMvc.perform(put("/api/reportes/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Nuevo\",\"autor\":\"Julian\",\"contenido\":\"Cambiado\",\"fechaGeneracion\":\"2025-09-28\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.autor").value("Julian"));
    }

    @Test
    void testEliminarReporte() throws Exception {
        Mockito.when(reporteService.obtenerPorId("123")).thenReturn(reporte);

        mockMvc.perform(delete("/api/reportes/123"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testFiltrarPorAutor() throws Exception {
        Mockito.when(reporteService.buscarPorAutor(eq("Julian")))
                .thenReturn(Collections.singletonList(reporte));

        mockMvc.perform(get("/api/reportes/autor/Julian"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].autor").value("Julian"));
    }
}
