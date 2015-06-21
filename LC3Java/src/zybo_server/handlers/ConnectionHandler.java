package zybo_server.handlers;

import java.io.IOException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sockethandler.SocketHandler;


public class ConnectionHandler implements Runnable
{
    private final SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final SocketHandler socketHandler;
    private SensorHandler sensor;
    private final int port;

    public ConnectionHandler(int port, SocketHandler socketHandler) 
            throws IOException
    {
        this.port = port;
        this.socketHandler = socketHandler;
    }
  
    public void run()
    {
        try
        {
            String clientSentence;
            while (true)
            {
                System.out.println("\n" + date.format(new Date()) + 
                        " - Client connected on port " + port);
                sensor = new SensorHandler();
                while (true)
                {                   
                    clientSentence = socketHandler.readLine();
                    if (clientSentence == null)
                    {
                        System.out.println("\n" + date.format(new Date()) + 
                                " - Client has disconnected from port " + port);
                        socketHandler.disconnect();
                        break;
                    }
                    if (!clientSentence.equals("GSTAT"))
                        System.out.println("\n" + date.format(new Date()) + 
                                " - Recieved: " + clientSentence);
                    
                    serverCommand(sensor, clientSentence);
                }
                break;
            }        
        }
        catch (SocketException ex)
        {
            System.out.println("Socket closed.");
            socketHandler.disconnect();
        }
        catch (InterruptedException ex)
        {
            ex.printStackTrace();
        }
        catch (IOException ex)
        {
            System.out.println("\n" + date.format(new Date()) + 
                    " - Client has disconnected from port " + port);
            socketHandler.disconnect();
        }
    }
    
    private void serverCommand(SensorHandler sensor, String clientSentence)
            throws IOException, InterruptedException
    {
        if (clientSentence.length() == 6)
        {
            if (clientSentence.startsWith("INCR"))
            {
                int sensorNumber = (clientSentence.charAt(5) - '0'); // Get the sensorNumber
                socketHandler.println(sensor.increase(sensorNumber));
            }

            else if (clientSentence.startsWith("DECR"))
            {
                int sensorNumber = (clientSentence.charAt(5) - '0'); // Get the sensorNumber
                socketHandler.println(sensor.decrease(sensorNumber));
            }

            else if (clientSentence.startsWith("STOP"))
            {
                int sensorNumber = (clientSentence.charAt(5) - '0'); // Get the sensorNumber
                socketHandler.println(sensor.stop(sensorNumber));
            }

            else if (clientSentence.startsWith("STAR"))
            {
                int sensorNumber = (clientSentence.charAt(5) - '0'); // Get the sensorNumber
                socketHandler.println(sensor.start(sensorNumber));
            }

            else
            {
                String answer = "\n" + date.format(new Date()) + " - Unknown command!";
                System.out.println(answer);
                socketHandler.println("Unknown command.");
            }

        }
        else if (clientSentence.equals("LIST"))
        {
            socketHandler.println(sensor.list());
        }

        else if (clientSentence.equals("DELE"))
        {
            socketHandler.println(sensor.wipeLog());
        }

        else if (clientSentence.equals("STAT"))
        {
            socketHandler.println(sensor.status());
        }

        else if (clientSentence.equals("GSTAT"))
        {
            socketHandler.println(sensor.getStatus());
        }
        
        else
        {
            String answer = "\n" + date.format(new Date()) + " - Unknown command!";
            System.out.println(answer);
            socketHandler.println("Unknown command.");
        }
    }
}
