package RFIDv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by ASUS on 28/03/2018.
 */
public class Category {
    private int ID;
    private String name;
    private int fixedDonation;
    private Connection conn;

//    Constructor
    public Category(int ID, Connection connection) throws Exception{
        this.ID = ID;
        this.conn = connection;
        String sql = "SELECT * FROM kategori";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            if (resultSet.getInt("kategori_ID") == getID()){
                setName(resultSet.getString("kategori_Name"));
                setFixedDonation(resultSet.getInt("donasi_tetap"));
            }
        }
    }

//    Getters
    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public int getFixedDonation() {
        return fixedDonation;
    }

//    Setters
    private void setName(String name) {
        this.name = name;
    }
    private void setFixedDonation(int fixedDonation) {
        this.fixedDonation = fixedDonation;
    }
}
