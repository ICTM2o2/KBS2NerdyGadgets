import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class OntwerperForm extends JFrame implements ActionListener{
    private GridBagConstraints gbc = new GridBagConstraints();

    //buttons etc
    private JButton saveBtn;
    private JButton loadBtn;
    private JButton backBtn;
    private JLabel totalAv, webAv, dbAv, fwAv;
    private JLabel totalPr, webPr, dbPr, fwPr;
    private JTextField priceTxt;
    private JTextField nameTxt;
    private JTextField availTxt;
    private JButton addBtn;
    private dragPanel dPanel = new dragPanel(this);
    private JComboBox<servComponent.serverType> componentBox;
    ArrayList<JPanel> components = new ArrayList<JPanel>(); 

    public OntwerperForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("HOME");
        setBounds(0, 0, 800, 700);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(140, 129, 94));
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
        sidePanel.setPreferredSize(new Dimension(200,0));
        add(sidePanel, BorderLayout.WEST);

        JPanel compListPanel = new JPanel(new FlowLayout());
        compListPanel.setBackground(new Color(242,230,194));

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
        priceTxt = new JTextField(11);
        priceTxt.addActionListener(this);
        compListPanel.add(new JLabel("Prijs: "));
        compListPanel.add(priceTxt);
        compListPanel.add(addBtn);

        
        
        sidePanel.add(compListPanel);

        JPanel beschikbaarheidPanel = new JPanel(new GridBagLayout());
        beschikbaarheidPanel.setBackground(new Color(242,230,194));
        totalAv = new JLabel("Totaal: 0%");
        fwAv = new JLabel("Firewall: 0%");
        webAv = new JLabel("Web: 0%");
        dbAv = new JLabel("Database: 0%");
        GridBagConstraints lol = new GridBagConstraints();
        lol.insets = new Insets(1,0,1,0);
        lol.gridy = 1;        
        beschikbaarheidPanel.add(new JLabel("Beschikbaarheid"), lol);
        lol.gridy = 2;  
        beschikbaarheidPanel.add(totalAv,lol);
        lol.gridy = 3; 
        beschikbaarheidPanel.add(fwAv,lol);
        lol.gridy = 4; 
        beschikbaarheidPanel.add(webAv,lol);
        lol.gridy = 5; 
        beschikbaarheidPanel.add(dbAv,lol);
        sidePanel.add(beschikbaarheidPanel);
        //== price
        lol.insets = new Insets(10,0,1,0);
        lol.gridy = 6; 
        beschikbaarheidPanel.add(new JLabel("Kosten"), lol);
        lol.insets = new Insets(1,0,1,0);
        totalPr = new JLabel("Totaal: €0");
        webPr = new JLabel("Firewall: €0");
        dbPr = new JLabel("Web: €0");
        fwPr = new JLabel("Database: €0");
        lol.gridy = 7; 
        beschikbaarheidPanel.add(totalPr,lol);
        lol.gridy = 8;  
        beschikbaarheidPanel.add(fwPr,lol);
        lol.gridy = 9;  
        beschikbaarheidPanel.add(webPr,lol);
        lol.gridy = 10;  
        beschikbaarheidPanel.add(dbPr,lol);
        add(dPanel);
    }

    public void setAvails(double totalAv, double fwAv, double webAv, double dbAv){
        this.totalAv.setText("Totaal: " + totalAv +"%");
        this.fwAv.setText("Firewall: " + fwAv +"%");
        this.webAv.setText("Web: " + webAv +"%");
        this.dbAv.setText("Database: " + dbAv +"%");
    }

    public void setPrices(double totalPr, double fwPr, double webPr, double dbPr){
        this.totalPr.setText("Totaal: €" + totalPr);
        this.fwPr.setText("Firewall: €" + fwPr);
        this.webPr.setText("Web: €" + webPr);
        this.dbPr.setText("Database: €" + dbPr);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn){
            Double availability, priceDbl;
            try {
                availability = Double.parseDouble(availTxt.getText());
            } catch (Exception ex) {
                availability = 0.0;
            }
            
            try {
                priceDbl = Double.parseDouble(priceTxt.getText());
            } catch (Exception ex) {
                priceDbl = 0.0;
            }

            dPanel.addServer(new servComponent(availability, nameTxt.getText(), (servComponent.serverType)componentBox.getSelectedItem(), priceDbl));
            dPanel.repaint();
        }
        else if (e.getSource() == loadBtn){
            JFileChooser f = new JFileChooser();
            f.setFileFilter(new FileNameExtensionFilter("Ontwerp bestand", "ontwerp"));
            f.setDialogTitle("Kies een plek om het ontwerp op te slaan.");
            try {
                Files.createDirectories(Path.of(System.getProperty("user.dir")+"\\ontwerpen"));
            } catch (Exception ex) {
                System.out.println("could not create dir");
            }
            f.setCurrentDirectory(new File(System.getProperty("user.dir")+"\\ontwerpen"));
            int result = f.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION){
                File fi= f.getSelectedFile();
                try {
                    dPanel.loadDesign(Files.readString(Path.of(fi.getAbsolutePath())));
                } catch (Exception exception) {
                    System.out.println("eww error");
                }
            }
        }
        else if (e.getSource() == saveBtn){
            String name = JOptionPane.showInputDialog("Ontwerp naam:");
            if (name != null){
                if (!name.isEmpty()){
                    Path savePath = Path.of(System.getProperty("user.dir") + "\\ontwerpen");
                    try {
                        File f = new File(savePath + "\\" + name+ ".ontwerp");
                        f.getParentFile().mkdirs();
                        FileWriter fw = new FileWriter(f);
                        fw.write(dPanel.getCurrentDesign());
                        fw.close();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        }
    }
}
