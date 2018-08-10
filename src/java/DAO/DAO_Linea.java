/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Int_linea;
import Modelo.Linea;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.VS_Linea;

/**
 *
 * @author gateway1
 */
public class DAO_Linea extends VS_Linea implements Int_linea {

    @Override
    public Linea getcorridawithID(int clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Eliminar(int clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Modificar(Linea p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean nuevoprod(Linea p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Linea> getall() {
        ArrayList<Linea> arr= new ArrayList<Linea>();
        try {
            arr=buscarall();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Linea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Linea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    @Override
    public ArrayList<Linea> getLinea(String v) {
        ArrayList<Linea> arr= new ArrayList<Linea>();
        try {
            arr=buscarall_consulta(v);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Linea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Linea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    
}
