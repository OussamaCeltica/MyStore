/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import plus.BoxConfirm;
import plus.doOnClick;

/**
 *
 * @author Client
 */
public class MesProduits extends Application {
    public static MesProduits me;
    
    
    public MesProduits() throws IOException, SQLException{
        start(new Stage());
         ResultSet r=Jade.BD.read("select * from produit where quantity > 0");
        
        while(r.next()){
            AfficherProduits(r);
            
        }
        
        me=this;
    }
    
    public MesProduits(String Code) throws IOException, SQLException{
        start(new Stage());
         ResultSet r=Jade.BD.read("select * from produit where codebar='"+Code+"'");
        
        while(r.next()){
            AfficherProduits(r);
            
        }
        
        me=this;
    }
    
    @Override
    public void start(Stage stage) throws IOException, SQLException {
       
        Parent root = FXMLLoader.load(getClass().getResource("MesProduits.fxml"));
         Scene scene = new Scene(root);
         stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
         
         /*String css=Accueil.class.getResource("/css/accueilCSS.css").toExternalForm();
        scene.getStylesheets().add(css);*/
         divAffich=(VBox) scene.lookup("#divAffich");
         divAffich.getChildren().removeAll(divAffich.getChildren());
        
       new plus.Drag().DragFen((Pane)scene.lookup("#barTitle"));
       
       /*-------- Reduire la fenetre ---------------*/
       ((ImageView)scene.lookup("#minus")).setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               stage.setIconified(true);
           }
       
       });
       
       
        
       
        
       
    }
    
    public void AfficherProduits(ResultSet r) throws SQLException{
        divProduit = new Pane();
        label5 = new Label();
        label6 = new Label();
        label7 = new Label();
        label8 = new Label();
        label9 = new Label();
        label10 = new Label();
        label11 = new Label();
        code = new Label();
        nomPr = new Label();
        typePr = new Label();
        prixAcht = new Label();
        prixVente = new Label();
        dateStock = new Label();
        quantity = new Label();
        modifButt = new Button();
        suppButt = new Button();
        s1 = new Separator();
        s2 = new Separator();
        s3 = new Separator();
        s4 = new Separator();
        s5 = new Separator();
        s6 = new Separator();
        s7 = new Separator();
        s8 = new Separator();
        sepItem = new Separator();
        
        
                divProduit.setMaxHeight(USE_PREF_SIZE);
        divProduit.setMaxWidth(USE_PREF_SIZE);
        divProduit.setMinHeight(USE_PREF_SIZE);
        divProduit.setMinWidth(USE_PREF_SIZE);
        divProduit.setPrefHeight(55.0);
        divProduit.setPrefWidth(1111.0);
        divProduit.setStyle("-fx-background-color: black;");

        code.setLayoutX(4.0);
        code.setLayoutY(14.0);
        code.setPrefHeight(26.0);
        code.setPrefWidth(117.0);
        code.setText(r.getString("codebar"));
        code.setTextFill(javafx.scene.paint.Color.WHITE);
        code.setStyle("-fx-font-family: Cairo;-fx-font-size:14px;");

        nomPr.setLayoutX(151.0);
        nomPr.setLayoutY(14.0);
        nomPr.setPrefHeight(26.0);
        nomPr.setPrefWidth(140.0);
        nomPr.setTooltip(new Tooltip(r.getString("nom_pr")));
        nomPr.setText(r.getString("nom_pr"));
        nomPr.setTextFill(javafx.scene.paint.Color.WHITE);
        nomPr.setStyle("-fx-font-family: Cairo;-fx-font-size:14px;");

        typePr.setLayoutX(298.0);
        typePr.setLayoutY(14.0);
        typePr.setPrefHeight(26.0);
        typePr.setPrefWidth(133.0);
        typePr.setText(r.getString("type"));
        typePr.setTextFill(javafx.scene.paint.Color.WHITE);
        typePr.setStyle("-fx-font-family: Cairo;-fx-font-size:14px;");

        prixAcht.setLayoutX(444.0);
        prixAcht.setLayoutY(15.0);
        prixAcht.setPrefHeight(26.0);
        prixAcht.setPrefWidth(131.0);
        prixAcht.setText(r.getString("prix_acht")+" DA");
        prixAcht.setTooltip(new Tooltip(r.getString("prix_acht")+" DA"));
        prixAcht.setTextFill(javafx.scene.paint.Color.WHITE);
        prixAcht.setStyle("-fx-font-family: Cairo;-fx-font-size:14px;");

        prixVente.setLayoutX(584.0);
        prixVente.setLayoutY(15.0);
        prixVente.setPrefHeight(26.0);
        prixVente.setPrefWidth(132.0);
        prixVente.setText(r.getString("prix_vente")+" DA");
        prixVente.setTooltip(new Tooltip(r.getString("prix_vente")+" DA"));
        prixVente.setTextFill(javafx.scene.paint.Color.WHITE);
        prixVente.setStyle("-fx-font-family: Cairo;-fx-font-size:14px;");

        dateStock.setLayoutX(728.0);
        dateStock.setLayoutY(14.0);
        dateStock.setPrefHeight(26.0);
        dateStock.setPrefWidth(88.0);
        dateStock.setText(r.getString("date_stock"));
        dateStock.setTooltip( new Tooltip(r.getString("date_stock")));
        dateStock.setTextFill(javafx.scene.paint.Color.WHITE);
        dateStock.setStyle("-fx-font-family: Cairo;-fx-font-size:14px;");

        quantity.setLayoutX(872.0);
        quantity.setLayoutY(15.0);
        quantity.setPrefHeight(26.0);
        quantity.setPrefWidth(152.0);
        quantity.setText(r.getString("quantity"));
        quantity.setTextFill(javafx.scene.paint.Color.WHITE);
        quantity.setStyle("-fx-font-family: Cairo;-fx-font-size:14px;");

        modifButt.setLayoutX(1035.0);
        modifButt.setLayoutY(4.0);
        modifButt.setMnemonicParsing(false);
        modifButt.setPrefHeight(18.0);
        modifButt.setPrefWidth(59.0);
        modifButt.setStyle("-fx-background-color: white; -fx-background-radius: 30px;");
        modifButt.setText("Modifier");
        modifButt.setCursor(Cursor.HAND);
        modifButt.setFont(new Font(8.0));
        modifButt.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
                
                Pane parent=(Pane)((Button)(e.getSource())).getParent();
                
                //System.out.println("code"+((Label)parent.getChildren().get(7)).getText());
                
                MesProduitsController.me.modifFormSetData(((Label)parent.getChildren().get(7)).getText(),((Label)parent.getChildren().get(8)).getText(),((Label)parent.getChildren().get(9)).getText(),((Label)parent.getChildren().get(11)).getText().substring(0, ((Label)parent.getChildren().get(11)).getText().length()-3),((Label)parent.getChildren().get(10)).getText().substring(0,((Label)parent.getChildren().get(10)).getText().length()-3),((Label)parent.getChildren().get(13)).getText());
                
               }
        
        });

        suppButt.setLayoutX(1035.0);
        suppButt.setLayoutY(29.0);
        suppButt.setMnemonicParsing(false);
        suppButt.setPrefHeight(18.0);
        suppButt.setPrefWidth(59.0);
        suppButt.setStyle("-fx-background-color: #9a2626; -fx-background-radius: 30px;");
        suppButt.setText("Supprimer");
        suppButt.setTextFill(javafx.scene.paint.Color.WHITE);
        suppButt.setCursor(Cursor.HAND);
        suppButt.setFont(new Font(8.0));
        suppButt.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
                Pane parent=(Pane)((Button)(e.getSource())).getParent();
                try {
                    BoxConfirm b = null;
                    b=new plus.BoxConfirm(new doOnClick()  {
                        @Override
                        public void onConfirm(Stage s) {
                             System.out.println("code confirm:"+((Label)parent.getChildren().get(7)).getText());
                            try {
                                Jade.BD.write("delete from produit where codebar='"+((Label)parent.getChildren().get(7)).getText()+"'");
                               s.hide();
                               divAffich.getChildren().remove(parent);
                                
                            } catch (SQLException ex) {
                                Logger.getLogger(MesProduits.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        @Override
                        public void onAnnul(Stage s) {
                          System.out.println("code annul:"+((Label)parent.getChildren().get(7)).getText());
                          s.hide();
                          }
                  
                      
                        
                    });
                } catch (Exception ex) {
                    Logger.getLogger(MesProduits.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                }
        
        });

       s1.setLayoutX(143.0);
        s1.setLayoutY(0.0);
        s1.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s1.setPrefHeight(53.0);
        s1.setPrefWidth(6.0);

        s2.setLayoutX(290.0);
        s2.setLayoutY(0.0);
        s2.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s2.setPrefHeight(53.0);
        s2.setPrefWidth(6.0);

        s3.setLayoutX(436.0);
        s3.setLayoutY(0.0);
        s3.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s3.setPrefHeight(53.0);
        s3.setPrefWidth(6.0);

        s4.setLayoutX(580.0);
        s4.setLayoutY(0.0);
        s4.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s4.setPrefHeight(53.0);
        s4.setPrefWidth(6.0);

        s5.setLayoutX(722.0);
        s5.setLayoutY(0.0);
        s5.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s5.setPrefHeight(53.0);
        s5.setPrefWidth(6.0);

        s6.setLayoutX(866.0);
        s6.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s6.setPrefHeight(53.0);
        s6.setPrefWidth(6.0);

        s7.setLayoutX(1012.0);
        s7.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s7.setPrefHeight(53.0);
        s7.setPrefWidth(6.0);

        s8.setLayoutX(-6.0);
        s8.setLayoutY(53.0);
        s8.setPrefHeight(3.0);
        s8.setPrefWidth(1119.0);

        sepItem.setPrefHeight(1.0);
        sepItem.setPrefWidth(1111.0);
        
        divProduit.getChildren().addAll(label5,label6,label7,label8,label9,label10,label11,
                code,nomPr,typePr,prixAcht,prixVente,dateStock,
                quantity,modifButt,suppButt,
                s1,s2,s3,s4,s5,s6,s7,s8
        );
         
      
         
        divAffich.getChildren().add(divProduit);
        //divAffich.getChildren().add(sepItem);
        
        
        
        
       
        
        
        
    }
    
    VBox divAffich;
     Pane divProduit;
     Label label5;
     Label label6;
     Label label7;
     Label label8;
     Label label9;
     Label label10;
     Label label11;
     Label code;
     Label nomPr;
     Label typePr;
     Label prixAcht;
     Label prixVente;
     Label dateStock;
     Label quantity;
     Button modifButt;
     Button suppButt;
      Separator s1;
     Separator s2;
      Separator s3;
      Separator s4;
     Separator s5;
     Separator s6;
      Separator s7;
     Separator s8;
     Separator sepItem;

   
    
}
