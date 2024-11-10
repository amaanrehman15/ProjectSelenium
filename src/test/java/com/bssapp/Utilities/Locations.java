package com.bssapp.Utilities;

public class Locations {


	public static String locations() {
		String linuxFileLocation = "/Data Files/GlobalVariables.xlsx";
		String windowFileLocation =".\\DataFiles\\GlobalVariables.xlsx";
		String location = "";
		String OS = System.getProperty("os.name");
		System.out.println(OS);
		if(OS.contains("Windows"))
		{

			location = windowFileLocation;
		}
		else if(OS.contains("Linux"))
		{
			
			location = linuxFileLocation;
		}
		System.out.println(location);
		return location;
	}
}

