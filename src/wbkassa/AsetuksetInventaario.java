package wbkassa;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * @author Ronja
 *
 */
public class AsetuksetInventaario extends JDialog {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private Inventaario inventaario;
    private EditPanelPerus tuotteidenTiedostoKentta;
    private EditPanelPerus osastojenTiedostoKentta;
    private EditPanelPerus kayttajanimiKentta;

    /**
     * Launch the application.
     * @param args ei käytössä
     */
    public static void main(String[] args) {
      
        try {
            AsetuksetInventaario dialog = new AsetuksetInventaario();
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     * @param parent 
     */
    public AsetuksetInventaario() {
        setBounds(100, 100, 461, 442);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{434, 0};
        gridBagLayout.rowHeights = new int[]{332, 33, 0};
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
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            kayttajanimiKentta = new EditPanelPerus();
            kayttajanimiKentta.setText("K\u00E4ytt\u00E4j\u00E4nimi");
            GridBagConstraints gbc_editPanelPerus = new GridBagConstraints();
            gbc_editPanelPerus.insets = new Insets(0, 0, 5, 5);
            gbc_editPanelPerus.fill = GridBagConstraints.BOTH;
            gbc_editPanelPerus.gridx = 2;
            gbc_editPanelPerus.gridy = 4;
            contentPanel.add(kayttajanimiKentta, gbc_editPanelPerus);
        }
        {
            JPanel panel_1 = new JPanel();
            GridBagConstraints gbc_panel_1 = new GridBagConstraints();
            gbc_panel_1.gridheight = 4;
            gbc_panel_1.insets = new Insets(0, 0, 5, 0);
            gbc_panel_1.fill = GridBagConstraints.VERTICAL;
            gbc_panel_1.gridx = 3;
            gbc_panel_1.gridy = 4;
            contentPanel.add(panel_1, gbc_panel_1);
            panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
            {
                JRadioButton rdbtnNewRadioButton = new JRadioButton("default");
                rdbtnNewRadioButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        asetaDefault(); 
                    }
                });
                panel_1.add(rdbtnNewRadioButton);
            }
            {
                JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
                panel_1.add(rdbtnNewRadioButton_1);
            }
            {
                JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("New radio button");
                panel_1.add(rdbtnNewRadioButton_2);
            }
            {
                JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("New radio button");
                panel_1.add(rdbtnNewRadioButton_3);
            }
            {
                JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("New radio button");
                panel_1.add(rdbtnNewRadioButton_4);
            }
        }
        {
            tuotteidenTiedostoKentta = new EditPanelPerus();
            tuotteidenTiedostoKentta.setText("Tuotteiden tiedostonimi");
            GridBagConstraints gbc_editPanelPerus = new GridBagConstraints();
            gbc_editPanelPerus.insets = new Insets(0, 0, 5, 5);
            gbc_editPanelPerus.fill = GridBagConstraints.BOTH;
            gbc_editPanelPerus.gridx = 2;
            gbc_editPanelPerus.gridy = 5;
            contentPanel.add(tuotteidenTiedostoKentta, gbc_editPanelPerus);
        }
        {
            osastojenTiedostoKentta = new EditPanelPerus();
            osastojenTiedostoKentta.setText("Osastojen tiedostonimi");
            GridBagConstraints gbc_editPanelPerus_1 = new GridBagConstraints();
            gbc_editPanelPerus_1.fill = GridBagConstraints.HORIZONTAL;
            gbc_editPanelPerus_1.insets = new Insets(0, 0, 5, 5);
            gbc_editPanelPerus_1.gridx = 2;
            gbc_editPanelPerus_1.gridy = 6;
            contentPanel.add(osastojenTiedostoKentta, gbc_editPanelPerus_1);
        }
        {
            JButton btnTallenna = new JButton("Tallenna");
            GridBagConstraints gbc_btnTallenna = new GridBagConstraints();
            gbc_btnTallenna.insets = new Insets(0, 0, 5, 5);
            gbc_btnTallenna.gridx = 2;
            gbc_btnTallenna.gridy = 7;
            contentPanel.add(btnTallenna, gbc_btnTallenna);
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
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        setTiedostot(); 
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        
        }
    }
    

//---------------------------------------------------------------------------------------------------------------------------------------

    
    /**
     * 
     */
    protected void asetaDefault() {
        kayttajanimiKentta.getTextField().setText("default");
        tuotteidenTiedostoKentta.getTextField().setText("tuotteet");
        osastojenTiedostoKentta.getTextField().setText("osastot");
        
    }

    /**
     * lopetetaan 
     */
    protected void cancel() {
        this.dispose();
        
    }

    /**
     * asetetaan uudet tiedostot
     */
    protected void setTiedostot() {
        
       if( tuotteidenTiedostoKentta.getText().equals("") &&
        
        osastojenTiedostoKentta.getText().equals("")) {tuotteidenTiedostoKentta.getTextField().setText("tuotteet");
       osastojenTiedostoKentta.getTextField().setText("osastot");}
       else {
           String a = tuotteidenTiedostoKentta.getTextField().getText();
           String b = osastojenTiedostoKentta.getTextField().getText();
           inventaario = new Inventaario(a, b); 
           inventaario.setVisible(true);
       }    
        
        
     }
    

}

