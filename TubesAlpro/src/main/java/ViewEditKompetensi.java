/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author Ardiansyah Setiajati
 */
public class ViewEditKompetensi {
    String id, nama, prasyarat;
    int sks, pilihan;
    Scanner input = new Scanner(System.in);

    public ViewEditKompetensi() {
    }

    public ViewEditKompetensi(String id, String nama, String prasyarat, int sks, int pilihan) {
        this.id = id;
        this.nama = nama;
        this.prasyarat = prasyarat;
        this.sks = sks;
        this.pilihan = pilihan;
    }
    
    
    
    public void MenuEditKompetensi(){
        System.out.println("Id ditemukan, pilih bagian yg akan diedit ");
        //id = input.next();
        System.out.println("1. Edit Nama Kompetensi");
        System.out.println("2. Edit Prasyarat Kompetensi");
        System.out.println("3. Edit SKS");
        System.out.print("Masukkan pilihan : ");
        pilihan = input.nextInt();
    }
    
     public void MenuCekEditKompetensi(){
        System.out.print("Masukkan ID Kompetensi yang akan diedit : ");
        id = input.nextLine();
    }
     
    
    public void MenuEditNama(){
        System.out.print("Masukkan nama kompetensi  yg baru: ");
        nama = input.next();
    }
    
    public void MenuEditPrasyarat(){
        System.out.print("Masukkan prasyarat kompetensi yg baru : ");
        prasyarat = input.next();
    }
    
    public void MenuEditSKS(){
        System.out.print("Masukkan sks yg baru : ");
        sks = input.nextInt();
    }
    
     public void MenuHapusAkun(){
        System.out.print("Masukkan id yg akan dihapus : ");
        id = input.next();
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPrasyarat() {
        return prasyarat;
    }

    public int getSks() {
        return sks;
    }

    public int getPilihan() {
        return pilihan;
    }
    
    
    
}
