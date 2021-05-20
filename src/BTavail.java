import java.math.BigDecimal;
import java.util.ArrayList;

public class BTavail {
    public static void main(String[] args) {
        ArrayList<servComponent> allComps = new ArrayList<>();
        servComponent f = new servComponent(99.99, "pfsense", servComponent.serverType.FIREWALL, 7000);
        servComponent w = new servComponent(80.00, "web01", servComponent.serverType.WEB, 7000);
        servComponent d = new servComponent(80.00, "db01", servComponent.serverType.DATABASE, 7000);
        allComps.add(f);
        allComps.add(w);
        allComps.add(d);
        solve(allComps, new ArrayList<servComponent>(), Integer.MAX_VALUE, 99.99, 70000, 0, 1000);
    }


    public static boolean solve(ArrayList<servComponent> allComps, ArrayList<servComponent> currentComps, int currentLowestPrice, double desiredAvailability, int hardPriceLimit, int currentStepCount, int hardCompLimit){

        if (currentStepCount >= hardCompLimit){
            return false;
        }
        else if (isValid(currentComps, desiredAvailability, hardPriceLimit)){
            for (servComponent c : currentComps) {
                System.out.println(c.getName());
            }
            return true;
        }

        for (servComponent servComponent : allComps) {
            ArrayList<servComponent> temp = new ArrayList<>();//create temporary list
            temp.addAll(currentComps);//add all previous things in tree
            temp.add(servComponent);
            if (!isValid(temp, desiredAvailability, hardPriceLimit)){
                if(solve(allComps, temp, currentLowestPrice, desiredAvailability, hardPriceLimit, currentStepCount++, hardCompLimit)){
                    System.out.println("--------awnser------");
                    for (servComponent c : temp) {
                        System.out.println(c.getName());
                    }
                    System.out.println("--------------------");
                    
                }
            }
        }
        return false;
    }

    public static boolean isValid(ArrayList<servComponent> currentComps, double desiredAvailability, int hardPriceLimit){
        ArrayList<servComponent> webs = new ArrayList<>();
        ArrayList<servComponent> dbs = new ArrayList<>();
        ArrayList<servComponent> fws = new ArrayList<>();
        for (servComponent comp : currentComps) {
            switch (comp.getType()){
                case FIREWALL:
                    fws.add(comp);
                    break;
                case DATABASE:
                    dbs.add(comp);
                    break;
                case WEB:
                    webs.add(comp);
                    break;
                default:
                    break;
            }
        }
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
        
        System.out.println("fw: "+fw);
        System.out.println("web: "+web);
        System.out.println("db: "+db);
        System.out.println("total: "+total);

        return !(total < desiredAvailability || web < desiredAvailability || db < desiredAvailability || fw < desiredAvailability);
        
    }
}