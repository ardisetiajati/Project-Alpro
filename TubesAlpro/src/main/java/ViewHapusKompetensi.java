/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author izu
 */
public class ViewHapusKompetensi {
    String id, nama, prasyarat;
    int sks;
    Scanner input = new Scanner(System.in);

    public ViewHapusKompetensi() {
    }
    

    public ViewHapusKompetensi(String id, String nama, String prasyarat, int sks) {
        this.id = id;
        this.nama = nama;
        this.prasyarat = prasyarat;
        this.sks = sks;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getPrasyarat() {
        return prasyarat;
    }

    public void setPrasyarat(String prasyarat) {
        this.prasyarat = prasyarat;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public void MenuHapusAkun(){
        System.out.print("Masukkan id yg akan dihapus : ");
        id = input.next();
    }
    
}
