import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OntwerperForm extends JFrame implements ActionListener{
    private GridBagConstraints gbc = new GridBagConstraints();

    //buttons etc
    private JButton opslaan;
    private JButton ontwerpLaden;
    private JButton terugKnop;
    private JComboBox componentBox;
    ArrayList<JPanel> components = new ArrayList<JPanel>(); 

    public OntwerperForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("HOME");
        setBounds(0, 0, 800, 700);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.blue);
        add(panel, BorderLayout.NORTH);

        gbc.insets = new Insets(20,5,10,5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Server Ontwerper"),gbc);
        gbc.gridx = 1;
        opslaan = new JButton("Ontwerp opslaan");
        opslaan.addActionListener(this);
        panel.add(opslaan,gbc);
        gbc.gridx = 2;
        ontwerpLaden = new JButton("Ontwerp laden");
        ontwerpLaden.addActionListener(this);
        panel.add(ontwerpLaden,gbc);
        gbc.gridx = 3;
        terugKnop = new JButton("Terug");
        terugKnop.addActionListener(this);
        panel.add(terugKnop,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        JPanel sidePanel = new JPanel(new GridLayout(2,0));
        sidePanel.setBackground(Color.pink);
        sidePanel.setPreferredSize(new Dimension(200,0));
        add(sidePanel, BorderLayout.WEST);

        JPanel compListPanel = new JPanel(new FlowLayout());
        compListPanel.setBackground(Color.GREEN);

        componentBox = new JComboBox();
        componentBox.addItem("SERVER1");
        componentBox.addItem("SERVER2");
        compListPanel.add(new JLabel("Server: "));
        compListPanel.add(componentBox);
        compListPanel.add(new JLabel("Beschikbaarheid: "));
        compListPanel.add(new JTextField(4));
        compListPanel.add(new JButton("Server toevoegen"));
        
        
        sidePanel.add(compListPanel);

        JPanel beschikbaarheidPanel = new JPanel(new FlowLayout());
        beschikbaarheidPanel.setBackground(Color.CYAN);
        beschikbaarheidPanel.add(new JLabel("BESCHIKBAARHEID: 0%"));
        sidePanel.add(beschikbaarheidPanel);

        add(new dragPanel());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
