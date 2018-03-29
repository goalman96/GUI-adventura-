package com.github.goalman96.adventura.logika;

import java.util.Observable;
/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *  Do prostorů vytváří také předměty.
 *  Vytvoří batoh.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Tibor Vondrasek
 *@version    29.12.2017
 */
public class HerniPlan extends Observable {
    
    private Prostor aktualniProstor;
    private Batoh batoh;
    private boolean zaplaceno;
    private Prostor kuchyne;
    private Prostor loznice;
    private Prostor obyvak;
    private Prostor garaz;
    private Prostor parkoviste;
    private Prostor vchod;
    private Prostor pokladny;
    private Prostor akcniZbozi;
    private Prostor ovoceZelenina;
    private Prostor lusteniny;
    private Prostor koreni;
    private Prostor piti;
    private Prostor chlazeneZbozi;
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Vytvaří předměty pro prostory.
     *  Vytváří batoh.
     *  Jako výchozí aktuální prostor nastaví kuchyň.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Vytvaří předměty pro prostory.
     *  Vytváří batoh.
     *  Jako výchozí aktuální prostor nastaví kuchyň.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        kuchyne = new Prostor("kuchyne","kuchyně, kde si uvaříš večeři", 97.0, 87.0);
        loznice = new Prostor("loznice", "ložnice, kde asi spíš", 97.0, 46.0);
        obyvak = new Prostor("obyvak","obývák, máš tu bordel", 167.0, 45.0);
        garaz = new Prostor("garaz","garáž, kde máš auto", 123.0, 101.0);
        Prostor auto = new Prostor("auto","auto, kterým můžeš jet do Tesca", 150.0, 107.0);
        parkoviste = new Prostor("parkoviste","parkoviště u Tesca", 160.0, 341.0);
        vchod = new Prostor("vchod","vchod do Tesca", 195.0, 296.0);
        pokladny = new Prostor("pokladny","pokladny, kde musíš zaplatit nákup", 118.0, 295.0);
        akcniZbozi = new Prostor("akcniZbozi","akční zboží,kde je zboží v akci", 157.0, 248.0);
        ovoceZelenina = new Prostor("ovoceZelenina","ovoce a zelenina, je to zdravé, takže to nechceš", 197.0, 225.0);
        lusteniny = new Prostor("lusteniny","luštěniny... někdo to jí", 150.0, 225.0);
        koreni = new Prostor("koreni","koření, kterým si můžeš okořenit jídlo", 107.0, 225.0);
        piti = new Prostor("piti","pití, kde jsou minerálky, limonády, piva, vodky a tak", 70.0, 225.0);
        chlazeneZbozi= new Prostor("chlazeneZbozi","chlazené zboží, které je stejné jako ostatní, akorát je chlazené...", 167.0, 177.0);
        
        kuchyne.setVychod(loznice, garaz);
        loznice.setVychod(kuchyne, obyvak);
        obyvak.setVychod(loznice, garaz);
        garaz.setVychod(kuchyne, auto, obyvak);
        auto.setVychod(parkoviste, garaz);
        parkoviste.setVychod(vchod, auto);
        vchod.setVychod(parkoviste, akcniZbozi);
        akcniZbozi.setVychod(vchod, pokladny, ovoceZelenina, lusteniny, koreni, piti);
        ovoceZelenina.setVychod(akcniZbozi, chlazeneZbozi);
        lusteniny.setVychod(akcniZbozi, chlazeneZbozi);
        koreni.setVychod(akcniZbozi, chlazeneZbozi);
        piti.setVychod(akcniZbozi, chlazeneZbozi);
        chlazeneZbozi.setVychod(ovoceZelenina, lusteniny, koreni, piti);
        pokladny.setVychod(akcniZbozi, parkoviste);
        zalozPredmety();
        
        Batoh vytvorenyBatoh = new Batoh(5);
        
