package kassa;
import static kanta.Kanta.alustaKanta;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kanta.Kanta;
/**
 * Osastot-luokka
 * @author Ronja Lindholm
 * 15.3.2015
 *
 */

public class Osastot{

    private Kanta kanta; 
    private static Osasto aOsasto = new Osasto();

    
    
    /**
     * Pääohjelma testeihin
     * @param args ei käytössä
     */
    public static void main(String[] args){
        
        Osastot osastot = new Osastot("osastot"); 
        Osasto a = new Osasto("a", "selite");
        osastot.lisaa(a);
            for(int i = 1; i <= osastot.getLkm(); i++) {
                System.out.println(osastot.anna(i).toString());
            }
            System.out.print(osastot.getLkm());
    }


    /**
     * Alustestetaan Tuote-luokka 
     * @param nimi tietokannan nimi
     */
    public Osastot(String nimi){
        kanta = alustaKanta(nimi);
        try (Connection con = kanta.annaKantayhteys()) {
            DatabaseMetaData meta = con.getMetaData();

            try (ResultSet tulos = meta.getTables(null,  null, "Osastot", null)) {  // onko taulua
                if(!tulos.next() ) {
                    try ( PreparedStatement sql = con.prepareStatement(aOsasto.annaLuontilauseke())) { 
                        sql.execute();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.print("Ongelmia tietokannan kanssa");
        }

    }

    /**
     * Lisätään Uusi osasto
     * @param osasto
     * @return -1 jos ei onnistu
     * @example
     * <pre name="test">
     * Osastot osastot = new Osastot("osastot1");
     * Osasto osasto1 = new Osasto("osasto1", "osaston 1 juttuja");
     * osastot.lisaa(osasto1);
     * osastot.getLkm() === 1; 
     * Osasto osasto2 = new Osasto();
     * osastot.lisaa(osasto2);
     * osastot.etsi("osasto1") === "osasto1"; 
     * osastot.etsi("osasto2") === "";
     * osastot.getLkm() === 2; 
     * Osasto osasto3 = new Osasto("o", "ooo");
     * osastot.lisaa(osasto3);
     * osastot.getLkm() === 3; 
     * </pre>
     */
    public int lisaa(Osasto osasto){

        try ( Connection con = kanta.annaKantayhteys();
                PreparedStatement sql = osasto.annaLisayslauseke(con) ) {
            sql.executeUpdate();

        } catch (SQLException e) {
            return -1;
        }
        return 1; 

    }

    /**
     * Poistaa osaston osastot-listasta
     * @param osasto mikä osasto poistetaan
     * @example
     * <pre name="test">
     * Osastot osastot = new Osastot("osastot2");
     * osastot.etsi("kukka") === "";
     * Osasto osasto = new Osasto("kukka", "kukkajutut"); 
     * osastot.lisaa(osasto);
     * osastot.etsi("kukka") === "kukka";
     * osastot.poista(osasto); 
     * osastot.etsi("kukka") === "";
     * </pre>
     */
    public void poista(Osasto osasto) {
        try ( Connection con = kanta.annaKantayhteys();
                PreparedStatement st = con.prepareStatement("DELETE FROM Osastot WHERE osastoNimi like ?")) {
            st.setString(1, osasto.getOsastonNimi());
            st.executeUpdate(); 
        } catch (SQLException e){
            System.out.print("Ongelmia tietokannan kanssaa");
        }
    }


    /**
     * Etsitään osastoa sen nimellä, ja palautetaan sen nimi jos se löytyy
     * @param osasto
     * @return Osaston nimi tai "" jos ei löydy
     * @example
     * <pre name="test">
     * Osastot a = new Osastot("osastot3");
     * a.etsi("kukka") === ""; 
     * Osasto kukat = new Osasto("Ku", "kukkajuttuja");
     * a.etsi("Ku") === "";
     * a.lisaa(kukat) === 1; 
     * a.etsi("Ku") === "Ku";
     * a.etsi("ku") === "Ku";
     * Osasto leivät = new Osasto("maalaisleipa", "osastoSelite"); 
     * a.lisaa(leivät) === 1; 
     * a.etsi("maalaisleipa") === "maalaisleipa"; 
     * a.etsi("Ku") === "Ku"; 
     * </pre>
     */
    public String etsi(String osasto){
        String a = ""; 
        try ( Connection con = kanta.annaKantayhteys();
                PreparedStatement sql = con.prepareStatement("SELECT osastoNimi FROM Osastot WHERE osastoNimi like ?")) {
            sql.setString(1, osasto);

            @SuppressWarnings("resource")
            ResultSet tulos = sql.executeQuery();

            a = tulos.getString(1);



        } catch (SQLException e){
            return ""; 
        }
        return a; 
    }
    /**
     * Haetaan osasto paikasta i
     * @param i osaston indeksi
     * @return osasto paikassa i
     * @example
     * <pre name="test">
     * Osastot osastot = new Osastot("osastot4");
     * Osasto uusiOsasto = new Osasto("hula", "hula-tuotteet"); 
     * osastot.lisaa(uusiOsasto) === 1;
     * osastot.anna(0).toString() === ("hula|hula-tuotteet|");
     * </pre>
     */
    public Osasto anna(int i) {
        Osasto a = new  Osasto(); 
        try ( Connection con = kanta.annaKantayhteys();
                PreparedStatement sql = con.prepareStatement("SELECT * FROM Osastot LIMIT 1 OFFSET ?")) {
            sql.setInt(1, i-1);

            @SuppressWarnings("resource")
            ResultSet tulos = sql.executeQuery();

            try{ a.setOsastonNimi(tulos.getString(1));
            a.setOsastonSelite(tulos.getString(2));
            } catch (NullPointerException e){
                System.out.print("Tuotetta ei löydy");
            }

        } catch (SQLException e){
            return null; 
        }
        return a;
    }
    /**
     * Montako osastoa on
     * @return osastojen lkm
     * <pre name="test">
     * Osastot osastot = new Osastot("osastot5");
     * osastot.getLkm() === 0; 
     * Osasto uusiOsasto = new Osasto("hula", "hula-tuotteet"); 
     * osastot.lisaa(uusiOsasto) === 1;
     * osastot.getLkm() === 1; 
     * Osasto tO = new Osasto("l1", "vähän kaikkee"); 
     * osastot.getLkm() === 1; 
     * osastot.lisaa(tO); 
     * osastot.getLkm() ===2; 
     * </pre>
     */
    public int getLkm() {
        int rowcount = 0;
        try ( Connection con = kanta.annaKantayhteys();
                PreparedStatement sql = con.prepareStatement("SELECT COUNT(*) FROM Osastot;")) {

            @SuppressWarnings("resource")
            ResultSet tulos =  sql.executeQuery();
            rowcount = tulos.getInt(1); 
        } catch (SQLException e){
            System.out.print("Ongelmia tietokannan kanssaa");
        }

        return rowcount;
        
        

                 

    }

}
