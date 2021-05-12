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
    public int aantalservers = 4;
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
        setLayout(new FlowLayout());

        // Label voor titel
        JLabel Monitoring = new JLabel("MONITORING");
        Monitoring.setFont(new Font("Arial", Font.PLAIN, 30));
        Monitoring.setPreferredSize(new Dimension(300, 50));
        Monitoring.setHorizontalAlignment(SwingConstants.CENTER);
        add(Monitoring);

        //terugknop naar home
        terugknop = new JButton("Terug");
        terugknop.addActionListener(this);
        terugknop.setHorizontalAlignment(SwingConstants.RIGHT);
        add(terugknop);


        JScrollPane scrollPane = new JScrollPane(panel1,   ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(1500, 750));



            for (int i = 1; i <= aantalservers; i++) {
                Component = new JPanel();
                Component.setLayout(new GridLayout(0,1));
                Component.setSize(new Dimension(200, 300));
                Component.setBackground(Color.gray);

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

        this.revalidate();

        add(scrollPane);



        }

    private boolean isOnline() {
        return false;
    }

    @Override
        public void actionPerformed (ActionEvent e){
        if(e.getSource() == terugknop){
            HomeForm home = new HomeForm();
            home.setVisible(true);
            setVisible(false);

        }

        }
    }
