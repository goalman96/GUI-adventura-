package com.github.goalman96.adventura.logika;

/**
 *  Třída PrikazZaplat implementuje pro hru příkaz zaplať.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Tibor Vondrasek
 *@version    29.12.2017
 */
public class PrikazZaplat implements IPrikaz {
    private static final String NAZEV = "zaplat";
    private HerniPlan plan;
    private boolean aktivni = true;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazZaplat(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se zaplatit zboží v prostoru pokladna. Pokud aktuální prostor
     *  je pokladna a v batohu je peněženka, je zaplaceno. Chybové hlášení se vypíše pokud
     *  aktuální prostor není pokladna, nebo v batohu není peněženka, nebo už bylo zaplaceno.
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String provedPrikaz(String... parametry) {
        Prostor aktualniProstor = plan.getAktualniProstor();
        Batoh batoh = plan.getBatoh();
        boolean zaplaceno = plan.getZaplaceno();
        
        if (batoh.najdiPredmet("penezenka") == null) {
            return "Nemáš čím zaplatit, zapomněl jsi peněženku.\n";
        }
        else if (zaplaceno) {
            return "Už jsi platil.\n";
        }
        else if (aktualniProstor.getNazev().equals("pokladny")) {
            zaplaceno = true;
            plan.setZaplaceno(zaplaceno);
            return "Zaplatil jsi zboží. \n" 
                    + aktualniProstor.dlouhyPopis() +"\n"
                    + batoh.getObsah()+".\n";
        }
        else {
            return "Komu tady chceš platit?\n";
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

}
