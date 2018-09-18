/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;


import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Window;

/**
 *
 * @author Client
 */
public class loginController implements Initializable {
    
    @FXML
    TextField nomAdmin;
    
    @FXML
    PasswordField mpAdmin;
    
    
    /*---------- les méthodes sont par la .. -----------*/
    
    //connecter ..
    @FXML
    private void login(MouseEvent e) throws Exception{
        ResultSet r;
        if(nomAdmin.getText().equals("") || mpAdmin.getText().equals("")){
            new plus.Alert("Remlissez tous les champs !");
            
        }else {
            r=Jade.BD.read("select mdp from admin where user='"+nomAdmin.getText()+"'");
            
            if(r.next()){
                if(r.getString("mdp").equals(mpAdmin.getText())){
                    new Accueil();
                    Node source = (Node)e.getSource();
                    Window stage = source.getScene().getWindow();
                    stage.hide();
                }else {
                     new plus.Alert("Mot de passe incorrect !");
                }
            }else {
                 new plus.Alert("Nom d'utilisateur incorrect !");
            }
            
        }
        
    }
    
    // fermer la fenetre ...
    @FXML
    private void exit(MouseEvent e) {
        System.exit(0);
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
