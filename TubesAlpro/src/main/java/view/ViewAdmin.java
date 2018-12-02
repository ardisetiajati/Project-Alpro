/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Ardiansyah Setiajati
 */
public class ViewAdmin {
    private String pilihan;
    private String username, password;
    private int role;
    Pattern pattern = Pattern.compile("[A-Za-z0-9_]+");
    Scanner input = new Scanner(System.in);

    public ViewAdmin() {
    }
    
    public void menuAdmin(){
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
        System.out.println("0.  Keluar");
        
        System.out.print("Pilih Menu : ");
        pilihan = input.next();
    }
    
    public void menuKelolaAkun(){
        System.out.println("1. Tambah Akun");
        System.out.println("2. Edit Akun");
        System.out.println("3. Hapus Akun");
        System.out.println("0. Kembali");
        System.out.print("Pilih Menu : ");
        pilihan = input.next();
        
    }
    
    public void menuTambahAkun(){
        //String nim = new String ("itb");
        String cekitb,cekni;
        
        System.out.println("Pilihan role");
        System.out.println("1. Mahasiswa");
        System.out.println("2. Dosen");
        System.out.println("3. Admin");
        System.out.println("0. Kembali");
        System.out.print("Masukkan role : ");
        role = input.nextInt();
        switch  (role) {
            case 1:
                do {
                   
                    System.out.print("Masukkan username : ");
                    username = input.next();
                    cekitb = username.substring(0, 3);
                    cekni = username.substring(3);
                     
                    if (cekitb.equals("itb") == false || username.length() != 11 || cekni.matches("[0-9_]+")== false) {
                        System.out.println("username tidak valid, harus terdiri dari itb<NIM>, mis: itb23518001 ");
                    }
                    
                    //return input=true;
                }while (cekitb.equals("itb") == false || username.length() != 11 || cekni.matches("[0-9_]+")== false);
                System.out.print("Masukkan password : ");
                password = input.next();
                break;
            case 2:
                do {
                    System.out.print("Masukkan username : ");
                    username = input.next();
                    cekitb = username.substring(0, 3);
                    cekni = username.substring(3);
                    
                    if (cekitb.equals("itb") == false || username.length() != 13 || cekni.matches("[0-9_]+")== false) {
                        System.out.println("username tidak valid, harus terdiri dari itb<NIP>, mis: itb9904201611");
                    }
                }while (cekitb.equals("itb") == false || username.length() != 13 || cekni.matches("[0-9_]+")== false);
                System.out.print("Masukkan password : ");
                password = input.next();
                break;
            case 3:
                do {
                    System.out.print("Masukkan username : ");
                    username = input.next();
                    cekitb = username.substring(0, 5);
                    cekni = username.substring(5);
                    
                    if (cekitb.equals("admin") == false || username.length() != 15 || cekni.matches("[0-9_]+")== false) {
                        System.out.println("username tidak valid, harus terdiri dari admin<NIP>, mis: admin9904201611");
                    }
                }while (cekitb.equals("admin") == false || username.length() != 15 || cekni.matches("[0-9_]+")== false);
                System.out.print("Masukkan password : ");
                password = input.next();
                break;
            case 0:
                menuKelolaAkun();
                break;
            default:
                System.out.println("role tidak ditemukan");
                menuTambahAkun();
                break;
        }
           
    }
    
    public void MenuEditAkun(){
        System.out.print("Masukkan username yg akan diganti password nya : ");
        username = input.nextLine();
    }
      
    public void MenuHapusAkun(){
        System.out.print("Masukkan username yg akan dihapus : ");
        username = input.nextLine();
    }
    
     public void MenuEditPassword(){
        System.out.print("Masukkan password baru: ");
        password = input.nextLine();
        //System.out.println(nama);
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }    
    
    public String getPilihan() {
        return pilihan;
    }
    
}
