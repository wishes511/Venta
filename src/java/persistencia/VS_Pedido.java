/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;


import Modelo.Corrida;
import Modelo.Pedido;
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
public class VS_Pedido extends conBD {


    // Busquedas--------------
        public int getlast() throws ClassNotFoundException, SQLException {// ultimo pedido desde empresa
        String query = "select MAX(pedido) as 'pedido' from empresa";
        int last_ped=0;
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect=getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            last_ped=df.getInt("pedido");
        }
        df.close();
        smt.close();
        return last_ped+1;
    }
        public int nuevopedido(Pedido p,ArrayList<String> dis,ArrayList<Producto> cor, ArrayList<Corrida> corridas) throws ClassNotFoundException, SQLException {
        int clave_ped=0;
        PreparedStatement st = null;
        try{
            abrirs();
        getConexions().setAutoCommit(false);
            String s = "insert into Pedidos values("+p.getPedido()+",'"+p.getFechapedido()+"','"
                    +p.getFechaentrega()+"','"+p.getNombrecliente()+"','"+p.getRfc()+"','"
                    +p.getDireccion()+"','"+p.getTelefono()+"','"+p.getEmail()+"',"+p.getTotalpares()+","
                    +p.getImporte()+","+p.getTotal()+","+p.getIva()+",'"+p.getStatus()+"','"+p.getUsuario()+"')";
            System.out.println(s);
            st = getConexions().prepareStatement(s);
            st.executeUpdate();// insercion de nuevo pedido en la bd
            st.close();
        String query = "select max(clave_pedido) as 'clave_pedido' from Pedidos"; // recuperar ultimo pedido realizado
        System.out.println(query);
        Statement smt;
        ResultSet df;
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            clave_ped=df.getInt("clave_pedido");
        }
        df.close();
        smt.close();
        // Insercion en DPedidos
        s="";
        String subs="";
        String str="";
        int cont=0;
        int cant=1;
        int cantxprod=0;
        boolean flag=true;
        for(int i =0;i<corridas.size();i++){
            float pi=corridas.get(i).getPi();
            float pf= corridas.get(i).getPf()+1;
            while(pi<pf){
//                if(Integer.parseInt(dis.get(cont))!=0){
                    if(flag){
                    s=""+dis.get(cont);
                    subs="cant"+cant;
                    flag=false;
                    }else{
                    s+=","+dis.get(cont);
                    subs+=",cant"+cant;
                    }
                    cantxprod+=Integer.parseInt(dis.get(cont));
//                }
                    cont++;
                    cant++;
                    pi+=0.5;
            }
                str = "insert into DPedidos(Clave_pedido,Pedido,producto,estilo,corrida,combinacion,linea,"+subs+",totalprod,costo) "
                  + "values ("+clave_ped+","+p.getPedido()+","+cor.get(i).getProducto()+","+cor.get(i).getEstilo()+","
                  +cor.get(i).getClave_corrida()+","+cor.get(i).getClave_combinacion()+","+cor.get(i).getClave_linea()+","
                  +s+","+cantxprod+","+cor.get(i).getCostof()+")";
                System.out.println(str);
                st = getConexions().prepareStatement(str);
                st.executeUpdate();// insercion de nuevo pedido en la bd
                st.close();
                flag=true;
                cant=1;
                cantxprod=0;
                str ="update empresa set pedido=pedido+1";
                st = getConexions().prepareStatement(str);
                st.executeUpdate();// Actualizar numero de pedido en +1
                st.close();       
        }
        getConexions().commit();
        } catch (Exception e) {
            Logger.getLogger(VS.class.getName()).log(Level.SEVERE, null, e);
            try {
                getConexions().rollback();
            } catch (Exception o) {
                System.out.println(o.getMessage());
            }   
    }
        return clave_ped;
    }
        public ArrayList<Pedido> getpeds(String f1, String f2,String b,String s) throws ClassNotFoundException, SQLException{
        ArrayList<Pedido> arr = new ArrayList<Pedido>();
        
        String query = "select distinct p.pedido as 'pedido',convert(date,fechapedido) as 'fecha',convert(date,fechaentrega) as 'fechae',p.clave_pedido as 'clave',nombrecliente,telefono,totalpares,importe,iva,total\n" +
"from pedidos p join DPedidos dp on dp.clave_pedido=p.clave_pedido "
 + "join Productos prod on dp.producto=prod.producto join Combinaciones c on dp.combinacion = c.combinacion\n" +
"where p.statue='"+s+"' and (dp.estilo like '%"+b+"%'or c.descripcion like '%"+b+"%' or  p.pedido like '%"+b+"%' or p.nombrecliente like '%"+b+"%') and convert(date,fechapedido) between '"+f1+"' and '"+f2+"'\n" +
" order by pedido DESC";
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect=getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            Pedido p = new Pedido();
            p.setClavepedido(df.getInt("clave"));
            p.setPedido(df.getInt("pedido"));
            p.setFechapedido(df.getString("fecha"));
            p.setFechaentrega(df.getString("fechae")); 
            p.setNombrecliente(df.getString("nombrecliente"));
            p.setTelefono(df.getString("telefono"));
            p.setTotalpares(df.getInt("totalpares"));
            p.setImporte(df.getFloat("importe"));
            p.setIva(df.getFloat("iva"));
            p.setTotal(df.getFloat("total"));
            arr.add(p);
        }
        df.close();
        smt.close();
        return arr;
        }
        public int buscarall_consulta(String tipo) throws ClassNotFoundException, SQLException {// ultimo pedido desde empresa
        String query = "select MAX(pedido) as 'pedido' from empresa";
        int last_ped=0;
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect=getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            last_ped=df.getInt("submarca");
        }
        df.close();
        smt.close();
        return last_ped+1;
    }
                public ArrayList<Pedido> buscarcliente_consultas() throws ClassNotFoundException, SQLException {
        ArrayList<Pedido> arr = new ArrayList<Pedido>();
        String query="";
        query = "select distinct nombrecliente from Pedidos\n" +
" group by nombrecliente order by nombrecliente";
        System.out.println(query);
        Statement smt;
        ResultSet df;
        abrirs();
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            Pedido l = new Pedido();
            l.setNombrecliente(df.getString("nombrecliente"));
            arr.add(l);
        }
        df.close();
        smt.close();
        return arr;
    }
                public void modstatus(String s, int clave) throws ClassNotFoundException, SQLException{
                    PreparedStatement st = null;
                  try{
                    abrirs();
        getConexions().setAutoCommit(false);
                   String str ="update pedidos set statue='"+s+"' where clave_pedido="+clave;
                   //System.out.println(str);
                st = getConexions().prepareStatement(str);
                st.executeUpdate();// Actualizar numero de pedido en +1
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
                }
}
