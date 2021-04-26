import javax.swing.ImageIcon;
import java.awt.Point;

public class servComponent {
    private ImageIcon image;
    private Double availability;
    private String name;
    private serverType type;
    public Point currentPt;
    
    
    enum serverType {
        FIREWALL,
        DATABASE,
        WEB
    }



    public servComponent(Double availability, String name, serverType type, Point startPoint) {
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
            this.availability = Math.round(availability * 100.0) / 100.0;
        }
        

        this.name = name;
        this.type = type;
        setCurrentPt(startPoint);
    }

    public servComponent(Double availability, String name, serverType type){
        this(availability, name, type, new Point(10,10));
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