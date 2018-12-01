package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mahasiswa;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    private String namaKelas;
    private ArrayList<Mahasiswa> daftarPeserta = new ArrayList<Mahasiswa>();
    private int tarifPerPeserta;
    private double pendapatanTotal;

    public Kelas() {
    }
    
    public Kelas(String namaKelas, int tarifPerPeserta, double pendapatanTotal) {
        this.namaKelas = namaKelas;
        this.tarifPerPeserta = tarifPerPeserta;
        this.pendapatanTotal = pendapatanTotal;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public ArrayList<Mahasiswa> getDaftarPeserta() {
        return daftarPeserta;
    }

    
    public void setTarifPerPeserta(int tarifPerPeserta) {
        this.tarifPerPeserta = tarifPerPeserta;
    }

    public double getPendapatanTotal() {
        return pendapatanTotal;
    }

    public void setPendapatanTotal(double pendapatanTotal) {
        this.pendapatanTotal = pendapatanTotal;
    }
}