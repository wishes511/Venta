/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Int_producto;
import Modelo.Producto;
import java.sql.SQLException;
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
            abrir();
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
    public boolean nuevoprod(Producto p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
