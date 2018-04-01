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
        kuchyne = new Prostor("kuchyne","kuchyně, kde si uvaříš večeři",30,80);
        loznice = new Prostor("loznice", "ložnice, kde asi spíš",30,10);
        obyvak = new Prostor("obyvak","obývák, máš tu bordel",110,10);
        garaz = new Prostor("garaz","garáž, kde máš auto",0,0);
        Prostor auto = new Prostor("auto","auto, kterým můžeš jet do Tesca",0,0);
        parkoviste = new Prostor("parkoviste","parkoviště u Tesca",0,0);
        vchod = new Prostor("vchod","vchod do Tesca",0,0);
        pokladny = new Prostor("pokladny","pokladny, kde musíš zaplatit nákup",0,0);
        akcniZbozi = new Prostor("akcniZbozi","akční zboží,kde je zboží v akci",0,0);
        ovoceZelenina = new Prostor("ovoceZelenina","ovoce a zelenina, je to zdravé, takže to nechceš",0,0);
        lusteniny = new Prostor("lusteniny","luštěniny... někdo to jí",0,0);
        koreni = new Prostor("koreni","koření, kterým si můžeš okořenit jídlo",0,0);
        piti = new Prostor("piti","pití, kde jsou minerálky, limonády, piva, vodky a tak",0,0);
        chlazeneZbozi= new Prostor("chlazeneZbozi","chlazené zboží, které je stejné jako ostatní, akorát je chlazené...",0,0);
        
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
        Predmet hrnec = new Predmet("hrnec",false,"hrnec.jpg");
        Predmet sporak = new Predmet("sporak",false,"sporak.jpg");
        Predmet klice = new Predmet("klice",true,"klice.jpg");
        Predmet penezenka = new Predmet("penezenka",true,"penezenka.jpg");
        Predmet postel = new Predmet("postel",false,"postel.jpg");
        Predmet gauc = new Predmet("gauc",false,"gauc.jpg");
        Predmet televize = new Predmet("televize",false,"televize.jpg");
        Predmet pneumatika = new Predmet("pneumatika",false,"pneumatika.jpg");
        Predmet autoHonda= new Predmet("autoHonda",false,"autoHonda.jpg");
        Predmet autoCitroen= new Predmet("autoCitroen",false,"autoCitroen.jpg");
        Predmet kosik= new Predmet("kosik",false,"kosik.jpg");
        Predmet letaky= new Predmet("letaky",true,"letaky.jpg");
        Predmet kalendar = new Predmet("kalendar",true,"kalendar.jpg");
        Predmet cokolada = new Predmet("cokolada",true,"cokolada.jpg");
        Predmet kava = new Predmet("kava",true,"kava.jpg");
        Predmet jablko = new Predmet("jablko",true,"jablko.jpg");
        Predmet mrkev = new Predmet("mrkev",true,"mrkev.jpg");
        Predmet celer = new Predmet("celer",true,"celer.jpg");
        Predmet cocka = new Predmet("cocka",true,"cocka.jpg");
        Predmet hladkaMouka= new Predmet("hladkaMouka",true,"hladkaMouka.jpg");
        Predmet hrubaMouka= new Predmet("hrubaMouka",true,"hrubaMouka.jpg");
        Predmet ryze= new Predmet("ryze",true,"ryze.jpg");
        Predmet chilli= new Predmet("chilli",true,"chilli.jpg");
        Predmet sul = new Predmet("sul",true,"sul.jpg");
        Predmet koreniBrambory= new Predmet("koreniAmerickeBrambory",true,"koreniBrambory.jpg");
        Predmet matonni= new Predmet("Mattoni",true,"mattoni.jpg");
        Predmet cocacola= new Predmet("Cocacola",true,"cocacola.jpg");
        Predmet kureciStehna = new Predmet("kureciStehna",true,"kureciStehna.jpg");
        Predmet vejce = new Predmet("vejce",true,"vejce.jpg");
        Predmet hranolky = new Predmet("hranolky",true,"hranolky.jpg");
        Predmet mleko = new Predmet("mleko",true,"mleko.jpg");
        Predmet maslo = new Predmet("maslo",true,"maslo.jpg");
        Predmet pokladna = new Predmet("pokladna",false,"pokladna.jpg");
        Predmet cigarety = new Predmet("cigarety",true,"cigarety.jpg");
        Predmet prodavacka = new Predmet("prodavacka",false,"prodavacka.jpg");
        
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
    
    @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }

}
