package com.virtualbooks.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Usuario user;

    @Column(length = 30, nullable = false)
    private String nombre;

    @Column(length = 100, nullable = false)
    private String nacionalidad;

    private LocalDate fechaNacido;

    private String foto;
}
