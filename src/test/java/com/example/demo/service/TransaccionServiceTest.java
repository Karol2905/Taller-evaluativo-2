package com.example.demo.service;

import com.example.demo.model.Transaccion;
import com.example.demo.repository.TransaccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransaccionServiceTest {

    @Mock
    private TransaccionRepository transaccionRepository;

    @InjectMocks
    private TransaccionService transaccionService;

    private Transaccion transaccion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transaccion = new Transaccion("T1", 200.0, "Ingreso", "Pago recibido", new Date());
    }

    @Test
    void testGuardar() {
        when(transaccionRepository.save(transaccion)).thenReturn(transaccion);

        Transaccion resultado = transaccionService.guardar(transaccion);

        assertEquals("T1", resultado.getId());
        verify(transaccionRepository, times(1)).save(transaccion);
    }

    @Test
    void testObtenerTodos() {
        when(transaccionRepository.findAll()).thenReturn(Arrays.asList(transaccion));

        List<Transaccion> lista = transaccionService.obtenerTodos();

        assertEquals(1, lista.size());
        verify(transaccionRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPorId() {
        when(transaccionRepository.findById("T1")).thenReturn(Optional.of(transaccion));

        Transaccion resultado = transaccionService.obtenerPorId("T1");

        assertNotNull(resultado);
        assertEquals(200.0, resultado.getMonto());
    }

    @Test
    void testActualizar() {
        when(transaccionRepository.save(transaccion)).thenReturn(transaccion);

        Transaccion actualizado = transaccionService.actualizar(transaccion);

        assertEquals("T1", actualizado.getId());
        verify(transaccionRepository, times(1)).save(transaccion);
    }

    @Test
    void testEliminar() {
        doNothing().when(transaccionRepository).deleteById("T1");

        transaccionService.eliminar("T1");

        verify(transaccionRepository, times(1)).deleteById("T1");
    }


}
