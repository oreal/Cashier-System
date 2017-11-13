package kassa; 
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * @author Ronja
 * @24.2.2015
 */

public class Tuote {
    private String koodi = "";
    private String nimi = "Tuote"; 
    private String osasto = "";
    private int tunnusNro; 
    private int pikakoodi; 
    private double hinta = 0; 
    private static int seuraavaNro = 1; 
    private int myydytLkm = 0; 


    /**
     * Alustetaan tuotteen luokka
     */
    public Tuote() {
    }
    /**
     * @param viivakoodi
     * @param nimi2
     * @param luokka2
     * @param hinta2
     * @example
     * <pre name="test">
     * Tuote tuote = new Tuote("12", "nimi", "luokka", 2.0);
     * tuote.getNimi() === "nimi";
     * tuote.getKoodi() === "12";
     * tuote.getLuokka() === "luokka";
     * tuote.getHinta() ~~~2.0; 
     * Tuote tuotet = new Tuote("", "", "", 0.0);
     * tuotet.getNimi() === "";
     * tuotet.getKoodi() === "";
     * tuotet.getLuokka() === "";
     * tuotet.getHinta() ~~~ 0;
     * Tuote tuotett = new Tuote();
     * tuotett.setKoodi("1");
     * tuotett.getKoodi() === "1";
     * tuotett.setHinta(5.0);
     * tuotett.getHinta() ~~~ 5;
     * tuotett.getMyydyt() ~~~0;
     * </pre>
     */
    public Tuote(String viivakoodi, String nimi2, String luokka2, Double hinta2) {
        this.koodi = viivakoodi; 
        this.nimi = nimi2;
        this.osasto = luokka2;
        this.pikakoodi = RandomNumero(); 
        this.hinta = hinta2;
        this.myydytLkm = 0;
    }
    
    /**
     * Alustetaan tuote pikakoodin kanssa
     * @param viivakoodi
     * @param nimi2
     * @param luokka2
     * @param pikakoodit
     * @param hinta2
     */
    public Tuote(String viivakoodi, String nimi2, String luokka2, int pikakoodit, Double hinta2) {
        this.koodi = viivakoodi; 
        this.nimi = nimi2;
        this.osasto = luokka2;
        this.pikakoodi = pikakoodit; 
        this.hinta = hinta2;
        this.myydytLkm = 0;
    }
    /**
     * Testataan p‰‰ohjelmalla
     * @param args
     */
    public static void main(String[] args) {
        Tuote pulla = new Tuote(); 
        pulla.tulosta(System.out); 
        pulla.vastaaRandomNumero(); 
        pulla.tulosta(System.out); 
        Tuote limu = new Tuote();
        limu.vastaaRandomNumero(); 
        limu.tulosta(System.out); 
    }


    /**
     * Tehd‰‰n tuotteelle oma numero
     * @return random numeron
     *
     */
    public int RandomNumero() {
        Random rand = new Random(); 
        return rand.nextInt(500); 
    }

    /**
     * Testaamiseen tarkoitettu ohjelma joka laittaa valmiin nimen ja hinnan
     *
     */
    public void vastaaRandomNumero() {
        nimi = "pulla";
        pikakoodi = RandomNumero(); 
        hinta = 2.5;  
    }

    /**
     * Rekisterˆid‰‰n tuote ja luodaan sille oma tunnusNro
     * @return tunnusNro
     */
    public int rekisteroi(){
        tunnusNro = seuraavaNro; 
        seuraavaNro ++; 
        return seuraavaNro; 
    }

    /**
     * Tulosta tuotteen perustiedot
     * @param out
     */
    public void tulosta(PrintStream out) {

        out.println(nimi + " " + hinta + " " + osasto);
    }
    
    public String toString() {
        return koodi+"|"+nimi+"|"+osasto+"|"+pikakoodi+"|"+hinta+"|"+myydytLkm+"|";
    }
   
   
    /**
     * Palautetaan tuotteen luontilauseke tietokannalle
     * @return luontilauseke tietokannalle
     */
    public String annaLuontilauseke() {
        return "CREATE TABLE Tuotteet (" +
        "koodi VARCHAR(13) PRIMARY KEY ," +
        "nimi  VARCHAR (100) NOT NULL," +
        "osasto VARCHAR (3) NOT NULL," +
        "pikakoodi INTEGER," + 
        "hinta DECIMAL (10,2)," + 
        "myydytLkm INTEGER DEFAULT '0' )";
    }
    
