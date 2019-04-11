import java.sql.*;
import java.util.ArrayList;

/**
 * Created by ASUS on 07/03/2018.
 */
public class CharityRun {
    private ArrayList<String> fastestRunner, mostLap, mostExpensive;


    public ArrayList<String> getFastestRunner() {
        return fastestRunner;
    }
    public ArrayList<String> getMostLap() {
        return mostLap;
    }
    public ArrayList<String> getMostExpensive() {
        return mostExpensive;
    }

    public void lapTerbanyak(Connection conn, String gender, int category){
        ResultSet resultSet1, resultSet2;
        String sql = "SELECT * FROM registration WHERE gender = '" + gender + "' AND kategori_ID = '" + category + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            resultSet1 = ps.executeQuery(sql);

//            Dapatkan Nama Kategori
            sql = "SELECT kategori_Name FROM kategori WHERE kategori_ID = '" + category + "'";
            resultSet2 = conn.prepareStatement(sql).executeQuery();
            resultSet2.next();
            System.out.println("Pelari Kategori " + resultSet2.getString("kategori_Name") + " "
                    + gender + " dengan jumlah lap terbanyak");

            int thisLapCount;
            long firstLapCount = 0, secondLapCount = 0, thirdLapCount = 0;
            String firstPlace = "", secondPlace = "", thirdPlace = "";
            while (resultSet1.next()) {
                sql = "SELECT COUNT(runner_ID) AS jumlah FROM record WHERE runner_ID=" + resultSet1.getString("no_Peserta");
                ps = conn.prepareStatement(sql);
                resultSet2 = ps.executeQuery();
                resultSet2.next();
                thisLapCount = resultSet2.getInt("jumlah");

                if (thisLapCount > thirdLapCount){
                    thirdLapCount = thisLapCount;
                    thirdPlace = resultSet1.getString("nama");
                }
                if (thisLapCount > secondLapCount) {
                    thirdLapCount = secondLapCount;
                    thirdPlace = secondPlace;
                    secondLapCount = thisLapCount;
                    secondPlace = resultSet1.getString("nama");
                }
                if (thisLapCount > firstLapCount) {
                    secondLapCount = firstLapCount;
                    secondPlace = firstPlace;
                    firstLapCount = thisLapCount;
                    firstPlace = resultSet1.getString("nama");
                }
            }
            mostLap.add(firstPlace);mostLap.add(secondPlace);mostLap.add(thirdPlace);

            System.out.println("Terbanyak no 1: " + firstPlace + " | " + firstLapCount + " laps");
            System.out.println("Terbanyak no 2: " + secondPlace + " | " + secondLapCount + " laps");
            System.out.println("Terbanyak no 3: " + thirdPlace + " | " + thirdLapCount + " laps\n");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public void lapTercepat(Connection conn, String gender, int category) {
        String sql = "SELECT * FROM record";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet1 = ps.executeQuery();

//            Dapatkan Nama Kategori
            sql = "SELECT kategori_Name FROM kategori WHERE kategori_ID = '" + category + "'";
            ResultSet resultSet2 = conn.prepareStatement(sql).executeQuery();
            resultSet2.next();
            System.out.println("Pelari Kategori " + resultSet2.getString("kategori_Name") + " "
                    + gender + " dengan putaran lap tercepat");

            long thisLapTime;
            long firstLapTime = 1000, secondLapTime = 1000, thirdLapTime = 1000;
            String firstPlace = "", secondPlace = "", thirdPlace = "";
            while (resultSet1.next()) {
//                Dapatkan nama pelari
                sql = "SELECT nama FROM registration WHERE no_Peserta = " + resultSet1.getInt("runner_ID") +
                        " AND gender = '" + gender + "' AND kategori_ID = " + category;
                ps = conn.prepareStatement(sql);
                resultSet2 = ps.executeQuery();
                if (resultSet2.next()){
                    Timestamp record_Awal = resultSet1.getTimestamp("record_Awal");
                    Timestamp record_Akhir = resultSet1.getTimestamp("record_Akhir");
                    thisLapTime = ((record_Akhir.getTime() - record_Awal.getTime()) / 1000);

                    if (thisLapTime < thirdLapTime) {
                        thirdPlace = resultSet2.getString("nama");
                        thirdLapTime = thisLapTime;
                    }
                    if (thisLapTime < secondLapTime) {
                        thirdLapTime = secondLapTime;
                        thirdPlace = secondPlace;
                        secondPlace = resultSet2.getString("nama");
                        secondLapTime = thisLapTime;
                    }
                    if (thisLapTime < firstLapTime) {
                        secondLapTime = firstLapTime;
                        secondPlace = firstPlace;
                        firstPlace = resultSet2.getString("nama");
                        firstLapTime = thisLapTime;
                    }
                }
            }
            fastestRunner.add(firstPlace);fastestRunner.add(secondPlace);fastestRunner.add(thirdPlace);

            System.out.println("Tercepat no 1: " + firstPlace + " | " + firstLapTime + " seconds");
            System.out.println("Tercepat no 2: " + secondPlace + " | " + secondLapTime + " seconds");
            System.out.println("Tercepat no 3: " + thirdPlace + " | " + thirdLapTime + " seconds\n");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    public void pelariTermahal(Connection conn, String gender, int category){
        int donation, fixedDonation;
        String sql = "SELECT * FROM registration WHERE gender = '" + gender + "' AND kategori_ID = '" + category + "'";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet resultSet1 = ps.executeQuery();

//            Dapatkan Nama Kategori
            sql = "SELECT kategori_Name FROM kategori WHERE kategori_ID = '" + category + "'";
            ResultSet resultSet2 = conn.prepareStatement(sql).executeQuery();
            resultSet2.next();
            System.out.println("Pelari Kategori " + resultSet2.getString("kategori_Name") + " "
                    + gender + " dengan total donasi terbanyak");

            int thisLapCount;
            long firstDonation = 0, secondDonation = 0, thirdDonation = 0;
            String firstPlace = "", secondPlace = "", thirdPlace = "";
            while (resultSet1.next()) {
                sql = "SELECT COUNT(runner_ID) AS jumlah FROM record WHERE runner_ID=" + resultSet1.getString("no_Peserta");
                ps = conn.prepareStatement(sql);
                resultSet2 = ps.executeQuery();
                resultSet2.next();

                thisLapCount = resultSet2.getInt("jumlah");
                donation = thisLapCount * resultSet1.getInt("donasi_Lap");
//                System.out.println("Pelari no: " + resultSet1.getString("no_Peserta") + " - " + thisLapCount + " laps");

//                Tambah dengan donasi tetap sesuai kategori
                sql = "SELECT donasi_tetap FROM kategori WHERE kategori_ID = '" + category + "'";
                ps = conn.prepareStatement(sql);
                resultSet2 = ps.executeQuery();
                resultSet2.next();
                fixedDonation = resultSet2.getInt("donasi_tetap");
                donation += fixedDonation;

                if (donation > thirdDonation){
                    thirdDonation = donation;
                    thirdPlace = resultSet1.getString("nama");
                }
                if (donation > secondDonation) {
                    thirdDonation = secondDonation;
                    thirdPlace = secondPlace;
                    secondDonation = donation;
                    secondPlace = resultSet1.getString("nama");
                }
                if (donation > firstDonation) {
                    secondDonation = firstDonation;
                    secondPlace = firstPlace;
                    firstDonation = donation;
                    firstPlace = resultSet1.getString("nama");
                }
            }
            mostExpensive.add(firstPlace);mostExpensive.add(secondPlace);mostExpensive.add(thirdPlace);
            System.out.println("Termahal no 1: " + firstPlace + " | " + firstDonation + " rupiah");
            System.out.println("Termahal no 2: " + secondPlace + " | " + secondDonation + " rupiah");
            System.out.println("Termahal no 3: " + thirdPlace + " | " + thirdDonation + " rupiah\n");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

}
