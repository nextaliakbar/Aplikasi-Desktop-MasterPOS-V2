/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import control.FieldsPemeriksaan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.ModelDetailPemeriksaan;
/**
 *
 * @author usER
 */
public class ServiceDetailPemeriksaan {
    
    private Connection connection;
    private final DecimalFormat df = new DecimalFormat("#,##0.##");
    public ServiceDetailPemeriksaan() {
        connection = Koneksi.getConnection();
    }
    
    public void loadData(DefaultTableModel tabModel, ModelDetailPemeriksaan detail) {
        String query = "SELECT detPem.Kode_Tindakan, tdk.Nama_Tindakan, tdk.Biaya_Tindakan, detPem.Biaya_Tindakan_Final, "
                + "detPem.Potongan, detPem.Subtotal FROM detail_pemeriksaan detPem INNER JOIN "
                + "tindakan tdk ON detPem.Kode_Tindakan=tdk.Kode_Tindakan WHERE No_Pemeriksaan='"+detail.getModelPemeriksaan().getNoPemeriksaan()+"'";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                String kode = rst.getString("Kode_Tindakan");
                String nama = rst.getString("Nama_Tindakan");
                int hargaFinal = rst.getInt("Biaya_Tindakan_Final");
                int harga = rst.getInt("Biaya_Tindakan");
                StringBuilder stringBuilder = new StringBuilder();  
                
                if(hargaFinal != harga) {
                    stringBuilder.append("  ( Harga Sebelum = ")
                            .append(df.format(hargaFinal))
                            .append(" )");
                }
                String info = stringBuilder.toString();
                int potongan = rst.getInt("Potongan");
                int totalHarga = rst.getInt("Subtotal");
                
                tabModel.addRow(new Object[]{kode, nama, df.format(harga).concat(info), df.format(potongan), df.format(totalHarga)});
            }
            
            pst.close();
            rst.close();
            
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public List<FieldsPemeriksaan> getData(String noPemeriksaan) {
        String query = "SELECT * FROM detail_pemeriksaan WHERE No_Pemeriksaan='"+noPemeriksaan+"'";
        List<FieldsPemeriksaan> fields = new ArrayList<>();
        try(PreparedStatement pst = connection.prepareStatement(query);
                ResultSet rst = pst.executeQuery()) {
            
            while(rst.next()) {
                String kode = rst.getString("Kode_Tindakan");
                int hargaFinal = rst.getInt("Biaya_Tindakan_Final");
                int potongan = rst.getInt("Potongan");
                int totalHarga = rst.getInt("Subtotal");
                fields.add(new FieldsPemeriksaan(kode, df.format(hargaFinal), df.format(potongan), df.format(totalHarga)));
                
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return fields;
    }
}
