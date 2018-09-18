/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Client
 */
public class Barcode extends Application {
    
    public Barcode () throws Exception{
        start(new Stage());
    }

    @Override
    public void start(Stage stage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("Barcode.fxml"));
         Scene scene = new Scene(root);
        
         
          stage.setScene(scene);
          stage.initStyle(StageStyle.UNDECORATED);
          stage.show();
         
        }
    
}
