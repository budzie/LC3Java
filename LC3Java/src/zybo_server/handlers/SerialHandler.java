package zybo_server.handlers;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.util.Enumeration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class SerialHandler //implements SerialPortEventListener
{

    private final File lockFile = new File("/var/lock/LCK..ttyPS1");
    private SerialPort serialPort;
    private final String PORT_NAME = "/dev/ttyPS1";
    private BufferedReader input;
    private BufferedWriter output;
    private final int TIME_OUT = 200000;
    private final int DATA_RATE = 115200;

    public void initialize()
    {
        // Removing stale lock file:
        lockFile.delete();

        // Adding port "/dev/ttyPS1":
        System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyPS1");

        // Checking if comm-port is valid and set port ID:
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements())
        {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum
                    .nextElement();
            if (currPortId.getName().equals(PORT_NAME))
            {
                portId = currPortId;
                break;
            }
        }
        if (portId == null)
        {
            System.out.println("Could not find COM port.");
            return;
        }

        try
        {
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

            // Open the streams:
            input = new BufferedReader(new InputStreamReader(
                    serialPort.getInputStream()));
            output = new BufferedWriter(new OutputStreamWriter(
                    serialPort.getOutputStream()));

            //serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            System.out.println("Serial port open on " + PORT_NAME);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public synchronized void close()
    {
        if (serialPort != null)
        {
            //serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public synchronized String readLine() throws IOException
    {
        String inputLine = null;
        while (true)
        {
            if (input.ready())
            {
                inputLine = input.readLine();
                if (!inputLine.equals(null))
                {
                    return inputLine;
                }
            }
        }
    }

    public synchronized void write(int outputByte)
    {
        try
        {
            output.write(outputByte);
            output.flush();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /*public synchronized void serialEvent(SerialPortEvent oEvent)
     {
     if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE)
     {
     try
     {
     if (input.ready())
     {
     inputLine = input.readLine();
     System.out.println("Recieved: " + inputLine);
     }
     }
     catch (Exception e)
     {
     System.err.println(e.toString());
     }
     }
     }*/
}
