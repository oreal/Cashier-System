package wbkassa;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import kassaSwing.kassaSwingInventaario;
/**
 * @author Ronja Lindholm
 * @version 15.2.2015
 *
 *
 */
/*
 * EI TOIMI: p‰ivityksess‰ on nullpointerException jolle ei tehd‰ mit‰‰n
 * EI TOIMI: osastoja ei voi muokata
 */

public class Inventaario extends JDialog {
    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTable tableTuotelista;
    private final kassaSwingInventaario kassaSwing; 
    private EditPanelPerus panelViivakoodi;
    private EditPanelPerus panelName;
    private EditPanelPerus panelOsasto;
    private EditPanelPerus panelPikakoodi;
    private EditPanelPerus panelOsastoSelite;
    private EditPanelPerus panelHinta;

    private JLabel lisaaViivakoodi; 
    private JLabel lisaaNimi; 
    private JLabel lisaaOsasto; 
    private JLabel lisaaOsastoSelite; 
    private JLabel lisaaHinta; 
    private EditPanelPerus etsiOsastoa; 
    private EditPanelPerus editPanelhori;
    private EditPanelPerus editPanelhori_1;
    private KassaGUI kassaGUI;


    /**
     * Launch the application.
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {
        try {
            Inventaario dialog = new Inventaario(null, null); 

            dialog.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     * @param tuotteidenTiedosto 
     * @param osastonNimi 
     */
    public Inventaario(String tuotteidenTiedosto, String osastonNimi) {
        kassaSwing = new kassaSwingInventaario(this); 
        setForeground(SystemColor.activeCaption);
        setBackground(SystemColor.menu);


        JMenuBar menuBar = new JMenuBar();
        menuBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Kassa 078");
        mnNewMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                kassa(); 
            }
        });
        mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu);

        Component horizontalGlue = Box.createHorizontalGlue();
        menuBar.add(horizontalGlue);

        JMenu mnNewMenu_1 = new JMenu("Inventaario");
        mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu_1);

        JMenu mnNewMenu_2 = new JMenu("Ohjeet");
        mnNewMenu_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                ohjeet();
            }
        });
        mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu_2);

        JMenu mnNewMenu_3 = new JMenu("Kirjaudu ulos");
        mnNewMenu_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                aloitus();

            }
        });
        mnNewMenu_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
        mnNewMenu_3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu_3);


        setBounds(100, 100, 758, 724);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.9);
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                );
        gl_contentPanel.setVerticalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(splitPane, GroupLayout.PREFERRED_SIZE, 630, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

        JPanel panel = new JPanel();
        splitPane.setRightComponent(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel panel_1 = new JPanel();
        panel_1.setMaximumSize(new Dimension(32767, 20000));
        panel.add(panel_1);
        GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[]{0, 0};
        gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0};
        panel_1.setLayout(gbl_panel_1);

        panelViivakoodi = new EditPanelPerus();
        panelViivakoodi.setText("viivakoodi");
        GridBagConstraints gbc_editPanelhori_2 = new GridBagConstraints();
        gbc_editPanelhori_2.insets = new Insets(0, 0, 5, 0);
        gbc_editPanelhori_2.fill = GridBagConstraints.BOTH;
        gbc_editPanelhori_2.gridx = 0;
        gbc_editPanelhori_2.gridy = 0;
        panel_1.add(panelViivakoodi, gbc_editPanelhori_2);

        lisaaViivakoodi = new JLabel("Lis\u00E4\u00E4 viivakoodi!");
        lisaaViivakoodi.setVisible(false);
        lisaaViivakoodi.setForeground(new Color(255, 0, 0));
        panelViivakoodi.add(lisaaViivakoodi, BorderLayout.SOUTH);

        JRadioButton rdbtnNewRadioButton = new JRadioButton("Auto");
        rdbtnNewRadioButton.addActionListener(new ActionListener() {
            @SuppressWarnings("synthetic-access")
            public void actionPerformed(ActionEvent e) {
                if (rdbtnNewRadioButton.isSelected()) setAutoKoodi();
                else { panelViivakoodi.setTextField(""); }
            }
        });
        panelViivakoodi.add(rdbtnNewRadioButton, BorderLayout.EAST);

        panelName = new EditPanelPerus();
        panelName.setText("nimi");
        GridBagConstraints gbc_editPanelhori = new GridBagConstraints();
        gbc_editPanelhori.insets = new Insets(0, 0, 5, 0);
        gbc_editPanelhori.fill = GridBagConstraints.BOTH;
        gbc_editPanelhori.gridx = 0;
        gbc_editPanelhori.gridy = 1;
        panel_1.add(panelName, gbc_editPanelhori);

        lisaaNimi = new JLabel("Lis\u00E4\u00E4 nimi!");
        lisaaNimi.setVisible(false);
        lisaaNimi.setForeground(new Color(255, 0, 0));
        panelName.add(lisaaNimi, BorderLayout.SOUTH);

        panelPikakoodi = new EditPanelPerus();
        panelPikakoodi.setText("Pikakoodi");
        GridBagConstraints gbc_editPanelhori_3 = new GridBagConstraints();
        gbc_editPanelhori_3.insets = new Insets(0, 0, 5, 0);
        gbc_editPanelhori_3.fill = GridBagConstraints.BOTH;
        gbc_editPanelhori_3.gridx = 0;
        gbc_editPanelhori_3.gridy = 2;
        panel_1.add(panelPikakoodi, gbc_editPanelhori_3);

        panelHinta = new EditPanelPerus();
        panelHinta.setText("Hinta");
        GridBagConstraints gbc_editPanelhori_5 = new GridBagConstraints();
        gbc_editPanelhori_5.insets = new Insets(0, 0, 5, 0);
        gbc_editPanelhori_5.fill = GridBagConstraints.BOTH;
        gbc_editPanelhori_5.gridx = 0;
        gbc_editPanelhori_5.gridy = 3;
        panel_1.add(panelHinta, gbc_editPanelhori_5);

        lisaaHinta = new JLabel("Lis\u00E4\u00E4 hinta!");
        lisaaHinta.setVisible(false);
        lisaaHinta.setForeground(new Color(255, 0, 0));
        panelHinta.add(lisaaHinta, BorderLayout.SOUTH);

        panelOsasto = new EditPanelPerus();
        panelOsasto.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e1) {
                if(e1.getKeyCode() == KeyEvent.VK_ENTER){
                    lisaaTuoteTuotelistaanInv(); 
                }
            }
        });
        GridBagConstraints gbc_panelOsasto = new GridBagConstraints();
        gbc_panelOsasto.fill = GridBagConstraints.HORIZONTAL;
        gbc_panelOsasto.insets = new Insets(0, 0, 5, 0);
        gbc_panelOsasto.gridx = 0;
        gbc_panelOsasto.gridy = 4;
        panel_1.add(panelOsasto, gbc_panelOsasto);
        panelOsasto.setText("Osasto");

        lisaaOsasto = new JLabel("Lis\u00E4\u00E4 osasto!");
        lisaaOsasto.setVisible(false);
        lisaaOsasto.setForeground(new Color(255, 0, 0));
        panelOsasto.add(lisaaOsasto, BorderLayout.SOUTH);

        panelOsastoSelite = new EditPanelPerus();
        panelOsastoSelite.getTextField().addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e1) {
                if(e1.getKeyCode() == KeyEvent.VK_ENTER){
                    lisaaOsasto(); 
                }
            }
        });


        panelOsastoSelite.setText("Osastoselite");
        GridBagConstraints gbc_editPanelhori_4 = new GridBagConstraints();
        gbc_editPanelhori_4.insets = new Insets(0, 0, 5, 0);
        gbc_editPanelhori_4.fill = GridBagConstraints.BOTH;
        gbc_editPanelhori_4.gridx = 0;
        gbc_editPanelhori_4.gridy = 5;
        panel_1.add(panelOsastoSelite, gbc_editPanelhori_4);

        lisaaOsastoSelite = new JLabel("Lis\u00E4\u00E4 selite!");
        panelOsastoSelite.add(lisaaOsastoSelite, BorderLayout.SOUTH);
        lisaaOsastoSelite.setVisible(false);
        lisaaOsastoSelite.setForeground(new Color(255, 0, 0));

        Button lisaaOsasto_1 = new Button("Lis\u00E4\u00E4 osasto");
        lisaaOsasto_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                lisaaOsasto();
            }
        });

        JLabel lblLisTaiMuokkaa = new JLabel("Lis\u00E4\u00E4 tai muokkaa");
        lblLisTaiMuokkaa.setFont(new Font("Tahoma", Font.PLAIN, 9));
        GridBagConstraints gbc_lblLisTaiMuokkaa = new GridBagConstraints();
        gbc_lblLisTaiMuokkaa.anchor = GridBagConstraints.SOUTHWEST;
        gbc_lblLisTaiMuokkaa.insets = new Insets(0, 0, 5, 0);
        gbc_lblLisTaiMuokkaa.gridx = 0;
        gbc_lblLisTaiMuokkaa.gridy = 6;
        panel_1.add(lblLisTaiMuokkaa, gbc_lblLisTaiMuokkaa);
        GridBagConstraints gbc_lisaaOsasto_1 = new GridBagConstraints();
        gbc_lisaaOsasto_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_lisaaOsasto_1.insets = new Insets(0, 0, 5, 0);
        gbc_lisaaOsasto_1.gridx = 0;
        gbc_lisaaOsasto_1.gridy = 7;
        panel_1.add(lisaaOsasto_1, gbc_lisaaOsasto_1);

        Button lisaaTuote = new Button("Lis\u00E4\u00E4 tuote");
        lisaaTuote.setFont(new Font("Dialog", Font.BOLD, 12));
        lisaaTuote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                lisaaTuoteTuotelistaanInv();
            }
        });
        GridBagConstraints gbc_lisaaTuote = new GridBagConstraints();
        gbc_lisaaTuote.fill = GridBagConstraints.BOTH;
        gbc_lisaaTuote.insets = new Insets(0, 0, 5, 0);
        gbc_lisaaTuote.gridx = 0;
        gbc_lisaaTuote.gridy = 10;
        panel_1.add(lisaaTuote, gbc_lisaaTuote);

        Button button_5 = new Button("Poista osasto");
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                poistaOsasto();
            }

        });

        Button button_6 = new Button("Muokkaa tuotetta");
        button_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                muokkaa();  
            }
        });
        GridBagConstraints gbc_button_6 = new GridBagConstraints();
        gbc_button_6.anchor = GridBagConstraints.EAST;
        gbc_button_6.insets = new Insets(0, 0, 5, 0);
        gbc_button_6.gridx = 0;
        gbc_button_6.gridy = 11;
        panel_1.add(button_6, gbc_button_6);
        button_5.setActionCommand("");
        GridBagConstraints gbc_button_5 = new GridBagConstraints();
        gbc_button_5.fill = GridBagConstraints.HORIZONTAL;
        gbc_button_5.insets = new Insets(0, 0, 5, 0);
        gbc_button_5.gridx = 0;
        gbc_button_5.gridy = 19;
        panel_1.add(button_5, gbc_button_5);

        Label label = new Label("Valitse tuote luettelosta");
        label.setFont(new Font("Dialog", Font.PLAIN, 9));
        GridBagConstraints gbc_label = new GridBagConstraints();
        gbc_label.anchor = GridBagConstraints.SOUTHWEST;
        gbc_label.insets = new Insets(0, 0, 5, 0);
        gbc_label.gridx = 0;
        gbc_label.gridy = 21;
        panel_1.add(label, gbc_label);

        Button button_2 = new Button("Poista tuote");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                poistaTuote();
            }
        });
        button_2.setFont(new Font("Dialog", Font.BOLD, 12));
        GridBagConstraints gbc_button_2 = new GridBagConstraints();
        gbc_button_2.anchor = GridBagConstraints.NORTH;
        gbc_button_2.fill = GridBagConstraints.HORIZONTAL;
        gbc_button_2.gridx = 0;
        gbc_button_2.gridy = 22;
        panel_1.add(button_2, gbc_button_2);

        JPanel panel_2 = new JPanel();
        panel.add(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

        JPanel panel_3 = new JPanel();
        panel_3.setAlignmentY(Component.TOP_ALIGNMENT);
        splitPane.setLeftComponent(panel_3);
        GridBagLayout gbl_panel_3 = new GridBagLayout();
        gbl_panel_3.columnWidths = new int[] {10, 100, 100, 100};
        gbl_panel_3.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 30, 0, 0, 30, 0, 0, 30, 0, 0};
        gbl_panel_3.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0};
        gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 1.0, 0.0, 1.0};
        panel_3.setLayout(gbl_panel_3);

        tableTuotelista = new JTable();
        tableTuotelista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                selectRow();
            }
        });
        tableTuotelista.setFillsViewportHeight(true);
        tableTuotelista.setFont(new Font("Tahoma", Font.BOLD, 11));
        tableTuotelista.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tableTuotelista.setCellSelectionEnabled(true);
        tableTuotelista.setColumnSelectionAllowed(true);
        tableTuotelista.setToolTipText("");
        tableTuotelista.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "Viivakoodi", "Nimi", "Osasto", "Pk", "Selite", "Hinta", "Kpl", "Myydyt"
                }
                ));
        tableTuotelista.getColumnModel().getColumn(0).setPreferredWidth(100);
        tableTuotelista.getColumnModel().getColumn(0).setMinWidth(30);
        tableTuotelista.getColumnModel().getColumn(2).setPreferredWidth(47);
        tableTuotelista.getColumnModel().getColumn(3).setPreferredWidth(50);
        tableTuotelista.getColumnModel().getColumn(4).setPreferredWidth(100);
        tableTuotelista.getColumnModel().getColumn(4).setMinWidth(20);
        tableTuotelista.getColumnModel().getColumn(5).setPreferredWidth(63);
        tableTuotelista.getColumnModel().getColumn(6).setPreferredWidth(45);
        tableTuotelista.getColumnModel().getColumn(6).setMinWidth(8);
        tableTuotelista.getColumnModel().getColumn(7).setPreferredWidth(45);
        tableTuotelista.getColumnModel().getColumn(7).setMinWidth(12);

        JLabel lblEtsi = new JLabel("Etsi");
        lblEtsi.setFont(new Font("Tahoma", Font.PLAIN, 17));
        GridBagConstraints gbc_lblEtsi = new GridBagConstraints();
        gbc_lblEtsi.anchor = GridBagConstraints.WEST;
        gbc_lblEtsi.insets = new Insets(0, 0, 5, 5);
        gbc_lblEtsi.gridx = 0;
        gbc_lblEtsi.gridy = 0;
        panel_3.add(lblEtsi, gbc_lblEtsi);

        Button button = new Button("P\u00E4ivit\u00E4");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paivita(); 
            }
        });

        etsiOsastoa = new EditPanelPerus();
        etsiOsastoa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                EtsiOsastot();
            }
        });
        etsiOsastoa.setText("Osasto        ");
        GridBagConstraints gbc_editPanelhori_8 = new GridBagConstraints();
        gbc_editPanelhori_8.insets = new Insets(0, 0, 5, 5);
        gbc_editPanelhori_8.fill = GridBagConstraints.BOTH;
        gbc_editPanelhori_8.gridx = 0;
        gbc_editPanelhori_8.gridy = 1;
        panel_3.add(etsiOsastoa, gbc_editPanelhori_8);

        JButton button_4 = new JButton("Etsi");
        etsiOsastoa.add(button_4, BorderLayout.EAST);

        editPanelhori = new EditPanelPerus();
        etsiOsastoa.add(editPanelhori, BorderLayout.SOUTH);
        editPanelhori.setText("Nimi             ");
        JButton btnNewButton = new JButton("Etsi");
        editPanelhori.add(btnNewButton, BorderLayout.EAST);

        editPanelhori_1 = new EditPanelPerus();
        editPanelhori_1.getTextField().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                EtsiOsastot();
            }
        });
        editPanelhori.add(editPanelhori_1, BorderLayout.SOUTH);
        editPanelhori_1.setText("Viivakoodi    ");

        JButton button_3 = new JButton("Etsi");
        editPanelhori_1.add(button_3, BorderLayout.EAST);
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                EtsiKoodinMukaan();
            }
        });
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                EtsiNimenMukaan(); 
            }
        });
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EtsiOsastot();
            }
        });

        Button button_1 = new Button("Tyhjenn\u00E4");
        GridBagConstraints gbc_button_1 = new GridBagConstraints();
        gbc_button_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_button_1.insets = new Insets(0, 0, 5, 5);
        gbc_button_1.gridx = 2;
        gbc_button_1.gridy = 2;
        panel_3.add(button_1, gbc_button_1);
        button_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tyhjennaInventaario();
            }
        });
        GridBagConstraints gbc_button = new GridBagConstraints();
        gbc_button.fill = GridBagConstraints.HORIZONTAL;
        gbc_button.insets = new Insets(0, 0, 5, 0);
        gbc_button.gridx = 3;
        gbc_button.gridy = 2;
        panel_3.add(button, gbc_button);

        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.gridwidth = 4;
        gbc_scrollPane.gridheight = 12;
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 3;
        panel_3.add(scrollPane, gbc_scrollPane);
        //        GridBagConstraints gbc_table = new GridBagConstraints();
        //        gbc_table.gridheight = 3;
        //        gbc_table.fill = GridBagConstraints.BOTH;
        //        gbc_table.gridx = 1;
        //        gbc_table.gridy = 7;
        scrollPane.setViewportView(tableTuotelista);
        contentPanel.setLayout(gl_contentPanel);
        
        
        //-----------------------------------------------------------------------------------------------------------------------------
        //oma koodi
        //-----------------------------------------------------------------------------------------------------------------------------
        paivita();
    }
    /**
     * Muokataan tuotetta
     */
    protected void muokkaa() {
        //IDEA: poistetaan ja lis‰t‰‰n uusi
        if(tableTuotelista.getSelectedRow() != -1) {
            virheilmoituksetPois(); 
            String viivakoodi = panelViivakoodi.getText(); 
            Boolean virheet = onkoVirheita(viivakoodi, 13, 13, lisaaViivakoodi, 'd', "numeroa"); 

            String nimi = panelName.getText();
            Boolean virheet2 = onkoVirheita(nimi, 1,30, lisaaNimi, 'D', "kirjainta");

            String osasto = panelOsasto.getText();
            Boolean virheet3 = onkoVirheita(osasto, 1,3, lisaaOsasto, 'D', "kirjainta");

            Boolean virheet4 = false;
            String hinta = panelHinta.getText(); 
            String testattava = "\\d*\\.*\\d*";
            if   ( Pattern.matches(testattava, hinta)) {virheet4 = true; }
            else {lisaaHinta.setText("desimaaliluku"); lisaaHinta.setVisible(true); }


            if(virheet && virheet2 && virheet3 && virheet4) {    
                Boolean onkoTaytetty = tsekkaaOnkoTyhjiaTuote(viivakoodi, nimi, osasto, hinta); 
                if (onkoTaytetty){

                    @SuppressWarnings("unused")
                    Double hintaD = Double.parseDouble(hinta);
                    DefaultTableModel model = (DefaultTableModel) tableTuotelista.getModel();

                    kassaSwing.poistaTuoteTuotelistasta(panelViivakoodi.getText());
                    model.removeRow( tableTuotelista.getSelectedRow());

                    lisaaTuoteTuotelistaanInv();
                }
            }  
        }
    }
    
    /**
     * Asetetaan random viivakoodi kentt‰‰n
     */
    protected void setAutoKoodi() {
        StringBuffer sb = new StringBuffer();
        Random rand = new Random(); 
        for(int i = 0; i<13; i++){
            int a = rand.nextInt(9);
            sb.append(a);
        }
        panelViivakoodi.setTextField(sb.toString());
    }
    
    /**
     * Valitaan hiirell‰ valittu rivi tuotelistasta
     */
    protected void selectRow() {
        virheilmoituksetPois();
        if(tableTuotelista.getSelectedRow() != -1) {
            DefaultTableModel model = (DefaultTableModel) tableTuotelista.getModel();
            String koodi = model.getValueAt(tableTuotelista.getSelectedRow(), 0).toString();
            String nimi = model.getValueAt(tableTuotelista.getSelectedRow(), 1).toString();
            String osasto = model.getValueAt(tableTuotelista.getSelectedRow(), 2).toString();
            String pikakoodi = model.getValueAt(tableTuotelista.getSelectedRow(), 3).toString();
            String selite = model.getValueAt(tableTuotelista.getSelectedRow(), 4).toString();
            String hinta = model.getValueAt(tableTuotelista.getSelectedRow(), 5).toString();

            panelViivakoodi.setTextField(koodi);
            panelName.setTextField(nimi);
            panelOsasto.setTextField(osasto);
            panelHinta.setTextField(hinta);
            panelOsastoSelite.setTextField(selite);
            panelPikakoodi.setTextField(pikakoodi);


        } 
    }
    
    /**
     * Poistaa osaston ja sen kaikki tuotteet
     */
    protected void poistaOsasto() {

        DefaultTableModel model = (DefaultTableModel) tableTuotelista.getModel();
        model.addTableModelListener(tableTuotelista);
        if(!(model.getValueAt(tableTuotelista.getSelectedRow(), 2).toString().equals(""))){
            kassaSwing.poistaOsasto(model.getValueAt(tableTuotelista.getSelectedRow(), 2).toString());
            model.removeRow( tableTuotelista.getSelectedRow());
        }

    }
    
    /**
     * Etsit‰‰n osaston mukaisesti osasto ja sen tuotteet
     */
    protected void EtsiOsastot() {
        Object[][] roww =  kassaSwing.EtsiOsastot(etsiOsastoa.getText());
        for(int i = 0; i<roww.length; i++)
            try {  if (!(roww[i][0].equals(null))) {
                {
                    lisaaRiviTuotelistaan(roww[i][0].toString(), roww[i][1].toString(), roww[i][2].toString(), roww[i][3].toString(),
                            roww[i][4].toString(), roww[i][5].toString(), roww[i][6].toString(), roww[i][7].toString()); 
                }
            }
            } catch (NullPointerException e) {
                //TODO: MITƒ TEHDƒƒN!
            }

    }
    
    /**
     * Etsit‰‰n tuotteen nimen mukaisesti tuote
     */
    protected void EtsiNimenMukaan() {
        Object[][] roww =  kassaSwing.EtsiNimenMukaan(getNimiFromEtsi().getText());
        for(int i = 0; i<roww.length; i++)
            try {  if (!(roww[i][0].equals(null))) {
                {
                    lisaaRiviTuotelistaan(roww[i][0].toString(), roww[i][1].toString(), roww[i][2].toString(), roww[i][3].toString(),roww[i][4].toString(), roww[i][5].toString(), roww[i][6].toString(), roww[i][7].toString());  
                }
            }
            } catch (NullPointerException e) {
                //TODO: MITƒ TEHDƒƒN!
            }

    }



    /**
     * Etsit‰‰n tuotteen nimen mukaisesti tuote
     */
    protected void EtsiKoodinMukaan() {
        Object[][] roww = kassaSwing.EtsiviivakoodinMukaan(getViivakoodiFromEtsi().getText());

        for(int i = 0; i<roww.length; i++)
            try {  if (!(roww[i][0].equals(null))) {
                {
                    lisaaRiviTuotelistaan(roww[i][0].toString(), roww[i][1].toString(), roww[i][2].toString(), roww[i][3].toString(),roww[i][4].toString(), roww[i][5].toString(), roww[i][6].toString(), roww[i][7].toString()); 
                }
            }
            } catch (NullPointerException e) {
                //TODO: MITƒ TEHDƒƒN!
            }

    }
    /**
     * P‰ivitetaan tuotelista vastaamaan oikeaa listausta osastoista ja tuotteista
     */
    protected void paivita() {
        tableTuotelista.removeAll();
        DefaultTableModel model = (DefaultTableModel) tableTuotelista.getModel();
        model.setRowCount(0);


        Object[][] roww = kassaSwing.paivita(); 

        for(int i = 0; i<roww.length; i++) {

            try { if (roww[i][0].equals(null)) break;

            lisaaRiviTuotelistaan(roww[i][0].toString(), roww[i][1].toString(), roww[i][2].toString(), roww[i][3].toString(),roww[i][4].toString(), roww[i][5].toString(), roww[i][6].toString(), roww[i][7].toString()); 
            } catch (NullPointerException e) {
                //TODO: MITƒ TEHDƒƒN!
            }
        }
    }


    /**
     * Lis‰t‰‰n uusi osasto
     */
    protected void lisaaOsasto() {
        virheilmoituksetPois(); 
        String osasto = panelOsasto.getText();
        String osastoSelite = panelOsastoSelite.getText();

        Boolean onkoTaytettyOsasto = tsekkaaOnkoTyhjiaOsasto(osasto, osastoSelite); 

        Boolean virheet3 = onkoVirheita(osasto, 1,3, lisaaOsasto, 'D', "kirjainta");                                                
        if( virheet3.equals(true)) {
            if (onkoTaytettyOsasto)
            { int luku = kassaSwing.lisaaUusiOsasto(osasto, osastoSelite);
            if(luku == -1) {lisaaOsasto.setVisible(true);
            lisaaOsasto.setText("Osasto on k‰ytetty");
            }

            //DefaultTableModel model = (DefaultTableModel) tableTuotelista.getModel();
            DefaultTableModel model = (DefaultTableModel) tableTuotelista.getModel();
            if(luku != -1) {
                Object[] row = {"", "", osasto, "", osastoSelite };
                model.addRow(row);}

            }
        }}

    /**
     * Lis‰t‰‰n rivi tuotelistaan
     * @param viivakoodi
     * @param nimi
     * @param osasto
     * @param pikakoodi
     * @param osastoSelite
     * @param hinta
     * @param kpl 
     * @param myydyt 
     */
    protected void lisaaRiviTuotelistaan(String viivakoodi, String nimi, String osasto, String pikakoodi, String osastoSelite, String hinta, String kpl, String myydyt) {
        DefaultTableModel model = (DefaultTableModel) tableTuotelista.getModel();
        Object[] row = { viivakoodi, nimi, osasto, pikakoodi, osastoSelite, hinta, kpl ,myydyt};
        model.addRow(row);
    }

    /**
     * Lis‰t‰‰n k‰ytt‰j‰n asettamat arvot tuotelistaan
     */
    public void lisaaTuoteTuotelistaanInv(){
        virheilmoituksetPois(); 
        String viivakoodi = panelViivakoodi.getText(); 
        Boolean virheet = onkoVirheita(viivakoodi, 13, 13, lisaaViivakoodi, 'd', "numeroa"); 

        String nimi = panelName.getText();
        Boolean virheet2 = onkoVirheita(nimi, 1,30, lisaaNimi, 'D', "kirjainta");

        String osasto = panelOsasto.getText();
        Boolean virheet3 = onkoVirheita(osasto, 1,3, lisaaOsasto, 'D', "kirjainta");

        Boolean virheet4 = false;
        String hinta = panelHinta.getText(); 
        String testattava = "\\d*\\.*\\d*";
        if   ( Pattern.matches(testattava, hinta)) {virheet4 = true; }
        else {lisaaHinta.setText("desimaaliluku"); lisaaHinta.setVisible(true); }


        if(virheet && virheet2 && virheet3 && virheet4) {    
            Boolean onkoTaytetty = tsekkaaOnkoTyhjiaTuote(viivakoodi, nimi, osasto, hinta); 
            if (onkoTaytetty){
                Double hintaD = Double.parseDouble(hinta);
                DefaultTableModel model = (DefaultTableModel) tableTuotelista.getModel();
                int tuotteenpikakoodi = kassaSwing.lisaaUusiTuote(viivakoodi, nimi, osasto, hintaD);
                if(tuotteenpikakoodi == -1) {lisaaViivakoodi.setVisible(true);
                lisaaViivakoodi.setText("Viivakoodi on k‰ytetty");
                }
                if(tuotteenpikakoodi == -2) {lisaaOsasto.setVisible(true);
                lisaaOsasto.setText("Osastoa ei lˆydy");
                }

                if(tuotteenpikakoodi != -1 && tuotteenpikakoodi != -2) {Object[] row = { viivakoodi, nimi, osasto, tuotteenpikakoodi, "", hintaD, "" , 0};
                model.addRow(row);
                }}
        }
    }

    /**
     * Testaa onko virheit‰ syˆtetyiss‰ kentiss‰
     * @param teksti
     * @param vahintaan 
     * @param enintaan 
     * @param maara
     * @param virheTeksti
     * @param OnkoDigit 
     * @param kirjaimiaVaiNumeroita 
     * @return onkoOikein
     */
    protected Boolean onkoVirheita(String teksti, int vahintaan, int enintaan, JLabel virheTeksti, char OnkoDigit, String kirjaimiaVaiNumeroita){

        String testattava = "\\"+ OnkoDigit + "{" + vahintaan + "," + enintaan+"}";
        if( Pattern.matches(testattava, teksti)) return true; 
        if(!(vahintaan==enintaan)) {
            virheTeksti.setText(vahintaan+ "-"+ enintaan + " "+ kirjaimiaVaiNumeroita);
            virheTeksti.setVisible(true); }
        else {virheTeksti.setText(vahintaan+ " "+ kirjaimiaVaiNumeroita);
        virheTeksti.setVisible(true); }
        return false;
    }
    
    /**
     * Katsotaan onko kaikki tyhji‰, jolloin virheilmoitus tulee kaikista annetuista attribuuteista
     * @param viivakoodi
     * @param nimi
     * @param osasto
     * @param hinta
     * @return
     */
    private Boolean tsekkaaOnkoTyhjiaTuote(String viivakoodi, String nimi, String osasto, String hinta) {
        if(viivakoodi.isEmpty()) {
            lisaaViivakoodi.setText("Lis‰‰ viivakoodi");
            lisaaViivakoodi.setVisible(true);
        }

        if(nimi.isEmpty()) lisaaNimi.setVisible(true);
        if(osasto.isEmpty()) lisaaOsasto.setVisible(true);
        if(hinta.isEmpty()) lisaaHinta.setVisible(true);
        if(viivakoodi.isEmpty() || nimi.isEmpty() || osasto.isEmpty() || hinta.isEmpty()) return false; 
        return true; 
    }
    
    /**
     * Katsotaan onko kaikki muut tyhji‰ paitsi osasto ja osastoselite
     * @param viivakoodi
     * @param nimi
     * @param osasto
     * @param pikakoodi
     * @param hinta
     * @param osastoSelite
     * @return
     */
    private Boolean tsekkaaOnkoTyhjiaOsasto(String osasto, String osastoSelite) {
        if(osasto.isEmpty()) lisaaOsasto.setVisible(true);
        if(osastoSelite.isEmpty()) lisaaOsastoSelite.setVisible(true);
        if( osasto.isEmpty() || osastoSelite.isEmpty()) return false; 
        return true; 
    }
    
    /**
     * Asettaa kaikille virheilmoituksille asetuksen ei-n‰kyvill‰
     */
    private void virheilmoituksetPois() {
        lisaaViivakoodi.setVisible(false);
        lisaaNimi.setVisible(false);
        lisaaOsasto.setVisible(false);
        lisaaOsastoSelite.setVisible(false);
        lisaaHinta.setVisible(false);
    }
    
    /**
     * Tyhjennet‰‰n kent‰t lis‰‰ uusi tuote osiosta
     */
    protected void poistaUusiTuoteKentat() {
        kassaSwing.poistaUusiTuoteKentat();

    }
    
    /**
     * Poistetaan tuote tuotelistasta
     */
    protected void poistaTuote() {

        DefaultTableModel model = (DefaultTableModel) tableTuotelista.getModel();
        model.addTableModelListener(tableTuotelista);
        if(tableTuotelista.getSelectedRow() != -1) {
            if(!(model.getValueAt(tableTuotelista.getSelectedRow(), 0).toString().equals(""))){
                kassaSwing.poistaTuoteTuotelistasta(model.getValueAt(tableTuotelista.getSelectedRow(), 0).toString());
                model.removeRow( tableTuotelista.getSelectedRow());
            }
        }}
    
    /**
     * N‰ytet‰‰n ohjeet
     */
    protected void ohjeet() {
        kassaSwing.ohjeet();

    }

    /**
     * Kirjaudutaan ulos ja menn‰‰n aloitusikkunaan
     */
    protected void aloitus() {
        this.dispose(); 

        
    }
    /**
     * Menn‰‰n kassatilaan
     */
    protected void kassa() {
        kassaGUI = new KassaGUI();
        kassaGUI.setVisible(true); 
    }

    /**
     *Tyhjennet‰‰n tuotelistaa
     */
    public void tyhjennaInventaario() {

        DefaultTableModel model = (DefaultTableModel) tableTuotelista.getModel();
        model.setRowCount(0);

    }
    
    /**
     * Haetaan ja palautetaan kirjoitettu syˆte kohdasta osaston etsiminen
     * @return syˆtetty teksti
     */
    public EditPanelPerus getEtsiOsastoa() {
        return etsiOsastoa;
    }
    
    /**
     * Haetaan ja palautetaan kirjoitettu syˆte kohdasta nimen etsiminen
     * @return syˆtetty teksti
     */
    public EditPanelPerus getNimiFromEtsi() {
        return editPanelhori;
    }
    
    /**
     * Haetaan ja palautetaan kirjoitettu syˆte kohdasta viivakoodin etsiminen
     * @return syˆtetty teksti
     */
    public EditPanelPerus getViivakoodiFromEtsi() {
        return editPanelhori_1;
    }

    /**
     * EI TOIMI: TULEVIA ASETUKSIA VARTEN
     * @param tiedostoTuotteet
     * @param tiedostoOsasto
     */
    public void setTiedostot(String tiedostoTuotteet, String tiedostoOsasto ) {

        // kassaSwing.setTiedostot(tiedostoTuotteet, tiedostoOsasto);


    }
}
