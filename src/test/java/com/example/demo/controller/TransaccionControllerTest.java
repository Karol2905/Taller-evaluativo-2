package com.example.demo.controller;

import com.example.demo.model.Transaccion;
import com.example.demo.service.TransaccionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransaccionControllerTest {

    @Mock
    private TransaccionService transaccionService;

    @InjectMocks
    private TransaccionController transaccionController;

    private Transaccion transaccionBase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaccionBase = new Transaccion(
                "1",
                1500.50,
                "compra",
                "Compra de insumos de oficina",
                 new Date()
        );
    }

    @Test
    void testCrear() {
        when(transaccionService.guardar(transaccionBase)).thenReturn(transaccionBase);

        Transaccion response = transaccionController.crear(transaccionBase);

        assertEquals(transaccionBase, response);
        assertEquals(1500.50, response.getMonto());
        assertEquals("compra", response.getTipo());
        assertEquals("Compra de insumos de oficina", response.getDescripcion());
        verify(transaccionService, times(1)).guardar(transaccionBase);
    }

    @Test
    void testListar() {
        List<Transaccion> lista = Arrays.asList(
                transaccionBase,
                new Transaccion("2", 750.0, "venta", "Venta de producto", new Date())
        );
        when(transaccionService.obtenerTodos()).thenReturn(lista);

        List<Transaccion> response = transaccionController.listar();

        assertEquals(2, response.size());
        assertEquals("compra", response.get(0).getTipo());
        verify(transaccionService, times(1)).obtenerTodos();
    }

    @Test
    void testObtenerPorId() {
        when(transaccionService.obtenerPorId("1")).thenReturn(transaccionBase);

        Transaccion response = transaccionController.obtenerPorId("1");

        assertEquals("Compra de insumos de oficina", response.getDescripcion());
        assertEquals(1500.50, response.getMonto());
        verify(transaccionService, times(1)).obtenerPorId("1");
    }

    @Test
    void testEliminar() {
        String id = "1";

        transaccionController.eliminar(id);

        verify(transaccionService, times(1)).eliminar(id);
    }

    @Test
    void testBuscarPorFecha() {
        LocalDate fecha = LocalDate.of(2025, 9, 25);
        List<Transaccion> lista = Arrays.asList(transaccionBase);
        when(transaccionService.buscarPorFecha(fecha)).thenReturn(lista);

        List<Transaccion> response = transaccionController.buscarPorFecha("2025-09-25");

        assertEquals(1, response.size());
        assertEquals("compra", response.get(0).getTipo());
        verify(transaccionService, times(1)).buscarPorFecha(fecha);
    }
}