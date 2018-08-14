/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author mich
 */
public class VS_Usuario extends conBD {

    // Busquedas--------------

    public Usuario buscauser(Usuario u) throws ClassNotFoundException, SQLException{
        String query = "select p.descripcion as 'tipo' from usuarios u join privilegios p on u.clave_tipo=p.clave_tipo where "
                + " usuario='"+u.getUsuario()+"' and contrasena='"+u.getPass()+"' ";
        System.out.println(query);
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            u.setTipo(df.getString("tipo"));
        }
        df.close();
        smt.close();
        return u;
    }
}
