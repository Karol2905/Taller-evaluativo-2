package com.example.demo.controller;
import com.example.demo.model.ReporteBasico;
import com.example.demo.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}