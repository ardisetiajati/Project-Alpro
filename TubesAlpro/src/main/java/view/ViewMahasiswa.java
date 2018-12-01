/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Kompetensi;
import model.Pekerjaan;
/**
 *
 * @author ASUS
 */
public class ViewMahasiswa {
    private String pilihan;
    private String username, password, nama;
    Scanner input = new Scanner(System.in);
    private ArrayList<Kompetensi> kompetensi = new ArrayList<Kompetensi>();
    private ArrayList<Pekerjaan> pekerjaan = new ArrayList<Pekerjaan>();
    public ViewMahasiswa(){
    }
    
    public void menuMahasiswa(){
        System.out.println("#Menu Utama Mahasiswa#");
        System.out.println("1.  Kelola Data Diri");
        System.out.println("2.  Kelola Jadwal");
        System.out.println("3.  Lihat Riwayat Tagihan");
        System.out.println("4.  Ubah Password");
        System.out.println("5.  Keluar");
        System.out.print("Pilih Menu : ");
        pilihan = input.next();
    }
    
    public void menuKelolaAkun(){
        System.out.println("1. Edit Nama");
        System.out.println("2. Tambah Pekerjaan");
        System.out.println("3. Tambah Kompetensi yang dimiliki");
        System.out.print("Pilihan Menu : ");
        pilihan = input.next();
    }
    
    public void MenuEditNama(){
        System.out.print("Masukkan nama yg baru: ");
        nama = input.nextLine();
    }
    
    public void MenuEditPassword(){
        System.out.print("Masukkan password baru: ");
        password = input.nextLine();
        //System.out.println(nama);
    }
    
    public void MenuTambahKompetensi() {
        String inKompetensi;
        int lanjut;
        do {
            System.out.print("Masukkan ID kompetensi yg dimiliki : ");
            inKompetensi = input.nextLine();
            Kompetensi k = new Kompetensi(inKompetensi);
            kompetensi.add(k);
            System.out.print("\nApakah ingin menambahkan kompetensi lagi? (1 untuk yes/0 untuk no)" );
            lanjut = input.nextInt();
        }
        while (lanjut == 1);
    }
    
    public void MenuTambahPekerjaan() {
        String inPekerjaan;
        int i=1;
        do {
            System.out.println("Masukan ID pekerjaan yang diinginkan ke-"+ i);
            inPekerjaan = input.next();
            if(inPekerjaan.matches("[0-9_]+")== false){
                System.out.println("ID yang anda masukan salah! Lihat Daftar Pekerjaan diatas!");
            } else {
                Pekerjaan p = new Pekerjaan (inPekerjaan);
                pekerjaan.add(p);
                i++;
            }
            
            
        }while(i<=3);
    }
    
    public String getNama() {
        return nama;
    }
    
    public String getPilihan() {
        return pilihan;
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Kompetensi> getKompetensi() {
        return kompetensi;
    }
    
    public ArrayList<Pekerjaan> getPekerjaan() {
        return pekerjaan;
    }

    
}
