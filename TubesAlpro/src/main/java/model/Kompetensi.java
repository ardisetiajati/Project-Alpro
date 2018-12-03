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
 * 
 * 
 * 
 * @author Ardiansyah Setiajati
 */
public class Kompetensi {

    private String id;
    private String nama;
    private ArrayList<Kompetensi> prasyarat = new ArrayList<Kompetensi>();
    private int sks;
    private boolean hasPraktikum;
    private long biaya;
    //private Kelas tatapMuka;
    //private Kelas Praktikum;
    //jam
    //hari

    public Kompetensi(String id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public Kompetensi() {
    }

    public Kompetensi(String id) {
        this.id = id;
    }

    public Kompetensi(String id, String nama, ArrayList<Kompetensi> prasyarat, int sks, boolean hasPraktikum) {
        this.id = id;
        this.nama = nama;
        this.prasyarat = prasyarat;
        this.sks = sks;
        this.hasPraktikum = hasPraktikum;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public ArrayList<Kompetensi> getPrasyarat() {
        return prasyarat;
    }

    public boolean isHasPraktikum() {
        return hasPraktikum;
    }

    public int getSks() {
        return sks;
    }

    public long getBiaya() {
        return biaya;
    }
    

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPrasyarat(ArrayList<Kompetensi> prasyarat) {
        this.prasyarat = prasyarat;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public void setHasPraktikum(boolean hasPraktikum) {
        this.hasPraktikum = hasPraktikum;
    }

    public void setBiaya(long biaya) {
        this.biaya = biaya;
    }
    
    

    public void ReadKompetensiFromJson() {
        JSONObject root = new JSONObject();
        JSONArray kompetensi = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        File f = new File(ConfigDirektori.direktoriKompetensi);

        if (f.exists() && !f.isDirectory()) {
            try {
                //JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));

                JSONObject jsonObject = (JSONObject) obj;
                //JSONObject jsonObject2 = (JSONObject) jsonObject.get("kompetensi");
                array = (JSONArray) jsonObject.get("kompetensi");

                String tbl = "| %-3s | %-14s | %-48s | %-28s | %-7s |%n";
                System.out.format("+-----+----------------+--------------------------------------------------+------------------------------+---------+%n");
                System.out.format("| No  | Id Kompetensi  | Kompetensi                                       | prasyarat                    | Bobot   |%n");
                System.out.format("+-----+----------------+--------------------------------------------------+------------------------------+---------+%n");
                //System.out.println("No" +"\t|\t"+ "Id Kompetensi " + "\t|\t"+ "Kompetensi" +"\t\t\t\t\t|\t\t" + "prasyarat" +"\t|\t"+ "Bobot" +"\t|");
                for (int i = 0; i < array.size(); i++) {
                    char kode;
                    // get all JSON Object
                    JSONObject itemArr = (JSONObject) array.get(i);
                    String nama = (String) itemArr.get("nama");
                    String id = (String) itemArr.get("id");
                    long bobot = (long) itemArr.get("sks");
                    boolean hasPraktikum = (boolean) itemArr.get("hasPraktikum");
                    // loop array
                    JSONArray msg = (JSONArray) itemArr.get("prasyarat");
                    Iterator<String> iterator = msg.iterator();

                    // nama Kompetensi [M/P]
                    if (!hasPraktikum) {
                        kode = 'M';
                    } else {
                        kode = 'P';
                    }
                    // print all JSONObject
                    //System.out.print(i+1 + "\t|\t");
                    //System.out.print(id +"\t\t|\t");
                    //System.out.print(nama  + "["+ kode+ "]"+"\t\t\t\t|\t");
                    //while (iterator.hasNext()) {
                    //   System.out.print(iterator.next() + ", ");
                    //}
                    //System.out.print("\t|\t");
                    //System.out.print(bobot +"\t|");

                    System.out.format(tbl, i + 1, id, nama + " [" + kode + "]", msg, bobot);
                }
                System.out.format("+-----+----------------+--------------------------------------------------+------------------------------+---------+%n");
                System.out.println("");
                //System.out.println(array);

                //String name = (String) jsonObject.get("name");
                // System.out.println(name);
                //long age = (Long) jsonObject.get("age");
                //System.out.println(age);
                // loop array
                //JSONArray msg = (JSONArray) jsonObject.get("messages");
                //Iterator<String> iterator = msg.iterator();
                //while (iterator.hasNext()) {
                //   System.out.println(iterator.next());
                //}
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /*
        
                System.out.printf("No");
                System.out.printf("Kompetensi");
                System.out.printf("Prasyarat");
                System.out.printf("Bobot");
            
        
        for (int i = 0; i < array.size(); i++) {
             char kode;
            JSONObject itemArr = (JSONObject)array.get(i);
                // No
                System.out.printf("%d", i+1);
                
                // nama Kompetensi [M/P]
                if (itemArr.get("haspraktikum").toString().equals("false")) {
                    kode ='M';
                } else {
                    kode = 'P';
                }
                System.out.printf("%s  [%c]",itemArr.get("nama"), kode);
                
                // Prasyarat
                 JSONObject KompetensiArr = (JSONObject) itemArr.get("prasyarat");
                for (int j = 0; j < KompetensiArr.size(); j++) {
                    System.out.printf("%s", KompetensiArr.get(j));
        }
                
                // SKS
                System.out.printf("%d", sks);
            
        }
         */
    }

    public void TulisKompetensiToJson() {
        JSONObject root = new JSONObject();
        JSONArray kompetensi = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        File f = new File(ConfigDirektori.direktoriKompetensi);

        if (f.exists() && !f.isDirectory()) {

            try {
                JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
                array = (JSONArray) objFromFile.get("kompetensi");
                //array = (JSONArray) read;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Create JSONObject and JSONArray and store a class object on it
        JSONObject uo = new JSONObject();
        //JSONObject ro = new JSONObject();
        uo.put("id", id);
        uo.put("nama", nama);

        JSONArray list = new JSONArray();
        for (int i = 0; i < prasyarat.size(); i++) {
            list.add(prasyarat.get(i).id);
        }
        uo.put("prasyarat", list);
        uo.put("sks", sks);
        uo.put("hasPraktikum", hasPraktikum);

        if (hasPraktikum) {
            biaya = 750000 * sks;
        } else {
            biaya = 500000 * sks;
        }

        uo.put("biaya", biaya);

        //ro.put("username", username);
        //ro.put("password", password);
        if (f.exists() && !f.isDirectory()) {
            array.add(uo);
            // add the array to the root object
            root.put("kompetensi", array);

        } else {
            kompetensi.add(uo);
            // add the array to the root object
            root.put("kompetensi", kompetensi);
        }

        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriKompetensi)) {
            file.write(root.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public JSONArray getListPrasyaratFromJson(String kode) {
        JSONObject root = new JSONObject();
        JSONArray prasyarat= new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        JSONArray listPrasyarat = new JSONArray();

        File f = new File(ConfigDirektori.direktoriKompetensi);
        
        if (f.exists() && !f.isDirectory()) {
            try {
                //JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));

                JSONObject jsonObject = (JSONObject) obj;
                //JSONObject jsonObject2 = (JSONObject) jsonObject.get("kompetensi");
                array = (JSONArray) jsonObject .get("kompetensi");

                for (int i = 0; i < array.size(); i++) {
                    JSONObject itemArr = (JSONObject)array.get(i);
                    //String id =  (String) itemArr.get("id");
            
                    if(itemArr.get("id").equals(kode)){
                        listPrasyarat.add(itemArr.get("prasyarat"));
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
        return listPrasyarat;
    }
    
    public boolean CekKompetensiFromJson() {
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        boolean found = false;

        try {
            JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
            array = (JSONArray) objFromFile.get("kompetensi");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject) array.get(i);

            if (itemArr.get("id").equals(id)) {
                found = true;
            }
        }
        return found;
    }

    public void EditKompetensiFromJson() {
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        try {
            JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
            array = (JSONArray) objFromFile.get("kompetensi");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject) array.get(i);

            if (itemArr.get("id").equals(id)) {
                if (nama != null) {
                    itemArr.put("nama", nama);
                } else if (prasyarat != null) {
                    JSONArray list = new JSONArray();
                    for (int j = 0; j < prasyarat.size(); j++) {
                        list.add(prasyarat.get(j).getId());
                    }
                    itemArr.put("prasyarat", list);
                    //itemArr.put("prasyarat", prasyarat);
                } else if (sks != 0) {
                    itemArr.put("sks", sks);
                }
            }
            root.put("kompetensi", array);
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriKompetensi)) {

                file.write(root.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void EditPrasyaratKompetensiFromJson() {
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        try {
            JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
            array = (JSONArray) objFromFile.get("kompetensi");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject) array.get(i);

            if (itemArr.get("id").equals(id)) {
                itemArr.put("prasyarat", prasyarat);
            }
            root.put("kompetensi", array);
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriKompetensi)) {
                file.write(root.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void HapusKompetensiFromJson() {
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        JSONArray arrayDeleted = new JSONArray();

        try {
            JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
            array = (JSONArray) objFromFile.get("kompetensi");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject) array.get(i);

            if (!itemArr.get("id").equals(id)) {
                JSONObject uo = new JSONObject();
                //JSONObject ro = new JSONObject();
                uo.put("id", itemArr.get("id"));
                uo.put("nama", itemArr.get("nama"));
                uo.put("prasyarat", itemArr.get("prasyarat"));
                uo.put("sks", itemArr.get("sks"));
                uo.put("hasPraktikum", itemArr.get("hasPraktikum"));
                uo.put("biaya", itemArr.get("biaya"));
                uo.put("hasAllocated", itemArr.get("hasAllocated"));
                arrayDeleted.add(uo);
            }
        }
        root.put("kompetensi", arrayDeleted);

        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriKompetensi)) {
            file.write(root.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
