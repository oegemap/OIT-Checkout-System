/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oit.key.checkout;
import java.util.ArrayList;
import oit.key.checkout.Objects.*;
import oit.key.checkout.fileIO.file;
//import java.lang.Boolean;
/**
 *
 * @author phillip.oegema
 */
public class shared {
    ArrayList<KeyObject> keys = new ArrayList();
    ArrayList<UserObject> users = new ArrayList();
    file file = new file();
    
    public void setup(){
        file.openForWrite();
    }

    public ArrayList<KeyObject> getKeys() {
        return keys;
    }

    public ArrayList<UserObject> getUsers() {
        return users;
    }
    
    public boolean userExists(UserObject user){
        for(UserObject u: users){
            if(user.getName().equals(u.getName()))
                return true;
        }
        return false;
    }
    
    public boolean keyExists(KeyObject key){
        for(KeyObject k: keys){
            if(key.getName().equals(k.getName()))
                return true;
        }
        return false;
    }
    
    public boolean barcodeExists(KeyObject key){
        for(KeyObject k: keys){
            if(key.getBarcode().equals(k.getBarcode()))
                return true;
        }
        return false;
    }
    
    public boolean barcodeExists(UserObject user){
        for(UserObject u: users){
            if(user.getBarcode().equals(u.getBarcode()))
                return true;
        }
        return false;
    }
    
    public void loadFile(){

        
    }
    
    public void addKey(KeyObject key){
        keys.add(key);
        file.writeToFile(key);
    }
    
    public void addUser(UserObject user){
        users.add(user);
    }
}
