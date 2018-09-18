/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Client
 */
public class AccueilController implements Initializable {
    
    @FXML
    Pane addProd,voirProd,vendProd,caisse,type,barCode;
    
    
    /*----------------Les Methodes ------------*/
    
    //ajouter un produit
    @FXML
    private void addProduct(MouseEvent e) throws IOException{
        new AjouterProduit();
        
    }
    
    //afficher les produit ..
    @FXML
   private void AfficherProduct(MouseEvent e) throws IOException, SQLException{
       new MesProduits();
        
    }
   
   //vendre un produit .....
   @FXML
   private void vendProduct(MouseEvent e) throws IOException{
       new Vendre();
        
    }
   
   // voir la caisse ..
   @FXML
   private void voirCaisse(MouseEvent e) throws SQLException, IOException{
        new Caisse();
    }
   
   //Configurer les type ..
    @FXML
   private void configType(MouseEvent e) throws SQLException, IOException, Exception{
        new typePr();
    }
   
   // cgénérer les codes bar ..
     @FXML
   private void generateBarcode(MouseEvent e) throws SQLException, IOException, Exception{
        //new Barcode();
        new CodeBar();
    }
   
   
   
   //fermer la fenetre 
   @FXML
   private void exit(MouseEvent e){
       System.exit(0);
   }
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
