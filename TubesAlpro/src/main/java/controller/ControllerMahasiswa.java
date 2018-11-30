/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Kompetensi;
import model.Mahasiswa;
import model.Pekerjaan;
import model.User;
import view.ViewMahasiswa;


/**
 *
 * @author ASUS
 */
public class ControllerMahasiswa {
    User user;
    Mahasiswa mhs;
    Pekerjaan pekerjaan;
    
    public ControllerMahasiswa(User user){
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }
    
    public void ControlMenuMahasiswa(){
        //System.out.println(user.getUsername());
        ViewMahasiswa viewMahasiswa = new ViewMahasiswa();
        viewMahasiswa.menuMahasiswa();
        if(viewMahasiswa.getPilihan()==1){
            ControlMenuKelolaAkun();
        }
        else if(viewMahasiswa.getPilihan()==2){
            //System.out.println("wakwaw");
            ControlMenuKelolaJadwal();
        }
        else if(viewMahasiswa.getPilihan()==3){
            //System.out.println("wakwaw");
            ControlMenuLihatTagihan();
        }
        else if(viewMahasiswa.getPilihan()==4){
            //System.out.println("wakwaw");
            ControlMenuUbahPassword();
        }
        else if(viewMahasiswa.getPilihan()==0){
            System.exit(0);
        }
    }
    
    public void ControlMenuKelolaAkun(){
        ViewMahasiswa viewMahasiswaKelolaAkun = new ViewMahasiswa();
        viewMahasiswaKelolaAkun.menuKelolaAkun();
        if(viewMahasiswaKelolaAkun.getPilihan()==1){
            ControlMenuEditNama();
        }
        else if (viewMahasiswaKelolaAkun.getPilihan()==2){
            ControlMenuTambahPekerjaan();
        }
        else if (viewMahasiswaKelolaAkun.getPilihan()==3){
            ControlMenuTambahKompetensi();
        }
    }
    


    public void ControlMenuKelolaKompetensi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void ControlMenuKelolaJadwal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void ControlMenuLihatTagihan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void ControlMenuUbahPassword() {
        ViewMahasiswa viewMahasiswaEditPassword = new ViewMahasiswa();
        viewMahasiswaEditPassword.MenuEditPassword();
        
        editPassword(viewMahasiswaEditPassword.getPassword());
        ControlMenuMahasiswa();
    }

    public void ControlMenuEditNama() {
        ViewMahasiswa viewMahasiswaEditNama = new ViewMahasiswa();
        viewMahasiswaEditNama.MenuEditNama();
        
        editUser(viewMahasiswaEditNama.getNama());
        ControlMenuMahasiswa();
        
    }

    public void ControlMenuTambahPekerjaan() {
        System.out.println("##Daftar Pekerjaan");
        readPekerjaan();
        ViewMahasiswa viewMahasiswaTambahPekerjaan = new ViewMahasiswa();
        viewMahasiswaTambahPekerjaan.MenuTambahPekerjaan();
        
        tambahPekerjaan(viewMahasiswaTambahPekerjaan.getPekerjaan());
    }
    
    public void ControlMenuTambahKompetensi() {
        ViewMahasiswa viewMahasiswaTambahKompetensi = new ViewMahasiswa();
        viewMahasiswaTambahKompetensi.MenuTambahKompetensi();
        
        tambahKompetensi(viewMahasiswaTambahKompetensi.getKompetensi());
    }

    public void editUser(String nama) {
        mhs = new Mahasiswa(user.getUsername(), nama);
        mhs.EditUserMahasiswaFromJson();
        System.out.println("Nama sudah berubah");
    
    }
    
    public void editPassword(String password) {
        mhs = new Mahasiswa(user.getUsername(), password, 0);
        mhs.EditUserPasswordMahasiswaFromJson();
        System.out.println("Password telah diubah...");
    
    }

    private void tambahKompetensi(ArrayList<Kompetensi> kompetensi) {
        mhs = new Mahasiswa(null,null,null,0,kompetensi,0,null,user.getUsername(),null,0);
        mhs.EditKompetensiFromJson();
        System.out.println("Berhasil menambahkan kompetensi..");
        ControlMenuMahasiswa(); 
        
    }
    
    private void tambahPekerjaan(ArrayList<Pekerjaan> pekerjaan) {
        mhs = new Mahasiswa(null,null,pekerjaan,0,null,0,null,user.getUsername(),null,0);
        mhs.EditPekerjaanFromJson();
        System.out.println("Berhasil menambahkan pekerjaan..");
        ControlMenuMahasiswa(); 
        
    }
    
    public void readPekerjaan(){
        pekerjaan = new Pekerjaan();
        pekerjaan.ReadPekerjaanFromJson();
    }

    
}
