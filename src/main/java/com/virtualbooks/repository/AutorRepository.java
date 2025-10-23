package com.virtualbooks.repository;

import com.virtualbooks.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByUserId(Long userId); // Todos los autores de un usuario
}
