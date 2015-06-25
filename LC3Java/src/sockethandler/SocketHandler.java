package sockethandler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketHandler
{

    private final Socket socket;
    private final BufferedReader in;
    private final DataOutputStream out;

    public SocketHandler(String host, int port) throws IOException
    {
        this(new Socket(host, port));
    }
    
    // Constructor-method used for getPassivSocket() (FtpHandler.java): 
    public SocketHandler(SocketHandler socketHandler, int port) 
            throws IOException
    {
        this(new Socket(socketHandler.socket.getInetAddress(), port));
    }

    public SocketHandler(Socket socket) throws IOException
    {
        this.socket = socket;
        out = new DataOutputStream(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }
    
    public synchronized String send(String in) throws IOException
    {
        println(in);
        return readLine();
    }
    
    public synchronized void flush() throws IOException
    {
        out.flush();
    }
    
    public synchronized void print(String msg) throws IOException
    {
        out.writeBytes(msg);
        out.flush();
    }

    public synchronized void println(String msg) throws IOException
    {
        print(msg + "\r\n");
    }

    public synchronized String readLine() throws IOException
    {
        return in.readLine();
    }

    public synchronized final void disconnect()
    {
        try
        {
            out.close();
            in.close();
            socket.close();
            
        }
        catch (IOException ex)
        {
            System.out.println("Socket closed.");
        }

    } 
}




