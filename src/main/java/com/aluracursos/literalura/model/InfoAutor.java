package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InfoAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer nace,
        @JsonAlias("death_year") Integer muere){
}

