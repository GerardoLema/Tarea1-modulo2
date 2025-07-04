package com.distribuida.principal;

import com.distribuida.entities.Autor;
import com.distribuida.entities.Categoria;
import com.distribuida.entities.Libro;
import java.util.Date;
public class LibroPrincipal {
    public static void main(String[] args){
        Libro libro = new Libro();
        Autor autor = new Autor( 1, "Pamela", "Tapia", "Argentina","Av. sucre", "456874", "paulina@hotmail.com");
        Categoria categoria = new Categoria(1,"Matematicas", "Analisis matematico");
        libro.setIdlibro(1);
        libro.setTitulo("Programacion web");
        libro.setEditorial("Manning");
        libro.setNumpaginas(200);
        libro.setEdicion("2th");
        libro.setIdioma("Español");
        libro.setFechapublicacion(new Date());
        libro.setDescripcion("programacion básica");
        libro.setTipopasta("Pasta dura");
        libro.setIsbn("978-1617296644");
        libro.setNumejemplares(2);
        libro.setPortada("Blanca");
        libro.setPresentacion("Virtual");
        libro.setPrecio( 23.50 );
        //inyeccion de dependencia
        libro.setAutor(autor);
        libro.setCategoria(categoria);
        System.out.println(libro.toString());
    }
}