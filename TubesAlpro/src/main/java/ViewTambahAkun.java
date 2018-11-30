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
public class ViewTambahAkun {
    private String username, password;
    private int role;
    Scanner input = new Scanner(System.in);
    
    public void menuTambahAkun(){
        System.out.println("Pilihan role");
        System.out.println("1. Mahasiswa");
        System.out.println("2. Dosen");
        System.out.println("3. Admin");
        System.out.print("Masukkan role : ");
        role = input.nextInt();
        switch  (role) {
            case 1:
                            do {
                                System.out.print("Masukkan username : ");
                                username = input.next();
                                    if (username.length() != 11) {
                                            System.out.println("username tidak valid, harus terdiri dari itb<NIM>, mis: itb23518001 ");
                                            }
                                }
                                    while (username.length() != 11);
                                     System.out.print("Masukkan password : ");
                                     password = input.next();
                                     break;
              case 2:
                            do {
                                System.out.print("Masukkan username : ");
                                username = input.next();
                                    if (username.length() != 13) {
                                            System.out.println("username tidak valid, harus terdiri dari itb<NIP>, mis: itb9904201611");
                                            }
                                }
                                    while (username.length() != 13);
                                     System.out.print("Masukkan password : ");
                                     password = input.next();
                                     break;
               case 3:
                            do {
                                System.out.print("Masukkan username : ");
                                username = input.next();
                                    if (!username.contains("admin")) {
                                            System.out.println("username tidak valid, harus terdiri dari admin<NIP>, mis: admin9904201611");
                                            }
                                }
                                    while (!username.contains("admin"));
                                     System.out.print("Masukkan password : ");
                                     password = input.next();
                                     break;
                default:
                                    System.out.println("role tidak ditemukan");
                                     break;
                 }
           
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
}
