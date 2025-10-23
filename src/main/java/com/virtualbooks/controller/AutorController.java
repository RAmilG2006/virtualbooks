package com.virtualbooks.controller;

import com.virtualbooks.model.Autor;
import com.virtualbooks.model.Usuario;
import com.virtualbooks.repository.UsuarioRepository;
import com.virtualbooks.service.AutorService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/autores")
public class AutorController {

    private final AutorService autorService;
    private final UsuarioRepository usuarioRepository;

    public AutorController(AutorService autorService, UsuarioRepository usuarioRepository) {
        this.autorService = autorService;
        this.usuarioRepository = usuarioRepository;
    }

    // Listar autores del usuario
    @GetMapping
    public String listarAutores(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());
        List<Autor> autores = autorService.listarAutoresPorUsuario(usuario.getId());
        model.addAttribute("autores", autores);
        return "autores/list";
    }

    // Obtener autor por ID (AJAX)
    @GetMapping("/editar/{id}")
    @ResponseBody
    public Autor obtenerAutor(@PathVariable Long id) {
        return autorService.obtenerPorId(id);
    }

    // Agregar autor
    @PostMapping("/agregar")
    public String agregarAutor(@AuthenticationPrincipal UserDetails userDetails,
                               @RequestParam String nombre,
                               @RequestParam String nacionalidad,
                               @RequestParam String fechaNacido) {
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());
        if (usuario == null) return "redirect:/user/autores";

        Autor autor = new Autor();
        autor.setNombre(nombre);
        autor.setNacionalidad(nacionalidad);
        autor.setFechaNacido(fechaNacido);
        autor.setUsuario(usuario);

        autorService.guardarAutor(autor);
        return "redirect:/user/autores";
    }

    // Editar autor
    @PostMapping("/editar/{id}")
    public String editarAutor(@PathVariable Long id,
                              @RequestParam String nombre,
                              @RequestParam String nacionalidad,
                              @RequestParam String fechaNacido,
                              @AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());
        if (usuario == null) return "redirect:/user/autores";

        Autor autor = autorService.obtenerPorId(id);
        if (autor == null) return "redirect:/user/autores";

        autor.setNombre(nombre);
        autor.setNacionalidad(nacionalidad);
        autor.setFechaNacido(fechaNacido);
        autor.setUsuario(usuario);

        autorService.guardarAutor(autor);
        return "redirect:/user/autores";
    }

    // Eliminar autor
    @PostMapping("/eliminar/{id}")
    public String eliminarAutor(@PathVariable Long id) {
        autorService.eliminarAutor(id);
        return "redirect:/user/autores";
    }
}
