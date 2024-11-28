package com.example.projectbd;

public class Pesanan {

    private int Pesanan_id,Customer_id,Pegawai_id,Item_id,Total_harga;
    private String radius, layanan, kondisi, pencucian, status, tgl_pesan,tgl_kembali;

    public Pesanan(int pesanan_id, int customer_id, int pegawai_id, int item_id, int total_harga, String radius, String layanan, String kondisi, String pencucian, String status, String tgl_pesan, String tgl_kembali) {
        Pesanan_id = pesanan_id;
        Customer_id = customer_id;
        Pegawai_id = pegawai_id;
        Item_id = item_id;
        Total_harga = total_harga;
        this.radius = radius;
        this.layanan = layanan;
        this.kondisi = kondisi;
        this.pencucian = pencucian;
        this.status = status;
        this.tgl_pesan = tgl_pesan;
        this.tgl_kembali = tgl_kembali;
    }

    public int getPesanan_id() {
        return Pesanan_id;
    }

    public void setPesanan_id(int pesanan_id) {
        Pesanan_id = pesanan_id;
    }

    public int getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(int customer_id) {
        Customer_id = customer_id;
    }

    public int getPegawai_id() {
        return Pegawai_id;
    }

    public void setPegawai_id(int pegawai_id) {
        Pegawai_id = pegawai_id;
    }

    public int getItem_id() {
        return Item_id;
    }

    public void setItem_id(int item_id) {
        Item_id = item_id;
    }

    public int getTotal_harga() {
        return Total_harga;
    }

    public void setTotal_harga(int total_harga) {
        Total_harga = total_harga;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public String getLayanan() {
        return layanan;
    }

    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getPencucian() {
        return pencucian;
    }

    public void setPencucian(String pencucian) {
        this.pencucian = pencucian;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTgl_pesan() {
        return tgl_pesan;
    }

    public void setTgl_pesan(String tgl_pesan) {
        this.tgl_pesan = tgl_pesan;
    }

    public String getTgl_kembali() {
        return tgl_kembali;
    }

    public void setTgl_kembali(String tgl_kembali) {
        this.tgl_kembali = tgl_kembali;
    }
}
