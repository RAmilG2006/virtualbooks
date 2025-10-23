package com.virtualbooks.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    @Column(length = 100, nullable = false)
    private String nombre;

    private String imagen;

    @OneToMany(mappedBy = "category")
    private List<Libro> libros;
}
