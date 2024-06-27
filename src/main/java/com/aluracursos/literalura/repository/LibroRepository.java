package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.CategoriaIdioma;
import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findLibroByTitulo (String titulo);
    List<Libro> findLibrosByIdioma (CategoriaIdioma idioma);
}
