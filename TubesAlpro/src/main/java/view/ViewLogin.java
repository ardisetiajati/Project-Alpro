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
public class ViewLogin {
    private int pilihan;
    private String username, password;
    Scanner input = new Scanner(System.in);

    public ViewLogin() {
        menuLogin();
    }
    
    public void menuLogin(){
        System.out.println("#Login#");
        System.out.print("UserName : ");
        username = input.nextLine();
        System.out.print("Password : ");
        password = input.nextLine();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    
}
