package zybo_server.connections;

import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;
import zybo_server.handlers.ConnectionHandler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import sockethandler.SocketHandler;
import zybo_server.handlers.SerialHandler;

public class Connector implements Runnable
{

    private final SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private SocketHandler socketHandler;
    private SerialHandler serialHandler;
    private final int port;

    public Connector()
    {
        port = 8001;
        serialHandler = new SerialHandler();
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
            System.out.println("fix det1");
        }
        catch (PortInUseException ex)
        {
            System.out.println("fix det2");
        }
        catch (UnsupportedCommOperationException ex)
        {
            System.out.println("fix det3");
        }
    }

    public void InitiateConnection() throws IOException, PortInUseException, UnsupportedCommOperationException
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
                serialHandler.initialize();
                ConnectionHandler ch = 
                        new ConnectionHandler(port, socketHandler, serialHandler);
                Thread t1 = new Thread(ch);
                t1.start();
            }           
        }
    }
}
