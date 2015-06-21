package zybo_client.gui.handlers;

import zybo_client.tcp.TcpHandler;
import java.io.IOException;

public class TcpMenuHandler
{
    private TcpHandler tcp;

    public void connect(String ip, int port) throws IOException
    {
        tcp = new TcpHandler(ip, port);
    }

    public String getList() throws IOException
    {
        return tcp.send("LIST");
    }
    
    public String incrSensor(int nr) throws IOException
    {
        String output = "INCR_" + nr;
        return tcp.send(output);
    }    

    public String decrSensor(int nr) throws IOException
    {    
        String output = "DECR_" + nr;
        return tcp.send(output);
    }      

    public String delete() throws IOException
    {
        String output = "DELE";
        return tcp.send(output);
    }
    
    public String start(int nr) throws IOException
    {
        String output = "STAR_" + nr;
        return tcp.send(output);
    } 

    public String stop(int nr) throws IOException
    {
        String output = "STOP_" + nr;
        return tcp.send(output);
    }     

    public String status() throws IOException
    {
        String output = "STAT";
        return tcp.send(output);
    }
    
    public String getStatus() throws IOException
    {
        String output = "GSTAT";
        return tcp.send(output);
    }
    
    public void exit()
    {
        disconnect();
        System.exit(0);
    }
    
    public void disconnect()
    {
        tcp.disconnect();
    }
}
