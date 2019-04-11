import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

/**
 * Created by ASUS on 29/01/2018.
 */
public class RecordGenerator extends MainProgram {
    private static Connection conn = null;

    public static void main(String[] args) {
        conn = connect();

//        Mengulang sejumlah jumlah peserta
        String query = "SELECT no_Peserta FROM registration";
//        T<3J
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Insert thread = new Insert(conn, resultSet.getInt("no_Peserta"));
                thread.start();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static class Insert extends Thread{
        private Connection conn;
        private int noPeserta;
        Insert(Connection conn, int noPeserta){
            this.conn = conn;
            this.noPeserta = noPeserta;
        }

        @Override
        public void run(){
            Random randomNum = new Random();
            String waktuAwal, waktuAkhir = null;
//            double lap;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
            waktuAwal = simpleDateFormat.format(Calendar.getInstance().getTime());

//            Iterasi jumlah lap secara random
            int jumlahLap = randomNum.nextInt(10) + randomNum.nextInt(10);
            for (int i = 0; i < jumlahLap; i++) {
                try {
                    waktuAkhir = simpleDateFormat.format(simpleDateFormat.parse(waktuAwal).getTime() + 95000 + randomNum.nextInt(90000));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String sql = "INSERT INTO record (runner_ID, record_Awal, record_Akhir) VALUES (?, ?, ?)";
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, noPeserta);
                    ps.setString(2, waktuAwal);
                    ps.setString(3, waktuAkhir);

                    ps.execute();
                } catch (SQLException e1) {
                    System.out.println("Print error");
                    e1.printStackTrace();
                } finally {
//                    Waktu akhir lap sebelum dijadikan waktu awal lap selanjutnya
                    waktuAwal = waktuAkhir;
                }
            }
            System.out.println("RFIDv2.Record for runner No. " + noPeserta + " inserted -- " + jumlahLap + " laps");
        }
    }
}