package kassaSwing;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import wbkassa.KassaGUI;
import kassa.Kassa;
import kassa.Osasto;
//import kassa.SailoException;
import kassa.Tuote;
/**
 * Kommunikoi kassan kanssa
 * @author Ronja Lindholm
 * @version 15.2.2015
 */
/*
 * EI TOIMI: OHJEET DIALOGI
 */

public class KassaSwing {
    private KassaGUI kassaGUI;
    private Kassa kassa;


    /**
     * Alustetaan kassaSwing kassaGUIlle
     * @param kassaGUI mit� komponentteja k�ytet��n
     */
    public KassaSwing(KassaGUI kassaGUI) {
        this.kassaGUI = kassaGUI;
        kassa = new Kassa(); 
    }   


    /**
     * Palautetaan viite kassaaan
     * @return kassa
     */
    public Kassa getKassa(){
        return kassa; 
    }


    /**
     * Palautetaan kuitti
     * @return kuitti
     */
    public JTable getKuitti() {return kassaGUI.getKuitti();}


    /**
     * Alustetaan kassaSwing
     */
    public KassaSwing() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Avataan kassa
     */
    public void setKassa() {

        kassaGUI = new KassaGUI();
        kassaGUI.setVisible(true); 
        kassa = getKassa(); 
    }


    /**
     * N�ytet��n error-message
     * @param teksti mik� ei toimi
     */
    public void ErrorMessage(String teksti) {
        JOptionPane.showMessageDialog(null, teksti + "\nEi toimi viel�!");

    } 


    /**
     * Lisataan uusi k�ytt�j�
     */
    public void uusiKayttaja() {

        ErrorMessage("Uusi myyj�"); 
    }


    /**
     * Ohjeet dialogi
     */
    public void ohjeet() {
        ErrorMessage("Ohjeet"); 

    }


    /**
     * Poista osasto tiedostosta
     * @param osasto 
     * @param string
     */
    public void poistaOsasto(String osasto) {
        Osasto o = kassa.etsiOsastoa(osasto);
        kassa.poistaOsasto(o);

    }



    /**
     * Haetaan osastojen tiedoston nimi
     * @return tiedoston nimi
     */
    public String getTiedostoOsastot() {
        return "osastot";
    }

    /**
     * Haetaan tuotteiden tiedoston nimi
     * @return tiedoston nimi
     */
    public String getTiedostoTuotteet() {
        return "tuotteet"; 

    }

    /**
     * Poista tuote tiedostosta
     * @param tuotteenViivakoodi 
     */
    public void poistaTuoteTuotelistasta(String tuotteenViivakoodi) {
        Tuote tuote = kassa.annaTuoteJollaViivakoodi(tuotteenViivakoodi);
        kassa.poista(tuote);


    }


    /**
     * Lis�� tuote kuittiin
     * @param merkkijono
     * @throws SailoException
     */
    public void lisaaTuoteKuittiin(String merkkijono)  {
        kassa.lueTiedostosta();
        String ab = kassa.etsiKoodi(merkkijono);
        Double hinta = kassa.etsiKoodinHinta(merkkijono);

        if (!(ab.equals("Tuotevirhe"))) {
            kassaGUI.lisaaKuittiListaan(merkkijono);
            kassaGUI.lisaaKuittiin(ab, hinta);
            kassaGUI.lisaaSummaan(hinta);
            kassaGUI.setVirhe("Tuote " + merkkijono + " l�ytyi!");
        }
        else {kassaGUI.setVirhe("Tuotetta "+ merkkijono +" ei l�ydy");}
    }


    /**
     * Haetaan tuotteen tiedot kassaGuin n�yt�lle
     * @param koodi k�ytt�j�n sy�tt�m� oletettu viivakoodi
     */
    public void tuoteinfo(String koodi) {
        Tuote o = new Tuote();
        Boolean jatketaanko = true; 
        try {
            o = kassa.annaTuoteJollaViivakoodi(koodi); 
            o.getHinta();
        } catch (NullPointerException e) {
            kassaGUI.setVirhe("Tuotetta ei l�ydy"); 
            jatketaanko = false;
        }
        if(jatketaanko) {

            String osasto = o.getLuokka();
            Osasto osastoviite = kassa.etsiOsastoa(osasto);
            String selite = osastoviite.getOsastonSelite();
            kassaGUI.setVirhe(

                    "Tuote " + o.getKoodi() + " l�ytyi!\n"
                            + "Nimi: " + o.getNimi()+"\n"
                            + "Hinta: " + o.getHinta()+"\n"
                            + "Pikakoodi:" + o.getPikakoodi()+"\n"+
                            "Osasto: " + o.getLuokka() + "\n" + 
                            "Osastoselite: " + selite );
        }
    }


    /**
     * Poistetaan tuote kuitista
     * @param viivakoodi 
     */
    public void poistaTuoteKuitista(String viivakoodi) {
        try {Tuote tuote =  kassa.annaTuoteJollaViivakoodi(viivakoodi);
        tuote.getClass();
        kassaGUI.poistaTuoteKuitista(tuote);
        }catch (NullPointerException e) {
            kassaGUI.setVirhe("Tuotetta ei l�ydy"); 
        }
    }


    /**
     * Hyv�ksyt��n maksu
     * @param viivakoodit 
     */
    public void maksa(ArrayList<String> viivakoodit) {
        kassa.lisaaMyytyja(viivakoodit);
    }


    /**
     * Hyl�t��n maksu
     */
    public void cancelMaksu() {
        ErrorMessage("Hyl�t��n maksu");
    }

}
