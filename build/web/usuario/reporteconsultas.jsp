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
            String tipo = (String)request.getParameter("tipo");
            String uso = (String)request.getParameter("report");
            String select = (String)request.getParameter("select");
            DAO_Producto prod = new DAO_Producto();
            try {
               String s="" ;
               if(select==null || select.equals("undefined")){select="";}
               //System.out.println(tipo+" -"+uso+" -"+select);
            if(uso.equals("clasificacion")){
                
                if(tipo.equals("concentrado")){
                   // System.out.println("Aqui debo de ");
                s="usuario/marca.jasper";
                }else s="usuario/marca_1.jasper";
            }else if(uso.equals("linea")){
                if(tipo.equals("concentrado")){
                
                    s="usuario/clasificacion.jasper";
                }else s="usuario/clasificacion_1.jasper";
            }else if(uso.equals("cliente")){
                if(tipo.equals("concentrado")){
                s="usuario/cliente.jasper";
                }else s="usuario/cliente_1.jasper";
            }else {
                out.print("<script>location='../index.jsp';</script>");
            }
                File reportfile =new File(application.getRealPath(s));
                // reporte de consultas
                //File reportfile = new File(application.getRealPath("usuario/consultas/clasificacion.jasper"));
                Map para = new HashMap();
                para.put("desc", new String(select));
                byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), para, prod.conexionbd());
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                
                ServletOutputStream outputstream = response.getOutputStream();
                outputstream.write(bytes, 0, bytes.length);
                outputstream.flush();
                outputstream.close();
                
            } catch (Exception e) {
                System.out.println(e);
                //out.print("<script>location='../index.jsp';</script>");
                 } finally {
                if (prod.conexionbd() != null) {
                    prod.closebd();
                    // response.sendRedirect("verpares.jsp");
                }
            }


        %>
    </body>

</html>
