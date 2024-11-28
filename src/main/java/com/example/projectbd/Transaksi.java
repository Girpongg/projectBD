package com.example.projectbd;

public class Transaksi {
    private int transaksi_id, nominal;
    private String tanggalPembayaran;
    private String metodeId;

    public Transaksi(int transaksi_id, int nominal, String tanggalPembayaran, String metodeId) {
        this.transaksi_id = transaksi_id;
        this.nominal = nominal;
        this.tanggalPembayaran = tanggalPembayaran;
        this.metodeId = metodeId;
    }

    public int getTransaksi_id() {
        return transaksi_id;
    }

    public void setTransaksi_id(int transaksi_id) {
        this.transaksi_id = transaksi_id;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(String tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public String getMetodeId() {
        return metodeId;
    }

    public void setMetodeId(String metodeId) {
        this.metodeId = metodeId;
    }
}
