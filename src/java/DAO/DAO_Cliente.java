/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Cliente;
import Modelo.Int_cliente;
import java.sql.Connection;
import java.util.ArrayList;
import persistencia.VS_Cliente;

/**
 *
 * @author GATEWAY1-
 */
public class DAO_Cliente extends VS_Cliente implements Int_cliente {

    /**
     *Se utiliza para obtener todos los clientes pero con la conexion adecuada
     * dada desde que se manda llamar
     * @param c
     * @param agente
     * @return
     */
    @Override
    public ArrayList<Cliente> getallclientes(Connection c, int agente) {
        return getclientes(c, agente);
    }

    /**
     * Obtiene toda la informacion acerca del cliente para insertarse en la bd
     * @param c conexion de cobranza
     * @param numcliente dato obtenido de el combobox del html
     * @param fecha la fecha en que se hara el documento
     * @return 
     */
    @Override
    public Cliente getcliente(Connection c, int numcliente, String fecha) {
        return getclient(c, numcliente, fecha);
    }

}
