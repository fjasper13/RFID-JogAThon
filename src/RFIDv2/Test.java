package RFIDv2;

import com.fazecast.jSerialComm.SerialPort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by ASUS on 28/03/2018.
 */
public class Test {
    public static void main(String[] args) throws Exception{
        ArrayList<Runner> runners = new ArrayList<>();
        Connection connection = MainJogathon.connect();
        String query = "SELECT * FROM registration";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Registering runners . . .");
        while (resultSet.next()){
            runners.add(new Runner(resultSet.getInt("no_Peserta"), resultSet.getString("nama"),
                    resultSet.getString("gender"), resultSet.getInt("kategori_ID"), resultSet.getInt("target_Lap"),
                    resultSet.getInt("donasi_Lap"), resultSet.getInt("donatur"), connection));
        }
        System.out.println(runners.size() + " runners registered");
        int i = 0;
        int total = 0;
        int totalLaps = 0;
//        Initialize donation
        for (Runner runner : runners){
            if (runner.getLaps() < 2){
                query = "SELECT * FROM record WHERE runner_ID = " + runner.getNUM();
                preparedStatement = connection.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    i++;
                    System.out.println(i + ": " + resultSet.getString("runner_ID") + " - " + (runner.getDONATION()+runner.getCategory().getFixedDonation()));
                    total += (runner.getDONATION()+runner.getCategory().getFixedDonation());
                    totalLaps += runner.getLaps();
                }
            }
        }
        System.out.println("Donation: " + total);
        System.out.println("Laps: " + totalLaps);

    }
}
