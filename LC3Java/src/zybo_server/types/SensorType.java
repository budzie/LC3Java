package zybo_server.types;

public class SensorType
{
    public String sensorName;
    public int sampleRate;

    public SensorType(String sensorName, int sampleRate)
    {
        this.sensorName = sensorName;
        this.sampleRate = sampleRate;
    }   
}
