/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.goalman96.adventura.logika;


/**
 *  Třída PrikazVyhod implementuje pro hru příkaz vyhoď.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Tibor Vondrášek
 *@version    29.12.2017
 */
public class PrikazVyhod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vyhod";
    private HerniPlan plan;
    private boolean aktivni = true;
    //== Konstruktory a tovární metody =============================================

    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazVyhod(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "vyhoď". Zkouší vyhodit zadaný předmět z batohu,
     *  chybové hlášení se vypíše, pokud není zadán předmět na vyhození,
     *  nebo zadaný předmět není v batohu.
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mám vyhodit? Musíš zadat název věci.\n";
        }

        String nazevVyhazPredmet= parametry[0];

        Batoh batoh = plan.getBatoh();
        Prostor aktualniProstor = plan.getAktualniProstor();
        Predmet vyhazovanyPredmet  = batoh.najdiPredmet(nazevVyhazPredmet);
        
        if (vyhazovanyPredmet == null) {
            return "To nemáš v batohu.\n";
        }
        else {
            batoh.vyhodPredmet(vyhazovanyPredmet);
            aktualniProstor.pridejPredmet(vyhazovanyPredmet);
            return "Vyhodil jsi "+ nazevVyhazPredmet + ".\n"
                       + aktualniProstor.dlouhyPopis()+".\n"
                       + batoh.getObsah()+".\n";
        }
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
    //== Soukromé metody (instancí i třídy) ========================================
}
