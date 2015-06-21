package zybo_client.tcp;

import java.io.IOException;

import sockethandler.SocketHandler;

public class TcpHandler extends SocketHandler 
{

	public TcpHandler(String host, int port) throws IOException 
        {
		super(host, port);
	}
}
