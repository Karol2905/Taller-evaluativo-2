package com.example.demo.repository;
import com.example.demo.model.ReporteBasico;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReporteRepository extends MongoRepository<ReporteBasico, String> {

}