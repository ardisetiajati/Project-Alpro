package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    public void ControlMenuOptimasi() {
        viewKelas = new ViewKelas();
        viewKelas.menuOptimasi();

        switch (viewKelas.getPilihan()) {
            case 1:
                Optimasi1();
                break;
            case 2:
                Optimasi2();
                break;
        }
    }

    public void Optimasi1() {
        mahasiswa = new Mahasiswa();
        ArrayList<String> list = mahasiswa.getListMahasiswa();
    }

    public void Optimasi2() {
        dbOfKelas dbKelasAkhir = new dbOfKelas();
        dbOfTagihanMahasiswa dbTagihan = new dbOfTagihanMahasiswa();
        
        dbOfMahasiswaPerKompetensiPerPekerjaan dbNimKelasPekerjaanTarif = new dbOfMahasiswaPerKompetensiPerPekerjaan();
        dbOfPekerjaanAndKompetensi dbData = new dbOfPekerjaanAndKompetensi();
        dbOfMahasiswa dbMahasiswa = new dbOfMahasiswa();
        dbOfKompetensiAvailableDosen dbKompetensiAvailable = new dbOfKompetensiAvailableDosen();

        System.out.println("\n----------------------------Daftar Kompetensi");
        for (Kompetensi item : dbData.daftarKompetensiUtama) {
            System.out.println("id : " + item.getId() + " -> " + item.getBiaya() + " " + item.getNama() + " " + item.getPrasyarat().toString());
        }
        System.out.println("\n----------------------------Daftar Pekerjaan");
        for (Pekerjaan item : dbData.daftarPekerjaanUtama) {
            System.out.println("id " + item.getId() + " : " + item.getNama() + " " + item.getListKompetensi());
        }
        System.out.println("\n----------------------------Kompetensi Available di Dosen");
        for (structKompetensiWithItsDosen item : dbKompetensiAvailable.data) {
            if (item != null && item.kompetensi != null) {
                System.out.println("id dosen  nip:" + item.nipDosen + " " + item.kompetensi.getId());
            }
        }

        //iterasi utk setiap Mahasiswa, masukkan data nim beserta kompetensi diambil ke dataNimKompetensi <structMahasiswaWithKelasDetail>
        for (Mahasiswa mhsToDo : dbMahasiswa.daftarMahasiswaUtama) {
            //siapkan object container
            if (mhsToDo != null) {

                //iterasi utk setiap pekerjaan yg dipilih mahasiswa
                if (!mhsToDo.getPekerjaan().isEmpty()) {
                    for (Pekerjaan opsiPekerjaanMhs : mhsToDo.getPekerjaan()) {

                        //iterasi utk setiap kompetensi didalam pekerjaan
                        for (Kompetensi kompetensiPekerjaan : opsiPekerjaanMhs.getListKompetensi()) {
                            structMahasiswaWithKelasDetail newMhsData = new structMahasiswaWithKelasDetail();

                            // set atribut container
                            newMhsData.nim = mhsToDo.getNim();
                            newMhsData.nama = mhsToDo.getNama();
                            newMhsData.pekerjaan = opsiPekerjaanMhs;
                            newMhsData.kompetensi = kompetensiPekerjaan;
                            newMhsData.tarif = newMhsData.kompetensi.getBiaya();

                            //check apakah data sudah ada
                            if (!dbNimKelasPekerjaanTarif.isDataAlreadyExists(newMhsData)) //if not, add this data
                            {
                                dbNimKelasPekerjaanTarif.data.add(newMhsData);
                            }
                        }
                    }
                }

                //masukkan prasyarat
                //iterasi utk setiap prasyarat kompetensi yg akan diambil mahasiswa
                // not implemented
            }
        }
        
        //sort data tiap mahasasiswa berdasarkan tarif, terbesar paling awal
        dbNimKelasPekerjaanTarif.sortByTarifDesc();
        
        //masukkan data tiap NIM kelas pekerjaan tarif ke Tagihan
        for(structMahasiswaWithKelasDetail item: dbNimKelasPekerjaanTarif.data){
            
            int indexOnDbTagihan = dbTagihan.checkIndexForThisNim(item.nim);
            if(indexOnDbTagihan>-1){
                //getThat Tagihan Object, then edit
                dbTagihan.data.get(indexOnDbTagihan).setJumlahTagihan(dbTagihan.data.get(indexOnDbTagihan).getJumlahTagihan()+item.tarif);
                dbTagihan.data.get(indexOnDbTagihan).getKompetensiDiambil().add(item.kompetensi.getId());
                dbTagihan.data.get(indexOnDbTagihan).getTarifKompetensiDiambil().add(item.tarif);
            }
            else{
                //buat new Tagihan object
                Tagihan tgh = new Tagihan();
                
                tgh.setIdMahasiswa(item.nim);
                tgh.setNamaMahasiswa(item.nama);
                tgh.setSudahDibayar(false);
                tgh.setJumlahTagihan(item.tarif);
                tgh.getKompetensiDiambil().add(item.kompetensi.getId());
                tgh.getTarifKompetensiDiambil().add(item.kompetensi.getBiaya());
                
                dbTagihan.data.add(tgh);
            }
        }
        
        dbTagihan.printList();
        
        //buat kelas utk setiap Kompetensi Available
        
        for(structKompetensiWithItsDosen item: dbKompetensiAvailable.data){
            
        }
        
        for(Tagihan item: dbTagihan.data){
            
        }
    }
}


