package kassa;

import java.util.ArrayList;

/**
 * Vastuualueet:                                     
 * | -tuote            |
 * | -tuotteet         |
 * | -osasto           |
 * huolehtii tuotteet ja osastot  -luokkien     
 * v‰lisest‰ yhteistyˆst‰      
 * Kassa-luokka
 * @author Ronja Lindholm
 * @version 25.2.2015
 *
 */
public class Kassa {
    private  Tuotteet tuotteet = new Tuotteet("tuotteet");
    private  Osastot osastot = new Osastot("osastot");
    private  String tiedostoTuotteet = "tuotteet";
    private  String tiedostoOsastot = "osastot";




    /**
     * Luetaan tiedostot
     * @param tiedNimi tuotteiden tiedosto
     * @param tiedNimi2 osastojen tiedosto
     */
    public void lueTiedostosta(){
        osastot = new Osastot(tiedostoOsastot); 
        tuotteet = new Tuotteet(tiedostoTuotteet); 


    }
    @SuppressWarnings("unused")
    private void setTiedostot(String tuotteidenTiedosto, String osastonNimi) {
        tiedostoTuotteet = tuotteidenTiedosto; 
        tiedostoOsastot = osastonNimi; 
    }

    /**
     * Palauttaa kaikkien tuotteiden lukum‰‰r‰n tuotetaulukosta
     * Huom. ei vastaa vltt‰m‰tt‰ tiedoston tuotelistaa, ellei ensin talleteta tuotteita
     * @return tuotteiden lukum‰‰r‰n
     * @example
     * <pre name="test">
     * Kassa kassa = new Kassa(); 
     * kassa.getTuotteita() === 0;  
     * Osasto osasto = new Osasto("osasto", "kukkajuttuja"); 
     * Tuote tuote = new Tuote("1", "kukka", "osasto", 0.95);
     * kassa.lisaa(osasto); 
     * kassa.lisaa(tuote);
     * kassa.getTuotteita() === 1;  
     * Tuote a = new Tuote();
     * kassa.lisaa(a); 
     * kassa.getTuotteita() === 2; 
     * Tuote b = new Tuote();
     * kassa.lisaa(b); 
     * kassa.getTuotteita() === 3;
     * </pre>
     */
    public int getTuotteita(){
        return tuotteet.getLkm();
    }

    /**
     * Poistaa tuotteen tiedostosta
     * @param tuote Mik‰ tuote poistetaan
     * @param tiedosto tuotteiden tiedosto mist‰ poistetaan
     * @example
     * <pre name="test">
     * #import java.io.File;
     * #THROWS NullPointerException
     * #THROWS IndexOutOfBoundsException
     * Kassa kassa = new Kassa(); 
     * Tuote tuote = new Tuote("123", "kukka", "osasto", 0.95);
     * kassa.lisaa(tuote); 
     * kassa.getTuotteita() === 1; 
     * kassa = new Kassa(); 
     * kassa.annaTuote(0);
     * kassa.getTuotteita() === 0; 
     * kassa.lueTiedostosta("tuotteet.txt", "osastot.txt");
     * kassa.getTuotteita() === 1;
     * kassa.annaTuote(0).toString() =R= "|123|kukka|osasto|.*|0.95|";
     * kassa.poista(tuote, "tuotteet.txt"); 
     * kassa.annaTuote(0).toString(); #THROWS NullPointerException
     * kassa.getTuotteita() === 0;
     * kassa.lisaa(tuote); 
     * kassa.getTuotteita() === 1; 
     * kassa.poista(tuote, "tuotteet.txt"); 
     * kassa.getTuotteita() === 0;
     * </pre>
     */
    public void poista(Tuote tuote){
        tuotteet.poista(tuote);
    }

    /**
     * Poistaa sen tuotteen joilla on nro
     * @param osasto Mik‰ osasto poistetaan
     * @param tiedosto Mist‰ tiedostosta poistetaan
     */
    public void poistaOsasto(Osasto osasto){
        Tuote[] a = annaTuotteet(osasto); 
        for(int i = 0; i<a.length; i++){
            if(a[i] != null)
                poista(a[i]);
        }
        osastot.poista(osasto);

    }


