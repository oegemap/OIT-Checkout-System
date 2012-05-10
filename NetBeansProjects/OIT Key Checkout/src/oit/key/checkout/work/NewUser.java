/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oit.key.checkout.work;

import oit.key.checkout.Objects.UserObject;
import java.lang.Boolean;
import javax.swing.JOptionPane;
import oit.key.checkout.shared;
/**
 *
 * @author phillip.oegema
 */
public class NewUser {
    shared shared = null;
    
 public UserObject createNewUser(String name, String barcode, int permissions, shared s){
      shared = s;
      
      Boolean unique;
        
        if(name.length() <=5){
            JOptionPane.showMessageDialog(null, "Name field too short.");
            return null;
        }
        else if(barcode.length() <= 1){
            JOptionPane.showMessageDialog(null, "Please enter a valid barcode key.");
            return null;
        }
       
        else{
            UserObject user = new UserObject();
    
            user.setName(name);
            user.setBarcode(barcode);
            user.setPermissions(permissions);
            unique = checkExistingCodes(user);
            if(!unique)
                return null;
            else
                return user;
        }
}

 private Boolean checkExistingCodes(UserObject u){
       if(shared.userExists(u)){
           JOptionPane.showMessageDialog(null, "Name alreay in use.");
           return false;
       }
       if(shared.barcodeExists(u)){
           JOptionPane.showMessageDialog(null, "Barcode already in use.");
           return false;
       }
     
       return true;
   }
 
}
