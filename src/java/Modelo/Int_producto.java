/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author gateway1
 */
public interface Int_producto {
    public Producto getprodwithID(int clave);
    public boolean Eliminar(int clave);
    public boolean Modificar(Producto p);
    public boolean nuevoprod(Producto p);
}
