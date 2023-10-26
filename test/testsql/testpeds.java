/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsql;

import Controlador.Crearpdf;
import Modelo.Cliente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import persistencia.VS_Pedido;
import persistencia.conBD;
import persistencia.mail;

/**
 *
 * @author GATEWAY1-
 */
public class testpeds {

    public static void main(String[] args) {
        testpeds t = new testpeds();
        //t.testmaxped();
        //t.sentemail();
        t.borrapdf();
    }

    /**
     * Prueba la consulta que traera el numera maximo del pedido
     */
    private void testmaxped() {
        try {
            conBD c = new conBD();
            c.abrir68("");
            VS_Pedido p = new VS_Pedido();
            System.out.println(p.getlast());
            c.cerrar68();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(testpeds.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(testpeds.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sentemail(){
        Cliente c = new Cliente();
        c.setEmail("wisheswis511@gmail.com");
        mail m = new mail("Pedido numero N","Pedido realizado, favor de verificar la informacion capturada",c,"pedido");
        m.sendmail();
    }
    
    private void borrapdf(){
        Crearpdf c = new Crearpdf();
        c.deletepdf("24V");
    }
}
