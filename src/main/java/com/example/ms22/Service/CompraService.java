package com.example.ms22.Service;

import com.example.ms22.Domain.Compra;
import com.example.ms22.Infrastracture.CompraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@Service
public class CompraService {

    @Autowired
    public CompraRepository compraRepository;

    public List<Compra> getAllCompra() { return compraRepository.findAll(); }

    public Compra saveCompra(Compra compra){ return compraRepository.save(compra); }

    public Optional<Compra> updateCompra(Long id, Compra compra){
        Optional<Compra> optionalCompra = compraRepository.findById(id);
        if(optionalCompra.isPresent()){
            Compra eixstingCompra = optionalCompra.get();
            eixstingCompra.setFecha_compra(compra.getFecha_compra());
            eixstingCompra.setMetodo_de_pago(compra.getMetodo_de_pago());
            eixstingCompra.setMonto_Total(compra.getMonto_Total());

            compraRepository.save(eixstingCompra);
        }
        return optionalCompra;
    }

    public Optional<Compra> patchCompra(Long id,Compra compra) {
        Optional<Compra> optionalCompra = compraRepository.findById(id);
        if (optionalCompra.isPresent()) {
            Compra existingCompra = optionalCompra.get();
            if (compra.getFecha_compra() != null) {
                existingCompra.setFecha_compra(compra.getFecha_compra());
            }
            if (compra.getMetodo_de_pago() != null) {
                existingCompra.setMetodo_de_pago(compra.getMetodo_de_pago());
            }
            if (compra.getMonto_Total() != null) {
                existingCompra.setMonto_Total(compra.getMonto_Total());
            }

            compraRepository.save(existingCompra);
        }
        return optionalCompra;
    }

    public Optional<Compra> deleteCompra(Long id) {
        Optional<Compra> optionalCompra = compraRepository.findById(id);
        if (optionalCompra.isPresent()) {
            Compra existingCompra = optionalCompra.get();
            compraRepository.delete(existingCompra);
        }
        return optionalCompra;
    }

    public Optional<Compra> getCompra(Long id) {
        return compraRepository.findById(id);
    }



}