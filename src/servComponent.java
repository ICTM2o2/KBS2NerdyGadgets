import javax.swing.ImageIcon;
import java.awt.Point;

public class servComponent {
    private ImageIcon image;
    private Double availability;
    private String name;
    private serverType type;
    public Point currentPt;
    private double price;
    
    
    enum serverType {
        FIREWALL,
        DATABASE,
        WEB
    }

    

    public servComponent(Double availability, String name, serverType type, Point startPoint, double price) {
        switch (type){
            case WEB:
                this.image = new ImageIcon("images/web.png");
                break;
            case DATABASE:
                this.image = new ImageIcon("images/database.png");
                break;
            case FIREWALL:
                this.image = new ImageIcon("images/firewall.png");
                break;
            default:
                this.image = new ImageIcon("images/imagenotfound.png");
                System.out.println("Eww cringe error");
                break;
        }
        if (availability > 100){
            this.availability = 100.0;
        }else if (availability <= 0){
            this.availability = 0.01;
        }
        else{
            this.availability = Math.round(availability * 10000.0) / 10000.0; //4 decimal
        }
        
        setPrice(price);
        this.name = name;
        this.type = type;
        setCurrentPt(startPoint);
    }
    
    public servComponent(Double availability, String name, serverType type, double price){
        this(availability, name, type, new Point(10,10),price);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0){
            this.price = 0;
        }
        this.price = Math.round(price * 100.0) / 100.0; //2 decimal
    }


    public serverType getType() {
        return type;
    }

    public void setCurrentPt(Point currentPt) {
        this.currentPt = currentPt;
    }

    public ImageIcon getImage() {
        return image;
    }

    public Double getAvailability() {
        return availability;
    }

    public String getName() {
        return name;
    }
}