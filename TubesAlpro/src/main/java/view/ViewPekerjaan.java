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
    private String pilihan;
    private String id, nama;
    private ArrayList<Kompetensi> listKompetensi = new ArrayList<Kompetensi>();
    Scanner input = new Scanner(System.in);
    
    public void menuKelolaPekerjaan(){
        System.out.println("1. Tambah Pekerjaan");
        System.out.println("2. Edit Pekerjaan");
        System.out.println("3. Hapus Pekerjaan");
        System.out.println("0. Kembali");
        System.out.print("Pilihan Menu : ");
        pilihan = input.next();
    }
    
    public void menuTambahPekerjaan(){
        String kompetensi,cekid,cekno;
        int lanjut;
        do {
            System.out.print("Masukkan id : ");
            id = input.next();
            cekid = id.substring(0,1);
            cekno = id.substring(1);
            if(cekid.equals("P")==false||id.length()!=5||cekno.matches("[0-9_]+")==false){
                System.out.println("Inputan ID tidak valid, harus terdiri dari P<kode>, misal : P0001");
            }
        }while(cekid.equals("P")==false||id.length()!=5||cekno.matches("[0-9_]+")==false);
        
        System.out.print("Masukkan nama pekerjaan : ");
        nama = input.nextLine();
        nama = input.nextLine();
        
        do {
            System.out.print("Masukkan ID kompetensi : ");
            kompetensi = input.next();
            cekid = kompetensi.substring(0,2);
            cekno = kompetensi.substring(2);
            if(cekid.equals("IF")==false||kompetensi.length()!=6||cekno.matches("[0-9_]+")==false){
                System.out.println("Inputan ID Kompetensi tidak valid, harus terdiri dari IF<kode>, misal : IF0001");
            } else {
                Kompetensi k = new Kompetensi(kompetensi);
                listKompetensi.add(k);
            }
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
        pilihan = input.next();
    }
    
     public void MenuCekEditPekerjaan(){
        System.out.print("Masukkan ID Pekerjaan yang akan diedit : ");
        id = input.next();
    }
     
    
    public void MenuEditNama(){
        System.out.print("Masukkan nama kompetensi  yg baru: ");
        nama = input.nextLine();
        nama = input.nextLine();
    }
    
    public void MenuEditKompetensi(){
        String kompetensi,cekid,cekno;
        int lanjut;
        
        do {
            System.out.print("Masukkan ID kompetensi : ");
            kompetensi = input.next();
            cekid = kompetensi.substring(0,2);
            cekno = kompetensi.substring(2);
            if(cekid.equals("IF")==false||kompetensi.length()!=6||cekno.matches("[0-9_]+")==false){
                System.out.println("Inputan ID Kompetensi tidak valid, harus terdiri dari IF<kode>, misal : IF0001");
            } else {
                Kompetensi k = new Kompetensi(kompetensi);
                listKompetensi.add(k);
            }
            System.out.print("Apakah ingin menambahkan kompetensi lagi? (1 untuk yes/0 untuk no)" );
            lanjut = input.nextInt();
        }while (lanjut == 1);
    }
    
    public void MenuHapusPekerjaan(){
        System.out.print("Masukkan id yg akan dihapus : ");
        id = input.next();
    }

    public String getId() {
        return id;
    }

    public String getPilihan() {
        return pilihan;
    }

    public String getNama() {
        return nama;
    }

    public ArrayList<Kompetensi> getListKompetensi() {
        return listKompetensi;
    }
    
    
     
    
     
}
