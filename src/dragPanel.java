import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

//joinked from https://www.youtube.com/watch?v=_fsee3Nu15U&t=88s&ab_channel=BroCode

public class dragPanel extends JPanel {
    Point prevPt;
    boolean dragValid = false;
    private ArrayList<servComponent> compList = new ArrayList<>();
    private ArrayList<servComponent> firewalls = new ArrayList<>();
    private ArrayList<servComponent> databases = new ArrayList<>();
    private ArrayList<servComponent> webs = new ArrayList<>();
    private servComponent currentServ;
    
    public void addServer(servComponent server){
        switch (server.getType()){
            case FIREWALL:
                firewalls.add(server);
                break;
            case DATABASE:
                databases.add(server);
                break;
            case WEB:
                webs.add(server);
                break;
            default:
                System.out.println("not a type");
                break;
        }
        compList.add(server);
    }

    
    dragPanel(){
        servComponent web1 = new servComponent(50.0, "WEB01", servComponent.serverType.WEB);
        servComponent web2 = new servComponent(50.0, "WEB01", servComponent.serverType.WEB);
        servComponent db1 = new servComponent(50.0, "DB01", servComponent.serverType.DATABASE);  
        servComponent db2 = new servComponent(50.0, "DB02", servComponent.serverType.DATABASE);
        servComponent fw1 = new servComponent(50.0, "FW1", servComponent.serverType.FIREWALL);    
        addServer(web1);
        addServer(web2);
        addServer(db1);
        addServer(db2);
        addServer(fw1);
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.darkGray);
        for (servComponent web : webs) {
            for (servComponent web1 : webs) { //line between web and web
                g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
                g2d.drawLine((int)web.currentPt.getX() + web.getImage().getIconWidth() / 2,(int)web.currentPt.getY() + web.getImage().getIconHeight() / 2, (int)web1.currentPt.getX() + web1.getImage().getIconWidth() / 2,(int)web1.currentPt.getY() + web1.getImage().getIconHeight() / 2);
                g2d.setStroke(new BasicStroke(2));
            }
            for (servComponent db : databases) {//line between web and db
                g2d.drawLine((int)web.currentPt.getX() + web.getImage().getIconWidth() / 2,(int)web.currentPt.getY() + web.getImage().getIconHeight() / 2, (int)db.currentPt.getX() + db.getImage().getIconWidth() / 2,(int)db.currentPt.getY() + db.getImage().getIconHeight() / 2);
            }
        }
        for (servComponent db : databases) {
            for (servComponent db1 : databases){//line between db and db
                g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
                g2d.drawLine((int)db1.currentPt.getX() + db1.getImage().getIconWidth() / 2,(int)db1.currentPt.getY() + db1.getImage().getIconHeight() / 2, (int)db.currentPt.getX() + db.getImage().getIconWidth() / 2,(int)db.currentPt.getY() + db.getImage().getIconHeight() / 2);
                g2d.setStroke(new BasicStroke(2));
            }
        }
        for (servComponent firewall : firewalls) {
            for (servComponent web : webs) { //line between firewall and web
                g2d.drawLine((int)web.currentPt.getX() + web.getImage().getIconWidth() / 2,(int)web.currentPt.getY() + web.getImage().getIconHeight() / 2, (int)firewall.currentPt.getX() + firewall.getImage().getIconWidth() / 2,(int)firewall.currentPt.getY() + firewall.getImage().getIconHeight() / 2);
            }
        }
        for (servComponent servComponent : compList) {
            servComponent.getImage().paintIcon(this, g, (int)servComponent.currentPt.getX(), (int)servComponent.currentPt.getY());
            g.setColor(Color.black);
            g.drawString(servComponent.getName(), (int)servComponent.currentPt.getX() + 5, (int)servComponent.currentPt.getY()  + servComponent.getImage().getIconHeight() + 10);
            g.drawString("beschikbaarheid: " + servComponent.getAvailability() + "%", (int)servComponent.currentPt.getX() + 5, (int)servComponent.currentPt.getY() + servComponent.getImage().getIconHeight() + 25);
        }
    }
    
    private class ClickListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {  
            prevPt = e.getPoint(); 
            for (servComponent servComponent : compList) {
                if ((e.getPoint().getX() > servComponent.currentPt.getX()) && 
                (e.getPoint().getX() < (servComponent.currentPt.getX() + servComponent.getImage().getIconWidth())) &&
                (e.getPoint().getY() > servComponent.currentPt.getY()) &&
                (e.getPoint().getY() < (servComponent.currentPt.getY() + servComponent.getImage().getIconHeight()))){
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
        @Override
        public void mouseDragged(MouseEvent e) { 
            if(dragValid){
                Point currentPt = e.getPoint();    
                currentServ.currentPt.translate(
                        (int)(currentPt.getX() - prevPt.getX()),
                        (int)(currentPt.getY() - prevPt.getY())
                );
                prevPt = currentPt;
                repaint();
            }
        }
    }
}
