import java.math.BigDecimal;
import java.util.ArrayList;

public class BTavail {
    public static void main(String[] args) {
        ArrayList<servComponent> allComps = new ArrayList<>();
        servComponent f = new servComponent(99.998, "pfsense", servComponent.serverType.FIREWALL, 4000);
        servComponent w = new servComponent(80.00, "web01", servComponent.serverType.WEB, 2200);
        servComponent w1 = new servComponent(90.00, "web02", servComponent.serverType.WEB, 3200);
        servComponent d = new servComponent(90.00, "db01", servComponent.serverType.DATABASE, 5100);
        servComponent d1 = new servComponent(95.00, "db02", servComponent.serverType.DATABASE, 7700);
        allComps.add(f);
        allComps.add(w);
        allComps.add(d);
        allComps.add(w1);
        allComps.add(d1);
        solve(allComps, new ArrayList<servComponent>(), 99.99, 70000, 15);//37600
    }

    static ArrayList<servComponent> finalList = new ArrayList<>();

    public static void printComps(ArrayList<servComponent> components){
        System.out.println("========");
        System.out.println("price: "+calcPrice(components));
        for (servComponent c : components) {
            System.out.println(c.getName());
        }
        System.out.println("========");
    }

    public static int curlow = Integer.MAX_VALUE;

    public static void solve(ArrayList<servComponent> allComps, ArrayList<servComponent> currentComps, double desiredAvailability, int hardPriceLimit, int hardCompLimit){
        if (isSolution(currentComps, desiredAvailability) && calcPrice(currentComps) < curlow && calcPrice(currentComps) <= hardPriceLimit){

            curlow = calcPrice(currentComps);
            finalList = currentComps;
            printComps(currentComps);
            return;
        }

        for (servComponent servComponent : allComps) {
            ArrayList<servComponent> temp = new ArrayList<>();//create temporary list
            temp.addAll(currentComps);//add all previous things in tree
            temp.add(servComponent);

            //create temporary allcomps list without the comps that are cheaper than servComponent | sort on price???
            ArrayList<servComponent> tempAll = new ArrayList<>();//create temporary list
            for (servComponent c : allComps) {
                if (c.getPrice() >= servComponent.getPrice()){
                    tempAll.add(c);
                }
            }
            if (temp.size() <= hardCompLimit){
                solve(tempAll, temp, desiredAvailability, hardPriceLimit, hardCompLimit);//recurse
            }
        }
        
    }

    public static int calcPrice(ArrayList<servComponent> currentComps){
        int total = 0;
        for (servComponent c : currentComps) {
            total += c.getPrice();
        }
        return total;
    }

    public static boolean isSolution(ArrayList<servComponent> currentComps, double desiredAvailability){
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
            if (!webs.isEmpty()) {
                BigDecimal temp = BigDecimal.valueOf(w.getAvailability()/100);
                temp = new BigDecimal("1").subtract(temp);
                webAv = webAv.multiply(temp);
            }
            else{
                webAv = new BigDecimal("0");
            }
        }
        BigDecimal dbAv = new BigDecimal("1");
        for (servComponent d : dbs) {
            if (!dbs.isEmpty()) {
                BigDecimal temp = BigDecimal.valueOf(d.getAvailability()/100);
                temp = new BigDecimal("1").subtract(temp);
                dbAv = dbAv.multiply(temp);
            }
            else{
                dbAv = new BigDecimal("0");
            }
        }
        BigDecimal fwAv = new BigDecimal("1");
        for (servComponent f : fws) {
            if (!fws.isEmpty()) {
                BigDecimal temp = BigDecimal.valueOf(f.getAvailability()/100);
                temp = new BigDecimal("1").subtract(temp);
                fwAv = fwAv.multiply(temp);
            }
            else{
                fwAv = new BigDecimal("0");
            }
        }
        double web = new BigDecimal("1").subtract(webAv).multiply(new BigDecimal("100")).doubleValue();
        double db = new BigDecimal("1").subtract(dbAv).multiply(new BigDecimal("100")).doubleValue();
        double fw = new BigDecimal("1").subtract(fwAv).multiply(new BigDecimal("100")).doubleValue();
        double total =  new BigDecimal("1").subtract(fwAv.multiply(dbAv.multiply(webAv))).multiply(new BigDecimal("100")).doubleValue();
        return (total >= desiredAvailability && web >= desiredAvailability && fw >= desiredAvailability && db >= desiredAvailability);
        
    }
}