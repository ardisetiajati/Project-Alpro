/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

/**
 *
 * @author izu
 */
public class Jadwal {
    Kompetensi kompetensi;
    ArrayList <Kompetensi> k = new ArrayList<Kompetensi>();
    String jamMasuk, jamSelesai;

    public Jadwal() {
    }

    public Jadwal(String jamMasuk, String jamSelesai) {
        this.jamMasuk = jamMasuk;
        this.jamSelesai = jamSelesai;
    }

    public String getJamMasuk() {
        return jamMasuk;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }
    
    public void ReadJadwalFromJson(){
        
    }
    
    public void ReadKompetensiFromJsonToObject() {
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        boolean found = false;
        
        
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
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            String nama = (String) itemArr.get("nama");
            String id = (String) itemArr.get("id");
            int  bobot = (int) itemArr.get("sks");
            boolean hasPraktikum = (boolean) itemArr.get("hasPraktikum");
             // loop array
            JSONArray msg = (JSONArray) itemArr.get("prasyarat");
            Iterator<String> iterator = msg.iterator();
            
            Kompetensi kompetensi = new Kompetensi(id,nama,msg,bobot, hasPraktikum);   
            k.add(kompetensi);
        }
        
    }
      
      public void BuatSlotToJson() {
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray slot= new JSONArray();
        JSONArray array = null;
        JSONArray arraySlot = null;
        boolean found = false;
        String id;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKalender));
            array= (JSONArray) objFromFile.get("kalender");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for ( int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            JSONObject bj = new JSONObject();
            JSONObject cj = new JSONObject();
            JSONObject dj = new JSONObject();
            JSONObject ej = new JSONObject();
            JSONObject fj = new JSONObject();
            JSONObject gj = new JSONObject();
            JSONObject hj = new JSONObject();
            JSONObject ij = new JSONObject();
            JSONObject jj = new JSONObject();
            JSONObject kj = new JSONObject();
            JSONObject lj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            long jammasukslot1 = (long) itemArr.get("jammasukslot1");
            long jammasukslot2 = (long) itemArr.get("jammasukslot2");
            long jammasukslot3 = (long) itemArr.get("jammasukslot3");
            long jammasukslot4 = (long) itemArr.get("jammasukslot4");
            long jamkeluarslot1 = (long) itemArr.get("jamkeluarslot1");
            long jamkeluarslot2 = (long) itemArr.get("jamkeluarslot2");
            long jamkeluarslot3 = (long) itemArr.get("jamkeluarslot3");
            long jamkeluarslot4 = (long) itemArr.get("jamkeluarslot4");
            
            //System.out.println(hari);
           // System.out.println(hari.equals("Jumat"));
            if (!libur) {
                if (hari.equals("Jumat")) {

                    aj.put("id",1);
                    aj.put("jammasuk", jammasukslot1);
                    aj.put("jamkeluar", jamkeluarslot1);
                    aj.put("tanggal",tanggal);

                    aj.put("kompetensi",null);
                    aj.put("dosen",null);
                    aj.put("listMhs",null);
                     //j++;
                    slot.add(aj);

                    bj.put("id",2);
                    bj.put("jammasuk", jammasukslot1);
                    bj.put("jamkeluar", jamkeluarslot1);
                    bj.put("tanggal",tanggal);

                    bj.put("kompetensi",null);
                    bj.put("dosen",null);
                    bj.put("listMhs",null);
                     //j++;
                    slot.add(bj);

                    cj.put("id",3);
                    cj.put("jammasuk", jammasukslot1);
                    cj.put("jamkeluar", jamkeluarslot1);
                    cj.put("tanggal",tanggal);

                    cj.put("kompetensi",null);
                    cj.put("dosen",null);
                    cj.put("listMhs",null);
                    //j++;
                    slot.add(cj);

                    dj.put("id",4);
                    dj.put("jammasuk", jammasukslot3);
                    dj.put("jamkeluar", jamkeluarslot3);
                    dj.put("tanggal",tanggal);

                    dj.put("kompetensi",null);
                    dj.put("dosen",null);
                    dj.put("listMhs",null);
                     //j++;
                    slot.add(dj);

                    ej.put("id",5);
                    ej.put("jammasuk", jammasukslot3);
                    ej.put("jamkeluar", jamkeluarslot3);
                    ej.put("tanggal",tanggal);

                    ej.put("kompetensi",null);
                    ej.put("dosen",null);
                    ej.put("listMhs",null);
                     //j++;
                    slot.add(ej);

                    fj.put("id",6);
                    fj.put("jammasuk", jammasukslot3);
                    fj.put("jamkeluar", jamkeluarslot3);
                    fj.put("tanggal",tanggal);

                    fj.put("kompetensi",null);
                    fj.put("dosen",null);
                    fj.put("listMhs",null);
                     //j++;
                    slot.add(fj);

                    gj.put("id",7);
                    gj.put("jammasuk", jammasukslot4);
                    gj.put("jamkeluar", jamkeluarslot4);
                    gj.put("tanggal",tanggal);

                    gj.put("kompetensi",null);
                    gj.put("dosen",null);
                    gj.put("listMhs",null);
                    //j++;
                    slot.add(gj);

                    hj.put("id",8);
                    hj.put("jammasuk", jammasukslot4);
                    hj.put("jamkeluar", jamkeluarslot4);
                    hj.put("tanggal",tanggal);

                    hj.put("kompetensi",null);
                    hj.put("dosen",null);
                    hj.put("listMhs",null);
                     //j++;
                    slot.add(hj);

                    ij.put("id",9);
                    ij.put("jammasuk", jammasukslot4);
                    ij.put("jamkeluar", jamkeluarslot4);
                    ij.put("tanggal",tanggal);

                    ij.put("kompetensi",null);
                    ij.put("dosen",null);
                    ij.put("listMhs",null);
                    //j++;
                    slot.add(ij);
                        
                }else{
                        
                    aj.put("id",1);
                    aj.put("jammasuk", jammasukslot1);
                    aj.put("jamkeluar", jamkeluarslot1);
                    aj.put("tanggal",tanggal);

                    aj.put("kompetensi",null);
                    aj.put("dosen",null);
                    aj.put("listMhs",null);
                    //j++;
                    slot.add(aj);
                    bj.put("id",2);
                    bj.put("jammasuk", jammasukslot1);
                    bj.put("jamkeluar", jamkeluarslot1);
                    bj.put("tanggal",tanggal);

                    bj.put("kompetensi",null);
                    bj.put("dosen",null);
                    bj.put("listMhs",null);
                    //j++;
                    slot.add(bj);

                    cj.put("id",3);
                    cj.put("jammasuk", jammasukslot1);
                    cj.put("jamkeluar", jamkeluarslot1);
                    cj.put("tanggal",tanggal);

                    cj.put("kompetensi",null);
                    cj.put("dosen",null);
                    cj.put("listMhs",null);
                     //j++;
                    slot.add(cj);

                    jj.put("id",4);
                    jj.put("jammasuk", jammasukslot2);
                    jj.put("jamkeluar", jamkeluarslot2);
                    jj.put("tanggal",tanggal);

                    jj.put("kompetensi",null);
                    jj.put("dosen",null);
                    jj.put("listMhs",null);
                     //j++;
                    slot.add(jj);

                    kj.put("id",5);
                    kj.put("jammasuk", jammasukslot2);
                    kj.put("jamkeluar", jamkeluarslot2);
                    kj.put("tanggal",tanggal);

                    kj.put("kompetensi",null);
                    kj.put("dosen",null);
                    kj.put("listMhs",null);
                     //j++;
                    slot.add(kj);

                    lj.put("id",6);
                    lj.put("jammasuk", jammasukslot2);
                    lj.put("jamkeluar", jamkeluarslot2);
                    lj.put("tanggal",tanggal);

                    lj.put("kompetensi",null);
                    lj.put("dosen",null);
                    lj.put("listMhs",null);
                     //j++;
                    slot.add(lj);

                    dj.put("id",7);
                    dj.put("jammasuk", jammasukslot3);
                    dj.put("jamkeluar", jamkeluarslot3);
                    dj.put("tanggal",tanggal);

                    dj.put("kompetensi",null);
                    dj.put("dosen",null);
                    dj.put("listMhs",null);
                    //j++;
                    slot.add(dj);

                    ej.put("id",8);
                    ej.put("jammasuk", jammasukslot3);
                    ej.put("jamkeluar", jamkeluarslot3);
                    ej.put("tanggal",tanggal);

                    ej.put("kompetensi",null);
                    ej.put("dosen",null);
                    ej.put("listMhs",null);
                     //j++;
                    slot.add(ej);

                    fj.put("id",9);
                    fj.put("jammasuk", jammasukslot3);
                    fj.put("jamkeluar", jamkeluarslot3);
                    fj.put("tanggal",tanggal);

                    fj.put("kompetensi",null);
                    fj.put("dosen",null);
                    fj.put("listMhs",null);
                    //j++;
                    slot.add(fj);

                    gj.put("id",10);
                    gj.put("jammasuk", jammasukslot4);
                    gj.put("jamkeluar", jamkeluarslot4);
                    gj.put("tanggal",tanggal);

                    gj.put("kompetensi",null);
                    gj.put("dosen",null);
                    gj.put("listMhs",null);
                    //j++;
                    slot.add(gj);

                    hj.put("id",11);
                    hj.put("jammasuk", jammasukslot4);
                    hj.put("jamkeluar", jamkeluarslot4);
                    hj.put("tanggal",tanggal);

                    hj.put("kompetensi",null);
                    hj.put("dosen",null);
                    hj.put("listMhs",null);
                    //j++;
                    slot.add(hj);

                    ij.put("id",12);
                    ij.put("jammasuk", jammasukslot4);
                    ij.put("jamkeluar", jamkeluarslot4);
                    ij.put("tanggal",tanggal);

                    ij.put("kompetensi",null);
                    ij.put("dosen",null);
                    ij.put("listMhs",null);
                    //j++;
                    slot.add(ij);
                           
                }
            }
                    
        }
        
        root.put("slot",slot);
        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriSlot)) {
            file.write(root.toJSONString());
            file.flush();
        } catch (IOException e) {
        }
    }
      
