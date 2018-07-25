/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author gateway1
 */
public class Corrida implements Serializable{
    int corrida,pi,pf;
    String desc;

    public int getCorrida() {
        return this.corrida;
    }

    public void setCorrida(int corrida) {
        this.corrida = corrida;
    }

    public int getPi() {
        return this.pi;
    }

    public void setPi(int pi) {
        this.pi = pi;
    }

    public int getPf() {
        return this.pf;
    }

    public void setPf(int pf) {
        this.pf = pf;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
