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
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Client
 */
public class AjouterProduitController implements Initializable {
    
    @FXML
    TextField code,nomPr,prixAcht,prixVente,quantity;
    
    @FXML
    ComboBox typePr;
    
    @FXML
    private void ajouter(MouseEvent e) throws Exception{
        if(code.getText().equals("") || nomPr.getText().equals("") || prixAcht.getText().equals("") || prixVente.getText().equals("") || quantity.getText().equals("") || typePr.getSelectionModel().isEmpty()){
            new plus.Alert("Remplissez tous les champs !");
        }else {
            
            if(!prixAcht.getText().matches("[0-9]*") || !prixVente.getText().matches("[0-9]*") || !quantity.getText().matches("[0-9]*")){
                 new plus.Alert("Donner une valeur numérique pour prix et quantité");
                 
                
            }else {
                //inséré au base de données ..
                
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getInstance().getTime();

                  // Display a date in day, month, year format
                  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                   String today = formatter.format(date);
                   System.out.println("Today : " + today);
                   
                Jade.BD.write("insert into produit (codebar,nom_pr,type,date_stock,prix_vente,prix_acht,quantity) values ('"+code.getText()+"','"+nomPr.getText()+"','"+typePr.getValue()+"','"+today+"',"+prixVente.getText()+","+prixAcht.getText()+","+quantity.getText()+")");
                new plus.Alert("le Produit est ajouté");
                code.setText("");
                nomPr.setText("");
                prixAcht.setText("");
                prixVente.setText("");
                quantity.setText("");
                
            }
            
        }
        
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
        
        try {
            ResultSet r=Jade.BD.read("select * from type");
            while(r.next()){
                typePr.getItems().add(r.getString("nom_ty"));
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
