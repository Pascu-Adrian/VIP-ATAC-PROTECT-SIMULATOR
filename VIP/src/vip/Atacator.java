package vip;

import java.util.Random;
import java.util.Vector;
import javax.swing.JLabel;

public class Atacator extends Thread{

    AgentVIP vip;
    //viteza
    int pasviteza;
    int timpviteza;
    //pozitie actuala
    int xactual;
    int yactual;
    int timpAtac;
    Random r;
    boolean vizat=false;
    boolean inviata=true;
    Aparator lovitor;
    public static Vector<Aparator> aparatori;
    JLabel label;
    InterfataDateAgenti ida;
    GUIgrupAgentAtacator gu;
    public int viata=5000;
    public int putere;
    private int cadenta;
    private int raza;
    private Vector<Atacator> listaatacatori;
    private Vector<Aparator> listaaparatori;
    public Atacator() {
    }

    public Atacator(JLabel label, AgentVIP vip, int pasviteza, int timpviteza, int xactual, int yactual, int timpAtac, InterfataDateAgenti ida, int putere,int cadenta, int raza,Vector<Aparator> listaaparatori,Vector<Atacator> listaatacatori) {
        r=new Random();
        this.listaaparatori=listaaparatori;
        this.lovitor=null;
        this.aparatori=new Vector<Aparator>();
        this.listaatacatori=listaatacatori;
        this.vip = vip;
        this.pasviteza = pasviteza;
        this.timpviteza = timpviteza;
        this.xactual = xactual;
        this.yactual = yactual;
        this.timpAtac = timpAtac;
        this.label = label;
        this.ida = ida;
        this.putere=putere;
        this.cadenta=cadenta;
        this.raza=raza;
          gu=new GUIgrupAgentAtacator("NUME: "+getName());
          ida.adaugaagent(gu);
          gu.put.setText(""+putere);
          gu.vit.setText(""+timpviteza);
          gu.via.setText(""+viata);
          
        label.setBounds(xactual, yactual, 150, 90);
        scrie("Atacator "+this.getName()+" creat viteza->"+pasviteza+"/"+timpviteza+" pozitia-> x"+xactual+" y"+yactual);
    }



    

    @Override
    public void run() {
        while(inviata&&vip.getinviata()&&!vip.getsecured()){
            verificaperimetru();
            miscasprevip();           
            scrie("Atacator "+this.getName()+" pozitia: x:"+xactual+" y:"+yactual);
            asteapta(timpviteza);
        }
        
        /*while(vip.getinviata()&&!vip.getsecured()&&inviata){
            verificaperimetru();
            if(aparatori.isEmpty()){
                if(estevipatacabil())
                    asasineaza();
                else{
              scrie("Atacator "+this.getName()+" se misca...");
            miscasprevip();
            
            asteapta(timpviteza);  
            }
            }
            
            if(!aparatori.isEmpty()){
                    ordoneazadupapericol();
                if(!aparatori.isEmpty()&&esteaparatoratacabil(aparatori.firstElement()))
                    if(estevipatacabil())
                    asasineaza();
                    else
                    atacaaparator();
                else{
            scrie("Atacator "+this.getName()+" se misca...");
            miscaspreaparator();
            ml=new MiscaLabel(label, xactual, yactual);
            ml.start();
            scrie("Atacator "+this.getName()+" pozitia: x:"+xactual+" y:"+yactual);
            asteapta(timpviteza);
            }      
            }         
        }*/
        if(listaatacatori.contains(this))
        listaatacatori.remove(this);
        scrie("OPRIT!");
        gu.stare.setText("ELIMINAT!");
        label.setVisible(false);

    }



    public void ordoneazadupapericol() {
        if(aparatori.size()>1){
        Aparator tmp=null;
    boolean doMore = true;
    while (doMore&&!aparatori.isEmpty()&&aparatori.size()>1) {
        doMore = false;
        for (int i=0; i<aparatori.size()-1; i++) {
            if (aparatori.elementAt(i).getInviata()&&aparatori.elementAt(i+1).getInviata()&&distantadintre(this,aparatori.elementAt(i))
                  >
                    distantadintre(this,aparatori.elementAt(i+1))) {
               tmp = aparatori.elementAt(i);
               aparatori.setElementAt(aparatori.elementAt(i+1), i);
               aparatori.setElementAt(tmp, i+1);
               doMore = true;
            }
        }
    }
        }
}  
    
