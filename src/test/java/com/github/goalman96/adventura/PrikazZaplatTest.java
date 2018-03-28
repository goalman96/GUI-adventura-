package com.github.goalman96.adventura;

import org.junit.Before;
import org.junit.Test;

import com.github.goalman96.adventura.logika.Batoh;
import com.github.goalman96.adventura.logika.Hra;
import com.github.goalman96.adventura.logika.Prostor;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Tibor Vondrasek
 * @version  29.12.2017
 */
public class PrikazZaplatTest {
    private Hra hra1;
    private String zaplat;

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
        zaplat = "zaplat";
        hra1.zpracujPrikaz("seber penezenka");
        hra1.zpracujPrikaz("jdi garaz");
        hra1.zpracujPrikaz("jdi auto");
        hra1.zpracujPrikaz("jed parkoviste");
        hra1.zpracujPrikaz("jdi vchod");
        hra1.zpracujPrikaz("jdi akcniZbozi");
        hra1.zpracujPrikaz("seber cokolada");
    }

    /***************************************************************************
     * Testuje zda není možné platit mimo prostor pokladny.
     */
    @Test
    public void testNeniPokladna() {
        assertEquals("Komu tady chceš platit?\n", hra1.zpracujPrikaz(zaplat));
    }
    
    /***************************************************************************
     * Testuje zda není možné platit bez peněženky v batohu
     */
    @Test
    public void testNeniPenezenka() {
        hra1.zpracujPrikaz("vyhod penezenka");
        hra1.zpracujPrikaz("jdi pokladny");
        assertEquals("Nemáš čím zaplatit, zapomněl jsi peněženku.\n", hra1.zpracujPrikaz(zaplat));
    }
    
    /***************************************************************************
     * Testuje je možné zaplatit, když je vše v pořádku.
     */
    @Test
    public void testZaplaceno() {
        hra1.zpracujPrikaz("jdi pokladny");
        Prostor aktualniProstor = hra1.getHerniPlan().getAktualniProstor();
        Batoh batoh = hra1.getHerniPlan().getBatoh();
        assertEquals("Zaplatil jsi zboží. \n"+ aktualniProstor.dlouhyPopis() +"\n"+batoh.getObsah()+".\n",hra1.zpracujPrikaz(zaplat));
        assertEquals("Už jsi platil.\n",hra1.zpracujPrikaz(zaplat));
    }
}
