package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private String nipDosen;
    private String namaDosen;
    private ArrayList<String> daftarPeserta = new ArrayList<String>();
    private double tarifPerPeserta;
    private double pendapatanTotal;

    public Kelas() {
    }

    public Kelas(String namaKelas, String nipDosen, String namaDosen, float tarifPerPeserta, double pendapatanTotal) {
        this.namaKelas = namaKelas;
        this.nipDosen = nipDosen;
        this.namaDosen = namaDosen;
        this.tarifPerPeserta = tarifPerPeserta;
        this.pendapatanTotal = pendapatanTotal;
    }


    public String getNamaKelas() {
        return namaKelas;
    }

    public void setNamaKelas(String namaKelas) {
        this.namaKelas = namaKelas;
    }

    public ArrayList<String> getDaftarPeserta() {
        return daftarPeserta;
    }

    public double getTarifPerPeserta() {
        return tarifPerPeserta;
    }
    
    public void setTarifPerPeserta(double tarifPerPeserta) {
        this.tarifPerPeserta = tarifPerPeserta;
    }

    public double getPendapatanTotal() {
        return pendapatanTotal;
    }

    public void setPendapatanTotal(double pendapatanTotal) {
        this.pendapatanTotal = pendapatanTotal;
    }

    public String getNipDosen() {
        return nipDosen;
    }

    public void setNipDosen(String nipDosen) {
        this.nipDosen = nipDosen;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public void TulisKelasToJson() {
        JSONObject root = new JSONObject();
        JSONArray kelas = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        File f = new File(ConfigDirektori.direktoriKelas);

        if (f.exists() && !f.isDirectory()) {

            try {
                JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKelas));
                array = (JSONArray) objFromFile.get("kelas");
                //array = (JSONArray) read;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(Kelas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Create JSONObject and JSONArray and store a class object on it
        JSONObject uo = new JSONObject();
        //JSONObject ro = new JSONObject();
        uo.put("namaKelas", namaKelas);
        uo.put("nipDosen", nipDosen);
        uo.put("namaDosen", namaDosen);

        JSONArray listOfPeserta = new JSONArray();
        for (int i = 0; i < daftarPeserta.size(); i++) {
            listOfPeserta.add(daftarPeserta.get(i));
        }
        
        uo.put("daftarPeserta", daftarPeserta);
        uo.put("tarifPerPeserta", tarifPerPeserta);
        uo.put("pendapatanTotal", pendapatanTotal);
        
        if (f.exists() && !f.isDirectory()) {
            array.add(uo);
            // add the array to the root object
            root.put("kelas", array);

        } else {
            kelas.add(uo);
            // add the array to the root object
            root.put("kelas", kelas);
        }

        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriKelas)) {
            file.write(root.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void ReadKelasFromJson() {
        JSONObject root = new JSONObject();
        JSONArray kelas = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        File f = new File(ConfigDirektori.direktoriKelas);

        if (f.exists() && !f.isDirectory()) {
            try {
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriKelas));

                JSONObject jsonObject = (JSONObject) obj;
                array = (JSONArray) jsonObject.get("kelas");

                String tbl = "| %-3s | %-14s | %-48s | %-28s | %-7s |%n";
                System.out.format("%nDaftar Kelas%n");
                System.out.format("+-----+--------------+---------------+---------------+-------------------+------------------+-------------+----------------|%n");
                System.out.format("| No  |  Kompetensi  | NIP Dosen     | Nama Dosen    | Tarif per Peserta | Total Keuntungan | Jml Peserta | Daftar Peserta %n");
                System.out.format("+-----|--------------+---------------+---------------+-------------------+------------------+-------------+----------------|%n");
               
                for (int i = 0; i < array.size(); i++) {
                    
                    // get all JSON Object
                    JSONObject itemArr = (JSONObject) array.get(i);
                    
                    String namaKelas = (String) itemArr.get("namaKelas");
                    String nipDosen = (String) itemArr.get("nipDosen");
                    String namaDosen = (String) itemArr.get("namaDosen");
                    JSONArray daftarPeserta = (JSONArray) itemArr.get("daftarPeserta");
                    double tarifPerPeserta = (double) itemArr.get("tarifPerPeserta");
                    double pendapatanTotal = (double) itemArr.get("pendapatanTotal");
                    
                    System.out.format(tbl, i + 1, namaKelas, nipDosen, namaDosen,tarifPerPeserta,pendapatanTotal,daftarPeserta);
                }
                System.out.format("+-----|--------------+---------------+---------------+-------------------+------------------+-------------+----------------%n");
                System.out.println("");
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(Kelas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


    String idMahasiswa;
    String namaMahasiswa;
    long jumlahTagihan;
    boolean statusPembayaran;
    private ArrayList<String> kompetensiDiambil;
    private ArrayList<Long> tarifKompetensiDiambil;