/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Modelo.Corrida;
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
    public Producto buscarprodID_mod(int clave) throws ClassNotFoundException, SQLException {
        Producto p = new Producto();
        String query = "select p.corrida as 'corrida',p.estilo as 'estilo',p.Producto as 'producto',p.tipo as 'desc', "
                + "c.descripcion as 'corridac',com.descripcion as 'combinacionchar', l.descripcion as 'linea' from Productos p \n"
                + "join Corridas c on p.Corrida=c.Corrida \n"
                + "join Combinaciones com on p.Combinacion=com.Combinacion join lineas l on p.linea=l.linea \n"
                + " where producto=" + clave + "";
        //System.out.println(query);
        Statement smt;
        ResultSet df;
        Connection conect = get68();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            p.setClave_corrida(df.getInt("corrida"));
            p.setEstilo(df.getInt("estilo"));
            p.setProducto(df.getInt("producto"));
            p.setLineachar(df.getString("linea"));
            p.setTipo(df.getString("desc"));
            p.setCombinacionchar(df.getString("combinacionchar"));
        }
        df.close();
        smt.close();
        return p;
    }

    public Producto buscarprodID(int clave) throws ClassNotFoundException, SQLException {
        Producto p = new Producto();
        p.setEstilo(0);
        String query = "select p.estilo as 'estilo',com.descripcion as 'combinacion',c.Descripcion as 'corrida' from Productos p \n"
                + "join Corridas c on p.Corrida=c.Corrida\n"
                + "join Combinaciones com on p.Combinacion=com.Combinacion\n"
                + " where producto=" + clave + "";
        Statement smt;
        ResultSet df;
        Connection conect = get68();
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
        Producto p = new Producto();
        String query = "select p.corrida as 'corrida',p.estilo as 'estilo',p.Producto as 'producto',p.combinacion as 'combinacion',p.linea as 'linea',p.tipo as 'desc',p.costo as 'costo', "
                + "c.descripcion as 'corridac',com.descripcion as 'combinacionchar' from Productos p \n"
                + "join Corridas c on p.Corrida=c.Corrida \n"
                + "join Combinaciones com on p.Combinacion=com.Combinacion \n"
                + " where producto=" + clave + "";
        System.out.println("pal carrito " + query);
        Statement smt;
        ResultSet df;
        Connection conect = get68();
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
                + "join Combinaciones com on p.Combinacion=com.Combinacion where p.statue='A' order by p.estilo";
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
                + "join Combinaciones com on p.Combinacion=com.Combinacion where p.statue='A' and (p.estilo like '%" + estilo + "%' OR com.descripcion like '%" + estilo + "%' OR p.submarca like '%" + estilo + "%')";
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

    public String nuevoproducto(Producto p) throws ClassNotFoundException, SQLException {
        String mensaje = "";
        int linea = 0;
        
        PreparedStatement st = null;
        if (getestcomb(p) != 0) {
            mensaje = "Ya existe estilo con esa corrida y combinaciòn";
        } else {
            if (getlinean(p) == 0) {
                VS_Linea vlinea = new VS_Linea();
                linea = vlinea.nuevalinea(p);
            } else {
                linea = getlinean(p);
            }
            int clave = 0;
            int comb = 0;
            int buscacomb = getcombequal(p);
            if (buscacomb == 0) {
            } else {
                comb = buscacomb;
            }

            try {
                // BD de vta
                getConexions().setAutoCommit(false);
                String s = "";
                if (buscacomb == 0) {
                    s = "insert into Combinaciones values(" + comb + ",'" + p.getCombinacionchar() + "')";
                    //System.out.println(s);
                    st = get68().prepareStatement(s);
                    st.executeUpdate();
                }
                s = "insert into Productos values(" + clave + "," + p.getEstilo() + "," + p.getClave_corrida() + "," + comb + "," + linea + ",'" + p.getTipo() + "'," + p.getCostof() + ",'" + p.getStatus() + "'," + p.getClave_clasificacion() + ",'" + p.getMarca() + "')";
                //System.out.println(s);
                st = get68().prepareStatement(s);
                st.executeUpdate();
                getConexions().commit();
                mensaje = "Estilo Completo";
            } catch (Exception e) {
                mensaje = e + "";
                Logger.getLogger(VS.class.getName()).log(Level.SEVERE, null, e);
                try {
                    getConexions().rollback();
                } catch (Exception o) {
                    System.out.println(o.getMessage());
                }
            }
        }
        //System.out.println(mensaje);
        return mensaje;
    }

    private int getestcomb(Producto p) throws ClassNotFoundException, SQLException {
        int comb = 0;
        String query = "select c.combinacion as 'combinacion' from Combinaciones c "
                + " join Productos p on p.combinacion=c.combinacion"
                + " where  p.corrida=" + p.getClave_corrida() + " and p.estilo=" + p.getEstilo() + " and c.combinacion like '" + p.getCombinacionchar() + "'";
        //System.out.println(query+"-"+comb);
        Statement smt;
        ResultSet df;
        Connection conect = get68();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            comb = df.getInt("combinacion");
        }
        df.close();
        smt.close();
        return comb;
    }

    private int getcombequal(Producto p) throws ClassNotFoundException, SQLException {
        int comb = 0;
        String query = "select combinacion from Combinaciones  where descripcion like '" + p.getCombinacionchar() + "'";
//    System.out.println(query+"-"+comb);
        Statement smt;
        ResultSet df;
        Connection conect = get68();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            comb = df.getInt("combinacion");
        }
        df.close();
        smt.close();
        return comb;
    }

    private int getlinean(Producto p) throws ClassNotFoundException, SQLException {
        int comb = 0;
        String query = "select linea from lineas"
                + " where  descripcion like '" + p.getLineachar() + "'";
//    System.out.println(query);
        Statement smt;
        ResultSet df;
        Connection conect = get68();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            comb = df.getInt("linea");
        }
        df.close();
        smt.close();
        return comb;
    }

    public ArrayList<Producto> buscarall_consulta() throws ClassNotFoundException, SQLException {
        ArrayList<Producto> arr = new ArrayList<Producto>();
        String query = "";

        query = "select distinct l.descripcion as 'descripcion' from DPedidos p join Lineas l on p.linea=l.linea\n"
                + "group by descripcion";
        Statement smt;
        ResultSet df;
        Connection conect = get68();
        smt = conect.createStatement();
        df = smt.executeQuery(query);
        while (df.next()) {
            Producto p = new Producto();
            p.setMarca(df.getString("descripcion"));
            arr.add(p);
        }
        df.close();
        smt.close();
        return arr;
    }

    public String actualizarprod(Producto p) throws SQLException {
        String msj = "";
        PreparedStatement st = null;
        try {
            getConexions().setAutoCommit(false);
            String str = "update productos set estilo=" + p.getEstilo() + ", combinacion=" + p.getClave_combinacion()
                    + ", corrida=" + p.getClave_corrida() + ", linea=" + p.getClave_linea() + ", tipo='" + p.getTipo() + "', submarca='" + p.getMarca() + "' "
                    + "where producto =" + p.getProducto();
            // System.out.println(str);
            st = get68().prepareStatement(str);
            st.executeUpdate();// Actualizar numero de pedido en +1
            st.close();
            getConexions().commit();
            msj = "Modificacion completa!";
        } catch (Exception e) {
            System.out.println(e);
            msj = "Verifique sus datos!";
            getConexions().rollback();
        }
        return msj;
    }

    public ArrayList<Producto> getest(String est, String stock) {
        ArrayList<Producto> arr = new ArrayList<>();
        try {
            Connection c = get68();
            PreparedStatement st;
            ResultSet rs;
            String tabla;
            String consulta;
//            Diferenciar entre tipo de existencia
            if (stock.equals("S")) {
                consulta = "and ((e.punto1+e.punto2+e.punto3+e.punto4+e.punto5+e.punto6+e.punto7+e.punto8+e.punto9+e.punto10+e.punto11+e.punto12)>0)";
                tabla = "ExistenciasStock";
            } else {
                consulta = "";
                tabla = "ExistenciasPedidos";
            }
            String sql = "select e.producto,e.almacen,al.Descripcion as alm,estilo,m.Descripcion as mat,col.Descripcion as col,cor.descripcion as cor\n"
                    + "from "+tabla+" e\n"
                    + "join productos p on e.producto=p.producto\n"
                    + "join combinaciones c on c.Combinacion=p.Combinacion\n"
                    + "join materiales m on c.material1=m.Material\n"
                    + "join colores col on c.Color1=col.Color\n"
                    + "join corridas cor on p.corrida=cor.Corrida\n"
                    + "join almacenes al on e.Almacen=al.Almacen\n"
                    + "where p.estatus='A' and estilo=" + est + " \n"
                    + consulta+"\n"
                    + "order by estilo";
//            System.out.println(sql);
            st = c.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setProducto(rs.getInt("producto"));
                p.setAlmacen(rs.getInt("almacen"));
                p.setCombinacionchar(rs.getInt("estilo") + " " + rs.getString("mat") + " " + rs.getString("col") + " " + rs.getString("cor") + " " + rs.getString("alm"));
                arr.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

//    public Producto getxpuntoest(int prod, int alm) {
//        Producto p = new Producto();
//        ArrayList<String> punt = new ArrayList<>();
//        try {
//            Connection c = get68();
//            PreparedStatement st;
//            ResultSet rs;
//            String sql = "select p.corrida,e.producto,e.almacen,al.Descripcion as alm,estilo,m.Descripcion as mat,col.Descripcion as col,"
//                    + "cor.descripcion as cor,cor.puntoinicial,cor.puntofinal,punto1,punto2,punto3,punto4,punto5,punto6,punto7,"
//                    + "punto8,punto9,punto10,punto11,punto12,p.combinacion,p.linea,p.Costo\n"
//                    + "from ExistenciasStock e\n"
//                    + "join productos p on e.producto=p.producto\n"
//                    + "join combinaciones c on c.Combinacion=p.Combinacion\n"
//                    + "join materiales m on c.material1=m.Material\n"
//                    + "join colores col on c.Color1=col.Color\n"
//                    + "join corridas cor on p.corrida=cor.Corrida\n"
//                    + "join almacenes al on e.Almacen=al.Almacen\n"
//                    + "where p.estatus='A' and e.producto=" + prod + " and e.almacen=" + alm + " and\n"
//                    + "((e.punto1+e.punto2+e.punto3+e.punto4+e.punto5+e.punto6+e.punto7+e.punto8+e.punto9+e.punto10+e.punto11+e.punto12)>0)\n"
//                    + "order by estilo";
//            System.out.println(sql);
//            st = c.prepareStatement(sql);
//            rs = st.executeQuery();
//            while (rs.next()) {
//                Corrida cor = new Corrida();
//                cor.setCorrida(rs.getInt("corrida"));
//                cor.setDesc(rs.getString("cor"));
//                cor.setPi(rs.getInt("puntoinicial"));
//                cor.setPf(rs.getInt("puntofinal"));
//                p.setCor(cor);
//                p.setProducto(rs.getInt("producto"));
//                p.setAlmacen(rs.getInt("almacen"));
//                p.setEstilo(rs.getInt("estilo"));
//                p.setCombinacionchar(rs.getString("mat") + " " + rs.getString("col") + " " + rs.getString("cor") );
//                p.setClave_combinacion(rs.getInt("combinacion"));
//                p.setLinea(rs.getInt("linea"));
//                p.setCostof(rs.getFloat("costo"));
//                punt.add(rs.getString("punto1"));
//                punt.add(rs.getString("punto2"));
//                punt.add(rs.getString("punto3"));
//                punt.add(rs.getString("punto4"));
//                punt.add(rs.getString("punto5"));
//                punt.add(rs.getString("punto6"));
//                punt.add(rs.getString("punto7"));
//                punt.add(rs.getString("punto8"));
//                punt.add(rs.getString("punto9"));
//                punt.add(rs.getString("punto10"));
//                punt.add(rs.getString("punto11"));
//                punt.add(rs.getString("punto12"));
//                p.setArr(punt);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(VS.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return p;
//    }
//    
    public Producto getxpuntoest(int prod, int alm, String stock) {
        Producto p = new Producto();
        ArrayList<String> punt = new ArrayList<>();
        try {
            Connection c = get68();
            PreparedStatement st;
            ResultSet rs;
            String tabla;
            String consulta;
            if (stock.equals("S")) {
                consulta = "and ((e.punto1+e.punto2+e.punto3+e.punto4+e.punto5+e.punto6+e.punto7+e.punto8+e.punto9+e.punto10+e.punto11+e.punto12)>0)";
                tabla = "ExistenciasStock";
            } else {
                consulta = "";
                tabla = "ExistenciasPedidos";
            }
            String sql = "select p.corrida,e.producto,e.almacen,al.Descripcion as alm,estilo,m.Descripcion as mat,col.Descripcion as col,"
                    + "cor.descripcion as cor,cor.puntoinicial,cor.puntofinal,punto1,punto2,punto3,punto4,punto5,punto6,punto7,"
                    + "punto8,punto9,punto10,punto11,punto12,p.combinacion,p.linea,p.Costo\n"
                    + "from " + tabla + " e\n"
                    + "join productos p on e.producto=p.producto\n"
                    + "join combinaciones c on c.Combinacion=p.Combinacion\n"
                    + "join materiales m on c.material1=m.Material\n"
                    + "join colores col on c.Color1=col.Color\n"
                    + "join corridas cor on p.corrida=cor.Corrida\n"
                    + "join almacenes al on e.Almacen=al.Almacen\n"
                    + "where p.estatus='A' and e.producto=" + prod + " and e.almacen=" + alm + " \n"
                    + consulta + "\n"
                    + "order by estilo";
//            System.out.println(sql);
            st = c.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Corrida cor = new Corrida();
                cor.setCorrida(rs.getInt("corrida"));
                cor.setDesc(rs.getString("cor"));
                cor.setPi(rs.getInt("puntoinicial"));
                cor.setPf(rs.getInt("puntofinal"));
                p.setCor(cor);
                p.setProducto(rs.getInt("producto"));
                p.setAlmacen(rs.getInt("almacen"));
                p.setEstilo(rs.getInt("estilo"));
                p.setCombinacionchar(rs.getString("mat") + " " + rs.getString("col") + " " + rs.getString("cor"));
                p.setClave_combinacion(rs.getInt("combinacion"));
                p.setLinea(rs.getInt("linea"));
                p.setCostof(rs.getFloat("costo"));
                punt.add(rs.getString("punto1"));
                punt.add(rs.getString("punto2"));
                punt.add(rs.getString("punto3"));
                punt.add(rs.getString("punto4"));
                punt.add(rs.getString("punto5"));
                punt.add(rs.getString("punto6"));
                punt.add(rs.getString("punto7"));
                punt.add(rs.getString("punto8"));
                punt.add(rs.getString("punto9"));
                punt.add(rs.getString("punto10"));
                punt.add(rs.getString("punto11"));
                punt.add(rs.getString("punto12"));
                p.setArr(punt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
}
