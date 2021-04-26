import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OntwerperForm extends JFrame implements ActionListener{
    private GridBagConstraints gbc = new GridBagConstraints();

    //buttons etc
    private JButton saveBtn;
    private JButton loadBtn;
    private JButton backBtn;
    private JTextField nameTxt;
    private JTextField availTxt;
    private JButton addBtn;
    private dragPanel dPanel = new dragPanel();
    private JComboBox<servComponent.serverType> componentBox;
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
        saveBtn = new JButton("Ontwerp opslaan");
        saveBtn.addActionListener(this);
        panel.add(saveBtn,gbc);
        gbc.gridx = 2;
        loadBtn = new JButton("Ontwerp laden");
        loadBtn.addActionListener(this);
        panel.add(loadBtn,gbc);
        gbc.gridx = 3;
        backBtn = new JButton("Terug");
        backBtn.addActionListener(this);
        panel.add(backBtn,gbc);
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

        compListPanel.add(new JLabel("Server name: "));
        nameTxt = new JTextField(7);
        compListPanel.add(nameTxt);
        compListPanel.add(new JLabel("Server type: "));
        componentBox = new JComboBox<>();
        componentBox.addItem(servComponent.serverType.FIREWALL);
        componentBox.addItem(servComponent.serverType.WEB);
        componentBox.addItem(servComponent.serverType.DATABASE);
        compListPanel.add(componentBox);
        availTxt = new JTextField(5);
        compListPanel.add(new JLabel("Beschikbaarheid: "));
        compListPanel.add(availTxt);
        addBtn = new JButton("Server toevoegen");
        addBtn.addActionListener(this);
        compListPanel.add(addBtn);
        
        
        sidePanel.add(compListPanel);

        JPanel beschikbaarheidPanel = new JPanel(new FlowLayout());
        beschikbaarheidPanel.setBackground(Color.CYAN);
        beschikbaarheidPanel.add(new JLabel("BESCHIKBAARHEID: 0%"));
        sidePanel.add(beschikbaarheidPanel);

        add(dPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == addBtn){
            Double availability;
            try {
                availability = Double.parseDouble(availTxt.getText());
            } catch (Exception ex) {
                availability = 0.0;
            }
            dPanel.addServer(new servComponent(availability, nameTxt.getText(), (servComponent.serverType)componentBox.getSelectedItem()));
            dPanel.repaint();
        }
    }
}
