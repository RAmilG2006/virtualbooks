package com.virtualbooks.controller;

import com.virtualbooks.model.Categoria;
import com.virtualbooks.model.Usuario;
import com.virtualbooks.repository.UsuarioRepository;
import com.virtualbooks.service.CategoriaService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/categorias") // <- esto coincide con tu formulario
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final UsuarioRepository usuarioRepository;

    public CategoriaController(CategoriaService categoriaService, UsuarioRepository usuarioRepository) {
        this.categoriaService = categoriaService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public String listarCategorias(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());
        List<Categoria> categorias = categoriaService.listarCategoriasPorUsuario(usuario.getId());
        model.addAttribute("categorias", categorias);
        model.addAttribute("categoriaForm", new Categoria());
        return "autores/categorias"; // Thymeleaf
    }

    @PostMapping("/guardar") // <- esto coincide con el formulario
    public String guardarCategoria(@AuthenticationPrincipal UserDetails userDetails,
                                   @ModelAttribute Categoria categoriaForm,
                                   Model model) {
        Usuario usuario = usuarioRepository.findByEmail(userDetails.getUsername());
        categoriaForm.setUsuario(usuario);

        categoriaService.guardarCategoria(categoriaForm);
        model.addAttribute("success", "Categoría guardada correctamente");
        return "redirect:/user/categorias";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id, Model model) {
        categoriaService.eliminarCategoria(id);
        model.addAttribute("success", "Categoría eliminada correctamente");
        return "redirect:/user/categorias";
    }

    @GetMapping("/editar/{id}")
    @ResponseBody
    public Categoria obtenerCategoria(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id);
    }
}
