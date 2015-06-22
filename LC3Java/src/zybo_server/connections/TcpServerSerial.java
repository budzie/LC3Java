package zybo_server.connections;

import zybo_server.handlers.SerialHandler;

public class TcpServerSerial extends Thread
{
    private SerialHandler serialHandler;

    public TcpServerSerial()
    {
        serialHandler = new SerialHandler();
    }
    
    public void run()
    {
        while (true)
        {
            try
            {
                serialHandler.initialize();
                System.out.println("Started");
                Thread.sleep(1000000);
            }
            catch (InterruptedException ex)
            {
                ex.printStackTrace();
            }
        }
    }   
}
