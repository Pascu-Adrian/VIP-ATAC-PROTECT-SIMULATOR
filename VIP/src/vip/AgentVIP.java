
package vip;

import java.awt.Color;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class AgentVIP extends Thread{
    public static Object doneLock;
    int pasviteza,x,y,destinatiecurenta=0,vizibilitate=80,vitezaactuala,viteza,viata=5000;
    int[] xdestinatie=new int[13],ydestinatie=new int[13];
    JLabel label;
    boolean inviata=true,secured=false;
    Vector<Aparator> aparatori;
    Atacator atac;
    
    public AgentVIP() {
    }
    public AgentVIP(JLabel label, Object doneLock, int pasviteza, int timpviteza,Vector<Aparator> aparatori) {
        this.atac=null;
        this.aparatori=aparatori;
        this.doneLock = doneLock;
        this.pasviteza = pasviteza;
        this.vitezaactuala = timpviteza;
        incarcatraseu();
        this.x = xdestinatie[destinatiecurenta];
        this.y = ydestinatie[destinatiecurenta];
        this.label = label;
        label.setBounds(x, y, 100, 100);
        this.setName("VIP");
        spune("Vipul a fost creat: viteza->"+pasviteza+"/"+timpviteza+" pozitia-> x"+x+" y"+y);
    }
    public synchronized int valxactual(){
        return x;
    }
    public synchronized int valyactual(){
        return y;
    }
    public void notifica() {
        synchronized (doneLock) {   
				doneLock.notifyAll();             
			} 
    }

    @Override
    public void run() {      
        while(!secured&&inviata){
            walk();
            notifica();               
            asteapta(vitezaactuala);    
        }
        if(secured){
        spune("Vip am ajuns teafar la destinatie");
         notifica(); 
        }
        if(!inviata){
            spune("Vip sunt mort!");
            label.setForeground(Color.DARK_GRAY);
           notifica();
        }
    }
 
    //----------------------------------------metode pentru interactiune cu alti agenti
    private void help() {
        for(int i=0;i<aparatori.size();i++){
            
            try {
                aparatori.elementAt(i).getAtacatorvizat().setVizat(false);
                if (aparatori.elementAt(i).isAlive() && aparatori.elementAt(i).getAtacatorvizat().getAparatori().contains(aparatori.elementAt(i))) {
                    aparatori.elementAt(i).getAtacatorvizat().getAparatori().remove(aparatori.elementAt(i));
                }
                aparatori.elementAt(i).setAtacatorvizat(atac);
                spune("HELP!");
                spune(aparatori.elementAt(i).getName() + " Ajuta pe VIP");
            } catch (Exception e) {
            }
            //aparatori.elementAt(i).intercepteaza();
            
        }
    }
    
    
    //--------------------------------------------metode pentru actiuni internet
    private void walk() {
        
          if(x>xdestinatie[destinatiecurenta]){

                x=x-pasviteza;
            }
            if(x<xdestinatie[destinatiecurenta]){

                x=x+pasviteza;
            }
            
            if(y>ydestinatie[destinatiecurenta]){
 
                y=y-pasviteza;
            }
            if(y<ydestinatie[destinatiecurenta]){

                y=y+pasviteza;
            }
            if(x==xdestinatie[destinatiecurenta]&&y==ydestinatie[destinatiecurenta]){
                if(x==xdestinatie[12]&&y==ydestinatie[12]){
                secured=true;
                }
                else
                destinatiecurenta++;
            }
        
        label.setBounds(x, y, label.getWidth(), label.getHeight());
            if(pasviteza>0)
        spune("Vip se misca: x"+x+" y"+y);
            
    }
    private void peBurta() {
        vizibilitate=20;
        pasviteza=0;
        spune("Sunt pe burta");
    }
    private void inPicioare() {
        vizibilitate=80;
        pasviteza=1;
        spune("Sunt in picioare");
    }
    private void mort() {
        setInviata(false);
        run();
    }
    public void hit(Atacator at) {
        setViata(viata-at.getPutere());
        spune("VIATA:"+viata);
        spune("LOVIT DE:"+at.getName()+" CU PUTEREA:"+at.getPutere());
        setAtac(at);
        peBurta();
        notifica(); 
        if(aparatori.size()>0)
        help();
        setAtac(null);
                inPicioare();
                walk();
    }
    
    
    //-------------------------------------------get si set
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getViata() {
        return viata;
    }
    public int getVizibilitate() {
        return vizibilitate;
    }  
    public boolean getinviata(){
        return inviata;
    }
    public boolean getsecured(){
        
        return secured;
    }
    public void setAtac(Atacator atac) {
        this.atac = atac;
    }
    public void setViata(int viata) {
        this.viata = viata;
        ///aici pt interfata
        if(!(viata>0)){
            setInviata(false);
            mort();
        }
    }
    public void setSecured(boolean secured) {
        this.secured = secured;
    }
    public void setInviata(boolean inviata) {
        this.inviata = inviata;
    }
    
    
    //------------------metode utils
    private void asteapta(int timpviteza) {
        try {
                Thread.sleep(timpviteza*VIP.vitezarulare);
            } catch (InterruptedException ex) {
                Logger.getLogger(AgentVIP.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    private void spune(String mesaj){
        System.out.println(mesaj);
    }
    private void incarcatraseu() {
        xdestinatie[0]=340;
        ydestinatie[0]=550;
        
        xdestinatie[1]=370;
        ydestinatie[1]=500;
        
        xdestinatie[2]=400;
        ydestinatie[2]=450;
        
        xdestinatie[3]=430;
        ydestinatie[3]=400;
        
        xdestinatie[4]=440;
        ydestinatie[4]=350;
        
        xdestinatie[5]=430;
        ydestinatie[5]=300;
        
        xdestinatie[6]=400;
        ydestinatie[6]=250;
        
        xdestinatie[7]=370;
        ydestinatie[7]=200;
        
        xdestinatie[8]=340;
        ydestinatie[8]=150;
        
        xdestinatie[9]=320;
        ydestinatie[9]=100;
        
        xdestinatie[10]=320;
        ydestinatie[10]=50;
        
        xdestinatie[11]=320;
        ydestinatie[11]=0;
        
        xdestinatie[12]=320;
        ydestinatie[12]=-20;
    }
}
