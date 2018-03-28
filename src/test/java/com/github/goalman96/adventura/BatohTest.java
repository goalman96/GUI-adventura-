package com.github.goalman96.adventura;

import org.junit.Before;
import org.junit.Test;

import com.github.goalman96.adventura.logika.Batoh;
import com.github.goalman96.adventura.logika.Hra;
import com.github.goalman96.adventura.logika.Predmet;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class BatohTest {
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
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPlny() {
        Batoh batoh = hra1.getHerniPlan().getBatoh();
        hra1.zpracujPrikaz("seber penezenka");
        hra1.zpracujPrikaz("seber klice");
        hra1.zpracujPrikaz("jdi garaz");
        hra1.zpracujPrikaz("jdi auto");
        hra1.zpracujPrikaz("jed parkoviste");
        hra1.zpracujPrikaz("jdi vchod");
        hra1.zpracujPrikaz("jdi akcniZbozi");
        hra1.zpracujPrikaz("seber kalendar");
        hra1.zpracujPrikaz("seber kava");
        Predmet cokolada = hra1.getHerniPlan().getAktualniProstor().najdiPredmet("cokolada");
        hra1.zpracujPrikaz("seber cokolada");
        assertEquals(cokolada, batoh.najdiPredmet("cokolada"));
        hra1.zpracujPrikaz("jdi koreni");
        assertEquals("Batoh je plný!\n", hra1.zpracujPrikaz("seber sul"));
    }
}
