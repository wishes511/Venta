/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Corrida;
import Modelo.Int_corrida;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.VS_Corrida;

/**
 *
 * @author gateway1
 */
public class DAO_Corrida extends VS_Corrida implements Int_corrida {

    @Override
    public Corrida getcorridawithID(int clave) {
    Corrida c = new Corrida();
        try {
            c=buscarCorID(clave);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Corrida.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Corrida.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return c;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Eliminar(int clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Modificar(Corrida p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean nuevoprod(Corrida p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<String> getall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
