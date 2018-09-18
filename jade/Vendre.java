/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import java.io.IOException;
import java.sql.ResultSet;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Client
 */
public class Vendre extends Application {
    public Vendre() throws IOException{
        this.start(new Stage());
    }
    
    @Override
    public void start(Stage stage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("Vendre.fxml"));
         Scene scene = new Scene(root);
         stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
         
         /*String css=Accueil.class.getResource("/css/accueilCSS.css").toExternalForm();
        scene.getStylesheets().add(css);*/
        
       new plus.Drag().DragFen((Pane)scene.lookup("#barTitle"));
       
       ((ImageView)scene.lookup("#minus")).setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               stage.setIconified(true);
           }
       
       });
        
        
       
       
    }

    
     
    
    
}
