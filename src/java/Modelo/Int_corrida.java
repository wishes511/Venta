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
public interface Int_corrida {
    public Corrida getcorridawithID(int clave);
    public boolean Eliminar(int clave);
    public boolean Modificar(Corrida p);
    public boolean nuevoprod(Corrida p);
    public ArrayList<String> getall();
}
