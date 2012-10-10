/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vip;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author MindSlave
 */
public class VIP {
    
static int nratacatori=4;
static int nraparatori=4;
public static Vector<Atacator> atacatori;
public static Vector<Aparator> aparatori;
public static Object doneLock;
public static JFrame frame;
private static AgentVIP vip;    
public static int vitezarulare=1;
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        atacatori=new Vector<Atacator>();
        aparatori=new Vector<Aparator>();
        doneLock = new Object();
        Random r=new Random();
        frame = new JFrame("Swing Frame");
        frame.setBounds(0, 0, 805, 620);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        try { 
                frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background.png"))))); 
        } catch (IOException e) { 
                e.printStackTrace(); 
        } 
        frame.setVisible(true); // Display the frame
        InterfataDateAgenti iap=new InterfataDateAgenti(new Aparator());
        InterfataDateAgenti iat=new InterfataDateAgenti(new Atacator());
        JLabel labelvip=new JLabel("VIP");
        labelvip.setForeground(Color.BLUE);
        labelvip.setVisible(true);
        
        frame.add(labelvip);
        vip=new AgentVIP(labelvip, doneLock, 1, 300,aparatori);
        vip.start();

        
        
        for(int i=1;i<nratacatori+1;i++){
            JLabel labelat=new JLabel("AT"+i);
            labelat.setVisible(true);
            labelat.setForeground(Color.RED);
            frame.add(labelat);
            int putere=(r.nextInt(4)+1)*50;
            int cadenta=(r.nextInt(70)+30);
            int raza=(r.nextInt(20));
           Atacator atacator=new Atacator(labelat,vip, 1, 200, r.nextInt(800), r.nextInt(500), 1000, iat, putere,cadenta,raza,aparatori,atacatori);
           atacator.setName("Atacator"+i);
           atacatori.add(atacator);
           
        }
        ordoneazadupapericol();
        for(int x=0;x<atacatori.size();x++){
            System.out.println(atacatori.elementAt(x).getName());
           atacatori.elementAt(x).start(); 
        }
        
        
        
        
        for(int i=1;i<nraparatori+1;i++){
            JLabel labelap=new JLabel("AP"+i);
            labelap.setVisible(true);
            labelap.setForeground(Color.BLUE);
            frame.add(labelap);
            int putere=(r.nextInt(4)+1)*80;
            int cadenta=(r.nextInt(70)+30);
            int raza=(r.nextInt(20));
           Aparator aparator=new Aparator(aparatori, atacatori, labelap, 1, r.nextInt(1000), vip.x, vip.y, vip, doneLock, iap, putere,cadenta,raza);

           aparator.setName("Aparator "+i);
           aparatori.add(aparator);
           
        }
        for(int x=0;x<aparatori.size();x++){
           aparatori.elementAt(x).start(); 
        }
       /* Manager man=new Manager(atacatori, aparatori, true);
        man.start();

*/        

    }


    public static void ordoneazadupapericol() {
        Atacator tmp=null;
    boolean doMore = true;
    while (doMore) {
        doMore = false;
        for (int i=0; i<atacatori.size()-1; i++) {
            if (distantapanalavip(atacatori.elementAt(i))
                 > 
                    distantapanalavip(atacatori.elementAt(i+1))) {
               // exchange elements
               tmp = atacatori.elementAt(i);
               atacatori.setElementAt(atacatori.elementAt(i+1), i);
               atacatori.setElementAt(tmp, i+1);
               doMore = true; 
            }
        }
    }
    for(int y=0;y<atacatori.size();y++){
        System.out.println(distantapanalavip(atacatori.elementAt(y)));
    }
}

    public static double distantapanalavip(Atacator a){
        double distanta = 0;
            distanta= Math.sqrt(Math.pow(a.xactual-vip.x, 2) + Math.pow(a.yactual-vip.y, 2));
        return distanta;
    }

    public static Vector<Atacator> getAtacatori() {
        return atacatori;
    }

    
}
