package zybo_client.gui.handlers;

import java.io.IOException;

public class SensorStateHandler implements Runnable
{

    private static String sensors;
    private final TcpMenuHandler tcp;

    public SensorStateHandler(String ip, TcpMenuHandler handler) throws IOException
    {
        sensors = "";
        tcp = handler;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                sensors = tcp.getStatus();
                Thread.sleep(300);
            }
            catch (IOException | InterruptedException ex) 
            {
                break;
            }
        }
    }
    
    public String getStates()
    {
        return sensors;
    }
}



