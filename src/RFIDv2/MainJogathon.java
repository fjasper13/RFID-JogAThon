package RFIDv2;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.OutputStream;
import java.sql.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainJogathon {
    static OutputStream outCOM3, outCOM4;
    static String dataToPass;
    static boolean allIDDetected = false;
    public static ArrayList<Runner> runners;
    public static long donationPool = 0;

    //    Connect to database
    public static Connection connect() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/acees_jogathon", "username", "password");
        return conn;
    }

    //    Registering runners from Database
    public static void initializeData() throws Exception {
        runners = new ArrayList<>();
//        Initialize connection to database
        Connection conn = connect();
//        Register the runners
        String query = "SELECT * FROM registration";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Registering runners . . .");
        while (resultSet.next()) {
            runners.add(new Runner(resultSet.getInt("no_Peserta"), resultSet.getString("nama"),
                    resultSet.getString("gender"), resultSet.getInt("kategori_ID"), resultSet.getInt("target_Lap"),
                    resultSet.getInt("donasi_Lap"), resultSet.getInt("donatur"), conn));
        }
        System.out.println(runners.size() + " runners registered");
//        Initialize donation
        for (Runner runner : runners) {
            donationPool += runner.getValue();
        }
    }

    //    Update total donation
    public static void updateDonation(int id) {
        donationPool += getRunners().get(id).getDONATION();
    }

    public static long getDonationPool() {
        return donationPool;
    }

    public static ArrayList<Runner> getRunners() {
        return runners;
    }

    //    Adding record
    public static void addRecordMacro(Runner runner, int id) throws Exception {
        runner.addRecord();
//        Don't add donation if target laps achieved
        if (runner.getLaps() > runner.getTARGET()) {
            System.out.println("====== Exceed lap target ======");
        }
//        System.out.println("Total donation: " + getDonationPool());
        System.out.println("ID = " + id + " , " + runner.getNUM() + " " + runner.getNAME() + ": "
                + runner.getLaps() + " laps");
//        printLaps(runner.getNUM(), runner.getLaps());
//        printDonation(getDonationPool());
    }

    public static void printLaps(int num, int laps) throws Exception {
        String send = "$STEXT^" + num+ " Laps^2^\r\n";
        byte[] bytes = send.getBytes();
        System.out.println(bytes);
        outCOM3.write(bytes);
        outCOM3.flush();
    }

    public static void printDonation(double text) throws Exception {
        String money = NumberFormat.getCurrencyInstance().format(text);
        String send = "$STEXT^" + money + "^5^";
//                String send = "$STEXT^" + text + "^0^<CR><LF>\n";
        outCOM3.write(send.getBytes());
        outCOM3.flush();
    }

    public static void main(String[] args) throws Exception {
//        1. Initialize Program
        initializeData();
        System.out.println("== JOGATHON 2018 ==");
        System.out.println("Total donation: " + getDonationPool());
//        For RT output donation
//        SerialPort com3 = SerialPort.getCommPort("COM3");
//        com3.setComPortParameters(9600, 8, 1, 0);
//        com3.openPort();
//        outCOM3 = com3.getOutputStream();

////        For RT output runner
//        SerialPort com10 = SerialPort.getCommPort("COM10");
//        com10.setComPortParameters(9600, 8, 1, 0);
//        com10.openPort();
//        outCOM10 = com10.getOutputStream();

//        For RFID Scanner
        SerialPort com5 = SerialPort.getCommPort("COM3");
        com5.setComPortParameters(57600, 8, 1, 0);

//        2. Start detecting ID
        while (true) {
            if (!com5.isOpen()) {
                allIDDetected = false;
            }
            com5.openPort();
            com5.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
            com5.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                @Override
                public void serialEvent(SerialPortEvent event) {
                    if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                        return;
                    try {
                        while (true) {
                            byte[] newData = new byte[1024];
                            int numRead = com5.readBytes(newData, newData.length);
                            if (numRead > 0) {
                                dataToPass = new String(newData);
//                                System.out.print(dataToPass);
                                String[] splitText = dataToPass.split("\n");
                                for (int i = 0; i < splitText.length - 1; i++) {
                                    //System.out.println(splitText[i]);
                                    if (!splitText[i].contains("Program")) {
//                                        // ONLY ID
//                                        System.outCOM3.printLaps("ID " + splitText[i].split("\t")[0].split(" ")[2]);
//                                        // ONLY LAP
//                                        System.outCOM3.println(" LAP " + splitText[i].split("\t")[1].split(" ")[3]);
                                        if (splitText[i].split("\t").length == 3) {
                                            int id = Integer.parseInt(splitText[i].split("\t")[0].split(" ")[3]);
                                            for (Runner runner : runners) {
//                                                System.out.println(runner.getID());
                                                if (runner.getID() == id) {
//                                                    Cek apakah sudah ada record sebelumnya
                                                    if (runner.getRecordsSize() == 0) {
                                                        runner.startRun();
                                                        System.out.println(runner.getNUM() + " ,  ID =  " + id + " launch!");
                                                    } else {
                                                        if (runner.isRunning()) {
                                                            addRecordMacro(runner, runner.getID());
                                                        } else {
                                                            System.out.println(runner.getNUM() + " is no longer running");
                                                        }
                                                    }
                                                }
                                            }
                                        } else {

                                            /**
                                             * FOR DEBUGGING
                                             * String coba = splitText[i].split("\t")[0];
                                             *                                             System.out.println(coba);
                                             *                                             String iniID = coba.split(" ")[3];
                                             *                                             System.out.println(iniID);
                                             */

//                                            int id = Integer.parseInt(splitText[i].split("\t")[0].split(" ")[3]);
                                            String splitpertama = splitText[i];
                                            String id2 = splitpertama.split("\t")[0];
                                            int id = Integer.parseInt(id2.split(" ")[3]);
//                                            System.out.println(id);
//                                            int id = 0;
                                            for (Runner runner : runners) {
                                                if (runner.getID() == id) {
                                                    addRecordMacro(runner, id);
                                                    break;
                                                }
                                            }
//                                            }
                                        }
                                    } else {
//                                        System.out.println(splitText[i].substring(0, 17));
                                        System.out.println(splitText[i]);
                                    }
                                }
//                                System.outCOM3.println(numRead); // Printing the size of the buffer received in bytes
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}