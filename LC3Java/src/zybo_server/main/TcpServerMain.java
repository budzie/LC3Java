package zybo_server.main;

import zybo_server.connections.TcpServer;
import zybo_server.connections.TcpServerPing;
import zybo_server.handlers.SerialHandler;

public class TcpServerMain 
{
    public static void main(String argv[]) throws Exception
    {
        TcpServer server = new TcpServer();
        TcpServerPing ping = new TcpServerPing();
        SerialHandler serialServer = new SerialHandler();
        Thread welcomeServer = new Thread(server);
        Thread pingServer = new Thread(ping);
        serialServer.start();
        pingServer.start();
        welcomeServer.start();        
    }
}