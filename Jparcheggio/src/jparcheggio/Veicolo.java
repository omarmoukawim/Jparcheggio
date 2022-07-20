/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jparcheggio;

import java.io.Serializable;

/**
 *Classe astratta comprendente tutti i veicoli che possono accedere al parcheggio e 
 * tutti i loro dati.
 * @author loris e omar
 */


public abstract class Veicolo implements Serializable{
	protected Posto posto;
	protected String targa;
        protected dimVeicolo dimensione;
       
        
        
        
        public String getTarga() {
            return targa;
        }
       
        
        public void setTarga(String s) {
            targa = s;
        }

	
	
	public dimVeicolo getDimVeicolo() {
		return dimensione;
	}

	/**
         * Parcheggia il veicolo nel posto p dato come parametro.
         * @param p Posto
         */
	public void parcheggia(Posto p) {
		posto=p;
	}
	
	/**
         * Toglie il veicolo dal posto che occupa.
         */
	public void azzeraPosto() {
            
            posto = null;
	}
        
	public Posto getPosto() {
            return posto;
        }
        
        public abstract boolean vediSeCiEntra(Posto p);
        @Override
        public abstract String toString();
}