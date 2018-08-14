/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Int_usuario;
import Modelo.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.VS_Usuario;

/**
 *
 * @author gateway1
 */
public class DAO_Usuario extends VS_Usuario implements Int_usuario {

    @Override
    public Usuario getUsuario(Usuario u) {
        try {
            u=buscauser(u);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }


}
