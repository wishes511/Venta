/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author GATEWAY1-
 */
public class Crearpdf {

    public void createpdf(String p, Connection c) {
        try {
            // reporte de etiquetas
            Map para = new HashMap();
            para.put("search", p);
            JasperPrint print = new JasperPrint();
            print = JasperFillManager.fillReport("C:\\af\\Venta\\Ventas_pedido.jasper", para, c);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "c:\\af\\Venta\\pdf\\" + getpedformat(p) + ".pdf");
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void deletepdf(String p) {
        try {
            File f = new File("c:\\af\\Venta\\pdf\\" + getpedformat(p)+ ".pdf");
            f.delete();
        } catch (Exception ex) {
            Logger.getLogger(Crearpdf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getpedformat(String f) {
        String resp = "";
        for (int i = 0; i < f.length(); i++) {
            String aux = f.charAt(i) + "";
            if (!aux.equals(" ")) {
                resp += f.charAt(i) + "";
            }
        }
        return resp;
    }
}
