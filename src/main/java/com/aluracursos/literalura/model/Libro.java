package com.aluracursos.literalura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private  String titulo;
    @Enumerated(EnumType.STRING)
    private CategoriaIdioma idioma;
    @ManyToOne
    private Autor autor;
    private Integer descargas;

    public Libro(){}

    public Libro (InfoLibros infoLibros){
        this.titulo = infoLibros.titulo();
        this.autor = new Autor(infoLibros.autores().get(0));
        this.descargas = infoLibros.cantidadDescargas();
        this.idioma = CategoriaIdioma.fromString(infoLibros
                .idiomas().toString().split(",")[0].trim());
    }

    @Override
    public String toString() {
        return "*-*-*-*-*Libro*-*-*-*-*-*" + '\n' +
                "Titulo: " + titulo + '\n' +
                "Idioma: " + idioma + '\n'+
                "Autor: " + autor.getNombre() +'\n'+
                "Numero de Descargas: " + descargas+ '\n';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public CategoriaIdioma getIdioma() {
        return idioma;
    }

    public void setIdioma(CategoriaIdioma idioma) {
        this.idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }
}
