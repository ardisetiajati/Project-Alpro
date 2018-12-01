package controller;

import java.util.ArrayList;
import model.Kelas;
import model.Mahasiswa;
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
    
    public void ControlMenuOptimasi(){
        viewKelas = new ViewKelas();
        viewKelas.menuOptimasi();
        
        switch(viewKelas.getPilihan()){
            case 1:
                Optimasi1();
                break;
            case 2:
                Optimasi2();
                break;
        }
    }
    
    public void Optimasi1(){
        mahasiswa = new Mahasiswa();
        ArrayList<String> list = mahasiswa.getListMahasiswa();
        
    }
    
    public void Optimasi2(){
    }
}


