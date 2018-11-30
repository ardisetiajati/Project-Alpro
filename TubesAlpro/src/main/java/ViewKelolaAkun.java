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
public class ViewKelolaAkun {
    private int pilihan;
    Scanner input = new Scanner(System.in);

    public ViewKelolaAkun() {
    }
    
    public void menuKelolaAkun(){
        System.out.println("1. Tambah Akun");
        System.out.println("2. Edit Akun");
        System.out.println("3. Hapus Akun");
        System.out.print("Pilihan Menu : ");
        pilihan = input.nextInt();
    }

    public int getPilihan() {
        return pilihan;
    }
}
