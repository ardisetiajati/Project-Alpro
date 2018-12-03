/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.Scanner;
import model.Tagihan;

/**
 *
 * @author ASUS
 */
public class ViewTagihan {
    private String id, nama, status;
    private String pilihan;
    private int jumlah;
    private boolean sudahDibayar;
    Scanner input = new Scanner(System.in);

    public void menuKelolaTagihan() {
        System.out.println("1. Validasi Tagihan");
        System.out.println("0. Kembali");
        System.out.println("Pilihan Menu : ");
        pilihan = input.next();
    }
    
    public void menuValidasiTagihan() {
        System.out.println("Apakah ingin di validasi bahwa mahasiswa tersebut sudah membayar tagihan? y untuk yes/n untuk no : "); 
        status = input.next();
    }
    
    public void menuCekValidasiTagihan(){
        System.out.println("Masukan ID mahasiswa yang akan di validasi : ");
        id = input.next();
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getPilihan() {
        return pilihan;
    }

    public int getJumlah() {
        return jumlah;
    }
    
    public String getStatus() {
        return status;
    }

    public boolean isSudahDibayar() {
        return sudahDibayar;
    }
    
}
