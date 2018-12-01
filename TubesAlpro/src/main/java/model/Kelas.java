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

    public void setDaftarPeserta(ArrayList<Mahasiswa> daftarPeserta) {
        this.daftarPeserta = daftarPeserta;
    }

    public int getTarifPerPeserta() {
        return tarifPerPeserta;
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

    public void TulisKelasToJson() {
        JSONObject jsonRoot = new JSONObject();
        JSONArray daftarPeserta = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray tempArray = null;

        File f = new File(ConfigDirektori.direktoriKelas);

        if (f.exists() && !f.isDirectory()) {

            try {
                JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKelas));
                tempArray = (JSONArray) objFromFile.get("kelas");
                //array = (JSONArray) read;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        //Create JSONObject and JSONArray and store a class object on it
        JSONObject uo = new JSONObject();
        
        uo.put("namaKelas", namaKelas);
        uo.put("tarifPerPeserta", tarifPerPeserta);
        uo.put("pendapatanTotal",pendapatanTotal);

        JSONArray list = new JSONArray();
        for (int i = 0; i < daftarPeserta.size(); i++) {
            list.add(daftarPeserta.get(i).());
        }
        uo.put("kompetensi", list);

        if (f.exists() && !f.isDirectory()) {
            tempArray.add(uo);

            // add the array to the root object
            jsonRoot.put("pekerjaan", tempArray);
        } else {
            pekerjaan.add(uo);
            
            // add the array to the root object
            jsonRoot.put("pekerjaan", pekerjaan);
        }

        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriPekerjaan)) {
            file.write(jsonRoot.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
