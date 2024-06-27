package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record InfoLibros(
        @JsonAlias("id")Long idLibro,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<InfoAutor> autores,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Integer cantidadDescargas
) {
}
