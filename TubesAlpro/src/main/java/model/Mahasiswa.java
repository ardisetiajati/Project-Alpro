package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

public class Mahasiswa extends User{
    //private String username;
    //private String password;
    private String nim;
    private String nama;
    private ArrayList<Pekerjaan> pekerjaan;
    private int jmlMatkulDiambil;
    private Kompetensi[] matkulDiambil;
    private int jmlMatkulDimiliki;
    private Kompetensi[] matkulDimiliki;
    private ArrayList<Kompetensi> kompetensi;

    public Mahasiswa(String username, String password, int role) {
        super(username, password, role);    
    }

    public Mahasiswa(String username, String nama) {
        this.username = username;
        this.nama = nama;
    }
    
    
    
    public Mahasiswa(String nim, String nama, ArrayList<Pekerjaan> pekerjaan, int jmlMatkulDiambil,ArrayList<Kompetensi> kompetensi, int jmlMatkulDimiliki, Kompetensi[] matkulDimiliki, String username, String password, int role) {
        super(username, password, role);
        this.nim = nim;
        this.nama = nama;
        this.pekerjaan = pekerjaan;
        this.jmlMatkulDiambil = jmlMatkulDiambil;
        this.kompetensi = kompetensi;
        this.jmlMatkulDimiliki = jmlMatkulDimiliki;
        this.matkulDimiliki = matkulDimiliki;
    }

    public Mahasiswa(String username, ArrayList<Kompetensi> kompetensi) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void EditUserMahasiswaFromJson(){
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            array = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            
            if(itemArr.get("username").equals(username)){
                itemArr.put("nama", nama);
            }       
            root.put("users",array);
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriAkun)) {

            file.write(root.toJSONString());
            file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void EditUserPasswordMahasiswaFromJson(){
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            array = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            
            if(itemArr.get("username").equals(username)){
                itemArr.put("password", password);
            }       
            root.put("users",array);
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriAkun)) {

            file.write(root.toJSONString());
            file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void EditKompetensiFromJson(){
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            array = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            
            if(itemArr.get("username").equals(username)){
                if ( kompetensi != null) {
                    
                JSONArray list = new JSONArray();
                    for (int j = 0; j < kompetensi.size(); j++) {
                        list.add(kompetensi.get(j).getId());
                    }
                    itemArr.put("kompetensi", list);
                    //itemArr.put("prasyarat", prasyarat);
                }   
            }       
            root.put("users",array);
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriAkun)) {

            file.write(root.toJSONString());
            file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
     public void EditPekerjaanFromJson(){
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            array = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            
            if(itemArr.get("username").equals(username)){
                if ( pekerjaan != null) {
                    JSONArray list = new JSONArray();
                    for (int j = 0; j < pekerjaan.size(); j++) {
                        list.add(pekerjaan.get(j).getId());
                    }
                    itemArr.put("pekerjaan", list);
                    //itemArr.put("prasyarat", prasyarat);
                }
            }       
            root.put("users",array);
            
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriAkun)) {
                file.write(root.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