    private void miscaspreaparator() {
        gu.stare.setText("MA MISC");
        gu.xy.setText("x:"+xactual+" y:"+yactual);
        
        if(!estevipatacabil()){
        while(inviata&&!aparatori.isEmpty()&&!esteaparatoratacabil(aparatori.firstElement())){
            
            if(xactual>aparatori.firstElement().getXactual()){
                xactual--;
            }
            if(xactual<aparatori.firstElement().getXactual()){
                xactual++;
            }           
            if(yactual>aparatori.firstElement().getYactual()){
                yactual--;
            }
            if(yactual<aparatori.firstElement().getYactual()){
                yactual++;
            }   
            miscaicoana();
            asteapta(timpviteza);
            scrie(this.getName()+" x: "+getXactual()+" y: "+getYactual());
            verificaperimetru();
        }
            if(inviata&&!aparatori.isEmpty()&&esteaparatoratacabil(aparatori.firstElement()))
            atacaaparator();
            scrie(this.getName()+" x: "+getXactual()+" y: "+getYactual());
        }
        else
            asasineaza();
    }
    



    public void asasineaza(){
        gu.stare.setText("ASASINEZ");
        while(inviata&&vip.getinviata()&&!vip.getsecured()&&estevipatacabil()){
        if(r.nextInt(100)<vip.getVizibilitate()){         
        vip.hit(this);
        scrie("Am lovit vip cu puterea:"+getPutere());
        
        }
        else
            scrie("Am ratat vip");
        asteapta(1000/this.cadenta);
        }
        if(!vip.getinviata())
        scrie(""+this.getName()+" Am asasinat vipul");
        if(!estevipatacabil())
            miscasprevip();
        
    }
    
    
    
   /*private void opreste() {
        gu.stare.setText("OPRIT");
        scrie(this.getName()+" ma opresc");
        listaatacatori.remove(this);
        label.setVisible(false);
        this.interrupt();
    }*/



    void hit(int putere,Aparator at) {
        setViata(viata-putere);
        setLovitor(at);
        if(!aparatori.contains(at))
            aparatori.add(at);
        
    }

    


    private void atacaaparator() {
        gu.stare.setText("ATAC");
        while(inviata&&!aparatori.isEmpty()&&aparatori.firstElement().getInviata()&&esteaparatoratacabil(aparatori.firstElement())){
            if(r.nextInt(100)<aparatori.firstElement().getVizibilitate()){
        aparatori.firstElement().hit(this);
        
                try {
                    scrie("Lovesc aparator:" + aparatori.firstElement().getName() + " cu puterea:" + putere);
                } catch (Exception e) {
                }
            }
            else
                scrie("RATAT:"+aparatori.firstElement().getName());
        
        asteapta(1000/this.cadenta);
        }
        if(estevipatacabil())
            asasineaza();
        if(!aparatori.firstElement().getInviata())
            aparatori.remove(aparatori.firstElement());
        else
            miscaspreaparator();
        scrie(this.getName()+" x: "+getXactual()+" y: "+getYactual());
    }




    private void verificaperimetru() {
        
        try {
            scrie(this.getName() + " Verific perimetru");
        } catch (Exception e) {
        }
        curatalista();
        if(!listaaparatori.isEmpty())
        if(distantadintre(this, vip)<100){
            intercepteaza(vip);

        }
        else{
        for(int i=0;i<listaaparatori.size()&&!listaaparatori.isEmpty()&&aparatori.size()<=5;i++){
            if(!listaaparatori.isEmpty()&&!aparatori.contains(listaaparatori.elementAt(i)))
            if(!listaaparatori.isEmpty()&&distantadintre(this, listaaparatori.elementAt(i))<100)      {
                aparatori.add(listaaparatori.elementAt(i));
            }
            if(listaaparatori.isEmpty()){
               break;
            }
        }
        if(!aparatori.isEmpty()){
            
            try {
                ordoneazadupapericol();
                intercepteaza(aparatori.firstElement());
            } catch (Exception e) {
            }
        }
        }
    }

    
        private void miscasprevip() {
        gu.stare.setText("MA MISC");
        gu.xy.setText("x:"+xactual+" y:"+yactual);
        if(!estevipatacabil()){
            if(xactual>vip.getX()){
                xactual=xactual-pasviteza;
            }
            if(xactual<vip.getX()){
                xactual=xactual+pasviteza;
            }
            
            if(yactual>vip.getY()){
                yactual=yactual-pasviteza;
            }
            if(yactual<vip.getY()){
                yactual=yactual+pasviteza;
            }   
            scrie(this.getName()+" x: "+getXactual()+" y: "+getYactual()); 
        }
        else
            asasineaza();
        miscaicoana();
        asteapta(timpviteza);
           
           verificaperimetru();
    }
    
    
    void mort() {
        scrie("Neutralizat de :"+lovitor.getName());
        scrie("MORT!");
        gu.stare.setText("ELIMINAT");
        label.setVisible(false);
        setInviata(false);
        run();
    }
    
