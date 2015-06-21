package zybo_server.types;

public class SensorType
{
    public String sensorName;
    public int sampleRate;
    public int sampleValue;

    public SensorType(String sensorName, int sampleRate, int sampleValue)
    {
        this.sensorName = sensorName;
        this.sampleRate = sampleRate;
        this.sampleValue = sampleValue;
    }   
}
