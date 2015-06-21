package zybo_client.gui.handlers;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFrame;
import zybo_client.gui.jpanels.JPanelFtpMenu;
import zybo_client.gui.jpanels.JPanelMenu;
import zybo_client.gui.jpanels.JPanelTcpMenu;

public class JPanelCreator
{

    private final String ip;
    private static JPanelMenu mainPanel;
    private static JPanelFtpMenu ftpPanel;
    private static JPanelTcpMenu tcpPanel;
    

    public JPanelCreator(String ip) throws InterruptedException
    {
        this.ip = ip;
    }

    public void paintMain() throws InterruptedException
    {
        mainPanel = new JPanelMenu(ip, this);
        JFrame vindue = new JFrame("Zybo TCP/FTP Connector");
        vindue.add(mainPanel);
        // Get screen size:
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Set window size:
        vindue.setSize(485, 427);
        // Calculate centre:
        vindue.setLocation(((screenWidth / 2) - vindue.getWidth() / 2), 
                ((screenHeight / 2) - vindue.getHeight() / 2));
        vindue.setVisible(true);
        // Add windowListener to check for window closing; then exit:
        vindue.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {                  
                System.exit(0);
            }       
        });
    }

    public void paintFtp() throws InterruptedException, IOException
    {
        ftpPanel = new JPanelFtpMenu(ip);
        JFrame vindue = new JFrame(ip + ":21 (FTP)");
        vindue.add(ftpPanel);
        // Get screen size:        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Set window size:
        vindue.setSize(725, 600);
        // Calculate centre:
        vindue.setLocation(((screenWidth / 2) - vindue.getWidth() / 2), ((screenHeight / 2) - vindue.getHeight() / 2));
        vindue.setVisible(true);
        // Add windowListener to check for window closing; if true then disconnect socket and notify user:
        vindue.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {                  
                ftpPanel.disconnect();
                System.out.println("FTP connection closed");
                mainPanel.appendText("\n\nFTP connection closed");
                mainPanel.setFtpOpen(false);
            }       
        });    
    }

    public void paintTcp() throws IOException, InterruptedException
    {
        tcpPanel = new JPanelTcpMenu(ip);
        JFrame vindue = new JFrame(ip + ":8001 (TCP)");
        vindue.add(tcpPanel);
        // Get screen size: 
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        vindue.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Set window size:
        vindue.setSize(940, 600);
        // Calculate centre:
        vindue.setLocation(((screenWidth / 2) - vindue.getWidth() / 2), 
                ((screenHeight / 2) - vindue.getHeight() / 2));
        vindue.setVisible(true);       
        /* Add windowListener to check for window closing; 
            if true then disconnect socket and notify user: */
        vindue.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {                  
                tcpPanel.disconnect();
                System.out.println("TCP connection closed");
                mainPanel.appendText("\n\nTCP connection closed");
                mainPanel.setTcpOpen(false);
            }       
        });
    }

    public void startMainMenuThread()
    {
        // Start mainPanel thread:
        Thread main = new Thread(mainPanel);
        main.start();
    }
    
    public void setTextTcp(String text)
    {
        if (mainPanel.getTcpOpen())
            tcpPanel.setText(text);
    }
    
    public void setTextFtp(String text)
    {
        if (mainPanel.getFtpOpen())
            ftpPanel.setText(text);
    }
    
    public void disconnectFtp()
    {
        if (mainPanel.getFtpOpen())
            ftpPanel.disconnect();
    }
    
    public void disconnectTcp()
    {
        if (mainPanel.getTcpOpen())
            tcpPanel.disconnect();
    }
}
