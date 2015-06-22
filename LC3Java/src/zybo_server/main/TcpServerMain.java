package zybo_server.main;

import zybo_server.connections.Connector;
import zybo_server.connections.TcpServerPing;

public class TcpServerMain 
{
    public static void main(String argv[]) throws Exception
    {
        Connector server = new Connector();
        TcpServerPing ping = new TcpServerPing();
        Thread welcomeServer = new Thread(server);
        Thread pingServer = new Thread(ping);     
        pingServer.start();
        welcomeServer.start();  
    }
}