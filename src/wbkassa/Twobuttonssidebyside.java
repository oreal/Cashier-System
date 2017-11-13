package wbkassa;

import java.awt.Button;
import java.awt.Component;
import java.awt.SystemColor;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * @author Ronja Lindholm
 * @version 15.2.2015
 */
public class Twobuttonssidebyside extends JPanel {
    private static final long serialVersionUID = 1L;
    private Button button;
    private Button button_1;

    /**
     * Create the panel.
     */
    public Twobuttonssidebyside() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        button = new Button("Hyv\u00E4ksy");
        button.setBackground(SystemColor.menu);
        add(button);

        Component horizontalStrut = Box.createHorizontalStrut(20);
        add(horizontalStrut);

        button_1 = new Button("Hylk\u00E4\u00E4");
      
        button_1.setBackground(SystemColor.menu);
        add(button_1);

    }

    //-----------------------------------------------------------------------------------------------------------------------------
    //oma koodi
    //-----------------------------------------------------------------------------------------------------------------------------
    
    /**
     * Palautetaan 
     * @return teksti
     */
    public String getButtonLabel() {
        return button.getLabel();
    }
    /**
     * @param label
     */
    public void setButtonLabel(String label) {
        button.setLabel(label);
    }
    /**
     * @return teksti
     */
    public String getButton_1Label() {
        return button_1.getLabel();
    }
    /**
     * @param label_1
     */
    public void setButton_1Label(String label_1) {
        button_1.setLabel(label_1);
    }
    /**
     * Palautetaan maksupainike
     * @return maksupainike
     */
    public Button getButtonHyvaksyMaksu() {
        return button;
    }
    /**
     * Palauteteaan hylkaa-nappi
     * @return hylk‰‰-nappi
     */
    public Button getButtonHylkaaMaksu() {
        return button_1;
    }
}
