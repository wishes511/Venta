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
public interface Int_pedido {
    public Pedido getprodwithID(int clave);
    public ArrayList<Producto> getall();
    public Connection conexionbd();
    public void closebd();
}
