/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Dosen;
import model.Mahasiswa;
import model.User;
import org.json.simple.parser.ParseException;
import view.ViewLogin;

/**
 *
 * @author Ardiansyah Setiajati
 */
public class ControllerMain {
    ViewLogin viewLogin;
    User user;
    Mahasiswa mhs;
    

    public ControllerMain() {
        viewLogin = new ViewLogin();
    }
    
    
    
    public void run() throws ParseException{
        
        User user = new User(viewLogin.getUsername(),viewLogin.getPassword());
        
        if(user.LoginFromJson(user.getUsername(), user.getPassword())==3){
            ControllerAdmin ctrAdmin = new ControllerAdmin(user);
            ctrAdmin.ControlMenuAdmin();
            
        }
        else if (user.LoginFromJson(user.getUsername(), user.getPassword())==2){
            //Dosen dsn = new Dosen(user.getUsername()); 
            ControllerDosen ctrDosen = new ControllerDosen(user);
            ctrDosen.ControlMenuDosen();
            
        }
        else if(user.LoginFromJson(user.getUsername(), user.getPassword())==1){
            //Mahasiswa mhs = new Mahasiswa(user.getUsername());
            ControllerMahasiswa ctrMahasiswa = new ControllerMahasiswa(user);
            ctrMahasiswa.ControlMenuMahasiswa();
        } 
        else {
            System.out.println("Username atau Password Salah!!");
            ControllerMain ctrMain = new ControllerMain();
            ctrMain.run();
        }
    }
    
    public static void main(String[] args) throws ParseException {
        ControllerMain main = new ControllerMain();
        main.run();
    }

    private void elseif(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
