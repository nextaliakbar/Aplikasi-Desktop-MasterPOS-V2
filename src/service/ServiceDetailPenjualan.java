/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import control.FieldsPemeriksaan;
import control.FieldsPenjualan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.ModelDetailPenjualan;
/**
 *
 * @author usER
 */
public class ServiceDetailPenjualan {
    private Connection connection;
    private final DecimalFormat df = new DecimalFormat("#,##0.##");
    
    public ServiceDetailPenjualan() {
        connection = Koneksi.getConnection();
    }
    
    public void loadData(ModelDetailPenjualan modelDetail, DefaultTableModel tabmodel) {
        String query = "SELECT dtl.Kode_Barang, dtl.Harga_Jual_Final, brg.Nama_Barang, brg.Satuan, brg.Harga_Jual, "
                + "dtl.Jumlah, dtl.Subtotal FROM detail_penjualan dtl JOIN barang brg "
                + "ON dtl.Kode_Barang=brg.Kode_Barang WHERE No_Penjualan='"+modelDetail.getModelPenjualan().getNoPenjualan()+"' ";
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rst = pst.executeQuery();
            while(rst.next()) {
                String kodeBrg = rst.getString("Kode_Barang");
                String namaBrg = rst.getString("Nama_Barang");
                String satuan = rst.getString("Satuan");
                int hrgJualFinal = rst.getInt("Harga_Jual_Final");
                int hrgJual = rst.getInt("Harga_Jual");
                int jumlah = rst.getInt("Jumlah");
                int subtotal = rst.getInt("Subtotal");
                
                StringBuilder builder = new StringBuilder();
                if(hrgJualFinal != hrgJual) {
                    builder.append(" (Harga Sebelum = ")
                            .append(hrgJualFinal)
                            .append(")");
                }
                
                tabmodel.addRow(new Object[]{kodeBrg, namaBrg, satuan, df.format(hrgJual).concat(builder.toString()), jumlah, df.format(subtotal)});
            }
            rst.close();
            pst.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public List<FieldsPenjualan> getData(String noPenjualan) {
        String query = "SELECT dtl.Harga_Jual_Final, brg.Nama_Barang,"
                + "dtl.Jumlah, dtl.Subtotal FROM detail_penjualan dtl JOIN barang brg "
                + "ON dtl.Kode_Barang=brg.Kode_Barang WHERE No_Penjualan='"+noPenjualan+"' ";
        List<FieldsPenjualan> fields = new ArrayList<>();
        try(PreparedStatement pst = connection.prepareStatement(query);
                ResultSet rst = pst.executeQuery()) {
            
            while(rst.next()) {
                String product = rst.getString("Nama_Barang");
                String price = rst.getString("Harga_Jual_Final");
                Integer qty = rst.getInt("Jumlah");
                String subtotal = rst.getString("Subtotal");
                fields.add(new FieldsPenjualan(product, df.format(Integer.parseInt(price)), qty, df.format(Integer.parseInt(subtotal))));
                
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        return fields;
    }
}
