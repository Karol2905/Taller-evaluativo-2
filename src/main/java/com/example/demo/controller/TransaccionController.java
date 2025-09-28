package com.example.demo.controller;

import com.example.demo.model.Transaccion;
import com.example.demo.service.TransaccionService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @PostMapping
    public Transaccion crear(@RequestBody Transaccion transaccion) {
        return transaccionService.guardar(transaccion);
    }

    @GetMapping
    public List<Transaccion> listar() {
        return transaccionService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Transaccion obtenerPorId(@PathVariable String id) {
        return transaccionService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable String id) {
        transaccionService.eliminar(id);
    }

    @GetMapping("/fecha/{fecha}")
    public List<Transaccion> buscarPorFecha(@PathVariable String fecha) {
        LocalDate fechaParseada = LocalDate.parse(fecha); // viene como String del path
        return transaccionService.buscarPorFecha(fechaParseada);
    }
}
