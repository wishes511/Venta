/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DAO_Corrida;
import DAO.DAO_Linea;
import DAO.DAO_Pedido;
import DAO.DAO_Producto;
import Modelo.Corrida;
import Modelo.Linea;
import Modelo.Pedido;
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
@WebServlet(name = "Consultas", urlPatterns = {"/Consultas"})
public class Consultas extends HttpServlet {

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
        System.out.println(usuario + " " + tipos);
        if (usuario != null && tipos != null && (tipos.equals("ADMIN")|| tipos.equals("VENTAS")|| tipos.equals("USUARIO")|| tipos.equals("ALTAS"))) {
            if(tipos.equals("VENTAS")){
                response.sendRedirect("usuario/index.jsp");
            }else if(tipos.equals("ALTAS")){
                response.sendRedirect("usuario/productos.jsp");
            }
        } else {
            response.sendRedirect("../index.jsp");
        }
        try{
        String uso = (String)request.getParameter("uso");
        
        PrintWriter out = response.getWriter();
        String tipo = (String)request.getParameter("tipo");
            ArrayList<Linea> arrlinea= new ArrayList<Linea>();
            ArrayList<Pedido> arrpedido= new ArrayList<Pedido>();
            ArrayList<Producto> arrproducto = new ArrayList<Producto>();
           
            DAO_Linea l=new DAO_Linea();
            DAO_Pedido p = new DAO_Pedido();
            System.out.println(uso+" "+tipo);
        if(uso.equals("clasificacion")){
            arrlinea=l.getLinea(uso);
            out.print("<label>Clasificacion:</label><select class=\"form-control\"  id=\"selectbuscar\" onchange=\"salto()\"><option></option>");
            for(int i = 0;i<arrlinea.size();i++){
                out.print("<option class=\"form-control\" value="+arrlinea.get(i).getDescripcion()+">"+arrlinea.get(i).getDescripcion()+"</option>");
            }
            out.print("</select>");
        }else if(uso.equals("cliente")) {
            arrpedido=p.getcliente_consulta();
            out.print("<label>Clientes:</label><select class=\"form-control\"  id=\"selectbuscar\" onchange=\"salto()\"><option></option>");
            for(int i = 0;i<arrpedido.size();i++){
                out.print("<option class=\"form-control\" value="+arrpedido.get(i).getNombrecliente()+">"+arrpedido.get(i).getNombrecliente()+"</option>");
            }
            out.print("</select>");
        }else if(uso.equals("linea")){
             DAO_Producto prod= new DAO_Producto();
            arrproducto=prod.getprod_consulta();
            out.print("<label>Marcas:</label><select class=\"form-control\"  id=\"selectbuscar\" onchange=\"salto()\"><option></option>");
            for(int i = 0;i<arrproducto.size();i++){
                out.print("<option class=\"form-control\" value="+arrproducto.get(i).getMarca()+">"+arrproducto.get(i).getMarca()+"</option>");
            }
            out.print("</select>");
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
