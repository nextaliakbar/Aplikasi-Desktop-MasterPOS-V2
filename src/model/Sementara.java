/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author usER
 */
public class Sementara {

    public Sementara(String kodeBrg, Integer jumlah, Integer subtotal) {
        this.kodeBrg = kodeBrg;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }
    
    public Sementara(String kodeBrg, Integer hargaFinal, Integer jumlah, Integer subtotal) {
        this.kodeBrg = kodeBrg;
        this.hargaFinal = hargaFinal;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }
        

    public Sementara() {
    
    }
    
    private String kodeBrg;
    private Integer hargaFinal;
    private Integer jumlah;
    private Integer subtotal;

    public String getKodeBrg() {
        return kodeBrg;
    }

    public void setKodeBrg(String kodeBrg) {
        this.kodeBrg = kodeBrg;
    }

    public Integer getHargaFinal() {
        return hargaFinal;
    }

    public void setHargaFinal(Integer hargaFinal) {
        this.hargaFinal = hargaFinal;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
