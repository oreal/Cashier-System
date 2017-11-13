package wbkassa;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import kassaSwing.SwingAloitus;

import java.awt.Color;
/**
 * @author Ronja Lindholm
 * @version 15.2.2015
 *
 */
/*
 * EI TOIMI: aloitusikkunan idea ei toimi: avaa aina kun laittaa default
 * EI TOIMI: uusi myyjä-toiminto ei toimi. Uutta käyttäjää ei siis voi lisätä
 * KOKO IKKUNA ON TÄLLÄ HETKELLÄ TÄYSIN TURHA
 */
public class AloitusIkkuna extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField textField;
    @SuppressWarnings("javadoc")
    protected final SwingAloitus kassaSwing;
    private KassaGUI kassaGUI;
    JLabel lblEiOikeuksia;


    /**
     * Launc the application
     * @param args ei käytössä
     */  
    public static void main(String[] args) {
        try {
            AloitusIkkuna dialog = new AloitusIkkuna(null);

            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Create the dialog.
     * @param kassaSwing 
     * @param parent 
     */
    public AloitusIkkuna(Frame parent) {

        kassaSwing = new SwingAloitus(this); 
        setBounds(100, 100, 454, 298);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{434, 0};
        gridBagLayout.rowHeights = new int[]{228, 33, 0};
        gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        GridBagConstraints gbc_contentPanel = new GridBagConstraints();
        gbc_contentPanel.fill = GridBagConstraints.BOTH;
        gbc_contentPanel.insets = new Insets(0, 0, 5, 0);
        gbc_contentPanel.gridx = 0;
        gbc_contentPanel.gridy = 0;
        getContentPane().add(contentPanel, gbc_contentPanel);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 194, 46, 0};
        gbl_contentPanel.rowHeights = new int[]{14, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            Component horizontalStrut = Box.createHorizontalStrut(20);
            GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
            gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
            gbc_horizontalStrut.gridx = 0;
            gbc_horizontalStrut.gridy = 4;
            contentPanel.add(horizontalStrut, gbc_horizontalStrut);
        }
        {
            JLabel lblAvaaKassal = new JLabel("Avaa kassa");
            GridBagConstraints gbc_lblAvaaKassal = new GridBagConstraints();
            gbc_lblAvaaKassal.anchor = GridBagConstraints.WEST;
            gbc_lblAvaaKassal.insets = new Insets(7, 0, 5, 5);
            gbc_lblAvaaKassal.gridx = 1;
            gbc_lblAvaaKassal.gridy = 5;
            contentPanel.add(lblAvaaKassal, gbc_lblAvaaKassal);
        }
        {
            textField = new JTextField();
            textField.setText("default");
            textField.addKeyListener(new KeyAdapter() {
                @SuppressWarnings("synthetic-access")
                @Override
                public void keyPressed(KeyEvent e1) {
                    if(e1.getKeyCode() == KeyEvent.VK_ENTER)

                    {                    if(     textField.getText().equals("default")) {
                        kassa();       } 
                    else {lblEiOikeuksia.setVisible(true);}
                    }
                }
            });
            GridBagConstraints gbc_textField = new GridBagConstraints();
            gbc_textField.insets = new Insets(0, 0, 5, 5);
            gbc_textField.fill = GridBagConstraints.HORIZONTAL;
            gbc_textField.gridx = 1;
            gbc_textField.gridy = 6;
            contentPanel.add(textField, gbc_textField);
            textField.setColumns(10);
        }
        {
            lblEiOikeuksia = new JLabel("Ei oikeuksia");
            lblEiOikeuksia.setForeground(Color.RED);
            lblEiOikeuksia.setVisible(false);
            GridBagConstraints gbc_lblEiOikeuksia = new GridBagConstraints();
            gbc_lblEiOikeuksia.insets = new Insets(0, 0, 5, 5);
            gbc_lblEiOikeuksia.gridx = 1;
            gbc_lblEiOikeuksia.gridy = 7;
            contentPanel.add(lblEiOikeuksia, gbc_lblEiOikeuksia);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            GridBagConstraints gbc_buttonPane = new GridBagConstraints();
            gbc_buttonPane.anchor = GridBagConstraints.NORTH;
            gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
            gbc_buttonPane.gridx = 0;
            gbc_buttonPane.gridy = 1;
            getContentPane().add(buttonPane, gbc_buttonPane);
            {
                JButton cancelButton = new JButton("Uusi koodi");
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {
                        kassaSwing.uusiKayttaja(); 
                    }
                });
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
    //-----------------------------------------------------------------------------------------------------------------------------
    //oma koodi
    //-----------------------------------------------------------------------------------------------------------------------------

    /**
     * Kun käyttäjä saa koodin oikein, avataan kassa
     * ja suljetaan tämä aloitusikkuna
     */
    protected void kassa() {
        kassaGUI = new KassaGUI();
        kassaGUI.setVisible(true);
        this.dispose();
    }
}