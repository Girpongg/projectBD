package com.example.projectbd;

public class Bulan{

    private  String bulan;
    private Integer Sales;

    public Bulan(String bulan, int sales) {
        this.bulan = bulan;
        Sales = sales;
    }


    public String getBulan() {
        return bulan;
    }

    public void setBulan(String bulan) {
        this.bulan = bulan;
    }

    public int getSales() {
        return Sales;
    }

    public void setSales(int sales) {
        Sales = sales;
    }
}
