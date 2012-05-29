/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clocking;

import oit.key.checkout.Objects.barcodeObject;
import oit.key.checkout.shared;
/**
 *
 * @author phillip.oegema
 */
public class clock {
    shared shared;
    public boolean clockObject(String barcode, shared s){
        shared = s;
        barcodeObject b;
        if(shared.isUser(barcode)){
            b = shared.getBarcodeObject(barcode);
            shared.clockTechOnDuty(b);
            return false;
        }
        else if(shared.isKey(barcode)){
            return true;
        }
        return false;
    }
}
