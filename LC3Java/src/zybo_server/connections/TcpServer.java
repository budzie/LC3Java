package zybo_server.connections;

import zybo_server.handlers.ConnectionHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import sockethandler.SocketHandler;

public class TcpServer implements Runnable
{

    private final SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private SocketHandler socketHandler;
    private final int port;

    public TcpServer()
    {
        port = 8001;
    }

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
            ServerSocket welcomeSocket = new ServerSocket(port);
            System.out.println("\n" + date.format(new Date()) + " - "
                    + "Ready for connections on port " + port);
            while (true)
            {
                Socket connectionSocket = welcomeSocket.accept();
                socketHandler = new SocketHandler(connectionSocket);
                System.out.println("\n" + date.format(new Date()) + " - "
                        + "Client connecting on port " + port);                
                ConnectionHandler ch = 
                        new ConnectionHandler(port, socketHandler);
                Thread t1 = new Thread(ch);
                t1.start();
            }           
        }
    }
}
