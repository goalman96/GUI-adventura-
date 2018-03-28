/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.goalman96.adventura.logika;


/*******************************************************************************
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *  Tato třída je součástí jednoduché textové hry.
 *
 * @author    Tibor Vondrasek
 * @version   29.12.2017
 */
public class PrikazSeber implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "seber";
    private HerniPlan plan;
    private boolean aktivni = true;
    //== Konstruktory a tovární metody =============================================

    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazSeber(HerniPlan plan)
    {
        this.plan = plan;
    }

    /**
     *  Provádí příkaz "seber". Sebere věc z prostoru a vloží ji do batohu.
     *  Vypíše chybovou zprávu, pokud chybí druhý parametr tj. jakou věc má sebrat, 
     *  nebo pokud v prostoru dan věc není, pokud je věc nepřenositelná, nebo je batoh plný.
     *  
     *@param parametry - jako  parametr obsahuje název sbíraného předmětu
     *  
     *@return zpráva, kterou vypíše hra hráči
     */ //== Nesoukromé metody (instancí i třídy) ======================================
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mám sebrat? Musíš zadat název věci";
        }

        String nazevSbiraneVeci = parametry[0];

        Batoh batoh = plan.getBatoh();
        Prostor aktualniProstor = plan.getAktualniProstor();
        Predmet sbiranyPredmet  = aktualniProstor.najdiPredmet(nazevSbiraneVeci);
        
        if (sbiranyPredmet == null) {
            return "To tu není!\n";
        }
        else {
            if(sbiranyPredmet.getPrenositelny()){
               if (batoh.getPocet() < batoh.getVelikost()) {
                   aktualniProstor.odeberPredmet(sbiranyPredmet);
                   aktualniProstor.dlouhyPopis();
                   batoh.vlozPredmet(sbiranyPredmet);
                   return "Sebral si "+ nazevSbiraneVeci + ".\n"
                          + aktualniProstor.dlouhyPopis()+"\n"
                          + batoh.getObsah()+".\n";    
               }
               else {
                   return "Batoh je plný!\n";
               } 
            }
            else{
                return "Jak tohle chceš dát do batohu?\n";
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
    //== Soukromé metody (instancí i třídy) ========================================
}
