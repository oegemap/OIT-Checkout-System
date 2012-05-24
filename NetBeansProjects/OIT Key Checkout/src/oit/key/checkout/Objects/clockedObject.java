/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package oit.key.checkout.Objects;
import java.util.Date;
import oit.key.checkout.Objects.barcodeObject;
/**
 *
 * @author phillip.oegema
 */
public class clockedObject {
    private String name;
    private Date checkinTime;
    private Date checkoutTime;
    private barcodeObject barcodeObject;

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public Date getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(Date checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public barcodeObject getBarcodeObject() {
        return barcodeObject;
    }

    public void setBarcodeObject(barcodeObject barcodeObject) {
        this.barcodeObject = barcodeObject;
    }
    
}
