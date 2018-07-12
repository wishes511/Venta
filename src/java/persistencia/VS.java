/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


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
public class VS extends conBD {


    // Busquedas--------------
        public Producto buscarprodID(int clave) throws ClassNotFoundException, SQLException {
        Producto p = new Producto();
        String query = "select p.estilo as 'estilo',(m.Descripcion+' - '+col.Descripcion) as 'combinacion',c.Descripcion as 'corrida' from Productos p \n" +
"join Corridas c on p.Corrida=c.Corrida\n" +
"join Combinaciones com on p.Combinacion=com.Combinacion\n" +
"join Materiales m on m.Material=com.Material1\n" +
"join Colores col on com.Color1=col.Color\n" +
" where producto="+clave+"";
        Statement smt;
        ResultSet df;
        abrir();
        Connection conect=getConexion();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            p.setEstilo(df.getInt("estilo"));
            p.setCombinacionchar("combinacion");
            p.setCorridachar("corrida");
        }
        df.close();
        smt.close();
        return p;
    }

}
