package net.petterroea.openmmo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionManager implements Runnable {
	boolean isRunning = true;
	Thread connectionThread;
	static ServerSocket socket;
	@Override
	public void run() {
		synchronized(this)
		{
		try {
			socket = new ServerSocket(25523);
		} catch (IOException e) {
			EntryPoint.log("ERROR: Could not init serverSocket! Error: " + e + "\n    Server will now shut down...", EntryPoint.MessageType.ERROR_SHUTDOWN, EntryPoint.Component.CONNECTION_MANAGER);
			System.exit(0);
		}
		}
		EntryPoint.log("Initalized connection", EntryPoint.MessageType.INFORMATION, EntryPoint.Component.CONNECTION_MANAGER);
		while(NetworkSettings.stillRunning)
		{
			try {
				Socket newConnection = socket.accept();
				new Thread(new PlayerListener(newConnection)).start();
			} catch (IOException e) {
				EntryPoint.log("Error! Could not connect player! " + e, EntryPoint.MessageType.WARNING, EntryPoint.Component.CONNECTION_MANAGER);
			}
		}

	}

}