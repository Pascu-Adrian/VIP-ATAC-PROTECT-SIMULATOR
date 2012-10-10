package vip;

import java.awt.Color;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class Aparator extends Thread {

    int putere,cadenta,raza,pasviteza,timpviteza,xactual,yactual,vizibilitate,frag = 0,viata = 5000;
    private Object doneLock;
    static AgentVIP vip;
    Random r;
    JLabel label;
    static Vector<Atacator> atacatori;
    Vector<Aparator> aparatori;
    InterfataDateAgenti ida;
    GUIgrupAgentPaza gu;
    Atacator lovitor,atacatorvizat = null;
    boolean inviata=true;
    
    public Aparator() {
    }
    public Aparator(Vector<Aparator> aparatori, Vector<Atacator> atacatori, JLabel label, int pasviteza, int timpviteza, int xactual, int yactual, AgentVIP vip, Object donelock, InterfataDateAgenti ida, int putere, int cadenta, int raza) throws IOException {
        this.lovitor = null;
        this.r = new Random();
        this.vizibilitate = 80;
        this.pasviteza = pasviteza;
        this.timpviteza = timpviteza;
        this.xactual = xactual;
        this.yactual = yactual;
        this.vip = vip;
        this.doneLock = donelock;
        this.label = label;
        this.atacatori = atacatori;
        this.aparatori = aparatori;
        this.ida = ida;
        this.putere = putere;
        this.cadenta = cadenta;
        this.raza = raza;
        gu = new GUIgrupAgentPaza("NUME: " + getName());
        ida.adaugaagent(gu);
        gu.viata.setText("" + viata);
        gu.putere.setText("" + putere);
        gu.viteza.setText("" + timpviteza);
        label.setBounds(xactual, yactual, 150, 90);
        scrie("Aparator " + this.getName() + " creat viteza->" + pasviteza + "/" + timpviteza + " pozitia-> x" + xactual + " y" + yactual);
    }
    public synchronized void notificare() {
        if (vip.viata > 0 && !vip.secured) {
            try {
                synchronized (doneLock) {
                    doneLock.wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void run() {
        while (vip.getinviata()&& !vip.getsecured()&&inviata) {
            //notificare();
            verificaperimetru();
            if (atacatorvizat == null) {
                scrie("Aparator " + this.getName() + " verific perimetru");
                scrie("Aparator " + this.getName() + " interpretez datele");
                //notificare();
                mergilavip();
                escort();               
                scrie("Aparator " + this.getName() + " pozitia-> x" + xactual + " y" + yactual);
            }
            if (atacatorvizat != null) {
                intercepteaza();
            }
        }
        if(aparatori.contains(this))
        aparatori.remove(this);
        if (vip.getViata() <= 0) {
            scrie("Aparator " + this.getName() + " vip asasinat... Ma opresc");
        }
        if (vip.secured) {
            scrie("Aparator " + this.getName() + " vip securizat... Ma opresc");
        }
    }

    

    

    

    

    

    

    

    

    

    
    
    

    

    

    
    
    
    //----------------------------------------metode pentru interactiune cu alti agenti
    public void intercepteaza(){
        if(inviata)
        gu.stare.setText("INTERCEPTEZ");
        if(vizibilitate<80)
            inPicioare();
        while (inviata&&atacatorvizat!=null&&atacatorvizat.isAlive()&&atacatorvizat.getInviata() && !esteatacabil() && vip.getinviata()&&!vip.getsecured()) {
            gu.xy.setText("x:" + xactual + " y:" + yactual);
            if (xactual > atacatorvizat.getXactual()) {
                xactual--;
            }
            if (xactual < atacatorvizat.getXactual()) {
                xactual++;
            }
            if (yactual > atacatorvizat.getYactual()) {
                yactual--;
            }
            if (yactual < atacatorvizat.getYactual()) {
                yactual++;
            }
            miscaicoana();
            scrie(this.getName() + " pozitia-> x" + xactual + " y" + yactual);
            try {
                scrie(this.getName() + " atacator vizat " + atacatorvizat.getName() + " pozitia-> x" + atacatorvizat.getXactual() + " y" + atacatorvizat.getYactual());
            } catch (Exception e) {
               setAtacatorvizat(null);
                verificaperimetru();
                mergilavip(); 
            }
            asteapta(timpviteza);
        }   
        try {
            if (atacatorvizat.getInviata() && esteatacabil()) {
                atacaatacator();
            }
            if (!atacatorvizat.getInviata()) {
                setAtacatorvizat(null);
                verificaperimetru();
                mergilavip();
            }
        } catch (Exception e) {
            setAtacatorvizat(null);
                verificaperimetru();
                mergilavip();
        }
    }
    private void mergilavip() {
        if(inviata)
        gu.stare.setText("MERG LA VIP");
        if(vizibilitate<80)
            inPicioare();
        while (atacatorvizat != null && (vip.x != xactual || vip.y != yactual) && vip.viata > 0 && inviata) {
            gu.xy.setText("x:" + xactual + " y:" + yactual);
            if (xactual > vip.x) {
                xactual--;
            }
            if (xactual < vip.x) {
                xactual++;
            }
            if (yactual > vip.y) {
                yactual--;
            }
            if (yactual < vip.y) {
                yactual++;
            }
            miscaicoana();
            asteapta(timpviteza);
            scrie("Aparator " + this.getName() + "ma intorc la vip pozitia-> x" + xactual + " y" + yactual);
            atacatorvizat = null;
            verificaperimetru();
            

        }
    }
    private void escort() {
        if(inviata)
        gu.stare.setText("ESCORTEZ");
        if(vizibilitate<80)
            inPicioare();
        if (xactual > vip.getX()) {
                xactual--;
                xactual--;
                xactual--;
            }
            if (xactual < vip.getX()) {
                xactual++;
                xactual++;
                xactual++;
            }
            if (yactual > vip.getY()) {
                yactual--;
                yactual--;
                yactual--;
            }
            if (yactual < vip.getY()) {
                yactual++;
                yactual++;
                yactual++;
            }
        gu.xy.setText("x:" + xactual + " y:" + yactual);
        miscaicoana();
        asteapta(timpviteza);
    }
    private void help() {
        if ( inviata&&aparatori.size()-1>0&&atacatorvizat != null && atacatorvizat.getInviata()) {
            atacatorvizat.setVizat(false);
            scrie("HELP!");
            spune(this.getName()+" HELP!");
            gu.stare.setText("HELP!");
            for (int i = 0; i < aparatori.size(); i++) {
                if (aparatori.elementAt(i).getAtacatorvizat() == null&&atacatorvizat.inviata) {
                    aparatori.elementAt(i).setAtacatorvizat(atacatorvizat);
                    spune(aparatori.elementAt(i).getName()+" Ajuta pe:"+this.getName());
                    atacatorvizat.setVizat(true);
                    break;
                }
            }
        }
    }
    private void atacaatacator() {
        while (atacatorvizat != null &&inviata&& atacatorvizat.getInviata() && vip.getinviata() && !vip.getsecured()&&esteatacabil()) {
            if (r.nextInt(100) < 50) {
                if (r.nextInt(100) < 2&&atacatorvizat.getInviata()) {
                    atacatorvizat.hit(atacatorvizat.getViata(), this);
                    scrie("Lovesc atacator:" + atacatorvizat.getName() + " HEADSHOT!");
                    atacatorvizat=null;
                }
                if (atacatorvizat != null && atacatorvizat.getInviata()) {
                    scrie("Lovesc atacator:" + atacatorvizat.getName() + " cu puterea:" + putere);
                    atacatorvizat.hit(putere, this);
                    gu.stare.setText("LOVESC");
                }
            } else {
                scrie("Am ratat:" + atacatorvizat.getName());
            }
            asteapta(1000/cadenta);
        }
        if (!atacatorvizat.getInviata()) {
            atacatorvizat = null;
        }
        if(inviata&&atacatorvizat.getInviata()&&!esteatacabil())
            intercepteaza();
        inPicioare();
        mergilavip();
    }
    public void verificaperimetru() {
        gu.stare.setText("VERIFIC PER");
        ordoneazadupapericol();
        if (inviata&&atacatori.size() > 0 && vip.getinviata()) {
            for (int i = 0; i < atacatori.size()&&inviata; i++) {
                if (inviata&&!atacatori.elementAt(i).getVizat() &&atacatori.elementAt(i).getInviata()) {
                    if (distantadintre(atacatori.elementAt(i), vip) < 250) {
                        scrie(atacatori.elementAt(i).getName() + " se afla in perimetru");
                        Aparator aptmp = null;
                        for (int x = 0; x < aparatori.size()&&inviata; x++) {
                            if (inviata&&aparatori.elementAt(x).getAtacatorvizat() == null) {
                                if (aptmp != null) {
                                    if (distantadintre(atacatori.elementAt(i), aparatori.elementAt(x)) <= distantadintre(atacatori.elementAt(i), aptmp)) {
                                        aptmp = aparatori.elementAt(x);
                                    }
                                } else {
                                    aptmp = aparatori.elementAt(x);
                                }
                            }
                        }
                        try {
                            if (inviata && aptmp.isAlive() && this.getName().equals(aptmp.getName())) {
                                
                                this.atacatorvizat = atacatori.elementAt(i);
                                atacatorvizat.setVizat(true);
                                if (!atacatorvizat.getAparatori().contains(this)) {
                                    atacatorvizat.getAparatori().add(this);
                                }
                                
                                scrie("Aparator " + this.getName() + " interceptez-> " + atacatorvizat.getName());
                                
                                if (aparatori.size() > 1 && atacatorvizat != null && putere * cadenta <= (atacatorvizat.getPutere() * atacatorvizat.getCadenta()) - ((atacatorvizat.getPutere() * atacatorvizat.getCadenta()) / 3) || this.viata <= 5000) {
                                    help();
                                }
                                i = atacatori.size();
                            }
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }
    //--------------------------------------------metode pentru actiuni internet
    void hit(Atacator at) {
        if(inviata){
        setViata(viata - at.getPutere()); 
        
        if(vizibilitate>30)
        inGenunchi();
        setLovitor(at);
        if (atacatorvizat == null || !atacatorvizat.getInviata()) {
            setAtacatorvizat(at);
        }
        }
        else
            run();
        
    }
    public void mort() {
        gu.stare.setText("ELIMINAT!");
        scrie("MORT!");
        label.setForeground(Color.DARK_GRAY);
        label.setVisible(false);
        setInviata(false);
        aparatori.remove(this);
    }
    private void peBurta() {
        vizibilitate=20;
        scrie("Sunt pe burta");
    }
    private void inGenunchi() {
        vizibilitate = 30;
        scrie("Sunt in genunchi");
    }
    private void inPicioare() {
        vizibilitate = 80;
        scrie("Sunt in picioare");
    }
    //-------------------------------------------get si set
    public void setViata(int viata) {
        this.viata = viata;
        gu.viata.setText("" + (viata));
        if(!(viata>0)){
        mort();
        if(viata<5000){
            help();
        peBurta();
        }
        }
    }
    public Atacator getAtacatorvizat() {
        return atacatorvizat;
    }
    public boolean getInviata(){
        return inviata;
    }
    public Atacator getLovitor() {
        return lovitor;
    }
    public int getPasviteza() {
        return pasviteza;
    }
    public int getYactual() {
        return yactual;
    }
    public void setAtacatorvizat(Atacator atacatorvizat) {
        this.atacatorvizat = atacatorvizat;
    }
    public int getXactual() {
        return xactual;
    }
    public int getVizibilitate() {
        return vizibilitate;
    }
    public int getPutere() {
        return putere;
    }
    public int getTimpviteza() {
        return timpviteza;
    }
    public int getViata() {
        return viata;
    }
    public void setInviata(boolean inviata) {
        this.inviata = inviata;
    }
    public void setPasviteza(int pasviteza) {
        this.pasviteza = pasviteza;
    }
    public void setTimpviteza(int timpviteza) {
        this.timpviteza = timpviteza;
    }
    public void setLovitor(Atacator lovitor) {
        this.lovitor = lovitor;
    }
    public void setXactual(int xactual) {
        this.xactual = xactual;
    }
    public void setYactual(int yactual) {
        this.yactual = yactual;
    }
    
    //------------------metode utils
    private void spune(String mesaj){
        System.out.println(mesaj);
    }
    private boolean esteatacabil() {
        if (inviata&&distantadintre(atacatorvizat, this) <= raza) {
            return true;
        } else {
            return false;
        }
    }
    public static double distantadintre(Atacator at, Aparator ap) {
        double distanta = 0;
        distanta = Math.sqrt(Math.pow(ap.xactual - at.getXactual(), 2) + Math.pow(ap.yactual - at.getYactual(), 2));
        return distanta;
    }
    public static double distantadintre(Atacator at, AgentVIP vi) {
        double distanta = 0;
        if(vi.getX() - at.getXactual()==0&&vi.getY() - at.getYactual()==0)
            distanta=0;
        else
        distanta = Math.sqrt(Math.pow(vi.getX() - at.getXactual(), 2) + Math.pow(vi.getY() - at.getYactual(), 2));
        return distanta;
    }
    public static double distantadintre(Aparator ap, AgentVIP vi) {
        double distanta = 0;
        distanta = Math.sqrt(Math.pow(vi.getX() - ap.getXactual(), 2) + Math.pow(vi.getY() - ap.getYactual(), 2));
        return distanta;
    }
    private void scrie(String mesaj) {
        gu.addtext(mesaj);
    }
    private void asteapta(int timp) {
        try {
            Thread.sleep(10000/timp);
        } catch (InterruptedException ex) {
            Logger.getLogger(Atacator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void ordoneazadupapericol() {
        Atacator tmp = null;
        boolean doMore = true;
        while (doMore&&atacatori.size()>=1) {
            doMore = false;
            for (int i = 0; i < atacatori.size() - 1; i++) {
                if (distantadintre(atacatori.elementAt(i), vip)
                        > distantadintre(atacatori.elementAt(i + 1), vip)) {
                    tmp = atacatori.elementAt(i);
                    atacatori.setElementAt(atacatori.elementAt(i + 1), i);
                    atacatori.setElementAt(tmp, i + 1);
                    doMore = true;
                }
            }
        }
    }
    private void miscaicoana() {
        label.setBounds(getXactual(), getYactual(), label.getWidth(), label.getHeight());
    }
}
