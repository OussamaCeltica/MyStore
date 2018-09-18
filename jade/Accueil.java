/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Client
 */

//new changes ..
public class Accueil extends Application {
    
    public Accueil() throws IOException{
        this.start(new Stage());
    }
    
    @Override
    public void start(Stage stage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
         Scene scene = new Scene(root);
         
         String css=Accueil.class.getResource("/css/accueilCSS.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        new plus.Drag().DragFen((Pane)scene.lookup("#barTitle"));
        
        /*-------- Reduire la fenetre ---------------*/
       ((ImageView)scene.lookup("#minus")).setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               stage.setIconified(true);
           }
       
       });
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

   
    
}
