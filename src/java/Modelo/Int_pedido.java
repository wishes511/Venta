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
    public ArrayList<Pedido> getall(String f1,String f2,String b,String status);
    public Connection conexionbd();
    public void closebd();
    public String nuevoped (Pedido p,ArrayList<String> dis,ArrayList<Producto> cor, ArrayList<Corrida> corridas);
    public int max_pedemp();
    public ArrayList<Pedido> getcliente_consulta();
    public void alta(String a);
    public void baja(String a);
}
