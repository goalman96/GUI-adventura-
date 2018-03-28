package com.github.goalman96.adventura.logika;
import java.util.List;
import java.util.ArrayList;


/**
 * Trida Batoh - definuje batoh
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * Batoh obsahuje omezený počet předmětů
 * 
 * @author    Tibor Vondrášek
 * @version   29.12.2017
 */
public class Batoh
{
    // instance variables - replace the example below with your own
    private int velikost;
    private List<Predmet> batoh;
    
    /**
     * Vytvoření batohu se zadanou velikostí
     *
     * @param velikost batohu.
     */
    public Batoh(int velikost) {
        this.velikost = velikost;
        this.batoh = new ArrayList<Predmet>();
    }
    
    /**
     * Vrací velikost batohu.
     *
     * @return velikost batohu
     */
    public int getVelikost() {
        return velikost;
    }
    
    /**
     * Vrací počet předmětů v batohu
     *
     * @return název předmětu
     */
    public int getPocet() {
        return batoh.size();
    }
    
    /**
     * Přidá zadaný předmět do batohu.
     *
     * @param přidávaný předmět
     *
     */
    public void vlozPredmet(Predmet predmet) {
        batoh.add(predmet);
    }
    
    /**
     * Vyhodí zadaný předmět z batohu
     *
     * @param vyhazovaný předmět
     *
     */
    public void vyhodPredmet(Predmet predmet) {
        batoh.remove(predmet);
    }
    
    /**
     * Najde zadaný předmět v batohu a vrátí ho.
     *
     * @param hledaný předmět
     *
     * @return nalezený předmět
     */
    public Predmet najdiPredmet(String predmet) {
        for (Predmet hledanyPredmet : batoh) {
            if (hledanyPredmet.getNazev().equals(predmet)) {
                return hledanyPredmet;
            }
        }
        return null;
    }
    
    /**
     * Změní velikost batohu
     *
     * @param velikost batohu
     *
     */
    public void setVelikost(int velikost) {
        this.velikost = velikost;
    }
    
    /**
     * Vrácí názvy předmětů v batohu.
     *
     * @return názvy předmětů v batohu
     */
    public String  getObsah() {
        String vracenyText="batoh:";
        for (Predmet predmet : batoh) {
           vracenyText += " "+predmet.getNazev();
        }
        return vracenyText;
    }
}
