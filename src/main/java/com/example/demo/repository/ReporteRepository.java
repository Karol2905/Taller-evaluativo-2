package com.example.demo.repository;
import com.example.demo.Decorator.Reporte;
import com.example.demo.model.ReporteBasico;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface ReporteRepository extends MongoRepository<ReporteBasico, String> {
    List<Reporte> findByTitulo(String titulo);
    List<Reporte> findByAutor(String autor);
}