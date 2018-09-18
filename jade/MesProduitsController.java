/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Client
 */
public class MesProduitsController implements Initializable {
    
    public static MesProduitsController me;
    private String produit="";
    
    @FXML
    VBox divAffich;
    
    @FXML
    ComboBox typePr1;
    
    @FXML
    TextField modifPrixAcht,modifPrixVente,modifQuantity,modifNom,modifCodebar,searchNomInp,searchCodeInp;
    @FXML
    ComboBox modifType;
    @FXML
    Button validerModif;
    
    
    public void modifFormSetData(String code,String nom,String type,String pAch,String pVent,String qt){
        produit=code;
       
        modifPrixAcht.setText(pAch);
        modifPrixVente.setText(pVent);
        modifQuantity.setText(qt);
        modifNom.setText(nom);
        modifCodebar.setText(code);
        validerModif.setVisible(true);
        modifType.setValue(type);
        
    }
    
    @FXML
    private void validerModification(MouseEvent e) throws Exception{
        if(modifPrixAcht.getText().equals("") || modifPrixVente.getText().equals("") || modifQuantity.getText().equals("") || modifNom.getText().equals("") || modifCodebar.getText().equals("") || modifType.getSelectionModel().isEmpty() ){
            new plus.Alert("Remplissez tous les champs !");
        }else {
            if(!modifPrixAcht.getText().matches("[0-9]*(.)[0-9]*") || !modifPrixVente.getText().matches("[0-9]*(.)[0-9]*") || !modifQuantity.getText().matches("[0-9]*")  ){
                new plus.Alert("Donner une valeur numérique pour prix et quantité");
            }else {
                Jade.BD.write("update produit set codebar='"+modifCodebar.getText()+"' , nom_pr='"+modifNom.getText()+"',type='"+modifType.getValue()+"',prix_acht='"+modifPrixAcht.getText()+"',prix_vente='"+modifPrixVente.getText()+"',quantity='"+modifQuantity.getText()+"' where codebar='"+produit+"'");
                new plus.Alert("La modification est terminé.");
            }
            
        }
        
    }
    
    /*----------- La Recherche des produits -----------*/
    
    @FXML
    private void ChercherParNom(KeyEvent e) throws SQLException{
        divAffich.getChildren().removeAll(divAffich.getChildren());
        ResultSet r;
        
        if(!searchNomInp.getText().equals("") ){
            
            
            if(typePr1.getValue().toString().equals("Tous")){
                 r= Jade.BD.read("select * from produit where LOWER(nom_pr)='"+searchNomInp.getText().toLowerCase()+"'  OR LOWER(nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND quantity > 0 ");
        
            }else {
                 r= Jade.BD.read("select * from produit where (LOWER(nom_pr)='"+searchNomInp.getText().toLowerCase()+"'  OR LOWER(nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%') AND type='"+typePr1.getValue().toString()+"' AND quantity > 0 ");
        
                
            }
            
            
       
           
            
        }
        
        else {
            if(typePr1.getValue().toString().equals("Tous")){
                 r= Jade.BD.read("select * from produit where quantity > 0");
            }else {
                 r= Jade.BD.read("select * from produit where quantity > 0 AND  type='"+typePr1.getValue().toString()+"' ");
        
                
            }
            
            
        
            
        }
        while (r.next()){
            MesProduits.me.AfficherProduits(r);
        }
        
       
        
        
    }
    
