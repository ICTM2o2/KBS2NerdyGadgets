public class Main {
    private static HomeForm form1;
    public static void main(String[] args) {
        form1 = new HomeForm();
        form1.setVisible(true);

        OntwerperForm o = new OntwerperForm();
        o.setVisible(true);

        OptimalisatieForm p = new OptimalisatieForm();
        p.setVisible(true);

        MonitoringForm m = new MonitoringForm();
        m.setVisible(true);


    }
}
