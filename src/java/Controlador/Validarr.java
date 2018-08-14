/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DAO_Usuario;
import Modelo.Corrida;
import Modelo.Producto;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mich
 */
@WebServlet(name = "Validarr", urlPatterns = {"/Validarr"})
public class Validarr extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Redireccionar a una pagina de respuesta
        // Redireccionar a una pagina de respuesta
    }

    private boolean regularexp(String name, String pass) {
        boolean flag = true;
        // String patt="\\d{1,2}\\-\\d{1,2}\\-\\d{4}";
        String patp = "[a-zA-Z0-9]+";
        Pattern pat1 = Pattern.compile(patp);
        Matcher match1 = pat1.matcher(name);
        Pattern pat2 = Pattern.compile(patp);
        Matcher match2 = pat2.matcher(pass);
        if (match1.matches() && match2.matches()) {
            flag = false;
        }
        return flag;
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
        response.sendRedirect("index.jsp");
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
        HttpSession objSesion = request.getSession(true);
        try {
            String nombre = request.getParameter("user");
            String contrasena = request.getParameter("pass");
            boolean flag = false;
            int interv = 1800;
            PrintWriter out = response.getWriter();
            //control de acceso 
                if (nombre==null || contrasena==null || nombre.equals("") || contrasena.equals("")) {// se regresa al inicio si el usuario o contrasena son vacios
                    out.println("<script type=\"text/javascript\">");
                    out.println("location='index.jsp';");
                    out.println("</script>");
                    flag = true;
                }else {
                    // Definir variable de referencia a un objeto de tipo Usuario
                    // Consultar Base de datos
            String patt = "[a-z1-9A-Z\\s]*";
            Pattern pat = Pattern.compile(patt);
            Matcher match = pat.matcher(nombre);
            Matcher match1 = pat.matcher(contrasena);
            if(match.matches() && match1.matches()){
                Usuario u = new Usuario();
                    u.setUsuario(nombre);
                    u.setPass(contrasena);
                    DAO_Usuario du = new DAO_Usuario();// busca elusuario con los datos proporcionados y guarda el tipo de usuario en 'tipo'
                    u=du.getUsuario(u);
                    if (u.getTipo()==null) {// si no encontro nada
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Usuario o contrasena incorrectos');");
                        out.println("location='index.jsp';");
                        out.println("</script>");
                    } else {
                        String redir="";
                        switch (u.getTipo()) {
                            //usuario administrador
                            case "ADMIN":
                                redir="usuario/index.jsp";
                                
                                break;
                            case "VENTAS"://usuario normal
                                 redir="usuario/index.jsp";
                                break;
                            case "USUARIO"://usuario normal
                                 redir="usuario/productos.jsp";
                                break;
                            case "ALTAS"://usuario normal
                                 redir="usuario/productos.jsp";
                                break;
                            default:// si lo que encontro es diferente a lo antes descrito manda un msj y lo regresa a ala pagina de inicio
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('Usuario o contrasena incorrectos');");
                                out.println("location='index.jsp';");
                                out.println("</script>");
                                break;
                        }
                                ArrayList<String> arr=new ArrayList<>();
                                ArrayList<Producto>arr1 = new ArrayList<>();
                                ArrayList<Corrida>arr2 = new ArrayList<>();
                                objSesion.setMaxInactiveInterval(interv + 100000);
                                objSesion.setAttribute("usuario",nombre);
                                objSesion.setAttribute("tipo",u.getTipo());
                                objSesion.setAttribute("distribucion", arr);
                                objSesion.setAttribute("producto", arr1);
                                objSesion.setAttribute("corrida", arr2);
                                response.sendRedirect(redir);
                    }
            }else {
                out.println("<script type=\"text/javascript\">");
                out.println("location='index.jsp';alert('Favor de Introducir informacion valida!');");
                out.println("</script>");
            }
                    
                }
            
        }catch (Exception ex) {
            Logger.getLogger(Validarr.class.getName()).log(Level.SEVERE, null, ex);
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("location='index.jsp';alert('Error al procesar datos de entrada: " + ex + "');");
            out.println("</script>");
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
