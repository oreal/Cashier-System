package wbkassa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import fi.jyu.mit.ohj2.Mjonot;
//import kassa.SailoException;
import kassa.Tuote;
import kassaSwing.KassaSwing;
import java.awt.Dimension;
/**
 * @author Ronja Lindholm
 * @version 15.2.2015
 *
 */

/*
 * EI TOIMI: Ohjeet-ikkuna ei toimi! 
 * EI TOIMI: pikakoodi ei toimi, pit‰‰ k‰ytt‰‰ kokonaista viivakoodia!
 */
public class KassaGUI extends JFrame {
    private KassaSwing kassaSwing; 

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea ilmoitusTaulu;
    private KoodiPanel koodiPanel = new KoodiPanel();
    private JTable Kuitti=  new JTable(10,2);
    private JLabel maksuSumma;
    private Double summaMaara = 0.0;
    private PanelMaksu editpanel;
    private PanelMaksu editpanel_1;
    private ArrayList<String> Viivakoodit = new ArrayList<>(); 
    private Twobuttonssidebyside twobuttonssidebyside;
    private JScrollPane scrollPane;
    private Inventaario inventaario;

    /**
     * Launch the application.
     * @param args ei k‰ytˆss‰
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    KassaGUI frame = new KassaGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public KassaGUI() {
        kassaSwing = new KassaSwing(this); 
        setForeground(Color.BLACK);
        setBackground(SystemColor.activeCaption);
        setTitle("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 664, 547);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(SystemColor.menu);
        menuBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
        setJMenuBar(menuBar);

        JMenu mnNewMenu = new JMenu("Kassa 078");
        mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu);

        Component horizontalGlue = Box.createHorizontalGlue();
        menuBar.add(horizontalGlue);

        JMenu mnNewMenu_1 = new JMenu("Inventaario");
        mnNewMenu_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                inventaario(); 

            }
        });
        mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu_1);

        JMenu mnNewMenu_2 = new JMenu("Ohjeet");
        mnNewMenu_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ohjeet();

            }
        });


        mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu_2);

        JMenu mnNewMenu_3 = new JMenu("Kirjaudu ulos");
        mnNewMenu_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                lopeta();
            }


        });

        mnNewMenu_3.setAlignmentX(Component.RIGHT_ALIGNMENT);
        mnNewMenu_3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        menuBar.add(mnNewMenu_3);

        contentPane = new JPanel();
        contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

        JPanel panel_1 = new JPanel();
        panel_1.setAlignmentX(Component.LEFT_ALIGNMENT);
        contentPane.add(panel_1);
        panel_1.setLayout(new BorderLayout(0, 0));

        koodiPanel = new KoodiPanel();
        koodiPanel.setMinimumSize(new Dimension(50, 60));
        koodiPanel.getTextKoodi().setBackground(Color.WHITE);
        koodiPanel.getButtonInfo().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                tuoteinfo();
            }
        });
        koodiPanel.getTextKoodi().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                lisaaTuoteKassaGui(); 
            }
        });
        koodiPanel.getButtonPoista().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                poistaTuoteKuitista(); 
            }
        });
        panel_1.add(koodiPanel, BorderLayout.NORTH);

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(SystemColor.menu);
        panel_1.add(panel_3, BorderLayout.CENTER);
        panel_3.setLayout(new BorderLayout(0, 0));

        JLabel label = new JLabel(" ");
        panel_3.add(label, BorderLayout.NORTH);

        JLabel label_2 = new JLabel(" ");
        panel_3.add(label_2, BorderLayout.SOUTH);

        scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.WHITE);
        panel_3.add(scrollPane, BorderLayout.CENTER);
        Kuitti.setPreferredScrollableViewportSize(new Dimension(300, 400));
        Kuitti.setPreferredSize(new Dimension(50, 160));
        Kuitti.setMinimumSize(new Dimension(20, 160));
        Kuitti.setFillsViewportHeight(true);
        Kuitti.setRowSelectionAllowed(false);
        scrollPane.setRowHeaderView(Kuitti);
        Kuitti.setForeground(Color.BLACK);
        Kuitti.setBackground(Color.WHITE);
        getKuitti().setName("KuittiTable");
        getKuitti().setFont(new Font("Tahoma", Font.PLAIN, 16));
        getKuitti().setGridColor(Color.WHITE);
        getKuitti().setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "", ""
                }
                ));
        Kuitti.getColumnModel().getColumn(1).setMinWidth(9);
        scrollPane.setViewportView(Kuitti);
        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));

        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setMinimumSize(new Dimension(33, 0));
        contentPane.add(horizontalStrut);

        JPanel panel_4 = new JPanel();
        panel_4.setMinimumSize(new Dimension(70, 10));
        contentPane.add(panel_4);
        GridBagLayout gbl_panel_4 = new GridBagLayout();
        gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0};
        gbl_panel_4.rowHeights = new int[] {30, 30, 30, 0, 30, 0, 30, 30, 30, 30, 30, 0, 30, 30, 30, 30};
        gbl_panel_4.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_panel_4.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0};
        panel_4.setLayout(gbl_panel_4);


        ilmoitusTaulu = new JTextArea();
        ilmoitusTaulu.setMinimumSize(new Dimension(50, 22));
        ilmoitusTaulu.setBorder(UIManager.getBorder("PopupMenu.border"));
        ilmoitusTaulu.setFont(new Font("Monospaced", Font.PLAIN, 15));
        GridBagConstraints gbc_textArea = new GridBagConstraints();
        gbc_textArea.gridwidth = 3;
        gbc_textArea.insets = new Insets(0, 0, 5, 0);
        gbc_textArea.gridheight = 10;
        gbc_textArea.fill = GridBagConstraints.BOTH;
        gbc_textArea.gridx = 0;
        gbc_textArea.gridy = 0;
        panel_4.add(ilmoitusTaulu, gbc_textArea);

        maksuSumma = new JLabel("00.00");
        maksuSumma.setForeground(new Color(0, 0, 0));
        maksuSumma.setBackground(Color.BLACK);
        maksuSumma.setFont(new Font("Tahoma", Font.PLAIN, 23));
        maksuSumma.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_label_1 = new GridBagConstraints();
        gbc_label_1.gridheight = 2;
        gbc_label_1.anchor = GridBagConstraints.EAST;
        gbc_label_1.insets = new Insets(0, 0, 5, 0);
        gbc_label_1.gridx = 2;
        gbc_label_1.gridy = 12;
        panel_4.add(maksuSumma, gbc_label_1);

        editpanel = new PanelMaksu();
        editpanel.getEditPanelMaksu().addMouseListener(new MouseAdapter() {
            @SuppressWarnings("synthetic-access")
            @Override
            public void mouseClicked(MouseEvent arg0) {
                editpanel.getEditPanelMaksu().setText("");

            }
        });
        editpanel.getEditPanelMaksu().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                maksu(); 
            }
        });

        editpanel.setLlText("Maksu   ");
        GridBagConstraints gbc_editpanel = new GridBagConstraints();
        gbc_editpanel.gridwidth = 3;
        gbc_editpanel.insets = new Insets(0, 0, 5, 0);
        gbc_editpanel.fill = GridBagConstraints.BOTH;
        gbc_editpanel.gridx = 0;
        gbc_editpanel.gridy = 14;
        panel_4.add(editpanel, gbc_editpanel);

        editpanel_1 = new PanelMaksu();
        editpanel_1.setLlText("Takaisin");
        GridBagConstraints gbc_editpanel_1 = new GridBagConstraints();
        gbc_editpanel_1.gridwidth = 3;
        gbc_editpanel_1.insets = new Insets(0, 0, 5, 0);
        gbc_editpanel_1.fill = GridBagConstraints.BOTH;
        gbc_editpanel_1.gridx = 0;
        gbc_editpanel_1.gridy = 15;
        panel_4.add(editpanel_1, gbc_editpanel_1);

        twobuttonssidebyside = new Twobuttonssidebyside();
        twobuttonssidebyside.getButtonHylkaaMaksu().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                hylkaaMaksu();
            }
        });
        twobuttonssidebyside.getButtonHyvaksyMaksu().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                hyvaksyMaksu(); 

            }
        });
        GridBagConstraints gbc_twobuttonssidebyside = new GridBagConstraints();
        gbc_twobuttonssidebyside.gridwidth = 3;
        gbc_twobuttonssidebyside.gridheight = 2;
        gbc_twobuttonssidebyside.fill = GridBagConstraints.BOTH;
        gbc_twobuttonssidebyside.gridx = 0;
        gbc_twobuttonssidebyside.gridy = 16;
        panel_4.add(twobuttonssidebyside, gbc_twobuttonssidebyside);


        //-----------------------------------------------------------------------------------------------------------------------------
        //oma koodi
        //----------------------------------------------------------------------------------------------------------------------------- 

    }

    /**
     * Avataan inventaario
     */
    protected void inventaario() {
        inventaario = new Inventaario(kassaSwing.getTiedostoTuotteet(), kassaSwing.getTiedostoOsastot());
        inventaario.setVisible(true);
    }

