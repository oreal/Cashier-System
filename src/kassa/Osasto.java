package kassa;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fi.jyu.mit.ohj2.Mjonot;
/**
 * @author Ronja Lindholm
 * @version 11.3.2015
 *
 */
public class Osasto {
    private String osastoNimi;
    private String osastoSelite;


    /**
     * Alustetaan osasto
     * @param osastonNimi
     * @param osastonSelite
     * @example
     * <pre name="test">
     * Osasto a = new Osasto("kissa", "kissojen juttuja");
     * Osasto b = new Osasto("1", "ekan kerroksen välineet 4.5");
     * Osasto c = new Osasto("", "Kaikki");
     * a.getOsastonNimi() === "kissa";
     * b.getOsastonNimi() === "1";
     * c.getOsastonNimi() === ""; 
     * a.getOsastonSelite() === "kissojen juttuja";
     * b.getOsastonSelite() === "ekan kerroksen välineet 4.5"; 
     * c.getOsastonSelite() === "Kaikki";
     * </pre>
     */
    public Osasto(String osastonNimi, String osastonSelite){
        this.osastoNimi = osastonNimi; 
        this.osastoSelite = osastonSelite;

    }

    /**
     * Alustetaan osasto
     */
    public Osasto(){
        this.osastoNimi = ""; 
        this.osastoSelite = "";
    }

    /**
     * Palautetaan tuotteen luontilauseke tietokannalle
     * @return luontilauseke tietokannalle
     */
    public String annaLuontilauseke() {
        return "CREATE TABLE Osastot (" +
                "osastoNimi VARCHAR (3) PRIMARY KEY," +
                "osastoSelite VARCHAR(100) )";
    }

    /**
     * Palauttaa tuotteen lisäyslausekkeen
     * @param con
     * @return lisäyslauseke
     * @throws SQLException
     */
    public PreparedStatement annaLisayslauseke(Connection con) throws SQLException {

        PreparedStatement sql = con.prepareStatement("INSERT INTO Osastot" +
                "(osastoNimi, osastoSelite) " + 
                "VALUES (?,?)");

        sql.setString(1,  osastoNimi);
        sql.setString(2,  osastoSelite);

        return sql;

    }
    
    /**
     * Tulostetaan osasto ja sen selite
     * @param os
     */
    public void tulosta(PrintStream os) {
        os.print(osastoNimi + osastoSelite);

    }
    
    /**
     * Tiedosto muodossa
     * osastonimi|osastoselite|
     * @param rivi
     */
    public void parse(String rivi) {
        StringBuffer ss = new StringBuffer(rivi); 
        osastoNimi = Mjonot.erota(ss, '|',osastoNimi); 
        osastoSelite = Mjonot.erota(ss, '|', osastoSelite); 
    }



    public String toString() {
        return osastoNimi+"|" + osastoSelite+"|";
    }

    
    //------------------------- GETTERS AND SETTERS------------------------------------------------------
    /**
     * Palautetaan osaston nimi
     * @return osaston nimi
     */
    public String getOsastonNimi(){
        return osastoNimi; 
    }

    /**
     * Palautetaan osaston nimi
     * @param teksti uusi nimi
     */
    public void setOsastonNimi(String teksti){
        osastoNimi = teksti;
    }

    /**
     * Palautetaan osasto
     * @return osaston nimi
     */
    public Osasto getOsasto(){
        return this; 
    }
    /**
     * Palautetaan osaston selite
     * @return osaston selite
     */
    public String getOsastonSelite(){
        return osastoSelite; 
    }

    /**
     * Asetetaan osastoselite
     * @param teksti uusi selite
     */
    public void setOsastonSelite(String teksti){
        osastoSelite = teksti; 
    }
  

  
}
