package kassaSwing;

import javax.swing.JOptionPane;

import wbkassa.Inventaario;
import kassa.Kassa;
import kassa.Osasto;
import kassa.Tuote;

/**
 * Kommunikoi kassan kanssa
 * @author Ronja
 * @version 25.3.2015
 */
public class kassaSwingInventaario {
    
    @SuppressWarnings("unused")
    private Inventaario inventaario;
    private Kassa kassa;
    private KassaSwing KassaS; 

    
    /**
     * Alustetaan kassaSwing inventaariolle
     * @param inventaario
     */
    public kassaSwingInventaario(Inventaario inventaario) {
        this.inventaario = inventaario; 
        kassa = new Kassa();

     } 
    
    /**
     */
    public kassaSwingInventaario() {
     
        // TODO:
    }

    /**
     * Palautetaan viite kassaaan
     * @return kassa
     */
    public Kassa getKassa(){
        return kassa; 
    }
    
    /**
     * Ohjeet dialogi
     */
    public void ohjeet() {
        ErrorMessage("Ohjeet"); 
    }
    
    /**
     * N‰ytet‰‰n error-message
     * @param teksti mik‰ ei toimi
     */
    public void ErrorMessage(String teksti) {
        JOptionPane.showMessageDialog(null, teksti + "\nEi toimi viel‰!");
           
        } 
    
 
    /**
     * Lis‰‰ uusi tuote tuotelistaan
     * @param viivakoodi
     * @param nimi
     * @param Osasto
     * @param Luokka
     * @param selite
     * @param hinta
     * @return tuotteen muodostettu pikakoodi
     */
    public int lisaaUusiTuote(String viivakoodi, String nimi, String Osasto, Double hinta ) {
        Osasto o = kassa.etsiOsastoa(Osasto);
        try {o.getOsastonNimi(); 
        }catch (NullPointerException e) {
            return -2;
        }
        Tuote tuote = new Tuote(viivakoodi, nimi, Osasto, hinta); 
        tuote.rekisteroi();
        Boolean onnistuiko = kassa.lisaa(tuote);
        if(onnistuiko) return tuote.getPikakoodi();


        return -1;
    }
    
    
    /**
     * Lis‰t‰‰n uusi osasto
     * @param osasto
     * @param osastoSelite
     * @return -1 jos ei onnistu
     */
    public int lisaaUusiOsasto(String osasto, String osastoSelite) {
        Osasto o = kassa.etsiOsastoa(osasto); 
        try{o.getOsasto();} catch (NullPointerException  e) {
            Osasto uusiOsasto = new Osasto(osasto, osastoSelite);
            kassa.lisaa(uusiOsasto); 
            return 1;
        }

        return -1;

    }
    
    
    /**
     * P‰ivit‰ tuotelista
     * @param tiedNimi tuotteiden tiedosto
     * @param tiedNimi2 osastojen tiedosto
     * @return uusi rivi
     */
    public Object[][] paivita() {
        Object[][] roww = new Object[kassa.OsastojenLkm()*kassa.tuotteidenLkm()+kassa.OsastojenLkm()][8]; 
        Tuote[] tuot;
        int rivinPaikka = 0; 
        for(int osastojenMaara =1; osastojenMaara<=kassa.OsastojenLkm(); osastojenMaara++) {
            roww[rivinPaikka][0] = "" ;
            roww[rivinPaikka][1] = "" ;
            roww[rivinPaikka][2] = kassa.annaOsasto(osastojenMaara).getOsastonNimi();
            roww[rivinPaikka][3] = "" ;
            roww[rivinPaikka][4] = kassa.annaOsasto(osastojenMaara).getOsastonSelite();
            roww[rivinPaikka][5] = "";
            roww[rivinPaikka][6] = kassa.annaTuotteetLkm(kassa.annaOsasto(osastojenMaara)); 
            roww[rivinPaikka][7] = "";
            rivinPaikka++;

            for(int osastoaVastaavaTuote=0; osastoaVastaavaTuote<kassa.annaTuotteetLkm(kassa.annaOsasto(osastojenMaara)); osastoaVastaavaTuote++) {   
                tuot = kassa.annaTuotteet(kassa.annaOsasto(osastojenMaara)); 
                roww[rivinPaikka][0] = tuot[osastoaVastaavaTuote].getKoodi();
                roww[rivinPaikka][1] = tuot[osastoaVastaavaTuote].getNimi() ;
                roww[rivinPaikka][2] = tuot[osastoaVastaavaTuote].getLuokka();
                roww[rivinPaikka][3] = tuot[osastoaVastaavaTuote].getPikakoodi();
                roww[rivinPaikka][4] = kassa.annaOsasto(osastojenMaara).getOsastonSelite();
                roww[rivinPaikka][5] = tuot[osastoaVastaavaTuote].getHinta();
                roww[rivinPaikka][6] = "";
                roww[rivinPaikka][7] = tuot[osastoaVastaavaTuote].getMyydyt();
                rivinPaikka++;


            }
        }


        Object[][] roww2 = new Object[rivinPaikka][6]; 
        roww2 = roww.clone();

        return roww2;
    }
    
