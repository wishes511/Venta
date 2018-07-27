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
public interface Int_combinacion {
    public Combinacion getprodwithID(int clave);
    public boolean Eliminar(int clave);
    public boolean Modificar(Combinacion p);
    public String nuevocamb(Combinacion p);
    public Combinacion getprodwithID_nochar(int clave);
    public ArrayList<Combinacion> getall();
}
