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
        HttpSession objSesion = request.getSession(false);
        String usuario = (String) objSesion.getAttribute("usuario");
        String tipos = (String) objSesion.getAttribute("tipo");
        ArrayList<String> dis = (ArrayList<String>) objSesion.getAttribute("distribucion");
        ArrayList<Producto> cor = (ArrayList<Producto>) objSesion.getAttribute("producto");
        ArrayList<Corrida> corrida = (ArrayList<Corrida>) objSesion.getAttribute("corrida");
        if (usuario != null && tipos != null && (tipos.equals("ADMIN"))) {

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
            cor.add(dprod.getprodwithID_nochar(Integer.parseInt(prod)));
            corrida.add(dcor.getcorridawithID(Integer.parseInt(prod)));
            System.out.println(cor.get(0).getEstilo());
            objSesion.setAttribute("distribucion", getdis(dato, dis));
            objSesion.setAttribute("producto", cor);
            objSesion.setAttribute("corrida", corrida);
            out.print(cor.size());
        }
        if (uso.equals("vaciar")) {
            dis.clear();
            cor.clear();
            corrida.clear();
            objSesion.setAttribute("distibucion", dis);
            objSesion.setAttribute("producto", cor);
            objSesion.setAttribute("corrida", corrida);
        }

    }

    private ArrayList<String> getdis(String dato, ArrayList<String> arr) {
        String aux = "";
        for (int i = 0; i < dato.length(); i++) {
            int var = dato.charAt(i);
            System.out.println("dato s " + var);
            if (var >= 46 || var <= 57) {
                aux = var + "";
            } else {
                arr.add(aux);
                aux = "";
            }
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
