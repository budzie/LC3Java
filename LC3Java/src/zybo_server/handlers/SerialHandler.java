package zybo_server.handlers;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.util.Enumeration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.DataInputStream;

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

    public void close()
    {
        if (serialPort != null)
        {
            //serialPort.removeEventListener();
            serialPort.close();
        }
    }
    
      public int readUnsignedByte() throws IOException
    {
        int inputLine;
        DataInputStream in = new DataInputStream(serialPort.getInputStream());
        while (true)
        {
            if (input.ready())
            {
                inputLine = in.readUnsignedByte();
                if (inputLine > -1)
                {
                    int nl = in.readUnsignedByte();
                    if (nl == 10)
                    {
                       return inputLine; 
                    }              
                }
                //return 0;
            }
        }
    }
      
      
      public String readString() throws IOException
    {
        int inputLine;
        String inputString = null;
        while (true)
        {
            if (input.ready())
            {
                inputLine = input.read();
                if (inputLine > -1)
                {
                    inputString = inputString + (inputLine + "0");
                    int nl = input.read();
                    if (nl == 10)
                    {
                       return inputString;
                    }
                    inputString = inputString + (nl + "0");
                }
            }
        }
    }
      
    public void write(int outputByte)
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
}
