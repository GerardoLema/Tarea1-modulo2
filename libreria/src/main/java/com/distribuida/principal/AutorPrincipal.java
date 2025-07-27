package com.distribuida.principal;
import com.distribuida.model.Autor;
public class AutorPrincipal {
    public static void main(String[]args){
        Autor autor = new Autor( 1, "Pamela", "Tapia", "Argentina","Av. sucre", "456874", "paulina@hotmail.com");
        System.out.println(autor.toString());
    }}