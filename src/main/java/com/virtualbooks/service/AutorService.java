package com.virtualbooks.service;

import com.virtualbooks.model.Autor;
import com.virtualbooks.repository.AutorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> listarAutoresPorUsuario(Long userId) {
        return autorRepository.findByUsuarioId(userId);
    }

    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    public void eliminarAutor(Long id) {
        autorRepository.deleteById(id);
    }

    public Autor obtenerPorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }
}

