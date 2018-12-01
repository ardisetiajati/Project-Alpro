package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ConfigDirektori;
import model.Kelas;
import model.Kompetensi;
import model.Mahasiswa;
import model.Pekerjaan;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.ViewKelas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ardhi Rizki Harahap
 */
public class ControllerKelas {
    Kelas kelas;
    ViewKelas viewKelas;
    Mahasiswa mahasiswa;
    
    public void ControlMenuOptimasi(){
        viewKelas = new ViewKelas();
        viewKelas.menuOptimasi();
        
        switch(viewKelas.getPilihan()){
            case 1:
                Optimasi1();
                break;
            case 2:
                Optimasi2();
                break;
        }
    }
    
    public void Optimasi1(){
        mahasiswa = new Mahasiswa();
        ArrayList<String> list = mahasiswa.getListMahasiswa();   
    }
    
    public void Optimasi2(){
        ArrayList<neededDataForOptimasi2> dataNimKompetensi = new ArrayList<neededDataForOptimasi2>();
        neededDataForOptimasi2 tempData = new neededDataForOptimasi2();
        
        
        ArrayList<Kompetensi> daftarKompetensiUtama = tempData.getKompetensiList();
       
        for(Kompetensi item : daftarKompetensiUtama){
            System.out.println("id : "+item.getId());
        }
    }
}

class neededDataForOptimasi2 {
    String nim;
    String nama;
    Kompetensi kompetensi;
    
    public ArrayList<Mahasiswa> getMahasiswaList(){
     
        return null;
    }
    
    public ArrayList<Pekerjaan> getPekerjaaanList(){
        
        return null;
    }
    
    public ArrayList<Kompetensi> getKompetensiList(){
        JSONObject root = new JSONObject();
        JSONArray kompetensi = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        
        ArrayList<Kompetensi> kompetensiListToReturn = new ArrayList<Kompetensi>();

        File f = new File(ConfigDirektori.direktoriKompetensi);

        if (f.exists() && !f.isDirectory()) {
            try {
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));

                JSONObject jsonObject = (JSONObject) obj;
                array = (JSONArray) jsonObject.get("kompetensi");

                 for (int i = 0; i < array.size(); i++) {
                    
                    Kompetensi kompetensiNewToAdd = new Kompetensi();

                    // get all JSON Object
                    JSONObject itemArr = (JSONObject) array.get(i);
                    
                    kompetensiNewToAdd.setId((String) itemArr.get("id"));
                    kompetensiNewToAdd.setNama((String) itemArr.get("nama"));
                    kompetensiNewToAdd.setBiaya((long)itemArr.get("biaya"));
                    kompetensiNewToAdd.setHasPraktikum( (boolean) itemArr.get("hasPraktikum"));
                    Long sks = (long)itemArr.get("sks");
                    kompetensiNewToAdd.setSks(sks.intValue()); 
                    kompetensiNewToAdd.setPrasyarat((JSONArray) itemArr.get("prasyarat"));
                    
                    kompetensiListToReturn.add(kompetensiNewToAdd);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return kompetensiListToReturn;
    }
}
