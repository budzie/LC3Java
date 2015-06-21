package zybo_client.main;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import zybo_client.gui.handlers.JPanelCreator;

public class ZyboMain
{

    private ZyboMain(String ip) throws InterruptedException
    {
           JPanelCreator jpc = new JPanelCreator(ip);
           jpc.paintMain();
           jpc.startMainMenuThread();
    }

    public static void main(String[] args) throws IOException, InterruptedException, ConnectException, UnknownHostException, SocketException
    {
        // Check if ip-adress was entered as argument, if not use "localhost":
        if (args.length == 1)
            new ZyboMain(args[0]);
        else
            new ZyboMain("192.168.0.25");
    }
}
