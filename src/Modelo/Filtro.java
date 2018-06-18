/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author LN710Q
 */
public class Filtro {
    
    private String nombre, tipo;
    private int cant;
    private float precio;
    
    public Filtro(){}
    
    public Filtro(String nombre, String tipo, int cant, float precio){
        this.nombre = nombre;
        this.cant = cant;
        this.tipo = tipo;
        this.precio = precio;
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCant() {
        return cant;
    }

    public float getPrecio() {
        return precio;
    }
    
    
    
}
