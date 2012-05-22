/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oit.key.checkout;
import java.util.ArrayList;
//import oit.key.checkout.Objects.*;
import oit.key.checkout.Objects.barcodeObject;
import oit.key.checkout.fileIO.file;
import javax.swing.JOptionPane;
//import java.lang.Boolean;
/**
 *
 * @author phillip.oegema
 */
public class shared {
    ArrayList<barcodeObject> objects = new ArrayList();

    
    file file = new file();
    
    public void setup(){
        ArrayList<barcodeObject> bl = new ArrayList();
        bl = file.readFromFile();
        
        if(bl !=null)
        for(barcodeObject b: bl){
            objects.add(b);
        }
        
      //  file.openForWrite();
    }

    public ArrayList<barcodeObject> getObjects() {
        return objects;
    }
    
    public boolean objectExists(barcodeObject item){
    if(objects != null){
        for(barcodeObject b: objects){
            if(b.getName().equals(item.getName())){
                JOptionPane.showMessageDialog(null, "Name already in use.");
                return true;
            } 
               
            if(b.getBarcode().equals(item.getBarcode())){
                JOptionPane.showMessageDialog(null, "Barcode already in use.");
                return true;             
            }
        }
    }
        return false;
    }
    
    public void loadFile(){

        
    }
    
    public void addObject(barcodeObject bco){
        objects.add(bco);
       file.writeToFile(bco);
    }
    
    public void addObjectToArray(barcodeObject bco){
        objects.add(bco);
    }
    
    public void closeFile(){
        file.closeFile();
    }
    
    public boolean isUser(String barcode){
        for(barcodeObject b: objects){
            if(barcode.equals(b.getBarcode()))
                return true;
        }
        return false;
    }
    
}