    /**
     * Lis‰‰ tuote jos sit‰ ei ole viel‰ olemassa
     * @param tuote mik‰ tuote lis‰t‰‰n
     * @return true jos onnistui 
     * @example
     * <pre name="test">
     * Kassa kassa = new Kassa(); 
     * Tuote tuote = new Tuote("123", "kukka", "osasto", 0.95);
     * kassa.lisaa(tuote) === true; 
     * kassa.lisaa(tuote) === false; 
     * Tuote tuote2 = new Tuote("12", "kakku", "osasto", 0.95); 
     * kassa.lisaa(tuote2) === true; 
     * </pre>
     */
    public Boolean lisaa(Tuote tuote)  {
        Boolean onnistuiko = tuotteet.lisaa(tuote);
        if (onnistuiko) return true;
        return false;
    } 

    /**
     * Lisaa osasto
     * @param osasto mik‰ osasto lis‰t‰‰n
     * @return onnistuiko lis‰ys 
     * @example
     * <pre name="test">
     * #THROWS IndexOutOfBoundsException
     * Kassa kassa = new Kassa();
     * Osasto o = new Osasto("kukka", "kukkajutut"); 
     * kassa.lisaa(o) === 1; 
     * kassa.lisaa(o) ===-1; 
     * Osasto ai = new Osasto("kukka", "kukkajutu");
     * kassa.lisaa(ai) === -1;
     * Osasto aii = new Osasto("juo", "juomat ja mehut");
     * kassa.lisaa(aii) === 1; 
     * kassa.annaOsasto(0).toString() === "kukka|kukkajutut|";
     * kassa.annaOsasto(1).toString() === "juo|juomat ja mehut|"; 
     * kassa.annaOsasto(2); #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public int lisaa(Osasto osasto)  {
        int luku = osastot.lisaa(osasto);
        return luku;
    } 

    /**
     * Palauttaa tuotteen paikassa i
     * @param i tuotteen paikka
     * @return viitteen tuotteeseen i
     * @throws IndexOutOfBoundsException jos i on v‰‰rin
     */
    public Tuote annaTuote(int i ) throws IndexOutOfBoundsException {
        return tuotteet.anna(i); 
    }