    @FXML
    private void ChercherParCode(KeyEvent e) throws SQLException{
        divAffich.getChildren().removeAll(divAffich.getChildren());
        ResultSet r;
        
        if(!searchCodeInp.getText().equals("")){
            
             if(typePr1.getValue().toString().equals("Tous")){
                 r= Jade.BD.read("select * from produit where codebar='"+searchCodeInp.getText()+"' OR codebar LIKE '"+searchCodeInp.getText()+"%' and quantity > 0  ");
        
            }else {
                  r= Jade.BD.read("select * from produit where codebar='"+searchCodeInp.getText()+"' OR codebar LIKE '"+searchCodeInp.getText()+"%' and quantity > 0 and  type='"+typePr1.getValue().toString()+"'");
        
                
            }
                      
        }else {
           if(typePr1.getValue().toString().equals("Tous")){
                 r= Jade.BD.read("select * from produit where quantity > 0  ");
        
            }else {
                  r= Jade.BD.read("select * from produit where quantity > 0 and  type='"+typePr1.getValue().toString()+"'");
        
                
            }
        
            
        }
        while (r.next()){
            MesProduits.me.AfficherProduits(r);
        }
        
       
        
        
    }
    
    // La Rechercche Par Type
    
    @FXML
    private void ChercherParType(ActionEvent event) throws SQLException {
        ResultSet r = null;
        
         divAffich.getChildren().removeAll(divAffich.getChildren());
        
        //si on a pa un type choisi ..
         if(typePr1.getValue().toString().equals("Tous")){
             
             if(searchCodeInp.getText().equals("") && searchNomInp.getText().equals("")){
                  r= Jade.BD.read("select * from produit where  quantity > 0 ");
        
             
            }else if(searchCodeInp.getText().equals("")){
                 r= Jade.BD.read("select * from produit where (LOWER(nom_pr)='"+searchNomInp.getText().toLowerCase()+"'  OR LOWER(nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%')  AND quantity > 0 ");
        
             
            }else if(searchNomInp.getText().equals("")) {
                 r= Jade.BD.read("select * from produit where codebar='"+searchCodeInp.getText().toLowerCase()+"'  OR codebar LIKE '"+searchCodeInp.getText().toLowerCase()+"%')  AND quantity > 0 ");
        
                
            }
             
         //si on a un type choisi ..
         }else {
             
             if(searchCodeInp.getText().equals("") && searchNomInp.getText().equals("")){
                  r= Jade.BD.read("select * from produit where  type='"+typePr1.getValue().toString()+"' AND quantity > 0 ");
        
             
            }else if(searchCodeInp.getText().equals("")){
                 r= Jade.BD.read("select * from produit where (LOWER(nom_pr)='"+searchNomInp.getText().toLowerCase()+"'  OR LOWER(nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%') AND type='"+typePr1.getValue().toString()+"' AND quantity > 0 ");
        
             
            }else if(searchNomInp.getText().equals("")) {
                 r= Jade.BD.read("select * from produit where codebar='"+searchCodeInp.getText().toLowerCase()+"'  OR codebar LIKE '"+searchCodeInp.getText().toLowerCase()+"%') AND type='"+typePr1.getValue().toString()+"' AND quantity > 0 ");
        
                
            }
             
         }
          while (r.next()){
            MesProduits.me.AfficherProduits(r);
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
        // TODO
        me=this;
        
         try {
            ResultSet r=Jade.BD.read("select * from type");
            while(r.next()){
               modifType.getItems().add(r.getString("nom_ty"));
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        try {
            ResultSet r=Jade.BD.read("select * from type");
            while(r.next()){
                typePr1.getItems().add(r.getString("nom_ty"));
            }
             typePr1.getItems().add("Tous");
            typePr1.setValue("Tous");
             
        } catch (SQLException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        /*
        modifType.getItems().add("Pulls");
       modifType.getItems().add("Vestes");
       modifType.getItems().add("Pantalons");
       modifType.getItems().add("Chaussures");
       modifType.getItems().add("Sacs");
       
        typePr1.getItems().add("Tous");
         typePr1.getItems().add("Pulls");
       typePr1.getItems().add("Vestes");
       typePr1.getItems().add("Pantalons");
       typePr1.getItems().add("Chaussures");
       typePr1.getItems().add("Sacs");
       
       typePr1.setValue("Tous");
*/
    }    
    
}
