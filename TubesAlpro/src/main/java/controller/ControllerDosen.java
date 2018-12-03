/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Dosen;
import model.Kompetensi;
import model.User;
import view.ViewDosen;


/**
 *
 * @author ASUS
 */
public class ControllerDosen {

    /**
     *
     */
    
    String username;
    User user;
    Dosen dsn;
    Dosen dsnAlokasi;
    Kompetensi kompetensi;

    public ControllerDosen(String username){
        this.username = username;
    }

    ControllerDosen(User user) {
        this.user = user;
    }
    
     public void ControlMenuDosen(){
        ViewDosen viewDosen = new ViewDosen();
        viewDosen.menuDosen();
        
        if(viewDosen.getPilihan()==1){
            ControlMenuKelolaAkun();
        }
        else if(viewDosen.getPilihan()==2){
            //System.out.println("wakwaw");
            ControlMenuKelolaJadwal();
        }
        else if(viewDosen.getPilihan()==3){
            //System.out.println("wakwaw");
            ControlMenuTambahHariLibur();
        }
        else if(viewDosen.getPilihan()==4){
            //System.out.println("wakwaw");
            ControlMenuUbahPassword();
        }
        else if(viewDosen.getPilihan()==0){
            System.exit(0);
        }
        
    }

    private void ControlMenuKelolaAkun() {
        ViewDosen viewDosenKelolaAkun = new ViewDosen();
        viewDosenKelolaAkun.menuKelolaAkun();
        if(viewDosenKelolaAkun.getPilihan()==1){
            ControlMenuEditNama();
        }
        else if (viewDosenKelolaAkun.getPilihan()==2){
            ControlMenuEditKompetensi();
        }
    }

    private void ControlMenuKelolaJadwal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void ControlMenuTambahHariLibur() {
        ViewDosen viewDosenTambahLibur = new ViewDosen();
        viewDosenTambahLibur.MenuTambahLibur();

        tambahLibur(viewDosenTambahLibur.getLibur());
        ControlMenuDosen();
    }

    private void ControlMenuLihatTagihan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void ControlMenuUbahPassword() {
        ViewDosen viewDosenEditPassword = new ViewDosen();
        viewDosenEditPassword.MenuEditPassword();
        
        editPassword(viewDosenEditPassword.getPassword());
        ControlMenuDosen();
    }

    private void ControlMenuEditKompetensi() {
        System.out.println("##Daftar Kompetensi");
        readKompetensi();
        ViewDosen viewDosen = new ViewDosen();
        viewDosen.MenuEditKompetensi();

        editKompetensi(viewDosen.getKompetensi());
    }
    
    public void ControlMenuEditNama() {
        ViewDosen viewDosenEditNama = new ViewDosen();
        viewDosenEditNama.MenuEditNama();
        
        editUser(viewDosenEditNama.getNama());
        ControlMenuDosen();
    }


    public void editUser(String nama) {
        dsn = new Dosen(user.getUsername(), nama);
        dsn.EditUserDosenFromJson();
        System.out.println("Nama sudah berubah");
    }
    
     public void editKompetensi(ArrayList<Kompetensi> kompetensi) {
        dsn = new Dosen(null,null,kompetensi,user.getUsername(), null , 0, null);
        dsn.EditKompetensiFromJson();
         if (dsn.EditKompetensiFromJson()) {
              for (int j = 0; j < dsn.getKompetensi().size(); j++) {
                    
              for (int i = 2; i <= 11; i++) {
                        dsn.AlokasiJadwal(i, dsn.getKompetensi().get(j).getId());
             }
                 
             }
         }
         int jmlCutiDosen = dsn.cekCutiDosen();
         System.out.println("cutidosen "+jmlCutiDosen);
         if (jmlCutiDosen > 0) {
             System.out.println("masukcuti");
                            int numMinggu = 10 - jmlCutiDosen;
                            int numCuti = jmlCutiDosen;
                            
                             int itemsPerBucket = (numCuti / numMinggu);
                             int remainingItems = (numCuti % numMinggu);
                 for (int j = 0; j < dsn.getKompetensi().size(); j++) {
                             for (int i = 1; i <= numMinggu; i++)
                                {
                                    int extra = (i <= remainingItems) ? 1:0;
                                    int minggu = i+jmlCutiDosen+1;
                                    int pertemuan = itemsPerBucket + extra;
                                   dsn.AlokasiJadwalDosenCuti(minggu, pertemuan, dsn.getKompetensi().get(j).getId());
                                }
                 }
             
         }
       
        System.out.println("Kompetensi telah ditambahkan");
        ControlMenuDosen();
    }
    
    public void editPassword(String password) {
        dsn = new Dosen(user.getUsername(), password, 0);
        dsn.EditUserPasswordDosenFromJson();
        System.out.println("Password telah diubah...");
    }

    private void tambahLibur(ArrayList<String> libur) {
       dsn = new Dosen (null,null,null,user.getUsername(), null , 0,libur);
       dsn.TambahLiburFromJson();
       
       System.out.println("hari libur telah ditambahkan");
    }
    
    public void readKompetensi(){
        kompetensi = new Kompetensi();
        kompetensi.ReadKompetensiFromJson();
    }
}
