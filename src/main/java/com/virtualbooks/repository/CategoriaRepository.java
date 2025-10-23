package com.virtualbooks.repository;

import com.virtualbooks.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByUsuarioId(Long usuarioId);
}
