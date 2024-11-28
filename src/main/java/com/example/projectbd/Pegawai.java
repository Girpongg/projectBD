package com.example.projectbd;

import java.time.LocalDate;

public class Pegawai {
    private String Pegawai_id;
    private String First_name;
    private String lastname;
    private String hire_date;
    private String notelp;
    private int Salary;
    private String job_id;

    public Pegawai(String pegawai_id, String first_name, String lastname, String hire_date, String notelp, int salary, String job_id) {
        Pegawai_id = pegawai_id;
        First_name = first_name;
        this.lastname = lastname;
        this.hire_date = hire_date;
        this.notelp = notelp;
        Salary = salary;
        this.job_id = job_id;
    }

    public String getPegawai_id() {
        return Pegawai_id;
    }

    public void setPegawai_id(String pegawai_id) {
        Pegawai_id = pegawai_id;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }
}