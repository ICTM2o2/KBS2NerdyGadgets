import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

//joinked from https://www.youtube.com/watch?v=_fsee3Nu15U&t=88s&ab_channel=BroCode

public class dragPanel extends JPanel {
    ImageIcon image = new ImageIcon("images/server.png");
    final int WIDTH = image.getIconWidth();
    final int HEIGHT = image.getIconHeight();
    Point imageCorner;
    Point prevPt;
    boolean dragValid = false;
    private ArrayList<servComponent> compList = new ArrayList<servComponent>();
    private servComponent currentServ;
    servComponent server1 = new servComponent(image, 50, "server1");
    
    servComponent server2 = new servComponent(image, 50, "server2");
    

    public void addServer(servComponent server){
        compList.add(server);
    }

    
    dragPanel(){
        server1.currentPt = new Point(100,100);
        compList.add(server1);
        compList.add(server2);
        imageCorner = new Point(0,0);
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (servComponent servComponent : compList) {
            servComponent.getImage().paintIcon(this, g, (int)servComponent.currentPt.getX(), (int)servComponent.currentPt.getY());
            g.drawString(servComponent.getName(), (int)servComponent.currentPt.getX() + 5, (int)servComponent.currentPt.getY() + servComponent.getImage().getIconHeight() + 10);
        }

        //image.paintIcon(this, g, (int)imageCorner.getX(), (int)imageCorner.getY());
    }
    
    private class ClickListener extends MouseAdapter{
        public void mousePressed(MouseEvent e) {  
            prevPt = e.getPoint(); 
            /*
            dragValid = (e.getPoint().getX() > imageCorner.getX()) && 
                    (e.getPoint().getX() < (imageCorner.getX() + WIDTH)) &&
                    (e.getPoint().getY() > imageCorner.getY()) &&
                    (e.getPoint().getY() < (imageCorner.getY() + HEIGHT));
            */
            
            
            for (servComponent servComponent : compList) {
                if ((e.getPoint().getX() > servComponent.currentPt.getX()) && 
                (e.getPoint().getX() < (servComponent.currentPt.getX() + WIDTH)) &&
                (e.getPoint().getY() > servComponent.currentPt.getY()) &&
                (e.getPoint().getY() < (servComponent.currentPt.getY() + HEIGHT))){
                    dragValid = true;
                    currentServ = servComponent;
                    return;
                }
                else{
                    dragValid = false;
                }
            }
                
        }
    }

    private class DragListener extends MouseMotionAdapter{
        public void mouseDragged(MouseEvent e) { 
                if(dragValid){
                    Point currentPt = e.getPoint();    
                    currentServ.currentPt.translate(
                            (int)(currentPt.getX() - prevPt.getX()),
                            (int)(currentPt.getY() - prevPt.getY())
                    );
                    prevPt = currentPt;//
                    repaint();
                }
        }
    }
}
