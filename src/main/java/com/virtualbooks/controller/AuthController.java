package com.virtualbooks.controller;

import com.virtualbooks.dto.UsuarioRegistroDto;
import com.virtualbooks.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/auth/register")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("user", new UsuarioRegistroDto());
        return "auth/register";
    }

    @PostMapping("/auth/register")
    public String registrarUsuario(UsuarioRegistroDto userDto, Model model) {
        try {
            usuarioService.registrarUsuario(userDto);
            model.addAttribute("successMessage", "Registro exitoso. Ahora puedes iniciar sesión.");
            model.addAttribute("user", new UsuarioRegistroDto()); // para que el formulario no falle
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("user", userDto); // mantener los datos ingresados
        }
        return "auth/register";
    }
}

