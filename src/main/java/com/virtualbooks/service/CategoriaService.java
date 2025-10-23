package com.virtualbooks.service;

import com.virtualbooks.model.Categoria;
import com.virtualbooks.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategoriasPorUsuario(Long usuarioId) {
        return categoriaRepository.findByUsuarioId(usuarioId);
    }

    public void guardarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public Categoria obtenerPorId(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }

    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}
