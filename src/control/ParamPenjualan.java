/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.List;

/**
 *
 * @author usER
 */
public class ParamPenjualan {

    public ParamPenjualan(String tglJam, String noPenjualan, String total, String bayar, String kembali, String jenis, List<FieldsPenjualan> fields) {
        this.tglJam = tglJam;
        this.noPenjualan = noPenjualan;
        this.total = total;
        this.bayar = bayar;
        this.kembali = kembali;
        this.jenis = jenis;
        this.fields = fields;
    }

    public ParamPenjualan() {
    }
    
    
    private String tglJam;
    private String noPenjualan;
    private String total;
    private String bayar;
    private String kembali;
    private String jenis;
    private List<FieldsPenjualan> fields;

    public String getTglJam() {
        return tglJam;
    }

    public void setTglJam(String tglJam) {
        this.tglJam = tglJam;
    }

    public String getNoPenjualan() {
        return noPenjualan;
    }

    public void setNoPenjualan(String noPenjualan) {
        this.noPenjualan = noPenjualan;
    }
    
    public List<FieldsPenjualan> getFields() {
        return fields;
    }
    
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getBayar() {
        return bayar;
    }

    public void setBayar(String bayar) {
        this.bayar = bayar;
    }

    public String getKembali() {
        return kembali;
    }

    public void setKembali(String kembali) {
        this.kembali = kembali;
    }


    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public void setFields(List<FieldsPenjualan> fields) {
        this.fields = fields;
    }
}
