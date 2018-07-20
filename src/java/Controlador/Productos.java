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
        System.out.println(usuario + " " + tipos);
        if (usuario != null && tipos != null && (tipos.equals("ADMIN"))) {

        } else {
            response.sendRedirect("../index.jsp");
        }
        try{
        String uso = (String)request.getParameter("uso");
        if(uso.equals("buscar")){
            PrintWriter out = response.getWriter();
            String clave = (String)request.getParameter("p");
            DAO_Producto dprod = new DAO_Producto();
            DAO_Corrida dcor = new DAO_Corrida();
            Producto p = new Producto();
            p=dprod.getprodwithID(Integer.parseInt(clave));
            Corrida c = new Corrida();
            c=dcor.getcorridawithID(Integer.parseInt(clave));
            //
            out.print("<div class=\"container-fluid\"><div class=\"col-md-4\" style=\"padding-top: 2%\" align=\"center\">\n" +
"                        <div class=\"row\"><label class=\"prodbusqeuda\">Estilo</label><label class=\"prodbusqeuda\">Combinacion</label><label class=\"prodbusqeuda\">Corrida</label></div>\n" +
"                        <div class=\"row\"><label class=\"prodbusqeuda\">"+p.getEstilo()+"</label><label class=\"prodbusqeuda\">"+p.getCombinacionchar()+"</label><label class=\"prodbusqeuda\">"+p.getCorridachar()+"</label></div>\n" +
"                    </div>"+
"            <div class=\" col-md-offset-4\"  id=\"get_catalogo\" align=\"center\" style=\"padding-top: 2%\">\n" +
"                <div style=\" overflow: auto\"  class=\"col-md-12\" align=\"center\" >\n" +
"                    <table border=\"1\" width=\"5\" class=\"table table-bordered table-condensed\" style=\"overflow: auto\" align=\"center\">\n" +
"                        <thead align=\"center\" >");
            float i = c.getPi();
            float z = c.getPf()+1;
            while(i<z){
                out.print("<th width=\"10\">"+i+"</th>");
                i+=0.50;
            }
           i = c.getPi();
           z = c.getPf()+1;
            out.print("</thead><tr align=\"center\" contenteditable=\"true\">");
             while(i<z){
                 if(c.getPf()+0.5==i){
                 out.print("<td width=\"10\" >0</td>");
                 }else{
                 out.print("<td width=\"10\">0</td>");
                 }
                i+=0.50;
            }
             out.print("</tr></table></div></div>");
             out.print("<div class=\"row\"><div class=\"col-md-offset-6\">"
                     + "<a class=\"btn\"><img onclick=\"averdato()\" class=\"imagentabla\" src=\"../images/ok.png\" alt=\"\"></a>"
                     + "</div></div></div>");
        }
        
        }catch (Exception e) {
        System.out.println(e);
        response.sendRedirect("../index.jsp");
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
