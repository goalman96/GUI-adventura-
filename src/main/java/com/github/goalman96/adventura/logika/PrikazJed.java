package com.github.goalman96.adventura.logika;
/**
 *  Třída PrikazJed implementuje pro hru příkaz jeď.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Tibor Vondrášek
 *@version    29.12.2017
 */
public class PrikazJed implements IPrikaz {
    private static final String NAZEV = "jed";
    private HerniPlan plan;
    private boolean aktivni = true;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJed(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jeď". Zkouší jet do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jet? Musíš zadat místo\n";
        }

        String smer = parametry[0];
        Batoh batoh = plan.getBatoh();
        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);
        
        
        if (sousedniProstor == null) {
            return "Tam se odsud jet nedá!\n";
        }
        else if (plan.getAktualniProstor().getNazev().equals("auto")) {
            plan.setAktualniProstor(sousedniProstor);
            return sousedniProstor.dlouhyPopis() +"\n"
                   + batoh.getObsah()+".\n";
        }
        else {
            return "Nemůžeš jezdit, když nejsi v autě...";
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
