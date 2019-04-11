package RFIDv2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

import static RFIDv2.MainJogathon.connect;


/**
 * Created by ASUS on 28/03/2018.
 */
public class Jogathon {
    public static ArrayList<Runner> runners;
    private static long donationPool;

    //    Final Result Methods
    public static void mostDonors(int category) {
        Runner most = null;
        String categoryName = " ";
        for (int i = 0; i < runners.size(); i++) {
            if (runners.get(i).getCategory().getID() == category) {
                if (most != null) {
                    if (most.getDONORS() < runners.get(i).getDONORS()) {
                        most = runners.get(i);
                    }
                } else {
                    most = runners.get(i);
                    categoryName = most.getCategory().getName();
                }
            }
        }
        System.out.println("Donatur "+categoryName + " with most donor is :  " + most.getNUM() + " " + most.getNAME() + " with " + most.getDONORS() + " donors");
    }
    public static void mostLap(int category,String gender) {
        Runner most = null;
        String categoryName = "";
        for (int i = 0; i < runners.size(); i++) {
            if (runners.get(i).getGENDER().equalsIgnoreCase(gender) && runners.get(i).getCategory().getID() == category) {
                if (most != null) {
                    if (most.getLaps() < runners.get(i).getLaps()) {
                        most = runners.get(i);
                    }
                } else {
                    most = runners.get(i);
                    categoryName = most.getCategory().getName();
                }
            }
        }
        System.out.println("Terjauh " + categoryName +" dengan "+ gender+ ": " + most.getNUM() + " " + most.getNAME()+" " +
                most.getLaps() +" Laps");
    }
    public static void mostValuableRunner(int category) {
//        Insert Code
        Runner most = null;
        String categoryName = "";
        for (Runner runner : runners) {
//            Cek kategori
            if (runner.getCategory().getID() == category) {
                if (most != null) {
                    if (most.getValue() < runner.getValue()) {
                        most = runner;
                    }
                } else {
                    most = runner;
                    categoryName = runner.getCategory().getName();
                }
            }
        }
        System.out.println("Termahal " + categoryName + ": " + most.getNUM() + " " + most.getNAME() + ", Rp " + most.getValue());
    }
    //    Sync data methods
    public static void refresh() throws Exception {
        donationPool = 0;
        for (Runner runner : runners) {
            runner.syncRecords();
            donationPool += runner.getValue();
        }
    }
    public static void main(String[] args) throws Exception{
//        1. Initialize program
        runners = new ArrayList<>();
//        Initialize connection to database
        Connection conn = connect();
//        Register the runners
        String query = "SELECT * FROM registration";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Registering runners . . .");
        while (resultSet.next()){
            runners.add(new Runner(resultSet.getInt("no_Peserta"), resultSet.getString("nama"),
                    resultSet.getString("gender"), resultSet.getInt("kategori_ID"), resultSet.getInt("target_Lap"),
                    resultSet.getInt("donasi_Lap"), resultSet.getInt("donatur"), conn));
        }
        System.out.println(runners.size() + " runners registered");
//        Initialize donation
        for (Runner runner : runners){
            if (runner.getLaps() > 0)
            donationPool += runner.getValue();
        }

//        2. Manual control and monitoring
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while (!(input = br.readLine()).equalsIgnoreCase("stop")) {
                refresh();
                if (input.equalsIgnoreCase("donors")) {
                    mostDonors(2);
                    mostDonors(3);
                }  else if (input.contains("laps")) {
                    String[] splittedInput = input.split(" ");
                    if (splittedInput.length > 1) {
                        for (Runner runner : runners) {
                            if (splittedInput[1].equals(String.valueOf(runner.getNUM()))) {
                                System.out.println(runner.getNAME() + ": " + runner.getLaps() + " laps");
                            }
                        }
                    } else {
                        mostLap(2,"L");
                        mostLap(2,"P");
                        mostLap(3,"L");
                        mostLap(3,"P");
                    }
                }else if (input.contains("info")) {
                    String[] splittedInput = input.split(" ");
                    if (splittedInput.length > 1) {
                        for (Runner runner : runners) {
                            if (splittedInput[1].equals(String.valueOf(runner.getNUM()))) {
                                System.out.println(runner.toString());
                            }
                        }
                    }
                } else if (input.equalsIgnoreCase("termahal")) {
                    mostValuableRunner(2);
                    mostValuableRunner(3);
                } else if (input.equalsIgnoreCase("runners")) {
                    int totalLaps = 0;
                    int total = 0;
                    for (Runner runner : runners) {
                        System.out.println(runner.toString());
                        totalLaps += runner.getLaps();
                        total += runner.getValue();
                    }
                    System.out.println("Donation: " + total);
                    System.out.println("Laps: " + totalLaps);
                } else if (input.equalsIgnoreCase("records")) {
                    for (Runner runner : runners) {
                        for (Record record : runner.getRecords()) {
                            System.out.println(record.toString());
                        }
                    }
                } else if (input.contains("add")) {
                    String[] splittedInput = input.split(" ");
                    if (splittedInput.length > 1) {
                        for (Runner runner : runners) {
                            if (splittedInput[1].equals(String.valueOf(runner.getNUM()))) {
                                if (runner.isRunning()) {
                                    MainJogathon.addRecordMacro(runner, runner.getID());
                                    System.out.println("Manual add success");
                                } else {
                                    System.out.println(runner.getNUM() + " is no longer running");
                                    runner.endRun();
                                }
                            }
                        }
                    }
                } else if (input.contains("end")) {
                    String[] splittedInput = input.split(" ");
                    if (splittedInput.length > 1) {
                        for (Runner runner : runners) {
                            if (splittedInput[1].equals(String.valueOf(runner.getNUM()))) {
                                if (runner.isRunning()) {
                                    runner.endRun();
                                    System.out.println(runner.getNUM() + " stop running!");
                                } else {
                                    System.out.println(runner.getNUM() + " is no longer running");
                                    runner.endRun();
                                }
                            }
                        }
                    }
                } else if (input.equalsIgnoreCase("start")) {
                    for (Runner runner : runners) {
                        if (!(runner.getNAME().equalsIgnoreCase("?"))){
                            runner.startRun();
                        }
                    }
                } else if (input.equalsIgnoreCase("donations")) {
                    System.out.println("Total donations: " + donationPool);
//                    MainJogathon.printDonation(donationPool);
                } else if (input.equals("clear records")) {
                    String sql = "TRUNCATE TABLE record";
                    preparedStatement = conn.prepareStatement(sql);
                    preparedStatement.execute();
                    System.out.println("All records deleted");
                    donationPool = 0;
                }
            }
            for (Runner runner : runners) {
                runner.endRun();
            }
            System.out.println("-----------------------------------------------------------");
            mostLap(2,"L");
            mostLap(2,"P");
            mostLap(3,"L");
            mostLap(3,"P");
            System.out.println("-----------------------------------------------------------");
            mostDonors(2);
            mostDonors(3);
            System.out.println("-----------------------------------------------------------");
            mostValuableRunner(2);
            mostValuableRunner(3);
            System.out.println("-----------------------------------------------------------");
            System.out.println("Total Donation : "+ donationPool);
        }
    }
