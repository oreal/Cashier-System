package wbkassa;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kassaSwing.KassaSwing;


/**
 * @author Ronja Lindholm
 * @version 15.2.2015
 */
public class KoodiPanel extends JPanel {

    /**
     * Viite luokkaan kassaSwing
     */
    protected final KassaSwing kassaSwing; 
    private static final long serialVersionUID = 1L;
    /**
     * Koodipanelin teksti
     */
    private final JTextField textKoodi = new JTextField(); 
    private JLabel lblNewLabel;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private Component rigidArea;
    private Button button;
    private Button button_1;
    private JLabel lblNewLabel_1;


    /**
     * Create the panel.
     */
    public KoodiPanel() {
        kassaSwing = new KassaSwing(); 
        setLayout(new BorderLayout(0, 0));
        lblNewLabel = new JLabel("Viivakoodi");
        lblNewLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        add(lblNewLabel, BorderLayout.NORTH);
        textKoodi.setName("textKoodi");
        textKoodi.setFont(new Font("Tahoma", Font.PLAIN, 13));
        textKoodi.setMaximumSize(new Dimension(40, 20));
        textKoodi.setPreferredSize(new Dimension(6, 22));
        add(textKoodi, BorderLayout.CENTER);
        textKoodi.setColumns(10);

        panel = new JPanel();
        add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel_1 = new JPanel();
        panel.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));

        rigidArea = Box.createRigidArea(new Dimension(117, 23));
        panel_1.add(rigidArea, BorderLayout.WEST);

        button = new Button("Info");
        button.setBackground(SystemColor.menu);
        panel_1.add(button, BorderLayout.CENTER);

        button_1 = new Button("    Poista     ");
        button_1.setBackground(SystemColor.menu);
        panel_1.add(button_1, BorderLayout.EAST);

        panel_2 = new JPanel();
        panel.add(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

        lblNewLabel_1 = new JLabel("");
        add(lblNewLabel_1, BorderLayout.EAST);

    }
    //-----------------------------------------------------------------------------------------------------------------------------
    //oma koodi
    //-----------------------------------------------------------------------------------------------------------------------------



    /**
     * @return teksti
     */
    public String getLblNewLabelText() {
        return lblNewLabel.getText();
    }
    /**
     * @param text mikä teksti laitetaan
     */
    public void setLblNewLabelText(String text) {
        lblNewLabel.setText(text);
    }

    /**
     * Palauttaa kooditekstin
     * @return koodiPanelin teksti
     */
    public String getKoodiText() {
        return textKoodi.getText();
    }

    /**
     * Asetetaan teksti koodipaneliin
     * @param teksti teksti joka laitetaan
     */
    public void setKoodiText(String teksti) {
        textKoodi.setText(teksti);

    }
    
    /**
     * Palauttaa infopainikkeen
     * @return infopainike
     */
    public Button getButtonInfo() {
        return button;
    }
    /**
     * Palauttaa poistopainikkeen
     * @return poistopainike
     */
    public Button getButtonPoista() {
        return button_1;
    }

    /**
     * Palautetaan tekstikentän teksti
     * @return käyttäjän teksti
     */
    public JTextField getTextKoodi() {
        return textKoodi;
    }
}
