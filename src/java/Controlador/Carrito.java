/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DAO_Cliente;
import DAO.DAO_Corrida;
import DAO.DAO_Pedido;
import DAO.DAO_Producto;
import Modelo.Cliente;
import Modelo.Corrida;
import Modelo.DPedido;
import Modelo.Pedido;
import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.VS_Cliente;
import persistencia.VS_Pedido;
import persistencia.conBD;
import persistencia.mail;

/**
 *
 * @author gateway1
 */
@WebServlet(name = "Carrito", urlPatterns = {"/Carrito"})
public class Carrito extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    private ArrayList<String> getdis(String dato, ArrayList<String> arr) {
        //System.out.println("getdis " + dato);
        String patt = "[0-9\\,\\<br>]*";
        Pattern pat = Pattern.compile(patt);
        Matcher match = pat.matcher(dato);
        if (match.matches()) {// verifica si es algo de acuerdo al patron
            String aux = "";
            for (int i = 0; i < dato.length(); i++) { // for por el tamaño de la cadena
                try {
                    int var = dato.charAt(i); // tomamos el digito de la celda en i
                    if (var >= 46 && var <= 57) {// comparamos si es numero o punto
                        aux += (char) var + ""; // asignar a variable
                    } else {// si no es algo referente a numeros
                        if (var >= 60 && var <= 63 || var == 98 || var == 114) {
                            //verifica si es algo con <br>
                        } else {// sino la variable aux se almacenara en el arreglo
                            if (var == 108) {
                                aux += 0 + "";
                            }
                            arr.add(aux);
                            aux = "";
                        }
                    }
                } catch (Exception e) {
                    // si hay algo raro con la distribucion acabar el ciclo y vaciar arreglo
                    i = dato.length();
                    arr.clear();
                    break;
                }
            }
            if (arr.size() < 12) {
                for (int i = arr.size(); i < 12; i++) {
                    arr.add("0");
                }
            }
        } else {// si no cumple el patron vaciar lista
            arr.clear();
        }
        return arr;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        HttpSession objSesion = request.getSession(false);
        String usuario = (String) objSesion.getAttribute("usuario");
        String tipos = (String) objSesion.getAttribute("tipo");
        ArrayList<String> dis = (ArrayList<String>) objSesion.getAttribute("distribucion");
        ArrayList<Producto> cor = (ArrayList<Producto>) objSesion.getAttribute("producto");
        ArrayList<Corrida> corrida = (ArrayList<Corrida>) objSesion.getAttribute("corrida");
        int agente = (int) objSesion.getAttribute("agente");
        if (usuario != null && tipos != null && (tipos.equals("ADMIN") || tipos.equals("VENTAS") || tipos.equals("USUARIO") || tipos.equals("ALTAS"))) {
            if (tipos.equals("ALTAS")) {
                response.sendRedirect("usuario/productos.jsp");
            }
        } else {
            response.sendRedirect("../index.jsp");
        }
        PrintWriter out = response.getWriter();
        String uso = (String) request.getParameter("uso");
        if (uso.equals("anadir")) {
            String dato = (String) request.getParameter("p");
            String prod = (String) request.getParameter("prod");
            String precio = (String) request.getParameter("precio");
            String stock = (String) request.getParameter("stock");
            DAO_Producto dprod = new DAO_Producto();
            DAO_Corrida dcor = new DAO_Corrida();
            int producto = formatmesprod(prod, "prod");
            int alm = formatmesprod(prod, "alm");
            if (cor.isEmpty()) {
                ArrayList<String> distcar = new ArrayList<String>();
                distcar = getdis(dato, dis);

                if (!distcar.isEmpty()) {
                    dis.clear();
                    dis = distcar;
//                    Obtiene el objeto producto para darle valor con precio y serie o algun otro valor mas
                    Producto pr = dprod.getprodwithpuntos(producto, alm, stock);
                    pr.setPrecio(Float.parseFloat(precio));
                    //pr.setSerie(serie);
                    pr.setStock(stock);
                    cor.add(pr);
                    corrida.add(dcor.getcorridawithID(producto));
                    objSesion.setAttribute("distribucion", getdis(dato, dis));
                    objSesion.setAttribute("producto", cor);
                    objSesion.setAttribute("corrida", corrida);
                    out.print(cor.size());
                } else {
                    out.print("<script>alert('Error al procesar datos de distribucion Intentelo de nuevo');</script>");
                }
            } else {// si la distribucion ya tiene pedidos
                ArrayList<String> distcar = new ArrayList<String>();
                distcar = getdis(dato, distcar);
                if (!distcar.isEmpty()) {// si la distribucion no es vacia
                    int cont = 0;
                    boolean flag = true;
                    boolean flagtemp = false;
                    for (int i = 0; i < cor.size(); i++) {
                        // System.out.println(prod+" l "+cor.get(i).getProducto()+" i "+i);
                        float pi = corrida.get(i).getPi();
                        float pf = corrida.get(i).getPf() + 1;
                        if (producto == cor.get(i).getProducto()) {
                            int contint = 0;
                            while (pi < pf) {
                                // System.out.println(dis.get(cont)+" + "+distcar.get(contint) +"- "+pi+"-"+pf);
                                dis.set(cont, (Integer.parseInt(dis.get(cont)) + Integer.parseInt(distcar.get(contint))) + "");
                                cont++;
                                pi += 0.5;
                                contint++;
                            }
                            flagtemp = true;
                            flag = true;
                            i = cor.size();
                        } else {
                            while (pi < pf) {
                                cont++;
                                pi += 0.5;
                            }
                            if (!flagtemp) {
                                flag = false;
                            } else {
                                i = cor.size();
                            }
                        }
                    }
                    if (flag) {
                        objSesion.setAttribute("distribucion", dis);
                    } else {
                        Producto pr = dprod.getprodwithpuntos(producto, alm, stock);
                        pr.setPrecio(Float.parseFloat(precio));
                        //pr.setSerie(serie);
                        pr.setStock(stock);
                        cor.add(pr);
                        corrida.add(dcor.getcorridawithID(producto));
                        objSesion.setAttribute("distribucion", getdis(dato, dis));
                        objSesion.setAttribute("producto", cor);
                        objSesion.setAttribute("corrida", corrida);
                        out.print(cor.size());
                    }
                } else {
                    out.print("<script>alert('Error al procesar datos de distribucion Intentelo de nuevo');</script>");
                }
            }
        } else if (uso.equals("vaciar")) {
            dis.clear();
            cor.clear();
            corrida.clear();
            objSesion.setAttribute("distribucion", dis);
            objSesion.setAttribute("producto", cor);
            objSesion.setAttribute("corrida", corrida);
        } else if (uso.equals("deleterow")) {
            ArrayList<String> arraux = new ArrayList<>();
            String producto = (String) request.getParameter("prod");
            int tamano = corrida.size();
            int cont = 0;
            int indice = 0;
            for (int i = 0; i < tamano; i++) {
                double pi = corrida.get(i).getPi();
                double pf = corrida.get(i).getPf() + 1;
                if (cor.get(i).getProducto() != Integer.parseInt(producto)) {// comparar idproducto con el parametro del campo recibido
                    while (pi < pf) {// ciclo para almacenar la distribucion
//                        System.out.println("cont:" + cont + " " + dis.get(cont));
                        arraux.add(dis.get(cont));
                        pi += 0.5;
                        cont++;
                    }
                } else { // para saber en que parte del producto se quedo y eliminar de laas otras estructuras
                    while (pi < pf) {
                        pi += 0.5;
                        cont++;
                    }
                    indice = i;
                }
            }
            if (corrida.isEmpty()) {
                dis.clear();// limpiar todas las estructuras
                cor.clear();
                corrida.clear();
                objSesion.setAttribute("distribucion", dis);
                objSesion.setAttribute("producto", cor);
                objSesion.setAttribute("corrida", corrida);
            } else {
                dis.clear();// limpiar 
                cor.remove(indice); //borrar del indice el registro del producto
                corrida.remove(indice);
                dis = arraux;// actualizar estructura con el producto fuera
                objSesion.setAttribute("producto", cor);
                objSesion.setAttribute("corrida", corrida);
                objSesion.setAttribute("distribucion", dis);
            }
        } else if (uso.equals("nuevopedido")) {
            //esperar();
            if (!cor.isEmpty()) {
                DecimalFormat formateador = new DecimalFormat("####.##");
                String cli = (String) request.getParameter("cliente");
                String obs = (String) request.getParameter("obs");
                String serie = (String) request.getParameter("serie");
//                System.out.println("vars " + cli + " " + obs + " " + serie);
                int cont = 0;
                int totalpares = 0;
                float totalprecio = 0;
                int totalparesaux = 0;
                ArrayList<DPedido> arrdp = new ArrayList<>();
                for (int i = 0; i < corrida.size(); i++) {
                    int cantidad = 0;
                    int aux = 0;
//                Asignar valor al array del detalle de venta
                    DPedido dp = new DPedido();
                    dp.setProducto(cor.get(i).getProducto());
                    dp.setCorrida(cor.get(i).getCor().getCorrida());
                    dp.setPreciov(cor.get(i).getPrecio());
                    dp.setCombinacion(cor.get(i).getClave_combinacion());
                    dp.setEstilo(cor.get(i).getEstilo());
                    dp.setLinea(cor.get(i).getLinea());
                    dp.setCosto(cor.get(i).getCostof());
                    dp.setAlmacen(cor.get(i).getAlmacen());
                    dp.setStock(cor.get(i).getStock());
                    dp.setRenglon(i + 1);
//                Acomodo de puntos y cantidades de la distribucion
                    dp.setS1(Integer.parseInt(dis.get(cont)));
                    dp.setS2(Integer.parseInt(dis.get(cont + 1)));
                    dp.setS3(Integer.parseInt(dis.get(cont + 2)));
                    dp.setS4(Integer.parseInt(dis.get(cont + 3)));
                    dp.setS5(Integer.parseInt(dis.get(cont + 4)));
                    dp.setS6(Integer.parseInt(dis.get(cont + 5)));
                    dp.setS7(Integer.parseInt(dis.get(cont + 6)));
                    dp.setS8(Integer.parseInt(dis.get(cont + 7)));
                    dp.setS9(Integer.parseInt(dis.get(cont + 8)));
                    dp.setS10(Integer.parseInt(dis.get(cont + 9)));
                    dp.setS11(Integer.parseInt(dis.get(cont + 10)));
                    dp.setS12(Integer.parseInt(dis.get(cont + 11)));
                    float pi = corrida.get(i).getPi();
                    float pf = corrida.get(i).getPf() + 1;
                    //while (pi < pf) {
                    while (aux < 12) {
                        totalpares += Integer.parseInt(dis.get(cont));
                        cantidad += Integer.parseInt(dis.get(cont));
                        pi += 0.5;
                        cont++;
                        aux++;
                    }
                    dp.setCantxren(cantidad);
                    dp.setTcant(totalpares);
                    totalprecio += Float.parseFloat(formateador.format(cor.get(i).getPrecio() * totalpares));
                    totalparesaux += totalpares;
                    totalpares = 0;
                    arrdp.add(dp);
                }
                java.util.Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                conBD cbd = new conBD();
//            Si algun momento se llega a presentar cambiar si es B la conexion a la interna
                Connection cn = (serie.equals("A")) ? cbd.get68cob() : cbd.get68cob();
                DAO_Cliente dcli = new DAO_Cliente();
                Pedido p = new Pedido();
                DAO_Pedido dp = new DAO_Pedido();
//            Obtiene nuevo pedido para insertar en la bd
                p.setPed(dp.max_pedemp() + " V");
//            Se obtiene y asigna datos referente al cliente
                p.setC(dcli.getcliente(cn, Integer.parseInt(cli), sdf.format(date)));
                p.setFechaentrega(formatfecha(p.getC().getFechav()));
                p.setFechapedido(sdf.format(date));
                p.setUsuario(usuario.toUpperCase());
                p.setTotalpares(totalparesaux);
                p.setImporte(0);
                p.setIva(0);
                p.setTotal(totalprecio);
                p.setSerie(serie);
                p.setObs(obs);
                p.setArrdp(arrdp);
                System.out.println("total precio " + totalprecio);
                System.out.println(p.getC().getFechav());
                System.out.println("cont " + cont);
                if (!dp.nuevoped(p, dis, cor, corrida).equals("")) {
                    dis.clear();
                    cor.clear();
                    corrida.clear();
//                Creacion y exportacion de pdf
                    Crearpdf pdf = new Crearpdf();
                    pdf.createpdf(p.getPed(), cbd.get68());
//                Generar y mandar email con el archivo adjunto
                    String cuerpo = "Hola buenas tardes, \n Se le hace el envio del documento del pedido recien realizado con el numero " + p.getPed()+"\n "
                            + " Favor de no contestar ya que es un elemento auto gestionado.";
                    mail email = new mail(cuerpo, "Pedido Athletic Footwear", p.getC(), p.getPed());
                    email.sendmail();
                    pdf.deletepdf(p.getPed());
                    out.print(p.getPed());
                }

            }

        } else if (uso.equals("fechas")) {
            ArrayList<Pedido> arr = new ArrayList<>();
            String f1 = (String) request.getParameter("f1");
            String f2 = (String) request.getParameter("f2");
            String b = (String) request.getParameter("busqueda").toUpperCase();
            //String status = (String) request.getParameter("status").toUpperCase();
            DAO_Pedido ped = new DAO_Pedido();
            arr = ped.getall(f1, f2, b, usuario);
            String modo = "alta"; // se utiliza para distinguir que tipo de imagen y funcion utilizar
            String imagen = "up";
//            if (status.equals("A")) {
//                modo = "baja";
//                imagen = "down";
//            }
            for (int i = 0; i < arr.size(); i++) { // despliegue informacion en filas
                out.print("<tr>");
                out.print("<td>" + arr.get(i).getPed() + "</td>");
                out.print("<td>" + arr.get(i).getFechapedido() + "</td>");
                out.print("<td>" + arr.get(i).getFechaentrega() + "</td>");
                out.print("<td>" + arr.get(i).getNombrecliente() + "</td>");
                out.print("<td>" + arr.get(i).getTotalpares() + "</td>");
                out.print("<td>" + arr.get(i).getImporte() + "</td>");
                out.print("<td>" + arr.get(i).getSerie() + "</td>");
                out.print("<td>" + arr.get(i).getFoliokardex() + "</td>");
                // out.print("<td><img onclick=" + modo + "(" + arr.get(i).getClavepedido() + ") class=\"imagentabla\" src=\"../images/" + imagen + ".png\" ></td>");
                out.print("</tr>");
            }
        } else if (uso.equals("baja")) {
            String clave = (String) request.getParameter("clave");
            DAO_Pedido p = new DAO_Pedido();
            p.baja(clave);
        } else if (uso.equals("alta")) {
            String clave = (String) request.getParameter("clave");
            DAO_Pedido p = new DAO_Pedido();
            p.alta(clave);
        } else if (uso.equals("modificar")) {
            ArrayList<String> lista = new ArrayList<String>();
            String dato = (String) request.getParameter("p");
            String prod = (String) request.getParameter("prod");
            lista = getdis(dato, dis);
            for (int i = 0; i <= lista.size(); i++) {
                System.out.println(lista.get(i));
            }
        } else if (uso.equals("clientes")) {
            ArrayList<Cliente> arr;
            conBD cbd = new conBD();
            String serie = (String) request.getParameter("serie");
            Connection cn = (serie.equals("A")) ? cbd.get68cob() : cbd.get68cob();
            DAO_Cliente cl = new DAO_Cliente();
            arr = cl.getallclientes(cn, agente);
            System.out.println(arr.size());
            out.print("<select class=\"form-control\" id=\"clin\" name=\"clin\" >");
            for (Cliente arr1 : arr) {
                //System.out.println(arr1.getNombre());
                out.print("<option value=" + arr1.getNumcliente() + ">" + arr1.getNombre() + "</option>");
            }
            out.print("</select>");

        }

    }

    private String formatfecha(String f) {
        String fecha = "";
        for (int i = 0; i < 19; i++) {
            String car = f.charAt(i) + "";
            if (car.equals(" ")) {
                fecha += "T";
            } else {
                fecha += car;
            }
        }
        return fecha;
    }

    /**
     * string, prod o alm
     *
     * @param var
     * @param tipo
     * @return
     */
    private int formatmesprod(String var, String tipo) {
        int resp;
        String r = "";
        if (tipo.equals("prod")) {
            for (int i = 0; i < var.length(); i++) {
                String v = var.charAt(i) + "";
                if (v.equals(",")) {
                    i = var.length();
                } else {
                    r += v;
                }
            }
        } else {
            boolean band = false;
            for (int i = 0; i < var.length(); i++) {
                String v = var.charAt(i) + "";
                if (v.equals(",")) {
                    band = true;
                } else {
                    if (band) {
                        r += v;
                    }
                }

            }
        }
        resp = Integer.parseInt(r);
        return resp;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void esperar() {// metodo para evitar
        Random rnd = new Random();
        int seg = (int) (rnd.nextDouble() * 5 + 1);
        System.out.println(seg);
        try {
            Thread.sleep(seg * 1000);

        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}
