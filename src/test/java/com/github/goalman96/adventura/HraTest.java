package com.github.goalman96.adventura;

import org.junit.Before;
import org.junit.Test;

import com.github.goalman96.adventura.logika.Hra;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Tibor Vondrasek
 * @version   29.12.2017
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }
    
    /***************************************************************************
     * Testuje průběh hry, po zavolání příkazu testuje, zda hra končí
     * ,v jaké aktuální místnosti se hráč nachází, jaké věci jsou v místnosti,
     * ,jaké věci jsou v batohu a zda skutečně nejdou sebrat nepřenositelné věci.
     * 
     */
    @Test
    public void testPrubehHry() {
        assertEquals("kuchyne", hra1.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("předměty: hrnec sporak klice penezenka", hra1.getHerniPlan().getAktualniProstor().popisPredmetu());
        assertEquals("batoh:", hra1.getHerniPlan().getBatoh().getObsah());
        assertEquals("Jak tohle chceš dát do batohu?\n", hra1.zpracujPrikaz("seber sporak"));
        hra1.zpracujPrikaz("seber klice");
        assertEquals("předměty: hrnec sporak penezenka", hra1.getHerniPlan().getAktualniProstor().popisPredmetu());
        assertEquals("batoh: klice", hra1.getHerniPlan().getBatoh().getObsah());
        hra1.zpracujPrikaz("jdi garaz");
        assertEquals(false, hra1.konecHry());
        assertEquals("předměty: pneumatika", hra1.getHerniPlan().getAktualniProstor().popisPredmetu());
        assertEquals("batoh: klice", hra1.getHerniPlan().getBatoh().getObsah());
        assertEquals("garaz", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi auto");
        assertEquals(false, hra1.konecHry());
        assertEquals("předměty:", hra1.getHerniPlan().getAktualniProstor().popisPredmetu());
        assertEquals("batoh: klice", hra1.getHerniPlan().getBatoh().getObsah());
        assertEquals("auto", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("vyhod klice");
        assertEquals("předměty: klice", hra1.getHerniPlan().getAktualniProstor().popisPredmetu());
        assertEquals("batoh:", hra1.getHerniPlan().getBatoh().getObsah());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }
    
    /***************************************************************************
     * Testuje, zda je hru možné vyhrát.
     */
    @Test
    public void testVyhra() {
        hra1.zpracujPrikaz("seber penezenka");
        hra1.zpracujPrikaz("jdi garaz");
        hra1.zpracujPrikaz("jdi auto");
        hra1.zpracujPrikaz("jed parkoviste");
        hra1.zpracujPrikaz("jdi vchod");
        hra1.zpracujPrikaz("jdi akcniZbozi");
        hra1.zpracujPrikaz("jdi lusteniny");
        hra1.zpracujPrikaz("seber hladkaMouka");
        hra1.zpracujPrikaz("jdi chlazeneZbozi");
        hra1.zpracujPrikaz("seber vejce");
        hra1.zpracujPrikaz("seber mleko");
        hra1.zpracujPrikaz("jdi koreni");
        hra1.zpracujPrikaz("seber sul");
        hra1.zpracujPrikaz("jdi akcniZbozi");
        hra1.zpracujPrikaz("jdi pokladny ");
        hra1.zpracujPrikaz("zaplat");
        hra1.zpracujPrikaz("jdi parkoviste");
        hra1.zpracujPrikaz("jdi auto");
        hra1.zpracujPrikaz("jed garaz");
        hra1.zpracujPrikaz("jdi kuchyne");
        hra1.zpracujPrikaz("uvar");
        assertEquals("palacinky", hra1.getHerniPlan().getAktualniProstor().najdiPredmet("palacinky").getNazev());
    }
}
