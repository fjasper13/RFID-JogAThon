import RFIDv2.Runner;

import java.io.*;
import java.net.Socket;

/**
 * Created by ASUS on 28/03/2018.
 */
public class ServerThread extends Thread {
    private Socket socket;
    private Runner runner;

    public ServerThread(Socket socket, Runner runner){
        this.socket = socket;
        this.runner = runner;
    }

    @Override
    public void run() {
//        Start run!!
        try {
            runner.startRun();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Preparing to add
        DataInputStream din = null;
        try {
            din = new DataInputStream(socket.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String strRecieve = "";

//        Start adding records (Change into Read Serial)
        while (!strRecieve.equalsIgnoreCase("end")) {
            try {
                strRecieve = din.readUTF();
                if (strRecieve.equals("add")){
//                    Add Record
                    runner.addRecord();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        End run
        try {
            runner.endRun();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            din.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
