package net.petterroea.openmmo;

import java.io.*;
import java.util.*;

/**
 * propertiesWrapper.java
 * 
 * Wrapper for java.util.Properties, that makes life easier...
 * 
 * @author Petterroea
 */

public class PropertiesFile {
	private String product;
    private Properties propertiesFile = new Properties();
    private String fileExtension = ".properties";
	public PropertiesFile(String name)
	{
		this.product = name + fileExtension;
		File theFile = new File(product);
		try {
			
			if(theFile.exists())
			{
				reload();
			}
			else
			{
				save();
			}
		} catch (IOException ex)
		{
			System.out.println("Failed to load settings file: " + product);
			System.out.println("The Exeption is: " + ex);
		}
	}
	void reload() throws IOException
	{
		FileInputStream FileInStream = null;
		try{
			FileInStream = new FileInputStream(product);
			propertiesFile.load(FileInStream);
		} catch (IOException ex) {
			System.out.println("Failed to load settings file: " + product);
			System.out.println("The Exeption is: " + ex);
		} finally {
			try {
				if(FileInStream != null) {
					FileInStream.close(); 
					} 
				}catch (IOException ex) {
					System.out.println("Failed to load settings file: " + product);
					System.out.println("The Exeption is: " + ex);
				}
			}
		}
		void save() {
			FileOutputStream FileOutStream = null;
			try{
				FileOutStream = new FileOutputStream(product);
				propertiesFile.store(FileOutStream, null);
			} catch (IOException ex) {
				System.out.println("Failed to load settings file: " + product);
				System.out.println("The Exeption is: " + ex);
			} finally {
				try {
					if(FileOutStream != null) {
						FileOutStream.close(); 
						} 
					}catch (IOException ex) {
						System.out.println("Failed to load settings file: " + product);
						System.out.println("The Exeption is: " + ex);
					}
				}
			}
		public boolean containsKey(String name) 
		{
	        return propertiesFile.containsKey(name);
	    }
		public void removeKey(String name) 
		{
	        if (propertiesFile.containsKey(name)) 
	        {
	            propertiesFile.remove(name);
	            save();
	        }
	    }
		public String getProperty(String name) 
		{
	        return propertiesFile.getProperty(name);
	    }
		public String getString(String name) 
		{
			if(containsKey(name))
			{
				return getProperty(name);
			}
			return "";
			
		}
		public void setString(String name, String value)
		{
			propertiesFile.put(name, value);
			save();
		}
		public int getInt(String name)
		{
			if(containsKey(name))
			{
				return Integer.parseInt(getProperty(name));
			}
			return 0;
		}
		public void setInt(String name, int value)
		{
			propertiesFile.put(name, String.valueOf(value));
			
			save();
		}
		public boolean getBool(String name)
		{
			if(containsKey(name))
			{
				return Boolean.parseBoolean(getProperty(name));
			}
			return false;
		}
		public void setBool(String name, boolean value)
		{
			propertiesFile.put(name, String.valueOf(value));
			save();
		}
	}
	
