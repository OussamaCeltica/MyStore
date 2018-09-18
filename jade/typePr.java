/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import plus.doOnClick;

/**
 *
 * @author Client
 */
public class typePr extends Application {
    
    public typePr() throws Exception{
        start(new Stage());
    }

    @Override
    public void start(Stage stage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("typePr.fxml"));
         Scene scene = new Scene(root);
         
         TypePrController.me.Div_Affich.getChildren().removeAll(TypePrController.me.Div_Affich.getChildren());
         ResultSet r=Jade.BD.read("select * from type");
         
         while (r.next()){
             afficherTypes(r);
         }
         
          stage.setScene(scene);
          stage.initStyle(StageStyle.UNDECORATED);
          stage.show();
         }
    
    public void afficherTypes(ResultSet r) throws SQLException{
        DivType = new Pane();
        nom_type = new Label();
        supp_type = new ImageView();
        separator0 = new Separator();
        
        DivType.setPrefHeight(55.0);
        DivType.setPrefWidth(525.0);
        DivType.setStyle("-fx-background-color: black;");

        nom_type.setLayoutX(26.0);
        nom_type.setLayoutY(17.0);
        nom_type.setStyle("-fx-font-family: Ubuntu;");
        nom_type.setText(r.getString("nom_ty"));
        nom_type.setTextFill(javafx.scene.paint.Color.WHITE);
        nom_type.setFont(new Font(18.0));

        supp_type.setFitHeight(30.0);
        supp_type.setFitWidth(29.0);
        supp_type.setLayoutX(480.0);
        supp_type.setLayoutY(13.0);
        supp_type.setPickOnBounds(true);
        supp_type.setPreserveRatio(true);
        supp_type.setImage(new Image(getClass().getResource("/images/delete.png").toExternalForm()));
        supp_type.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    Pane parent=(Pane) ((ImageView)e.getSource()).getParent();
                    System.out.println(""+((Label)parent.getChildren().get(0)).getText());
                    new plus.BoxConfirm(new doOnClick(){
                        @Override
                        public void onConfirm(Stage s) {
                            try {
                                Jade.BD.write("delete from type where nom_ty='"+((Label)parent.getChildren().get(0)).getText()+"'");
                                TypePrController.me.Div_Affich.getChildren().remove(parent);
                                new plus.Alert("le type est supprimé");
                                s.hide();
                            } catch (SQLException ex) {
                                Logger.getLogger(typePr.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(typePr.class.getName()).log(Level.SEVERE, null, ex);
                            }
                              }
                        
                        @Override
                        public void onAnnul(Stage s) {
                           s.hide();
                        }
                        
                    });
                } catch (Exception ex) {
                    Logger.getLogger(typePr.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        
        });

        separator0.setPrefWidth(525.0);
        
        DivType.getChildren().addAll(nom_type,supp_type,separator0);
        TypePrController.me.Div_Affich.getChildren().add(DivType);
        
    }
    
      Pane DivType;
     Label nom_type;
      ImageView supp_type;
     Separator separator0;
    
}
