import java.util.ArrayList;
import java.sql.*;
        


public class Connect {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        ArrayList<String> servernames = new ArrayList<String>();
        ArrayList<Integer> storage_used = new ArrayList<Integer>();
        ArrayList<Integer> storage_free = new ArrayList<Integer>();
        ArrayList<Double> cpu_load = new ArrayList<Double>();

        String url = "jdbc:mysql://localhost:3306/nerdygadgets2";
        String username="root", password= "";

        Connection connection = DriverManager.getConnection( url,username,password );
        Class.forName("com.mysql.jdbc.Driver");

        Statement statement = connection.createStatement();

        ResultSet rs =
                statement.executeQuery(
                        "SELECT * FROM monitoring_info");


        while( rs.next() ) {

            servernames.add(rs.getString("servernames"));  // kolom ‘Naam’
            storage_used.add(rs.getInt("storage_used"));
            storage_free.add(rs.getInt("storage_free"));
            cpu_load.add(rs.getDouble("cpu_load"));// 3e kolom

            System.out.println(servernames + " " + storage_used + storage_free + cpu_load);
        }







//        public ArrayList<Servers> server_info(){
//            ArrayList<Servers> servers = new ArrayList<Servers>();
//            for(int i=0; i<servernames.size(); i++){
//                servers.add(new Servers((String)servernames.get(i), (int)storage_used.get(i), (int)storage_free.get(i), (Double)cpu_load.get(i)));
//            }
//            return servers;
//        }




    }
}
