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
public class ViewKelolaKompetensi {
    private int pilihan;
    Scanner input = new Scanner(System.in);

    public ViewKelolaKompetensi() {
    }
    
    public void menuKelolaKompetensi(){
        System.out.println("1. Tambah Kompetensi");
        System.out.println("2. Edit Kompetensi");
        System.out.println("3. Hapus Kompetensi");
        System.out.print("Pilihan Menu : ");
        pilihan = input.nextInt();
    }

    public int getPilihan() {
        return pilihan;
    }
}
