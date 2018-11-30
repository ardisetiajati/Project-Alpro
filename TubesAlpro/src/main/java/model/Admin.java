package model;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ardiansyah Setiajati
 */
public class Admin extends User{

    public void tambahKompetensi(String id, String nama){
        Kompetensi kompetensi = new Kompetensi(id, nama); 
    }
    
    public boolean kompetensiExist(int id){
        return true;
    }


    
}
