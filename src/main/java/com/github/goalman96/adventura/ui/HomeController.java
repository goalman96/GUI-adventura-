package com.github.goalman96.adventura.ui;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.github.goalman96.adventura.logika.IHra;
import com.github.goalman96.adventura.logika.Prostor;
import com.github.goalman96.adventura.logika.Predmet;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import com.github.goalman96.adventura.logika.Hra;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky, Tibor Vondrasek
 *
 */
public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ListView<Predmet> seznamVeciMistnost;
	@FXML private ListView<Prostor> seznamVychodu;
    @FXML private ListView<Object> seznamVeciBatoh;
	@FXML private ImageView uzivatel;
	
        
        
        private ObservableList<Object> veciBatoh = FXCollections.observableArrayList();
	private IHra hra;
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	@FXML public void odesliPrikaz() {
		String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
		vystup.appendText("\n----------\n"+vstupniText.getText()+"\n----------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
                hra.getHerniPlan().notifyObservers();
		if(hra.konecHry()) {
			vystup.appendText("\n----------\nKonec hry\n----------\n");
			vstupniText.setDisable(true);
		}
                
	}
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	public void inicializuj(IHra hra) {
		vystup.setText(hra.vratUvitani());
		vystup.setEditable(false);
		this.hra = hra;
		seznamVeciMistnost.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getPredmety());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
                seznamVeciBatoh.setItems(veciBatoh);
		hra.getHerniPlan().addObserver(this);
                hra.getHerniPlan().notifyObservers();
	}
	
	/**
	 * Metoda spustí novou hru
	 * zavolá metodu notifyObservers, aby uvědomila objekty 
	 */
        @FXML public void novaHra() 
        {
                hra = new Hra();
		vystup.setText(hra.vratUvitani());
		vstupniText.setDisable(false);
                hra.getHerniPlan().addObserver(this);
                hra.getHerniPlan().notifyObservers();
        }
        
        /**
    	 * Metoda ukončí hru
    	 */ 
        @FXML public void konecHry() 
        {
            vstupniText.setText("konec");
            odesliPrikaz();
        }
        
        /**
    	 * Metoda zobrazí nápovědu uloženou v html souboru pomocí webview
    	 * nápovědu zobrazí v novém okně
    	 */
         @FXML public void Napoveda() 
        {
            Stage stage = new Stage();
            stage.setTitle("Nápověda");
            
            WebView webView = new WebView();               
            webView.getEngine().load(com.github.goalman96.adventura.ui.Application.class.getResource("/zdroje/napoveda.html").toExternalForm());
            
            stage.setScene(new Scene(webView, 1200, 650));
            stage.show();
        }
        
         /**
     	 * Metoda slouží k aktualiozování aktuální místnsoti, posunu panáčka na mapě
     	 * metoda se volá vždy když je pozorovaný objekt změněn zavolá notifyObservers metodu
     	 * @param arg0 pozorovaný objekt
     	 * @param arg1 argument pro notifyObservers meotdu
     	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		seznamVeciMistnost.getItems().clear();
		seznamVychodu.getItems().clear();
		seznamVeciMistnost.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getPredmety());
		seznamVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		uzivatel.setX(hra.getHerniPlan().getAktualniProstor().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniProstor().getY());
                
                List<Predmet> sBatoh = hra.getHerniPlan().getBatoh().getBatoh();
                veciBatoh.clear();
                for (Predmet vec : sBatoh) 
                {
                    ImageView obrazek = new ImageView(new Image(com.github.goalman96.adventura.ui.Application.class.getResourceAsStream("/zdroje/"+vec.getObrazek()), 100, 100, false, false));
                    veciBatoh.add(obrazek);
                }
                
		
	}

}
