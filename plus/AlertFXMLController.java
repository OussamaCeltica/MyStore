/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Client
 */
public class AlertFXMLController implements Initializable {

    @FXML
    private void exitButton (MouseEvent e1){
        Node source = (Node)e1.getSource();
        Window stage = source.getScene().getWindow();
        stage.hide();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
