/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jparcheggio;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *Main del programma.
 * @author loris e omar
 * 
 */

public class Jparcheggio {
    
    
     // @param args the command line arguments
     //@throws java.io.FileNotFoundException
     
   
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
  
Parcheggio parc = new Parcheggio();
try{  
  ObjectInputStream inp =new ObjectInputStream(new FileInputStream("parcheggio.dat"));  
  parc =(Parcheggio)inp.readObject();  
  inp.close(); }
catch(IOException | ClassNotFoundException e){
}
   
    

       
        
        boolean cond = true;
        while (cond) {
        Scanner in = new Scanner(System.in);
        System.out.println("Benvenuto, voui parcheggiare (p), rimuovere un veicolo (r),");
        System.out.println("cercare una targa (c), sapere i postii disponibili (d) o uscire (q)?");
        String scelta = in.nextLine();
        if (scelta.equals("p")) {
             System.out.println("voui parcheggiare un'automobile (a), un camion (c) o una moto (m) ?");
                String a = in.nextLine();
                System.out.println("inserire targa del veicolo: ");
                String t = in.nextLine();
                if (a.equals("a")) {
                    Macchina v = new Macchina(t);
                    boolean p = parc.parcheggia(v);
                    
                    if (p) {
                        System.out.println("Il veicolo è stato parcheggiato nel posto");
                        System.out.println(v.getPosto());
                                            }
                    else{
                        System.out.println("Spiacenti non c'è posto");
                    }   
                } 
                else if(a.equals("c")) {
                    Camion v = new Camion(t);
                    
                    boolean p = parc.parcheggia(v);
                    if (p) {
                        System.out.println("Il veicolo è stato parcheggiato nel posto ");
                        System.out.println(v.getPosto());
                    }
                    else{
                        System.out.println("Spiacenti non c'è posto");
                    } 
                    }
                else if(a.equals("m")) {
                    Moto v = new Moto(t);
                    
                    boolean p = parc.parcheggia(v);
                    if (p) {
                        System.out.println("Il veicolo è stato parcheggiato nel posto ");
                        System.out.println(v.getPosto());
                        
                    }
                    else{
                        System.out.println("Spiacenti non c'è posto");
                    }   
                    }
           
          
                }
                
            else if(scelta.equals("r")){  
                System.out.println("inserire targa del veicolo da rimuovere");
                String tar = in.nextLine();
                parc.cercaTarga(tar);
                                
                 
                     if (parc.cercaTarga(tar)) {
                     System.out.println("A chi intesterà la ricevuta?");
                     String nome = in.nextLine();
                     System.out.println("Quanto è durata la permanenza?");
                     double tempo = in.nextDouble();
                     parc.rimuoviVeicolo(parc.getCoppietv().get(tar),tempo ,nome);
                     System.out.println("Il veicolo con targa "+ tar + " è stato rimosso");
                     
                    }
                    else {
                         System.out.println("Il veicolo non è stato trovato");
                    }
                 
                }
            
            else if(scelta.equals("c")){
                System.out.println("inserire targa del veicolo da cercare");
                String targa = in.nextLine();
                if (parc.cercaTarga(targa)){
                        System.out.println(parc.getDatiVeicolo(parc.getCoppietv().get(targa)));
                    }
                    else {
                        System.out.println("Il veicolo non è stato trovato");
                    }
                
            }        
                  
            else if(scelta.equals("d")){
                System.out.println(parc.getPostiLiberi());
                
            }  
            else if(scelta.equals("q")){
            cond = false;
            }
        parc.statoParc(parc.getCoppietv());
                
        }
  FileOutputStream fout=new FileOutputStream("parcheggio.dat");  
  ObjectOutputStream out=new ObjectOutputStream(fout);  
  out.writeObject(parc);  
  out.flush();  
  out.close();  
    }
}              
    


