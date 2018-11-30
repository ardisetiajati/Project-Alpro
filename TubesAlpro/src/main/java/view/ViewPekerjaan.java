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
public class ViewPekerjaan {
    private int pilihan;
    private String id, nama;
    private ArrayList<Kompetensi> listKompetensi = new ArrayList<Kompetensi>();
    Scanner input = new Scanner(System.in);
    
    public void menuKelolaPekerjaan(){
        System.out.println("1. Tambah Pekerjaan");
        System.out.println("2. Edit Pekerjaan");
        System.out.println("3. Hapus Pekerjaan");
        System.out.print("Pilihan Menu : ");
        pilihan = input.nextInt();
    }
    
    public void menuTambahPekerjaan(){
        String kompetensi;
        int lanjut;
        System.out.print("Masukkan id : ");
        id = input.nextLine();
        System.out.print("Masukkan nama pekerjaan : ");
        nama = input.nextLine();
        do {
            System.out.print("Masukkan ID kompetensi : ");
            kompetensi = input.nextLine();
            Kompetensi k = new Kompetensi(kompetensi);
            listKompetensi.add(k);
            System.out.print("Apakah ingin menambahkan kompetensi lagi? (1 untuk yes/0 untuk no)" );
            lanjut = input.nextInt();
        }while (lanjut == 1);
    }
    
    public void MenuEditPekerjaan(){
        System.out.println("Id ditemukan, pilih bagian yg akan diedit ");
        //id = input.next();
        System.out.println("1. Edit Nama Pekerjaan");
        System.out.println("2. Edit Kompetensi Pekerjaan");
        System.out.print("Masukkan pilihan : ");
        pilihan = input.nextInt();
    }
    
     public void MenuCekEditPekerjaan(){
        System.out.print("Masukkan ID Pekerjaan yang akan diedit : ");
        id = input.nextLine();
    }
     
    
    public void MenuEditNama(){
        System.out.print("Masukkan nama kompetensi  yg baru: ");
        nama = input.nextLine();
    }
    
    public void MenuEditKompetensi(){
        String kompetensi;
        int lanjut;
        do {
            System.out.print("Masukkan ID kompetensi : ");
            kompetensi = input.nextLine();
            Kompetensi k = new Kompetensi(kompetensi);
            listKompetensi.add(k);
            System.out.print("Apakah ingin menambahkan kompetensi lagi? (1 untuk yes/0 untuk no)" );
            lanjut = input.nextInt();
        }while (lanjut == 1);
    }
    
    public void MenuHapusPekerjaan(){
        System.out.print("Masukkan id yg akan dihapus : ");
        id = input.nextLine();
    }

    public String getId() {
        return id;
    }

    public int getPilihan() {
        return pilihan;
    }

    public String getNama() {
        return nama;
    }

    public ArrayList<Kompetensi> getListKompetensi() {
        return listKompetensi;
    }
    
    
     
    
     
}
