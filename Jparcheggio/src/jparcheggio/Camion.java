/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jparcheggio;

/**
 * Veicolo di dimensione grande.
 * @author loris e omar
 */
public class Camion extends Veicolo{
    
	public Camion() {
		super.dimensione = dimVeicolo.grande;
	}
        public Camion(String s) {
		super.dimensione = dimVeicolo.grande;
                super.targa =s;
                
	}
	/**
         * Controlla se la dimensione del posto è adatta.
         * @param p Posto
         * @return True se ci entra, False altrimenti.
         */
	public boolean vediSeCiEntra(Posto p) {
		return p.getDimPosto()== dimVeicolo.grande;
	}
        
        @Override
        public String toString() {
            return "Dati veicolo: Camion con targa " + targa;
        }
	
}