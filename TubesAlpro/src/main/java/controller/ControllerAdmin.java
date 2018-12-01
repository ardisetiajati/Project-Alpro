/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import model.Admin;
import model.Kompetensi;
import model.User;
import view.ViewAdmin;


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
    Admin admin;
    Kompetensi kompetensi;
    ControllerKompetensi ctrKompetensi;
    ControllerPekerjaan ctrPekerjaan;
    ControllerJadwal ctrJadwal;
    ControllerKelas ctrKelas;

    public ControllerAdmin(User user) {
        ctrKompetensi = new ControllerKompetensi();
        ctrPekerjaan = new ControllerPekerjaan();
        ctrJadwal = new ControllerJadwal();
        ctrKelas = new ControllerKelas();
        this.user = user;
    }
    
    
    public User getUser() {
        return user;
    }
    
    /**
 * Controller  Utama
 * 
 */
    public void ControlMenuAdmin() {
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
                break;
            case "6":
                break;
            case "7":
                break;
            case "8":
                ctrJadwal.ControlBangkitkanJadwal();
                ctrKelas.ControlMenuOptimasi();
                ControlMenuAdmin();
                break;
            case "9":
                ControlMenuUbahPassword();
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
    

    

    public void ControlMenuKelolaAkun() {

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
    
    
    public void ControlMenuTambahAkun() {
        //ViewTambahAkun viewTambahAkun = new ViewTambahAkun();
        //viewTambahAkun.menuTambahAkun();
        ViewAdmin viewAdmin = new ViewAdmin();
        viewAdmin.menuTambahAkun();
        
        tambahUser(viewAdmin.getUsername(), viewAdmin.getPassword(), viewAdmin.getRole());
        ControlMenuAdmin();
    }
    
    public void ControlMenuEditAkun() {
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
    
    public void ControlMenuHapusAkun() {
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
     * @param username
     * @param password
     * @param role
 */ 
    
    public void tambahUser(String username, String password, int role){
        user = new User(username, password, role);
        user.TulisUserToJson();
        System.out.println("Akun berhasil ditambahkan");
        
    }
    
    /**
 *  Edit to Model
 * 
     * @param username
 */
    
    public void editUser (String username){
        user = new User(username);
        user.EditUserFromJson();
        System.out.println("password telah diset ke default");
    }
    
    /**
 * Hapus to Model
     * @param username
 */
       
    public void hapusUser (String username){
        user = new User(username);
        user.HapusUserFromJson();
        System.out.println("akun telah dihapus");
    }
    
    /**
 * validasi
     * @param username
     * @return 
 */
    
    public boolean  cekUser (String username){
        user = new User(username);
        return user.CekUserFromJson();
    }
    
    public void readUser(){
        user = new User();
        user.ReadUserFromJson();
    }
    
    public void ControlMenuUbahPassword() {
        ViewAdmin viewAdminEditPassword = new ViewAdmin();
        viewAdminEditPassword.MenuEditPassword();
        
        editPassword(viewAdminEditPassword.getUsername(), viewAdminEditPassword.getPassword());
        ControlMenuAdmin();
    }
    
    public void editPassword(String username, String password) {
        user = new User(user.getUsername(), password);
        user.EditUserPasswordAdminFromJson();
        System.out.println("Password telah diubah...");
    
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
