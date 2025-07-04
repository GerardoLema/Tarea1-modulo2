package com.distribuida.principal;
import com.distribuida.entities.Categoria;
public class CategoriaPrincipal {
    public static void main(String[]args){
        Categoria categoria = new Categoria(1,"Matematicas", "Analisis matematico");
        System.out.println(categoria.toString());
    }
}