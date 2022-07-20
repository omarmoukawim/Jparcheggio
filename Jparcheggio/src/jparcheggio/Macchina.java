/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jparcheggio;



/**
 * Veicolo di dimensione compatta.
 * @author loris e omar
 */
public class Macchina extends Veicolo  {
    public Macchina() {
	
	super.dimensione = dimVeicolo.compatta;
    }
    public Macchina(String s) {
            super.dimensione = dimVeicolo.compatta;
            super.targa =s;
        }
         /**
         * Controlla se la dimensione del posto è adatta.
         * @param p Posto
         * @return True se ci entra, False altrimenti.
         */
	public boolean vediSeCiEntra(Posto p) {
        return p.getDimPosto() == dimVeicolo.compatta;
    }
	
    @Override
    public String toString() {
        return "Dati veicolo: Macchina con targa " + targa;
    }
}