/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clocking;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Calendar;
import oit.key.checkout.Objects.*;
import java.lang.Long;

/**
 *
 * @author phillip.oegema
 */
public class ClockListener implements ActionListener {
    clockedObject co = new clockedObject();
    String time;
    Calendar diff = null;
    
    public void actionPerformed(ActionEvent e) {
    Calendar now = Calendar.getInstance();
    Calendar checkedOut = null;
    checkedOut.setTime(co.getCheckoutTime());
    
    diff.setTimeInMillis(now.getTimeInMillis() - checkedOut.getTimeInMillis());
    
    }
    
    public String run(clockedObject cob){
        co = cob;
        actionPerformed(null);
        long length = diff.getTimeInMillis();
        Long sec = length/1000;
        Long min = sec/60;
        Long hour = min/60;
   
        return hour.toString() + ":" + min.toString() + ":" + sec.toString();
    }
    
    
}
