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

    public ArrayList<Kompetensi> getKompetensi() {
        return kompetensi;
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
    
    public boolean EditKompetensiFromJson(){
         boolean finished = false;
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
            finished = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        }
        return finished;
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

    public void AlokasiJadwal(int minggu , String gotKompetensi) {
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        JSONArray arrayJadwal = null;
        JSONArray arrayUser = null;
        long sks = 0;
        boolean praktikum = false;
        String getKompetensi = null;
        ArrayList <String> getLibur = new ArrayList<String>();
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
            array = (JSONArray) objFromFile.get("kompetensi");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriSlot+"jadwal"+minggu+".json"));
            arrayJadwal = (JSONArray) objFromFile.get("minggu"+minggu);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            arrayUser = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          // pre requiresite= libur dosen
         boolean found = false;
        for (int z = 0; z < arrayUser.size() && found == false; z++) {
                JSONObject itemArr = (JSONObject)arrayUser.get(z);
                String getUsernameDosen = (String) itemArr.get("username");
                //System.out.println(username);
                //System.out.println(getUsernameDosen);
                
                if (getUsernameDosen.equals(username)){
                             getLibur = (ArrayList<String>) itemArr.get("libur");
                             //System.out.println(getLibur);
                             found = true;
                }
        }
        // pre requiresite= kompetensi
        found = false;
        for (int i = 0; i < array.size() && found == false; i++) {
                JSONObject itemArr = (JSONObject)array.get(i);
                System.out.println(itemArr.get("id"));
                System.out.println(gotKompetensi);
                if (itemArr.get("id").equals(gotKompetensi)) {
                                long getSKS = (long) itemArr.get("sks");
                                boolean getPraktikum = (boolean) itemArr.get("hasPraktikum");
                               getKompetensi = (String) itemArr.get("id");
                                sks = getSKS;
                                praktikum = getPraktikum;
                                found = true;
                                }
        }
                
                 // pre requiresite= cek cuti
                boolean cuti = true;
                boolean read = false;
               for (int j = 0; j < arrayJadwal.size() && read == false; j++) {
                JSONObject itemArrJadwal = (JSONObject)arrayJadwal.get(j);
                String tanggal = (String) itemArrJadwal.get("tanggal");
                 //  System.out.println(arrayJadwal.size());
                   //System.out.println(tanggal);
                                if (getLibur != null){
                                for (int i = 0; i <getLibur.size() && cuti == true; i++) {
                                                if (getLibur.get(i).equals(tanggal)) {
                                                            cuti = false;
                                                }
                                }
                                
                                }else{
                                            cuti = false;
                                        }
                
                                
                   if (!cuti) {
                                String inputan = "M_"+getKompetensi+"_"+username;
                                String inputanPraktikum = "P_"+getKompetensi+"_"+username;
                                if (sks == 5) {
                                    

                                    if (itemArrJadwal.get("hari").equals("Jumat")) {
                                            continue;  
                                    }else {

                                             if (itemArrJadwal.get("jam7kelas1") == null) {

                                             
                                             if (praktikum) {
                                                                                    itemArrJadwal.put("jam7kelas1", inputan);
                                                                    itemArrJadwal.put("jam8kelas1", inputan);
                                                                    itemArrJadwal.put("jam9kelas1", inputan);
                                                                    itemArrJadwal.put("jam10kelas1", inputan);
                                                                    itemArrJadwal.put("jam11kelas1", inputan);
                                                                               JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);
                                                                              itemArrJadwalPraktikum.put("jam7kelas1", inputanPraktikum);
                                                                                itemArrJadwalPraktikum.put("jam8kelas1", inputanPraktikum);
                                                                                itemArrJadwalPraktikum.put("jam9kelas1", inputanPraktikum);
                                                                                itemArrJadwalPraktikum.put("jam10kelas1", inputanPraktikum);
                                                                                itemArrJadwalPraktikum.put("jam11kelas1", inputanPraktikum);
                                                                                read = true;
                                                                 }else{
                                                                  itemArrJadwal.put("jam7kelas1", inputan);
                                                                    itemArrJadwal.put("jam8kelas1", inputan);
                                                                    itemArrJadwal.put("jam9kelas1", inputan);
                                                                    itemArrJadwal.put("jam10kelas1", inputan);
                                                                    itemArrJadwal.put("jam11kelas1", inputan);
                                                                    read = true;
                                             }
                                             }else if (itemArrJadwal.get("jam7kelas2") == null){

                                                             String kelasDosen = (String) itemArrJadwal.get("jam7kelas1");
                                                             String cekDosen = kelasDosen.substring(7);
                                                             if (cekDosen.equals(username)) {
                                                                     continue;
                                                             }else{

                                                                              itemArrJadwal.put("jam7kelas2", inputan);
                                                                              itemArrJadwal.put("jam8kelas2", inputan);
                                                                              itemArrJadwal.put("jam9kelas2", inputan);
                                                                              itemArrJadwal.put("jam10kelas2", inputan);
                                                                              itemArrJadwal.put("jam11kelas2", inputan);
                                                                              read = true;
                                                                             if (praktikum) {
                                                                                    itemArrJadwal.put("jam7kelas2", inputan);
                                                                              itemArrJadwal.put("jam8kelas2", inputan);
                                                                              itemArrJadwal.put("jam9kelas2", inputan);
                                                                              itemArrJadwal.put("jam10kelas2", inputan);
                                                                              itemArrJadwal.put("jam11kelas2", inputan);
                                                                               JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                               itemArrJadwalPraktikum.put("jam7kelas2", inputanPraktikum);
                                                                              itemArrJadwalPraktikum.put("jam8kelas2", inputanPraktikum);
                                                                              itemArrJadwalPraktikum.put("jam9kelas2", inputanPraktikum);
                                                                              itemArrJadwalPraktikum.put("jam10kelas2", inputanPraktikum);
                                                                              itemArrJadwalPraktikum.put("jam11kelas2", inputanPraktikum);
                                                                              read = true;
                                                                 }

                                                             }

                                             }else if (itemArrJadwal.get("jam7kelas3") == null){
                                             //alokasi
                                                             String kelasDosen = (String) itemArrJadwal.get("jam7kelas1");
                                                             String cekDosen = kelasDosen.substring(7);
                                                             String kelasDosen1 = (String) itemArrJadwal.get("jam7kelas2");
                                                             String cekDosen1 = kelasDosen1.substring(7);
                                                             if (cekDosen.equals(username) || cekDosen1.equals(username) ) {
                                                                     continue;
                                                             }else{

                                                                             itemArrJadwal.put("jam7kelas3", inputan);
                                                                             itemArrJadwal.put("jam8kelas3", inputan);
                                                                             itemArrJadwal.put("jam9kelas3", inputan);
                                                                             itemArrJadwal.put("jam10kelas3", inputan);
                                                                             itemArrJadwal.put("jam11kelas3", inputan);
                                                                             read = true;
                                                                              if (praktikum) {
                                                                                    itemArrJadwal.put("jam7kelas3", inputan);
                                                                             itemArrJadwal.put("jam8kelas3", inputan);
                                                                             itemArrJadwal.put("jam9kelas3", inputan);
                                                                             itemArrJadwal.put("jam10kelas3", inputan);
                                                                             itemArrJadwal.put("jam11kelas3", inputan);
                                                                               JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                itemArrJadwalPraktikum.put("jam7kelas3", inputanPraktikum);
                                                                             itemArrJadwalPraktikum.put("jam8kelas3", inputanPraktikum);
                                                                             itemArrJadwalPraktikum.put("jam9kelas3", inputanPraktikum);
                                                                             itemArrJadwalPraktikum.put("jam10kelas3", inputanPraktikum);
                                                                             itemArrJadwalPraktikum.put("jam11kelas3", inputanPraktikum);
                                                                             read = true;
                                                                 }

                                                             }
                                             }else {
                                             continue;
                                             }
                                    }
                                }else if(sks == 4){
                                    //boolean flag = false;
                                                
                                                                if (itemArrJadwal.get("jam7kelas1") == null) {

                                                                
                                                                if (praktikum) {
                                                                                                     itemArrJadwal.put("jam7kelas1", inputan);
                                                                                                    itemArrJadwal.put("jam8kelas1", inputan);
                                                                                                    itemArrJadwal.put("jam9kelas1", inputan);
                                                                                                    itemArrJadwal.put("jam10kelas1", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);
                                                                                                     itemArrJadwalPraktikum.put("jam7kelas1", inputanPraktikum);
                                                                                                    itemArrJadwalPraktikum.put("jam8kelas1", inputanPraktikum);
                                                                                                    itemArrJadwalPraktikum.put("jam9kelas1", inputanPraktikum);
                                                                                                    itemArrJadwalPraktikum.put("jam10kelas1", inputanPraktikum);
                                                                                                    read = true;
                                                                                    }else{
                                                                                     itemArrJadwal.put("jam7kelas1", inputan);
                                                                                    itemArrJadwal.put("jam8kelas1", inputan);
                                                                                    itemArrJadwal.put("jam9kelas1", inputan);
                                                                                    itemArrJadwal.put("jam10kelas1", inputan);
                                                                                    read = true;
                                                                }
                                                                }else if (itemArrJadwal.get("jam7kelas2") == null){

                                                                                String kelasDosen = (String) itemArrJadwal.get("jam7kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                if (cekDosen.equals(username)) {
                                                                                       // flag = true;
                                                                                }else{

                                                                                                itemArrJadwal.put("jam7kelas2", inputan);
                                                                                                itemArrJadwal.put("jam8kelas2", inputan);
                                                                                                itemArrJadwal.put("jam9kelas2", inputan);
                                                                                                itemArrJadwal.put("jam10kelas2", inputan);
                                                                                                read = true;
                                                                                                if (praktikum) {
                                                                                                     itemArrJadwal.put("jam7kelas2", inputan);
                                                                                                itemArrJadwal.put("jam8kelas2", inputan);
                                                                                                itemArrJadwal.put("jam9kelas2", inputan);
                                                                                                itemArrJadwal.put("jam10kelas2", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                   itemArrJadwalPraktikum.put("jam7kelas2", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam8kelas2", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam9kelas2", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam10kelas2", inputanPraktikum);
                                                                                                read = true;
                                                                                    }

                                                                                }

                                                                }else if (itemArrJadwal.get("jam7kelas3") == null ){
                                                                 //   flag = false;
                                                                //alokasi
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam7kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                String kelasDosen1 = (String) itemArrJadwal.get("jam7kelas2");
                                                                                String cekDosen1 = kelasDosen1.substring(7);
                                                                                if (cekDosen.equals(username) || cekDosen1.equals(username) ) {
                                                                                      //  flag = true;
                                                                                }else{

                                                                                                itemArrJadwal.put("jam7kelas3", inputan);
                                                                                                itemArrJadwal.put("jam8kelas3", inputan);
                                                                                                itemArrJadwal.put("jam9kelas3", inputan);
                                                                                                itemArrJadwal.put("jam10kelas3", inputan);
                                                                                                read = true;
                                                                                                 if (praktikum) {
                                                                                                      itemArrJadwal.put("jam7kelas3", inputan);
                                                                                                itemArrJadwal.put("jam8kelas3", inputan);
                                                                                                itemArrJadwal.put("jam9kelas3", inputan);
                                                                                                itemArrJadwal.put("jam10kelas3", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                  itemArrJadwalPraktikum.put("jam7kelas3", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam8kelas3", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam9kelas3", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam10kelas3", inputanPraktikum);
                                                                                                read = true;
                                                                                    }

                                                                                }
                                                                } else
                                                                if (itemArrJadwal.get("jam13kelas1") == null ) {
                                                                            //alokasi
                                                                             itemArrJadwal.put("jam13kelas1", inputan);
                                                                             itemArrJadwal.put("jam14kelas1", inputan);
                                                                             itemArrJadwal.put("jam15kelas1", inputan);
                                                                             itemArrJadwal.put("jam16kelas1", inputan);
                                                                             read = true;
                                                                if (praktikum) {
                                                                                                  itemArrJadwal.put("jam13kelas1", inputan);
                                                                                                itemArrJadwal.put("jam14kelas1", inputan);
                                                                                                itemArrJadwal.put("jam15kelas1", inputan);
                                                                                                itemArrJadwal.put("jam16kelas1", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);
                                                                                                  itemArrJadwalPraktikum.put("jam13kelas1", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam14kelas1", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam15kelas1", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam16kelas1", inputanPraktikum);
                                                                                                read = true;
                                                                                    }
                                                                }else if (itemArrJadwal.get("jam13kelas2") == null){
                                                                                
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam13kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                if (cekDosen.equals(username)) {
                                                                                        
                                                                                }else{

                                                                                              itemArrJadwal.put("jam13kelas2", inputan);
                                                                                              itemArrJadwal.put("jam14kelas2", inputan);
                                                                                              itemArrJadwal.put("jam15kelas2", inputan);
                                                                                              itemArrJadwal.put("jam16kelas2", inputan);
                                                                                              read = true;
                                                                                                if (praktikum) {
                                                                                                      itemArrJadwal.put("jam13kelas2", inputan);
                                                                                              itemArrJadwal.put("jam14kelas2", inputan);
                                                                                              itemArrJadwal.put("jam15kelas2", inputan);
                                                                                              itemArrJadwal.put("jam16kelas2", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam13kelas2", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam14kelas2", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam15kelas2", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam16kelas2", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }

                                                                }else if (itemArrJadwal.get("jam13kelas3") == null){
                                                                //alokasi
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam13kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                String kelasDosen1 = (String) itemArrJadwal.get("jam13kelas2");
                                                                                String cekDosen1 = kelasDosen1.substring(7);
                                                                                if (cekDosen.equals(username) || cekDosen1.equals(username) ) {

                                                                                }else{

                                                                                               itemArrJadwal.put("jam13kelas3", inputan);
                                                                                               itemArrJadwal.put("jam14kelas3", inputan);
                                                                                               itemArrJadwal.put("jam15kelas3", inputan);
                                                                                               itemArrJadwal.put("jam16kelas3", inputan);
                                                                                               read = true;
                                                                                                 if (praktikum) {
                                                                                                      itemArrJadwal.put("jam13kelas3", inputan);
                                                                                               itemArrJadwal.put("jam14kelas3", inputan);
                                                                                               itemArrJadwal.put("jam15kelas3", inputan);
                                                                                               itemArrJadwal.put("jam16kelas3", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam13kelas3", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam14kelas3", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam15kelas3", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam16kelas3", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }
                                                                }else {
                                                                break;
                                                                }
                                                
                                                
                                                

                                }else if(sks == 3){
                                                
                                    //boolean flag = false;
                                                
                                                                if (itemArrJadwal.get("jam7kelas1") == null) {

                                                                
                                                                if (praktikum) {
                                                                                                     itemArrJadwal.put("jam7kelas1", inputan);
                                                                                                    itemArrJadwal.put("jam8kelas1", inputan);
                                                                                                    itemArrJadwal.put("jam9kelas1", inputan);
                                                                                                    itemArrJadwal.put("jam10kelas1", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);
                                                                                                     itemArrJadwalPraktikum.put("jam7kelas1", inputanPraktikum);
                                                                                                    itemArrJadwalPraktikum.put("jam8kelas1", inputanPraktikum);
                                                                                                    itemArrJadwalPraktikum.put("jam9kelas1", inputanPraktikum);
                                                                                                    itemArrJadwalPraktikum.put("jam10kelas1", inputanPraktikum);
                                                                                                    read = true;
                                                                                    }else{
                                                                                     itemArrJadwal.put("jam7kelas1", inputan);
                                                                                    itemArrJadwal.put("jam8kelas1", inputan);
                                                                                    itemArrJadwal.put("jam9kelas1", inputan);
                                                                                    itemArrJadwal.put("jam10kelas1", inputan);
                                                                                    read = true;
                                                                }
                                                                }else if (itemArrJadwal.get("jam7kelas2") == null){

                                                                                String kelasDosen = (String) itemArrJadwal.get("jam7kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                if (cekDosen.equals(username)) {
                                                                                       // flag = true;
                                                                                }else{

                                                                                                itemArrJadwal.put("jam7kelas2", inputan);
                                                                                                itemArrJadwal.put("jam8kelas2", inputan);
                                                                                                itemArrJadwal.put("jam9kelas2", inputan);
                                                                                                itemArrJadwal.put("jam10kelas2", inputan);
                                                                                                read = true;
                                                                                                if (praktikum) {
                                                                                                     itemArrJadwal.put("jam7kelas2", inputan);
                                                                                                itemArrJadwal.put("jam8kelas2", inputan);
                                                                                                itemArrJadwal.put("jam9kelas2", inputan);
                                                                                                itemArrJadwal.put("jam10kelas2", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                   itemArrJadwalPraktikum.put("jam7kelas2", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam8kelas2", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam9kelas2", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam10kelas2", inputanPraktikum);
                                                                                                read = true;
                                                                                    }

                                                                                }

                                                                }else if (itemArrJadwal.get("jam7kelas3") == null ){
                                                                 //   flag = false;
                                                                //alokasi
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam7kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                String kelasDosen1 = (String) itemArrJadwal.get("jam7kelas2");
                                                                                String cekDosen1 = kelasDosen1.substring(7);
                                                                                if (cekDosen.equals(username) || cekDosen1.equals(username) ) {
                                                                                      //  flag = true;
                                                                                }else{

                                                                                                itemArrJadwal.put("jam7kelas3", inputan);
                                                                                                itemArrJadwal.put("jam8kelas3", inputan);
                                                                                                itemArrJadwal.put("jam9kelas3", inputan);
                                                                                                itemArrJadwal.put("jam10kelas3", inputan);
                                                                                                read = true;
                                                                                                 if (praktikum) {
                                                                                                      itemArrJadwal.put("jam7kelas3", inputan);
                                                                                                itemArrJadwal.put("jam8kelas3", inputan);
                                                                                                itemArrJadwal.put("jam9kelas3", inputan);
                                                                                                itemArrJadwal.put("jam10kelas3", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                  itemArrJadwalPraktikum.put("jam7kelas3", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam8kelas3", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam9kelas3", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam10kelas3", inputanPraktikum);
                                                                                                read = true;
                                                                                    }

                                                                                }
                                                                }

                                }else if(sks == 2){
                                                

                                    if (itemArrJadwal.get("hari").equals("Jumat")) {
                                                                if (itemArrJadwal.get("jam13kelas1") == null ) {
                                                                            //alokasi
                                                                             itemArrJadwal.put("jam13kelas1", inputan);
                                                                             itemArrJadwal.put("jam14kelas1", inputan);
                                                                             read = true;
                                                                if (praktikum) {
                                                                                                  itemArrJadwal.put("jam13kelas1", inputan);
                                                                                                itemArrJadwal.put("jam14kelas1", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);
                                                                                                  itemArrJadwalPraktikum.put("jam13kelas1", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam14kelas1", inputanPraktikum);
                                                                                                read = true;
                                                                                    }
                                                                }else if (itemArrJadwal.get("jam13kelas2") == null){
                                                                                
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam13kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                if (cekDosen.equals(username)) {
                                                                                        
                                                                                }else{

                                                                                              itemArrJadwal.put("jam13kelas2", inputan);
                                                                                              itemArrJadwal.put("jam14kelas2", inputan);
                                                                                              read = true;
                                                                                                if (praktikum) {
                                                                                                      itemArrJadwal.put("jam13kelas2", inputan);
                                                                                              itemArrJadwal.put("jam14kelas2", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam13kelas2", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam14kelas2", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }

                                                                }else if (itemArrJadwal.get("jam13kelas3") == null){
                                                                //alokasi
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam13kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                String kelasDosen1 = (String) itemArrJadwal.get("jam13kelas2");
                                                                                String cekDosen1 = kelasDosen1.substring(7);
                                                                                if (cekDosen.equals(username) || cekDosen1.equals(username) ) {

                                                                                }else{

                                                                                               itemArrJadwal.put("jam13kelas3", inputan);
                                                                                               itemArrJadwal.put("jam14kelas3", inputan);
                                                                                               read = true;
                                                                                                 if (praktikum) {
                                                                                                      itemArrJadwal.put("jam13kelas3", inputan);
                                                                                               itemArrJadwal.put("jam14kelas3", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam13kelas3", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam14kelas3", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }
                                                                }else if (itemArrJadwal.get("jam15kelas1") == null ) {
                                                                            //alokasi
                                                                             itemArrJadwal.put("jam15kelas1", inputan);
                                                                             itemArrJadwal.put("jam16kelas1", inputan);
                                                                             read = true;
                                                                if (praktikum) {
                                                                                                  itemArrJadwal.put("jam15kelas1", inputan);
                                                                                                itemArrJadwal.put("jam16kelas1", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);
                                                                                                  itemArrJadwalPraktikum.put("jam15kelas1", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam16kelas1", inputanPraktikum);
                                                                                                read = true;
                                                                                    }
                                                                }else if (itemArrJadwal.get("jam15kelas2") == null){
                                                                                
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam15kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                if (cekDosen.equals(username)) {
                                                                                        
                                                                                }else{

                                                                                              itemArrJadwal.put("jam15kelas2", inputan);
                                                                                              itemArrJadwal.put("jam16kelas2", inputan);
                                                                                              read = true;
                                                                                                if (praktikum) {
                                                                                                      itemArrJadwal.put("jam15kelas2", inputan);
                                                                                              itemArrJadwal.put("jam16kelas2", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam15kelas2", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam16kelas2", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }

                                                                }else if (itemArrJadwal.get("jam15kelas3") == null){
                                                                //alokasi
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam15kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                String kelasDosen1 = (String) itemArrJadwal.get("jam15kelas2");
                                                                                String cekDosen1 = kelasDosen1.substring(7);
                                                                                if (cekDosen.equals(username) || cekDosen1.equals(username) ) {

                                                                                }else{

                                                                                               itemArrJadwal.put("jam15kelas3", inputan);
                                                                                               itemArrJadwal.put("jam16kelas3", inputan);
                                                                                               read = true;
                                                                                                 if (praktikum) {
                                                                                                      itemArrJadwal.put("jam15kelas3", inputan);
                                                                                               itemArrJadwal.put("jam16kelas3", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam15kelas3", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam16kelas3", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }
                                                                }
                                            
                                    }else {
                                        
                                                   
                                        
                                        if (itemArrJadwal.get("jam10kelas1") == null ) {
                                                                            //alokasi
                                                                             itemArrJadwal.put("jam10kelas1", inputan);
                                                                             itemArrJadwal.put("jam11kelas1", inputan);
                                                                             read = true;
                                                                if (praktikum) {
                                                                                                  itemArrJadwal.put("jam10kelas1", inputan);
                                                                                                itemArrJadwal.put("jam11kelas1", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);
                                                                                                  itemArrJadwalPraktikum.put("jam10kelas1", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam11kelas1", inputanPraktikum);
                                                                                                read = true;
                                                                                    }
                                                                }else if (itemArrJadwal.get("jam10kelas2") == null){
                                                                                
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam10kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                if (cekDosen.equals(username)) {
                                                                                        
                                                                                }else{

                                                                                              itemArrJadwal.put("jam10kelas2", inputan);
                                                                                              itemArrJadwal.put("jam11kelas2", inputan);
                                                                                              read = true;
                                                                                                if (praktikum) {
                                                                                                      itemArrJadwal.put("jam10kelas2", inputan);
                                                                                              itemArrJadwal.put("jam11kelas2", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam10kelas2", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam11kelas2", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }

                                                                }else if (itemArrJadwal.get("jam10kelas3") == null){
                                                                //alokasi
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam10kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                String kelasDosen1 = (String) itemArrJadwal.get("jam10kelas2");
                                                                                String cekDosen1 = kelasDosen1.substring(7);
                                                                                if (cekDosen.equals(username) || cekDosen1.equals(username) ) {

                                                                                }else{

                                                                                               itemArrJadwal.put("jam10kelas3", inputan);
                                                                                               itemArrJadwal.put("jam11kelas3", inputan);
                                                                                               read = true;
                                                                                                 if (praktikum) {
                                                                                                      itemArrJadwal.put("jam10kelas3", inputan);
                                                                                               itemArrJadwal.put("jam11kelas3", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam10kelas3", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam11kelas3", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }
                                                                }
                                        else  if (itemArrJadwal.get("jam13kelas1") == null ) {
                                                                            //alokasi
                                                                             itemArrJadwal.put("jam13kelas1", inputan);
                                                                             itemArrJadwal.put("jam14kelas1", inputan);
                                                                             read = true;
                                                                if (praktikum) {
                                                                                                  itemArrJadwal.put("jam13kelas1", inputan);
                                                                                                itemArrJadwal.put("jam14kelas1", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);
                                                                                                  itemArrJadwalPraktikum.put("jam13kelas1", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam14kelas1", inputanPraktikum);
                                                                                                read = true;
                                                                                    }
                                                                }else if (itemArrJadwal.get("jam13kelas2") == null){
                                                                                
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam13kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                if (cekDosen.equals(username)) {
                                                                                        
                                                                                }else{

                                                                                              itemArrJadwal.put("jam13kelas2", inputan);
                                                                                              itemArrJadwal.put("jam14kelas2", inputan);
                                                                                              read = true;
                                                                                                if (praktikum) {
                                                                                                      itemArrJadwal.put("jam13kelas2", inputan);
                                                                                              itemArrJadwal.put("jam14kelas2", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam13kelas2", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam14kelas2", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }

                                                                }else if (itemArrJadwal.get("jam13kelas3") == null){
                                                                //alokasi
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam13kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                String kelasDosen1 = (String) itemArrJadwal.get("jam13kelas2");
                                                                                String cekDosen1 = kelasDosen1.substring(7);
                                                                                if (cekDosen.equals(username) || cekDosen1.equals(username) ) {

                                                                                }else{

                                                                                               itemArrJadwal.put("jam13kelas3", inputan);
                                                                                               itemArrJadwal.put("jam14kelas3", inputan);
                                                                                               read = true;
                                                                                                 if (praktikum) {
                                                                                                      itemArrJadwal.put("jam13kelas3", inputan);
                                                                                               itemArrJadwal.put("jam14kelas3", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam13kelas3", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam14kelas3", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }
                                                                }else if (itemArrJadwal.get("jam15kelas1") == null ) {
                                                                            //alokasi
                                                                             itemArrJadwal.put("jam15kelas1", inputan);
                                                                             itemArrJadwal.put("jam16kelas1", inputan);
                                                                             read = true;
                                                                if (praktikum) {
                                                                                                  itemArrJadwal.put("jam15kelas1", inputan);
                                                                                                itemArrJadwal.put("jam16kelas1", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);
                                                                                                  itemArrJadwalPraktikum.put("jam15kelas1", inputanPraktikum);
                                                                                                itemArrJadwalPraktikum.put("jam16kelas1", inputanPraktikum);
                                                                                                read = true;
                                                                                    }
                                                                }else if (itemArrJadwal.get("jam15kelas2") == null){
                                                                                
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam15kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                if (cekDosen.equals(username)) {
                                                                                        
                                                                                }else{

                                                                                              itemArrJadwal.put("jam15kelas2", inputan);
                                                                                              itemArrJadwal.put("jam16kelas2", inputan);
                                                                                              read = true;
                                                                                                if (praktikum) {
                                                                                                      itemArrJadwal.put("jam15kelas2", inputan);
                                                                                              itemArrJadwal.put("jam16kelas2", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam15kelas2", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam16kelas2", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }

                                                                }else if (itemArrJadwal.get("jam15kelas3") == null){
                                                                //alokasi
                                                                                String kelasDosen = (String) itemArrJadwal.get("jam15kelas1");
                                                                                String cekDosen = kelasDosen.substring(7);
                                                                                String kelasDosen1 = (String) itemArrJadwal.get("jam15kelas2");
                                                                                String cekDosen1 = kelasDosen1.substring(7);
                                                                                if (cekDosen.equals(username) || cekDosen1.equals(username) ) {

                                                                                }else{

                                                                                               itemArrJadwal.put("jam15kelas3", inputan);
                                                                                               itemArrJadwal.put("jam16kelas3", inputan);
                                                                                               read = true;
                                                                                                 if (praktikum) {
                                                                                                      itemArrJadwal.put("jam15kelas3", inputan);
                                                                                               itemArrJadwal.put("jam16kelas3", inputan);
                                                                                                  JSONObject itemArrJadwalPraktikum= (JSONObject)arrayJadwal.get(j+1);

                                                                                                 itemArrJadwalPraktikum.put("jam15kelas3", inputanPraktikum);
                                                                                                 itemArrJadwalPraktikum.put("jam16kelas3", inputanPraktikum);
                                                                                                 read = true;
                                                                                    }

                                                                                }
                                                                }
                                            
                                  
                                        
                                    }

                                }else{
                                    System.out.println("cuma menerima sks > 1 & sks < 6");
                                    break;
                                }

            
                   }
                
               }
               
               root.put("minggu"+minggu,arrayJadwal);
                    try (FileWriter file = new FileWriter(ConfigDirektori.direktoriSlot+"jadwal"+minggu+".json")) {
                    file.write(root.toJSONString());
                    file.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                    
//                 root.put("minggu"+minggu,arrayJadwal);
//                    try (FileWriter file = new FileWriter(ConfigDirektori.direktoriSlot+"jadwal"+minggu+".json")) {
//                    file.write(root.toJSONString());
//                    file.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
        
                
        }
    
    
    
    
}
    

