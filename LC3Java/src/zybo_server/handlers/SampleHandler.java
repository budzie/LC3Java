package zybo_server.handlers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SampleHandler implements Runnable
{

    private final SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final SerialHandler serialHandler;
    private final String sensorName;
    private final int sensorNr;
    private final int sampleRate;
    private static String sampleValue;
    private boolean exit;
    private String binaryString;

    public SampleHandler(int nr, String name, int rate, SerialHandler serialHandler)
    {
        this.serialHandler = serialHandler;
        sensorNr = nr;
        sensorName = name;
        sampleRate = rate;
        exit = false;
    }

    public String getSampleValue()
    {
        return sampleValue;
    }

    private synchronized Boolean selectChannel() throws IOException
    {
        serialHandler.write(0x7C);
        binaryString = Integer.toBinaryString(serialHandler.read());
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
            binaryString = Integer.toBinaryString(serialHandler.read());
            if (binaryString.equals("1110000"))
            {
                return true;
            }
        }
        return false;
    }

    private synchronized void startSampling() throws IOException, InterruptedException
    {

        serialHandler.write(0x7C);
        binaryString = Integer.toBinaryString(serialHandler.read());
        if (binaryString.equals("1110000"))
        {
            serialHandler.write(0x20);
            int sampleByte0 = serialHandler.read();
            int sampleByte1 = serialHandler.read();
            System.out.println("Recieved sample byte0 from " + sensorName + ": " + sampleByte0);
            System.out.println("Recieved sample byte1 from " + sensorName + ": " + sampleByte1);
            int totalSampleValue = sampleByte0;
            totalSampleValue = (totalSampleValue << 8) | sampleByte1;
            long unsignedValue = totalSampleValue & 0xffffffffl;
            System.out.println("Recieved sample value from " + sensorName + ": " + totalSampleValue);
            //if (new File("ADCvalues.log").exists())
            if (new File("/home/xilinx/ADCvalues.log").exists())
            {
                FileWriter file = new FileWriter("/home/xilinx/ADCvalues.log", true);
                //FileWriter file = new FileWriter("ADCvalues.log", true);
                PrintWriter out = new PrintWriter(file);
                out.println(date.format(new Date()) + " - Value of " + sensorName + " = " + sampleValue + " (" + sampleRate + " sec. sample rate)");
                out.close();
            }
            else
            {
                FileWriter file = new FileWriter("/home/xilinx/ADCvalues.log");
                //FileWriter file = new FileWriter("ADCvalues.log");
                PrintWriter out = new PrintWriter(file);
                out.println(date.format(new Date()) + " - Value of " + sensorName + " = " + sampleValue + " (" + sampleRate + " sec. sample rate)");
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
