import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class JSerialTest {

    public static void main(String[] args) {
        SerialPort comPort = SerialPort.getCommPort("COM5");
        comPort.setComPortParameters(115200, 8, 1, 0);
        final String[] dataToPass = new String[1]; // Wadah untuk menyimpan data dari Arduino

        // Always running !!!
        while (true){
            comPort.openPort();
            comPort.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() {
                    return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
                }

                @Override
                public void serialEvent(SerialPortEvent event) {
                    if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                        return;
                    byte[] newData = new byte[comPort.bytesAvailable()];
                    comPort.readBytes(newData, newData.length);

                    //
                    // dataToPass[0] INI YANG MAU DI KIRIM DAN DI PROSES KE SPLIT STRING
                    // BENTUK DATANYA :
                    // ID : x
                    // ID : 4
                    // ID : 5
                    //
                    dataToPass[0] = new String(newData);
                    System.out.print(dataToPass[0]);
                }
            });
        }

    }

}
