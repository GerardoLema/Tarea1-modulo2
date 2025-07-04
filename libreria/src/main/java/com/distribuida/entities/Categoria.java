package com.distribuida.entities;

public class Categoria {
    private int idcategoria;
    private String categoria;
    private  String descripcion;

    public Categoria(int idcategoria, String categoria, String descripcion) {
        this.idcategoria = idcategoria;
        this.categoria = categoria;
        this.descripcion = descripcion;}

    public Categoria() {

    }

    public int getIdcategoria() {
        return idcategoria;}
    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;}
    public String getCategoria() {
        return categoria;}
    public void setCategoria(String categoria) {
        this.categoria = categoria;}
    public String getDescripcion() {
        return descripcion;}
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;}
    @Override
    public String toString() {
        return "Categoria{" +
                "idcategoria=" + idcategoria +
                ", categoria='" + categoria + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}