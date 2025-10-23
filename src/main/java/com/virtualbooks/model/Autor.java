package com.virtualbooks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String nacionalidad;

    // Guardamos la fecha como String
    private String fechaNacido;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario usuario;

    // ---------------- Getters y Setters ----------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public String getFechaNacido() { return fechaNacido; }
    public void setFechaNacido(String fechaNacido) { this.fechaNacido = fechaNacido; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
