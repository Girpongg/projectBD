package com.example.projectbd;

public class Kategori {
    private String Kategori_id;
    private String Nama_Kategori;
    private String Promo_id;

    public Kategori(String kategori_id, String nama_Kategori, String promo_id) {
        Kategori_id = kategori_id;
        Nama_Kategori = nama_Kategori;
        Promo_id = promo_id;
    }

    public String getKategori_id() {
        return Kategori_id;
    }

    public void setKategori_id(String kategori_id) {
        Kategori_id = kategori_id;
    }

    public String getNama_Kategori() {
        return Nama_Kategori;
    }

    public void setNama_Kategori(String nama_Kategori) {
        Nama_Kategori = nama_Kategori;
    }

    public String getPromo_id() {
        return Promo_id;
    }

    public void setPromo_id(String promo_id) {
        Promo_id = promo_id;
    }
}
