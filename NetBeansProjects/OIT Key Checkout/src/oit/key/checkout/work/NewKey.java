/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oit.key.checkout.work;

import oit.key.checkout.Objects.KeyObject;
import java.lang.Boolean;
import javax.swing.JOptionPane;

/**
 *
 * @author phillip.oegema
 */
public class NewKey {
    
   
    public KeyObject createNewKey(String name, String barcode, String description){
   
        Boolean unique = checkExistingCodes();
        
        if(name.length() < 2){
            JOptionPane.showMessageDialog(null, "Name field too short.");
            return null;
        }
        else if(barcode.length() <= 1){
            JOptionPane.showMessageDialog(null, "Please enter a valid barcode key.");
            return null;
        }
       
        else if(unique == false){
            JOptionPane.showMessageDialog(null, "Barcode currently already in use.");
            return null;
        }
        
        //All tests pass, create new new object
        else{
        
            KeyObject key = new KeyObject();
        
            key.setName(name);
            key.setBarcode(barcode);
            key.setDescription(description);
        
            return key;
        }
    }
    
    
   //Modify to check for any existing barcodes with the same numbering as what was input. 
   private Boolean checkExistingCodes(){
       return true;
   }
}
