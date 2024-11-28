package com.example.projectbd;

public class Item {
    private String item;
    private String namaitem;
    private int harga;
    private String size;
    private String idkategori;

    public Item(String item, String namaitem, int harga, String size, String idkategori) {
        this.item = item;
        this.namaitem = namaitem;
        this.harga = harga;
        this.size = size;
        this.idkategori = idkategori;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNamaitem() {
        return namaitem;
    }

    public void setNamaitem(String namaitem) {
        this.namaitem = namaitem;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getIdkategori() {
        return idkategori;
    }

    public void setIdkategori(String idkategori) {
        this.idkategori = idkategori;
    }
}
