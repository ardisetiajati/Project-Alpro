/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Scanner;
import model.Kompetensi;

/**
 *
 * @author Ardiansyah Setiajati
 */
public class ViewTambahKompetensi {
    private String id, nama;
    private ArrayList<Kompetensi> prasyarat = new ArrayList<Kompetensi>();
    private int sks;
    private boolean hasPraktikum = false;
    Scanner input = new Scanner(System.in);
    
    public void menuTambahKompetensi(){
        String inPrasyarat;
        int lanjut;
        System.out.print("Masukkan id : ");
        id = input.next();
        System.out.print("Masukkan nama kompetensi : ");
        nama = input.next();
        do {
        System.out.print("Masukkan ID kompetensi prasyarat : ");
        inPrasyarat = input.next();
        Kompetensi k = new Kompetensi(inPrasyarat);
        prasyarat.add(k);
        System.out.print("Apakah ingin menambahkan prasyarat lagi? (1 untuk yes/0 untuk no)" );
        lanjut = input.nextInt();
        }
        while (lanjut == 1);
        System.out.print("Masukkan sks : ");
        sks = input.nextInt();
        
         System.out.print("apakah ada praktikum? (y/n) ");
         String inPraktikum = input.next();
         if (inPraktikum.equals("y")) {
             hasPraktikum = true;
        }
       
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

   

    public int getSks() {
        return sks;
    }

    public boolean isHasPraktikum() {
        return hasPraktikum;
    }
    

    
}
