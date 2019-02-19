/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gateway1
 */
public class conBD {
//    private String url = "jdbc:sqlserver://192.168.6.75\\SQLEXPRESS:9205;databaseName=RCPT;";
//    private String urlcpt = "jdbc:sqlserver://192.168.6.75\\SQLEXPRESS:9205;databaseName=CPT;";
//    private String selfurl = "jdbc:sqlserver://192.168.6.75\\SQLEXPRESS:9205;databaseName=Venta;";
    private String url ="jdbc:sqlserver://192.168.6.8\\datos65:9205;databaseName=RCPT;";
    private String urlcpt ="jdbc:sqlserver://192.168.6.8\\datos65:9205;databaseName=CPT;";
    private String selfurl ="jdbc:sqlserver://192.168.6.8\\datos65:9205;databaseName=Venta;";
    private String drive = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    protected Connection conexion = null;
    public Connection getConexion() {
        return this.conexion;
    }
    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    public void abrir() throws ClassNotFoundException, SQLException {
        Class.forName(drive);
//        conexion = DriverManager.getConnection(url, "mich", "mich");
        //System.out.println();
        //conexion = DriverManager.getConnection(url, "sa", "Prok2001");
    }
    public void cerrar() throws SQLException {
        conexion.close();
    }
    // BD fuera de RCPT
    protected Connection conexions = null;
    public Connection getConexions() {
        return this.conexions;
    }
    public void setConexions(Connection conexions) {
        this.conexions = conexions;
    }
    public void abrirs() throws ClassNotFoundException, SQLException {
        Class.forName(drive);
//        conexions = DriverManager.getConnection(selfurl, "mich", "mich");
        //System.out.println();
        conexions = DriverManager.getConnection(selfurl, "sa", "Prok2001");
    }
    public void cerrars() throws SQLException {
        conexions.close();
    }
    // BD CPT
        protected Connection conexioncpt = null;
    public Connection getConexioncpt() {
        return this.conexioncpt;
    }
    public void setConexioncpt(Connection conexioncpt) {
        this.conexioncpt = conexioncpt;
    }
    public void abrircpt() throws ClassNotFoundException, SQLException {
        Class.forName(drive);
//        conexioncpt = DriverManager.getConnection(urlcpt, "mich", "mich");
        //System.out.println();
        //conexioncpt = DriverManager.getConnection(urlcpt, "sa", "Prok2001");
    }
    public void cerrarcpt() throws SQLException {
        conexioncpt.close();
    }
}
