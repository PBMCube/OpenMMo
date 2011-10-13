package net.petterroea.openmmo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EntryPoint implements Runnable{

	/**
	 * @param args
	 */
	static Class[] packets;
	static int logAmount = 21;
	public static void main(String[] args) {
		packets = new Class[255];
		packets[0] = PacketHandshake.class;
		EntryPoint start = new EntryPoint();
		Thread thread = new Thread(start);
		thread.start();

	}

	@Override
	public void run() {
		Thread connectionThread = new Thread(new ConnectionManager());
		connectionThread.start();
		long lastUpdate = System.currentTimeMillis();
		while(true)
		{
			long unprocessedTime = System.currentTimeMillis() - lastUpdate;
			if(unprocessedTime < 0)
			{
				log("Time ran backwards! Did you change the system clock?", MessageType.INFORMATION, Component.MAIN);
			}
		}
		
	}
	public enum MessageType
	{
		INFORMATION,
		WARNING,
		CRITICAL,
		STATUS,
		FML,
		OMFG,
		ERROR_SHUTDOWN
	}
	public enum Component
	{
		CONNECTION_MANAGER,
		PLAYER_MANAGER,
		MAIN
	}
	public static void log(String string, MessageType type, Component comp) {
		if(logAmount > 20)
		{
			logAmount = 0;
			System.out.println("Log syntax: [Date] [Message Type] [Component sending message] (Message)");
		}
		logAmount++;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateTime = dateFormat.format(date);
        System.out.println("[" + dateTime + "] "+ "[" + type.toString() + "] [" + comp + "] " + string);
		
	}

}
