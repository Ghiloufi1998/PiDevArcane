/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import com.maxmind.db.*;
import com.maxmind.db.Reader.FileMode;
import com.maxmind.geoip2.exception.AddressNotFoundException;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.*;
import java.io.File;


/**
 * FXML Controller class
 *
 * @author bensa
 */
public class FXML1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            InetAddress ip =InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            Logger.getLogger(FXML1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
     public void data() throws IOException,GeoIp2Exception{
        String Ip="";
        String loc="C:\\GeoLite2-City.mmdb";
        File db =new File(loc);
        DatabaseReader dbr =new  DatabaseReader 
    }
    
}