    /**
     * @param args ei k‰ytˆss‰
     * @example
     * <pre name="test">
     *  Kassa kassa = new Kassa();
     *  Tuote k = new Tuote();
     *  Osasto o = new Osasto("", "");
     *  Tuote kk = new Tuote();
     *  kassa.lisaa(k);
     *  kassa.lisaa(o);
     *  k.getHinta() ~~~ 0.0;
     *  o.getOsastonNimi() === "";
     * </pre>
     */
    public static void main(String[] args) {
        Kassa kassa = new Kassa(); 
        Tuote Megaforce = new Tuote(); Megaforce.rekisteroi(); Megaforce.vastaaRandomNumero();
        Tuote Megaforc = new Tuote(); Megaforc.rekisteroi(); Megaforc.vastaaRandomNumero();
        Osasto kahvila = new Osasto("Kahvila", "Kahvilatuotteet"); 
        Osasto Mak = new Osasto("Mak", "Makeiset"); 
        kassa.lisaa(Megaforce);
        kassa.lisaa(Megaforc);
        kassa.lisaa(Megaforc);
        kassa.lisaa(Megaforc);
        kassa.lisaa(Megaforc);
        kassa.lisaa(Megaforc);
        kassa.lisaa(kahvila);
        kassa.lisaa(Mak);
        for (int i = 0; i<kassa.getTuotteita(); i++){
            Tuote tuote = kassa.annaTuote(i);
            System.out.print("Tuote nro " + i +" ");
            tuote.tulosta(System.out);

        }
    }
    /**
     * Etsit‰‰n viivakoodia
     * @param merkkijono mit‰ viivakoodia etsit‰‰n
     * @return palauttaa viivakoodin jos se lˆytyy, muuten "Tuotevirhe";
     */
    public String etsiKoodi(String merkkijono) {
        lueTiedostosta();
        Tuote a = tuotteet.etsiTuoteJollaViivakoodi(merkkijono); 
        try {a.getKoodi(); } catch (NullPointerException e) {
            return "Tuotevirhe";
        }
        return a.getKoodi(); 

    }
    /**
     * Etsit‰‰n viivakoodin hinnan
     * @param merkkijono koodin viivakoodi
     * @return palauttaa hinnan jos vastaavuus lˆytyy
     */
    public Double etsiKoodinHinta(String merkkijono) {
        return tuotteet.etsiKoodinHinta(merkkijono);

    }
    /**
     * Antaa osaston viitteen
     * @param i osaston indeksi
     * @return osasto
     */
    public Osasto annaOsasto(int i) {
        return osastot.anna(i);

    }
    /**
     * Palauta tuotteet joilla on sama osasto kuin annetulla osastolla
     * @param osasto mink‰ osaston tuotteita etsit‰‰n
     * @return tuotteet
     */
    public Tuote[] annaTuotteet(Osasto osasto) {
        Tuote[] a = new Tuote[30]; 
        int lkm = tuotteet.getLkm();
        int oikeaSumma = 0;
        for(int i = 1; i<=lkm; i++){
            if(tuotteet.anna(i).getLuokka().toString().equals(osasto.getOsastonNimi())) {
                a[oikeaSumma] = tuotteet.anna(i) ; oikeaSumma++;
            }
        }
        Tuote[] ab = new Tuote[oikeaSumma];   
        ab = a.clone(); 
        return ab; 
    }
    /**
     * Palauttaa sopivien tuotteiden lukum‰‰r‰n
     * @param osasto mink‰ osaston tuotteita etsit‰‰n
     * @return sopivien tuotteiden lukum‰‰r‰n
     * @example
     * <pre name="test">
     * Kassa kassa = new Kassa();
     * Osasto o = new Osasto(); 
     * kassa.annaTuotteetLkm(o) === 0;                         // perustesti ennen mit‰‰n
     * Tuote tuote = new Tuote(); 
     * kassa.annaTuotteetLkm(o) === 0;       
     * tuote = new Tuote("1", "kukka", "luokka", 0.95);        // lis‰t‰‰n tuote
     * kassa.lisaa(tuote);
     * o = new Osasto("luokka", "luokkaselite");                // lis‰t‰‰n osasto
     * kassa.annaTuotteetLkm(o) === 1;                          // osastolla on yksi tuote
     * Tuote toineTuote = new Tuote("12", "kukka", "luokka", 0.95);  // lis‰t‰‰n toinen tuote
     * kassa.lisaa(toineTuote);                                
     * kassa.annaTuotteetLkm(o) === 2;                          // osastolla on kaksi tuotetta
     * Osasto toinenOsasto = new Osasto();                        
     * kassa.annaTuotteetLkm(toinenOsasto) === 0;               // lopputesti viel‰ uudella, tyhj‰ll‰ osastolla 
     * </pre>
     */
    public int annaTuotteetLkm(Osasto osasto) {
        int oikeaSumma = 0;
        int lkm = tuotteet.getLkm();
        for(int i = 1; i<=lkm; i++){
            if(tuotteet.anna(i).getLuokka().toString().equals(osasto.getOsastonNimi())) {
                oikeaSumma++;
            }
        }
        return oikeaSumma; 
    }
    /**
     * Montako osastoa on olemassa
     * @return osastojen lkm
     * @example
     * <pre name="test">
     * Kassa kassa = new Kassa();
     * Osasto o = new Osasto();
     * kassa.OsastojenLkm() === 0;
     * kassa.lisaa(o);
     * kassa.OsastojenLkm() === 1; 
     * </pre>
     */
    public int OsastojenLkm() {
        return osastot.getLkm();
    }
    /**
     * Palautetaan tuotteiden lkm
     * @return tuotteiden lkm
     */
    public int tuotteidenLkm() {
        return tuotteet.getLkm();
    }

    /**
     * Etsii osastoa sen nimell‰
     * @param osastonNimi
     * @return osaston tai null jos ei lˆydy
     */
    public Osasto etsiOsastoa(String osastonNimi) {
        for(int i = 1; i<=osastot.getLkm(); i++){
            if(osastot.anna(i).getOsastonNimi().equals(osastonNimi)) return osastot.anna(i);
        }
        return null;
    }

    /**
     * Etsii tuotteen jolla on parametrina annettu nimi
     * @param nimi
     * @return tuotteen tai null
     */
    public Tuote etsiTuoteNimenMukaan(String nimi) {

        return tuotteet.etsiNimi(nimi);
    }

    /**
     * Etsit‰‰n tuote jolla on annettu viivakoodi
     * @param koodi
     * @return tuote tai null jos ei lˆydy
     * 
     */
    public Tuote annaTuoteJollaViivakoodi(String koodi) {

        return tuotteet.etsiTuoteJollaViivakoodi(koodi); 
    }



    /**
     * Lis‰t‰‰n myytyj‰
     * @param array lista viivakoodeista
     */
    public void lisaaMyytyja(ArrayList<String> array) {
        tuotteet.lisaaMyytyja(array);

    }


    /**
     * @param tiedostoTuotteet2
     * @param tiedostoOsasto
     */
    public void asetaOsastot(String tiedostoTuotteet2, String tiedostoOsasto) {
        tiedostoTuotteet = tiedostoTuotteet2; 
        tiedostoOsastot = tiedostoOsasto; 

    }


}






