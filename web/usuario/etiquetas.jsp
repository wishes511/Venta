<%-- 
    Document   : depcosto
    Created on : Sep 15, 2017, 9:51:16 AM
    Author     : gateway1
--%>

<%@page import="DAO.DAO_Producto"%>
<%@page import="persistencia.VS"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>

        <% HttpSession objSesion = request.getSession(true);
//i_d
            boolean estado;
            String usuario = (String) objSesion.getAttribute("usuario");
            String tipos = (String) objSesion.getAttribute("tipo");
            String ids = String.valueOf(objSesion.getAttribute("i_d"));

            if (usuario != null && tipos != null && (tipos.equals("USUARIO") || tipos.equals("VENTA") || tipos.equals("ADMIN"))) {

            } else {
                response.sendRedirect("../index.jsp");
            }
            String f1 = (String)request.getParameter("catalogo");
            String patt = "\\d{0,7}";
            DAO_Producto prod = new DAO_Producto();

            try {
                // reporte de etiquetas
                File reportfile = new File(application.getRealPath("usuario/etiquetas.jasper"));
                Map para = new HashMap();
                para.put("param", new String(f1));
                byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), para, prod.conexionbd());
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                ServletOutputStream outputstream = response.getOutputStream();
                outputstream.write(bytes, 0, bytes.length);
                outputstream.flush();
                outputstream.close();
            } catch (Exception e) {
                e.printStackTrace();

                //  response.sendRedirect("verpares.jsp");
            } finally {
                if (prod.conexionbd() != null) {
                    prod.closebd();
                    // response.sendRedirect("verpares.jsp");
                }
            }


        %>
    </body>

</html>
