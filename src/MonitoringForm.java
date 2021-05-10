import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MonitoringForm extends JFrame implements ActionListener {
    private static int Index = 0;
    Border border1 = BorderFactory.createLineBorder(Color.black);
    Border border2 = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, null, null, null);
    private JPanel Component = new JPanel();
    private JPanel AllePanels;
    private JLabel Processorb, Beschikbaarheid, Type, I_Type, I_Beschikbaarheid, Diskruimte, Placeholder, I_Proccesb, I_Diskruimte, Prijs, I_Prijs, OnOff_Line, Naam;
    private JSeparator Bottom;
    //private ArrayList<Componenten> database;
    private ArrayList<JPanel> panels = new ArrayList<>();
    public int aantalservers = 8;
    private boolean isOnline = false;
    private JScrollPane scrollPane = new JScrollPane();
    private JButton terugknop;
    private JPanel panel1 = new JPanel();

    public MonitoringForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MONITOR");
        setBounds(0, 0, 1000,750 );
        Border border_g = BorderFactory.createLineBorder(Color.green);
        Border border_r = BorderFactory.createLineBorder(Color.red);
        //arraylist panels legen
//        public void getComponent () {
//            for (int i = 0; i < panels.size(); i++) {
//                this.remove(panels.get(i));
//            }
//            panels.clear();
        setLayout(new FlowLayout());
        terugknop = new JButton("Terug");
        terugknop.addActionListener(this);
        add(terugknop);



        // Panels aanmaken voor momentele componenten


        JScrollPane scrollPane = new JScrollPane(panel1,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(1500, 750));



            for (int i = 1; i <= aantalservers; i++) {
                Component = new JPanel();
                Component.setLayout(new GridLayout(0,1));
                Component.setPreferredSize(new Dimension(200, 400));
                Component.setBackground(Color.GRAY);

                Naam = new JLabel("Server " + i);
                Naam.setBorder(border2);
                Naam.setPreferredSize(new Dimension(150, 50));
                Naam.setHorizontalAlignment(SwingConstants.CENTER);
                Component.add(Naam);

                Placeholder = new JLabel("  ");
                Component.add(Placeholder);

                Processorb = new JLabel("Processorbelasting ");
                Component.add(Processorb);

                I_Proccesb = new JLabel(" %");
                I_Proccesb.setBorder(border1);
                I_Proccesb.setPreferredSize(new Dimension(70, 15));
                Component.add(I_Proccesb);

                Bottom = new JSeparator();
                Bottom.setPreferredSize(new Dimension(100, 1));
                Bottom.setForeground(Color.darkGray);
                Component.add(Bottom);

                Diskruimte = new JLabel("Beschikbare ruimte");
                Component.add(Diskruimte);

                Placeholder = new JLabel("");
                Component.add(Placeholder);

                I_Diskruimte = new JLabel(" GB");
                I_Diskruimte.setBorder(border1);
                I_Diskruimte.setPreferredSize(new Dimension(70, 15));
                Component.add(I_Diskruimte);

                Bottom = new JSeparator();
                Bottom.setPreferredSize(new Dimension(190, 1));
                Bottom.setForeground(Color.darkGray);
                Component.add(Bottom);

                Type = new JLabel("Type");
                Component.add(Type);

                Placeholder = new JLabel("                            ");
                Component.add(Placeholder);

                I_Type = new JLabel("Type");
                I_Type.setBorder(border1);
                I_Type.setPreferredSize(new Dimension(70, 15));
                Component.add(I_Type);

                Bottom = new JSeparator();
                Bottom.setPreferredSize(new Dimension(190, 1));
                Bottom.setForeground(Color.darkGray);
                Component.add(Bottom);

                Beschikbaarheid = new JLabel("Beschikbaarheid");
                Component.add(Beschikbaarheid);

                Placeholder = new JLabel("     ");
                Component.add(Placeholder);

                I_Beschikbaarheid = new JLabel(" %");
                I_Beschikbaarheid.setBorder(border1);
                I_Beschikbaarheid.setPreferredSize(new Dimension(70, 15));
                Component.add(I_Beschikbaarheid);

                Bottom = new JSeparator();
                Bottom.setPreferredSize(new Dimension(190, 5));
                Bottom.setForeground(Color.darkGray);
                Component.add(Bottom);

                Prijs = new JLabel("Prijs");
                Component.add(Prijs);

                Placeholder = new JLabel("        ");
                Component.add(Placeholder);

                Placeholder = new JLabel("        ");
                Component.add(Placeholder);

                OnOff_Line = new JLabel();
                if (isOnline()) {
                    OnOff_Line = new JLabel("Online"); // controle of het component offline of online is
                    OnOff_Line.setBorder(border_g);
                } else { OnOff_Line = new JLabel("Offline");
                    OnOff_Line.setBorder(border_r);

                }
                OnOff_Line.setPreferredSize(new Dimension(70, 15));
                Component.add(OnOff_Line);

                Index++;
                panel1.add(Component);

            }

            scrollPane.getViewport().add(Component, null);
        this.revalidate();

        add(scrollPane);



        }

    private boolean isOnline() {
        return false;
    }

    @Override
        public void actionPerformed (ActionEvent e){

        }
    }

//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.concurrent.Flow;
//
//public class MonitoringForm extends JFrame implements ActionListener{
//    private JButton terugknop;
//    private JTextField servernamen;
//    private JScrollBar scrollBar;
//    public  boolean isOnline = true;
//    int[] servernummer;
//
//    public MonitoringForm() {
//        setTitle("SERVER MONITORING");
//        setBounds(0, 0, 650, 550);
//        terugknop = new JButton("Terug");
//        terugknop.addActionListener(this);
//        add(terugknop);
//
//        JPanel servers = new JPanel();
//        add(servers);
//
//        setPreferredSize(new Dimension(650, 300));
//        setMaximumSize(getPreferredSize());
//        getContentPane().setLayout(
//                new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS)
//        );
//
//        for (int i = 1; i <= servernummer.length; i++) {
//            JLabel title = new JLabel("Server " + i);
//            title.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
//            add(title, BorderLayout.CENTER);
//
//            JLabel uptime = new JLabel("Uptime: ");
//            uptime.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
//            add(uptime, BorderLayout.CENTER);
//
//            JLabel diskruimte = new JLabel("Diskruimte");
//            diskruimte.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
//            add(diskruimte, BorderLayout.CENTER);
//
//            JLabel dagenBeschikbaar = new JLabel("Dagen beschikbaar: ");
//            dagenBeschikbaar.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
//            add(dagenBeschikbaar, BorderLayout.CENTER);
//
//            JLabel processorBelasting = new JLabel("Processor belasting: ");
//            processorBelasting.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
//            add(processorBelasting, BorderLayout.CENTER);
//
//            if (isOnline) {
//                //zorg dat het programma een vinkje weergeeft onder de serverinfo.
//                ImageIcon vinkje = new ImageIcon("C:/Users/Bart de Boer/Documents/School/SEMESTER2/KBS.d/download.png");
//                JLabel imagelabel = new JLabel(vinkje);
//
//            } else {
//                //zorg dat het programma een kruisje weergeeft onder de serverinfo
//
//            }
//        }
//
//
//        JScrollPane listScroller = new JScrollPane();
//        listScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//        add(listScroller);
//
//
//
//
//        setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    }
//
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//
//    }
//
//    /* Listeners ================================================== */
//
//
//}