package wbkassa;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * @author Ronja Lindholm
 * @vrsion 15.2.2015
 */
public class EditPanelPerus extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField textField;
    private JLabel lblNewLabel;
   
    /**
     * Create the panel.
     */
    public EditPanelPerus() {

        setLayout(new BorderLayout(0, 0));
        lblNewLabel = new JLabel("New label");
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblNewLabel, BorderLayout.NORTH);
        textField = new JTextField();
        add(textField, BorderLayout.CENTER);
        textField.setColumns(10);
    }
    //-----------------------------------------------------------------------------------------------------------------------------
    //oma koodi
    //-----------------------------------------------------------------------------------------------------------------------------
    /**
     * Palautetaan tekstikentän teksti
     * @return teksti
     */
    public String getText() {
        return textField.getText();
    }
    
    
    /**
     * Asetetaan teksti
     * @param text_3 aseta teksti
     */
    public void setText(String text_3) {
        lblNewLabel.setText(text_3);
    }


    /**
     * Asetetaan teksti
     * @param teksti
     */
    public void setTextField(String teksti) {
        textField.setText(teksti);
    }
    
    /**
     * Palauta teksti
     * @return teksti
     */
    public JTextField getTextField() {
        return textField;
    }
}