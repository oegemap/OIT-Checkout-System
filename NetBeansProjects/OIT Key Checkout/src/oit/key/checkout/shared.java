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
import oit.key.checkout.fileIO.recordsFile;
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
    
    recordsFile recordsFile = new recordsFile();
    
    private ArrayList<clockedObject> techsOnDuty = new ArrayList();
    private ArrayList<clockedObject> keysCheckedOut = new ArrayList();
    
    public void setup(){
        ArrayList<barcodeObject> bl = new ArrayList();
        bl = file.readFromFile();
        
        if(bl !=null)
        for(barcodeObject b: bl){
            objects.add(b);
        }
        
      //  file.openForWrite();
    }

    public boolean isKeyCheckedOut(String barcode){
        for(clockedObject c: keysCheckedOut){
            if(c.getBarcodeObject().getBarcode().equals(barcode))
                return true;
        }
        return false;
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
            if(onDuty){
                clockedObject goingOut = techsOnDuty.get(index);
                goingOut.setInOrOut("out");
                goingOut.setCheckoutTime(new Date());
                writeToRecordsFile(goingOut);
                techsOnDuty.remove(index);
                
            }
            else{
                clockedObject clocking = new clockedObject();
                clocking.setBarcodeObject(co);
                clocking.setCheckinTime(new Date());
                clocking.setName(co.getName());
                clocking.setType("user");
                clocking.setInOrOut("in");
                techsOnDuty.add(clocking);  
                writeToRecordsFile(clocking);
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
            techs = techs.concat(t.getName());
          /*  for(clockedObject k: keysCheckedOut){
                if(t.getBarcodeObject().getBarcode().equals(k.getCheckedOutTo().getBarcode()))
                    techs = techs.concat(" (" + k.getName() + ")");
            }*/
            techs = techs.concat("\n");         
        }
        return techs;
    }
    
    public String getKeysCheckedOut(){
        String keys = "";
        for(clockedObject k: keysCheckedOut){
            keys = keys.concat(k.getName() + " " + k.getCheckedOutTo().getName() + " " + k.getCheckoutTime().toString() + "\n");
        }
        return keys;
    }
    
    public void writeToRecordsFile(clockedObject co){
        recordsFile.writeToFile(co);
    }
    
    public void checkoutKey(barcodeObject key, barcodeObject user){
        clockedObject co = new clockedObject();
        boolean out = false;
        int index = -1;
        for(clockedObject c: keysCheckedOut){
            //Checkin key
            if(c.getBarcodeObject().getBarcode().equals(key.getBarcode())){
                index = keysCheckedOut.indexOf(c);
                co = c;
                out = true;           
            }
        }
        if(out){ 
            co.setCheckinTime(new Date());
            co.setInOrOut("in");
            
        }
        else{
           co.setBarcodeObject(key);
            co.setCheckedOutTo(user);
            co.setCheckoutTime(new Date());
            co.setInOrOut("out");
            co.setName(key.getName());
            co.setType("key");
            keysCheckedOut.add(co); 
        }
        writeToRecordsFile(co);
        
        if(out && index >= 0 && index <= keysCheckedOut.size())
            keysCheckedOut.remove(index);
    }
}
