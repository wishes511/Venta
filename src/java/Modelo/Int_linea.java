/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author gateway1
 */
public interface Int_linea {
    public Linea getcorridawithID(int clave);
    public boolean Eliminar(int clave);
    public boolean Modificar(Linea p);
    public boolean nuevoprod(Linea p);
    public ArrayList<Linea> getall();
}
