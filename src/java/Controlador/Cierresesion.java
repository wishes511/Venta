/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.conBD;

/**
 *
 * @author gateway1
 */
@WebServlet(name = "Cierresesion", urlPatterns = {"/Cierresesion"})
public class Cierresesion extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            HttpSession objSesion = request.getSession(false);
            objSesion.invalidate();
            conBD c = new conBD();
            c.cerrar68();
            c.cerrars();
        } catch (Exception e) {

        }
        out.println("<script type=\"text/javascript\">");
        out.println("location='index.jsp';");
        out.println("</script>");
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
        PrintWriter out = response.getWriter();
        try {
            HttpSession objSesion = request.getSession(false);
            objSesion.invalidate();
            conBD c = new conBD();
            c.cerrar68();
            c.cerrars();
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
        try {
            HttpSession objSesion = request.getSession(true);
            objSesion.invalidate();
            Cookie galle_nombre = new Cookie("user", "");
            Cookie tipo = new Cookie("tipo", "");
            Cookie empresa = new Cookie("empresa", "");
            galle_nombre.setMaxAge(0);
            tipo.setMaxAge(0);
            empresa.setMaxAge(0);
            conBD c = new conBD();
            c.cerrar68();
            c.cerrars();
            response.addCookie(tipo);
            response.addCookie(galle_nombre);
            response.addCookie(empresa);
            response.sendRedirect("index.jsp");
        } catch (Exception e) {
            response.sendRedirect("index.jsp");

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
