/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import plus.MyBD;

/**
 *
 * @author Client
 */
public class Jade extends Application {
    
    public static MyBD BD=new MyBD("jdbc:sqlite:C:\\cabinet\\jadeDB.db","org.sqlite.JDBC");
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("loginFen.fxml"));
         Scene scene = new Scene(root);
         
         String css=Jade.class.getResource("/css/loginCSS.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        new plus.Drag().DragFen((Pane)scene.lookup("#barExit"));
        
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
