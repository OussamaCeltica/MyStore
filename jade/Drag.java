/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jade;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Client
 */
public class Drag {
    
    public int mx,my;
    
    
    /* ---------- Jpanel Drag ------------*/
    public void Draggable(JPanel panel){
    final JPanel p = panel;
    
    panel.addMouseListener(new MouseAdapter() {
        
        @Override
        public void mousePressed(MouseEvent me){
            mx=me.getX();
            my=me.getY();
        }
    });
    
        panel.addMouseMotionListener(new MouseMotionAdapter() {
  
            @Override
            public void mouseDragged(MouseEvent me) {
                
                me.translatePoint(me.getComponent().getLocation().x, me.getComponent().getLocation().y);
                 p.setLocation(me.getX()-mx,me.getY()-my);
               
            }

        });
}
 
    /* ---------- Jpanel Drag ------------*/
    public void Draggable(JLabel label){
    final JLabel l = label;
    
    label.addMouseListener(new MouseAdapter() {
        
        @Override
        public void mousePressed(MouseEvent me){
            mx=me.getX();
            my=me.getY();
        }
    });
    
       label.addMouseMotionListener(new MouseMotionAdapter() {
  
            @Override
            public void mouseDragged(MouseEvent me) {
                
                me.translatePoint(me.getComponent().getLocation().x, me.getComponent().getLocation().y);
                 l.setLocation(me.getX()-mx,me.getY()-my);
               
            }

        });
}
    
    /* ---------- Jpanel Drag ------------*/
    public void Draggable(JFrame label){
    final JFrame l = label;
    
    label.addMouseListener(new MouseAdapter() {
        
        @Override
        public void mousePressed(MouseEvent me){
            mx=me.getX();
            my=me.getY();
        }
    });
    
       label.addMouseMotionListener(new MouseMotionAdapter() {
  
            @Override
            public void mouseDragged(MouseEvent me) {
                
                me.translatePoint(me.getComponent().getLocation().x, me.getComponent().getLocation().y);
                 l.setLocation(me.getX()-mx,me.getY()-my);
               
            }

        });
}
    
}
