package com.distribuida.principal;
import com.distribuida.entities.Cliente;
public class ClientePrincipal {
    public static void main(String[]args){
        Cliente cliente = new Cliente(1,"0502474489" , "Juan", "Tapia" , "Av. Unidad Nacional", "0982261075", "gl_ert@hotmail.com");
        System.out.println(cliente.toString());
    }
}
