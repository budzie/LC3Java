package zybo_server.handlers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import zybo_server.types.SensorType;

public class SensorHandler
{

    private final ArrayList<SensorType> sensors;

    private SampleHandler sample;

    public SensorHandler() throws FileNotFoundException, IOException
    {
        sensors = new ArrayList<SensorType>();
        BufferedReader ind = new BufferedReader(new FileReader("Sensors.txt"));

        String linje = ind.readLine();
        while (linje != null)
        {
            String[] bidder = linje.split("_");
            String sensorName = bidder[0];
            int sensorRate = Integer.parseInt(bidder[1]);
            int sensorValue = Integer.parseInt(bidder[2]);
            addSensor(sensorName, sensorRate, sensorValue);
            linje = ind.readLine();
        }
        ind.close();
    }

    private void addSensor(String sensorName, int sensorRate, int sensorValue)
    {
        sensors.add(new SensorType(sensorName, sensorRate, sensorValue));
        System.out.println("Added " + sensorName + " with update every " + sensorRate + " seconds.");
    }

    public String increase(int sensorNumber)
    {
        if (!sensors.get(sensorNumber - 1).sensorName.isEmpty())
        {
            if (sensors.get(sensorNumber - 1).sampleRate <= 2048)
            {
                sensors.get(sensorNumber - 1).sampleRate = (2 * sensors.get(sensorNumber - 1).sampleRate);
                String answer = "Successful, Sensor " + sensorNumber + " now has an update rate of " + sensors.get(sensorNumber - 1).sampleRate + " Seconds.";
                System.out.println(answer);
                return answer;
            }
            else
            {
                String answer = "Sensor " + sensorNumber + " is at highest sample rate.";
                System.out.println(answer);
                return answer;
            }
        }
        else
        {
            String answer = "Unsuccessful, no sensor with the value " + sensorNumber + ".";
            System.out.println(answer);
            return answer;
        }
    }

    public String decrease(int sensorNumber)
    {
        if (!sensors.get(sensorNumber - 1).sensorName.isEmpty())
        {
            if (sensors.get(sensorNumber - 1).sampleRate > 1)
            {
                sensors.get(sensorNumber - 1).sampleRate = (sensors.get(sensorNumber - 1).sampleRate / 2);
                String answer = "Successful, Sensor " + sensorNumber + " now has an update rate of " + sensors.get(sensorNumber - 1).sampleRate + " Seconds.";
                System.out.println(answer);
                return answer;
            }
            else
            {
                String answer = "Sensor " + sensorNumber + " is at lowest sample rate.";
                System.out.println(answer);
                return answer;
            }
        }
        else
        {
            String answer = "Unsuccessful, no sensor with the value " + sensorNumber + ".";
            System.out.println(answer);
            return answer;
        }
    }

    public String stop(int sensorNumber) throws InterruptedException
    {
        if (!sensors.get(sensorNumber - 1).sensorName.isEmpty())
        {
            for (Thread t : Thread.getAllStackTraces().keySet())
            {
                if (t.getName().equals(sensorNumber + ""))
                {
                    t.interrupt();
                    String answer = "Successful, Sensor " + sensorNumber + " has been stopped.";
                    System.out.println(answer);
                    return answer;
                }
            }
        }
        String answer = "Unsuccessful, no active sensor with that value.";
        System.out.println(answer);
        return answer;
    }

    public String start(int sensorNumber) throws IOException, InterruptedException
    {
        for (Thread t : Thread.getAllStackTraces().keySet())
        {
            if (t.getName().equals(sensorNumber + ""))
            {
                String answer = "Unsuccessful, sensor already active.";
                System.out.println(answer);
                return answer;
            }
        }
        if (!sensors.get(sensorNumber - 1).sensorName.isEmpty())
        {
            sample = new SampleHandler(sensors.get(sensorNumber - 1).sensorName, sensors.get(sensorNumber - 1).sampleRate, sensors.get(sensorNumber - 1).sampleValue);
            Thread sh = new Thread(sample, sensorNumber + "");
            sh.start();

            String answer = "Successful, Sensor " + sensorNumber + " has started logging with an update rate of " + sensors.get(sensorNumber - 1).sampleRate + " Seconds.";
            System.out.println(answer);
            return answer;
        }
        else
        {
            String answer = "Unsuccessful, no sensor with that value.";
            System.out.println(answer);
            return answer;
        }
    }

    public String list()
    {
        String answer = "Sensors: ";
        for (SensorType st : sensors)
        {
            answer = answer + st.sensorName + ", ";
        }
        System.out.println(answer);
        return answer;
    }

    public String wipeLog() throws IOException
    {
        FileWriter file = new FileWriter("/home/xilinx/SensorData.log");
        //FileWriter file = new FileWriter("SensorData.log");
        PrintWriter out = new PrintWriter(file);
        out.close();
        String answer = "Log has been wiped.";
        System.out.println(answer);
        return answer;
    }
    
    
    public String status()
    {
        String answer = "";
        for (Thread t : Thread.getAllStackTraces().keySet())
        {
            for (int i = 1; i < 6; i++)
            {
                if ((i + "").equals(t.getName()))
                    answer = answer + "Sensor " + t.getName() + " is active.";
            }
        }
        if (answer.isEmpty())
            answer = "No active sensors.";
        System.out.println(answer);
        return answer;
    }
    
    public String getStatus()
    {
        String answer = "";
        for (Thread t : Thread.getAllStackTraces().keySet())
        {
            for (int i = 1; i < 6; i++)
            {
                if ((i + "").equals(t.getName()))
                    answer = answer + t.getName();
            }
        }
        return answer;
    }
}
