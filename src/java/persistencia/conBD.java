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

    private String url = "jdbc:sqlserver://192.168.6.8\\datos65:9205;databaseName=RCPT;";
    private String urlcpt = "jdbc:sqlserver://192.168.6.8\\datos65:9205;databaseName=CPT;";
    private String url68 = "jdbc:sqlserver://192.168.6.8\\datos65:9205;databaseName=";
    private String urlB = "jdbc:sqlserver://192.168.90.1\\datos620:1433;database=";
//    private String url68="jdbc:sqlserver://192.168.6.8\\datos65:9205;databaseName=";
    private String selfurl = "jdbc:sqlserver://192.168.6.8\\datos65:9205;databaseName=Venta;";
    private String drive = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    protected static Connection conexion = null;
    protected static Connection conexion68 = null;
    protected static Connection conexion68cpt = null;
    protected static Connection conexion68cob = null;
    protected static Connection conexion68cobB = null;
    

    public Connection getConexion() {
        return conBD.conexion;
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
        System.out.println("Abrir bdventa");
        conexions = DriverManager.getConnection(selfurl, "sa", "Prok2001");
    }

    /**
     * Abrir todas las conexiones del 68 rcpt,cpt,y cobranza
     * @param bd
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public void abrir68(String bd) throws ClassNotFoundException, SQLException {
        Class.forName(drive);
        System.out.println("Abrir 68 y mas conexiones");
//        conexions = DriverManager.getConnection(selfurl, "mich", "mich");
//        conexion68 = DriverManager.getConnection(url68 + "RCPT", "sa", "Prok2001");
//        conexion68cpt=DriverManager.getConnection(url68 + "CPT", "sa", "Prok2001");
//        conexion68cob = DriverManager.getConnection(url68 + "ACobranza", "sa", "Prok2001");
        //conexion68cobB = DriverManager.getConnection(urlB + "RaCobranza", "sa", "Admin1305");
//        Conexiones de prueba
        conexion68 = DriverManager.getConnection(url68 + "fatimaRCPT", "sa", "Prok2001");
        conexion68cpt=DriverManager.getConnection(url68 + "fatimaCPT", "sa", "Prok2001");
        conexion68cob = DriverManager.getConnection(url68 + "ACobranzaFH", "sa", "Prok2001");
//        conexion68cobB = DriverManager.getConnection(urlB + "RaCobranzaFH", "sa", "Admin1305");
    }
    
    public Connection get68(){
        return conexion68;
    }
    public Connection get68cpt(){
        return conexion68cpt;
    }
    public Connection get68cob(){
        return conexion68cob;
    }
    public Connection get68cobB(){
        return conexion68cobB;
    }
    /**
     * Cerrar todas las conexiones del 68 rcpt,cpt,y cobranza
     * @throws SQLException 
     */
    public void cerrar68() throws SQLException{
        conexion68.close();
        conexion68cpt.close();
        conexion68cob.close();
        //conexion68cobB.close();
        System.out.println("cerrar68 conexiones");
    }

    public void cerrars() throws SQLException {
        conexions.close();
        System.out.println("cerrarvta");
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
