/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oit.key.checkout.fileIO;

import java.lang.Boolean;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import oit.key.checkout.shared;

import oit.key.checkout.Objects.*;
/**
 *
 * @author phillip.oegema
 */



public class file {
    private final String FILENAME = "C:\\Key Files\\records";
    
    File file = new File(FILENAME);
    
    ObjectOutputStream objectOut = null;
    
    shared shared = null;
    
    private void createFile(){
           
        try{
            file.createNewFile();
            openForWrite();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Could not create File.\n" + e.toString());
        }
    }
    
    public boolean openForWrite(){
        if(file.exists()){
           try{
                objectOut = new ObjectOutputStream(new FileOutputStream(FILENAME, true));
                System.err.println("Opened for write");
                //http://www.javadb.com/write-to-file-using-bufferedoutputstream
                //http://www.javadb.com/writing-objects-to-file-with-objectoutputstream
           }
           catch(Exception e){
               JOptionPane.showMessageDialog(null, "Error opening file.\n" + e.toString());
               return false;
           }
          // BufferedOutputStream bs = new BufferedOutputStream(out);
           return true;
        }
       else{
          if(JOptionPane.showConfirmDialog(null, "File does not exist. Create one?") == JOptionPane.YES_OPTION)
              createFile();
            return true;
       }
    }
    
    public void writeToFile(barcodeObject o){
       if(file.exists()){

           try{         
               openForWrite();
               System.out.println(o.getName());
                objectOut.writeObject(o);
                objectOut.close();
                //http://www.javadb.com/write-to-file-using-bufferedoutputstream
                //http://www.javadb.com/writing-objects-to-file-with-objectoutputstream
           }
           catch(Exception e){
               JOptionPane.showMessageDialog(null, "Error writing to file.\n" + e.toString());
           }
          // BufferedOutputStream bs = new BufferedOutputStream(out);
       }
       else{
          if(JOptionPane.showConfirmDialog(null, "File does not exist. Create one?") == JOptionPane.YES_OPTION){
              createFile();
              writeToFile(o);
          }
       }    
    }
    
    public void closeFile(){
        try{
            objectOut.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error closing file." + e.toString());
        }
    }
    
    public ArrayList<barcodeObject> readFile(){
        barcodeObject bco;
        ArrayList<barcodeObject> objects = new ArrayList();
        
        if(file.exists()){
            try{
                FileInputStream fin = new FileInputStream(file);
                System.out.println("after fin");
                ObjectInputStream ois = new ObjectInputStream(fin);
                System.out.println("after ois");
                //bco = (barcodeObject) ois.readObject();
               
              
                while((bco = (barcodeObject) ois.readObject()) != null){
                    objects.add(bco);
                    System.err.println(bco.getName());
                }
                    ois.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error reading file.\n" + e.toString());
                return null;
            }
        }
      return objects;  
    } 

}
