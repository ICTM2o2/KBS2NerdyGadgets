import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.jar.Manifest;

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
    private ArrayList<servComponent> removeList = new ArrayList<>();
    private OntwerperForm mainframe; 
    
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
        calculateAvailability(webs, databases, firewalls);
        calculatePrices(webs, databases, firewalls);
    }

    dragPanel(OntwerperForm frame){
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
        mainframe = frame;
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
            g.drawString("Prijs: € " + servComponent.getPrice(), (int)servComponent.currentPt.getX() + 5, (int)servComponent.currentPt.getY() + servComponent.getImage().getIconHeight() + 40);
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
                    if (e.getButton() == 3){
                        removeList.add(servComponent);
                    }
                    else{
                        dragValid = true;
                        currentServ = servComponent;
                        return;
                    }
                    
                }
                else{
                    dragValid = false;
                }
            }
            compList.removeAll(removeList);
            firewalls.removeAll(removeList);
            databases.removeAll(removeList);
            webs.removeAll(removeList);
            calculateAvailability(webs, databases, firewalls);
            calculatePrices(webs, databases, firewalls);
            repaint();
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

    private void calculateAvailability(ArrayList<servComponent> webs, ArrayList<servComponent> dbs, ArrayList<servComponent> fws){
        // 90% -> 0.1   -> 0.1 ^ aantal servers    ->   (1 - 0.1^x) = availability
        BigDecimal webAv = new BigDecimal("1"); //
        for (servComponent w : webs) {
            BigDecimal temp = BigDecimal.valueOf(w.getAvailability()/100);
            temp = new BigDecimal("1").subtract(temp);
            webAv = webAv.multiply(temp);
        }
        BigDecimal dbAv = new BigDecimal("1");
        for (servComponent d : dbs) {
            BigDecimal temp = BigDecimal.valueOf(d.getAvailability()/100);
            temp = new BigDecimal("1").subtract(temp);
            dbAv = dbAv.multiply(temp);
        }
        BigDecimal fwAv = new BigDecimal("1");
        for (servComponent f : fws) {
            BigDecimal temp = BigDecimal.valueOf(f.getAvailability()/100);
            temp = new BigDecimal("1").subtract(temp);
            fwAv = fwAv.multiply(temp);
        }
        double total =  new BigDecimal("1").subtract(fwAv.multiply(dbAv.multiply(webAv))).multiply(new BigDecimal("100")).doubleValue();
        double web = new BigDecimal("1").subtract(webAv).multiply(new BigDecimal("100")).doubleValue();
        double db = new BigDecimal("1").subtract(dbAv).multiply(new BigDecimal("100")).doubleValue();
        double fw = new BigDecimal("1").subtract(fwAv).multiply(new BigDecimal("100")).doubleValue();
        mainframe.setAvails(total,fw, web, db);
    }

    private void calculatePrices(ArrayList<servComponent> webs, ArrayList<servComponent> dbs, ArrayList<servComponent> fws){
        double webT = 0;
        double dbsT = 0;
        double fwsT = 0;
        double T = 0;
        for (servComponent i : webs) {
            webT += i.getPrice();
        }
        for (servComponent i : dbs) {
            dbsT += i.getPrice();
        }
        for (servComponent i : fws) {
            fwsT += i.getPrice();
        }
        T = webT + dbsT + fwsT;
        mainframe.setPrices(T, fwsT, webT,dbsT);
    }

    private void clearServers(){
        compList.clear();
        firewalls.clear();
        databases.clear();
        webs.clear();
    }

    public void loadDesign(String designString){
        clearServers();
        for (String serv : designString.split("\n")) {
            String[] temp = serv.split("[|]");//avail name type x y 
            servComponent.serverType type; 
            switch (temp[2]){
                case "fw":
                    type = servComponent.serverType.FIREWALL;
                    break;
                case "db":
                    type = servComponent.serverType.DATABASE;
                    break;
                case "web":
                    type = servComponent.serverType.WEB;
                    break;
                default:
                    type = servComponent.serverType.FIREWALL;
                    break;
            }
            addServer(new servComponent(Double.parseDouble(temp[0]), temp[1], type, new Point((int)Double.parseDouble(temp[3]), (int)Double.parseDouble(temp[4])), Double.parseDouble(temp[5])));
        }
        repaint();
    }

    public String getCurrentDesign() {
        String design = "";
        for (servComponent servComponent : compList) {
            //Double availability, String name, serverType type, Point startPoint
            String tempType;
            switch (servComponent.getType()){
                case FIREWALL:
                    tempType = "fw";
                    break;
                case DATABASE:
                    tempType = "db";
                    break;
                case WEB:
                    tempType = "web";
                    break;
                default:
                    tempType = "";
                    break;
            }
            design += servComponent.getAvailability().toString() + "|" + servComponent.getName() + "|" + tempType + "|" + servComponent.currentPt.getX() + "|" + servComponent.currentPt.getY() + "|" + servComponent.getPrice();
            design += "\n";
        }
        return design.substring(0, design.length() - 1);
    }
}
