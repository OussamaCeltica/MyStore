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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import plus.doOnClick;

/**
 *
 * @author Client
 */
public class Caisse extends Application {
    
    public static Caisse me;
    
    
    public Caisse() throws SQLException, IOException{
        this.start(new Stage());
        me=this;
    }
    
    @Override
    public void start(Stage stage) throws SQLException, IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("Caisse.fxml"));
         Scene scene = new Scene(root);
         stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
         
         /*String css=Accueil.class.getResource("/css/accueilCSS.css").toExternalForm();
        scene.getStylesheets().add(css);*/
         divAffich=(VBox) scene.lookup("#divAffich");
         divAffich.getChildren().removeAll(divAffich.getChildren());
         
         /*-------- Reduire la fenetre ---------------*/
       ((ImageView)scene.lookup("#minus")).setOnMouseClicked(new EventHandler<MouseEvent>(){
           @Override
           public void handle(MouseEvent event) {
               stage.setIconified(true);
           }
       
       });
         
         total=(Label) scene.lookup("#total");
        
       new plus.Drag().DragFen((Pane)scene.lookup("#barTitle"));
        
        ResultSet r=Jade.BD.read("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv where p.codebar=pv.codebare order by date_vente DESC");
        
        while(r.next()){
            AfficherProduitsVendu(r);
     
        }
        
        r=Jade.BD.read("select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare ");
            while(r.next()){
            total.setText("Bénéfice Total: "+r.getString("benifice")+" DA");
           
            }
        
    }
    
    public void AfficherProduitsVendu(ResultSet r) throws SQLException{
        
        divPr = new Pane();
        nom = new Label();
        code = new Label();
        pVente = new Label();
        quantity = new Label();
        benifice = new Label();
        type = new Label();
        date = new Label();
        suppButt = new Label();
        voirButt = new Label();
        s1 = new Separator();
        s2 = new Separator();
        s3 = new Separator();
        s4 = new Separator();
        s5 = new Separator();
        s6 = new Separator();
        s7 = new Separator();
        s8 = new Separator();
        
        divPr.setMaxHeight(USE_PREF_SIZE);
        divPr.setMaxWidth(USE_PREF_SIZE);
        divPr.setMinHeight(USE_PREF_SIZE);
        divPr.setMinWidth(USE_PREF_SIZE);
        divPr.setPrefHeight(53.0);
        divPr.setPrefWidth(1115.0);
        divPr.setStyle("-fx-background-color: black;");

        nom.setLayoutX(151.0);
        nom.setLayoutY(10.0);
        nom.setPrefHeight(26.0);
        nom.setPrefWidth(132.0);
        nom.setStyle("-fx-font-family: Cairo;-fx-font-size:13");
        nom.setText(r.getString("nom_pr"));
        nom.setTextFill(javafx.scene.paint.Color.WHITE);
         

        code.setLayoutX(14.0);
        code.setLayoutY(10.0);
        code.setPrefHeight(26.0);
        code.setPrefWidth(124.0);
        code.setStyle("-fx-font-family: Cairo;-fx-font-size:13");
        code.setText(r.getString("codebar"));
        code.setTextFill(javafx.scene.paint.Color.WHITE);
        

        pVente.setLayoutX(443.0);
        pVente.setLayoutY(8.0);
        pVente.setPrefHeight(26.0);
        pVente.setPrefWidth(125.0);
        pVente.setStyle("-fx-font-family: Cairo;-fx-font-size:13");
        pVente.setText(r.getString("prix_vente")+" DA");
        pVente.setTextFill(javafx.scene.paint.Color.WHITE);
         

        quantity.setLayoutX(589.0);
        quantity.setLayoutY(8.0);
        quantity.setPrefHeight(26.0);
        quantity.setPrefWidth(123.0);
        quantity.setStyle("-fx-font-family: Cairo;-fx-font-size:13");
        quantity.setText(r.getString("quantity_vendu"));
        quantity.setTextFill(javafx.scene.paint.Color.WHITE);
         

        benifice.setLayoutX(731.0);
        benifice.setLayoutY(10.0);
        benifice.setPrefHeight(26.0);
        benifice.setPrefWidth(116.0);
        benifice.setStyle("-fx-font-family: Cairo;-fx-font-size:13");
        benifice.setText(r.getString("benifice")+" DA");
        benifice.setTextFill(javafx.scene.paint.Color.WHITE);
         

        type.setLayoutX(298.0);
        type.setLayoutY(10.0);
        type.setPrefHeight(26.0);
        type.setPrefWidth(118.0);
        type.setStyle("-fx-font-family: Cairo;");
        type.setText(r.getString("type"));
        type.setTextFill(javafx.scene.paint.Color.WHITE);
        type.setFont(new Font(14.0));

        date.setLayoutX(871.0);
        date.setLayoutY(8.0);
        date.setPrefHeight(26.0);
        date.setPrefWidth(132.0);
        date.setStyle("-fx-font-family: Cairo;");
         date.setText(r.getString("date_vente"));
        date.setTextFill(javafx.scene.paint.Color.WHITE);
        

        suppButt.setAlignment(javafx.geometry.Pos.CENTER);
        suppButt.setLayoutX(1030.0);
        suppButt.setLayoutY(5.0);
        suppButt.setPrefHeight(18.0);
        suppButt.setPrefWidth(64.0);
        suppButt.setStyle("-fx-background-color: red; -fx-background-radius: 25px;");
        suppButt.setText("Supprimer");
        suppButt.setTextFill(javafx.scene.paint.Color.WHITE);
        suppButt.setFont(new Font(9.0));
        suppButt.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
                Pane parent=(Pane) ((Label)e.getSource()).getParent();
                
                System.out.println("code="+((Label)parent.getChildren().get(0)).getText());
                System.out.println("date="+((Label)parent.getChildren().get(4)).getText());
                System.out.println("qt="+((Label)parent.getChildren().get(3)).getText());
                try {
                    new plus.BoxConfirm(new doOnClick(){
                        @Override
                        public void onConfirm(Stage s) {
                            try {
                                Jade.BD.write("delete from produit_vendu where codebare='"+((Label)parent.getChildren().get(0)).getText()+"' and date_vente='"+((Label)parent.getChildren().get(4)).getText()+"'");
                                Jade.BD.write("update produit set quantity=quantity+"+((Label)parent.getChildren().get(3)).getText()+" where codebar='"+((Label)parent.getChildren().get(0)).getText()+"' ");
                                divAffich.getChildren().remove(parent);
                            } catch (SQLException ex) {
                                Logger.getLogger(Caisse.class.getName()).log(Level.SEVERE, null, ex);
                            }
                              s.hide();
                           }
                        
                        @Override
                        public void onAnnul(Stage s) {
                            s.hide();
                            }
                        
                    });
                            } catch (Exception ex) {
                    Logger.getLogger(Caisse.class.getName()).log(Level.SEVERE, null, ex);
                }
                }
        
        });

        voirButt.setAlignment(javafx.geometry.Pos.CENTER);
        voirButt.setLayoutX(1030.0);
        voirButt.setLayoutY(26.0);
        voirButt.setPrefHeight(17.0);
        voirButt.setPrefWidth(67.0);
        voirButt.setStyle("-fx-background-color: white; -fx-background-radius: 25px;");
        voirButt.setText("Voir le produit");
        voirButt.setFont(new Font(9.0));
         voirButt.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
                Pane parent=(Pane) ((Label)e.getSource()).getParent();
                try {
                    //System.out.println("codebar: "+((Label)parent.getChildren().get(0)).getText());
                    new MesProduits(((Label)parent.getChildren().get(0)).getText());
                } catch (IOException ex) {
                    Logger.getLogger(Caisse.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Caisse.class.getName()).log(Level.SEVERE, null, ex);
                }
               }
        
        });

        s1.setLayoutX(142.0);
        s1.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s1.setPrefHeight(50.0);
        s1.setPrefWidth(6.0);

        s2.setLayoutX(289.0);
        s2.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s2.setPrefHeight(50.0);
        s2.setPrefWidth(6.0);

        s3.setLayoutX(435.0);
        s3.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s3.setPrefHeight(50.0);
        s3.setPrefWidth(6.0);

        s4.setLayoutX(579.0);
        s4.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s4.setPrefHeight(50.0);
        s4.setPrefWidth(6.0);

        s5.setLayoutX(723.0);
        s5.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s5.setPrefHeight(50.0);
        s5.setPrefWidth(6.0);

        s6.setLayoutX(867.0);
        s6.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s6.setPrefHeight(50.0);
        s6.setPrefWidth(6.0);

        s7.setLayoutX(1011.0);
        s7.setOrientation(javafx.geometry.Orientation.VERTICAL);
        s7.setPrefHeight(50.0);
        s7.setPrefWidth(6.0);

        s8.setLayoutX(-3.0);
        s8.setLayoutY(51.0);
        s8.setPrefHeight(1.0);
        s8.setPrefWidth(1115.0);
        
         divPr.getChildren().addAll(code,type,pVente,quantity,date,voirButt,suppButt,benifice,nom,s1,s2,s3,s4,s5,s6,s7,s8);
  
        divAffich.getChildren().add(divPr);
        // divAffich.getChildren().add(separator);
        
    }
    
   VBox divAffich;
   Label total;
   
        Pane divPr;
      Label nom;
      Label code;
     Label pVente;
      Label quantity;
      Label benifice;
      Label type;
      Label date;
      Label suppButt;
      Label voirButt;
     Separator s1;
      Separator s2;
      Separator s3;
     Separator s4;
      Separator s5;
     Separator s6;
      Separator s7;
      Separator s8;
        

    
}

    