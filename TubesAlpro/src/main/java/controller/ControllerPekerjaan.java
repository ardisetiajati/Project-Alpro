/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Kompetensi;
import model.Pekerjaan;
import org.json.simple.parser.ParseException;
import view.ViewPekerjaan;

/**
 *
 * @author Ardiansyah Setiajati
 */
public class ControllerPekerjaan {
    Pekerjaan pekerjaan;
    ViewPekerjaan viewPekerjaan;
    ControllerAdmin ctrAdmin;

    public ControllerPekerjaan() {
        viewPekerjaan = new ViewPekerjaan();
    }
    
    public void ControlMenuKelolaPekerjaan() throws ParseException{ 
        System.out.println("## Daftar Pekerjaan  ##");
        readPekerjaan();
        
        viewPekerjaan.menuKelolaPekerjaan();
        
        switch(viewPekerjaan.getPilihan()){
            case "1":
                ControlMenuTambahPekerjaan();
                break;
            case "2":
                ControlMenuEditPekerjaan();
                break;
            case "3":
                ControlMenuHapusPekerjaan();
                break;
            case "0":
                ctrAdmin = new ControllerAdmin();
                ctrAdmin.ControlMenuAdmin();
                break;
            default:
                System.out.println("Inputan Salah!");
                ControlMenuKelolaPekerjaan();
                break;
        }
        
    }
    
    public void ControlMenuTambahPekerjaan(){
        viewPekerjaan.menuTambahPekerjaan();
        
        tambahPekerjaan(viewPekerjaan.getId(), viewPekerjaan.getNama(), viewPekerjaan.getListKompetensi());
    }
    
    public void ControlMenuEditPekerjaan() throws ParseException{
        viewPekerjaan.MenuCekEditPekerjaan();
        
        if (!cekIdPekerjaan(viewPekerjaan.getId())) {
             System.out.println("Pekerjaan tidak dapat ditemukan");
        }
        else {
            //viewEditKompetensi.MenuEditKompetensi();
            viewPekerjaan.MenuEditPekerjaan();
            
            
            switch(viewPekerjaan.getPilihan()){
                case "1":
                    viewPekerjaan.MenuEditNama();
                    //System.out.println(viewEditKompetensi.getId());
                   editNamaPekerjaan(viewPekerjaan.getId(), viewPekerjaan.getNama());
                    break;
                case "2":
                    viewPekerjaan.MenuEditKompetensi();
                    editKompetensiPekerjaan(viewPekerjaan.getId(), viewPekerjaan.getListKompetensi());
                    break;
                case "3":
                    ControlMenuHapusPekerjaan();
                    break;
                case "0":
                    ControlMenuKelolaPekerjaan();
                    break;
                default:
                    System.out.println("Inputan Salah!");
                     viewPekerjaan.MenuCekEditPekerjaan();
                     break;
            }
        }
    }
    
    public void ControlMenuHapusPekerjaan(){
        viewPekerjaan.MenuHapusPekerjaan();
        
        if (!cekIdPekerjaan(viewPekerjaan.getId())) {
            System.out.println("Id tidak ditemukan");
        }
        else{
            hapusPekerjaan(viewPekerjaan.getId());
        }
    }
    
    public boolean  cekIdPekerjaan (String id){
        pekerjaan = new Pekerjaan(id);
        return pekerjaan.CekPekerjaanFromJson();
    }
    
    public void readPekerjaan(){
        pekerjaan = new Pekerjaan();
        pekerjaan.ReadPekerjaanFromJson();
    }
    
    public void tambahPekerjaan(String id, String nama, ArrayList<Kompetensi> listKompetensi){
        pekerjaan = new Pekerjaan(id, nama, listKompetensi);
        pekerjaan.TulisPekerjaanToJson();
        System.out.println("Pekerjaan berhasil ditambahkan");
    }
    
    public void editNamaPekerjaan (String id, String nama){
        pekerjaan = new Pekerjaan(id, nama, null);
        pekerjaan.EditPekerjaanFromJson();
        System.out.println("Edit nama pekerjaan berhasil");
    }
    
    public void editKompetensiPekerjaan (String id, ArrayList<Kompetensi> listKompetensi){
        pekerjaan = new Pekerjaan(id, null, listKompetensi);
        pekerjaan.EditListKompetensiFromJson();
        System.out.println("Edit prasyarat berhasil");
    }
    
    public void hapusPekerjaan (String id){
        pekerjaan = new Pekerjaan(id);
        pekerjaan.HapusPekerjaanFromJson();
        System.out.println("Pekerjaan telah dihapus");
    }

}
