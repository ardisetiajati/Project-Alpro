/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Jadwal;
import model.Kompetensi;

/**
 *
 * @author izu
 */
public class ControllerJadwal {
    Kompetensi kompetensi;
    Jadwal jadwal;

    public ControllerJadwal() {
        jadwal = new Jadwal();
    }

    public Kompetensi getKompetensi() {
        return kompetensi;
    }

    public Jadwal getJadwal() {
        return jadwal;
    }
    
    void ControlBangkitkanJadwal() {
       // System.out.println("wakwaw");
        jadwal.BuatSlotToJson();
        jadwal.InsertDosenToJadwalJson();
        //jadwal.ReadKompetensiFromJsonToObject();
        
        //for (int i = 0; i < jadwal.getK().size(); i++) {
            //if(jadwal.getK().get(i).getSks() == 3) {
                
        //}
    //}
    
}

}
