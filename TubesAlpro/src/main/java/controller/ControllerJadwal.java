/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Jadwal;
import model.Kompetensi;
import view.ViewJadwal;

/**
 *
 * @author izu
 */
public class ControllerJadwal {
    Kompetensi kompetensi;
    Jadwal jadwal;
    ViewJadwal viewJadwal;

    public ControllerJadwal() {
        jadwal = new Jadwal();
    }

    public Kompetensi getKompetensi() {
        return kompetensi;
    }

    public Jadwal getJadwal() {
        return jadwal;
    }
    
    public void ControlMenuPilihMinggu(){
        viewJadwal = new ViewJadwal();
        viewJadwal.menuPilihanJadwal();
        
        switch(viewJadwal.getMinggu()){
            case 1:
                readJadwal(viewJadwal.getMinggu());
                break;
            case 2:
                readJadwal(viewJadwal.getMinggu());
                break;
            case 3:
                readJadwal(viewJadwal.getMinggu());
                break;
            case 4:
                readJadwal(viewJadwal.getMinggu());
                break;
            case 5:
                readJadwal(viewJadwal.getMinggu());
                break;
            case 6:
                readJadwal(viewJadwal.getMinggu());
                break;
            case 7:
                readJadwal(viewJadwal.getMinggu());
                break;
            case 8:
                readJadwal(viewJadwal.getMinggu());
                break;
            case 9:
                readJadwal(viewJadwal.getMinggu());
                break;
            case 10:
                readJadwal(viewJadwal.getMinggu());
                break;
            case 11:
                readJadwal(viewJadwal.getMinggu());
                break;
        }
        
    }
    
    
    
    void ControlBangkitkanJadwal() {
       // System.out.println("wakwaw");
        for (int i = 1; i <= 11; i++) {
                 jadwal.BangkitkanAlokasiJadwal(i);
        }
       
        //jadwal.InsertDosenToJadwalJson();
        //jadwal.ReadKompetensiFromJsonToObject();
        
        //for (int i = 0; i < jadwal.getK().size(); i++) {
            //if(jadwal.getK().get(i).getSks() == 3) {
                
        //}
    //}
    
    }
    
    public void readJadwal(int minggu){
        jadwal = new Jadwal();
        jadwal.ReadJadwalFromJson();
    }

}
