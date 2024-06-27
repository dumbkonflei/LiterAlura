package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumiAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ConsumiAPI consumoAPI = new ConsumiAPI();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";
    private List<Libro> libros;
    private List<Autor> autores;
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;


    public Principal(AutorRepository autorRepository, LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void mostrarMenu() {
        int eleccion = 0;
        String menu = """
                ****************************************************
                ******************** Literalura ********************
                    Saludos, bienvenido al buscador de libros! =D
                         
                1.- Buscar libro por título.
                2.- Listar libros registrados.
                3.- Listar autores registrados.
                4.- Listar autores vivos en un determinado año.
                5.- Listar libros por idioma.
                          
                9.- Salir.
                """;


        while (eleccion != 9) {
            System.out.println(menu);
            try {
                eleccion = teclado.nextInt();
                teclado.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, ingrese alguno de los números mostrados en pantalla");
                teclado.nextLine();
            }

        switch (eleccion) {
            case 1:
                buscarLibroPorTitulo();
                break;
            case 2:
                listarLibrosRegistrados();
                break;
            case 3:
                listarAutoresRegistrados();
                break;
            case 4:
                listarAutoresPorAño();
                break;
            case 5:
                listarLibrosPorIdioma();
                break;
            case 9:
                System.out.println("Saliendo.Que tenga un excelente día! =D");
                break;
            default:
                System.out.println("Entrada inválida, ingrese alguno de los números mostrados en pantalla");
        }

    }
    }
    private String realizarConsulta() {
        System.out.println("Escriba el nombre del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        String url = URL_BASE + "?search=" + nombreLibro.replace(" ", "%20");
        System.out.println("Esperando resultados...");
        String respuesta = consumoAPI.obtenerDatosApi(url);
        return respuesta;

    }

    private void buscarLibroPorTitulo() {
        String respuesta = realizarConsulta();
        DatosAPI consultaDatosAPI = convierteDatos.obtenerDatos(respuesta, DatosAPI.class);
        if (consultaDatosAPI.numeroLibros() != 0) {
            InfoLibros primerLibro = consultaDatosAPI.resultado().get(0);
            Autor autorLibro = new Autor(primerLibro.autores().get(0));
            Optional<Libro> libroBase = libroRepository.findLibroByTitulo(primerLibro.titulo());
            if (libroBase.isPresent()) {
                System.out.println("No se puede registrar el mismo libro");
            } else {
                Optional<Autor> autorDeBase = autorRepository.findBynombre(autorLibro.getNombre());
                if (autorDeBase.isPresent()) {
                    autorLibro = autorDeBase.get();
                } else {
                    autorRepository.save(autorLibro);
                }
                    Libro libro = new Libro((primerLibro));
                libro.setAutor(autorLibro);
                libroRepository.save(libro);
                System.out.println(libro);

            }
        } else {
            System.out.println("Libro no encontrado");
        }


    }

    private void listarLibrosRegistrados() {
        libros = libroRepository.findAll();
        libros.stream().forEach(System.out::println);

    }

    private void listarAutoresRegistrados() {
        autores = autorRepository.findAll();
        autores.stream().forEach(System.out::println);

    }

    private void listarAutoresPorAño() {
        System.out.println("Ingrese el año en el que vivio el/los Autor(es) que desea buscar: ");
        try {
            Integer año = teclado.nextInt();
            autores = autorRepository.findAutoresByYear(año);
            if (autores.isEmpty()){
                System.out.println("No hay autores en ese rango");
            } else {
                autores.stream().forEach(System.out::println);
            }
        } catch (InputMismatchException e){
            System.out.println("Ingrese un año correcto");
            teclado.nextLine();
        }
    }

    private void listarLibrosPorIdioma() {
        String menuIdioma = """
                ¿En qué idioma quiere buscar sus libros?
                es -> Español
                en -> Inglés
                pt -> Portugues
                """;
        System.out.println(menuIdioma);
        String idiomaDeseado = teclado.nextLine();
        CategoriaIdioma idioma = null;
        switch (idiomaDeseado) {
            case "es":
                idioma = CategoriaIdioma.fromEspanol("Español");
                break;
            case "en":
                idioma = CategoriaIdioma.fromEspanol("Ingles");
                break;
            case "pt":
                idioma = CategoriaIdioma.fromEspanol("Portugues");
                break;
            default:
                System.out.println("Idioma Inválido");
                return;
        }
        buscarPorIdioma(idioma);

    }

    private void buscarPorIdioma(CategoriaIdioma idioma) {
        libros = libroRepository.findLibrosByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros guardados");
        } else {
            libros.stream().forEach(System.out::println);
        }
    }


}
