package net.petterroea.openmmo;

public class NetworkSettings {
	static boolean stillRunning = true;
	static int users = 0;
	static int newThreadsNeeded = 1;
	static int maxUsers = 20;
	static PropertiesFile properties;
	public static void loadSettings()
	{
		properties = new PropertiesFile("server");
		if(properties.containsKey("maxUsers"))
		{
			maxUsers = properties.getInt("maxUsers");
		}
		else
		{
			properties.setInt("maxUsers", maxUsers);
		}
	}
}