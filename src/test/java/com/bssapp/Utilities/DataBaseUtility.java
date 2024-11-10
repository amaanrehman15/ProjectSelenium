package com.bssapp.Utilities;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.bssapp.TestBase.BaseClass;



public class DataBaseUtility extends BaseClass{

	public Connection connection = null;
   
	public Connection ConnectDB() throws IOException, ClassNotFoundException, SQLException {

		String url=getProperty(envUrlPropertiesPath,"url");

		if (url.toLowerCase().contains("uat"))  {
			System.out.println( "test uat");
			url ="jdbc:oracle:thin:@//uatpub.gdiindia.com:1521/uatpub1"; // 'jdbc:oracle:thin:@10.10.2.36:1521:uatpub1'
		}
		else if (url.toLowerCase().contains("appg")){

			System.out.println( "testappgdi");
			url = "jdbc:oracle:thin:@//gdiqadb.gdiindia.com:1521/gdiqadb.gdiindia.com";
		}
		else if (url.toLowerCase().contains("preprod"))  {

			System.out.println( "testqa");
			url ="jdbc:oracle:thin:@//preprod.gdiindia.com:1521/preprod"; // 'jdbc:oracle:thin:@10.10.2.36:1521:uatpub1'
		}
		

		        String username= "akushwaha[amer5]";

				String password ="GDIAUTO111";

				String conn =url;		//"jdbc:mysql://" + url + ":" + port + "/" + dbname

				Class.forName("oracle.jdbc.driver.OracleDriver");

				connection = DriverManager.getConnection(conn, username, password);
			    return connection;
				
			
	}
    
   

	  public void connectPRODDB() throws ClassNotFoundException, SQLException, IOException{

		//Load driver class for your specific database type
		  String url=getProperty(envUrlPropertiesPath,"url");
		
		if (url.toLowerCase().contains("www12."))  {

			System.out.println( "testPROD");
			url ="jdbc:oracle:thin:@//repos8.gdiindia.com/repos8.birchstreet.net"; // 'jdbc:oracle:thin:@10.10.2.36:1521:uatpub1'
		}

		String username= "akushwaha";

		String password ="GDIAUTO112";

		String conn =url ;			//"jdbc:mysql://" + url + ":" + port + "/" + dbname

		Class.forName("oracle.jdbc.driver.OracleDriver");

		connection = DriverManager.getConnection(conn, username, password);
		
	}
	 public ResultSet executeQuery(String queryString) throws SQLException, ClassNotFoundException, IOException {
		                    ConnectDB();
		                    PreparedStatement preparedStatementSELECT = connection.prepareStatement(queryString);

			 ResultSet rs = preparedStatementSELECT.executeQuery();

			return rs;

		}
	 public void closeDatabaseConnection() throws SQLException {

			if(connection != null && !connection.isClosed()){

				connection.close();

			}
		}
}
//2024.01 - automation02 - Created This Utility------aurehman 09/10/2024