/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plus;


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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Client
 */
public class BoxConfirm extends Application {
    
   
    
    
    
    private String Option="NULL";
    public  Button oui,non;
    public ImageView v;
    public Stage me;
    private doOnClick meth;
    
    public Stage s;
    
    public BoxConfirm(doOnClick meth) throws Exception{
        start(new Stage());
        this.meth=meth;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("BoxConfirmUI.fxml"));
        me=stage;
        Scene scene = new Scene(root);
         oui=(Button)scene.lookup("#oui");
        non=(Button)scene.lookup("#non");
        v=(ImageView)scene.lookup("#exit");
        
        stage.setScene(scene);
       stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
        s=stage;
      //  new Drag().DragFen((Pane)scene.lookup("#barExit"));
      
      oui.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                meth.onConfirm(s);
                }
      
      });
      
      //On annluer ..
      
      non.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                meth.onAnnul(s);
                }
      
      });
        
    }
    
   

    /**
     * @param args the command line arguments
     */
  
    
}
