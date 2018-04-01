/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.goalman96.adventura.logika;


/**
 * Trida Prostor - definuje předměty v prostorech
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * Každý prostor může obsahovat několik předmětů
 * 
 * @author    Tibor Vondrasek
 * @version   29.12.2017
 */
public class Predmet
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private boolean prenositelnost;
    private String obrazek;
    //== Konstruktory a tovární metody =============================================

    /**
     * Vytvoření předmětu se zadaným názvem a přenositelností
     *
     * @param název předmětu.
     * 
     * @param přenositelnost.
     */
    public Predmet(String nazev, boolean prenositelnost,String obrazek)
    {
        this.nazev = nazev;
        this.prenositelnost = prenositelnost;
        this.obrazek = obrazek;
    }

    /**
     * Vrací název předmětu (byl zadán při vytváření předmětu jako parametr
     * konstruktoru)
     *
     * @return název předmětu
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     * Vrací přenositelnost předmětu (byla zadáan při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return přenositelnost
     */
    public boolean getPrenositelny(){
        return prenositelnost;
    }
    
    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNazev();
	}
        
        
    

    //== Soukromé metody (instancí i třídy) ========================================

    public String getObrazek() {
        return obrazek;
    }

}
