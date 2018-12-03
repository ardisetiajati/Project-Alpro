/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Ardiansyah Setiajati
 */
public class Tagihan {
    String idMahasiswa;
    String namaMahasiswa;
    long jumlahTagihan;
    boolean sudahDibayar;
    private ArrayList<String> kompetensiDiambil;
    private ArrayList<Long> tarifKompetensiDiambil;

    public Tagihan() {
        kompetensiDiambil = new ArrayList<String>();
        tarifKompetensiDiambil = new ArrayList<Long>();
    }

    public Tagihan(String idMahasiswa, String namaMahasiswa, long jumlahTagihan, boolean sudahDibayar, ArrayList<String> kompetensiDiambil, ArrayList<Long> tarifKompetensiDiambil) {
        this.idMahasiswa = idMahasiswa;
        this.namaMahasiswa = namaMahasiswa;
        this.jumlahTagihan = jumlahTagihan;
        this.sudahDibayar = sudahDibayar;
        this.kompetensiDiambil = kompetensiDiambil;
        this.tarifKompetensiDiambil = tarifKompetensiDiambil;
    }

    public Tagihan(String idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }
    
    public String getIdMahasiswa() {
        return idMahasiswa;
    }

    public void setIdMahasiswa(String idMahasiswa) {
        this.idMahasiswa = idMahasiswa;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public long getJumlahTagihan() {
        return jumlahTagihan;
    }

    public void setJumlahTagihan(long jumlahTagihan) {
        this.jumlahTagihan = jumlahTagihan;
    }

    public boolean isSudahDibayar() {
        return sudahDibayar;
    }

    public void setSudahDibayar(boolean sudahDibayar) {
        this.sudahDibayar = sudahDibayar;
    }

    public ArrayList<String> getKompetensiDiambil() {
        return kompetensiDiambil;
    }

    public void setKompetensiDiambil(ArrayList<String> kompetensiDiambil) {
        this.kompetensiDiambil = kompetensiDiambil;
    }

    public ArrayList<Long> getTarifKompetensiDiambil() {
        return tarifKompetensiDiambil;
    }

    public void setTarifKompetensiDiambil(ArrayList<Long> tarifKompetensiDiambil) {
        this.tarifKompetensiDiambil = tarifKompetensiDiambil;
    }
    
    public void TulisTagihanToJson() {
        JSONObject root = new JSONObject();
        JSONArray tagihan = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        File f = new File(ConfigDirektori.direktoriTagihan);

        if (f.exists() && !f.isDirectory()) {

            try {
                JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriTagihan));
                array = (JSONArray) objFromFile.get("tagihan");
                //array = (JSONArray) read;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(Tagihan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Create JSONObject and JSONArray and store a class object on it
        JSONObject uo = new JSONObject();
        //JSONObject ro = new JSONObject();
        uo.put("idMahasiswa", idMahasiswa);
        uo.put("namaMahasiswa", namaMahasiswa);
        uo.put("jumlahTagihan", jumlahTagihan);
        uo.put("sudahDibayar", sudahDibayar);

        JSONArray listOfKompetensiDiambil = new JSONArray();
        for (int i = 0; i < kompetensiDiambil.size(); i++) {
            listOfKompetensiDiambil.add(kompetensiDiambil.get(i));
        }
                
        uo.put("kompetensiDiambil", listOfKompetensiDiambil);
        
        JSONArray listOfTarifKompetensiDiambil = new JSONArray();
        for (int i = 0; i < tarifKompetensiDiambil.size(); i++) {
            listOfTarifKompetensiDiambil.add(tarifKompetensiDiambil.get(i));
        }
        uo.put("tarifKompetensiDiambil", listOfTarifKompetensiDiambil);
        
        
        if (f.exists() && !f.isDirectory()) {
            array.add(uo);
            // add the array to the root object
            root.put("tagihan", array);

        } else {
            tagihan.add(uo);
            // add the array to the root object
            root.put("tagihan", tagihan);
        }

        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriTagihan)) {
            file.write(root.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void ReadAllTagihanFromJson() {
        JSONObject root = new JSONObject();
        JSONArray tagihan = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        File f = new File(ConfigDirektori.direktoriTagihan);

        if (f.exists() && !f.isDirectory()) {
            try {
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriTagihan));

                JSONObject jsonObject = (JSONObject) obj;
                array = (JSONArray) jsonObject.get("tagihan");
                
                String tbl = "| %-2s | %-5s | %-10s | %-27s | %-13s | %-20s |%n";
                System.out.format("%nNama%n");
                System.out.format("+----+-------+------------+-----------------------------+---------------+----------------------|%n");
                System.out.format("| No | Lunas | NIM        | Nama                        | Total Tagihan | Jumlah Kelas Diambil |%n");
                System.out.format("+----|-------+------------+-----------------------------+---------------+----------------------|%n");
               
                for (int i = 0; i < array.size(); i++) {
                    
                    // get all JSON Object
                    JSONObject itemArr = (JSONObject) array.get(i);
                    
                    String idMahasiswa = (String) itemArr.get("idMahasiswa");
                    String namaMahasiswa = (String) itemArr.get("namaMahasiswa");
                    long a = (long) itemArr.get("jumlahTagihan");
                    String jumlahTagihan = String.format("%,d", a);
                    boolean sudahDibayar = (boolean) itemArr.get("sudahDibayar");
                    JSONArray daftarPeserta = (JSONArray) itemArr.get("kompetensiDiambil");
                    
                    System.out.format(tbl, i + 1, sudahDibayar, idMahasiswa, namaMahasiswa,jumlahTagihan,daftarPeserta.size());
                }
                System.out.format("+----+-------+------------+-----------------------------+---------------+----------------------|%n");
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(Tagihan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void ReadTagihanForThisNIMFromJSON(String idMahasiswa){
        JSONObject root = new JSONObject();
        JSONArray tagihan = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        File f = new File(ConfigDirektori.direktoriTagihan);

        if (f.exists() && !f.isDirectory()) {
            try {
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriTagihan));

                JSONObject jsonObject = (JSONObject) obj;
                array = (JSONArray) jsonObject.get("tagihan");
                
                
                int indexFound = 0;
                for (int i = 0; i < array.size(); i++) {
                    JSONObject itemArr = (JSONObject) array.get(i);
                    String itemIdMahasiswa = (String) itemArr.get("idMahasiswa");
                    
                    if(itemIdMahasiswa.equals(idMahasiswa))
                        indexFound = i;
                }
                
                JSONObject foundedItem = (JSONObject) array.get(indexFound);
                
                String namaMahasiswa = (String) foundedItem.get("namaMahasiswa");
                long a = (long) foundedItem.get("jumlahTagihan");
                String jumlahTagihan = String.format("%,d", a);
                boolean sudahDibayar = (boolean) foundedItem.get("sudahDibayar");
                JSONArray kompetensiDiambil = (JSONArray) foundedItem.get("kompetensiDiambil");
                JSONArray tarifKompetensiDiambil = (JSONArray) foundedItem.get("tarifKompetensiDiambil");
                //String tarifKompetensiDiambil = String.format("%,d", b);
                
                
                System.out.println("\nData Tagihan Mahasiswa\n---------------");
                System.out.println("NIM           : "+idMahasiswa);
                System.out.println("Nama          : "+namaMahasiswa);
                System.out.println("Total Tagihan : "+jumlahTagihan);
                System.out.println("Sudah Dibayar : "+sudahDibayar);

                
                String tbl = "| %-2s | %-10s | %-15s |%n";
                System.out.format("Detail Tagihan\n");
                System.out.format("+----+------------+-----------------|%n");
                System.out.format("| No | Kompetensi |     Tagihan     |%n");
                System.out.format("+----+------------+-----------------|%n");
               
                for (int i = 0; i < kompetensiDiambil.size(); i++) {   
                    System.out.format(tbl, i + 1,kompetensiDiambil.get(i),tarifKompetensiDiambil.get(i));
                }
                System.out.format("+---+------------+-----------------|%n");
                System.out.format("           Total : "+jumlahTagihan);
                System.out.println("");
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(Tagihan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public boolean CekTagihanFromJson() {
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        boolean found = false;

        try {
            JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriTagihan));
            array = (JSONArray) objFromFile.get("tagihan");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject) array.get(i);

            if (itemArr.get("idMahasiswa").equals(idMahasiswa)) {
                found = true;
            }
        }
        return found;
    }
    
    public void ValidasiTagihanFromJson() {
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        try {
            JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriTagihan));
            array = (JSONArray) objFromFile.get("tagihan");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject) array.get(i);

            if (itemArr.get("idMahasiswa").equals(idMahasiswa)) {           
                    itemArr.put("sudahDibayar", true);
    
            }
            root.put("tagihan", array);
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriTagihan)) {

                file.write(root.toJSONString());
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
