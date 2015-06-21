package zybo_server.main;

import zybo_server.connections.TcpServer;
import zybo_server.connections.TcpServerPing;

public class TcpServerMain 
{
    public static void main(String argv[]) throws Exception
    {
        TcpServer server = new TcpServer();
        TcpServerPing ping = new TcpServerPing();
        
        Thread welcomeServer = new Thread(server);
        Thread pingServer = new Thread(ping);
        
        pingServer.start();
        welcomeServer.start();        
    }
}