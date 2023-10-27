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
    public int getlast() {
        int last_ped = 0;
        try {
            // ultimo pedido desde rcpt
            String query = "select max(convert(integer,substring(pedido,0,len(pedido)-1))) as ped\n"
                    + "                    from pedidos\n"
                    + "                    where marca=' V'";
            //System.out.println(query);
            PreparedStatement smt;
            ResultSet df;
            Connection conect = get68();
            smt = conect.prepareStatement(query);
            df = smt.executeQuery();
            while (df.next()) {
                last_ped = df.getInt("ped");
            }
            df.close();
            smt.close();
        } catch (SQLException ex) {
            last_ped = 0;
            Logger.getLogger(VS_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return last_ped + 1;
    }

    public String nuevopedido(Pedido p, ArrayList<String> dis, ArrayList<Producto> cor, ArrayList<Corrida> corridas) throws ClassNotFoundException, SQLException {
        PreparedStatement st;
        try {
            get68().setAutoCommit(false);
            get68cpt().setAutoCommit(false);
            String pedido = p.getPed();
            String fecha = p.getFechapedido();
            String fechav = p.getFechaentrega();
            int cliente = p.getC().getNumcliente();
            int cvemb = p.getC().getEmbaque();
            int agente = p.getC().getAgente();
            int plazo = p.getC().getPlazo();
            float total = p.getTotal();
            int tpares = p.getTotalpares();
            String obs = p.getObs();
            String serie = p.getSerie();
            String user = p.getUsuario();
            String s = "insert into pedidos(pedido,fechapedido,fechacaptura,fecharecibido,fechaentrega,numcliente,"
                    + "cveagente,cveembarque,tipo,plazo,descuento,importe,totalpares,estatus,fechaestatus,paquete,"
                    + "observaciones, prioridad, estatusfincado, serie, usuario, registro,usuariom,fecham,marca) "
                    + "values('" + pedido + "','" + fecha + "','" + fecha + "','" + fecha + "','" + fechav + "'," + cliente + "," + agente + ","
                    + cvemb + ",'S'," + plazo + ",0," + total + "," + tpares + ",1,'" + fecha + "',0,'" + obs + "',0,'F','" + serie + "','" + user + "','" + fecha + "','" + user + "','" + fecha + "',' V')";
//            System.out.println("pedidos " + s);
            st = get68().prepareStatement(s);
            st.executeUpdate();// insercion de nuevo pedido en la bd
            if (serie.equals("A")) {
//                System.out.println("pedido a");
                st = get68cpt().prepareStatement(s);
                st.executeUpdate();// insercion de nuevo pedido en la bd CPT
            }
            // Insercion en DPedidos
            for (int i = 0; i < p.getArrdp().size(); i++) {
                int prod = p.getArrdp().get(i).getProducto();
                int ren = p.getArrdp().get(i).getRenglon();
                int est = p.getArrdp().get(i).getEstilo();
                int corrida = p.getArrdp().get(i).getCorrida();
                int comb = p.getArrdp().get(i).getCombinacion();
                int linea = p.getArrdp().get(i).getLinea();
                int alm = p.getArrdp().get(i).getAlmacen();
                int c1 = p.getArrdp().get(i).getS1();
                int c2 = p.getArrdp().get(i).getS2();
                int c3 = p.getArrdp().get(i).getS3();
                int c4 = p.getArrdp().get(i).getS4();
                int c5 = p.getArrdp().get(i).getS5();
                int c6 = p.getArrdp().get(i).getS6();
                int c7 = p.getArrdp().get(i).getS7();
                int c8 = p.getArrdp().get(i).getS8();
                int c9 = p.getArrdp().get(i).getS9();
                int c10 = p.getArrdp().get(i).getS10();
                int c11 = p.getArrdp().get(i).getS11();
                int c12 = p.getArrdp().get(i).getS12();
                float precio = p.getArrdp().get(i).getPreciov();
                float costo = p.getArrdp().get(i).getCosto();
                int cant = p.getArrdp().get(i).getCantxren();
                String stock=p.getArrdp().get(i).getStock();
                String ob = est + ", " + comb + ", " + corrida + "- alm" + alm;
                s = "insert into DPedidos "
                        + "values('" + pedido + "','" + fecha + "'," + ren + "," + prod + "," + est + ",''," + corrida + "," + comb + "," + linea + ""
                        + "," + c1 + "," + c2 + "," + c3 + "," + c4 + "," + c5 + "," + c6 + "," + c7 + "," + c8 + "," + c9 + "," + c10 + "," + c11 + "," + c12 + ",0,0"// cantidad capturada en la distribucion
                        + ",0,0,0,0,0,0,0,0,0,0,0,0,0,0"//surtido 14
                        + ",0,0,0,0,0,0,0,0,0,0,0,0,0,0"//stock 14
                        + ",0,0,0,0,0,0,0,0,0,0,0,0,0,0"//SA 14
                        + ",0,0,0,0,0,0,0,0,0,0,0,0,0,0"//Programa 14
                        + ",0,0,0,0,0,0,0,0,0,0,0,0,0,0"//Entrada 14
                        + ",0,0,0,0,0,0,0,0,0,0,0,0,0,0"//factS 14
                        + "," + cant + ",0,0,0,0,0," + costo + "," + precio + ",'"+stock+"',0,NULL,1,'" + fecha + "','F',NULL,NULL,NULL,NULL,'" + ob + "','" + user + "',NULL)";
//                System.out.println("dpedido " + s);
                st = get68().prepareStatement(s);
                st.executeUpdate();
                if (serie.equals("A")) {
//                    System.out.println("dpedidoa");
                    st = get68cpt().prepareStatement(s);
                    st.executeUpdate();
                }
            }
            get68().commit();
            get68cpt().commit();
            return pedido;
        } catch (Exception e) {
            Logger.getLogger(VS.class.getName()).log(Level.SEVERE, null, e);
            try {
                get68().rollback();
                get68cpt().rollback();
            } catch (Exception o) {
                System.out.println(o.getMessage());
            }
            return "";
        }
    }

    public ArrayList<Pedido> getpeds(String f1, String f2, String b, String s) throws ClassNotFoundException, SQLException {
        ArrayList<Pedido> arr = new ArrayList<>();

        String query = "select pedido,convert(date,Fechapedido) as fc,convert(date,FechaEntrega) as fe,\n"
                + "TotalPares,Importe,serie,nombre,isnull((select folio from kardex where npedido=pedido),'') as folio\n"
                + "from pedidos p\n"
                + "join ACobranza.dbo.Clientes c on p.NumCliente=c.NumCliente\n"
                + "where convert(date,fechapedido) between '" + f1 + "' and '" + f2 + "' and marca=' V' and usuario='" + s + "' and nombre like '%" + b + "%'\n"
                + "order by fechacaptura desc";
//        System.out.println("Ver pedidos "+query);
        Statement smt;
        ResultSet df;
        Connection conect = get68();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            Pedido p = new Pedido();
            p.setPed(df.getString("pedido"));
            p.setFechapedido(df.getString("fc"));
            p.setFechaentrega(df.getString("fe"));
            p.setNombrecliente(df.getString("nombre"));
            p.setTotalpares(df.getInt("totalpares"));
            p.setImporte(df.getFloat("importe"));
            p.setSerie(df.getString("serie"));
            p.setFoliokardex(df.getInt("folio"));
            arr.add(p);
        }
        df.close();
        smt.close();
        return arr;
    }

    public int buscarall_consulta(String tipo) throws ClassNotFoundException, SQLException {// ultimo pedido desde empresa
        String query = "select MAX(pedido) as 'pedido' from empresa";
        int last_ped = 0;
        Statement smt;
        ResultSet df;
        Connection conect = getConexions();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            last_ped = df.getInt("submarca");
        }
        df.close();
        smt.close();
        return last_ped + 1;
    }

    public ArrayList<Pedido> buscarcliente_consultas() throws ClassNotFoundException, SQLException {
        ArrayList<Pedido> arr = new ArrayList<Pedido>();
        String query = "";
        query = "select distinct nombrecliente from Pedidos\n"
                + " group by nombrecliente order by nombrecliente";
//        System.out.println(query);
        Statement smt;
        ResultSet df;
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

    public void modstatus(String s, int clave) throws ClassNotFoundException, SQLException {
        PreparedStatement st = null;
        try {
            getConexions().setAutoCommit(false);
            String str = "update pedidos set statue='" + s + "' where clave_pedido=" + clave;
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
