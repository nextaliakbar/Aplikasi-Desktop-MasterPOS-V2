/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

/**
 *
 * @author usER
 */
public class FieldsPengeluaran {

    public FieldsPengeluaran(String type, String detailType, String subtotal) {
        this.type = type;
        this.detailType = detailType;
        this.subtotal = subtotal;
    }

    public FieldsPengeluaran() {
    }

    private String type;
    private String detailType;
    private String subtotal;

    public String getType() {
        return type;
    }

    public String getDetailType() {
        return detailType;
    }

    public String getSubtotal() {
        return subtotal;
    }
}
