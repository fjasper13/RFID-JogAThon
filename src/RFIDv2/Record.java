package RFIDv2;

/**
 * Created by ASUS on 28/03/2018.
 */
public class Record {
    private static int nextID = 1;
    private int ID;
    private Runner runner;
    private String initTime;
    private String endTime;

//    Constructor
    public Record(Runner runner, String initTime) {
        this.runner = runner;
        this.initTime = initTime;
        this.endTime = "Null";
        this.ID = nextID;
        nextID++;
    }

    public Record(int ID, Runner runner, String initTime, String endTime) {
        this.ID = ID;
        this.runner = runner;
        this.initTime = initTime;
        this.endTime = endTime;
        nextID++;
    }

    //    Getters
    public int getID() {
        return ID;
    }
    public Runner getRunner() {
        return runner;
    }
    public String getInitTime() {
        return initTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public static int getNextID() {
        return nextID;
    }

    //    Setters
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ID = " + ID +
                ", runner = " + runner.getNUM() +
                ", initTime = '" + initTime + '\'' +
                ", endTime = '" + endTime + '\'';
    }
}
