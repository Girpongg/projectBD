package com.example.projectbd;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.time.LocalDate;

public class Promo {
    private String id;
    private String nama;
    private String start;
    private String end;

    private int potongan;

    public Promo(String id, String nama, String start, String end,int potongan) {
        this.id = id;
        this.nama = nama;
        this.start = start;
        this.end = end;
        this.potongan=potongan;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getPotongan() {
        return potongan;
    }

    public void setPotongan(int potongan) {
        this.potongan = potongan;
    }
}
