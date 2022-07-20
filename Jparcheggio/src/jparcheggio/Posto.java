/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jparcheggio;

import java.io.Serializable;

/**
 *Posti che compongono un livello.
 * Ogni posto ha ad esso associato un veicolo quando occupato e di conseguenza una dimensione.
 * @author loris e omar
 */
public class Posto implements Serializable {
	private Veicolo veicolo;
	private dimVeicolo dimPosto;
	private int settore;
	private int numPosto;
	private Livello livello;
	
        public Posto() {}
        
	public Posto(Livello l, int sett, int num, dimVeicolo dv) {
            
		this.livello = l;
		this.settore = sett;
		this.numPosto = num;
		this.dimPosto = dv;
	}
	
	public boolean verificaOccupato() {
		return veicolo == null;
	}
	
	/**
         * Veifica che il posto sia della dimensione adatta al veicolo dato in parametro, e
         * se il posto è disponibile.
         * @param v Veicolo
         * @return True se il posto è adatto e libero, False altrimenti.
         */
	public boolean adattoAlPosto(Veicolo v) {
		return verificaOccupato() && v.vediSeCiEntra(this);
	}
	
	/**
         * Assegna il veicolo dato in parametro al posto selezionato.
         * @param v Veicolo
         * @return True se tutte le condizioni sono rispettate, False altrimenti.
         */
	public boolean parcheggia(Veicolo v) {
            
		if (adattoAlPosto(v)) {
                    v.parcheggia(this);
                    veicolo=v;
                    return true;
	            }

               return false;
	}
	
	public int getSettore() {
		return settore;
	}
	
	public int getNumPosto() {
		return numPosto;
	}
	
	public dimVeicolo getDimPosto() {
		return dimPosto;
	}
	
	/**
         * Rimuove il veicolo dal posto.
         */
	public void togliVeicolo() {
		livello.postoLiberato();
		veicolo = null;
                
	}
        public Posto getPos() {
            return this;
        }
        
       
        public String toString() {
            return "Numero posto "+ numPosto + " settore "+ settore + " livello " + livello;
        }
}
    

