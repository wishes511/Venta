

<%@page import="java.io.OutputStream"%>
<%@page import="net.sf.jasperreports.engine.JRExporter"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="net.sf.jasperreports.engine.export.JRPdfExporter"%>
<%@page import="net.sf.jasperreports.engine.JRExporterParameter"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="persistencia.conBD"%>
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

        <% HttpSession objSesion = request.getSession(false);
//i_d
            boolean estado;
            String usuario = (String) objSesion.getAttribute("usuario");
            String tipos = (String) objSesion.getAttribute("tipo");
            String ids = String.valueOf(objSesion.getAttribute("i_d"));

            if (usuario != null && tipos != null && (tipos.equals("USUARIO") || tipos.equals("VENTAS") || tipos.equals("ADMIN") || tipos.equals("ALTAS"))) {
                if (tipos.equals("ALTAS")) {
                    response.sendRedirect("usuario/productos.jsp");
                }
            } else {
                response.sendRedirect("../index.jsp");
            }
            String p = (String) request.getParameter("peds");
            conBD c = new conBD();

            try {
                // reporte de etiquetas
                File reportfile = new File(application.getRealPath("usuario/Ventas_pedido.jasper"));
                Map para = new HashMap();
                para.put("search", new String(p));
                /*byte[] bytes = JasperRunManager.runReportToPdf(reportfile.getPath(), para, c.get68());
                response.setContentType("application/pdf");
                response.setContentLength(bytes.length);
                JasperRunManager.runReportToPdfFile(reportfile.getPath(), para, c.get68());
                ServletOutputStream outputstream = response.getOutputStream();
                outputstream.write(bytes, 0, bytes.length);
                outputstream.flush();
                outputstream.close();*/
                JasperPrint print = new JasperPrint();
                print = JasperFillManager.fillReport(reportfile.getPath(), para, c.get68());
                byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
                response.setContentType("application/pdf");
                //response.setContentType("application/x-pdf");
                //response.setContentType("application/html");
                //response.setHeader("Content-Disposition", "attachment;filename=rep.pdf;");
                response.setHeader("Content-Disposition", "inline;filename=pedido " + p + ".pdf");
                response.getOutputStream().write(pdfBytes);
                response.flushBuffer();
                final OutputStream outStream = response.getOutputStream();
                JasperExportManager.exportReportToPdfStream(print, outStream);
                JasperExportManager.exportReportToPdfFile(print, "reporte.pdf");
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "c:\\af\\Rep.pdf");
                exporter.exportReport();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e);
                //  response.sendRedirect("verpares.jsp");
            }


        %>
    </body>

</html>
