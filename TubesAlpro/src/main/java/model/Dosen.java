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

public class Dosen extends User{
    //private String username;
    //private String password;
    private String nik;
    private String nama;
    private ArrayList<Kompetensi> kompetensi = new ArrayList<Kompetensi>();
    private ArrayList<String> libur = new ArrayList<String>();
    
    public Dosen(String username, String password, int role) {
        super(username, password, role);
    }
    

    public Dosen(String nik, String nama, ArrayList<Kompetensi> kompetensi, String username, String password, int role, ArrayList<String> libur) {
        super(username, password, role);
        this.nik = nik;
        this.nama = nama;
        this.kompetensi = kompetensi;
        this.libur = libur;
    }

    public Dosen(String username, String nama) {
        this.username = username;
        this.nama = nama;
    }
    
    public void EditUserDosenFromJson(){
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
    
    public void EditUserPasswordDosenFromJson(){
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
                if (nama != null) {
                    itemArr.put("nama", nama);
                } 
                else if ( kompetensi != null) {
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
    
    public void TambahLiburFromJson(){
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
                JSONArray list = new JSONArray();
                for (int j = 0; j < libur.size(); j++) {
                    list.add(libur.get(j));
                }
                itemArr.put("libur", list);
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
    

