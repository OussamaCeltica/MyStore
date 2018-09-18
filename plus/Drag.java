/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plus;


import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Drag  {
    
    public Double x,y;
    
    /*----------- Deplacer un Pane .. -------------*/
    public void Draggable(Pane p){
        
          p.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent m) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 x=m.getX();
                 y=m.getY();
                
            }
        });
        
        
        
        p.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 p.setLayoutX(event.getSceneX()-x);
                  p.setLayoutY(event.getSceneY()-y);
                
            }
            }
          );
    }
    
    
     /*----------- Deplacer un Label .. -------------*/
    public void Draggable(Label p){
        
          p.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent m) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 x=m.getX();
                 y=m.getY();
                
            }
        });
        
        
        
        p.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 p.setLayoutX(event.getSceneX()-x);
                  p.setLayoutY(event.getSceneY()-y);
                
            }
            }
          );
    }
    
    
    /*----------- Deplacer une Fenetre -------------*/
    public void DragFen(Pane p){
        
          p.setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent m) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 x=m.getSceneX();
                 y=m.getSceneY();
                
            }
        });
        
        
        
        p.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 p.getScene().getWindow().setX(event.getScreenX()-x);
                  p.getScene().getWindow().setY(event.getScreenY()-y);
                 //System.out.println("sourDep="+event.getScreenY()+" y="+y);
                
            }
            }
          );
    }
    
    
 
    
}