    /**
     * Lopetetaan ikkuna
     */
    protected void lopeta() {
        this.dispose();    
    }


    /**
     * Hyl‰t‰‰n koko maksutapahtuma
     */
    protected void hylkaaMaksu() {
        ilmoitusTaulu.setText("Maksu hyl‰tty!");
        tyhjaaKaikki(); 
    }


    /**
     * Hyv‰ksyt‰‰n maksutapahtuma
     */
    protected void hyvaksyMaksu() {
        ilmoitusTaulu.setText("Maksu hyv‰ksytty");
        kassaSwing.maksa(Viivakoodit);
        tyhjaaKaikki();  
    }

    /*
     * Tyhj‰‰ kuitin, summan ja viivakoodilistan
     */
    private void tyhjaaKaikki() {    
        summaMaara = 0.0;
        String a = String.format("%5.2f", summaMaara);
        maksuSumma.setText(a);

        Viivakoodit = new ArrayList<>(); 
        koodiPanel.setKoodiText("");
        Kuitti.removeAll();
        DefaultTableModel model = (DefaultTableModel) Kuitti.getModel();
        model.setRowCount(0);
    }




    /**
     * Ilmoita takaisin annettava summa tuotteiden yhteisumman ja annetun summan perusteella
     */
    protected void maksu() {

        String annettuSumma = editpanel.getEditPanelMaksu().getText();
        String yhteishinta = maksuSumma.getText();
        Double takaisin = Mjonot.erotaDouble(annettuSumma, 0) - Mjonot.erotaDouble(yhteishinta, 0);
        editpanel_1.getEditPanelMaksu().setText(takaisin.toString());

    }


