/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author izu
 */
public class ViewHapusAkun {

    private String username, password;
    private int role;
    Scanner input = new Scanner(System.in);

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


      public void MenuHapusAkun(){
        System.out.print("Masukkan username yg akan dihapus : ");
        username = input.next();
    }
    
}
