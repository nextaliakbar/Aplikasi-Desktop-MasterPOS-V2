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
public class PemeriksaanSementara {

    public PemeriksaanSementara(String kodeTindakan, Integer biayaTindakanFinal, Integer potongan, Integer subtotal) {
        this.kodeTindakan = kodeTindakan;
        this.biayaTindakanFinal = biayaTindakanFinal;
        this.potongan = potongan;
        this.subtotal = subtotal;
    }

    

    public PemeriksaanSementara() {
    
    }
    
    private String kodeTindakan;
    private Integer biayaTindakanFinal;
    private Integer potongan;
    private Integer subtotal;

    public String getKodeTindakan() {
        return kodeTindakan;
    }

    public void setKodeTindakan(String kodeTindakan) {
        this.kodeTindakan = kodeTindakan;
    }

    public Integer getBiayaTindakanFinal() {
        return biayaTindakanFinal;
    }

    public void setBiayaTindakanFinal(Integer biayaTindakanFinal) {
        this.biayaTindakanFinal = biayaTindakanFinal;
    }

    public Integer getPotongan() {
        return potongan;
    }

    public void setPotongan(Integer potongan) {
        this.potongan = potongan;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
    
    
    
}