    /**
     * Poistetaan tuote kuitista
     */
    protected void poistaTuoteKuitista() {

        String teksti = getKoodiText();
        if(!(teksti.isEmpty())){
            kassaSwing.poistaTuoteKuitista(teksti);}

    }
    
    /**
     * Poista tuote kuitista
     * @param tuote mik‰ tuote poistetaan
     */
    public void poistaTuoteKuitista(Tuote tuote) {

        int viivakoodienLkm = Viivakoodit.size();
        for(int i = viivakoodienLkm-1; i>=0; i-- )
            if(Viivakoodit.get(i).equals(tuote.getKoodi())) {

                DefaultTableModel model = (DefaultTableModel) Kuitti.getModel();
                Object[] row1 = { "Poistetaan tuote: "};
                Object[] row2 = { tuote.getNimi(), tuote.getHinta()*-1 };

                Viivakoodit.remove(i);
                lisaaSummaan(tuote.getHinta()*-1);
                model.addRow(row1);
                model.addRow(row2);
            }
    }

    /**
     * Avaa tuoteinfo
     */
    protected void tuoteinfo() {
        kassaSwing.tuoteinfo(koodiPanel.getTextKoodi().getText()); 
    }

    /**
     * Avataan ohjeiden ikkuna
     * Avataan ohjeet
     */
    protected void ohjeet() {
        kassaSwing.ohjeet();   
    }



    /**
     * Lis‰t‰‰n tuote kuittiin jos se lˆytyy
     * @throws SailoException
     */
    protected void lisaaTuoteKassaGui()  {
        if (!(koodiPanel.getKoodiText().isEmpty())) {

            kassaSwing.lisaaTuoteKuittiin(koodiPanel.getKoodiText()); 
        }
    }

    /**
     *Lis‰t‰‰n tuote kuittiin
     * @param tuotteenNimi
     * @param hinta
     *
     */
    public void lisaaKuittiin(String tuotteenNimi, double hinta) {

        DefaultTableModel model = (DefaultTableModel) Kuitti.getModel();
        Object[] row = { tuotteenNimi, hinta };
        model.addRow(row);
    }


   


    /**
     * Lis‰t‰‰n tuotteiden summapalkkiin tuotteen hinta
     * @param hinta
     */
    public void lisaaSummaan(Double hinta) {
        summaMaara += hinta; 
        String a = String.format("%5.2f", summaMaara);
        maksuSumma.setText(a);

    }


    /**
     * Asetetaan virheilmoitus ilmoitustauluun
     * @param virheTeksti
     */
    public void setVirhe(String virheTeksti) {
        ilmoitusTaulu.setText(virheTeksti);


    }

 


    /**
     * Lis‰t‰‰n tuotteen viivakoodi viivakoodien listaan
     * @param tuotteenViivakoodi
     */
    public void lisaaKuittiListaan(String tuotteenViivakoodi) {
        Viivakoodit.add(tuotteenViivakoodi);
    } 
    
    //----------------------------------------SETTERS AND GETTERS-------------------------------------------
   
    /**
     * Viivakoodin teksti
     * @return koodin teksti
     */
    public String getKoodiText() {
        return koodiPanel.getKoodiText();

    }
    
    /**
     * Palautetaan PanelMaksu
     * @return paneelit maksu ja takaisin
     */
    public PanelMaksu getEditpanelMaksu() {
        return editpanel;
    }
    

    /**
     * @return kassaguin viitteen
     */
    protected KassaGUI getKassaGUI(){ return this;}

    /**
     * Haetaan kuitti
     * @return viite kuittiin
     */
    public JTable getKuitti() {return this.Kuitti;}



}

