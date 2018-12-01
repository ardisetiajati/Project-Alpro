/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ardiansyah Setiajati
 */
public class Tagihan {
    String idMahasiswa;
    long jumlahTagihan;
    boolean statusPembayaran;
    Mahasiswa mhs;

    public Tagihan() {
    }

    public String getIdMahasiswa() {
        return idMahasiswa;
    }

    public long getJumlahTagihan() {
        return jumlahTagihan;
    }

    public boolean isStatusPembayaran() {
        return statusPembayaran;
    }
    
    public void TulisTagihanToJson(){
        
    }
    
    public void EditStatusPembayaranFromJson(){
        
    }
    
    
}
