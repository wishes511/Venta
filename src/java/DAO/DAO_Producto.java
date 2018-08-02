/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Int_producto;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.VS;

/**
 *
 * @author gateway1
 */
public class DAO_Producto extends VS implements Int_producto{

    @Override
    public Producto getprodwithID(int clave) {
        Producto p =new Producto();
        try {
            abrirs();
            p=buscarprodID(clave);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }

    @Override
    public boolean Eliminar(int clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Modificar(Producto p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
        public Producto getprodwithID_nochar(int clave) {
        Producto p=new Producto();
        try {
            abrir();
            p=buscarprodID_nochar(clave);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p;
    }

    @Override
    public ArrayList<Producto> getall() {
    ArrayList<Producto> arr= new ArrayList<>();
        try {
            abrirs();
            arr=buscarall();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    @Override
    public String nuevoprod(Producto p) {
        String msj="";
        try {
            msj= nuevoproducto(p);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msj;
    }

    @Override
    public boolean isexist(int estilo, int combinacion, int corrida) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Connection conexionbd() {
        try {
            abrirs();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    return getConexions();
    }

    @Override
    public void closebd() {
        try {
            cerrars();
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Producto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
