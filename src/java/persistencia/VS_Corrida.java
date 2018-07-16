/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import Modelo.Corrida;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mich
 */
public class VS_Corrida extends conBD {


    // Busquedas--------------
        public Corrida buscarCorID(int clave) throws ClassNotFoundException, SQLException {
        Corrida p = new Corrida();
        String query = "select PuntoInicial,PuntoFinal,c.Descripcion as Descripcion from Corridas c join Productos p on p.Corrida =c.Corrida"
                + " where p.producto="+clave+"";
        Statement smt;
        ResultSet df;
        abrir();
        Connection conect=getConexion();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            p.setCorrida(clave);
            p.setPi(df.getInt("PuntoInicial"));
            p.setPf(df.getInt("PuntoFinal"));
            p.setDesc(df.getString("Descripcion"));
        }
        df.close();
        smt.close();
        return p;
    }

}