        private boolean estevipatacabil(){
        if(distantadintre(this, vip) <raza){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean esteaparatoratacabil(Aparator ap){
        if(distantadintre(this, ap) <raza){
            return true;
        }
        else{
            return false;
        }
    }
        public void setViata(int viata) {
        this.viata = viata;
        gu.via.setText(""+viata);
        if(!(viata>0)){
            setInviata(false);
        if(!(viata>0)){
        mort();
        }
        }
    }
        private void asteapta(int timp) {  
        try {
            Thread.sleep(10000/timp);
        } catch (InterruptedException ex) {
            System.out.println("Error:"+ex);
        }
    }
     
        public static double distantadintre(Atacator at,Aparator ap){           
            double distanta = 0;
            distanta= Math.sqrt(Math.pow(Math.abs(ap.getXactual()-at.getXactual()), 2) + Math.pow(Math.abs(ap.getYactual()-at.getYactual()), 2));
        return distanta;
        }
        public static double distantadintre(Atacator at,AgentVIP vi){           
            double distanta = 0;
            distanta= Math.sqrt(Math.pow(Math.abs(vi.getX()-at.getXactual()), 2) + Math.pow(Math.abs(vi.getY()-at.getYactual()), 2));
        return distanta;
        }
        public static double distantadintre(Aparator ap,AgentVIP vi){           
            double distanta = 0;
            distanta= Math.sqrt(Math.pow(Math.abs(vi.getX()-ap.getXactual()), 2) + Math.pow(Math.abs(vi.getY()-ap.getYactual()), 2));
        return distanta;
        }
        public static Vector<Aparator> getAparatori() {
        return aparatori;
    }
    public int getCadenta() {
        return cadenta;
    }
    public int getViata() {
        return viata;
    }
    public int getPutere() {
        return putere;
    }
    public int getPasviteza() {
        return pasviteza;
    }  
    public int getTimpAtac() {
        return timpAtac;
    }
    public int getTimpviteza() {
        return timpviteza;
    }
    public AgentVIP getVip() {
        return vip;
    }
    public int getXactual() {
        return xactual;
    }
    public int getYactual() {
        return yactual;
    }
    public JLabel getLabel() {
        return label;
    }
    public void setLovitor(Aparator lovitor){
        this.lovitor=lovitor;
    }
    public void setPasviteza(int pasviteza) {
        this.pasviteza = pasviteza;
    }
    public void setTimpAtac(int timpAtac) {
        this.timpAtac = timpAtac;
    }
    public void setTimpviteza(int timpviteza) {
        this.timpviteza = timpviteza;
    }
    public void setVip(AgentVIP vip) {
        this.vip = vip;
    }
    public Aparator getLovitor() {
        return lovitor;
    }
    public void setVizat(boolean vizat) {
        this.vizat = vizat;
    }
    public void setXactual(int xactual) {
        this.xactual = xactual;
    }
    public void setYactual(int yactual) {
        this.yactual = yactual;
    }
    public boolean getVizat() {
        return vizat;
    }
    private void scrie(String mesaj){
        gu.addtext(mesaj);
    }
    void setInviata(boolean inviata) {
        this.inviata=inviata;
    }
    public boolean getInviata(){
        return inviata;
    }

    private void intercepteaza(AgentVIP vip) {
        while(vip.getinviata()&&!vip.getsecured()&&!estevipatacabil()&&inviata)
            miscasprevip();
        
        while(vip.getinviata()&&!vip.getsecured()&&estevipatacabil()&&inviata)
            asasineaza();
    }

    private void intercepteaza(Aparator firstElement) {
        while(vip.getinviata()&&!vip.getsecured()&&!estevipatacabil()&&inviata&&firstElement.getInviata()){
            miscaspreaparator();
        }
        while(vip.getinviata()&&!vip.getsecured()&&estevipatacabil()&&inviata)
            asasineaza();
        while(vip.getinviata()&&!vip.getsecured()&&esteaparatoratacabil(firstElement) &&inviata)
            atacaaparator();
    }
    private void spune(String mesaj){
        //System.out.println(mesaj);
    }

    private void miscaicoana() {
        label.setBounds(getXactual(), getYactual(), label.getWidth(), label.getHeight());
    }

    private void curatalista() {
        for(int i=0;i<aparatori.size()&&aparatori.size()>0&&inviata;i++){
            try {
                if (!aparatori.elementAt(i).isAlive() || aparatori.elementAt(i).getInviata()) {
                    aparatori.removeElementAt(i);
                }
            } catch (Exception e) {
                aparatori.removeAllElements();
            }
        }
    }
}
