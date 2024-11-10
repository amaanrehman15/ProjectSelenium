package com.bssapp.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunBatchFileUtility {

	
	String hubpath="D:\\BSSSELENIUM\\bss_sel_automation\\hub.bat";
	String localNode="D:\\BSSSELENIUM\\bss_sel_automation\\node.bat";
	String kill="taskkill /F /IM java.exe";
	Process process;
	Process process1;
	Process process2;
	public void runHub() throws IOException, InterruptedException {
		
	 process=Runtime.getRuntime().exec(hubpath);
		
		
	}
	public void runLocalNode() throws IOException, InterruptedException {
		
		 process1=Runtime.getRuntime().exec(localNode);
		
		}
	public void closeGrid() throws IOException, InterruptedException {
		
		 process2=Runtime.getRuntime().exec(kill);
			
			
			
		}
	
		
		
		
       
	}
    
	
	
	
	
	
	
	
	
	
	
	


