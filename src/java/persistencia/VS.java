/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Modelo.Linea;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
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
        p.setEstilo(0);
        String query = "select p.estilo as 'estilo',com.descripcion as 'combinacion',c.Descripcion as 'corrida' from Productos p \n"
                + "join Corridas c on p.Corrida=c.Corrida\n"
                + "join Combinaciones com on p.Combinacion=com.Combinacion\n"
                + " where producto=" + clave + "";
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            p.setEstilo(df.getInt("estilo"));
            p.setCombinacionchar(df.getString("combinacion"));
            p.setCorridachar(df.getString("corrida"));
        }
        //System.out.println(query + "\n " + p.getCombinacionchar() + " " + p.getCorridachar());
        df.close();
        smt.close();
        return p;
    }

    public Producto buscarprodID_nochar(int clave) throws ClassNotFoundException, SQLException {
        ArrayList<Producto> arr = new ArrayList<>();
        Producto p = new Producto();
        String query = "select p.corrida as 'corrida',p.estilo as 'estilo',p.Producto as 'producto',p.combinacion as 'combinacion',p.linea as 'linea',p.tipo as 'desc',p.costo as 'costo', "
                + "c.descripcion as 'corridac',com.descripcion as 'combinacionchar' from Productos p \n"
                + "join Corridas c on p.Corrida=c.Corrida \n"
                + "join Combinaciones com on p.Combinacion=com.Combinacion \n"
                +" where producto=" + clave + "";
        System.out.println(query);
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            p.setClave_corrida(df.getInt("corrida"));
            p.setEstilo(df.getInt("estilo"));
            p.setProducto(df.getInt("producto"));
            p.setClave_combinacion(df.getInt("combinacion"));
            p.setClave_linea(df.getInt("linea"));
            p.setTipo(df.getString("desc"));
            p.setCorridachar(df.getString("corridac"));
            p.setCombinacionchar(df.getString("combinacionchar"));
            p.setCostof(df.getFloat("costo"));
        }
        df.close();
        smt.close();
        return p;
    }

    public ArrayList<Producto> buscarall() throws ClassNotFoundException, SQLException {
        ArrayList<Producto> arr = new ArrayList<Producto>();
        
        String query = "select p.producto as 'producto', p.estilo as 'estilo',c.descripcion as 'corrida',com.descripcion as 'combinacion',l.descripcion as 'linea',p.tipo as 'tipo',p.costo as 'costo', p.submarca as 'submarca' "
                + "from Productos p \n"
                + "join Corridas c on p.Corrida=c.Corrida \n"
                + "join Lineas l on p.linea=l.linea \n"
                + "join Combinaciones com on p.Combinacion=com.Combinacion where p.statue='A'";
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            Producto p = new Producto();
            p.setEstilo(df.getInt("estilo"));
            p.setProducto(df.getInt("producto"));
            p.setLineachar(df.getString("linea"));
            p.setTipo(df.getString("tipo"));
            p.setCorridachar(df.getString("corrida"));
            p.setCombinacionchar(df.getString("combinacion"));
            p.setCostof(df.getFloat("costo"));
            p.setMarca(df.getString("submarca"));
            arr.add(p);
        }
        df.close();
        smt.close();
        return arr;
    }

        public ArrayList<Producto> buscarcataprod(String estilo) throws ClassNotFoundException, SQLException {
        ArrayList<Producto> arr = new ArrayList<Producto>();
        
        String query = "select p.producto as 'producto', p.estilo as 'estilo',c.descripcion as 'corrida',com.descripcion as 'combinacion',l.descripcion as 'linea',p.tipo as 'tipo',p.costo as 'costo', p.submarca as 'submarca' "
                + "from Productos p \n"
                + "join Corridas c on p.Corrida=c.Corrida \n"
                + "join Lineas l on p.linea=l.linea \n"
                + "join Combinaciones com on p.Combinacion=com.Combinacion where p.statue='A' and (p.estilo like '%"+estilo+"%' OR com.descripcion like '%"+estilo+"%' OR p.submarca like '%"+estilo+"%')";
        //System.out.println("busca x cata "+query);
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            Producto p = new Producto();
            p.setEstilo(df.getInt("estilo"));
            p.setProducto(df.getInt("producto"));
            p.setLineachar(df.getString("linea"));
            p.setTipo(df.getString("tipo"));
            p.setCorridachar(df.getString("corrida"));
            p.setCombinacionchar(df.getString("combinacion"));
            p.setCostof(df.getFloat("costo"));
            p.setMarca(df.getString("submarca"));
            arr.add(p);
        }
        df.close();
        smt.close();
        return arr;
    }
    
    public String nuevoproducto(Producto p) throws ClassNotFoundException, SQLException{
        String mensaje="";
        int linea =0;
        abrirs();
        PreparedStatement st = null;
            if(getestcomb(p)!=0){
                mensaje="Ya existe estilo con esa corrida y combinaciòn";
            }else {
            if(getlinean(p)==0){
                VS_Linea vlinea = new VS_Linea();
                linea=vlinea.nuevalinea(p);
            }else linea=getlinean(p);
            
            int clave=getlastprod()+1;
            int comb=0;
            int buscacomb=getcombequal(p);
            if(buscacomb==0){
            comb=getlastcomb()+1;
            }else comb=buscacomb;
            
        try {
            abrirs();// BD de vta
            getConexions().setAutoCommit(false);
            String s="";
            if(buscacomb==0){
            s = "insert into Combinaciones values("+comb+",'"+p.getCombinacionchar()+"')";
            //System.out.println(s);
            st = getConexions().prepareStatement(s);
            st.executeUpdate();
            }
            s = "insert into Productos values("+clave+","+p.getEstilo()+","+p.getClave_corrida()+","+comb+","+linea+",'"+p.getTipo()+"',"+p.getCostof()+",'"+p.getStatus()+"',"+p.getClave_clasificacion()+",'"+p.getMarca()+"')";
            //System.out.println(s);
            st = getConexions().prepareStatement(s);
            st.executeUpdate();
            
//        } catch (Exception e) { insert into Productos values(7172,5454,94,1054,2,'CHOCLITO',132.56,'A',0,'RED TRAFFIC')
//            Logger.getLogger(VS.class.getName()).log(Level.SEVERE, null, e);
//            try {
//                getConexions().rollback();
//            } catch (Exception o) {
//                System.out.println(o.getMessage());
//            }
//            
//        }
//         try {
            abrir();// BD RCPT
            abrircpt();
            getConexion().setAutoCommit(false);
            getConexioncpt().setAutoCommit(false);
            if(buscacomb==0){
            s = "insert into Combinaciones(Combinacion,Material1,Color1) values("+comb+",1,1)";
            //System.out.println(s);
            st = getConexion().prepareStatement(s);
            st.executeUpdate();
            st.close();
            s = "insert into Combinaciones(Combinacion,Material1,Color1) values("+comb+",1,1)";
           // System.out.println(s);
            st = getConexioncpt().prepareStatement(s);
            st.executeUpdate();
            st.close();
            }
            s = "insert into Productos(Producto,Estilo,Corrida,Combinacion,Linea) values("+clave+","+p.getEstilo()+","+p.getClave_corrida()+","+comb+",99)";
            st = getConexion().prepareStatement(s);
            st.executeUpdate();
            st.close();
            s = "insert into Productos(Producto,Estilo,Corrida,Combinacion,Linea) values("+clave+","+p.getEstilo()+","+p.getClave_corrida()+","+comb+",99)";
            st = getConexioncpt().prepareStatement(s);
            st.executeUpdate();
            st.close();
            getConexion().commit();
            getConexions().commit();
            getConexioncpt().commit();
            mensaje="Estilo Completo";
        } catch (Exception e) {
            mensaje=e+"";
            Logger.getLogger(VS.class.getName()).log(Level.SEVERE, null, e);
            try {
                getConexions().rollback();
                getConexion().rollback();
                getConexioncpt().rollback();
            } catch (Exception o) {
                System.out.println(o.getMessage());
            }   
    }
            }
            //System.out.println(mensaje);
     return mensaje;
    }
    private int getlastprod() throws ClassNotFoundException, SQLException{
        int prod=0;
        String query = "select MAX(producto) as producto from Productos ";
        Statement smt;
        ResultSet df;
        abrir();
        Connection conect = getConexion();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            prod=df.getInt("producto");
        }
        //System.out.println(prod);
        df.close();
        smt.close();
        return prod;
    }
    private int getlastcomb() throws ClassNotFoundException, SQLException{
        int comb=0;
        String query = "select MAX(combinacion) as combinacion from Combinaciones";
        Statement smt;
        ResultSet df;
        abrir();
        Connection conect = getConexion();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            comb=df.getInt("combinacion");
        }
        System.out.println(comb);
        df.close();
        smt.close();
        return comb;
    }
    private int getestcomb(Producto p) throws ClassNotFoundException, SQLException{
    int comb=0;
    String query = "select c.combinacion as 'combinacion' from Combinaciones c "
            + " join Productos p on p.combinacion=c.combinacion"
            + " where  p.corrida="+p.getClave_corrida()+" and p.estilo="+p.getEstilo()+" and c.combinacion like '%"+p.getCombinacionchar()+"%'";
    System.out.println(query+"-"+comb);
    Statement smt;
    ResultSet df;
    abrir();
    Connection conect = getConexion();
    smt = conect.createStatement();
    df = smt.executeQuery(query);
    while (df.next()) {
        comb=df.getInt("combinacion");
    }
    
    df.close();
    smt.close();
    return comb;
    }
     private int getcombequal(Producto p) throws ClassNotFoundException, SQLException{
    int comb=0;
    String query = "select combinacion from Combinaciones  where descripcion like '%"+p.getCombinacionchar()+"%'";
    System.out.println(query+"-"+comb);
    Statement smt;
    ResultSet df;
    abrirs();
    Connection conect = getConexions();
    smt = conect.createStatement();
    df = smt.executeQuery(query);
    while (df.next()) {
        comb=df.getInt("combinacion");
    }
    
    df.close();
    smt.close();
    return comb;
    }
    private int getlinean(Producto p) throws ClassNotFoundException, SQLException{
    int comb=0;
    String query = "select linea from lineas"
            + " where  descripcion like '%"+p.getLineachar()+"%'";
    System.out.println(query);
    Statement smt;
    ResultSet df;
    abrirs();
    Connection conect = getConexions();
    smt = conect.createStatement();
    df = smt.executeQuery(query);
    while (df.next()) {
        comb=df.getInt("linea");
    }
    
    df.close();
    smt.close();
    return comb;
    }


            public ArrayList<Producto> buscarall_consulta() throws ClassNotFoundException, SQLException {
        ArrayList<Producto> arr = new ArrayList<Producto>();
        String query="";
        
        query = "select distinct p.submarca as 'submarca' from productos p join dpedidos dp on dp.producto = p.producto\n" +
        "group by submarca\n" +
        "order by submarca";
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            Producto p = new Producto();
            p.setMarca(df.getString("submarca"));
            arr.add(p);
        }
        df.close();
        smt.close();
        return arr;
    }
}
