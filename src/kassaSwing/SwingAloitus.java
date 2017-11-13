/**
 * 
 */
package kassaSwing;

import javax.swing.JOptionPane;

import wbkassa.AloitusIkkuna;

/**
 * @author Ronja
 *
 */
public class SwingAloitus {
    
    @SuppressWarnings("unused")
    private AloitusIkkuna aloitusIkkuna;


    /**
     * @param aloitusIkkuna
     */
    public SwingAloitus(AloitusIkkuna aloitusIkkuna) {
        this.aloitusIkkuna = aloitusIkkuna; 

    }
    /**
     * Lisataan uusi k‰ytt‰j‰
     */
    public void uusiKayttaja() {

        ErrorMessage("Uusi myyj‰"); 
    }


    /**
     * N‰ytet‰‰n error-message
     * @param teksti mik‰ ei toimi
     */
    public void ErrorMessage(String teksti) {
        JOptionPane.showMessageDialog(null, teksti + "\nEi toimi viel‰!");

    } 
}
