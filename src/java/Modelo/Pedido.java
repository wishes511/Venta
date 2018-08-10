/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author gateway1
 */
public class Pedido implements Serializable{
    int clavepedido,pedido,totalpares;
    float importe,total,iva;
    String fechapedido,fechaentrega,nombrecliente,rfc,direccion,email,status,usuario,telefono;
    ArrayList<Producto> arr= new ArrayList<Producto>();
    ArrayList<Corrida> cor= new ArrayList<Corrida>();
    ArrayList<String> dis= new ArrayList<String>();
            ;
    public int getClavepedido() {
        return this.clavepedido;
    }

    public void setClavepedido(int clavepedido) {
        this.clavepedido = clavepedido;
    }

    public int getPedido() {
        return this.pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getTotalpares() {
        return this.totalpares;
    }

    public void setTotalpares(int totalpares) {
        this.totalpares = totalpares;
    }

    public float getImporte() {
        return this.importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public float getTotal() {
        return this.total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getIva() {
        return this.iva;
    }

    public void setIva(float iva) {
        this.iva = iva;
    }

    public String getFechapedido() {
        return this.fechapedido;
    }

    public void setFechapedido(String fechapedido) {
        this.fechapedido = fechapedido;
    }

    public String getFechaentrega() {
        return this.fechaentrega;
    }

    public void setFechaentrega(String fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public String getNombrecliente() {
        return this.nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getRfc() {
        return this.rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Producto> getArr() {
        return this.arr;
    }

    public void setArr(ArrayList<Producto> arr) {
        this.arr = arr;
    }

    public ArrayList<Corrida> getCor() {
        return this.cor;
    }

    public void setCor(ArrayList<Corrida> cor) {
        this.cor = cor;
    }

    public ArrayList<String> getDis() {
        return this.dis;
    }

    public void setDis(ArrayList<String> dis) {
        this.dis = dis;
    }

    
}
