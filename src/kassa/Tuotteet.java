package kassa;

import static kanta.Kanta.alustaKanta;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kanta.Kanta;

/**
 * -osaa lisätä ja poistaa tuotteen  
 * -lukee ja kirjoittaa tuotteiston tiedostoon   
 * -osaa etsiä
 * @author Ronja
 * @version 24.2.2015
 *
 */
public class Tuotteet {
    
    private Kanta kanta; 
    private static Tuote atuote = new Tuote();

    
    /**
     * Alustestetaan Tuote-luokka 
     * @param nimi tietokannan nimi
     */
    public Tuotteet(String nimi){
        kanta = alustaKanta(nimi);
        try (Connection con = kanta.annaKantayhteys()) {
            DatabaseMetaData meta = con.getMetaData();
            
            try (ResultSet tulos = meta.getTables(null,  null, "Tuotteet", null)) {  // onko taulua
                if(!tulos.next() ) {
                    try ( PreparedStatement sql = con.prepareStatement(atuote.annaLuontilauseke())) { 
                            sql.execute();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.print("Ongelmia tietokannan kanssa");
        }
       
    }

    /**
     * Alustetaan tuotteet ilman parametreja
     */
    public Tuotteet() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Pääohjelma
     * @param args ei käytössa
     * @throws SailoException 
     */
   
    public static void main(String[] args) throws SailoException {
       new File("tuotteet").delete();
         Tuotteet tuotteet = new Tuotteet("tuotteet");

        Tuote aku = new Tuote("1234567856789", "kukka", "I", 7.0);
        Tuote aku2 = new Tuote("1234567856799", "lapio", "I", 7.0);
        System.out.println(aku.getHinta());
       System.out.print(aku.getKoodi());
        System.out.println(tuotteet.getLkm());
        tuotteet.lisaa(aku);
        tuotteet.lisaa(aku2);
        aku.tulosta(System.out);
        aku2.tulosta(System.out);
      
            for(int i = 0; i < tuotteet.getLkm(); i++) {
                System.out.println(tuotteet.anna(i).toString());
                
            }
        
    }
    
    /**
     * Lisää uuden tuotteen tietorakenteeseen
     * @param tuote mikä tuote yritetään lisätä
     * @return true jos onnistui false jos ei
     * @throws SailoException 
     * //HUOM! TESTIT KÄYTTÄÄ SAMAA TIETOKANTAA KUIN OHJELMA ITSE JA TYHJENTÄÄ SEN 
     * @example
     * <pre name="test">
     * #import java.io.File; 
     * new File("tuotteet.db").delete();
     * Tuotteet tuotteet = new Tuotteet("tuotteet"); 
     * Tuote kukka = new Tuote("1234567856789", "kukka", "I", 9, 7.0);
     * Tuote lapio = new Tuote("1234567856799", "lapio", "I", 8, 7.0);
     * tuotteet.lisaa(kukka);
     * tuotteet.lisaa(lapio);
     * kukka.toString() === "1234567856789|kukka|I|9|7.0|0|";   
     * tuotteet.getLkm() === 2; 
     * new File("tuotteet.db").delete();
     * </pre>
     */
    public Boolean lisaa(Tuote tuote)  {

     try ( Connection con = kanta.annaKantayhteys();
     PreparedStatement sql = tuote.annaLisayslauseke(con) ) {
             sql.executeUpdate();
            
     } catch (SQLException e) {
         return false;
        }
      return true; 
     }
    
     

    /**
     * Poistaa tuotteen tiedostosta
     * @param tuote poistettava tuote
     * @param tiedosto mistä tiedostosta poistetaan
     */
    protected void poista(Tuote tuote)  {
        try ( Connection con = kanta.annaKantayhteys();
                PreparedStatement st = con.prepareStatement("DELETE FROM tuotteet WHERE koodi like ?")) {
        st.setString(1, tuote.getKoodi());
        st.executeUpdate(); 
        } catch (SQLException e){
            System.out.print("Ongelmia tietokannan kanssaa");
        }
    }

    /**
     * Lisää myytyjä yhdellä
     * @param array
     * @example
     * <pre name="test">
     * #import java.io.File; 
     * #import java.util.ArrayList;
     * new File("tuotteet.db").delete();
     * ArrayList<String> lista = new ArrayList<String>(); 
     * Tuotteet tuotteet = new Tuotteet("tuotteet"); 
     * Tuote kukka = new Tuote("1234567856789", "kukka", "I", 9, 7.0);
     * tuotteet.lisaa(kukka);
     * lista.add(kukka.getKoodi()); 
     * kukka.toString() === "1234567856789|kukka|I|9|7.0|0|";   
     * tuotteet.lisaaMyytyja(lista); 
     * kukka.toString() === "1234567856789|kukka|I|9|7.0|0|"; 
     * kukka.getMyydyt() === 0; 
     * </pre>
     */
    public void lisaaMyytyja(ArrayList<String> array) {
        for(String a : array){
            try ( Connection con = kanta.annaKantayhteys();
                    PreparedStatement st = con.prepareStatement("UPDATE TUOTTEET set myydytlkm = (myydytlkm +1) WHERE koodi LIKE ?;")) {
                    st.setString(1, a.toString());
                    st.executeUpdate(); 
                }
        
        catch (SQLException e){
                    System.out.print("Ongelmia tietokannan kanssaa");
                
        } 
        }
    }

  
    /**
     * Haetaan rivien lkm
     * @return rivien lkm
     */
    public int getLkm() {
        int rowcount = 0;
        try ( Connection con = kanta.annaKantayhteys();
                PreparedStatement sql = con.prepareStatement("SELECT COUNT(*) FROM Tuotteet;")) {
                        
                    @SuppressWarnings("resource")
                    ResultSet tulos =  sql.executeQuery();
                    rowcount = tulos.getInt(1); 
        } catch (SQLException e){
            System.out.print("Ongelmia tietokannan kanssaa");
        }

        return rowcount;
                 
    }

    /**
     * @param i
     * @return null
     * @example
     * <pre name="test">
     * #import java.io.File; 
     * #import java.util.ArrayList;
     * new File("tuotteet.db").delete();
     * Tuotteet tuotteet = new Tuotteet("tuotteet"); 
     * Tuote kukka1 = new Tuote("1234567856789", "kukka1", "I", 9, 7.0);
     * Tuote kukka2 = new Tuote("1234567856787", "kukka2", "I", 9, 7.0);
     * Tuote kukka3 = new Tuote("1234567856784", "kukka3", "I", 9, 7.0);
     * tuotteet.lisaa(kukka1);
     * tuotteet.lisaa(kukka2);
     * tuotteet.lisaa(kukka3);
     * Tuote b = tuotteet.anna(2); 
     * b.toString() === "1234567856787|kukka2|I|9|7.0|0|"; 
     * new File("tuotteet.db").delete();
     * </pre>
     */
    public Tuote anna(int i) {
        Tuote a = new Tuote(); 
        try ( Connection con = kanta.annaKantayhteys();
                PreparedStatement sql = con.prepareStatement("SELECT * FROM Tuotteet LIMIT 1 OFFSET ?")) {
                 sql.setInt(1, i-1);
                 
                 @SuppressWarnings("resource")
                ResultSet tulos = sql.executeQuery();
            
              try{ a.setKoodi(tulos.getString(1));
                a.setNimi(tulos.getString(2));
                a.setLuokka(tulos.getString(3)); 
                a.setPikakoodi(tulos.getInt(4));
                a.setHinta(tulos.getDouble(5));
                a.setMyydyt(tulos.getInt(6)); } catch (NullPointerException e){
                    System.out.print("Tuotetta ei löydy");
                }

        } catch (SQLException e){
            return null; 
        }
        return a;

    }

    /**
     * Palautetaan tuotteen hinta
     * @param merkkijono viivakoodi
     * @return hinta
     */
    public Double etsiKoodinHinta(String merkkijono) {
        Double a = 0.0; 
        try ( Connection con = kanta.annaKantayhteys();
                PreparedStatement sql = con.prepareStatement("SELECT hinta FROM Tuotteet WHERE koodi like ?")) {
                 sql.setString(1, merkkijono);
                 
                @SuppressWarnings("resource")
                ResultSet tulos = sql.executeQuery(); 
                a = tulos.getDouble(1); 
                
               
        } catch (SQLException e){
           //
        }
        return a; 
    }


    /**
     * Palautetaan tuote jolla on annettu viivakoodi
     * @param koodi viivakoodi
     * @return tuote
     */
    public Tuote etsiTuoteJollaViivakoodi(String koodi) {
        Tuote a = new Tuote(); 
        try ( Connection con = kanta.annaKantayhteys();
                PreparedStatement sql = con.prepareStatement("SELECT * FROM Tuotteet WHERE koodi like ?")) {
                 sql.setString(1, koodi);
                 
                 @SuppressWarnings("resource")
                ResultSet tulos = sql.executeQuery();
            
              try{ a.setKoodi(tulos.getString(1));
                a.setNimi(tulos.getString(2));
                a.setLuokka(tulos.getString(3)); 
                a.setPikakoodi(tulos.getInt(4));
                a.setHinta(tulos.getDouble(5));
                a.setMyydyt(tulos.getInt(6)); } catch (NullPointerException e){
                    System.out.print("Tuotetta ei löydy");
                }

        } catch (SQLException e){
            return null; 
        }
        return a;
    }
    
    /**
     * Etsitään nimen mukaisesti tuote
     * @param nimi
     * @return tuote jolla nimi
     */
    public Tuote etsiNimi(String nimi) {
        Tuote a = new Tuote(); 
        try ( Connection con = kanta.annaKantayhteys();
                PreparedStatement sql = con.prepareStatement("SELECT * FROM Tuotteet WHERE nimi like ?")) {
                 sql.setString(1, nimi);
                 
                 @SuppressWarnings("resource")
                ResultSet tulos = sql.executeQuery();
            
              try{ a.setKoodi(tulos.getString(1));
                a.setNimi(tulos.getString(2));
                a.setLuokka(tulos.getString(3)); 
                a.setPikakoodi(tulos.getInt(4));
                a.setHinta(tulos.getDouble(5));
                a.setMyydyt(tulos.getInt(6)); } catch (NullPointerException e){
                    System.out.print("Tuotetta ei löydy");
                }

        } catch (SQLException e){
            return null; 
        }
        return a;
    }

    

   
}