    /**
     * Palauttaa tuotteen lis‰yslausekkeen
     * @param con
     * @return lis‰yslauseke
     * @throws SQLException
     */
    public PreparedStatement annaLisayslauseke(Connection con) throws SQLException {
        
        PreparedStatement sql = con.prepareStatement("INSERT INTO Tuotteet" +
        "(koodi, nimi, osasto, pikakoodi, hinta, myydytLkm) " + 
                "VALUES (?,?,?,?,?,?)");
        
        sql.setString(1,  koodi);
        sql.setString(2,  nimi);
        sql.setString(3,  osasto);
        sql.setInt(4,  pikakoodi);
        sql.setDouble(5,  hinta);
        sql.setInt(6,  myydytLkm);
        
        return sql;
        
    }
    /**
     * V‰hent‰‰ tuotteen myytyjen lkm yhdell‰
     * @example
     * <pre name="test">
     * Tuote tuote = new Tuote(); 
     * tuote.getMyydyt() === 0; 
     * tuote.lisaaMyytyja(); 
     * tuote.getMyydyt() === 1; 
     * tuote.lisaaMyytyja(); 
     * tuote.lisaaMyytyja(); 
     * tuote.getMyydyt() === 3;
     * tuote.vahennaMyytyja(); 
     * tuote.getMyydyt() === 2;
     * tuote.vahennaMyytyja(); 
     * tuote.getMyydyt() === 1;
     * tuote.vahennaMyytyja(); tuote.vahennaMyytyja(); tuote.vahennaMyytyja(); 
     * tuote.getMyydyt() === 0; 
     * </pre>
     */
    public void vahennaMyytyja() {

        if(this.myydytLkm > 0) this.myydytLkm--; 
    }
    
    /**
     * Lis‰t‰‰n tuotteen myytyjen lkm yhdell‰
     */
    public void lisaaMyytyja() {

        this.myydytLkm++;

    }
   
   //-----------------------------------------GETTERS AND SETTERS--------------------------------------------------------

    /**
     * Asetetaan nimi
     * @param string
     */
    public void setNimi(String string) {
        nimi = string; 
        
    }
    
    /**
     * Asetetaan myydyt
     * @param int1
     */
    public void setMyydyt(int int1) {
        myydytLkm = int1;
        
    }
    
    /**
     * Palauttaa tuotteen myytyjen tuotteiden lukum‰‰r‰n
     * @return myytyjen lukum‰‰r‰
     */
    public int getMyydyt() {
        return myydytLkm;

    }
    /**
     * Palauttaa tuotteen luokan
     * @return tuotteen osasto
     */
    public String getLuokka() {
        return osasto;
    }
    
    /**
     * Aseta luokka
     * @param luokka
     */
    public void setLuokka(String luokka) {
        this.osasto = luokka;
    }

    /**
     * Palauttaa tuotteen hinnan
     * @return tuotteen hinta
     */
    public double getHinta() {
        return hinta;
    }
    
    /**
     * Palauttaa tuotteen hinnan
     * @param luku
     */
    public void setHinta(Double luku) {
        hinta +=luku;

    }
    
    /**
     * Palauta tuotteen viivakoodi
     * @return tuotteen viivakoodi
     */
    public String getKoodi() {
        return koodi;
    }
    
    /**
     * Aseta tuotteen koodi
     * @param koodi
     */
    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }
    
    /**
     * Palauttaa pikakoodin
     * @return tuotteen pikakoodi
     */
    public int getPikakoodi() {
        return pikakoodi;
    }
    
    /**
     * Asteta pikakoodi
     * @param pikakoodi
     */
    public void setPikakoodi(int pikakoodi) {
        this.pikakoodi = pikakoodi;
    }
    
    /**
     * Palauta tunnusnumero
     * @return tuotteen tunnusnumero
     */
    public int getTunnusNro(){
        return tunnusNro; 

    }

    /**
     * Palauta tuotteen nimi
     * @return tuotteen nimi
     */
    public String getNimi(){
        return nimi; 

    }
}