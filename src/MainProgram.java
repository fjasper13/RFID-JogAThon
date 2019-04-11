import RFIDv2.Runner;

import java.sql.*;
import java.util.ArrayList;

public class MainProgram {
    private static Connection conn = null;

    public static Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error Driver");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jogathon","root", "");
        } catch (SQLException e) {
            System.out.println("Error Connection");
            e.printStackTrace();
        }
        return conn;
    }


    public static void main(String[] args) {
        ArrayList<Runner> runners = new ArrayList<>();
//        Register the runners
//        Insert Code here

//        Start Run!!

    }
}