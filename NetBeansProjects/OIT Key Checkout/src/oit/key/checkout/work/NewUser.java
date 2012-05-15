/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oit.key.checkout.work;

import oit.key.checkout.Objects.barcodeObject;
import java.lang.Boolean;
import javax.swing.JOptionPane;
import oit.key.checkout.shared;
/**
 *
 * @author phillip.oegema
 */
public class NewUser {
    shared shared;
    
 public barcodeObject createNewUser(String name, String barcode, int permissions, shared s){
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
            barcodeObject user = new barcodeObject();
    
            user.setName(name);
            user.setBarcode(barcode);
            user.setPermissions(permissions);
            user.setType('u');
            
            unique = checkExistingCodes(user);
            if(!unique)
                return null;
            else
                return user;
        }
}

 private Boolean checkExistingCodes(barcodeObject u){
       if(shared.objectExists(u)){
           return false;
       }     
       return true;
   }
}
