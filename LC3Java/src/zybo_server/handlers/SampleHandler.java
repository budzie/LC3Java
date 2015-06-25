package zybo_server.handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import sockethandler.SocketHandler;

public class SampleHandler implements Runnable
{

    private final SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final SerialHandler serialHandler;
    private final SocketHandler socketHandler;
    private final String sensorName;
    private final int sensorNr;
    private final int sampleRate;
    private boolean exit;
    private String binaryString;

    public SampleHandler(int nr, String name, int rate, SerialHandler serialHandler, SocketHandler socketHandler)
    {
        this.serialHandler = serialHandler;
        this.socketHandler = socketHandler;
        sensorNr = nr;
        sensorName = name;
        sampleRate = rate;
        exit = false;
    }



    private synchronized Boolean selectChannel() throws IOException
    {
        serialHandler.write(0x7C);
        binaryString = Integer.toBinaryString(serialHandler.readUnsignedByte());
        if (binaryString.equals("1110000"))
        {
            int binaryOutput = 0;

            switch (sensorNr)
            {
                case 0:
                    binaryOutput = 0b00010000;
                    break;
                case 1:
                    binaryOutput = 0b00010001;
                    break;
                case 2:
                    binaryOutput = 0b00010010;
                    break;
                case 3:
                    binaryOutput = 0b00010011;
                    break;
                case 4:
                    binaryOutput = 0b00010100;
                    break;
                case 5:
                    binaryOutput = 0b00010101;
                    break;
                case 6:
                    binaryOutput = 0b00010110;
                    break;
                case 7:
                    binaryOutput = 0b00010111;
                    break;
            }
            serialHandler.write(binaryOutput);              // Send opcode to choose sensor
            binaryString = Integer.toBinaryString(serialHandler.readUnsignedByte());
            if (binaryString.equals("1110000"))
            {
                return true;
            }
        }
        return false;
    }

    private void startSampling() throws IOException, InterruptedException
    {

        serialHandler.write(0x7C);
        binaryString = Integer.toBinaryString(serialHandler.readUnsignedByte());
        if (binaryString.equals("1110000"))
        {
            serialHandler.write(0x20);
            int sampleByte0 = serialHandler.readUnsignedByte();
            int sampleByte1 = serialHandler.readUnsignedByte();
            System.out.println("Recieved sample byte0 from " + sensorName + ": #" + Integer.toBinaryString(sampleByte0));
            System.out.println("Recieved sample byte1 from " + sensorName + ": #" + Integer.toBinaryString(sampleByte1));
            
            int totalSampleValue = sampleByte0;
            totalSampleValue = (totalSampleValue << 8) | sampleByte1;    
            
            System.out.println("Calculated 10-bit unsigned sample value from " + sensorName + ": #" + totalSampleValue);;
 
            
            //if (new File("ADCvalues.log").exists())
            if (new File("/home/xilinx/ADCvalues.log").exists())
            {
                FileWriter file = new FileWriter("/home/xilinx/ADCvalues.log", true);
                //FileWriter file = new FileWriter("ADCvalues.log", true);
                PrintWriter out = new PrintWriter(file);
                out.println(date.format(new Date()) + " - Value of " + sensorName + " = " + totalSampleValue + " (" + sampleRate + " sec. sample rate)");
                out.close();
            }
            else
            {
                FileWriter file = new FileWriter("/home/xilinx/ADCvalues.log");
                //FileWriter file = new FileWriter("ADCvalues.log");
                PrintWriter out = new PrintWriter(file);
                out.println(date.format(new Date()) + " - Value of " + sensorName + " = " + totalSampleValue + " (" + sampleRate + " sec. sample rate)");
                out.close();
            }
        }
        else
        {
            exit = true;
        }
    }

    @Override
    public void run()
    {
        try
        {
            selectChannel();
            while (true)
            {
                try
                {
                    if (!exit)
                    {
                        startSampling();
                        Thread.sleep(sampleRate * 1200);
                    }
                    else
                    {
                        return;
                    }
                }
                catch (InterruptedException e)
                {
                    System.out.println("\n" + date.format(new Date()) + " - Logging of " + sensorName + " stopped.");
                    exit = true;
                }
                catch (IOException e)
                {
                    System.out.println("\n" + date.format(new Date()) + " - Cannot write sensor-data to log.");
                    exit = true;
                }
            }
        }
        catch (IOException ex)
        {
            System.out.println("\n" + date.format(new Date()) + " - Cannot change sensor channel.");
        }
    }
}
