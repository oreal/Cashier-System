package wbkassa;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
/**
 * @author Ronja Lindholm
 * @version 15.2.2015
 */
public class editPaneltupla extends JPanel {
        /**
     *
     */
    private static final long serialVersionUID = 1L;
    private JTextField textField;
        private final JLabel label = new JLabel("New label");
        private JTextField textField_1;
        /**
         * Create the panel.
         */
        public editPaneltupla() {
                        setLayout(new BorderLayout(0, 0));
                       
                        JSplitPane splitPane = new JSplitPane();
                        splitPane.setResizeWeight(0.5);
                        add(splitPane, BorderLayout.CENTER);
                       
                        JPanel panel = new JPanel();
                        splitPane.setLeftComponent(panel);
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                       
                        JLabel lblNewLabel = new JLabel("New label");
                        panel.add(lblNewLabel);
                       
                        textField = new JTextField();
                        panel.add(textField);
                        textField.setColumns(10);
                       
                        JPanel panel_1 = new JPanel();
                        splitPane.setRightComponent(panel_1);
                        panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
                        panel_1.add(label);
                       
                        textField_1 = new JTextField();
                        panel_1.add(textField_1);
                        textField_1.setColumns(10);
        }
       
}