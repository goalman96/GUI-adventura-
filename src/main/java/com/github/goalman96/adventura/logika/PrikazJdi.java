package com.github.goalman96.adventura.logika;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček, Tibor Vondrášek
 *@version    pro školní rok 2016/2017
 */
public class PrikazJdi implements IPrikaz {
    private static final String NAZEV = "jdi";
    private HerniPlan plan;
    private boolean aktivni = true;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazJdi(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *  Pokud je aktuální prostor auto, vypíše se chybové hlášení.
     *  Pokud je aktualní prostor "pokladny", sousední prostor je zadán "parkoviště"
     *  a u pokladen nebylo zaplaceno, tak ukončí hru.
     *
     *@param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu\n";
        }

        String smer = parametry[0];
        Batoh batoh = plan.getBatoh();
        Prostor aktualniProstor = plan.getAktualniProstor();
        boolean zaplaceno = plan.getZaplaceno();
        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = aktualniProstor.vratSousedniProstor(smer);

        if (aktualniProstor.getNazev().equals("auto")) {
            return "Autem se nechodí, autem se jezdí...";
        }
        else if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!\n";
        }
        else {
            if ( sousedniProstor.getNazev().equals("parkoviste") && aktualniProstor.getNazev().equals("pokladny") && !zaplaceno ) {
                return "Odešel jsi bez placení, chytila tě ochranka, prohrál jsi.\n"
                    +"Napiš 'restart' pro novou hru nebo 'konec' pro ukončení hry\n";
            }
            else {
                if ( aktualniProstor.getNazev().equals("pokladny")) {
                    zaplaceno = false;
                    plan.setZaplaceno(zaplaceno);
                }
                plan.setAktualniProstor(sousedniProstor);
                return sousedniProstor.dlouhyPopis() +"\n"
                        + batoh.getObsah()+".\n";
            }
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
