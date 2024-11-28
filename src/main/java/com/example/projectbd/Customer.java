package com.example.projectbd;

public class Customer {
    private String id;
    private String First_name;

    private String Last_name;
    private String alamat;
    private int no_wa;

    public Customer(String id, String first_name, String last_name, String alamat, int no_wa) {
        this.id = id;
        First_name = first_name;
        Last_name = last_name;
        this.alamat = alamat;
        this.no_wa = no_wa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getNo_wa() {
        return no_wa;
    }

    public void setNo_wa(int no_wa) {
        this.no_wa = no_wa;
    }
}
