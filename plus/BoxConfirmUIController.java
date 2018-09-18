/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plus;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

/**
 *
 * @author Client
 */
public class BoxConfirmUIController implements Initializable {
    
    @FXML
    private Label texte;
    
    @FXML
    Button oui,non;
    
    //fermer la fenetre 
   @FXML
   private void exit(MouseEvent e){
        Node source = (Node)e.getSource();
                    Window stage = source.getScene().getWindow();
                    stage.hide();
   }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
