/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author GATEWAY1-
 */
public interface Int_cliente {

    ArrayList<Cliente> getallclientes(Connection c, int agente);

    Cliente getcliente(Connection c, int numcliente, String fecha);

}
