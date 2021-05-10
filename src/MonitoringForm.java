import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MonitoringForm extends JFrame implements ActionListener{
    // declare a new container to get the content pain
    Container window = getContentPane();

    public MonitoringForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Server Monitoring");
        setBounds(0, 0, 650, 550);



        setVisible(true);

    }

    /* Methods ==================================================== */
    public void createWindow(String title, int width, int height) {
        // set title, visibility, size and default close operation
        setTitle(title);
        setVisible(true);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    /* Listeners ================================================== */


}