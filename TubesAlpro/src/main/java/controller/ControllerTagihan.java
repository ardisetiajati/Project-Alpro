/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Tagihan;
import model.User;
import view.ViewTagihan;

/**
 *
 * @author ASUS
 */
public class ControllerTagihan {
    User user;
    Tagihan tagihan;
    ViewTagihan viewTagihan;
    ControllerAdmin ctrAdmin;
    
    public void ControlMenuKelolaTagihan(){
        System.out.println("## Daftar Kelola Tagihan ##");
        readTagihan();
        
        viewTagihan = new ViewTagihan();
        viewTagihan.menuKelolaTagihan();
        
        switch(viewTagihan.getPilihan()){
            case "1":
                ControlMenuValidasi();
                break;
            case "0":
                ctrAdmin = new ControllerAdmin();
                ctrAdmin.ControlMenuAdmin();
                break;
            default:
                System.out.println("Inputan Salah!");
                ControlMenuKelolaTagihan();
                break;
        }
    }
    
    public void ControlMenuValidasi() {
        ViewTagihan viewTagihan = new ViewTagihan();
        viewTagihan.menuCekValidasiTagihan();
        
        if (!cekIdMahasiswa(viewTagihan.getId())) {
            System.out.println("ID Mahasiswa tidak ditemukan");
            ControlMenuValidasi();
        } else {
           viewTagihan.menuValidasiTagihan();
           validasiTagihan(viewTagihan.getId());
        }
    }
    
    public void readTagihan() {
        tagihan = new Tagihan();
        tagihan.ReadAllTagihanFromJson();
    }
    
    public boolean cekIdMahasiswa (String id){
        tagihan = new Tagihan(id);
        return tagihan.CekTagihanFromJson();
    }

    private void validasiTagihan(String id) {
        tagihan = new Tagihan(id);
        tagihan.ValidasiTagihanFromJson();
        System.out.println("Validasi Berhasil");
        ControlMenuKelolaTagihan();
    }

    
}
