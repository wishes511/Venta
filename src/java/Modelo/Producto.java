/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author gateway1
 */
public class Producto {
    private int producto, clave_combinacion,clave_corrida,clave_linea,clave_clasificacion,estilo;

    public int getEstilo() {
        return this.estilo;
    }

    public void setEstilo(int estilo) {
        this.estilo = estilo;
    }
    private float costof, costom;

    public int getProducto() {
        return this.producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getClave_combinacion() {
        return this.clave_combinacion;
    }

    public void setClave_combinacion(int clave_combinacion) {
        this.clave_combinacion = clave_combinacion;
    }

    public int getClave_corrida() {
        return this.clave_corrida;
    }

    public void setClave_corrida(int clave_corrida) {
        this.clave_corrida = clave_corrida;
    }

    public int getClave_linea() {
        return this.clave_linea;
    }

    public void setClave_linea(int clave_linea) {
        this.clave_linea = clave_linea;
    }

    public int getClave_clasificacion() {
        return this.clave_clasificacion;
    }

    public void setClave_clasificacion(int clave_clasificacion) {
        this.clave_clasificacion = clave_clasificacion;
    }

    public float getCostof() {
        return this.costof;
    }

    public void setCostof(float costof) {
        this.costof = costof;
    }

    public float getCostom() {
        return this.costom;
    }

    public void setCostom(float costom) {
        this.costom = costom;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    private String tipo,combinacionchar,corridachar;

    public String getCorridachar() {
        return this.corridachar;
    }

    public void setCorridachar(String corridachar) {
        this.corridachar = corridachar;
    }

    public String getCombinacionchar() {
        return this.combinacionchar;
    }

    public void setCombinacionchar(String combinacionchar) {
        this.combinacionchar = combinacionchar;
    }
}
