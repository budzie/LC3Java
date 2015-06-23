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

    public synchronized void startSampling() throws IOException, InterruptedException
    {
        String binaryString;
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
            serialHandler.write(binaryOutput);              // Send opcode to choose sensor and start sampling           
            binaryString = Integer.toBinaryString(serialHandler.read());
            System.out.println(binaryString);
            if (binaryString.equals("1110000"))
            {
                serialHandler.write(0x7C);
                binaryString = Integer.toBinaryString(serialHandler.read());
                if (binaryString.equals("1110000"))
                {
                    serialHandler.write(0x20);
                    sampleValue = Integer.toBinaryString(serialHandler.read());
                    System.out.println("Recieved sample value from " + sensorName + ": " + sampleValue);
                    if (new File("SensorData.log").exists())
                    //if (new File("/home/xilinx/SensorData.log").exists())
                    {
                        //FileWriter file = new FileWriter("/home/xilinx/SensorData.log", true);
                        FileWriter file = new FileWriter("SensorData.log", true);
                        PrintWriter out = new PrintWriter(file);
                        out.println(date.format(new Date()) + " - Value of " + sensorName + " = " + sampleValue + " (" + sampleRate + " sec. sample rate)");
                        out.close();
                    }
                    else
                    {
                        //FileWriter file = new FileWriter("/home/xilinx/SensorData.log");
                        FileWriter file = new FileWriter("SensorData.log");
                        PrintWriter out = new PrintWriter(file);
                        out.println(date.format(new Date()) + " - Value of " + sensorName + " = " + sampleValue + " (" + sampleRate + " sec. sample rate)");
                        out.close();
                    }
                }
                else
                    exit = true;
            }
            else
                exit = true;
        }
        else

            exit = true;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                if (!exit)
                {
                    startSampling();
                    Thread.sleep(sampleRate * 1000);
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
}
