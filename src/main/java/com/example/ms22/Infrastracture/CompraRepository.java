package com.example.ms22.Infrastracture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ms22.Domain.Compra;



@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {
}