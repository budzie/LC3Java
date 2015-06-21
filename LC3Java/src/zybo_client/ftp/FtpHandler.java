package zybo_client.ftp;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import sockethandler.SocketHandler;

public class FtpHandler extends SocketHandler
{
    
    public FtpHandler(String ip) throws UnknownHostException, IOException
    {
        super(ip, 21);
    }

    @Override
    public String readLine() throws IOException
    {
        String msg = "";
        while (true)
        {
            /* Run loop until last line from server 
                (when message is 3 numbers without a following '-'): */
            String input = super.readLine();
            System.out.println(input);
            msg = msg + "\n" + input;
            if (input.length() >= 3 && input.charAt(3) != '-'
                    && Character.isDigit(input.charAt(0))
                    && Character.isDigit(input.charAt(1))
                    && Character.isDigit(input.charAt(2)))
                return msg;
        }
    }
    
    public boolean connect(String ip, String user, String pass) 
            throws IOException
    {           
        readLine();
        send("USER " + user);
        if (send("PASS " + pass).contains("230"))
            return true;
        return false;
    }

    private SocketHandler getPassiveSocket() throws IOException
    {
        // Enabling passive to recieve data:
        String adress = send("PASV");
        // Append tokens with ',':
        StringTokenizer st = new StringTokenizer(adress, "(,)");
        if (st.countTokens() < 7)
            throw new IOException("Message received does not follow "
                    + "the regular 7-token syntax (MSG.IP.IP.IP.IP.PORT.PORT");
        // Saving the first five tokens (Message + ip-adress):
        for (int i = 0; i < 5; i++)
            st.nextToken();
	// Parsing the last two tokens as integers to calculate port number, by
        // multiplying the sixth byte by 256 and adding the seventh byte (256 *
        // token6 + token7):
        int portNr = 256 * Integer.parseInt(st.nextToken())
                + Integer.parseInt(st.nextToken());
        return new SocketHandler(this, portNr);
    }

    public String getData(String out) throws IOException
    {
        SocketHandler socket = getPassiveSocket();
        String msg = send(out);     
        if (msg.contains("550 Failed to open file"))
            return msg;       
        StringBuilder sb = new StringBuilder();
        String input = socket.readLine();
        while (input != null)
        {
            sb.append(input).append("\n");
            input = socket.readLine();
        }
        socket.disconnect();
        readLine();
        return sb.toString();
    }
    
    public void exit()
    {
        disconnect();
        System.exit(0);
    }   
}
