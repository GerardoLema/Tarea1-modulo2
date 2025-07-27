package com.distribuida.model;
public class FacturaDetalle {
    private int idfacturadetalle;
    private int cantidad;
    private double subtotal;
    private Factura factura;
    private Libro libro;
    public FacturaDetalle() {
        this.idfacturadetalle = idfacturadetalle;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.factura = factura;
        this.libro = libro;}
    public int getId_factura_detalle() {
        return idfacturadetalle;
    }
    public void setIdfacturadetalle(int idfacturadetalle) {
        this.idfacturadetalle = idfacturadetalle;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
    public Factura getFactura(Factura factura) {
        return this.factura;
    }
    public Libro getLibro() {
        return libro;
    }
    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    @Override
    public String toString() {
        return "FacturaDetalle{" +
                "id_factura_detalle=" + idfacturadetalle +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                ", factura=" + factura +
                ", libro=" + libro +
                '}';}}
