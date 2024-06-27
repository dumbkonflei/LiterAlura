package com.aluracursos.literalura.model;

public enum CategoriaIdioma {
    ESPANOL("es", "Español"),
    INGL("en", "Ingles"),
    PORTUGUES("pt", "Portugues");

    private final String categoriaGutedenx;
    private final String categoriaEspanol;


    CategoriaIdioma(String categoriaGutedenx, String categoriaEspanol){
        this.categoriaEspanol = categoriaEspanol;
        this.categoriaGutedenx = categoriaGutedenx;
    }

    public static CategoriaIdioma fromString(String text) {
        for (CategoriaIdioma categoriaIdioma : CategoriaIdioma.values()) {
            if (categoriaIdioma.categoriaGutedenx.equalsIgnoreCase(text)) {
                return categoriaIdioma;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada" + text);
    }
    public static CategoriaIdioma fromEspanol (String text){
        for (CategoriaIdioma categoriaIdioma : CategoriaIdioma.values()){
            if (categoriaIdioma.categoriaEspanol.equalsIgnoreCase(text)){
                return categoriaIdioma;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }
}
