package com.github.goalman96.adventura.logika;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Tibor Vondrášek
 *@version    29.12.2017
 *  
 */
public class PrikazNapoveda implements IPrikaz {
    
    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;
    private boolean aktivni = true;
    
     /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů,
    *                       které je možné ve hře použít,
    *                       aby je nápověda mohla zobrazit uživateli. 
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     *  vcelku primitivní zpráva a seznam dostupných příkazů.
     *  
     *  @return napoveda ke hre
     */
    /*public String provedPrikaz(String... parametry) {
        return "Tvým úkolem je koupit si v Tescu suroviny na palačinky\n"
        + "a doma v kuchyni si uvařit palačinky, na které potřebuješ\n"
        + "vejce, mléko, hladkou mouku a sůl. Nezapomeň v Tescu zaplatit.\n"
        + "Můžeš zadat tyto příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu()+".\n";
    }*/
    
    public String provedPrikaz(String... parametry) {
    	return "Tvým úkolem je koupit si v Tescu suroviny na palačinky\n"
    	        + "a doma v kuchyni si uvařit palačinky, na které potřebuuješ.\n"
    	        + "vejce, mléko, hladkou mouku a sůl. Nezapomeň v Tescu zaplatit.\n"
    	        + "Můžeš zadat tyto příkazy:\n"
    	        + platnePrikazy.vratNazvyPrikazu()+".\n";
    }
    
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
     }
    
    /**
     *  Metoda vrací platnost prikazu
     *  
     *  @ return platnost prikazu
     */
     public boolean jeAktivni() {
        return aktivni;
    }
    
     /**
     *  Nastaví platnost příkazu, metodu využívá třída SeznamPrikazu,
     *  
     *  @param  aktivni  hodnota false= příkaz je neplatný, true = příkaz je platný
     */
    public void setAktivni(boolean aktivni) {
        this.aktivni = aktivni;
    }

}
