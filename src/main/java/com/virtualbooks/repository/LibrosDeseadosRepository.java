package com.virtualbooks.repository;

import com.virtualbooks.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibrosDeseadosRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByUserId(Long userId); // Libros favoritos de un usuario
}
