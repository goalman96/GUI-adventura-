package com.github.goalman96.adventura.logika;

/**
 *  Třída PrikazKonec implementuje pro hru příkaz konec.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Tibor Vondrášek
 *@version    29.12.2017
 *  
 */

public class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "konec";
    private Hra hra;
    private boolean aktivni = true;

    /**
     *  Konstruktor třídy
     *  
     *  @param hra odkaz na hru, která má být příkazem konec ukončena
     */    
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     * 
     * @return zpráva, kterou vypíše hra hráči
     */

    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Ukončit co? Nechápu, proč jste zadal druhé slovo.";
        }
        else {
            hra.setKonecHry(true);
            return "hra ukončena příkazem konec";
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
