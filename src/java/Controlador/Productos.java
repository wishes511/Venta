/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DAO_Corrida;
import DAO.DAO_Producto;
import Modelo.Corrida;
import Modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.VS;

/**
 *
 * @author gateway1
 */
@WebServlet(name = "Productos", urlPatterns = {"/Productos"})
public class Productos extends HttpServlet {

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
        PrintWriter out=  response.getWriter();
        try {
            HttpSession objSesion = request.getSession(false);
            objSesion.invalidate();
            out.println("<script type=\"text/javascript\">");
                    out.println("location='index.jsp';");
                    out.println("</script>");
        } catch (Exception e) {
            out.println("<script type=\"text/javascript\">");
                    out.println("location='index.jsp';");
                    out.println("</script>");
        }
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
        ArrayList<Producto> cor = (ArrayList<Producto>) objSesion.getAttribute("corrida");
//        System.out.println(usuario + " " + tipos);
        if (usuario != null && tipos != null && (tipos.equals("ADMIN")|| tipos.equals("VENTAS")|| tipos.equals("USUARIO")|| tipos.equals("ALTAS"))) {
            
        } else {
            response.sendRedirect("../index.jsp");
        }
        try {
            String uso = (String) request.getParameter("uso");
            //System.out.println(uso);
            PrintWriter out = response.getWriter();
            if (uso.equals("buscar")) {
                String clave = (String) request.getParameter("p");
                DAO_Producto dprod = new DAO_Producto();
                DAO_Corrida dcor = new DAO_Corrida();
                Producto p = new Producto();
                p = dprod.getprodwithID(Integer.parseInt(clave));// obtener producto desde campo HTML
                Corrida c = new Corrida();
                c = dcor.getcorridawithID(Integer.parseInt(clave)); // obtener corrida y puntos desde campo HTML
                if (p.getEstilo() != 0) {
                    out.print("<div class=\"container-fluid\"><div class=\"col-md-4\" style=\"padding-top: 2%\" align=\"center\">\n"
                            + " <div class=\"    row\"><label class=\"prodbusqeuda\">Estilo</label><label class=\"prodbusqeuda\">Combinacion</label><label class=\"prodbusqeuda\">Corrida</label></div>\n"
                            + " <div class=\"row\"><label class=\"prodbusqeuda\">" + p.getEstilo() + "</label><label class=\"prodbusqeuda\">" + p.getCombinacionchar() + "</label><label class=\"prodbusqeuda\">" + p.getCorridachar() + "</label></div>\n"
                            + " </div>"
                            + " <div class=\" col-md-offset-4\"  id=\"get_catalogo\" align=\"center\" style=\"padding-top: 2%\">\n"
                            + " <div style=\" overflow: auto\"  class=\"col-md-12\" align=\"center\" >\n"
                            + " <table  border=\"1\" width=\"5\" class=\"table table-bordered table-condensed\" style=\"overflow: auto\" align=\"center\">\n"
                            + " <thead align=\"center\" >");
                    float i = c.getPi();
                    float z = c.getPf() + 1;
                    while (i < z) {
                        out.print("<th width=\"10\">" + i + "</th>");
                        i += 0.50;
                    }
                    i = c.getPi();
                    z = c.getPf() + 1;
                    out.print("</thead><tr id=tabla align=\"center\" contenteditable=\"true\">");
                    while (i < z) {
                        if (i == c.getPi()) {
                            out.print("<td id=\"firstcell\" width=\"10\"></td>");
                        } else {
                            if (c.getPf() + 0.5 == i) {
                                out.print("<td width=\"10\"></td>");
                            } else {
                                out.print("<td width=\"10\"></td>");
                            }
                        }
                        i += 0.50;
                    }
                    out.print("</tr></table></div></div>");
                    out.print("<div class=\"row\"><div class=\"col-md-offset-5\">"
                            + "<label onclick=\"averdato()\">Agregar Al Pedido</label><a class=\"btn\"><img onclick=\"averdato()\" class=\"imagentabla\" src=\"../images/ok.png\" alt=\"\"></a>"
                            +"<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" data-target=\"#miModal\">\n" 
                            +"Iniciar Prepack</button>"
                            + "</div></div></div>");
                }else{ out.print("<div class=\"container-fluid\">");
                       out.print("<div class=\" col-md-offset-1\"  id=\"get_catalogo\" align=\"center\" style=\"padding-top: 2%\">");
                       out.print("<label>EL Producto No existe Intentelo de nuevo</label></div></div>");
                }

            }
            if (uso.equals("nuevo")) {
                int estilo = Integer.parseInt((String) request.getParameter("estilo"));
                String combinacion = ((String) request.getParameter("combinacion").toUpperCase());
                int corrida = Integer.parseInt((String) request.getParameter("corrida"));
                String linea = (String) request.getParameter("linea").toUpperCase();
                float costo = Float.parseFloat((String) request.getParameter("costo"));
                String tipo = ((String) request.getParameter("tipo").toUpperCase());
                String marca = ((String) request.getParameter("marca").toUpperCase());
                Producto p = new Producto();
                p.setEstilo(estilo);
                p.setCombinacionchar(combinacion);
                p.setClave_corrida(corrida);
                p.setLineachar(linea);
                p.setCostof(costo);
                p.setTipo(tipo);
                p.setStatus("A");
                p.setMarca(marca);
                DAO_Producto prod = new DAO_Producto();
                prod.nuevoprod(p);
                //out.print("<script>alert('"+prod.nuevoprod(p)+"');location='usuario/productos.jsp';</script>");
                out.print("<script>location='usuario/productos.jsp';</script>");
                //response.sendRedirect("usuario/productos.jsp");
            } else if (uso.equals("searchprod_cata")) {
                String estilo = (String) request.getParameter("p");
                DAO_Producto dp = new DAO_Producto();
                ArrayList<Producto> lista = new ArrayList<Producto>();
                lista = dp.buscarcataprod(estilo);
                out.print("<table class=\"table table-responsive mapa\" id=\"tabla-prods\">\n"
                        + "                            <tr>\n"
                        + "                                <td>Estilo</td>\n"
                        + "                                <td>Combinacion</td>\n"
                        + "                                <td>Corrida</td>\n"
                        + "                                <td>Linea</td>\n"
                        + "                                <td>Costo</td>\n"
                        + "                                <td>Tipo</td>\n"
                        + "                                <td>Submarca</td>\n"
                        + "                            </tr>");
                if (!lista.isEmpty()) {
                    for (int i = 0; i < lista.size(); i++) {
                        out.print("<tr>"
                                + "<td onclick=domod("+lista.get(i).getProducto()+")>" + lista.get(i).getEstilo() + "</td>"
                                + "<td>" + lista.get(i).getCombinacionchar() + "</td>"
                                + "<td>" + lista.get(i).getCorridachar() + "</td>"
                                + "<td>" + lista.get(i).getLineachar() + "</td>"
                                + "<td>" + lista.get(i).getCostof() + "</td>"
                                + "<td>" + lista.get(i).getTipo() + "</td>"
                                + "<td>" + lista.get(i).getMarca() + "</td>"
                                + "</tr>");
                    }
                }
                out.print("</table>");
            }else if(uso.equals("update")){
                int estilo = Integer.parseInt((String) request.getParameter("estilo"));
                String combinacion = ((String) request.getParameter("combinacion"));
                int corrida = Integer.parseInt((String) request.getParameter("corrida"));
                String linea = (String) request.getParameter("linea");
                String tipo = ((String) request.getParameter("tipo").toUpperCase());
                String marca = ((String) request.getParameter("marca").toUpperCase());
                int prod = Integer.parseInt((String) request.getParameter("prod"));
                Producto p = new Producto();
                p.setProducto(prod);
                p.setEstilo(estilo);
                p.setClave_combinacion(Integer.parseInt(combinacion));
                p.setClave_corrida(corrida);
                p.setClave_linea(Integer.parseInt(linea));
                p.setTipo(tipo);
                p.setMarca(marca);
                VS v = new VS();
                String msg="";
                msg=v.actualizarprod(p);
                out.print(msg);
            }

        }catch(NumberFormatException a){
         System.out.println(a);
         PrintWriter out=  response.getWriter();
         out.print("<script>alert('Error al procesar Letras con numeros, Intentelo de nuevo');");
         out.print("location='usuario/productos.jsp';</script>");
        } catch (Exception e) {
            
        PrintWriter out=  response.getWriter();
        out.print("<script>alert('Error al procesar Informacion');");
         out.print("location='location=../index';</script>");
            System.out.println(e);
            //response.sendRedirect("../index.jsp");
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
