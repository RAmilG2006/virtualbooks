package com.virtualbooks.repository;

import com.virtualbooks.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByUserId(Long userId);           // Todos los libros de un usuario
    List<Libro> findByAuthorId(Long authorId);       // Libros de un autor
    List<Libro> findByCategoryId(Long categoryId);   // Libros de una categoría
}
