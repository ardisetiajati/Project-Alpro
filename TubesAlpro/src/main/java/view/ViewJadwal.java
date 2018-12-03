/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;

/**
 *
 * @author Ardiansyah Setiajati
 */
public class ViewJadwal {
    int minggu, pilihan;
    Scanner input = new Scanner(System.in);

    public ViewJadwal() {
    }

    public int getMinggu() {
        return minggu;
    }

    public int getPilihan() {
        return pilihan;
    }
    
    public void menuPilihanJadwal(){
        System.out.print("Masukkan pilihan minggu (1-11): ");
        minggu = input.nextInt();
    }
    
    public void menuPilihanKembali(){
        System.out.println("1. Lihat Jadwal");
        System.out.println("2. Kembali");
        System.out.print("Masukkan pilihan : ");
        pilihan = input.nextInt();
    }
    
    
    
    
    
}
