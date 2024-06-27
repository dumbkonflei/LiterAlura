package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name ="autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private Integer nace;
    private Integer muere;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor(){}

    public Autor (InfoAutor infoAutor){
        this.nombre = infoAutor.nombre();
        this.nace = infoAutor.nace();
        this.muere = infoAutor.muere();
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNace() {
        return nace;
    }

    public void setNace(Integer nace) {
        this.nace = nace;
    }

    public Integer getMuere() {
        return muere;
    }

    public void setMuere(Integer muere) {
        this.muere = muere;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public String librosAutor (List<Libro> libros) {
        return libros.stream()
                .map(Libro::getTitulo).collect(Collectors.joining("\n"));
    }
    @Override
    public String toString() {
        return  "Autor: " + nombre + '\n' +
                "Nació: " + nace + '\n' +
                "Murió: " + muere + '\n' +
                "Libros publicados: " + librosAutor(libros)+ "\n";

    }

}



