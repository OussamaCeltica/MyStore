/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Window;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import net.connectcode.Code128Auto;

/**
 * FXML Controller class
 *
 * @author Client
 */
public class BarcodeController implements Initializable {
    
    public static BarcodeController me;
    
    @FXML
    Pane DivBar;
    
    
    

   @FXML
   public  Label  code;
   
   //*-------------- Les Methodes --------------*/
   @FXML
   private void generateCode(MouseEvent e){
       Code128Auto code2=new Code128Auto();
       // String br=code2.encode("056ggfgh");
        DivBar.getChildren().remove(0);
         final SwingNode swingNode = new SwingNode();
             createAndSetSwingContent(swingNode,"");
             DivBar.getChildren().add(swingNode);
        
       
         
        
        code.setText("056986321145");
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
       me=this;
        
      //  barcode.setFont(Font.font(String.valueOf(java.awt.Font.PLAIN), 24));
    }  
    
     private void createAndSetSwingContent(final SwingNode swingNode,String br ) {
             SwingUtilities.invokeLater(new Runnable() {
                 @Override
                 public void run() {
                     JButton b=new JButton("Click Me !");
                     
                      JLabel barcode=new JLabel("gngngngg");
                      
                      barcode.setOpaque(true);
                      barcode.setBackground(Color.WHITE);
                     
                     barcode.setFont(new java.awt.Font("CCode128_s3_Trial", java.awt.Font.PLAIN,24));
                      swingNode.setContent(new JLabel ("ClickMe !"));
                    
                      
                 }
             });
         }
    
}
