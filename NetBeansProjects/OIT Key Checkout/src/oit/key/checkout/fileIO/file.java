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


import oit.key.checkout.Objects.*;
/**
 *
 * @author phillip.oegema
 */



public class file {
    private final String FILENAME = "C:\\Key Files\\records";
    
    File file = new File(FILENAME);
    
    ObjectOutputStream objectOut = null;
    
    private void createFile(){
           
        try{
            file.createNewFile();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Could not create File.\n" + e.toString());
        }
    }
    
    public boolean openForWrite(){
        if(file.exists()){

           try{
                objectOut = new ObjectOutputStream(new FileOutputStream(FILENAME));
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
    
    public void writeToFile(Object o){
       if(file.exists()){

           try{             
                objectOut.writeObject(o);
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
          }
       }
        
        
    }
    
    public void openForRead(){
        
        
    }
    
    
    public ArrayList<Object> readFile(){
        ArrayList<Object> objects = new ArrayList();
        
        
        
        
        return objects;
    } 

}
