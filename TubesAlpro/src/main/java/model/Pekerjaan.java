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
public class Pekerjaan {
    private String id;
    private String nama;
    private ArrayList<Kompetensi> listKompetensi = new ArrayList<Kompetensi>();

    public Pekerjaan() {
    }
    
    public Pekerjaan(String id){
        this.id = id;
    }

    public Pekerjaan(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }
    
    public Pekerjaan(String id, String nama, ArrayList<Kompetensi> listKompetensi) {
        this.id = id;
        this.nama = nama;
        this.listKompetensi = listKompetensi;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public ArrayList<Kompetensi> getListKompetensi() {
        return listKompetensi;
    }
    
    public void TulisPekerjaanToJson(){
        JSONObject root = new JSONObject();
        JSONArray pekerjaan= new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        File f = new File(ConfigDirektori.direktoriPekerjaan);
        
        if (f.exists() && !f.isDirectory()) {
        
        try {
            JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriPekerjaan));
            array = (JSONArray) objFromFile.get("pekerjaan");
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
        //JSONObject ro = new JSONObject();
        uo.put("id", id);
        uo.put("nama", nama);
        
        JSONArray list = new JSONArray();
        for (int i = 0; i < listKompetensi.size(); i++) {
            list.add(listKompetensi.get(i).getId());
        }
        uo.put("kompetensi", list);

        //ro.put("username", username);
        //ro.put("password", password);
        if (f.exists() && !f.isDirectory()) {
            array.add(uo);
            // add the array to the root object
            root.put("pekerjaan",array);
        }
        else{
            pekerjaan.add(uo);
            // add the array to the root object
            root.put("pekerjaan",pekerjaan);
        }
        
        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriPekerjaan)) {
            file.write(root.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean CekPekerjaanFromJson() {
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        boolean found = false;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriPekerjaan));
            array = (JSONArray) objFromFile.get("pekerjaan");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            
            if(itemArr.get("id").equals(id)){
                found = true;
            }       
        }
        return found;
    }
    
    public void EditPekerjaanFromJson(){
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriPekerjaan));
            array = (JSONArray) objFromFile.get("pekerjaan");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            
            if(itemArr.get("id").equals(id)){
                if (nama != null) {
                    itemArr.put("nama", nama);
                } 
                else if ( listKompetensi != null) {
                    itemArr.put("kompetensi", listKompetensi);
                }
            }       
            root.put("pekerjaan",array);
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriPekerjaan)) {

            file.write(root.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }
    
    public void EditListKompetensiFromJson(){
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriPekerjaan));
            array = (JSONArray) objFromFile.get("pekerjaan");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            
            if(itemArr.get("id").equals(id)){
                if (nama != null) {
                    itemArr.put("nama", nama);
                } 
                else if ( listKompetensi != null) {
                    JSONArray list = new JSONArray();
                    for (int j = 0; j < listKompetensi.size(); j++) {
                        list.add(listKompetensi.get(j).getId());
                    }
                    itemArr.put("kompetensi", list);
                } 
            }       
            root.put("pekerjaan",array);
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriPekerjaan)) {

            file.write(root.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }
    
    public void HapusPekerjaanFromJson(){
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        JSONArray arrayDeleted = new JSONArray();

        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriPekerjaan));
            array = (JSONArray) objFromFile.get("pekerjaan");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Pekerjaan.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            
            if(!itemArr.get("id").equals(id)){
                JSONObject uo = new JSONObject();
                  //JSONObject ro = new JSONObject();
                uo.put("id", itemArr.get("id"));
                uo.put("nama", itemArr.get("nama"));
                uo.put("kompetensi", itemArr.get("kompetensi"));
                arrayDeleted.add(uo);
            }       
        }
        root.put("pekerjaan",arrayDeleted);
        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriPekerjaan)) {

        file.write(root.toJSONString());
        file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void ReadPekerjaanFromJson(){
        JSONObject root = new JSONObject();
        JSONArray pekerjaan = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        File f = new File(ConfigDirektori.direktoriPekerjaan);
        
        if (f.exists() && !f.isDirectory()) {
        
            try {
                //JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriPekerjaan));

                JSONObject jsonObject = (JSONObject) obj;
                //JSONObject jsonObject2 = (JSONObject) jsonObject.get("kompetensi");
                array = (JSONArray) jsonObject .get("pekerjaan");

                String tbl = "| %-3s | %-40s | %-45s |%n";
                System.out.format("+-----+------------------------------------------+-----------------------------------------------+%n");
                System.out.format("| No  | Nama Pekerjaan                           | Kompetensi                                    |%n");
                System.out.format("+-----+------------------------------------------+-----------------------------------------------+%n");

                for (int i = 0; i < array.size(); i++) {
                    char kode;
                    // get all JSON Object
                    JSONObject itemArr = (JSONObject)array.get(i);
                    String nama = (String) itemArr.get("nama");
                    String id = (String) itemArr.get("id");

                     // loop array
                    JSONArray msg = (JSONArray) itemArr.get("kompetensi");
                    Iterator<String> iterator = msg.iterator();


                    System.out.format(tbl, i+1, nama, msg);
                }
                System.out.format("+-----+------------------------------------------+-----------------------------------------------+%n");
                System.out.println("");
            
            } catch (FileNotFoundException e) {
             e.printStackTrace();
            } catch (IOException e) {
             e.printStackTrace();
            } catch (ParseException ex) {
             Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
    }
}
