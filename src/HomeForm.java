import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeForm extends JFrame implements ActionListener {
    private static final long serialVersionUID = -1450150616029361695L;
    private JButton btnServerConfigure;
    private JButton btnServerMonitor;
    private JButton btnServerOptimize;
    private JButton btnServerClose;
    private GridBagConstraints gbc = new GridBagConstraints();

    public HomeForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("HOME");
        setBounds(0, 0, 650, 550);
        setLayout(new GridBagLayout());
        //button1
        gbc.insets = new Insets(0, 0, 50, 0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        btnServerConfigure = new JButton("Server Configuratie");
        btnServerConfigure.addActionListener(this);
        add(btnServerConfigure, gbc);
        //button2
        gbc.gridy = 2;
        btnServerMonitor = new JButton("btnServerMonitor");
        btnServerMonitor.addActionListener(this);
        add(btnServerMonitor, gbc);
        //button3
        gbc.gridy = 3;
        btnServerOptimize = new JButton("btnServerOptimize");
        btnServerOptimize.addActionListener(this);
        add(btnServerOptimize, gbc);
        //button4
        gbc.gridy = 4;
        btnServerClose = new JButton("btnServerClose");
        btnServerClose.addActionListener(e -> System.exit(0));
        add(btnServerClose, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == btnServerMonitor) {
            MonitoringForm f = new MonitoringForm();
            f.setVisible(true);
        }
        if (e.getSource() == btnServerConfigure){
            OntwerperForm f = new OntwerperForm();
            f.setVisible(true);
        }

    }
}

