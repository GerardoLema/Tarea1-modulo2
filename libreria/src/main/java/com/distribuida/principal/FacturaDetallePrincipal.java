package com.distribuida.principal;
import com.distribuida.entities.Factura;
import com.distribuida.entities.FacturaDetalle;
import com.distribuida.entities.Libro;
public class FacturaDetallePrincipal {
    public static void main(String[] args){
        FacturaDetalle facturadetalle=new FacturaDetalle();
        Factura factura=new Factura();
        Libro libro=new Libro();
        facturadetalle.setIdfacturadetalle(1);
        facturadetalle.setCantidad(2);
        facturadetalle.setSubtotal(23.50);
        //inyecion de dependencia
        facturadetalle.getFactura(factura);
        facturadetalle.setLibro(libro);
        System.out.println(facturadetalle.toString());
    }
}