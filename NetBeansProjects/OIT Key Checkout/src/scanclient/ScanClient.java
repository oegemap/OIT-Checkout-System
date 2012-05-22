/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scanclient;
import oit.key.checkout.Frames.CheckoutInterface;
import oit.key.checkout.shared;
/**
 *
 * @author phillip.oegema
 */
public class ScanClient {
    
    static shared shared = new shared();
    
    
    public static void main(String[] args) {
        shared.setup();
        CheckoutInterface.main(shared);      
    }
    
}
