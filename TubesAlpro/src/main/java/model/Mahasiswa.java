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

public class Mahasiswa extends User{
    //private String username;
    //private String password;
    private String nim;
    private String nama;
    private ArrayList<Pekerjaan> pekerjaan;
    private ArrayList<Kompetensi> kompetensi;

    public Mahasiswa() {
    }

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
        this.kompetensi = kompetensi;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public ArrayList<Pekerjaan> getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(ArrayList<Pekerjaan> pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public ArrayList<Kompetensi> getKompetensi() {
        return kompetensi;
    }

    public void setKompetensi(ArrayList<Kompetensi> kompetensi) {
        this.kompetensi = kompetensi;
    }
    
    
    
    public ArrayList<String> getListMahasiswa(){
        JSONObject root = new JSONObject();
        JSONArray kompetensi= new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        JSONArray listMahasiswa = new JSONArray();

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
                        listMahasiswa.add(itemArr.get("username"));
                        
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
        
        return listMahasiswa;
    }
    
    public ArrayList<JSONArray> getListPekerjaan(){
        JSONObject root = new JSONObject();
        JSONArray kompetensi= new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        JSONArray listPekerjaan = new JSONArray();

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
                        listPekerjaan.add(itemArr.get("pekerjaan"));
                        
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
        
        return listPekerjaan;
    }
    
    public void ReadAkunFromJson(){
        JSONObject root = new JSONObject();
        JSONArray kompetensi= new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

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
            
                    if(itemArr.get("username").equals(username)){
                        System.out.println("Username : " + (String) itemArr.get("username"));
                        System.out.println("Nama : " + (String) itemArr.get("nama"));
                        System.out.println("NIM : " + (String) itemArr.get("nim"));
                        System.out.println("Pekerjaan : " + (JSONArray) itemArr.get("prasyarat"));
                        break;
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
