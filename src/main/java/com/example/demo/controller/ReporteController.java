package com.example.demo.controller;
import com.example.demo.Decorator.*;
import com.example.demo.builder.ReporteBuilder;
import com.example.demo.model.ReporteBasico;
import com.example.demo.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {
    @Autowired
    private ReporteService reporteService;

    @PostMapping
    public ResponseEntity<ReporteBasico> crearReporte(@RequestBody ReporteBasico reporte) {
        ReporteBasico reporteGuardado = reporteService.guardar(reporte);
        return ResponseEntity.ok(reporteGuardado);
    }
    @PostMapping("/decorado")
    public ResponseEntity<?> crearReporteDecorado(
            @RequestParam String titulo,
            @RequestParam String autor,
            @RequestParam String contenido,
            @RequestParam(defaultValue = "false") boolean agregarGraficas,
            @RequestParam(defaultValue = "false") boolean agregarEstadisticas,
            @RequestParam(defaultValue = "false") boolean agregarMarcaAgua,
            @RequestParam(defaultValue = "false") boolean hacerExportablePDF) {
        try {
            ReporteBuilder builder = new ReporteBuilder()
                .titulo(titulo)
                .autor(autor)
                .contenido(contenido)
                .fechaGeneracion(LocalDate.now());

            if (agregarGraficas) builder.conGraficas();
            if (agregarEstadisticas) builder.conEstadisticas();
            if (agregarMarcaAgua) builder.conMarcaAgua();
            if (hacerExportablePDF) builder.exportableAPDF();

            ReporteBasico reporte = builder.build();
            return ResponseEntity.ok(reporteService.guardar(reporte));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el reporte: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarReportes() {
        return ResponseEntity.ok(reporteService.obtenerTodos());
    }

    @GetMapping ("/{id}")
    public ResponseEntity<ReporteBasico> obtenerReporte(@PathVariable String id) {
        ReporteBasico reporte = reporteService.obtenerPorId(id);
        if (reporte != null) {
            return ResponseEntity.ok(reporte);
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReporteBasico> actualizar(@PathVariable String id, @RequestBody ReporteBasico reporte) {
        ReporteBasico existente = reporteService.obtenerPorId(id);
        if (existente != null) {
            reporte.setId(id);
            ReporteBasico actualizado = reporteService.actualizar(reporte);
            return ResponseEntity.ok(actualizado);
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        ReporteBasico existente = reporteService.obtenerPorId(id);
        if (existente != null) {
            reporteService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<?> filtrarPorFecha(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(reporteService.buscarPorFechaGeneracion(fecha));
    }
    @GetMapping("/rangoFechas") //entre?inicio=2025-09-01&fin=2025-09-15
    public ResponseEntity<?> filtrarEntreFechas(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(reporteService.filtrarPorRango(inicio, fin));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<?> filtrarPorTitulo(@PathVariable String titulo) {
        List<Reporte> resultados = reporteService.buscarPorTitulo(titulo);
        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity<?> filtrarPorAutor(@PathVariable String autor) {
        List<Reporte> resultados = reporteService.buscarPorAutor(autor);
        return ResponseEntity.ok(resultados);
    }
}