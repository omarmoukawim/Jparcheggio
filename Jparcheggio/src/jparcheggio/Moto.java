/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jparcheggio;

/**
 *Veicolo di dimensione motocicletta.
 * @author loris e omar
 */
public class Moto extends Veicolo {
    
	public Moto() {
		
		super.dimensione = dimVeicolo.motocicletta;
	}
        public Moto(String s) {
		
		super.dimensione = dimVeicolo.motocicletta;
                super.targa =s;
	}
	/**
         * Controlla se la dimensione del posto è adatta.
         * @param p Posto
         * @return True se ci entra, False altrimenti.
         */
	public boolean vediSeCiEntra(Posto p) {
		return p.getDimPosto() == dimVeicolo.motocicletta;
	}
	
        @Override
        public String toString() {
                return "Dati veicolo: Moto con targa " + targa;
        }	
}
