/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Kompetensi;

import view.ViewKompetensi;

/**
 *
 * @author Ardiansyah Setiajati
 */
public class ControllerKompetensi {
    Kompetensi kompetensi;
    ViewKompetensi viewKompetensi;
    ControllerAdmin ctrAdmin;
    
    public void ControlMenuKelolaKompetensi(){ 
        System.out.println("## Daftar Kompetensi  ##");
        readKompetensi();
        
        viewKompetensi = new ViewKompetensi();
        viewKompetensi.menuKelolaKompetensi();
        
        
        switch (viewKompetensi.getPilihan()){
            case "1":
                ControlMenuTambahKompetensi();
                break;
            case "2":
                ControlMenuEditKompetensi();
                break;
            case "3":
                ControlMenuHapusKompetensi();
                break;
            case "0":
                ctrAdmin = new ControllerAdmin();
                ctrAdmin.ControlMenuAdmin();
                break;
            default:
                System.out.println("Inputan Salah!");
                ControlMenuKelolaKompetensi();
                break;
                
        }
        
//        if (viewKompetensi.getPilihan() == "1") {
//            ControlMenuTambahKompetensi();
//        } 
//        else if (viewKompetensi.getPilihan() == "2") {
//            ControlMenuEditKompetensi();
//        } 
//        else if (viewKompetensi.getPilihan() == "3"){
//            ControlMenuHapusKompetensi();
//        }  
    }
    
    public void ControlMenuTambahKompetensi(){
        //ViewTambahKompetensi viewTambahKompetensi = new ViewTambahKompetensi();
        //viewTambahKompetensi.menuTambahKompetensi();
        
        viewKompetensi = new ViewKompetensi();
        viewKompetensi.menuTambahKompetensi();
        //String id = viewTambahKompetensi.getId();
        //String nama = viewTambahKompetensi.getNama();
        //int sks = viewTambahKompetensi.getSks();
        tambahKompetensi(viewKompetensi.getId(), viewKompetensi.getNama(), viewKompetensi.getPrasyarat(), viewKompetensi.getSks(), viewKompetensi.isHasPraktikum());
    }
    
    public void ControlMenuEditKompetensi(){
            
        ViewKompetensi viewKompetensi = new ViewKompetensi();
        viewKompetensi.MenuCekEditKompetensi();

        if (!cekIdKompetensi(viewKompetensi.getId())) {
             System.out.println("Id Kompetensi tidak dapat ditemukan");
             ControlMenuEditKompetensi();
        }
        else {
            //viewEditKompetensi.MenuEditKompetensi();
//            viewKompetensi.MenuEditKompetensi();
//            if (viewKompetensi.getPilihan() == "1") {
//                viewKompetensi.MenuEditNama();
//                 //System.out.println(viewEditKompetensi.getId());
//                editNamaKompetensi(viewKompetensi.getId(), viewKompetensi.getNama());
//            } 
//            else if (viewKompetensi.getPilihan() == "2") {
//                viewKompetensi.MenuEditPrasyarat();
//                editPrasyaratKompetensi(viewKompetensi.getId() , viewKompetensi.getPrasyarat());
//                //System.out.println("wakwaw2");
//            } 
//            else if (viewKompetensi.getPilihan() == "3") {
//                //System.out.println("wakwaw3");
//                viewKompetensi.MenuEditSKS();
//                editSksKompetensi(viewKompetensi.getId(), viewKompetensi.getSks());
//            } 
//            else  {
//                ControlMenuKelolaKompetensi();
//            }
//            viewKompetensi = new ViewKompetensi();
            viewKompetensi.MenuEditKompetensi();
            switch (viewKompetensi.getPilihan()){
                case "1":
                    viewKompetensi.MenuEditNama();
                 //System.out.println(viewEditKompetensi.getId());
                    editNamaKompetensi(viewKompetensi.getId(), viewKompetensi.getNama());
                    break;
                case "2":
                    viewKompetensi.MenuEditPrasyarat();
                    editPrasyaratKompetensi(viewKompetensi.getId() , viewKompetensi.getPrasyarat());
                    break;
                case "3":
                    viewKompetensi.MenuEditSKS();
                    editSksKompetensi(viewKompetensi.getId(), viewKompetensi.getSks());
                    System.out.println(viewKompetensi.getId());
                    break;
                case "0":
                    ControlMenuEditKompetensi();
                    break;
                default:
                    System.out.println("Inputan Salah!");
                    ControlMenuKelolaKompetensi();
                    break;

            }
            
        }      
     }
    
    public void ControlMenuHapusKompetensi(){
        //ViewHapusKompetensi viewHapusKompetensi = new ViewHapusKompetensi();
        //viewHapusKompetensi.MenuHapusAkun();
        
        ViewKompetensi viewKompetensi = new ViewKompetensi();
        viewKompetensi.MenuHapusKompetensi();

        if (!cekIdKompetensi(viewKompetensi.getId())) {
            System.out.println("Id tidak ditemukan");
            ControlMenuHapusKompetensi();
        }
        else{
            hapusKompetensi(viewKompetensi.getId());
        }
    }
    
    public void readKompetensi(){
        kompetensi = new Kompetensi();
        kompetensi.ReadKompetensiFromJson();
    }
    
    public void tambahKompetensi(String id, String nama, ArrayList<Kompetensi> prasyarat, int sks, boolean hasPraktikum){
        kompetensi = new Kompetensi(id, nama, prasyarat, sks, hasPraktikum);
        kompetensi.TulisKompetensiToJson();
        System.out.println("Kompetensi berhasil ditambahkan");
    }
    
    public void editNamaKompetensi (String id, String nama){
        kompetensi = new Kompetensi(id, nama, null, 0, false);
        kompetensi.EditKompetensiFromJson();
        System.out.println("Edit nama berhasil");
        ControlMenuKelolaKompetensi();
    }
    
    public void editPrasyaratKompetensi (String id , ArrayList<Kompetensi> listPrasyarat){
        kompetensi = new Kompetensi(id, null, listPrasyarat, 0, false);
        kompetensi.EditKompetensiFromJson();
        System.out.println("Edit prasyarat berhasil");
        ControlMenuKelolaKompetensi();
    }
      
    public void editSksKompetensi (String id,  int sks){
        kompetensi = new Kompetensi(id, null, null,sks, false);
        kompetensi.EditKompetensiFromJson();
        System.out.println("Edit SKS berhasil");
        ControlMenuKelolaKompetensi();
    }
    
    public void hapusKompetensi (String id){
        kompetensi = new Kompetensi(id);
        kompetensi.HapusKompetensiFromJson();
        System.out.println("Kompetensi telah dihapus");
        ControlMenuKelolaKompetensi();
    }
    
    public boolean  cekIdKompetensi (String id){
        kompetensi = new Kompetensi(id);
        return kompetensi.CekKompetensiFromJson();
    }
}
