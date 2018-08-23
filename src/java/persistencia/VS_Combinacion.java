/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import Modelo.Combinacion;
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
public class VS_Combinacion extends conBD {


    // Busquedas--------------
        public Combinacion buscarCorID(int clave) throws ClassNotFoundException, SQLException {
        Combinacion p = new Combinacion();
        String query = "select PuntoInicial,PuntoFinal,c.Descripcion as Descripcion from Corridas c join Productos p on p.Corrida =c.Corrida"
                + " where p.producto="+clave+"";
        Statement smt;
        ResultSet df;
        abrir();
        Connection conect=getConexion();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
        }
        df.close();
        smt.close();
        return p;
    }
            public ArrayList<Combinacion> buscarall() throws ClassNotFoundException, SQLException {
        ArrayList<Combinacion> arr = new ArrayList<Combinacion>();
        
        String query = "select distinct descripcion,combinacion from Combinaciones order by descripcion";
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            Combinacion c = new Combinacion();
            c.setClave(df.getInt("Combinacion"));
            c.setDescripcion(df.getString("descripcion"));
            arr.add(c);
        }
        df.close();
        smt.close();
        return arr;
    }

}
