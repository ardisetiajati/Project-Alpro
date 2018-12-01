/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.util.ArrayList;
import model.Admin;
import model.ConfigDirektori;
import model.Dosen;
import model.Kompetensi;
import model.Mahasiswa;
import model.User;
import org.json.simple.parser.ParseException;
import view.ViewAdmin;
import view.ViewKompetensi;


/**
 *
 * @author izu
 */

/**
 * Konstruktor
 * 
 */
public class ControllerAdmin {
    User user;
    Kompetensi kompetensi;
    ControllerKompetensi ctrKompetensi;
    ControllerPekerjaan ctrPekerjaan;
    ControllerJadwal ctrJadwal;

    public ControllerAdmin() {
        ctrKompetensi = new ControllerKompetensi();
        ctrPekerjaan = new ControllerPekerjaan();
        ctrJadwal = new ControllerJadwal();
    }
    
    
    public User getUser() {
        return user;
    }
    
    /**
 * Controller  Utama
 * 
 */
    public void ControlMenuAdmin() throws ParseException{
        ViewAdmin viewAdmin = new ViewAdmin();
        viewAdmin.menuAdmin();
        switch (viewAdmin.getPilihan()){
            case "1":
                ControlMenuKelolaAkun();
                break;
            case "2":
                ctrKompetensi.ControlMenuKelolaKompetensi();
                ControlMenuAdmin();
                break;
            case "3":
                ctrPekerjaan.ControlMenuKelolaPekerjaan();
                ControlMenuAdmin();
                break;
            case "4":
                break;
            case "5":
                ctrJadwal.ReadJadwalFromJson();
                break;
            case "6":
                break;
            case "7":
                break;
            case "8":
                ctrJadwal.ControlBangkitkanJadwal();
                ControlMenuAdmin();
                break;
            case "9":
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Inputan Salah!");
                ControlMenuAdmin();
                break;
                
        }
    
//        
    }
    
    /**
 * Controller Secondary
 * 
 */
    

    

    public void ControlMenuKelolaAkun() throws ParseException{

        ViewAdmin viewAdmin = new ViewAdmin();
        viewAdmin.menuKelolaAkun();
        
        switch (viewAdmin.getPilihan()){
            case "1":
                ControlMenuTambahAkun();
                break;
            case "2":
                ControlMenuEditAkun();
                break;
            case "3":
                ControlMenuHapusAkun();
                break;
            
            case "0":
                ControlMenuAdmin();
                break;
            default:
                System.out.println("Inputan Salah!");
                ControlMenuKelolaAkun();
                break;
                
        }
    }
    
    /**
 * Controller CRUD
 * 
 */
    
    
    public void ControlMenuTambahAkun() throws ParseException{
        //ViewTambahAkun viewTambahAkun = new ViewTambahAkun();
        //viewTambahAkun.menuTambahAkun();
        ViewAdmin viewAdmin = new ViewAdmin();
        viewAdmin.menuTambahAkun();
        
        tambahUser(viewAdmin.getUsername(), viewAdmin.getPassword(), viewAdmin.getRole());
        ControlMenuAdmin();
    }
    
    public void ControlMenuEditAkun() throws ParseException{
        //ViewEditAkun viewEditAkun = new ViewEditAkun();
        //viewEditAkun.MenuEditAkun();
        ViewAdmin viewAdmin = new ViewAdmin();
        viewAdmin.MenuEditAkun();

        if (!cekUser(viewAdmin.getUsername())) {
            System.out.println("Username tidak ditemukan");
            ControlMenuAdmin();
        }
        else{
            editUser(viewAdmin.getUsername());
             ControlMenuAdmin();
        }
     
    }
    
    public void ControlMenuHapusAkun() throws ParseException{
        //ViewHapusAkun viewHapusAkun = new ViewHapusAkun();
        //viewHapusAkun.MenuHapusAkun();
        
        ViewAdmin viewAdmin = new ViewAdmin();
        viewAdmin.MenuHapusAkun();

        if (!cekUser(viewAdmin.getUsername())) {
            System.out.println("Username tidak ditemukan");
            ControlMenuAdmin();
        }
        else{
            hapusUser(viewAdmin.getUsername());
            ControlMenuAdmin();
        }
     
        
    }
      
     /**
 *  Tambah  to Model
 */ 
    
    public void tambahUser(String username, String password, int role){
        user = new User(username, password, role);
        user.TulisUserToJson();
        System.out.println("Akun berhasil ditambahkan");
        
    }
    
    /**
 *  Edit to Model
 * 
 */
    
    public void editUser (String username){
        user = new User(username);
        user.EditUserFromJson();
        System.out.println("password telah diset ke default");
    }
    
    /**
 * Hapus to Model
 */
       
    public void hapusUser (String username){
        user = new User(username);
        user.HapusUserFromJson();
        System.out.println("akun telah dihapus");
    }
    
    /**
 * validasi
 */
    
    public boolean  cekUser (String username){
        user = new User(username);
        return user.CekUserFromJson();
    }
    
    public void readUser(){
        user = new User();
        user.ReadUserFromJson();
    }
    
     
     
    
    
    /*
    
    public void ControlMenuKelolaKompetensi(){
        ViewKelolaKompetensi viewKelolaKompetensi = new ViewKelolaKompetensi();
        viewKelolaKompetensi.menuKelolaKompetensi();
        if(viewKelolaKompetensi.getPilihan()==1){
            ControlMenuTambahKompetensi();
            System.out.println("wakwaw3");
        }
        else if(viewKelolaKompetensi.getPilihan()==2){
            //ControlMenuEditKompetensi();
        }
    }
    */
    
    /*
    public void ControlMenuEditKompetensi(){
        ViewEditKompetensi viewEditKompetensi = new ViewEditKompetensi();
        viewEditKompetensi.MenuEditKompetensi();
        //kompetensi = new Kompetensi(viewEditKompetensi.getId());
        System.out.println("wakwaw 2 ");
        if (1 ==1) {
            System.out.println("wakwaw");
            System.out.println("Kompetensi tidak ditemukan");
            ControlMenuEditKompetensi();
        }else{
            if(viewEditKompetensi.getPilihan()==1){
                viewEditKompetensi.MenuEditNama();
                kompetensi.EditKompetensiFromJson();
            }
            else if(viewEditKompetensi.getPilihan()==2){
                viewEditKompetensi.MenuEditPrasyarat();
                editKompetensi(viewEditKompetensi.getId());
            }
            else if(viewEditKompetensi.getPilihan()==3){
                viewEditKompetensi.MenuEditSKS();
                editKompetensi(viewEditKompetensi.getId());
            }
            
            ControlMenuAdmin();
        }
     
    }
    */  
}
