/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author gateway1
 */
public interface Int_producto {

    public Producto getprodwithID(int clave);

    public boolean Eliminar(int clave);

    public boolean Modificar(Producto p);

    public String nuevoprod(Producto p);

    public Producto getprodwithID_nochar(int clave);

    public ArrayList<Producto> getall();

    public boolean isexist(int estilo, int combinacion, int corrida);

    public ArrayList<Producto> getprod_consulta();

    public Connection conexionbd();

    public void closebd();
    
    public ArrayList<Producto> getprodxestilo(String estilo, String stock);
    
    //public Producto getprodwithpuntos(int prod, int alm);
    
    public Producto getprodwithpuntos(int prod, int alm, String stock);
}
