import RFIDv2.Runner;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by ASUS on 19/04/2018.
 */
public class serialBuffered {

    static OutputStream outCOM3, outCOM10;
    static String dataToPass;
    static boolean allIDDetected = false;
    public static ArrayList<Runner> runners;
    public static long donationPool = 0;

    public static void main(String[] args) {
        SerialPort com5 = SerialPort.getCommPort("COM5");
        com5.setComPortParameters(115200, 8, 1, 0);
//        final String[] dataToPass = new String[1]; // Wadah untuk menyimpan data dari Arduino

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
                                System.out.println(dataToPass);
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
