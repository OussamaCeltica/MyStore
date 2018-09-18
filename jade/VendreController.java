/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_PREF_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import plus.doOnClick;

/**
 * FXML Controller class
 *
 * @author Client
 */
public class VendreController implements Initializable {
    
    ArrayList<ProduitVendu> PrVent=new ArrayList<ProduitVendu>();
    
    @FXML
    VBox divAffich;
    
    @FXML
    TextField codeInp,prixInp,quantityInp;
    
    @FXML
    private void buttonAddPanier(MouseEvent e) throws Exception{
        if(codeInp.getText().equals("")   || quantityInp.getText().equals("") ){
            new plus.Alert("Remplissez tous les champs !");
            
        }else {
            if(!quantityInp.getText().matches("[1-9][0-9]*") || !prixInp.getText().matches("[0-9]*") ){
                new plus.Alert("Donner une valeur numérique pour prix ou quantité");
                
            }else {
                ResultSet r;
                if(prixInp.getText().equals("")){
                   r =Jade.BD.read("select codebar,quantity, nom_pr,type,('') as pVente,prix_vente,(prix_vente*"+Integer.parseInt( quantityInp.getText())+") as total,("+quantityInp.getText()+") as quantityVente from produit where codebar='"+codeInp.getText()+"'");
                  
                }else {
                   r=Jade.BD.read("select codebar,quantity, nom_pr,type,prix_vente,("+ prixInp.getText()+") as pVente,("+(Integer.parseInt( prixInp.getText())*Integer.parseInt( quantityInp.getText()))+") as total,("+quantityInp.getText()+") as quantityVente from produit where codebar='"+codeInp.getText()+"'");
              
                }
               
                 while(r.next()){
                   
                   if(r.getInt("quantity")>=r.getInt("quantityVente") ){
                       //Integer.parseInt(r.getInt("quantity"))
                       if(!ExistInPanier(codeInp.getText())){
                       addToPanier(r);
                       if(r.getString("pVente").equals("")){
                           PrVent.add(new ProduitVendu(codeInp.getText(),r.getString("prix_vente"),quantityInp.getText()));
                       }else {
                           PrVent.add(new ProduitVendu(codeInp.getText(),r.getString("pVente"),quantityInp.getText()));
                       }
                        
                        codeInp.setText("");
                        prixInp.setText("");
                        quantityInp.setText("");
                       
                        }else {
                         new plus.Alert("Ce Produit existe déja au panier");
                       
                        }
                       
                   }else {
                        new plus.Alert("On a pas cette quantité !");
                       
                   }
                   
                   
               
               }
            }
                
           
        }
        
    }
    @FXML
    private void validerVente(MouseEvent e) throws SQLException, Exception{
        int i;
        
         Calendar calendar = Calendar.getInstance();
                Date date = calendar.getInstance().getTime();

                  // Display a date in day, month, year format
                  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   String today = formatter.format(date);
                   
        for(i=0;i<PrVent.size();i++){
            Jade.BD.write("insert into produit_vendu(codebare,prix_vente,quantity_vendu,date_vente) values('"+PrVent.get(i).code+"','"+PrVent.get(i).prix+"','"+PrVent.get(i).qt+"','"+today+"')");
            Jade.BD.write("Update produit set quantity=quantity-"+PrVent.get(i).qt+" where codebar='"+PrVent.get(i).code+"'");
        }
        
        new plus.Alert("Le vente est terminé.");
    }
    
