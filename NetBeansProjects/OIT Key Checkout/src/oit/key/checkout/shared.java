/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oit.key.checkout;
import java.util.ArrayList;
//import oit.key.checkout.Objects.*;
import oit.key.checkout.Objects.barcodeObject;
import oit.key.checkout.Objects.clockedObject;
import oit.key.checkout.fileIO.file;
import javax.swing.JOptionPane;
import java.util.Date;
//import java.lang.Boolean;
/**
 *
 * @author phillip.oegema
 */
public class shared {
    ArrayList<barcodeObject> objects = new ArrayList();

    file file = new file();
    
    private ArrayList<clockedObject> techsOnDuty = new ArrayList();
    
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
            if(barcode.equals(b.getBarcode()) && b.getType().equals("user"))
                return true;
        }
        return false;
    }
    
    public boolean isKey(String barcode){
        for(barcodeObject b: objects){
            if(barcode.equals(b.getBarcode()) && b.getType().equals("key"))
                return true;
        }
        return false;
    }

    public ArrayList<clockedObject> getTechsOnDuty() {
        return techsOnDuty;
    }
    
    public void clockTechOnDuty(barcodeObject co){
        boolean onDuty = false;
        int index = -1;
        
        for(clockedObject t: techsOnDuty)
            if(t.getBarcodeObject().getBarcode().equals(co.getBarcode())){
                onDuty = true;
                index = techsOnDuty.indexOf(t);
            }
            if(onDuty)
                techsOnDuty.remove(index);
            else{
                clockedObject clocking = new clockedObject();
                clocking.setBarcodeObject(co);
                clocking.setCheckinTime(new Date());
                clocking.setName(co.getName());
                clocking.setType("user");
                techsOnDuty.add(clocking);            
            }
    }
    
    public barcodeObject getBarcodeObject(String barcode){
        for(barcodeObject b: objects){
            if(b.getBarcode().equals(barcode))
                return b;
        }
        return null;
    }
    
    public String getNamesOnDuty(){
        String techs = "";
        
        for(clockedObject t: techsOnDuty){
            techs = techs.concat(t.getName() + "\n");
        }
        System.out.println("Techs: " + techs);
        return techs;
    }

}
