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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class CaisseController implements Initializable {
    @FXML
    VBox divAffich;
    
    @FXML
     ComboBox typePr;
    
    @FXML
     Label total;
    
    @FXML
    TextField searchNomInp,searchJournéInp;
    
    private void Chercher(String QueryForAffich,String QueryForBenifice) throws SQLException{
        ResultSet r;
        
         r=Jade.BD.read(QueryForAffich);
            while(r.next()){
            Caisse.me.AfficherProduitsVendu(r);
           
            }
            
               r=Jade.BD.read(QueryForBenifice);
               while(r.next()){
                 total.setText("Total: "+r.getString("benifice")+" DA");
           
                }
        
    }
    
    @FXML
    private void ChercherParJourné(KeyEvent e) throws SQLException{
         divAffich.getChildren().removeAll(divAffich.getChildren());
         
          if(!searchJournéInp.getText().equals("")){
            if(searchNomInp.getText().equals("") && typePr.getValue().toString().equals("Tous")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND pv.date_vente LIKE '"+searchJournéInp.getText()+"%'"," select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND pv.date_vente LIKE '"+searchJournéInp.getText()+"%'");
             
             }
            else if(searchNomInp.getText().equals("")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  date_vente LIKE '"+searchJournéInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  date_vente LIKE '"+searchJournéInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' ");
            
            }
            else if(typePr.getValue().toString().equals("Tous")){
                 Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND date_vente LIKE'"+searchJournéInp.getText()+"%' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND date_vente LIKE'"+searchJournéInp.getText()+"%'  ");
            }
            else {
                //les 3 sont rempli ..
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' AND date_vente LIKE'"+searchJournéInp.getText()+"%' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' AND date_vente LIKE'"+searchJournéInp.getText()+"%'  ");
           
            }
         //si le champ de date est vide ..   
        }else {
            
             if(searchNomInp.getText().equals("") && typePr.getValue().toString().equals("Tous")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare group by p.codebar ","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  ");
             }
            else if(searchNomInp.getText().equals("")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare   AND type='"+typePr.getValue()+"' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  type='"+typePr.getValue()+"'   ");
            
            }
            else if(typePr.getValue().toString().equals("Tous")){
                 Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%'  group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' ");
            }
            else {
                //les 2 sont rempli ..
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare   AND type='"+typePr.getValue()+"' AND LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare   type='"+typePr.getValue()+"' AND LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' ");
           
            }
            
        }
        
         
         
         
    
        
    }
    
    @FXML
    private void ChercherParNom(KeyEvent e) throws SQLException{
        divAffich.getChildren().removeAll(divAffich.getChildren());
        
        if(!searchNomInp.getText().equals("")){
            if(searchJournéInp.getText().equals("") && typePr.getValue().toString().equals("Tous")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' ");
             }
            else if(searchJournéInp.getText().equals("")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"'   ");
            
            }
            else if(typePr.getValue().toString().equals("Tous")){
                 Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND date_vente LIKE'"+searchJournéInp.getText()+"%' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND date_vente LIKE'"+searchJournéInp.getText()+"%'  ");
            }
            else {
                //les 3 sont rempli ..
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' AND date_vente LIKE'"+searchJournéInp.getText()+"%' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' AND date_vente LIKE'"+searchJournéInp.getText()+"%'  ");
           
            }
         //si le champ de nom est vide ..   
        }else {
            
             if(searchJournéInp.getText().equals("") && typePr.getValue().toString().equals("Tous")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare group by p.codebar ","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  ");
             }
            else if(searchJournéInp.getText().equals("")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare   AND type='"+typePr.getValue()+"' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  type='"+typePr.getValue()+"'   ");
            
            }
            else if(typePr.getValue().toString().equals("Tous")){
                 Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND date_vente LIKE'"+searchJournéInp.getText()+"%' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND date_vente LIKE'"+searchJournéInp.getText()+"%' ");
            }
            else {
                //les 2 sont rempli ..
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare   AND type='"+typePr.getValue()+"' AND date_vente LIKE'"+searchJournéInp.getText()+"%' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare   type='"+typePr.getValue()+"' AND date_vente LIKE'"+searchJournéInp.getText()+"%'   ");
           
            }
            
        }
  }
    
    //Chercher Par Type ..
    @FXML
    private void ChercherParType(ActionEvent e) throws SQLException{
        
         divAffich.getChildren().removeAll(divAffich.getChildren());
        
        if(!typePr.getValue().equals("Tous")){
            if(searchJournéInp.getText().equals("") && searchNomInp.getText().equals("Tous")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  type='"+typePr.getValue()+"' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  type='"+typePr.getValue()+"' ");
             }
            else if(searchJournéInp.getText().equals("")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"'   ");
            
            }
            else if(searchNomInp.equals("")){
                 Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"'");
            }
            else {
                //les 3 sont rempli ..
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' AND date_vente LIKE'"+searchJournéInp.getText()+"%' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' AND date_vente LIKE'"+searchJournéInp.getText()+"%'  ");
           
            }
         //si le champ de type est a tous ..   
        }else {
            
            if(searchJournéInp.getText().equals("") && searchNomInp.getText().equals("Tous")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare   group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare   ");
             }
            else if(searchJournéInp.getText().equals("")){
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%'  group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%'  ");
            
            }
            else if(searchNomInp.equals("")){
                 Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%'  group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' ");
            }
            else {
                //les 2 sont rempli ..
                Chercher("select p.codebar,p.type ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,pv.date_vente,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%'  AND date_vente LIKE'"+searchJournéInp.getText()+"%' group by p.codebar","select  sum(((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu))) as benifice from produit p , produit_vendu pv  where p.codebar=pv.codebare  AND  LOWER(p.nom_pr) LIKE '"+searchNomInp.getText().toLowerCase()+"%' AND type='"+typePr.getValue()+"' AND date_vente LIKE'"+searchJournéInp.getText()+"%'  ");
           
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
             typePr.getItems().add("Tous");
            typePr.setValue("Tous");
             
        } catch (SQLException ex) {
            Logger.getLogger(AjouterProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    /*
    select p.codebar ,p.nom_pr, pv.prix_vente,pv.quantity_vendu,p.prix_acht, ((pv.prix_vente*pv.quantity_vendu)-(p.prix_acht*pv.quantity_vendu)) as benifice
from produit p , produit_vendu pv
where p.codebar=pv.codebare
    
    */
    
}
