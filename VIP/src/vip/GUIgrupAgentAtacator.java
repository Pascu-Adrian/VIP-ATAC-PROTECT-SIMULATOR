/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vip;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author MindSlave
 */
public class GUIgrupAgentAtacator extends JPanel{
    JScrollPane scrl;
    int width=200;
    JLabel NUME;
    
    JLabel PROP;
    JLabel viata;
    JLabel putere;
    JLabel viteza;
    

    JLabel via;
    JLabel put;
    JLabel vit;
    
    JLabel STATE;
    JLabel xy;
    JLabel stare;

    
    JLabel ACTIONL;
    JTextArea action;

    public GUIgrupAgentAtacator(String NUMESTR) {
        this.setLayout(null);
        setPreferredSize(new Dimension(width, 284));
        NUME=new JLabel(NUMESTR);
        NUME.setBounds(0, 0, width, 20);
        add(NUME);
        NUME.setVisible(true);
        
        PROP=new JLabel("PROP");
        PROP.setBounds(0, 22, width/3, 20);
        add(PROP);
        PROP.setVisible(true);
        
        viata=new JLabel("VIATA");
        viata.setBounds(0, 44, width/3, 20);
        add(viata);
        viata.setVisible(true);
        
        putere=new JLabel("PUTERE");
        putere.setBounds(0, 66, width/3, 20);
        add(putere);
        putere.setVisible(true);
        
        viteza=new JLabel("VITEZA");
        viteza.setBounds(0, 88, width/3, 20);
        add(viteza);
        viteza.setVisible(true);
        
        
        via=new JLabel("Propw1");
        via.setBounds(width/3, 44, width/3, 20);
        add(via);
        via.setVisible(true);
        
        put=new JLabel("Propw2");
        put.setBounds(width/3, 66, width/3, 20);
        add(put);
        put.setVisible(true);
        
        vit=new JLabel("Propw3");
        vit.setBounds(width/3, 88, width/3, 20);
        add(vit);
        vit.setVisible(true);
        
        
        STATE=new JLabel("STATE");
        STATE.setBounds(width/3+width/3, 22, width/3, 20);
        add(STATE);
        STATE.setVisible(true);
        
        xy=new JLabel("State1");
        xy.setBounds(width/3+width/3, 44, width/3, 20);
        add(xy);
        xy.setVisible(true);
        
        stare=new JLabel("State2");
        stare.setBounds(width/3+width/3, 66, width/3, 20);
        add(stare);
        stare.setVisible(true);
        

        
        
        ACTIONL=new JLabel("ACTION");
        ACTIONL.setBounds(0, 110, width/3, 20);
        add(ACTIONL);
        ACTIONL.setVisible(true);
        
        
        action=new JTextArea();
        //action.setPreferredSize(new Dimension(width, 150));
        scrl=new JScrollPane(action);
        scrl.setBounds(0, 132, width, 150);
        scrl.setLocation(0, 132);
        add(scrl);
        action.setVisible(true);  
        scrl.setVisible(true);
        
    }

    public void addtext(String TXT) {
        action.append(TXT);
        action.append("\n");
        //scrl.setCaretPosition(action.getDocument().getLength()); 
        action.setCaretPosition(action.getDocument().getLength());
    }
    
}
