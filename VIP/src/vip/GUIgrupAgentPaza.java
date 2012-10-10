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
public class GUIgrupAgentPaza extends JPanel{
    JScrollPane scrl;
    int width=200;
    JLabel NUME;
    
    JLabel PROP;
    JLabel Prop1;
    JLabel Prop2;
    JLabel Prop3;
    
    public JLabel viata;
    public JLabel putere;
    public JLabel viteza;
    
    JLabel STATE;
    public JLabel xy;
    public JLabel stare;
    public JLabel frag;
    
    JLabel ACTIONL;
    JTextArea action;

    public GUIgrupAgentPaza(String NUMESTR) {
        this.setLayout(null);
        setPreferredSize(new Dimension(width, 284));
        NUME=new JLabel(NUMESTR);
        NUME.setBounds(0, 0, width, 20);
        add(NUME);
        NUME.setVisible(true);
        
        PROP=new JLabel("PROPRIETATI");
        PROP.setBounds(0, 22, width/3+width/3, 20);
        add(PROP);
        PROP.setVisible(true);
        
        Prop1=new JLabel("VIATA:");
        Prop1.setBounds(0, 44, width/3, 20);
        add(Prop1);
        Prop1.setVisible(true);
        
        Prop2=new JLabel("PUTERE");
        Prop2.setBounds(0, 66, width/3, 20);
        add(Prop2);
        Prop2.setVisible(true);
        
        Prop3=new JLabel("VITEZA");
        Prop3.setBounds(0, 88, width/3, 20);
        add(Prop3);
        Prop3.setVisible(true);
        
        
        viata=new JLabel("Propw1");
        viata.setBounds(width/3, 44, width/3, 20);
        add(viata);
        viata.setVisible(true);
        
        putere=new JLabel("Propw2");
        putere.setBounds(width/3, 66, width/3, 20);
        add(putere);
        putere.setVisible(true);
        
        viteza=new JLabel("Propw3");
        viteza.setBounds(width/3, 88, width/3, 20);
        add(viteza);
        viteza.setVisible(true);
        
        
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
        
        frag=new JLabel("State3");
        frag.setBounds(width/3+width/3, 88, width/3, 20);
        add(frag);
        frag.setVisible(true);
        
        
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
