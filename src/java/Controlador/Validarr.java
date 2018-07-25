/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Corrida;
import Modelo.Producto;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
       // try {
            String nombre = request.getParameter("user");
            String contrasena = request.getParameter("pass");
            String save = request.getParameter("save");
            if(save!=null){
            }else save="no";
            boolean flag = false;
            int interv = 180;
            PrintWriter out = response.getWriter();
            //control de acceso 
            
                if (nombre==null || contrasena==null || nombre.equals("") || contrasena.equals("")) {// se regresa al inicio si el usuario o contrasena son vacios
                    out.println("<script type=\"text/javascript\">");
                    out.println("location='index.jsp';");
                    out.println("</script>");
                    flag = true;
                }
                 else {
                    // Definir variable de referencia a un objeto de tipo Usuario
                    //String tipo = "";
                    // Consultar Base de datos
                    Usuario u = new Usuario();
                    u.setNombre(nombre);
                    //u = a.buscaru(nombre, contrasena);// busca elusuario con los datos proporcionados y guarda el tipo de usuario en 'tipo'
                    if (u.getNombre().equals("n")) {// si no encontro nada
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Usuario o contrasena incorrectos');");
                        out.println("location='index.jsp';");
                        out.println("</script>");
                    } else {
                     //   switch (u.getTipoUsuario()) {
                            //usuario administrador
                      //      case "ADMIN":
                      ArrayList<String> arr=new ArrayList<>();
                      ArrayList<Producto>arr1 = new ArrayList<>();
                      ArrayList<Corrida>arr2 = new ArrayList<>();
                                objSesion.setMaxInactiveInterval(interv + 100000);
                                objSesion.setAttribute("usuario", nombre);
                                objSesion.setAttribute("tipo","ADMIN");
                                objSesion.setAttribute("distribucion", arr);
                                objSesion.setAttribute("producto", arr1);
                                objSesion.setAttribute("corrida", arr2);
                                response.sendRedirect("usuario/index.jsp");
                                
                       /*/         break;
                            case "USUARIO":
                                //usuario normal
                                objSesion.setMaxInactiveInterval(interv + 10000);
                                objSesion.setAttribute("usuario", nombre);
                                objSesion.setAttribute("tipo", m.getTipo_usuario());
                                objSesion.setAttribute("empresa", m.getNombre_empresa());
                                response.sendRedirect("usuario/index.jsp");
                                break;
                            case "VIGILANTE":
                                //posible usuario para inicio de la pagina
                                objSesion.setMaxInactiveInterval(interv + 150000);
                                objSesion.setAttribute("usuario", nombre);
                                objSesion.setAttribute("tipo", m.getTipo_usuario());
                                objSesion.setAttribute("empresa", m.getNombre_empresa());
                                response.sendRedirect("usuario/index.jsp");
                                break;    
                            default:// si lo que encontro es diferente a lo antes descrito manda un msj y lo regresa a ala pagina de inicio
                                out.println("<script type=\"text/javascript\">");
                                out.println("alert('Usuario o contrasena incorrectos');");
                                out.println("location='index.jsp';");
                                out.println("</script>");
                                break;
                        }/*/
                    }
                }
            
//        } catch (SQLException ex) {
//            System.out.println("Codigo 1: " + ex);
//            PrintWriter out = response.getWriter();
//            out.println("<script type=\"text/javascript\">");
//            out.println("location='index.jsp';alert('Codigo 1: " + ex + "');");
//            out.println("</script>");
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Validarr.class.getName()).log(Level.SEVERE, null, ex);
//            PrintWriter out = response.getWriter();
//            out.println("<script type=\"text/javascript\">");
//            out.println("location='index.jsp';alert('Codigo 1.1: " + ex + "');");
//            out.println("</script>");
//        }catch (Exception ex) {
//            Logger.getLogger(Validarr.class.getName()).log(Level.SEVERE, null, ex);
//            PrintWriter out = response.getWriter();
//            out.println("<script type=\"text/javascript\">");
//            out.println("location='index.jsp';alert('Codigo 1.2: " + ex + "');");
//            out.println("</script>");
//        }
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
