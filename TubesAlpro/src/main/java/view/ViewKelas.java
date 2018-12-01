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
public class ViewKelas {
    int pilihan;
    Scanner input = new Scanner(System.in);

    public int getPilihan() {
        return pilihan;
    }
    
    public void menuOptimasi(){
        System.out.println("1. Optimasi 1");
        System.out.println("2. Optimasi 2");
        System.out.print("Masukkan pilihan optimasi : ");
        pilihan = input.nextInt();
    }
}
