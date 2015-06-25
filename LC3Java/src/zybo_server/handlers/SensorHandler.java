package zybo_server.handlers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import sockethandler.SocketHandler;
import zybo_server.types.SensorType;

public class SensorHandler
{

    private final SerialHandler serialHandler;
    private final SocketHandler socketHandler;
    private final ArrayList<SensorType> sensors;

    private SampleHandler sample;

    public SensorHandler(SerialHandler serialHandler, SocketHandler socketHandler) throws FileNotFoundException, IOException
    {
        this.serialHandler = serialHandler;
        this.socketHandler = socketHandler;
        sensors = new ArrayList<SensorType>();

        BufferedReader in = new BufferedReader(new FileReader("Sensors.txt"));

        String line = in.readLine();
        while (line != null)
        {
            String[] parts = line.split("_");
            String sensorName = parts[0];
            int sensorRate = Integer.parseInt(parts[1]);
            addSensor(sensorName, sensorRate);
            line = in.readLine();
        }
        in.close();

    }

    private void addSensor(String sensorName, int sensorRate)
    {
        sensors.add(new SensorType(sensorName, sensorRate));
        System.out.println("Added " + sensorName);
    }

    public String increase(int sensorNumber)
    {
        if (!sensors.get(sensorNumber - 1).sensorName.isEmpty())
        {
            if (sensors.get(sensorNumber - 1).sampleRate < 4096)
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
            sample = new SampleHandler(sensorNumber - 1, sensors.get(sensorNumber - 1).sensorName, sensors.get(sensorNumber - 1).sampleRate, serialHandler, socketHandler);
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
        return answer;
    }

    public String wipeLog() throws IOException
    {
        FileWriter file = new FileWriter("/home/xilinx/ADCvalues.log");
        //FileWriter file = new FileWriter("ADCvalues.log");
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
            for (int i = 1; i < 9; i++)
            {
                if ((i + "").equals(t.getName()))
                {
                    answer = answer + "Sensor " + t.getName() + " is active.";
                }
            }
        }
        if (answer.isEmpty())
        {
            answer = "No active sensors.";
        }
        System.out.println(answer);
        return answer;
    }

    public String getStatus()
    {
        String answer = "";
        for (Thread t : Thread.getAllStackTraces().keySet())
        {
            for (int i = 1; i < 9; i++)
            {
                if ((i + "").equals(t.getName()))
                {
                    answer = answer + t.getName();
                }
            }
        }
        return answer;
    }

    public String getData() throws FileNotFoundException, IOException
    {
        BufferedReader in = new BufferedReader(new FileReader("/home/xilinx/ADCvalues.log"));
        String output = "";
        String line = in.readLine();
        while (line != null)
        {
            output = output + line + "\n";
            line = in.readLine();
        }
        in.close();
        System.out.println(output);
        return output;
    }
}
