package RFIDv2;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ASUS on 28/03/2018.
 */
public class Runner {
    private static int nextID = 1;
    private final int ID;
    private final int NUM;
    private final String NAME;
    private final String GENDER;
    private Category category;
    private final int TARGET;
    private final int DONATION;
    private final int DONORS;
    private int value;
    private boolean running;
    private int laps;

    private ArrayList<Record> records;
    private Connection conn;

//    Constructor
    public Runner(int NO, String NAME, String GENDER, int category,int TARGET, int DONATION, int DONORS, Connection connection) throws Exception{
        this.ID = nextID;
        nextID++;
//        Establish connection to Database
        this.conn = connection;
        this.NUM = NO;
        this.NAME = NAME;
        this.GENDER = GENDER;
        this.category = new Category(category, this.conn);
        this.value = this.category.getFixedDonation();
        this.TARGET = TARGET;
        this.DONATION = DONATION;
        this.DONORS = DONORS;
        records = new ArrayList<>();

        try {
            syncRecords();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Getters
    public int getID() {
        return ID;
    }
    public int getNUM() {
        return NUM;
    }
    public String getNAME() {
        return NAME;
    }
    public String getGENDER() {
        return GENDER;
    }
    public Category getCategory() {
        return category;
    }
    public int getTARGET() {
        return TARGET;
    }
    public int getDONATION() {
        return DONATION;
    }
    public int getDONORS() {
        return DONORS;
    }
    public int getRecordsSize() {
        return records.size();
    }
    public int getLaps() {
        if (getRecordsSize() != 0) {
            if (getRecord(getRecordsSize() - 1).getEndTime().equalsIgnoreCase("Null"))
                laps = getRecordsSize() - 1;
            else {
                laps = getRecordsSize();
            }
        } else laps = 0;
        return laps;
    }
    public Record getRecord(int index) {return records.get(index);}
    public ArrayList<Record> getRecords() {return records;}
    public boolean isRunning() {
        return running;
    }

    //    To String
    @Override
    public String toString() {
        return "ID=" + ID +
                ", NUM=" + NUM +
                ", NAME='" + NAME + '\'' +
                "| Laps=" + getLaps() +
                ", Total Donasi: " + getValue() + " |";
    }

    //    Start and End method
    public void startRun() throws Exception{
        running = true;
        if (records.isEmpty()){
            String startTime = new SimpleDateFormat("HH:mm:ss:SSS").format(Calendar.getInstance().getTime());
//        Add new record
            records.add(new Record(this, startTime));

//        Database
            String sql = "INSERT INTO record (runner_ID, record_Awal, record_Akhir) " +
                    "VALUES (" + this.getNUM() + ", '" + startTime + "', 'Null')";
            conn.prepareStatement(sql).execute();
        } else System.out.println(getNAME() + " already running");
    }

    //    Running method
    public void addRecord() throws Exception{
        String startTime = new SimpleDateFormat("HH:mm:ss:SSS").format(Calendar.getInstance().getTime());
        int lastRecordIndex = getRecordsSize() - 1;
//        Update the last record
        records.get(lastRecordIndex).setEndTime(startTime);
//        Update total donasi runner
        updateValue();
//        Add new record
        records.add(new Record(this, startTime));
//        Database
//        update the last record in Database
        String sql = "UPDATE record SET record_Akhir = '" + startTime + "' WHERE record_ID = " + records.get(lastRecordIndex).getID();
        conn.prepareStatement(sql).executeUpdate();
//        Add new record in Database
        sql = "INSERT INTO record (runner_ID, record_Awal, record_Akhir) " +
                "VALUES (" + this.getNUM() + ", '" + startTime + "', 'Null')";
        conn.prepareStatement(sql).execute();
    }
    //    Check Records
    public void syncRecords() throws Exception{
        records.clear();
        String sql = "SELECT * FROM record WHERE runner_ID = " + getNUM();
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            records.add(new Record(resultSet.getInt("record_ID"), this,
                    resultSet.getString("record_Awal"), resultSet.getString("record_Akhir")));
        }
        updateValue();
    }
    public void updateValue() {
//        Jumlah lap * Donasi/lap
        if (getLaps() < getTARGET()){
            setValue((getLaps() * (getDONATION() + getCategory().getFixedDonation())));
        } else {
            setValue((getTARGET() * (getDONATION() + getCategory().getFixedDonation())));
        }
    }
    public void endRun() throws Exception{
        running = false;
        if (!records.isEmpty()){
            if (records.get(getRecordsSize() - 1).getEndTime().equalsIgnoreCase("Null")) {
                records.remove(getRecordsSize() - 1);
            }
        }
//        Database
        String sql = "DELETE FROM record WHERE runner_ID = '" + this.getNUM() + "' AND record_Akhir = 'Null'";
        conn.prepareStatement(sql).execute();
    }
    //    Runner's total donation
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
