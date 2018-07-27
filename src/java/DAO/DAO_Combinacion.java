/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Combinacion;
import Modelo.Int_combinacion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.VS_Combinacion;

/**
 *
 * @author gateway1
 */
public class DAO_Combinacion extends VS_Combinacion implements Int_combinacion{

    @Override
    public Combinacion getprodwithID(int clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Eliminar(int clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Modificar(Combinacion p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Combinacion getprodwithID_nochar(int clave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Combinacion> getall() {
        ArrayList<Combinacion> arr = new ArrayList<Combinacion>();
        try {
            arr=buscarall();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Combinacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Combinacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;    
    }

    @Override
    public String nuevocamb(Combinacion p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
