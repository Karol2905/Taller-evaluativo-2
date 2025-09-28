package com.example.demo.service;

import com.example.demo.model.Transaccion;
import com.example.demo.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransaccionService {
    @Autowired
    private TransaccionRepository transaccionRepository;

    public Transaccion guardar(Transaccion transaccion) {return transaccionRepository.save(transaccion);}

    public List<Transaccion> obtenerTodos() { return transaccionRepository.findAll(); }

    public Transaccion obtenerPorId(String id) {return transaccionRepository.findById(id).orElse(null);}

    public Transaccion actualizar(Transaccion transaccion) {return transaccionRepository.save(transaccion);}

    public void eliminar(String id) {transaccionRepository.deleteById(id);}

    public List<Transaccion> buscarPorFecha(LocalDate fecha){
        return transaccionRepository.findAll().stream().filter(f -> f.getFecha() != null && f.getFecha().equals(fecha)).collect(Collectors.toList());
    }
}
