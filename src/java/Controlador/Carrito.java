/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DAO_Corrida;
import DAO.DAO_Pedido;
import DAO.DAO_Producto;
import Modelo.Corrida;
import Modelo.Pedido;
import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.VS_Pedido;

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
        if (match.matches()) {
            String aux = "";
            for (int i = 0; i < dato.length(); i++) {
                try {
                    int var = dato.charAt(i);
                    if (var >= 46 && var <= 57) {
                        aux += (char) var + "";
                    } else {
                        if (var >= 60 && var <= 63 || var == 98 || var == 114) {
                        } else {
                            if (var == 108) {
                                aux += 0 + "";
                            }
                            arr.add(aux);
                            aux = "";
                        }
                    }
                } catch (Exception e) {
                    i = dato.length();
                    arr.clear();
                    break;
                }
            }
        } else {
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
            DAO_Producto dprod = new DAO_Producto();
            DAO_Corrida dcor = new DAO_Corrida();
            if (cor.isEmpty()) {
                ArrayList<String> distcar = new ArrayList<String>();
                distcar = getdis(dato, dis);
                if (!distcar.isEmpty()) {
                    cor.add(dprod.getprodwithID_nochar(Integer.parseInt(prod)));
                    corrida.add(dcor.getcorridawithID(Integer.parseInt(prod)));
                    objSesion.setAttribute("distribucion", getdis(dato, dis));
                    objSesion.setAttribute("producto", cor);
                    objSesion.setAttribute("corrida", corrida);
                    out.print(cor.size());
                } else {
                    out.print("<script>alert('Error al procesar datos de distribucion Intentelo de nuevo');</script>");
                }
            } else {
                ArrayList<String> distcar = new ArrayList<String>();
                distcar = getdis(dato, distcar);
                if (!distcar.isEmpty()) {
                     int cont = 0;
                boolean flag = true;
                boolean flagtemp = false;
                for (int i = 0; i < cor.size(); i++) {
                    // System.out.println(prod+" l "+cor.get(i).getProducto()+" i "+i);
                    float pi = corrida.get(i).getPi();
                    float pf = corrida.get(i).getPf() + 1;
                    if (Integer.parseInt(prod) == cor.get(i).getProducto()) {
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
                // System.out.println(flag);
                if (flag) {
                    objSesion.setAttribute("distribucion", dis);
                } else {
                    cor.add(dprod.getprodwithID_nochar(Integer.parseInt(prod)));
                    corrida.add(dcor.getcorridawithID(Integer.parseInt(prod)));
                    objSesion.setAttribute("distribucion", getdis(dato, dis));
                    objSesion.setAttribute("producto", cor);
                    objSesion.setAttribute("corrida", corrida);
                    out.print(cor.size());
                }
                }else out.print("<script>alert('Error al procesar datos de distribucion Intentelo de nuevo');</script>");
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
                if (cor.get(i).getProducto() != Integer.parseInt(producto)) {

                    while (pi < pf) {
//                        System.out.println("cont:" + cont + " " + dis.get(cont));
                        arraux.add(dis.get(cont));
                        pi += 0.5;
                        cont++;
                    }
                } else {
                    while (pi < pf) {
                        pi += 0.5;
                        cont++;
                    }
                    indice = i;
                }
            }
            if (corrida.isEmpty()) {
                dis.clear();
                cor.clear();
                corrida.clear();
                objSesion.setAttribute("distribucion", dis);
                objSesion.setAttribute("producto", cor);
                objSesion.setAttribute("corrida", corrida);
            } else {
                dis.clear();
                cor.remove(indice);
                corrida.remove(indice);
                dis = arraux;
                objSesion.setAttribute("producto", cor);
                objSesion.setAttribute("corrida", corrida);
                objSesion.setAttribute("distribucion", dis);
            }
        } else if (uso.equals("nuevopedido")) {
            String fp = (String) request.getParameter("fp");
            String fe = (String) request.getParameter("fe");
            String nc = (String) request.getParameter("nc").toUpperCase();
            String dir = (String) request.getParameter("dir").toUpperCase();
            //String rfc = (String) request.getParameter("rfc").toUpperCase();
            String tel = (String) request.getParameter("tel");
            String email = (String) request.getParameter("email");
            int cont = 0;
            int totalpares = 0;
            int totalparesaux = 0;
            float costo = 0;
            for (int i = 0; i < corrida.size(); i++) {
                float pi = corrida.get(i).getPi();
                float pf = corrida.get(i).getPf() + 1;
                while (pi < pf) {
                    totalpares += Integer.parseInt(dis.get(cont));
//                    System.out.println(dis.get(cont));
                    pi += 0.5;
                    cont++;
                }
                totalparesaux += totalpares;
                costo += cor.get(i).getCostof() * totalpares;
                totalpares = 0;

            }
            Calendar fecha = Calendar.getInstance();
            int hour = fecha.get(Calendar.HOUR_OF_DAY);
            int min = fecha.get(Calendar.MINUTE);
            Pedido p = new Pedido();
            DAO_Pedido dp = new DAO_Pedido();
            p.setPedido(dp.max_pedemp());
            p.setFechapedido(fp + " " + hour + ":" + min + ":00.000");
            p.setFechaentrega(fe + " 00:00:00.000");
            p.setNombrecliente(nc);
            p.setRfc("");
            p.setDireccion(dir);
            p.setTelefono(tel);
            p.setEmail(email);
            p.setUsuario(usuario);
            p.setTotalpares(totalparesaux);
            p.setImporte(costo);
            p.setIva((float) (costo * 0.16));
            p.setTotal(costo + ((float) (costo * 0.16)));
            p.setStatus("A");
            int pedidonuevo = 0;
            pedidonuevo = dp.nuevoped(p, dis, cor, corrida);
            dis.clear();
            cor.clear();
            corrida.clear();
            out.print("PEDIDO ALMACENADO EXITOSAMENTE !!! ");

        } else if (uso.equals("fechas")) {
            ArrayList<Pedido> arr = new ArrayList<>();
            String f1 = (String) request.getParameter("f1");
            String f2 = (String) request.getParameter("f2");
            String b = (String) request.getParameter("busqueda").toUpperCase();
            DAO_Pedido ped = new DAO_Pedido();
            arr = ped.getall(f1, f2, b);
            for (int i = 0; i < arr.size(); i++) {
                out.print("<tr>");
                out.print("<td>" + arr.get(i).getPedido() + "</td>");
                out.print("<td>" + arr.get(i).getFechapedido() + "</td>");
                out.print("<td>" + arr.get(i).getFechaentrega() + "</td>");
                out.print("<td>" + arr.get(i).getNombrecliente() + "</td>");
                out.print("<td>" + arr.get(i).getTelefono() + "</td>");
                out.print("<td>" + arr.get(i).getTotalpares() + "</td>");
                out.print("<td>" + arr.get(i).getImporte() + "</td>");
                out.print("<td>" + arr.get(i).getIva() + "</td>");
                out.print("<td>" + arr.get(i).getTotal() + "</td>");
                out.print("</tr>");
            }

        }

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

}
