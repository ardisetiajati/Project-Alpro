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
import java.util.Scanner;
import java.io.Console;

public class MainApp {
    static int pilihan;
    static Scanner input = new Scanner(System.in);
    static String username, password;
    
    public static void main(String[] args) {
        System.out.println("#Login#");
        System.out.print("UserName : ");
        username = input.nextLine();
        System.out.print("Password : ");
        password = input.nextLine();
        
        if(username.equals("admin") && password.equals("admin")){
            menuAdmin();
        }
        else if(username.equals("dosen") && password.equals("dosen")){
            menuDosen();
        }
        else if(username.equals("mahasiswa") && password.equals("mahasiswa")){
            menuMahasiswa();
        }
    }
    
    public static void menuAdmin(){
        System.out.println("#Menu Utama Admin#");
        System.out.println("1.  Kelola Akun");
        System.out.println("2.  Kelola Kompetensi");
        System.out.println("3.  Kelola Pekerjaan");
        System.out.println("4.  Lihat Peserta Kelas & Dosen");
        System.out.println("5.  Lihat Jadwal Kelas");
        System.out.println("6.  Lihat Tagihan");
        System.out.println("7.  Lihat Hasil Kelas Pendek");
        System.out.println("8.  Hentikan Periode Isi Data");
        System.out.println("9.  Ubah Password");
        System.out.print("Pilih Menu : ");
        pilihan = input.nextInt();
        switch(pilihan){
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;
            case 4 :
                break;
            case 5 :
                break;
            case 6 :
                break;
            case 7 :
                break;
            case 8 :
                break;
            case 9 :
                break;
        }
    }
    
    public static void menuDosen(){
        System.out.println("#Menu Utama Dosen#");
        System.out.println("1.  Kelola Data Diri");
        System.out.println("2.  Kelola Jadwal");
        System.out.println("3.  Tambah Hari Libur");
        System.out.println("4.  Ubah Password");
        System.out.print("Pilih Menu : ");
        pilihan = input.nextInt();
        switch(pilihan){
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;
            case 4 :
                break;
        }
    }
    
    public static void menuMahasiswa(){
        System.out.println("#Menu Utama Mahasiswa#");
        System.out.println("1.  Kelola Data Diri");
        System.out.println("2.  Kelola Jadwal");
        System.out.println("3.  Tambah Hari Libur");
        System.out.println("4.  Lihat Riwayat Tagihan");
        System.out.println("5.  Ubah Password");
        System.out.print("Pilih Menu : ");
        pilihan = input.nextInt();
        switch(pilihan){
            case 1 :
                break;
            case 2 :
                break;
            case 3 :
                break;
            case 4 :
                break;
            case 5 :
                break;
        }
    }
    
}