class dbOfTagihanMahasiswa{
    ArrayList<Tagihan> data;
    
    dbOfTagihanMahasiswa(){
        data = new ArrayList<Tagihan>();
    }
    
    public int checkIndexForThisNim(String nim){
        int index = -1;
        for(Tagihan item: data){
            index++;
            if(item.getIdMahasiswa().equals(nim)){
                return index;
            }
        }
        return -1;
    }
    
    public void printList(){
        System.out.println("\nDaftar Tagihan");
        for(Tagihan item: data){
            System.out.println("NIM : "+item.getIdMahasiswa()+"-> Total :"+item.getJumlahTagihan()+"\n\tKompetensi "+item.getKompetensiDiambil()+ "\n\tTarif Item " +item.getTarifKompetensiDiambil());
        }
    }
}

class dbOfKelas{
    ArrayList<Kelas> data;
    
    dbOfKelas(){
        data = new ArrayList<Kelas>();
    }
}

class dbOfPekerjaanAndKompetensi {

    ArrayList<Kompetensi> daftarKompetensiUtama;
    ArrayList<Pekerjaan> daftarPekerjaanUtama;

    public dbOfPekerjaanAndKompetensi() {
        //always fetch data when class is initialized;
        this.daftarKompetensiUtama = getKompetensiListFromJSON();
        this.daftarPekerjaanUtama = getPekerjaanListFromJSON();
    }

