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
import model.Tagihan;
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
        
        
        for(Kompetensi item : tempData.daftarKompetensiUtama){
            System.out.println("id : "+item.getId() +"  hi -> "+item.getPrasyarat().toString());
        }
        
        
        for(Pekerjaan item : tempData.daftarPekerjaanUtama){
            System.out.println("id p : "+item.getNama());
        }
    }
}

class dataOfMahasiswaForOptimasi2 {
    String nim;
    String nama;
    ArrayList<Kompetensi> thisMahasiswaKompetensi;
    Tagihan thisMahasiswaTagihan;
}

class dbOfPekerjaanAndKompetensi{
    ArrayList<Kompetensi> daftarKompetensiUtama;
    ArrayList<Pekerjaan> daftarPekerjaanUtama;

    public dbOfPekerjaanAndKompetensi() {
        this.daftarKompetensiUtama = getKompetensiListFromJSON();
        this.daftarPekerjaanUtama = getPekerjaanListFromJSON();
    }
    
    public ArrayList<Pekerjaan> getPekerjaanListFromJSON(){
        JSONObject root = new JSONObject();
        JSONArray pekerjaan = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        ArrayList<Pekerjaan> listPekerjaanToReturn = new ArrayList<Pekerjaan>();
        
        File f = new File(ConfigDirektori.direktoriPekerjaan);
        
        if (f.exists() && !f.isDirectory()) {
        
            try {
                //JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriPekerjaan));

                JSONObject jsonObject = (JSONObject) obj;
                //JSONObject jsonObject2 = (JSONObject) jsonObject.get("kompetensi");
                array = (JSONArray) jsonObject .get("pekerjaan");

                for (int i = 0; i < array.size(); i++) {
                    
                    Pekerjaan newPekerjaanToAdd = new Pekerjaan();
                    JSONObject itemArr = (JSONObject)array.get(i);
                    
                    newPekerjaanToAdd.setId((String) itemArr.get("id"));
                    newPekerjaanToAdd.setNama((String) itemArr.get("nama"));
                    newPekerjaanToAdd.setListKompetensi((JSONArray) itemArr.get("kompetensi"));
                    
                    listPekerjaanToReturn.add(newPekerjaanToAdd);
                }
            } catch (FileNotFoundException e) {
             e.printStackTrace();
            } catch (IOException e) {
             e.printStackTrace();
            } catch (ParseException ex) {
             Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
    
        return listPekerjaanToReturn;
    }
    
    public ArrayList<Kompetensi> getKompetensiListFromJSON(){
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

class dbOfMahasiswa{
    ArrayList<Mahasiswa> daftarMahasiswaUtama;

    public dbOfMahasiswa() {
        this.daftarMahasiswaUtama = getMahasiswaListFromJSON();
    }
    public ArrayList<Mahasiswa> getMahasiswaListFromJSON(){
     
        JSONObject root = new JSONObject();
        JSONArray kompetensi= new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        ArrayList<Mahasiswa> listMahasiswaToReturn = new ArrayList<Mahasiswa>();

        File f = new File(ConfigDirektori.direktoriAkun);
        
        if (f.exists() && !f.isDirectory()) {
            try {
                //JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriAkun));

                JSONObject jsonObject = (JSONObject) obj;
                //JSONObject jsonObject2 = (JSONObject) jsonObject.get("kompetensi");
                array = (JSONArray) jsonObject .get("users");

                for (int i = 0; i < array.size(); i++) {
                    JSONObject itemArr = (JSONObject)array.get(i);
                    long roles = (long) itemArr.get("role");
            
                    if(roles ==  1){
                        Mahasiswa newMahasiswaToAdd = new Mahasiswa();
                        
                        newMahasiswaToAdd.setUsername((String) itemArr.get("username"));
                        newMahasiswaToAdd.setPassword((String) itemArr.get("password"));
                        newMahasiswaToAdd.setRole((int) itemArr.get("role"));
                        
                        ArrayList<String> listOfMahasiswaIdKompetensi = (JSONArray) itemArr.get("kompetensi");
                        ArrayList<String> listOfMahasiswaIdPekerjaan = (JSONArray) itemArr.get("pekerjaan");
                        
                        ArrayList<Kompetensi> listOfMahasiswaObjectKompetensi = new ArrayList<Kompetensi>();
                        ArrayList<Pekerjaan> listOfMahasiswaObjectPekerjaan = new ArrayList<Pekerjaan>();
                        
                        for(String item : listOfMahasiswaIdKompetensi){
                            
                        }
                        
                        listMahasiswaToReturn.add(newMahasiswaToAdd);
                    }
                    
                }
                
            } catch (FileNotFoundException e) {
             e.printStackTrace();
            } catch (IOException e) {
             e.printStackTrace();
            } catch (ParseException ex) {
             Logger.getLogger(Mahasiswa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return listMahasiswaToReturn;
    
    }
}
