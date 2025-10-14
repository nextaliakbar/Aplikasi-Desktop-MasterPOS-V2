/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import component.Chart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import util.ModelChart;
import model.ModelDashboard;
import model.ModelLastReservasi;
import swing.StatusType;
import swing.Table;
/**
 *
 * @author usER
 */
public class ServiceDashboard {
    private Connection connection;
    
    public ServiceDashboard() {
        connection = Koneksi.getConnection();
    }
    
    public double pendapatanPemeriksaan(ModelDashboard modelDashboard) {
        double revenue = 0;
        String sql = "SELECT SUM(total) AS Total FROM pemeriksaan WHERE Tanggal_Pemeriksaan BETWEEN ? AND ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, modelDashboard.getFromDate());
            pst.setString(2, modelDashboard.getToDate());
            try (ResultSet rst = pst.executeQuery()) {
                if (rst.next()) {
                    revenue = rst.getDouble("Total");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return revenue;
                        
    }
    
    public double totalPenjualan(ModelDashboard modelDashboard) {
        double total = 0;
        String sql = "SELECT SUM(Total_Penjualan) AS Total FROM penjualan WHERE Tanggal BETWEEN ? AND ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, modelDashboard.getFromDate());
            pst.setString(2, modelDashboard.getToDate());
            try (ResultSet rst = pst.executeQuery()) {
                if (rst.next()) {
                    total = rst.getDouble("Total");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }
    
    public double pengeluaran(ModelDashboard modelDashboard) {
        double pengeluaran = 0;
        String sql = "SELECT SUM(Total_Pengeluaran) AS Total FROM pengeluaran WHERE Tanggal_Pengeluaran BETWEEN ? AND ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, modelDashboard.getFromDate());
            pst.setString(2, modelDashboard.getToDate());
            try (ResultSet rst = pst.executeQuery()) {
                if (rst.next()) {
                    pengeluaran = rst.getDouble("Total");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pengeluaran;
    }
    
    public double keuntunganPenjualan(ModelDashboard modelDashboard) {
        double keuntungan = 0;
        String sql = "SELECT SUM(Total_Keuntungan) AS Total FROM penjualan WHERE Tanggal BETWEEN ? AND ?";
        try (PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, modelDashboard.getFromDate());
            pst.setString(2, modelDashboard.getToDate());
            try (ResultSet rst = pst.executeQuery()) {
                if (rst.next()) {
                    keuntungan = rst.getDouble("Total");
                }
            }
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        return keuntungan;
    }
    
    public void lastReseravsi(Table table) {
        String sql = "SELECT rsv.No_Reservasi, rsv.ID_Pasien, psn.Nama, psn.Jenis_Kelamin, rsv.Tanggal_Kedatangan, "
                + "rsv.Jam_Kedatangan, rsv.Status_Reservasi FROM reservasi rsv INNER JOIN pasien psn "
                + "ON rsv.ID_Pasien=psn.ID_Pasien ORDER BY (rsv.Tanggal_Kedatangan || ' ' || rsv.Jam_Kedatangan) DESC";

        try (PreparedStatement pst = connection.prepareStatement(sql);
             ResultSet rst = pst.executeQuery()) {
            
            while (rst.next()) {
                String noReservasi = rst.getString("No_Reservasi");
                String nama = rst.getString("Nama");
                String jenisKelamin = rst.getString("Jenis_Kelamin");
                String tglKedatangan = rst.getString("Tanggal_Kedatangan");
                String jamKedatangan = rst.getString("Jam_Kedatangan");
                String status = rst.getString("Status_Reservasi");
                String sourceImage = "/image/businesswoman.png";
                
                if (jenisKelamin.equals("Laki-laki")) {
                    sourceImage = "/image/office-worker.png";
                }

                StatusType type;
                if (status.equals("Menunggu")) {
                    type = StatusType.Wait;
                } else if (status.equals("Selesai")) {
                    type = StatusType.Finish;
                } else {
                    type = StatusType.Cancel;
                }
                
                table.addRow(new ModelLastReservasi(noReservasi,new ImageIcon(getClass().getResource(sourceImage)), nama, tglKedatangan.concat(" / " + jamKedatangan),type).toRowTable());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    private List<Double> getRevenues(List<Integer> months, String year) {
        List<Double> revenues = new ArrayList<>();
        String sql = "SELECT SUM(Total) AS Revenues FROM pemeriksaan WHERE strftime('%m', Tanggal_Pemeriksaan) = ? AND strftime('%Y', Tanggal_Pemeriksaan) = ?";
        for (int month : months) {
            double revenue = 0;
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                // Format bulan menjadi 2 digit (misal: 1 -> "01") agar cocok dengan strftime('%m')
                pst.setString(1, String.format("%02d", month));
                pst.setString(2, year);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        revenue = rst.getDouble("Revenues");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            revenues.add(revenue);
        }
        return revenues;
    }
    
    private List<Double> getTotalSells(List<Integer> months, String year) {
        List<Double> totalSells = new ArrayList<>();
        String sql = "SELECT SUM(Total_Penjualan) AS TotalSells FROM penjualan WHERE strftime('%m', Tanggal) = ? AND strftime('%Y', Tanggal) = ?";
        for (int month : months) {
            double totalSell = 0;
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setString(1, String.format("%02d", month));
                pst.setString(2, year);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        totalSell = rst.getDouble("TotalSells");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            totalSells.add(totalSell);
        }
        return totalSells;
    }
    
    private List<Double> getExpenditures(List<Integer> months, String year) {
        List<Double> expenditures = new ArrayList<>();
        String sql = "SELECT SUM(Total_Pengeluaran) AS Expenditures FROM pengeluaran WHERE strftime('%m', Tanggal_Pengeluaran) = ? AND strftime('%Y', Tanggal_Pengeluaran) = ?";
        for (int month : months) {
            double expenditure = 0;
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setString(1, String.format("%02d", month));
                pst.setString(2, year);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        expenditure = rst.getDouble("Expenditures");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            expenditures.add(expenditure);
        }
        return expenditures;
    }
        
    private List<Double> getProfits(List<Integer> months, String year) {
        List<Double> profits = new ArrayList<>();
        String sql = "SELECT SUM(dtl.Subtotal) - SUM(brg.Harga_Beli * dtl.Jumlah) AS Keuntungan "
                + "FROM detail_penjualan dtl INNER JOIN penjualan pjn "
                + "ON dtl.No_Penjualan = pjn.No_Penjualan INNER JOIN barang brg "
                + "ON dtl.Kode_Barang = brg.Kode_Barang WHERE strftime('%m', pjn.Tanggal) = ? "
                + "AND strftime('%Y', pjn.Tanggal) = ?";
        for (int month : months) {
            double profit = 0;
            try (PreparedStatement pst = connection.prepareStatement(sql)) {
                pst.setString(1, String.format("%02d", month));
                pst.setString(2, year);
                try (ResultSet rst = pst.executeQuery()) {
                    if (rst.next()) {
                        profit = rst.getDouble("Keuntungan");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            profits.add(profit);
        }
        return profits;
    }
    
    
    
    public void chartDiagram(Chart chart, String year, int index) {
        List<Integer> months = new ArrayList<>();
        
        for(int a = 1; a <= 12; a++) {
            months.add(a);
        }
        
        List<Double> revenues = getRevenues(months, year);
        List<Double> totalSells = getTotalSells(months, year);
        List<Double> expenditures = getExpenditures(months, year);
        List<Double> profits = getProfits(months, year);
        for(int a = 0; a < 12; a++) {
            Month month = Month.of(a+1);
            String bulan = styleString(month.toString());
            double[] values1 = {revenues.get(a), totalSells.get(a), expenditures.get(a)};
            double[] values2 = {profits.get(a)};
            if(index == 0) {
                chart.addData(new ModelChart(bulan, values1));
            } else {
                chart.addData(new ModelChart(bulan, values2));
            }
        }
    }
    
    private String styleString(String str) {
        String firstStr = str.substring(0, 1);
        String otherStr =  str.substring(1);
        return firstStr + otherStr.toLowerCase();
    }
}
