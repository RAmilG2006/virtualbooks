package com.virtualbooks.controller;

import com.virtualbooks.model.Usuario;
import com.virtualbooks.repository.LibroRepository;
import com.virtualbooks.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final UsuarioRepository usuarioRepository;
    private final LibroRepository libroRepository;

    public DashboardController(UsuarioRepository usuarioRepository, LibroRepository libroRepository) {
        this.usuarioRepository = usuarioRepository;
        this.libroRepository = libroRepository;
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard(Authentication authentication, Model model) {
        String email = authentication.getName();
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario.getRol() == 1) {
            return "dashboard/admin"; // admin
        }

        // usuario normal
        model.addAttribute("usuario", usuario);
        model.addAttribute("libros", libroRepository.findByUserId(usuario.getId()));
        return "dashboard/user";
    }
}
