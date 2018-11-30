/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Kompetensi;

/**
 *
 * @author Ardiansyah Setiajati
 */
public class ViewKompetensi {
    private String id, nama;
    private ArrayList<Kompetensi> prasyarat = new ArrayList<Kompetensi>();
    private int sks, pilihan;
    private boolean hasPraktikum = false;
    Scanner input = new Scanner(System.in);

    public ViewKompetensi() {
    }
    
    public void menuKelolaKompetensi(){
        System.out.println("1. Tambah Kompetensi");
        System.out.println("2. Edit Kompetensi");
        System.out.println("3. Hapus Kompetensi");
        System.out.print("Pilihan Menu : ");
        pilihan = input.nextInt();
    }
    
    public void menuTambahKompetensi(){
        String inPrasyarat;
        int lanjut;
        System.out.print("Masukkan id : ");
        id = input.nextLine();
        System.out.print("Masukkan nama kompetensi : ");
        nama = input.nextLine();
        do {
            System.out.print("Masukkan ID kompetensi prasyarat : ");
            inPrasyarat = input.nextLine();
            Kompetensi k = new Kompetensi(inPrasyarat);
            prasyarat.add(k);
            System.out.print("Apakah ingin menambahkan prasyarat lagi? (1 untuk yes/0 untuk no)" );
            lanjut = input.nextInt();
        }while (lanjut == 1);
        System.out.print("Masukkan sks : ");
        sks = input.nextInt();
        
        System.out.print("apakah ada praktikum? (y/n) ");
        String inPraktikum = input.nextLine();
        if (inPraktikum.equals("y")) {
            hasPraktikum = true;
        }
       
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
        nama = input.nextLine();
    }
    
    public void MenuEditPrasyarat(){
        String inPrasyarat;
        int lanjut;
        do {
            System.out.print("Masukkan ID kompetensi prasyarat : ");
            inPrasyarat = input.nextLine();
            Kompetensi k = new Kompetensi(inPrasyarat);
            prasyarat.add(k);
            System.out.print("Apakah ingin menambahkan prasyarat lagi? (1 untuk yes/0 untuk no)" );
            lanjut = input.nextInt();
        }while (lanjut == 1);
    }
    
    public void MenuEditSKS(){
        System.out.print("Masukkan sks yg baru : ");
        sks = input.nextInt();
    }
    
     public void MenuHapusKompetensi(){
        System.out.print("Masukkan id yg akan dihapus : ");
        id = input.next();
    }
    

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public ArrayList<Kompetensi> getPrasyarat() {
        return prasyarat;
    }

    public int getPilihan() {
        return pilihan;
    }

    public int getSks() {
        return sks;
    }

    public boolean isHasPraktikum() {
        return hasPraktikum;
    }
}