        batoh = vytvorenyBatoh;
        aktualniProstor = kuchyne;  // hra začíná v kuchyni  
        zaplaceno = false;
    }
    
    /**
     *  Vytváří jednotlivé předměty pro prostory.
     *  Přiřazuje předměty prostorům.
     */
    private void zalozPredmety() {
        Predmet hrnec = new Predmet("hrnec",false);
        Predmet sporak = new Predmet("sporak",false);
        Predmet klice = new Predmet("klice",true);
        Predmet penezenka = new Predmet("penezenka",true);
        Predmet postel = new Predmet("postel",false);
        Predmet gauc = new Predmet("gauc",false);
        Predmet televize = new Predmet("televize",false);
        Predmet pneumatika = new Predmet("pneumatika",false);
        Predmet autoHonda= new Predmet("autoHonda",false);
        Predmet autoCitroen= new Predmet("autoCitroen",false);
        Predmet kosik= new Predmet("kosik",false);
        Predmet letaky= new Predmet("letaky",true);
        Predmet kalendar = new Predmet("kalendar",true);
        Predmet cokolada = new Predmet("cokolada",true);
        Predmet kava = new Predmet("kava",true);
        Predmet jablko = new Predmet("jablko",true);
        Predmet mrkev = new Predmet("mrkev",true);
        Predmet celer = new Predmet("celer",true);
        Predmet cocka = new Predmet("cocka",true);
        Predmet hladkaMouka= new Predmet("hladkaMouka",true);
        Predmet hrubaMouka= new Predmet("hrubaMouka",true);
        Predmet ryze= new Predmet("ryze",true);
        Predmet chilli= new Predmet("chilli",true);
        Predmet sul = new Predmet("sul",true);
        Predmet koreniBrambory= new Predmet("koreniAmerickeBrambory",true);
        Predmet matonni= new Predmet("Mattonni",true);
        Predmet cocacola= new Predmet("Cocacola",true);
        Predmet kureciStehna = new Predmet("kureciStehna",true);
        Predmet vejce = new Predmet("vejce",true);
        Predmet hranolky = new Predmet("hranolky",true);
        Predmet mleko = new Predmet("mleko",true);
        Predmet maslo = new Predmet("maslo",true);
        Predmet pokladna = new Predmet("pokladna",false);
        Predmet cigarety = new Predmet("cigarety",true);
        Predmet prodavacka = new Predmet("prodavacka",false);
        
        kuchyne.setPredmet(hrnec,sporak,klice,penezenka);
        loznice.setPredmet(postel);
        obyvak.setPredmet(gauc,televize);
        garaz.setPredmet(pneumatika);
        parkoviste.setPredmet(autoHonda,autoCitroen);
        vchod.setPredmet(kosik,letaky);
        akcniZbozi.setPredmet(kalendar,cokolada,kava);
        ovoceZelenina.setPredmet(jablko,mrkev,celer);
        lusteniny.setPredmet(cocka,hladkaMouka,hrubaMouka,ryze);
        koreni.setPredmet(chilli,sul,koreniBrambory);
        piti.setPredmet(matonni,cocacola);
        chlazeneZbozi.setPredmet(kureciStehna,vejce,hranolky,mleko,maslo);
        pokladny.setPredmet(pokladna,cigarety,prodavacka);
        
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       setChanged();
       notifyObservers();
    }
    
    /**
     *  Metoda vrací batoh.
     *
     *@return     batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }
    
    /**
     *  Metoda nastaví nový plán,
     *  nastaví všechno jako na začátku,
     *  pro příkaz restart
     */
    public void novyPlan() {
        zalozProstoryHry();
    }
    
    /**
     *  Metoda vrací hodnotu proměnné zaplaceno.
     *
     *@return    je/neni zaplaceno
     */
    public boolean getZaplaceno() {
        return zaplaceno;
    }
    
    /**
     *  Metoda změní hodnotu proměnné zaplaceno, zaplaceno nebo nezaplaceno.
     *
     *@param  je/neni zaplaceno
     */
    public void setZaplaceno(boolean zaplaceno) {
        this.zaplaceno = zaplaceno;
    }
    

}
