package wbkassa;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * @author Ronja Lindholm   
 * @version 15.2.2015
 */
public class PanelMaksu extends JPanel {
        /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField textField;
        private JLabel lblNewLabel;
        /**
         * Create the panel.
         */
        public PanelMaksu() {
                setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
               
                JLabel label_1 = new JLabel("    ");
                add(label_1);
               
                lblNewLabel = new JLabel("New label");
                add(lblNewLabel);
               
                JLabel label = new JLabel("      ");
                add(label);
               
                textField = new JTextField();
                textField.setFont(new Font("Tahoma", Font.PLAIN, 23));
                textField.setHorizontalAlignment(SwingConstants.RIGHT);
                textField.setForeground(Color.LIGHT_GRAY);
                textField.setText("00.00");
                add(textField);
                textField.setColumns(10);
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
         * @param text laitettava teksti
         */
        public void setLblNewLabelText(String text) {
                lblNewLabel.setText(text);
        }
        /**
         * @return teksti
         */
        public String getLabelText() {
                return lblNewLabel.getText();
        }
        /**
         * @param text_1 laitettava teksti
         */
        public void setLabelText(String text_1) {
                lblNewLabel.setText(text_1);
        }
        /**
         * @return teksti
         */
        public String getLlText() {
                return lblNewLabel.getText();
        }
        /**
         * @param text_2 laitettava teksti
         */
        public void setLlText(String text_2) {
                lblNewLabel.setText(text_2);
        }
    /**
     * Palauttaa maksuikkunan tiedot
     * @return maksu
     */
    public JTextField getEditPanelMaksu() {
        return textField;
    }
}
