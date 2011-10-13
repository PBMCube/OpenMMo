package net.petterroea.openmmo.server;

import java.io.*;
import java.net.Socket;

public class PlayerListener implements Runnable{
	public PlayerListener(Socket socket)
	{
		this.socket = socket;
	}
	Socket socket;
	PlayerInstance instance;
	@Override
	public void run() {
		NetworkSettings.users = NetworkSettings.users + 1;
		try
		{
			instance = new PlayerInstance(this);
			Thread thread = new Thread(instance);
			thread.setPriority(Thread.NORM_PRIORITY + 1);
		InputStreamReader leseLeser = null;
		leseLeser = new InputStreamReader(socket.getInputStream());
		BufferedReader reader = null;
		reader = new BufferedReader(leseLeser);
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		String lines = null;
		lines = reader.readLine();
		while(lines != null)
		{
			byte[] bytes = lines.getBytes();
			if(bytes[0] == 0)
			{
				
			}
			lines = reader.readLine();
		}
		} catch (IOException e) {
			EntryPoint.log("Error! Could not connect player! " + e, EntryPoint.MessageType.WARNING, EntryPoint.Component.CONNECTION_MANAGER);
			try {
				socket.close();
			} catch (IOException e1) {
				EntryPoint.log("Failed to close socket! " + e, EntryPoint.MessageType.WARNING, EntryPoint.Component.CONNECTION_MANAGER);
			}
		}
		//Thread dying. Tell the NetworkSettings that player is disconnecting
		NetworkSettings.users = NetworkSettings.users - 1;
		
	}

}