public void BangkitkanAlokasiJadwal(int minggu) {
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray slot= new JSONArray();
        JSONArray array = null;
       // int countHari = 1;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKalender));
            array= (JSONArray) objFromFile.get("kalender");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (minggu == 1) {
                     //awal for
        for ( int i = 0; i <= 6; i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            if (!libur) {
                if (hari.equals("Jumat")) {
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        aj.put("jam17kelas1",null);
                                        aj.put("jam17kelas2",null);
                                        aj.put("jam17kelas3",null);
                                        //j++;
                                        slot.add(aj);
                    
                
                }else{
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam11kelas1",null);
                                        aj.put("jam11kelas2",null);
                                        aj.put("jam11kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        //j++;
                                        slot.add(aj);
                }
//                
            }
                    
        }
        // batas for
                
        
        }else if (minggu == 2){
             //awal for
        for ( int i = 7; i <= 13; i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            if (!libur) {
                if (hari.equals("Jumat")) {
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        aj.put("jam17kelas1",null);
                                        aj.put("jam17kelas2",null);
                                        aj.put("jam17kelas3",null);
                                        //j++;
                                        slot.add(aj);
                    
                
                }else{
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam11kelas1",null);
                                        aj.put("jam11kelas2",null);
                                        aj.put("jam11kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        //j++;
                                        slot.add(aj);
                }
//                
            }
                    
        }
        // batas for
        
        }else if (minggu == 3){
             //awal for
        for ( int i = 14; i <= 20; i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            if (!libur) {
                if (hari.equals("Jumat")) {
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        aj.put("jam17kelas1",null);
                                        aj.put("jam17kelas2",null);
                                        aj.put("jam17kelas3",null);
                                        //j++;
                                        slot.add(aj);
                    
                
                }else{
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam11kelas1",null);
                                        aj.put("jam11kelas2",null);
                                        aj.put("jam11kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        //j++;
                                        slot.add(aj);
                }
//                
            }
                    
        }
        // batas for
        
        }else if (minggu == 4){
             //awal for
        for ( int i = 21; i <= 27; i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            if (!libur) {
                if (hari.equals("Jumat")) {
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        aj.put("jam17kelas1",null);
                                        aj.put("jam17kelas2",null);
                                        aj.put("jam17kelas3",null);
                                        //j++;
                                        slot.add(aj);
                    
                
                }else{
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam11kelas1",null);
                                        aj.put("jam11kelas2",null);
                                        aj.put("jam11kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        //j++;
                                        slot.add(aj);
                }
//                
            }
                    
        }
        // batas for
        
        }else if (minggu == 5){
             //awal for
        for ( int i = 28; i <= 34; i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            if (!libur) {
                if (hari.equals("Jumat")) {
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        aj.put("jam17kelas1",null);
                                        aj.put("jam17kelas2",null);
                                        aj.put("jam17kelas3",null);
                                        //j++;
                                        slot.add(aj);
                    
                
                }else{
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam11kelas1",null);
                                        aj.put("jam11kelas2",null);
                                        aj.put("jam11kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        //j++;
                                        slot.add(aj);
                }
//                
            }
                    
        }
        // batas for
        
        }else if (minggu == 6){
             //awal for
        for ( int i = 35; i <= 41; i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            if (!libur) {
                if (hari.equals("Jumat")) {
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        aj.put("jam17kelas1",null);
                                        aj.put("jam17kelas2",null);
                                        aj.put("jam17kelas3",null);
                                        //j++;
                                        slot.add(aj);
                    
                
                }else{
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam11kelas1",null);
                                        aj.put("jam11kelas2",null);
                                        aj.put("jam11kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        //j++;
                                        slot.add(aj);
                }
//                
            }
                    
        }
        // batas for
        
        }else if (minggu == 7){
             //awal for
        for ( int i = 42; i <= 48; i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            if (!libur) {
                if (hari.equals("Jumat")) {
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        aj.put("jam17kelas1",null);
                                        aj.put("jam17kelas2",null);
                                        aj.put("jam17kelas3",null);
                                        //j++;
                                        slot.add(aj);
                    
                
                }else{
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam11kelas1",null);
                                        aj.put("jam11kelas2",null);
                                        aj.put("jam11kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        //j++;
                                        slot.add(aj);
                }
//                
            }
                    
        }
        // batas for
        
        }else if (minggu == 8){
             //awal for
        for ( int i = 49; i <= 55; i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            if (!libur) {
                if (hari.equals("Jumat")) {
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        aj.put("jam17kelas1",null);
                                        aj.put("jam17kelas2",null);
                                        aj.put("jam17kelas3",null);
                                        //j++;
                                        slot.add(aj);
                    
                
                }else{
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam11kelas1",null);
                                        aj.put("jam11kelas2",null);
                                        aj.put("jam11kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        //j++;
                                        slot.add(aj);
                }
//                
            }
                    
        }
        // batas for
        
        }else if (minggu == 9){
             //awal for
        for ( int i = 56; i <= 62; i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            if (!libur) {
                if (hari.equals("Jumat")) {
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        aj.put("jam17kelas1",null);
                                        aj.put("jam17kelas2",null);
                                        aj.put("jam17kelas3",null);
                                        //j++;
                                        slot.add(aj);
                    
                
                }else{
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam11kelas1",null);
                                        aj.put("jam11kelas2",null);
                                        aj.put("jam11kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        //j++;
                                        slot.add(aj);
                }
//                
            }
                    
        }
        // batas for
        
        }else if (minggu == 10){
             //awal for
        for ( int i = 63; i <= 69; i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            if (!libur) {
                if (hari.equals("Jumat")) {
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        aj.put("jam17kelas1",null);
                                        aj.put("jam17kelas2",null);
                                        aj.put("jam17kelas3",null);
                                        //j++;
                                        slot.add(aj);
                    
                
                }else{
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam11kelas1",null);
                                        aj.put("jam11kelas2",null);
                                        aj.put("jam11kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        //j++;
                                        slot.add(aj);
                }
//                
            }
                    
        }
        // batas for
        
        }else if (minggu == 11){
             //awal for
        for ( int i = 70; i <= 76; i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            JSONObject aj = new JSONObject();
            boolean libur = (boolean) itemArr.get("libur");
            String hari = (String) itemArr.get("hari");
            String tanggal = (String) itemArr.get("tanggal");
            if (!libur) {
                if (hari.equals("Jumat")) {
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        aj.put("jam17kelas1",null);
                                        aj.put("jam17kelas2",null);
                                        aj.put("jam17kelas3",null);
                                        //j++;
                                        slot.add(aj);
                    
                
                }else{
                                        aj.put("tanggal",tanggal);
                                        aj.put("hari",hari);
                                        aj.put("jam7kelas1",null);
                                        aj.put("jam7kelas2",null);
                                        aj.put("jam7kelas3",null);
                                        aj.put("jam8kelas1",null);
                                        aj.put("jam8kelas2",null);
                                        aj.put("jam8kelas3",null);
                                        aj.put("jam9kelas1",null);
                                        aj.put("jam9kelas2",null);
                                        aj.put("jam9kelas3",null);
                                        aj.put("jam10kelas1",null);
                                        aj.put("jam10kelas2",null);
                                        aj.put("jam10kelas3",null);
                                        aj.put("jam11kelas1",null);
                                        aj.put("jam11kelas2",null);
                                        aj.put("jam11kelas3",null);
                                        aj.put("jam13kelas1",null);
                                        aj.put("jam13kelas2",null);
                                        aj.put("jam13kelas3",null);
                                        aj.put("jam14kelas1",null);
                                        aj.put("jam14kelas2",null);
                                        aj.put("jam14kelas3",null);
                                        aj.put("jam15kelas1",null);
                                        aj.put("jam15kelas2",null);
                                        aj.put("jam15kelas3",null);
                                        aj.put("jam16kelas1",null);
                                        aj.put("jam16kelas2",null);
                                        aj.put("jam16kelas3",null);
                                        //j++;
                                        slot.add(aj);
                }
//                
            }
                    
        }
        // batas for
        
        }
        
          root.put("minggu"+minggu,slot);
         try (FileWriter file = new FileWriter(ConfigDirektori.direktoriSlot+"jadwal"+minggu+".json")) {
                      file.write(root.toJSONString());
                      file.flush();
                    } catch (IOException e) {
                    }
      
    }
      
      

    public ArrayList<Kompetensi> getK() {
        return k;
    }

    public void InsertDosenToJadwalJson() {
        JSONObject rootJadwal = new JSONObject();
        JSONObject rootKompetensi = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray slotJadwal= new JSONArray();
        JSONArray slotKompetensi= new JSONArray();
        JSONArray arrayJadwal = null;
        JSONArray arrayDosen = null;
        JSONArray arrayKompetensi = null;
        boolean found = false;
        String id;
        int i, j, k;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriSlot));
            arrayJadwal = (JSONArray) objFromFile.get("slot");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            arrayDosen = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
            arrayKompetensi = (JSONArray) objFromFile.get("kompetensi");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Jadwal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        boolean finished = false;
        for (i = 0; i < arrayDosen.size(); i++) {   
            System.out.println("1");
            
            JSONObject itemArrDosen = (JSONObject)arrayDosen.get(i);
            ArrayList<String> libur = (ArrayList<String>) itemArrDosen.get("libur");
            System.out.println(itemArrDosen.get("nama"));
                               
            // perulangan dosen
            finished = false;
            for (int l = 0; l < arrayJadwal.size() && finished == false; l++) {
                System.out.println("2");
                JSONObject itemArrJadwal = (JSONObject)arrayJadwal.get(l);
                JSONObject jo = new JSONObject();
                String tanggal = (String) itemArrJadwal.get("tanggal");
                // cek dosen punya libur

                //System.out.println(libur);
                long role = (long) itemArrDosen.get("role");
                //System.out.println(role);
                if (libur != null&& role == 2) {
                    System.out.println("3");
                    // perulangan array dosen libur dengan tanggal jadwal
                    System.out.println("size libur adalah:"+libur.size());
                    for (int m = 0; m < libur.size() && finished == false; m++) {
                        System.out.println("4");
                        System.out.println(libur.get(m));

                        // cek dosen libur nggak di hari yg ada di jadwal
                        if (!libur.get(m).equals(tanggal) ) {
                            System.out.println("5");
                            ArrayList <String> kompetensiDosen = (ArrayList <String>) itemArrDosen.get("kompetensi");
                            // perulangan list semua kompetensi
                            for (int n = 0; n < arrayKompetensi.size() && finished == false; n++) {
                                System.out.println("6");
                                JSONObject itemArrKompetensi = (JSONObject)arrayKompetensi.get(n);
                                System.out.println(itemArrKompetensi.get("id"));
                                System.out.println(kompetensiDosen.get(m));
                                // cek kompetensi dosen ada nggak di list kompetensi
                                if(kompetensiDosen.get(m).equals(itemArrKompetensi.get("id"))) {
                                    System.out.println("7");
                                    boolean hasAllocated = (boolean) itemArrKompetensi.get("hasAllocated");
                                    long sks = (long) itemArrKompetensi.get("sks");
                                    System.out.println("sks adalah" +sks);

                                    // cek kompetensi dosen udah di ajarkan sama dosen lain atau belum
                                    if (!hasAllocated) {
                                        System.out.println("8");
                                        // cek apakah 3 sks 
                                        if (sks == 3) {
                                            System.out.println("9");

                                            // perulangan kelas 3 sks 
                                            long jamMasuk = (long) itemArrJadwal.get("jammasuk");
                                            long jamSelesai = (long) itemArrJadwal.get("jamkeluar");
                                            String kompeten = (String) itemArrJadwal.get("kompetensi");
                                            System.out.println("jammasuk"+jamMasuk);
                                            System.out.println("jamselesai"+jamSelesai);
                                            System.out.println("kompetensi"+kompeten);
                                            while (jamSelesai-jamMasuk == 3 &&  kompeten == null && finished == false) {
                                                System.out.println("10");
                                                if(itemArrJadwal.get("kompetensi")==null){
                                                    System.out.println("11");
                                                    JSONObject uo = new JSONObject();
                                                    JSONObject ro = new JSONObject();
                                                    System.out.println("tanggal "+itemArrJadwal.get("tanggal"));
                                                    System.out.println("idjadwal "+itemArrJadwal.get("id"));
                                                    System.out.println("idkelas "+itemArrKompetensi.get("id"));
                                                    System.out.println("dosen "+itemArrDosen.get("username"));
                                                    itemArrJadwal.put("kompetensi", itemArrKompetensi.get("id"));
                                                    itemArrJadwal.put("dosen", itemArrDosen.get("username"));
                                                    itemArrJadwal.put("listMhs", null);
                                                    itemArrJadwal.put("jammasuk", itemArrJadwal.get("jammasuk"));
                                                    itemArrJadwal.put("jamkeluar", itemArrJadwal.get("jamkeluar"));
                                                    itemArrJadwal.put("tanggal", itemArrJadwal.get("tanggal"));
                                                    itemArrJadwal.put("id", itemArrJadwal.get("id"));


                                                    itemArrKompetensi.put("nama", itemArrKompetensi.get("nama"));
                                                    itemArrKompetensi.put("biaya", itemArrKompetensi.get("biaya"));
                                                    itemArrKompetensi.put("sks", itemArrKompetensi.get("sks"));
                                                    itemArrKompetensi.put("hasPraktikum", itemArrKompetensi.get("hasPraktikum"));
                                                    itemArrKompetensi.put("hasAllocated",true);
                                                    itemArrKompetensi.put("prasyarat",itemArrKompetensi.get("prasyarat"));

                                                    // slotJadwal.add(uo);
                                                    //slotKompetensi.add(ro);
                                                    finished = true;
                                                }
                                            }
                                            // cek apakah kelas [1,2,3] udah kepake atau nggak
                                            String kompetensiJadwal = (String) itemArrJadwal.get("kompetensi");
                                            // alokasikan kompetensi dan dosen ke kelas yg ksong di kelas 3 sks
                                        }
                                        else{
                                            System.out.println("12");
                                            long jamMasuk = (long) itemArrJadwal.get("jammasuk");
                                            long jamSelesai = (long) itemArrJadwal.get("jamkeluar");
                                            String kompeten = (String) itemArrJadwal.get("kompetensi");
                                            System.out.println("jammasuk"+jamMasuk);
                                            System.out.println("jamselesai"+jamSelesai);
                                            System.out.println("kompetensi"+kompeten);
                                            while(jamSelesai-jamMasuk == 2 &&  kompeten == null && finished == false){
                                                System.out.println("13");
                                                if(itemArrJadwal.get("kompetensi")==null){
                                                    System.out.println("14");
                                                    //kompetensi masuk
                                                    JSONObject uo = new JSONObject();
                                                    JSONObject ro = new JSONObject();
                                                    System.out.println("tanggal "+itemArrJadwal.get("tanggal"));
                                                    System.out.println("idjadwal "+itemArrJadwal.get("id"));
                                                    System.out.println("idkelas "+itemArrKompetensi.get("id"));
                                                    System.out.println("dosen "+itemArrDosen.get("username"));
                                                    itemArrJadwal.put("kompetensi", itemArrKompetensi.get("id"));
                                                    itemArrJadwal.put("dosen", itemArrDosen.get("username"));
                                                    itemArrJadwal.put("listMhs", null);
                                                    itemArrJadwal.put("jammasuk", itemArrJadwal.get("jammasuk"));
                                                    itemArrJadwal.put("jamkeluar", itemArrJadwal.get("jamkeluar"));
                                                    itemArrJadwal.put("tanggal", itemArrJadwal.get("tanggal"));
                                                    itemArrJadwal.put("id", itemArrJadwal.get("id"));


                                                    itemArrKompetensi.put("nama", itemArrKompetensi.get("nama"));
                                                    itemArrKompetensi.put("biaya", itemArrKompetensi.get("biaya"));
                                                    itemArrKompetensi.put("sks", itemArrKompetensi.get("sks"));
                                                    itemArrKompetensi.put("hasPraktikum", itemArrKompetensi.get("hasPraktikum"));
                                                    itemArrKompetensi.put("hasAllocated",true);
                                                    itemArrKompetensi.put("prasyarat",itemArrKompetensi.get("prasyarat"));

                                                    // slotJadwal.add(uo);
                                                    //slotKompetensi.add(ro);
                                                    finished = true;
                                                }
                                                // perulangan kelas 2 sks
                                                // cek apakah kelas [1,2,3] udah kepake atau nggak [ingat , kelas 2 sks ada 3 slot tiap hari]
                                                // alokasikan kompetensi dan dosen ke kelas yg ksong di kelas 2 sks
                                            }
                                        }
                                    }
                                    else{
                                        System.out.println("kompetensi dosen tidak sesuai dengan list kompetensi");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        rootJadwal.put("slot",arrayJadwal);
        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriSlot)) {
            file.write(rootJadwal.toJSONString());
            file.flush();
        } catch (IOException e) {
        }
         
        rootKompetensi.put("kompetensi",arrayKompetensi);
        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriKompetensi)) {
           file.write(rootKompetensi.toJSONString());
           file.flush();
        } catch (IOException e) {
        }
    }  
}
