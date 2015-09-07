package org.iot.server.properties;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;


public class ApplicationProperties
{
	static Properties properties = new Properties();
	static String propertiesPath = "src/main/resources/application.properties";
	
	static
	{
		InputStream input = null;

		try {

			input = new FileInputStream(propertiesPath);
						
			// load a properties file
			properties.load(input);
		}
		catch (IOException ex)
		{}
		finally
		{
			if(input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{}
			}
		}
	}
	
	public static String getProperty(String name)
	{
		return properties.getProperty(name);
	}
}