    /**
     * Etsit‰‰n osastojen mukaisesti tuotteet
     * @param osastonNimi 
     * @return rivit jotka t‰sm‰‰
     */
    public Object[][] EtsiOsastot(String osastonNimi) {
        Object[][] roww = new Object[0][0]; 
        Osasto o = kassa.etsiOsastoa(osastonNimi); 
        try { o.getOsastonNimi();
        } catch (NullPointerException e) {
            return roww;
        }

        Tuote[] tuot = kassa.annaTuotteet(o);
        Object[][] roww2 = new Object[tuot.length][8];

        int rivinPaikka = 0;
        roww2[0][0] = "" ;
        roww2[0][1] = "" ;
        roww2[0][2] = o.getOsastonNimi();
        roww2[0][3] = "" ;
        roww2[0][4] = o.getOsastonSelite();
        roww2[0][5] = "";
        roww2[0][6] = kassa.annaTuotteetLkm(o);
        roww2[0][7] = "";
        rivinPaikka++;

        for(int osastoaVastaavaTuote=0; osastoaVastaavaTuote < kassa.annaTuotteetLkm(o) ; osastoaVastaavaTuote++, rivinPaikka++) {   
            roww2[rivinPaikka][0] = tuot[osastoaVastaavaTuote].getKoodi();
            roww2[rivinPaikka][1] = tuot[osastoaVastaavaTuote].getNimi() ;
            roww2[rivinPaikka][2] = tuot[osastoaVastaavaTuote].getLuokka();
            roww2[rivinPaikka][3] = tuot[osastoaVastaavaTuote].getPikakoodi();
            roww2[rivinPaikka][4] = o.getOsastonSelite();
            roww2[rivinPaikka][5] = tuot[osastoaVastaavaTuote].getHinta();
            roww2[rivinPaikka][6] = "";
            roww2[rivinPaikka][7] = tuot[osastoaVastaavaTuote].getMyydyt();
            rivinPaikka++;
        }
        return roww2;
        
        
    }  
    
    
    /**
     * Etsit‰‰n nimen mukaisesti tuotteet
     * @param nimi 
     * @return matriisin jossa yhden tuotteen tiedot
     */
    public Object[][] EtsiNimenMukaan(String nimi) {
        Object[][] roww = new Object[0][0]; 
        Tuote o = kassa.etsiTuoteNimenMukaan(nimi);
        try { o.getNimi();
        } catch (NullPointerException e) {
            return roww;
        }

        String osastonNimi = o.getLuokka(); 
        Osasto osasto = kassa.etsiOsastoa(osastonNimi);

        Object[][] roww2 = new Object[1][8];

        roww2[0][0] = o.getKoodi();
        roww2[0][1] = o.getNimi();
        roww2[0][2] = o.getLuokka();
        roww2[0][3] = o.getPikakoodi();
        roww2[0][4] = osasto.getOsastonSelite();
        roww2[0][5] = o.getHinta();
        roww2[0][5] = o.getHinta();
        roww2[0][6] = "";
        roww2[0][7] = o.getMyydyt();


        return roww2;
    }


    /**
     * Etsit‰‰n viivakoodin mukaisesti tuotteet
     * @param koodi 
     * @param string 
     * @return  tuotteen jos lˆytyy, muuten tyhj‰ matriisi
     */
    public Object[][] EtsiviivakoodinMukaan(String koodi) {
        Object[][] roww = new Object[0][0]; 
        Tuote o = kassa.annaTuoteJollaViivakoodi(koodi);
        try { o.getNimi();
        } catch (NullPointerException e) {
            return roww;
        }

        String osastonNimi = o.getLuokka(); 
        Osasto osasto = kassa.etsiOsastoa(osastonNimi);

        Object[][] roww2 = new Object[1][8];

        roww2[0][0] = o.getKoodi();
        roww2[0][1] = o.getNimi();
        roww2[0][2] = o.getLuokka();
        roww2[0][3] = o.getPikakoodi();
        roww2[0][4] = osasto.getOsastonSelite();
        roww2[0][5] = o.getHinta();
        roww2[0][6] = "";
        roww2[0][7] = o.getMyydyt();



        return roww2;

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
     * Poista tuote tiedostosta
     * @param tuotteenViivakoodi 
     */
    public void poistaTuoteTuotelistasta(String tuotteenViivakoodi) {
        Tuote tuote = kassa.annaTuoteJollaViivakoodi(tuotteenViivakoodi);
        kassa.poista(tuote);
    }
    
    /**
     * Tyhjenn‰ kent‰t
     */
    public void poistaUusiTuoteKentat() {
       ErrorMessage("Tyhjenn‰ kent‰t");
       
    }

    /**
     * Asetetaan kassa
     */
    public void setKassa() {
        KassaS.setKassa();
        
    }



    /**
     * Aseta tiedostot EI TOTEUTETTU!s
     * @param tiedostoTuotteet
     * @param tiedostoOsasto
     */
   public void setTiedostot(String tiedostoTuotteet, String tiedostoOsasto) {
     //  kassa.asetaOsastot(tiedostoTuotteet, tiedostoOsasto);
        
    }

    
}
