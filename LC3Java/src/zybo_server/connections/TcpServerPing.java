package zybo_server.connections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import sockethandler.SocketHandler;

public class TcpServerPing implements Runnable
{
   
    private SocketHandler socketHandler;

    @Override
    public void run()
    {
        try
        {
            InitiateConnection();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public void InitiateConnection() throws IOException
    {
        while (true)
        {
            ServerSocket welcomeSocket = new ServerSocket(8000);           
            while (true)
            {
                Socket connectionSocket = welcomeSocket.accept();
                socketHandler = new SocketHandler(connectionSocket);
                socketHandler.disconnect();
                welcomeSocket.close();
                break;
            }
        }
    }
}
