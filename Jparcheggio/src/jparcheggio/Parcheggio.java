/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jparcheggio;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import static jparcheggio.dimVeicolo.compatta;
import static jparcheggio.dimVeicolo.motocicletta;

/**
 *Insieme di livelli.
 * @author loris e omar
 */
public class Parcheggio implements Serializable{
	private Livello[] livelli;
	private int numLivelli = 5;
        private TreeMap<String,Veicolo> coppietv = new TreeMap<String,Veicolo>();
	
        
	public Parcheggio() {
		livelli = new Livello[numLivelli];
		for (int i = 0; i < numLivelli; i++) {
			livelli[i] = new Livello(i, 30);
		}
	}
       
	
	/**
         * Richiama tutti i metodi necessari per parcheggiare il veicolo dato come parmetro.
         * @param v Veicolo
         * @return True se riesce a parcheggiare, false altrimenti.
         */
       public boolean parcheggia(Veicolo v) {
            //se liv.parcheggia == true esce
            for (Livello liv : livelli) {
                if (liv.parcheggia(v)) {
                    coppietv.put(v.getTarga(), v);
                    return true;
                }
            }
            return false;
	}
     /**
     *Simula l'uscita del veicolo dal parcheggio.
     * @param v Veicolo
     * @param tempo Tempo
     * @param nome  Nome
     * @throws java.io.FileNotFoundException Errore
     */
       public void rimuoviVeicolo(Veicolo v , double tempo, String nome) throws FileNotFoundException  {
           Posto p = v.getPosto();
           this.scontrino(v, tempo, nome);
           v.azzeraPosto();
           p.togliVeicolo();
           coppietv.remove(v.getTarga(), v);
           
        } 
       
       /**
        * Crea un file in cui ci sono tutti i dati necessari alla creazione di una rivevuta.
        * @param v Veicolo
        * @param tempo Tempo di permanenza
        * @throws FileNotFoundException dà errore se non trova il file
        */
       private void scontrino(Veicolo v, double tempo, String nome) throws FileNotFoundException {
           
        double totale;
        if (v.getDimVeicolo()== motocicletta){
            double prezzo=1;
            totale=prezzo*tempo;
        }
        
        else if (v.getDimVeicolo()== compatta) {
            double prezzo=2;
            totale=prezzo*tempo;
        }
        else {
            double prezzo =3;
            totale = prezzo*tempo;
        }
      
     this.emetti(nome +" "+v.getTarga(),nome ,v , totale);
    }
       
      private void emetti(String nomefile,String nome ,Veicolo v,double totale) throws FileNotFoundException {   
       
        try (PrintWriter out = new PrintWriter(nomefile + ".txt" )) {
            out.println(v);
            out.println(v.getPosto());
            out.println("Il prezzo da pagare è: "+ totale +"€");
            out.println("proprietario veicolo: "+nome);
            out.close();
            }
       }
           
       
	/**
         * Controlla quanti posti liberi sono rimasti all'interno del parcheggio e di che dimensione sono.
         * @return il numero di posti liberi per ogni dimensione
         */
	public String getPostiLiberi() {
            int postiG=0;
            int postiC=0;
            int postiM=0;
            for (Livello piano : livelli) {
             Posto[] a = piano.getPosti();
             for(Posto p:a) {
                 if (p.getDimPosto() == dimVeicolo.grande & p.verificaOccupato()){
                      postiG ++;
                 }
                 if (p.getDimPosto() == dimVeicolo.compatta & p.verificaOccupato()){
                     postiC++;
                 }
                 if (p.getDimPosto()== dimVeicolo.motocicletta & p.verificaOccupato()){
                     postiM++;
                 }
             }
                 
            }
            return "Ci sono: " + postiG + " posti grandi, " + postiC + " posti macchina e " + postiM + " posti moto.";
        }
        
        public String getDatiVeicolo(Veicolo v) {
            return v.toString() + "; " + v.getPosto();
        }
        
        //returna coppia targa veicolo
        public TreeMap<String,Veicolo> getCoppietv() {
            return coppietv;
        }
        

         
         public boolean cercaTarga(String s) {
                return coppietv.containsKey(s);
                
        }
        public void statoParc(TreeMap<String,Veicolo> coppie) throws FileNotFoundException{
            try (PrintWriter out = new PrintWriter("statoparcheggio.txt" )) {
                for (String key: coppie.keySet()){
                    out.println(coppie.get(key)+"; " +coppie.get(key).getPosto());
            }
                out.close();
            }
        }
}   
                    
                
            
        
        

