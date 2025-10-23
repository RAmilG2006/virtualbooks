package com.virtualbooks.controller;

import com.virtualbooks.model.Libro;
import com.virtualbooks.model.Usuario;
import com.virtualbooks.repository.LibroRepository;
import com.virtualbooks.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserDashboardController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LibroRepository libroRepository;

    @GetMapping("/user/dashboard")
    public String userDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        // Obtener usuario logeado
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());

        // Todos los libros del usuario
        List<Libro> libros = libroRepository.findByUserId(usuario.getId());

        // Libros por categoría
        Map<String, Long> librosPorCategoria = libros.stream()
                .collect(Collectors.groupingBy(l -> l.getCategory() != null ? l.getCategory().getNombre() : "Sin categoría",
                        Collectors.counting()));

        // Libros por autor
        Map<String, Long> librosPorAutor = libros.stream()
                .collect(Collectors.groupingBy(l -> l.getAuthor() != null ? l.getAuthor().getNombre() : "Sin autor",
                        Collectors.counting()));

        // Libros añadidos por mes (simplificado, solo mes-año)
        Map<String, Long> librosPorFecha = libros.stream()
                .collect(Collectors.groupingBy(l -> l.getCreatedAt().getMonthValue() + "/" + l.getCreatedAt().getYear(),
                        Collectors.counting()));

        model.addAttribute("usuario", usuario);
        model.addAttribute("libros", libros);
        model.addAttribute("librosPorCategoria", librosPorCategoria);
        model.addAttribute("librosPorAutor", librosPorAutor);
        model.addAttribute("librosPorFecha", librosPorFecha);

        return "user/dashboard";
    }
}
