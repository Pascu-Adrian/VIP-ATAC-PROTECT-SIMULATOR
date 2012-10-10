/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vip;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author MindSlave
 */
public class InterfataDateAgenti extends JFrame{
    JScrollPane panou;
    int width;
int height;
int xpoz;
int ypoz;
JPanel pn;
    Vector<GUIgrupAgentPaza> vect;
    Vector<GUIgrupAgentAtacator> vecta;
    public InterfataDateAgenti(Aparator a) throws HeadlessException {
        this.setBounds(815, 0, 220, 710);
        DoSetup();
        vect=new Vector<GUIgrupAgentPaza>();
        
        
    }

    public InterfataDateAgenti(Atacator a) throws HeadlessException {
        this.setBounds(1045, 0, 220, 710);
        DoSetup();
        vecta=new Vector<GUIgrupAgentAtacator>();
    }
    
    
    private void DoSetup() {
        
        this.setBackground(Color.DARK_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        pn=new JPanel();
        pn.setLayout(new GridLayout(0, 1));
        panou=new JScrollPane(pn);
        panou.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pn.setVisible(true);
        add(panou);
        panou.setVisible(true);
        setVisible(true);
    }
    
    public void adaugaagent(GUIgrupAgentAtacator gu){
          gu.setVisible(true);
          vecta.add(gu);
          pn.setPreferredSize(new Dimension(200, vecta.size()*284));
        pn.add(gu); 
        panou.setViewportView(pn);
        
    }
    public void adaugaagent(GUIgrupAgentPaza gu){
          gu.setVisible(true);
          vect.add(gu);
          pn.setPreferredSize(new Dimension(200, vect.size()*284));
        pn.add(gu); 
        panou.setViewportView(pn);
        
    }
}
