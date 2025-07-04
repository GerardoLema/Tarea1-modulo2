package com.distribuida.entities;
public class Autor {
    private  int idautor;
    private String nombre;
    private String apellido;
    private String pais;
    private  String direccion;
    private  String telefono;
    private  String correo;
    public Autor(int idautor, String nombre, String apellido, String pais, String direccion, String telefono, String correo) {
        this.idautor = idautor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.pais = pais;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;    }

    public Autor() {

    }

    public int getIdautor() { return idautor;    }
    public void setIdautor(int idautor) { this.idautor = idautor;    }
    public String getNombre() { return nombre;    }
    public void setNombre(String nombre) {  this.nombre = nombre;    }
    public String getApellido() {        return apellido;
    }    public void setApellido(String apellido) { this.apellido = apellido;    }
    public String getPais() {return pais;    }
    public void setPais(String pais) { this.pais = pais;    }
    public String getDireccion() { return direccion;    }
    public void setDireccion(String direccion) { this.direccion = direccion;    }
    public String getTelefono() {   return telefono;    }
    public void setTelefono(String telefono) {   this.telefono = telefono;    }
    public String getCorreo() {  return correo;    }
    public void setCorreo(String correo) {this.correo = correo;    }
    @Override
    public String toString() {
        return "Autor{" +
                "idautor=" + idautor +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", pais='" + pais + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}