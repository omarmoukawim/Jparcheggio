/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jparcheggio;

import java.io.Serializable;

/**
 *Rappresenta un livello del parcheggio.
 * Si compone di più settori ognuno dei quali ha un certo numero di posti.
 * @author loris e omar
 */
public class Livello implements Serializable  {
	private int piano;
	private Posto[] posti;
	private int postiDisponibili; 
	private int postiPerSettore = 10;
	
	public Livello(int p, int numeroPosti) {
            
		piano = p;
		posti = new Posto[numeroPosti];
                
		int postiGrandi = numeroPosti / 4;
		int postiMoto = numeroPosti / 4;
		int postiCompatti = numeroPosti - postiGrandi - postiMoto;
                
		for (int i = 0; i < numeroPosti; i++) {
                    //prima crea i posti per camion e macchina , i rimanenti sono per moto
                    //(non entra più nelle if!)
			dimVeicolo dv = dimVeicolo.motocicletta;
                        
			if (i < postiGrandi) {
				dv = dimVeicolo.grande;
			} 
                        else if (i < postiGrandi + postiCompatti) {//fino a 23 e i rimanenti 7 sono moto
				dv = dimVeicolo.compatta;
			}
                        
			int settore = i / postiPerSettore;// assume 0 1 2
                        
			posti[i] = new Posto(this, settore, i, dv);//this è il livello
		}
		postiDisponibili = numeroPosti;
	}
	
	public int getPostiDisponibili() {
		return postiDisponibili;
	}
        
        /**
         * Richiama due metodi: uno per trovare il primo posto disponibili
         * (adatto alle dimensioni del veicolo e libero)
         * e il secondo per occupare il posto, se trovato.
         * @param v Veicolo
         * @return True se riesce a parcheggiare, false altrimenti.
         */
	public boolean parcheggia(Veicolo v) {
               
		if (getPostiDisponibili() == 0) {
			return false;
		}
		int numPosto = trovaPostoDisp(v);
                
		if (numPosto < 0) {
			return false;
		}
		return occupaPosto(numPosto, v);
	}
        
      /**
       * Scorre tutti i posti per ogni settore del livello e tenendo conto delle caratteristiche
       * di ognuno di essi, li confronta al veicolo.
       * @param v Veicolo
       * @return Il numero del posto adatto al veicolo dato in parametro.
       */
	private int trovaPostoDisp(Veicolo v) {
            
		int contaSettore = -1; // scorre i vari settori del livello
		
                
		for (int i = 0; i < posti.length; i++) {
                    
			Posto posto= posti[i];
                        
			if (contaSettore != posto.getSettore()) {
				
				contaSettore = posto.getSettore();
			}
                        if (posto.adattoAlPosto(v)) {
				return i;
			} 
		}
		return -1;
	}
	
	/**
         * Occupa un posto parcheggiando un veicolo.
         * @param numPosto numero del posto
         * @param v veicolo
         * @return True se occupa il posto, False altrimenti.
         */
	private boolean occupaPosto(int numPosto, Veicolo v) {
                if (posti[numPosto].parcheggia(v)){
                postiDisponibili -=1;
                return true;
                }
	        else return false;
        }
        
	/**
         * Incrementa posti disponibili quando libero un posto.
         */
	public void postoLiberato() {
		postiDisponibili++;
	}
        public Posto[] getPosti() {
            return posti;
        }
     
        
       @Override
	public String toString() {
		return ": "+piano;
					
	}
	
	
}