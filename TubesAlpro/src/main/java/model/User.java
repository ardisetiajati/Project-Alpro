package model;

import java.io.File;
import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ardiansyah Setiajati
 */
public class User {
    protected String username;
    protected String nama;
    protected String password;
    protected int role;
    
    public User() {
    }
    
    public User (String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public User (String username){
        this.username = username;
    }
    
    public User(String username, String password, int role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }
    
    public String getNama() {
        return nama;
    }
    
    public void TulisUserToJson(){
        // create object
        JSONObject root = new JSONObject();
        JSONArray user= new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
          
        File f = new File(ConfigDirektori.direktoriAkun);
        
        if (f.exists() && !f.isDirectory()) { // check file is exist , if not create new file
            try {
                //Object  read = parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
                JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
                array = (JSONArray) objFromFile.get("users");
                //array = (JSONArray) read;
            } catch (FileNotFoundException e) {
             e.printStackTrace();
            } catch (IOException e) {
             e.printStackTrace();
            } catch (ParseException ex) {
             Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Create JSONObject and JSONArray and store a class object on it
        JSONObject uo = new JSONObject();
        if (role == 3){
            uo.put("username", username);
            uo.put("password", password);
            uo.put("role", role);
        } 
        else if (role == 2){
            uo.put("username", username);
            uo.put("password", password);
            uo.put("role", role);
            uo.put("nama", username);
            uo.put("NIP", username.substring(3));
             
            JSONArray list2 = new JSONArray();
            uo.put("kompetensi", list2);
        
        } 
        else if (role == 1){    
             uo.put("username", username);
             uo.put("password", password);
             uo.put("role", role);
             uo.put("nama", username);
             uo.put("NIM", username.substring(3));
             
             JSONArray list = new JSONArray();
            
            uo.put("pekerjaan", list);
            
            JSONArray list2 = new JSONArray();
           
            uo.put("kompetensi", list2);
        } 
        else {
            System.out.println("role tidak ditemukan");
        }
        
        if (f.exists() && !f.isDirectory()) {
            array.add(uo);
            // add the array to the root object
            root.put("users",array);
        }
        else{
             user.add(uo);
             // add the array to the root object
            root.put("users",user);
        }
        
        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriAkun)) {
            file.write(root.toJSONString()); // print object to new .json
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 
    
    public boolean CekUserFromJson() {
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        boolean found = false;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            array = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            
            if(itemArr.get("username").equals(username)){ // check if username exist
                found = true;
            }       
        }
        return found;
}
    
    public long LoginFromJson(String username, String Password) {
        JSONParser parser = new JSONParser();
        JSONArray array = null;  
        long roles = 0;
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            array = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
             //System.out.println(itemArr.get("username"));
             //System.out.println(itemArr.get("password"));
             //System.out.println(itemArr.get("role"));
            if (itemArr.get("username").equals(username) && itemArr.get("password").equals(Password)) {
                roles = (long) itemArr.get("role");  
                //roles = ((Long) itemArr.get("role").intValue());
                break;
            }
        }
        //System.out.println(role);
        return roles;
}
    
    public void EditUserFromJson(){
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            array = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            
            if(itemArr.get("username").equals(username)){
                itemArr.put("password", "makanmakan");
            }       
            root.put("users",array);
            
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriAkun)) {
                file.write(root.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void EditUserPasswordAdminFromJson(){
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            array = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            //System.out.println(itemArr.get(username));
            //System.out.println(username);
            if(itemArr.get("username").equals(username)){
                itemArr.put("password", password);
            }       
            root.put("users",array);
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriAkun)) {

            file.write(root.toJSONString());
            file.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void EditUserMahasiswaFromJson(){
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        
        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            array = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            
            if(itemArr.get("username").equals(username)){
                itemArr.put("nama", nama);
            }       
            root.put("users",array);
            
            try (FileWriter file = new FileWriter(ConfigDirektori.direktoriAkun)) {
                file.write(root.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void HapusUserFromJson(){
        JSONObject root = new JSONObject();
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        JSONArray arrayDeleted = new JSONArray();

        try {
            JSONObject  objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriAkun));
            array = (JSONArray) objFromFile.get("users");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < array.size(); i++) {
            JSONObject itemArr = (JSONObject)array.get(i);
            //System.out.println(username);
            if(!itemArr.get("username").equals(username)){
                JSONObject uo = new JSONObject();
                //JSONObject ro = new JSONObject();
                uo.put("username", itemArr.get("username"));
                uo.put("password", itemArr.get("password"));
                uo.put("role", itemArr.get("role"));
                if (itemArr.get("username").toString().length() > 11 && !itemArr.get("username").toString().contains("admin")) {
                    uo.put("nama", itemArr.get("nama"));
                    uo.put("NIP", itemArr.get("NIP"));
                    uo.put("kompetensi", itemArr.get("kompetensi"));
                } 
                else if (itemArr.get("username").toString().length() == 11) {
                    uo.put("nama", itemArr.get("nama"));
                    uo.put("NIM", itemArr.get("NIM"));
                    uo.put("kompetensi", itemArr.get("kompetensi"));
                    uo.put("pekerjaan", itemArr.get("pekerjaan"));
                }
                arrayDeleted.add(uo);
            }       
        }
        root.put("users",arrayDeleted);
        try (FileWriter file = new FileWriter(ConfigDirektori.direktoriAkun)) {
            file.write(root.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void ReadUserFromJson(){
        JSONObject root = new JSONObject();
        JSONArray pekerjaan = new JSONArray();
        JSONParser parser = new JSONParser();
        JSONArray array = null;

        File f = new File(ConfigDirektori.direktoriAkun);
        
        if (f.exists() && !f.isDirectory()) {
        
            try {
                //JSONObject objFromFile = (JSONObject) parser.parse(new FileReader(ConfigDirektori.direktoriKompetensi));
                Object obj = parser.parse(new FileReader(ConfigDirektori.direktoriAkun));

                JSONObject jsonObject = (JSONObject) obj;
                //JSONObject jsonObject2 = (JSONObject) jsonObject.get("kompetensi");
                array = (JSONArray) jsonObject .get("users");

                
                String tbl = "| %-6s | %-20s | %-30s |%-30s |%n";
                System.out.format("%nDaftar Akun%n");
                System.out.format("+----------+----------------------+--------------------------------+--------------------------------+%n");
                System.out.format("|Role      | Username             | Password                       | Nama                           |%n");
                System.out.format("+----------+----------------------+--------------------------------+--------------------------------+%n");

                for (int i = 0; i < array.size(); i++) {
                    char kode;
                    // get all JSON Object
                    JSONObject itemArr = (JSONObject)array.get(i);
                    String username = (String) itemArr.get("username");
                    String name = "";
                    Long role = (Long) itemArr.get("role");
                    if(role!=3){
                        name = (String) itemArr.get("nama");
                    }
                    
                    //set role Name
                    String roleName = "";
                    if(role==1)
                        roleName = "Mhswa";
                    else if(role==2)
                        roleName = "Dosen";
                    else
                        roleName = "Admin";
                        
                    String password = (String) itemArr.get("password");
                    System.out.format(tbl, role.toString() +"("+roleName+")", username, password, name);
                }
                System.out.format("+----------+----------------------+--------------------------------+--------------------------------+%n");
                System.out.println("");
            
            } catch (FileNotFoundException e) {
             e.printStackTrace();
            } catch (IOException e) {
             e.printStackTrace();
            } catch (ParseException ex) {
             Logger.getLogger(Kompetensi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }      
    }
}
