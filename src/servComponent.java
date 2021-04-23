import javax.swing.ImageIcon;
import java.awt.Point;

public class servComponent {
    private ImageIcon image;
    private int availability;
    private String name;
    private Point currentPt;
    
    public servComponent(ImageIcon image, int availability, String name) {
        this.image = image;
        this.availability = availability;
        this.name = name;
        setCurrentPt(new Point(0,0));
    }

    public Point getCurrentPt() {
        return currentPt;
    }

    public void setCurrentPt(Point currentPt) {
        this.currentPt = currentPt;
    }

    public ImageIcon getImage() {
        return image;
    }

    public int getAvailability() {
        return availability;
    }

    public String getName() {
        return name;
    }
}
