/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GATEWAY1-
 */
public class VS_Cliente {

    public ArrayList<Cliente> getclientes(Connection c, int agente) {
        ArrayList<Cliente> arr = new ArrayList<>();
        try {
            PreparedStatement st;
            ResultSet rs;
            String sql = "select nombre,numcliente,CorreoE from clientes\n"
                    + "where status='A' and agente1=" + agente + "\n"
                    + "order by nombre";
            System.out.println("get all lientes " + sql);
            st = c.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Cliente cl = new Cliente();
                cl.setNombre(rs.getString("nombre"));
                cl.setNumcliente(rs.getInt("numcliente"));
                cl.setEmail(rs.getString("Correoe"));
                arr.add(cl);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(VS_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public Cliente getclient(Connection c, int cliente, String fecha) {
        Cliente cl = new Cliente();
        try {
            PreparedStatement st;
            ResultSet rs;
            String sql = "select cveembarque,agente1,plazo,convert(datetime,'"+fecha+"')+plazo as fechav ,CorreoE \n"
                    + "from clientes\n"
                    + "where numcliente="+cliente;
            System.out.println("get cliente " + sql);
            st = c.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                cl.setAgente(rs.getInt("agente1"));
                cl.setNumcliente(cliente);
                cl.setEmbaque(rs.getInt("cveembarque"));
                cl.setPlazo(rs.getInt("plazo"));
                cl.setFechav(rs.getString("fechav"));
                cl.setEmail(rs.getString("Correoe"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(VS_Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cl;
    }
}
