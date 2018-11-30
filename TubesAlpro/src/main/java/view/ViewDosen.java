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
 * @author ASUS
 */
public class ViewDosen {
    private int pilihan;
    private String username, password, nama;
    private ArrayList<Kompetensi> kompetensi = new ArrayList<Kompetensi>();
    private ArrayList<String> libur = new ArrayList<String>();
    
    Scanner input = new Scanner(System.in);
    public ViewDosen(){
    }
    
    public void menuDosen(){
        System.out.println("#Menu Utama Dosen#");
        System.out.println("1.  Kelola Data Diri");
        System.out.println("2.  Kelola Jadwal");
        System.out.println("3.  Tambah Hari Libur");
        System.out.println("4.  Ubah Password");
        System.out.print("Pilih Menu : ");
        pilihan = input.nextInt();
    }
    
    public void menuKelolaAkun() {
        System.out.println("1. Edit Nama");
        System.out.println("2. Tambah Kompetensi");
        System.out.print("Pilihan Menu : ");
        pilihan = input.nextInt();
    }

    public int getPilihan() {
        return pilihan;
    }
    
    public void MenuEditNama(){
        System.out.print("Masukkan nama yg baru: ");
        nama = input.nextLine();
        //System.out.println(nama);
    }
    
    public void MenuEditPassword(){
        System.out.print("Masukkan password baru: ");
        password = input.nextLine();
        //System.out.println(nama);
    }
    
    public void MenuEditKompetensi(){
        String inKompetensi;
        int lanjut;
        do {
            System.out.print("Masukkan ID kompetensi yg dimiliki : ");
            inKompetensi = input.nextLine();
            Kompetensi k = new Kompetensi(inKompetensi);
            kompetensi.add(k);
            System.out.print("Apakah ingin menambahkan prasyarat lagi? (1 untuk yes/0 untuk no)" );
            lanjut = input.nextInt();
        }
        while (lanjut == 1);
    }
    
     public void MenuTambahLibur(){
        String inLibur;
        int lanjut;
        do {
            System.out.print("Masukkan hari libur (dd/mm/yyyy): ");
            inLibur = input.nextLine();
            libur.add(inLibur);
            System.out.print("Apakah ingin menambahkan prasyarat lagi? (1 untuk yes/0 untuk no)" );
            lanjut = input.nextInt();
        }
        while (lanjut == 1);
    }
    
    
    
     public String getPassword() {
        return password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getNama() {
        return nama;
    }

    public ArrayList<Kompetensi> getKompetensi() {
        return kompetensi;
    }

    public ArrayList<String> getLibur() {
        return libur;
    }
    
    
    
    
    
}
