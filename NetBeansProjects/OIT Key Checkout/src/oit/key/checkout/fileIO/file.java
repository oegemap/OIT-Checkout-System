/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oit.key.checkout.fileIO;

import JSON.JSONObject;
import java.io.*;
import java.lang.Boolean;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import oit.key.checkout.shared;
import java.io.PrintWriter;

import oit.key.checkout.Objects.*;
/**
 *
 * @author phillip.oegema
 */



public class file {
    private final String FILENAME = "C:\\Key Files\\records";
    
    File file = new File(FILENAME);
 
   // BufferedOutputStream bufferedOutput = null;
   
    PrintWriter out = null;
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
              out= new PrintWriter(new FileWriter(FILENAME, true));
                //bufferedOutput = new BufferedOutputStream(new FileOutputStream(FILENAME, true));
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
                out.println(o.getJSONObject().toString());
                //http://www.javadb.com/write-to-file-using-bufferedoutputstream
                //http://www.javadb.com/writing-objects-to-file-with-objectoutputstream
                out.close();
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
            out.close();
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
    
    public ArrayList<barcodeObject> readFromFile() {
        barcodeObject bco = new barcodeObject();
        ArrayList<barcodeObject> objects = new ArrayList();
        
        BufferedReader bufferedReader = null;
        
        try {
            
            //Construct the BufferedReader object
            bufferedReader = new BufferedReader(new FileReader(FILENAME));
            
            String line = null;
            
            while ((line = bufferedReader.readLine()) != null) {
                //Process the data, here we just print it out
                System.out.println(line);
                try{
                    JSONObject json = new JSONObject(line);
                    bco.setName(json.getString("name"));
                    bco.setBarcode(json.getString("barcode"));
                    bco.setType(json.getString("type"));
                    bco.setDescription(json.getString("description"));
                   
                    
                    System.out.println(json.getString("type"));
                    System.out.println(json.getString("name"));
                    System.out.println(json.getString("barcode"));
                    System.out.println(json.getString("description"));
                }catch(JSON.JSONException je){
                    System.out.println(je.toString());
                }
                
            }
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            //Close the BufferedReader
            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
