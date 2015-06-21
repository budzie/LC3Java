package zybo_client.gui.handlers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import zybo_client.ftp.FtpHandler;

public class FtpMenuHandler
{

    private FtpHandler ftp;
    private boolean connected;

    public boolean connectFTP(String ip) throws IOException, InterruptedException
    {
        ftp = new FtpHandler(ip);
        connected = ftp.connect(ip, "xilinx", "ftp");
        return connected;
    }

    public String list() throws IOException
    {
        if (connected)
            return ftp.getData("LIST");
        return "No files on server";
    }

    public String send(String in) throws IOException
    {
        if (connected)
        {
            String data = ftp.getData(in);
            return data;
        }
        return "Not connected";
    }

    public String sendCmd(String cmd) throws IOException
    {
        if (connected)
        {
            String data = ftp.send(cmd);
            return data;
        }
        return "Not connected";
    }

    public boolean saveFile(String name) throws IOException
    {
        if (connected)
        {
            if (name.length() > 0)
            {    
                String data = ftp.getData("RETR " + name);
                if (data.contains("550 Failed to open file"))
                    return false;        
                if (data.length() != 14)
                {
                    FileWriter file = new FileWriter(name);
                    PrintWriter out = new PrintWriter(file);
                    out.println(data);
                    out.close();
                    return true;
                }
            }
        }
        return false;
    }

    public void exit()
    {
        disconnect();
        System.exit(0);
    }

    public void disconnect()
    {
        ftp.disconnect();
    }
}
