package com.github.goalman96.adventura.logika;

/**
 *  Třída PrikazRestart implementuje pro hru příkaz restart.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Tibor Vondrasek
 *@version    29.12.2017
 */
public class PrikazRestart implements IPrikaz {
    private static final String NAZEV = "restart";
    private HerniPlan plan;
    private Hra hra;
    private SeznamPrikazu prikazy;
    private boolean aktivni = true;
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazRestart(Hra hra, HerniPlan plan, SeznamPrikazu prikazy) {
        this.plan = plan;
        this.hra = hra;
        this.prikazy = prikazy;
    }

    /**
     *  Provádí příkaz "restart". Restartuje hru tj. založí nový prostor hry
     *  a vrátí uvítání.
     *  
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String provedPrikaz(String... parametry) {
        IPrikaz jdi = prikazy.vratPrikaz("jdi");
        IPrikaz seber = prikazy.vratPrikaz("seber");
        IPrikaz vyhod = prikazy.vratPrikaz("vyhod");
        IPrikaz uvar = prikazy.vratPrikaz("uvar");
        IPrikaz zaplat = prikazy.vratPrikaz("zaplat");
        IPrikaz napoveda = prikazy.vratPrikaz("napoveda");
        IPrikaz jed = prikazy.vratPrikaz("jed");
        
        jdi.setAktivni(true);
        seber.setAktivni(true);
        vyhod.setAktivni(true);
        uvar.setAktivni(true);
        zaplat.setAktivni(true);
        napoveda.setAktivni(true);
        jed.setAktivni(true);
        
        plan.novyPlan();
        return hra.vratUvitani();
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
