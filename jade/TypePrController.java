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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Client
 */
public class TypePrController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    public static TypePrController me;
    
    @FXML
      VBox Div_Affich;
    
    @FXML
     TextField new_type;
    
     @FXML
     private void addType(MouseEvent e) throws SQLException, Exception{
         
         if(new_type.getText().equals("")){
             new plus.Alert("Remplissez le champ !");
         }else {
             ResultSet r=Jade.BD.read("select * from type where nom_ty='"+new_type+"'");
            if(r.next()){
                new plus.Alert("Ce type existe déja");
            }else {
                 Jade.BD.write("insert into type(nom_ty) values('"+new_type.getText()+"')");
                 new plus.Alert("Le type est ajouté");
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
     
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        me=this;
       
    }    
    
}
