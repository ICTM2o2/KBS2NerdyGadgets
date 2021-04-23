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
    ArrayList<JPanel> components = new ArrayList<JPanel>(); 

    public OntwerperForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("HOME");
        setBounds(0, 0, 650, 700);
        //setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.blue);
        add(panel, BorderLayout.NORTH);

        gbc.insets = new Insets(20,5,0,5);
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

        JPanel compListPanel = new JPanel(new GridLayout(0, 2));
        compListPanel.setBackground(Color.GREEN);
        compListPanel.setSize(new Dimension(200,0));
        
        
        sidePanel.add(compListPanel);
        sidePanel.add(new JLabel("BESCHIKBAARHEID: 0%"));

        JPanel tekenPanel = new JPanel();
        tekenPanel.setBackground(Color.YELLOW);
        add(tekenPanel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}
