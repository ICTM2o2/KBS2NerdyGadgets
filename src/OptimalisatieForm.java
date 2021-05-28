import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class OptimalisatieForm extends JFrame implements ActionListener {
    @Serial
    private static final long serialVersionUID = -1450150616029361695L;

    //buttons etc
    private JButton btnHome;
    private JButton btnServerToevoegen;
    private JButton btnOptimaliseren;
    private JButton btnVoegToe;

    private JLabel totaalPrijs;
    private JTextField aantalStappen;

    private String serv1naam;
    private String serv2naam;
    private String serv3naam;
    private String serv4naam;
    private String serv5naam;
    private String serv6naam;

    private int serv1beschik;
    private int serv2beschik;
    private int serv3beschik;
    private int serv4beschik;
    private int serv5beschik;
    private int serv6beschik;

    private int serv1prijs;
    private int serv2prijs;
    private int serv3prijs;
    private int serv4prijs;
    private int serv5prijs;
    private int serv6prijs;

    public OptimalisatieForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Server Optimalisatie");
        setBounds(0, 0, 800, 700);

        setLayout(new BorderLayout());

        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.setBackground(new Color(140, 129, 94));
        add(panel1, BorderLayout.NORTH);

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.setBackground(new Color(255,255,255));
        add(panel2, BorderLayout.CENTER);

        JPanel panel3 = new JPanel(new FlowLayout());
        panel3.setBackground(new Color(140, 129, 94));
        add(panel3, BorderLayout.SOUTH);

        //JPanel panel4 = new JPanel(new GridLayout());
        //panel4.setBackground(new Color(0, 0, 200));
        //panel2.add(panel4);

        //panel1
        panel1.add(new JLabel("Server Optimalisatie"));

        btnHome = new JButton("Home");
        btnHome.addActionListener(this);
        panel1.add(btnHome);

        //panel2
        btnServerToevoegen = new JButton("Server toevoegen");
        btnServerToevoegen.addActionListener(this);
        panel2.add(btnServerToevoegen);

        //panel3
        btnOptimaliseren = new JButton("Optimaliseren");
        btnOptimaliseren.addActionListener(this);
        panel3.add(btnOptimaliseren);

        totaalPrijs = new JLabel("Totale prijs: €0");
        //panel3.add(new JLabel("Totale prijs: €0"));
        panel3.add(totaalPrijs);

        aantalStappen = new JTextField(5);
        panel3.add(new JLabel("Aantal stappen: "));
        panel3.add(aantalStappen);

        //combo box
        serv1naam = "HAL9001DB";
        serv2naam = "HAL9002DB";
        serv3naam = "HAL9003DB";
        serv4naam = "HAL9001W";
        serv5naam = "HAL9002W";
        serv6naam = "HAL9003W";

        serv1beschik = 90;
        serv2beschik = 95;
        serv3beschik = 98;
        serv4beschik = 80;
        serv5beschik = 90;
        serv6beschik = 95;

        serv1prijs = 5100;
        serv2prijs = 7700;
        serv3prijs = 12200;
        serv4prijs = 2200;
        serv5prijs = 3200;
        serv6prijs = 5100;

        String[] servers={serv1naam,serv2naam,serv3naam,serv4naam,serv5naam,serv6naam};
        JComboBox jcs = new JComboBox(servers);

        jcs.setEditable(true);

        //create a JOptionPane
        Object[] options = new Object[] {};
        JOptionPane jop = new JOptionPane("Selecteer een server",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,options, null);

        btnVoegToe = new JButton("Voeg toe");
        btnVoegToe.addActionListener(this);

        //add combos to JOptionPane
        jop.add(jcs);
        jop.add(btnVoegToe);

        //create a JDialog and add JOptionPane to it
        JDialog diag = new JDialog();
        diag.getContentPane().add(jop);
        diag.pack();
        diag.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnHome) {
            HomeForm home = new HomeForm();
            home.setVisible(true);
            setVisible(false);
        }
    }
}