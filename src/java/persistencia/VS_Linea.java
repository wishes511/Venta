/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import Modelo.Combinacion;
import Modelo.Corrida;
import Modelo.Linea;
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
public class VS_Linea extends conBD {


    // Busquedas--------------
        public Linea buscarLinID(int clave) throws ClassNotFoundException, SQLException {
        Linea l = new Linea();
        String query = "";
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
        return l;
    }
         public int nuevalinea(Producto p) throws ClassNotFoundException, SQLException {
        int l=0;
        try{
        String query = "select MAX(linea) as 'linea' from Lineas";
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect=getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            l=df.getInt("linea");
        }
        l+=+1;
        df.close();
        smt.close();
        PreparedStatement st = null;
        getConexions().setAutoCommit(false);
            String s = "insert into Lineas values("+l+",'"+p.getLineachar()+"')";
//            System.out.println(s);
            st = getConexions().prepareStatement(s);
            st.executeUpdate();
            st.close();
            getConexions().commit();
            } catch (Exception e) {
            Logger.getLogger(VS.class.getName()).log(Level.SEVERE, null, e);
            try {
                getConexions().rollback();
            } catch (Exception o) {
                System.out.println(o.getMessage());
            }   
    }
        return l;
    }
        public ArrayList<Linea> buscarall() throws ClassNotFoundException, SQLException {
        ArrayList<Linea> arr = new ArrayList<Linea>();
        
        String query = "select linea,descripcion from Lineas order by descripcion";
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            Linea l = new Linea();
            l.setClave(df.getInt("linea"));
            l.setDescripcion(df.getString("descripcion"));
            arr.add(l);
        }
        df.close();
        smt.close();
        return arr;
    }
        public ArrayList<Linea> buscarall_consulta(String tipo) throws ClassNotFoundException, SQLException {
        ArrayList<Linea> arr = new ArrayList<Linea>();
        String query="";
        
        query = "select distinct p.submarca as 'submarca' from DPedidos dp join Productos p on dp.producto=p.producto\n" +
        "group by submarca";
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            Linea l = new Linea();
            l.setDescripcion(df.getString("submarca"));
            arr.add(l);
        }
        df.close();
        smt.close();
        return arr;
    }

}
