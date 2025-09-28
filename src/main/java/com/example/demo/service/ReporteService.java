package com.example.demo.service;

import com.example.demo.Decorator.Reporte;
import com.example.demo.model.ReporteBasico;
import com.example.demo.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;

    public ReporteBasico guardar(ReporteBasico reporte) {return reporteRepository.save(reporte);}

    public List<ReporteBasico> obtenerTodos() {
        return reporteRepository.findAll();
    }

    public ReporteBasico obtenerPorId(String id) {
        return reporteRepository.findById(id).orElse(null);
    }

    public ReporteBasico actualizar(ReporteBasico reporte) {
        return reporteRepository.save(reporte);
    }

    public void eliminar(String id) {
        reporteRepository.deleteById(id);
    }

    public List<Reporte> buscarPorTitulo(String titulo) {
        return reporteRepository.findByTitulo(titulo);
    }
    public List<Reporte> buscarPorFechaGeneracion(Date fechaGeneracion) {
        return reporteRepository.findByFechaGeneracion(fechaGeneracion);
    }
    public List<Reporte> buscarPorAutor(String autor) {
        return reporteRepository.findByAutor(autor);
    }
}