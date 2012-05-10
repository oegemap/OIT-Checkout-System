/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminclient;
import oit.key.checkout.Frames.AdminControlPanel;
import oit.key.checkout.shared;
import oit.key.checkout.fileIO.file;

/**
 *
 * @author phillip.oegema
 */
public class AdminClient {
    static shared s = new shared();

    public static void main(String[] args) {

        AdminControlPanel.main(s);
    }
}
