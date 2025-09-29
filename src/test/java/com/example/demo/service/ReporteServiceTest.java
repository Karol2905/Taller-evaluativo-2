package com.example.demo.service;

import com.example.demo.Decorator.Reporte;
import com.example.demo.model.ReporteBasico;
import com.example.demo.repository.ReporteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReporteServiceTest {

    @Mock
    private ReporteRepository reporteRepository;

    @InjectMocks
    private ReporteService reporteService;

    private ReporteBasico reporte;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        reporte = new ReporteBasico("Titulo test", LocalDate.now(), "Autor", "Contenido");
        reporte.setId("R1");
    }

    @Test
    void testGuardar() {
        when(reporteRepository.save(reporte)).thenReturn(reporte);

        ReporteBasico resultado = reporteService.guardar(reporte);

        assertEquals("R1", resultado.getId());
        verify(reporteRepository, times(1)).save(reporte);
    }

    @Test
    void testObtenerTodos() {
        when(reporteRepository.findAll()).thenReturn(Arrays.asList(reporte));

        List<ReporteBasico> lista = reporteService.obtenerTodos();

        assertEquals(1, lista.size());
        verify(reporteRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        when(reporteRepository.findById("R1")).thenReturn(Optional.of(reporte));

        ReporteBasico resultado = reporteService.obtenerPorId("R1");

        assertNotNull(resultado);
        assertEquals("Titulo test", resultado.getTitulo());
    }

    @Test
    void testActualizar() {
        when(reporteRepository.save(reporte)).thenReturn(reporte);

        ReporteBasico actualizado = reporteService.actualizar(reporte);

        assertEquals("R1", actualizado.getId());
        verify(reporteRepository, times(1)).save(reporte);
    }

    @Test
    void testEliminar() {
        doNothing().when(reporteRepository).deleteById("R1");

        reporteService.eliminar("R1");

        verify(reporteRepository, times(1)).deleteById("R1");
    }

    @Test
    void testBuscarPorTitulo() {
        when(reporteRepository.findByTitulo("Titulo test")).thenReturn(Arrays.asList(reporte));

        List<Reporte> resultados = reporteService.buscarPorTitulo("Titulo test");

        assertEquals(1, resultados.size());
        verify(reporteRepository, times(1)).findByTitulo("Titulo test");
    }

    @Test
    void testBuscarPorAutor() {
        when(reporteRepository.findByAutor("Autor")).thenReturn(Arrays.asList(reporte));

        List<Reporte> resultados = reporteService.buscarPorAutor("Autor");

        assertEquals(1, resultados.size());
        verify(reporteRepository, times(1)).findByAutor("Autor");
    }

    @Test
    void testBuscarPorFechaGeneracion() {
        when(reporteRepository.findAll()).thenReturn(Arrays.asList(reporte));

        List<ReporteBasico> resultados = reporteService.buscarPorFechaGeneracion(reporte.getFechaGeneracion());

        assertEquals(1, resultados.size());
    }

    @Test
    void testFiltrarPorRango() {
        when(reporteRepository.findAll()).thenReturn(Arrays.asList(reporte));

        List<ReporteBasico> resultados = reporteService.filtrarPorRango(
                reporte.getFechaGeneracion().minusDays(1),
                reporte.getFechaGeneracion().plusDays(1)
        );

        assertEquals(1, resultados.size());
    }
}
