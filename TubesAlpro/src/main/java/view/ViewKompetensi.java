/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Kompetensi;
import java.util.regex.Pattern;

/**
 *
 * @author Ardiansyah Setiajati
 */
public class ViewKompetensi {
    private String id, nama;
    private ArrayList<Kompetensi> prasyarat = new ArrayList<Kompetensi>();
    private int sks;
    private String pilihan;
    private boolean hasPraktikum = false;
    Scanner input = new Scanner(System.in);

    public ViewKompetensi() {
    }
    
    public void menuKelolaKompetensi(){
        System.out.println("1. Tambah Kompetensi");
        System.out.println("2. Edit Kompetensi");
        System.out.println("3. Hapus Kompetensi");
        System.out.println("0. Kembali");
        System.out.print("Pilihan Menu : ");
        pilihan = input.next();
    }
    
    public void menuTambahKompetensi(){
        String inPrasyarat,cekid,cekno;
        int lanjut;
        do {
           System.out.print("Masukkan id : ");
           id = input.nextLine();
           cekid = id.substring(0, 2);
           cekno = id.substring(2);
            if (cekid.equals("IF") == false || id.length() != 6 || cekno.matches("[0-9_]+")== false) {
                System.out.println("Masukan ID kompetensi harus sesuai dengan diatas!");
            }
           
        }while(cekid.equals("IF") == false || id.length() != 6 || cekno.matches("[0-9_]+")== false);
        
        System.out.print("Masukkan nama kompetensi : "); 
        nama = input.nextLine();
        
        do {
            //do {
                System.out.print("Masukkan ID kompetensi prasyarat : ");
                inPrasyarat = input.next();
                
                cekid = inPrasyarat.substring(0, 2);
                cekno = inPrasyarat.substring(2);
                
                if (cekid.equals("IF") == false || inPrasyarat.length() != 6 || cekno.matches("[0-9_]+")== false) {
                    System.out.println("Masukan ID kompetensi harus sesuai dengan diatas!");
                } else {
                    Kompetensi k = new Kompetensi(inPrasyarat);
                    prasyarat.add(k);
                   
                }
            //}while(cekid.equals("IF") == false || inPrasyarat.length() != 6 || cekno.matches("[0-9_]+")== false);
                
                System.out.print("Apakah ingin menambahkan prasyarat lagi? (1 untuk yes/0 untuk no)" );
                lanjut = input.nextInt();
            
            
        }while (lanjut == 1);
        System.out.print("Masukkan sks : ");
        sks = input.nextInt();
        
        System.out.print("apakah ada praktikum? (y/n) ");
        String inPraktikum = input.next();
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
        System.out.println("0. Kembali");
        System.out.print("Masukkan pilihan : ");
        pilihan = input.next();
    }
    
     public void MenuCekEditKompetensi(){
        System.out.print("Masukkan ID Kompetensi yang akan diedit : ");
        id = input.next();
    }
     
    
    public void MenuEditNama(){
        System.out.print("Masukkan nama kompetensi  yg baru: ");
        nama = input.nextLine();
        nama = input.nextLine();
    }
    
    public void MenuEditPrasyarat(){
        String inPrasyarat,cekid,cekno;
        int lanjut;
        
        do {
            //do {
                System.out.print("Masukkan ID kompetensi prasyarat : ");
                inPrasyarat = input.next();
                
                cekid = inPrasyarat.substring(0, 2);
                cekno = inPrasyarat.substring(2);
                
                if (cekid.equals("IF") == false || inPrasyarat.length() != 6 || cekno.matches("[0-9_]+")== false) {
                    System.out.println("Masukan ID kompetensi harus sesuai dengan diatas!");
                } else {
                    Kompetensi k = new Kompetensi(inPrasyarat);
                    prasyarat.add(k);
                   
                }
            //}while(cekid.equals("IF") == false || inPrasyarat.length() != 6 || cekno.matches("[0-9_]+")== false);
                
                System.out.print("Apakah ingin menambahkan prasyarat lagi? (1 untuk yes/0 untuk no)" );
                lanjut = input.nextInt();
            
            
        }while (lanjut == 1);
        
//        do {
//            System.out.print("Masukkan ID kompetensi prasyarat : ");
//            inPrasyarat = input.nextLine();
//            Kompetensi k = new Kompetensi(inPrasyarat);
//            prasyarat.add(k);
//            System.out.print("Apakah ingin menambahkan prasyarat lagi? (1 untuk yes/0 untuk no)" );
//            lanjut = input.nextInt();
//        }while (lanjut == 1);
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

    public String getPilihan() {
        return pilihan;
    }

    public int getSks() {
        return sks;
    }

    public boolean isHasPraktikum() {
        return hasPraktikum;
    }
}