    //fermer la fenetre 
   @FXML
   private void exit(MouseEvent e){
        Node source = (Node)e.getSource();
                    Window stage = source.getScene().getWindow();
                    stage.hide();
   }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        divAffich.getChildren().removeAll(divAffich.getChildren());
    }
    
    private boolean ExistInPanier(String code){
        int i;
        for (i=0;i<PrVent.size();i++){
            
            if(PrVent.get(i).code.equals(code)){
                return true;
            }
        }
        return false;
    }
    
    private void addToPanier (ResultSet r) throws SQLException{
        
        divVente = new Pane();
        separator = new Separator();
        separator0 = new Separator();
        separator1 = new Separator();
        separator2 = new Separator();
        separator3 = new Separator();
        code = new Label();
        nom = new Label();
        type = new Label();
        prixV = new Label();
        quantity = new Label();
        prixTot = new Label();
        separator4 = new Separator();
        
        
        
        divVente.setMaxHeight(USE_PREF_SIZE);
        divVente.setMaxWidth(USE_PREF_SIZE);
        divVente.setMinHeight(USE_PREF_SIZE);
        divVente.setMinWidth(USE_PREF_SIZE);
        divVente.setPrefHeight(55.0);
        divVente.setPrefWidth(970.0);
        divVente.setStyle("-fx-background-color: black;");
        divVente.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e) {
               
               System.out.println("hak"+divAffich.getChildren().indexOf((Pane)e.getSource()));
                System.out.println("hak"+PrVent.get((divAffich.getChildren().indexOf((Pane)e.getSource()))/2).code);
                try {
                    new plus.BoxConfirm(new doOnClick(){
                        @Override
                        public void onConfirm(Stage s) {
                           divAffich.getChildren().remove(divAffich.getChildren().indexOf((Pane)e.getSource()));
                           PrVent.remove((divAffich.getChildren().indexOf((Pane)e.getSource()))/2);
                            
                            s.hide();
                            }
                        
                        @Override
                        public void onAnnul(Stage s) {
                            s.hide();
                            }
                        
                    });
                } catch (Exception ex) {
                    Logger.getLogger(VendreController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        });
        
       
        
        

        separator.setLayoutX(152.0);
        separator.setLayoutY(1.0);
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator.setPrefHeight(56.0);
        separator.setPrefWidth(10.0);

        separator0.setLayoutX(314.0);
        separator0.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator0.setPrefHeight(56.0);
        separator0.setPrefWidth(10.0);

        separator1.setLayoutX(462.0);
        separator1.setLayoutY(1.0);
        separator1.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator1.setPrefHeight(56.0);
        separator1.setPrefWidth(10.0);

        separator2.setLayoutX(629.0);
        separator2.setLayoutY(1.0);
        separator2.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator2.setPrefHeight(56.0);
        separator2.setPrefWidth(10.0);

        separator3.setLayoutX(795.0);
        separator3.setOrientation(javafx.geometry.Orientation.VERTICAL);
        separator3.setPrefHeight(56.0);
        separator3.setPrefWidth(10.0);

        code.setAlignment(javafx.geometry.Pos.CENTER);
        code.setLayoutX(5.0);
        code.setLayoutY(21.0);
        code.setPrefHeight(19.0);
        code.setPrefWidth(138.0);
        code.setText(r.getString("codebar"));
        code.setTextFill(javafx.scene.paint.Color.WHITE);
        code.setFont(new Font(13.0));

        nom.setAlignment(javafx.geometry.Pos.CENTER);
        nom.setLayoutX(162.0);
        nom.setLayoutY(20.0);
        nom.setPrefHeight(19.0);
        nom.setPrefWidth(138.0);
        nom.setText(r.getString("nom_pr"));
        nom.setTextFill(javafx.scene.paint.Color.WHITE);
        nom.setFont(new Font(13.0));

        type.setAlignment(javafx.geometry.Pos.CENTER);
        type.setLayoutX(324.0);
        type.setLayoutY(21.0);
        type.setPrefHeight(19.0);
        type.setPrefWidth(138.0);
        type.setText(r.getString("type"));
        type.setTextFill(javafx.scene.paint.Color.WHITE);
        type.setFont(new Font(13.0));
        
        

        prixV.setAlignment(javafx.geometry.Pos.CENTER);
        prixV.setLayoutX(472.0);
        prixV.setLayoutY(20.0);
        prixV.setPrefHeight(19.0);
        prixV.setPrefWidth(138.0);
        if(!r.getString("pVente").equals("")){
             prixV.setText(r.getString("pVente")+" DA");
        }else {
            prixV.setText(r.getString("prix_vente")+" DA"); 
        }
        prixV.setTextFill(javafx.scene.paint.Color.WHITE);
        prixV.setFont(new Font(13.0));

        quantity.setAlignment(javafx.geometry.Pos.CENTER);
        quantity.setLayoutX(639.0);
        quantity.setLayoutY(21.0);
        quantity.setPrefHeight(19.0);
        quantity.setPrefWidth(138.0);
        quantity.setText(r.getString("quantityVente"));
        quantity.setTextFill(javafx.scene.paint.Color.WHITE);
        quantity.setFont(new Font(13.0));

        prixTot.setAlignment(javafx.geometry.Pos.CENTER);
        prixTot.setLayoutX(805.0);
        prixTot.setLayoutY(21.0);
        prixTot.setPrefHeight(19.0);
        prixTot.setPrefWidth(138.0);
        prixTot.setText(r.getString("total")+" DA");
        prixTot.setTextFill(javafx.scene.paint.Color.WHITE);
        prixTot.setFont(new Font(13.0));

        separator4.setPrefWidth(200.0);
        
        divVente.getChildren().add(separator);
        divVente.getChildren().add(separator0);
        divVente.getChildren().add(separator1);
        divVente.getChildren().add(separator2);
        divVente.getChildren().add(separator3);
        divVente.getChildren().add(code);
        divVente.getChildren().add(nom);
        divVente.getChildren().add(type);
        divVente.getChildren().add(prixV);
        divVente.getChildren().add(quantity);
        divVente.getChildren().add(prixTot);
        divAffich.getChildren().add(divVente);
        divAffich.getChildren().add(separator4);
    }

 Pane divVente;
      Separator separator;
      Separator separator0;
      Separator separator1;
      Separator separator2;
      Separator separator3;
      Label code;
      Label nom;
      Label type;
      Label prixV;
      Label quantity;
      Label prixTot;
      Separator separator4;    
    
}
