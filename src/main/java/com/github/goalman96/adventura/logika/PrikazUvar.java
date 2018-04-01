package com.github.goalman96.adventura.logika;

/**
 *  Třída PrikazUvar implementuje pro hru příkaz uvař.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova, Luboš Pavlíček
 *@version    pro školní rok 2016/2017
 */
public class PrikazUvar implements IPrikaz {
    private static final String NAZEV = "uvar";
    private HerniPlan plan;
    private SeznamPrikazu prikazy;
    private boolean aktivni = true;
    
    /**
    *  Konstruktor třídy
    *  
    *  @param plan herní plán, ve kterém se bude ve hře "chodit" 
    */    
    public PrikazUvar(HerniPlan plan, SeznamPrikazu prikazy) {
        this.plan = plan;
        this.prikazy = prikazy;
    }

    /**
     *  Provádí příkaz "uvař". Zkouší uvařit palačinky, pokud se podaří,
     *  tak se v prostoru objeví předmět "palačinky".
     *  Chybové hlášení se vypíše, pokud nejsou v batohu všechny suroviny,
     *  nebo aktuální prostor není kuchyně.
     *
     *@return zpráva, kterou vypíše hra hráči
     */ 
    public String provedPrikaz(String... parametry) {
        Prostor aktualniProstor = plan.getAktualniProstor();
        Batoh batoh = plan.getBatoh();
        Predmet vejce = batoh.najdiPredmet("vejce");
        Predmet mleko = batoh.najdiPredmet("mleko");
        Predmet hladkaMouka = batoh.najdiPredmet("hladkaMouka");
        Predmet sul = batoh.najdiPredmet("sul");
        IPrikaz jdi = prikazy.vratPrikaz("jdi");
        IPrikaz seber = prikazy.vratPrikaz("seber");
        IPrikaz vyhod = prikazy.vratPrikaz("vyhod");
        IPrikaz uvar = prikazy.vratPrikaz("uvar");
        IPrikaz zaplat = prikazy.vratPrikaz("zaplat");
        IPrikaz napoveda = prikazy.vratPrikaz("napoveda");
        IPrikaz jed = prikazy.vratPrikaz("jed");
        
        if (aktualniProstor.getNazev().equals("kuchyne")) {
            if (vejce == null || mleko == null || hladkaMouka == null || sul == null) {
                jdi.setAktivni(false);
                seber.setAktivni(false);
                vyhod.setAktivni(false);
                uvar.setAktivni(false);
                zaplat.setAktivni(false);
                napoveda.setAktivni(false);
                jed.setAktivni(false);
                return "Nemáš všechny potřebné suroviny, prohrál jsi.\n"
                    +"Napiš 'restart' pro novou hru nebo 'konec' pro ukončení hry\n";
                }
            else {
                Predmet palacinky = new Predmet("palacinky",true,"palacinky.jpg");
                aktualniProstor.pridejPredmet(palacinky);
                batoh.vyhodPredmet(vejce);
                batoh.vyhodPredmet(mleko);
                batoh.vyhodPredmet(hladkaMouka);
                batoh.vyhodPredmet(sul);
            
                jdi.setAktivni(false);
                seber.setAktivni(false);
                vyhod.setAktivni(false);
                uvar.setAktivni(false);
                zaplat.setAktivni(false);
                napoveda.setAktivni(false);
            
                return "Uvařil jsi palačinky! Konečně se můžeš najíst! Tímto jsi vyhrál!" 
                    + aktualniProstor.dlouhyPopis() +".\n"
                    + batoh.getObsah()+".\n"
                    +"Napiš 'restart' pro novou hru nebo 'konec' pro ukončení hry\n";
            }
        }
        else {
            return "Kde tady chceš vařit?\n";
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
