import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;

public class OptimalisatieForm extends JFrame implements ActionListener {
    @Serial
    private static final long serialVersionUID = -1450150616029361695L;
    private GridBagConstraints gbc = new GridBagConstraints();

    //buttons etc
    private JButton btnServerToevoegen;
    private JButton btnOptimaliseren;

    public OptimalisatieForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Server Optimalisatie");
        setBounds(0, 0, 800, 700);

        setLayout(new GridBagLayout());

        //button1
        gbc.insets = new Insets(0,0,100,0);
        gbc.gridy = 1;
        btnServerToevoegen = new JButton("Server Toevoegen");
        btnServerToevoegen.addActionListener(this);
        add(btnServerToevoegen,gbc);

        //button2
//        gbc.gridy = 2;
//        btnOptimaliseren = new JButton("Optimaliseren");
//        btnOptimaliseren.addActionListener(this);
//        add(btnOptimaliseren,gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    }
}