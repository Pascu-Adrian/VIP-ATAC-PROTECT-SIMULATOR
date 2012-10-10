/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vip;

/**
 *
 * @author MindSlave
 */
public class VerificaPerimetru extends Thread{
    Atacator atacator;
    Aparator aparator;
    public VerificaPerimetru() {
        
    }

    public VerificaPerimetru(Aparator aparator) {
        this.aparator = aparator;
    }
    public VerificaPerimetru(Atacator atacator) {
        this.atacator = atacator;
    }

    @Override
    public void run() {
        if(atacator!=null){
            
        }
        if(aparator!=null){
            
        }
    }
    
}
