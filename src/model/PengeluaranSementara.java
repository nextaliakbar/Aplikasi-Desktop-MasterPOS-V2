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
public class PengeluaranSementara {

    public PengeluaranSementara(String noJenis, String detailJenis, Integer subtotal) {
        this.noJenis = noJenis;
        this.detailJenis = detailJenis;
        this.subtotal = subtotal;
    }

    public PengeluaranSementara() {
    }
    
    private String noJenis;
    private String detailJenis;
    private Integer subtotal;

    public String getNoJenis() {
        return noJenis;
    }

    public void setNoJenis(String noJenis) {
        this.noJenis = noJenis;
    }

    public String getDetailJenis() {
        return detailJenis;
    }

    public void setDetailJenis(String detailJenis) {
        this.detailJenis = detailJenis;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }
    
    
}
