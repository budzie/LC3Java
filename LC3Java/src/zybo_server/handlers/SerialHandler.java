package zybo_server.handlers;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.util.ArrayList;
import java.util.Enumeration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.File;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class SerialHandler implements SerialPortEventListener
{
    private final Scanner keyIn = new Scanner(System.in);
    File lockFile = new File("/var/lock/LCK..ttyPS1");
    SerialPort serialPort;
    private static final String PORT_NAMES[] =
    {
        "/dev/ttyPS1"
    };
    private BufferedReader input;
    private BufferedWriter output;
    private static final int TIME_OUT = 200000;
    private static final int DATA_RATE = 115200;
    private ArrayList<Integer> offSensors = new ArrayList<>();

    public void initialize()
    {
        // Remove stale lock file:
        lockFile.delete();
        System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyPS1");
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        // First, Find an instance of serial port as set in PORT_NAMES:
        while (portEnum.hasMoreElements())
        {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum
                    .nextElement();
            for (String portName : PORT_NAMES)
            {
                if (currPortId.getName().equals(portName))
                {
                    portId = currPortId;
                    break;
                }
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

            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
                        int outputLine = 0x7C;
                        output.write(outputLine);
                        output.flush();
                        System.out.println("Sent: " + outputLine);
        }
        catch (Exception e)
        {
            System.err.println(e.toString());
        }
    }

    public synchronized void close()
    {
        if (serialPort != null)
        {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public synchronized void serialEvent(SerialPortEvent oEvent)
    {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE)
        {
            try
            {
                String inputLine = null;
                String outputLine = null;
                if (input.ready())
                {
                    inputLine = input.readLine();
                    System.out.println("Recieved: " + inputLine);
                    if (inputLine.equals("HEJ"))
                    {
                        outputLine = "5";
                        output.write(outputLine);
                        output.flush();
                        System.out.println("Sent: " + outputLine);
                    }
                }

            }
            catch (Exception e)
            {
                System.err.println(e.toString());
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        SerialHandler main = new SerialHandler();
        main.initialize();
        Thread t = new Thread()
        {
            public void run()
            {
				// the following line will keep this app alive for 1000 seconds,
                // waiting for events to occur and responding to them (printing
                // incoming messages to console).
                try
                {
                    Thread.sleep(100000000);
                }
                catch (InterruptedException ie)
                {
                    System.err.println(".." + ie.getMessage());
                }
            }
        };
        t.start();
        System.out.println("Started");
    }
}
