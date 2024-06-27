package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAPI(
        @JsonAlias("count") Integer numeroLibros,
        @JsonAlias("next") String sigPagina,
        @JsonAlias("previous") String pagAnterior,
        @JsonAlias("results") List<InfoLibros> resultado
) {
}
