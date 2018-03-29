package com.github.goalman96.adventura.logika;


import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.github.goalman96.adventura.logika.Predmet;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 * Každý prostor si ukládá předměty.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Tibor Vondrasek
 * @version 29.12.2017
 */
public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private List<Predmet> predmety;
    private double x;
    private double y;
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyně", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     * @param x 
     * @param y 
     */
    public Prostor(String nazev, String popis, double x, double y) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        predmety = new ArrayList<Predmet>();
        this.x = x;
        this.y = y;
    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor... vedlejsi) {
        for (Prostor vedlejsiProstor : vedlejsi)
        vychody.add(vedlejsiProstor);
    }
    
    /**
     * Definuje předměty, které se nachází v prostoru.
     *
     * @param predmety, ktere se nachází v prostoru.
     *
     */
     public void setPredmet(Predmet... vkladanePredmety) {
        for (Predmet vkladanyPredmet : vkladanePredmety) {
        predmety.add(vkladanyPredmet);
    }
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor 
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
       // batoh= plan.getBatoh();
        return "\n Jsi v mistnosti/prostoru " + popis + ".\n"
                + popisVychodu() + ".\n"
                + popisPredmetu()+ ".";
    }
    
    /**
     * Vrací seznam předmětů, které se nachází v prostoru např. následovně: 
     * předměty: peněženka, klíče
     *
     * @return seznam předmětů
     */
    public String popisPredmetu() {
        String vracenyText = "předměty:";
        for (Predmet predmet : predmety) {
            vracenyText += " "+ predmet.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " "+ sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota

     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    /**
     * Vrací hledaný předmět v prostoru, podle názvu.
     * Pokud se předmět v prostoru nenachází, vrátí null.
     *
     * @return předmět
     */
    public Predmet najdiPredmet(String hledanyPredmet) {
        for (Predmet predmet : predmety) {
            if (predmet.getNazev().equals(hledanyPredmet)) {
                return predmet;
            }
        }
        return null;
    }
    
    /**
     * Odebere zadaný předmět z prostoru.
     *
     * @param odebíraný předmět
     *
     */
    public void odeberPredmet(Predmet odebiranyPredmet) {
        predmety.remove(odebiranyPredmet);
    }
    
    /**
     * Přidá zadaný předmět do prostoru.
     *
     * @param přidávaný předmět
     *
     */
    public void pridejPredmet(Predmet predmet) {
        predmety.add(predmet);
    }
    
    /**
     * metoda vrací seznam věcí v místnosti
     * @return kolekce věcí
     */
    public Collection<Predmet> getPredmety() {
    	return Collections.unmodifiableCollection(predmety);
    }
    
    public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
