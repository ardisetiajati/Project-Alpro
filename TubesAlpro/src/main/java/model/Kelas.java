package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ardiansyah Setiajati
 */
public class Kelas {
    private int id;
    private String nama;
    private String[] jam;

    public Kelas() {
    }
    

    public Kelas(int id, String nama, String[] jam) {
        this.id = id;
        this.nama = nama;
        this.jam = jam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String[] getJam() {
        return jam;
    }

    public void setJam(String[] jam) {
        this.jam = jam;
    }
}
