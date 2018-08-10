/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Corrida;
import Modelo.Int_pedido;
import Modelo.Pedido;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.VS_Pedido;

/**
 *
 * @author gateway1
 */
public class DAO_Pedido extends VS_Pedido implements Int_pedido{

    @Override
    public Pedido getprodwithID(int clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Connection conexionbd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closebd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
// nuevo pedido desde carrito
    @Override
    public int nuevoped(Pedido p,ArrayList<String> dis,ArrayList<Producto> cor, ArrayList<Corrida> corridas) {
        int last_pedido=0;
        try {
            last_pedido=nuevopedido(p,dis,cor,corridas);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return last_pedido;
    }

    @Override
    public int max_pedemp() {// Carrito
        int last =0;
        try {
            last=getlast();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return last;
    }

    @Override
    public ArrayList<Pedido> getall(String f1, String f2, String b) {
       ArrayList<Pedido> arr = new ArrayList<Pedido>();
        try {
            arr=getpeds(f1,f2,b);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr; //To change body of generated methods, choose Tools | Templates.
    }


    
}