    public ArrayList<Pekerjaan> getPekerjaanListFromJSON() {
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
                array = (JSONArray) jsonObject.get("pekerjaan");

                for (int i = 0; i < array.size(); i++) {

                    Pekerjaan newPekerjaanToAdd = new Pekerjaan();
                    JSONObject itemArr = (JSONObject) array.get(i);

                    newPekerjaanToAdd.setId((String) itemArr.get("id"));
                    newPekerjaanToAdd.setNama((String) itemArr.get("nama"));

                    ArrayList<String> listOfIdKomp = (JSONArray) itemArr.get("kompetensi");
                    ArrayList<Kompetensi> listOfKompToAdd = new ArrayList<Kompetensi>();

                    for (String item : listOfIdKomp) {
                        if (item != null) {
                            listOfKompToAdd.add(getKompetensiObject(item));
                        }
                    }

                    newPekerjaanToAdd.setListKompetensi(listOfKompToAdd);

                    if (newPekerjaanToAdd.getListKompetensi() != null) {
                        listPekerjaanToReturn.add(newPekerjaanToAdd);
                    }
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

    public ArrayList<Kompetensi> getKompetensiListFromJSON() {
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
                    kompetensiNewToAdd.setBiaya((long) itemArr.get("biaya"));
                    kompetensiNewToAdd.setHasPraktikum((boolean) itemArr.get("hasPraktikum"));
                    Long sks = (long) itemArr.get("sks");
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

    public Kompetensi getKompetensiObject(String idKompetensi) {
        Kompetensi objectToReturn = new Kompetensi();

        for (Kompetensi item : daftarKompetensiUtama) {
            if (item.getId().equals(idKompetensi)) {
                objectToReturn = item;
            }
        }
        return objectToReturn;
    }

    public Pekerjaan getPekerjaanObject(String idPekerjaan) {
        Pekerjaan objectToReturn = new Pekerjaan();

        if (idPekerjaan != null) {
            for (Pekerjaan item : daftarPekerjaanUtama) {
                if (item.getId().equals(idPekerjaan)) {
                    objectToReturn = item;
                }
            }
        }

        return objectToReturn;
    }

    public boolean isKompetensiExist(String idOfItemToCheck) {
        boolean found = false;

        if (idOfItemToCheck != null) {
            for (Kompetensi item : daftarKompetensiUtama) {
                if (item.getId().equals(idOfItemToCheck)) {
                    found = true;
                }
            }
        }

        return found;
    }

    public boolean isPekerjaanExist(String idOfItemToCheck) {
        boolean found = false;

        if (idOfItemToCheck != null) {
            for (Pekerjaan item : daftarPekerjaanUtama) {
                if (item.getId().equals(idOfItemToCheck)) {
                    found = true;
                }
            }
        }
        return found;
    }
}

class dbOfMahasiswa {

    ArrayList<Mahasiswa> daftarMahasiswaUtama;

    public dbOfMahasiswa() {
        //always fetch data when class is initialized;
        this.daftarMahasiswaUtama = getMahasiswaListFromJSON();
    }

    public ArrayList<Mahasiswa> getMahasiswaListFromJSON() {

        //json
        JSONObject root = new JSONObject();
        JSONArray kompetensi = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        //java
        ArrayList<Mahasiswa> listMahasiswaToReturn = new ArrayList<Mahasiswa>();
        dbOfPekerjaanAndKompetensi dbData = new dbOfPekerjaanAndKompetensi();

        File f = new File(ConfigDirektori.direktoriAkun);

        if (f.exists() && !f.isDirectory()) {
            try {
                //JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriAkun));

                JSONObject jsonObject = (JSONObject) obj;
                //JSONObject jsonObject2 = (JSONObject) jsonObject.get("kompetensi");
                array = (JSONArray) jsonObject.get("users");

                for (int i = 0; i < array.size(); i++) {
                    JSONObject itemArr = (JSONObject) array.get(i);
                    long roles = (long) itemArr.get("role");

                    if (roles == 1) {
                        Mahasiswa newMahasiswaToAdd = new Mahasiswa();

                        newMahasiswaToAdd.setUsername((String) itemArr.get("username"));
                        newMahasiswaToAdd.setPassword((String) itemArr.get("password"));
                        newMahasiswaToAdd.setNama((String) itemArr.get("nama"));
                        newMahasiswaToAdd.setNim((String) itemArr.get("NIM"));
                        newMahasiswaToAdd.setRole((int) roles);

                        ArrayList<String> listOfMahasiswaIdKompetensi = (JSONArray) itemArr.get("kompetensi");
                        ArrayList<String> listOfMahasiswaIdPekerjaan = (JSONArray) itemArr.get("pekerjaan");

                        ArrayList<Kompetensi> listOfMahasiswaObjectKompetensi = new ArrayList<Kompetensi>();
                        ArrayList<Pekerjaan> listOfMahasiswaObjectPekerjaan = new ArrayList<Pekerjaan>();

                        for (String item : listOfMahasiswaIdKompetensi) {
                            if (dbData.isKompetensiExist(item) && item != null) {
                                listOfMahasiswaObjectKompetensi.add(dbData.getKompetensiObject(item));
                            }
                        }
                        newMahasiswaToAdd.setKompetensi(listOfMahasiswaObjectKompetensi);

                        for (String item : listOfMahasiswaIdPekerjaan) {
                            if (dbData.isPekerjaanExist(item) && item != null) {
                                listOfMahasiswaObjectPekerjaan.add(dbData.getPekerjaanObject(item));
                            }
                        }

                        newMahasiswaToAdd.setPekerjaan(listOfMahasiswaObjectPekerjaan);

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

class dbOfKompetensiAvailableDosen {

    ArrayList<structKompetensiWithItsDosen> data;

    public dbOfKompetensiAvailableDosen() {
        //always fetch data when class is initialized;
        this.data = populateDataFromJSON();
    }

    public ArrayList<structKompetensiWithItsDosen> populateDataFromJSON() {

        ArrayList<structKompetensiWithItsDosen> resultOfDataPopulation = new ArrayList<structKompetensiWithItsDosen>();

        //json
        JSONObject root = new JSONObject();
        JSONArray kompetensi = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        //java
        dbOfPekerjaanAndKompetensi dbData = new dbOfPekerjaanAndKompetensi();

        File f = new File(ConfigDirektori.direktoriAkun);

        if (f.exists() && !f.isDirectory()) {
            try {
                //JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriAkun));

                JSONObject jsonObject = (JSONObject) obj;
                //JSONObject jsonObject2 = (JSONObject) jsonObject.get("kompetensi");
                array = (JSONArray) jsonObject.get("users");

                for (int i = 0; i < array.size(); i++) {
                    JSONObject itemArr = (JSONObject) array.get(i);
                    long roles = (long) itemArr.get("role");

                    if (roles == 2) {
                        ArrayList<String> listOfIDKompetensi = (JSONArray) itemArr.get("kompetensi");
                        for (String item : listOfIDKompetensi) {
                            structKompetensiWithItsDosen newDosenToAdd = new structKompetensiWithItsDosen();
                            if (item != null && dbData.isKompetensiExist(item)) {

                                newDosenToAdd.nipDosen = ((String) itemArr.get("NIP"));
                                newDosenToAdd.namaDosen = ((String) itemArr.get("nama"));
                                newDosenToAdd.kompetensi = dbData.getKompetensiObject(item);

                                resultOfDataPopulation.add(newDosenToAdd);
                            }
                        }
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

        return resultOfDataPopulation;
    }
}

class structKompetensiWithItsDosen {

    Kompetensi kompetensi;
    String nipDosen;
    String namaDosen;
}

class structMahasiswaWithKelasDetail {

    String nim;
    String nama;
    Kompetensi kompetensi;
    Pekerjaan pekerjaan;
    long tarif;
}

class dbOfMahasiswaPerKompetensiPerPekerjaan {

    ArrayList<structMahasiswaWithKelasDetail> data;

    dbOfMahasiswaPerKompetensiPerPekerjaan() {
        data = new ArrayList<structMahasiswaWithKelasDetail>();
    }

    public boolean isDataAlreadyExists(structMahasiswaWithKelasDetail itemToCheck) {
        boolean found = false;
        if (itemToCheck != null) {
            if (!data.isEmpty()) {
                for (structMahasiswaWithKelasDetail item : data) {
                    if (item.kompetensi.getId().equals(itemToCheck.kompetensi.getId())
                            && item.pekerjaan.getId().equals(itemToCheck.pekerjaan.getId())
                            && item.nim.equals(itemToCheck.nim)) {

                        found = true;
                    }
                }
            }
        }

        return found;
    }

    public void sortByTarifDesc() {

        // need to be fixed
        Collections.sort(data, new Comparator<structMahasiswaWithKelasDetail>() {
            public int compare(structMahasiswaWithKelasDetail o1, structMahasiswaWithKelasDetail o2) {
                if (o1.tarif >= o2.tarif) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
    }

    public void printList() {
        System.out.println("\n\nIsi Data Mahasiswa per Kompetensi per Pekerjaan ");
        for (structMahasiswaWithKelasDetail item : data) {
            System.out.println(item.nim + " " + item.tarif + " " + item.kompetensi.getId() + " " + item.pekerjaan.getId());
        }
    }
}
