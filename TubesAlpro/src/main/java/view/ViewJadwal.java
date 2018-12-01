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
    int minggu;
    Scanner input = new Scanner(System.in);

    public ViewJadwal() {
    }

    public int getMinggu() {
        return minggu;
    }
    
    public void menuPilihanJadwal(){
        System.out.print("Masukkan pilihan minggu (1-11): ");
        minggu = input.nextInt();
    }
    
}
