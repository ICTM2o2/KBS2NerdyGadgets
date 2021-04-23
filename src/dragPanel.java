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
    

    public void addServer(servComponent server){
        //compList.add
    }

    
    dragPanel(){
        compList.add(server1);
        imageCorner = new Point(0,0);
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (servComponent servComponent : compList) {
            image.paintIcon(this, g, (int)servComponent.getCurrentPt().getX(), (int)servComponent.getCurrentPt().getY());
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
                if ((e.getPoint().getX() > servComponent.getCurrentPt().getX()) && 
                (e.getPoint().getX() < (servComponent.getCurrentPt().getX() + WIDTH)) &&
                (e.getPoint().getY() > servComponent.getCurrentPt().getY()) &&
                (e.getPoint().getY() < (servComponent.getCurrentPt().getY() + HEIGHT))){
                    dragValid = true;
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
                    imageCorner.translate(
                            (int)(currentPt.getX() - prevPt.getX()),
                            (int)(currentPt.getY() - prevPt.getY())
                    );
                    prevPt = currentPt;//
                    repaint();
                }
        }
    }
}
