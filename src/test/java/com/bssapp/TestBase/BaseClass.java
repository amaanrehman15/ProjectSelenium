package com.bssapp.TestBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.bssapp.PageObjects.Page_APInvoice;
import com.bssapp.PageObjects.Page_AccountPayable;
import com.bssapp.PageObjects.Page_Attachments;
import com.bssapp.PageObjects.Page_BrowseCatalogs;
import com.bssapp.PageObjects.Page_Cart;
import com.bssapp.PageObjects.Page_Favorites;
import com.bssapp.PageObjects.Page_Home;
import com.bssapp.PageObjects.Page_Inbox;
import com.bssapp.PageObjects.Page_InvoiceMaintenance;
import com.bssapp.PageObjects.Page_LogIn;
import com.bssapp.PageObjects.Page_ManageOrders;
import com.bssapp.PageObjects.Page_MySetting;
import com.bssapp.PageObjects.Page_Notes;
import com.bssapp.PageObjects.Page_OrderGuide;
import com.bssapp.PageObjects.Page_PO;
import com.bssapp.PageObjects.Page_POEditLine;
import com.bssapp.PageObjects.Page_POHeaderUpdate;
import com.bssapp.PageObjects.Page_POPrintwDetails;
import com.bssapp.PageObjects.Page_Purchasing;
import com.bssapp.PageObjects.Page_REQ;
import com.bssapp.PageObjects.Page_REQHeaderUpdate;
import com.bssapp.PageObjects.Page_Receiving;
import com.bssapp.PageObjects.Page_Requisitions;
import com.bssapp.PageObjects.Page_SOI;
import com.bssapp.PageObjects.Page_SupplierInvoice;
import com.bssapp.PageObjects.Page_SupplierPO;
import com.bssapp.PageObjects.Page_SupplierPortal;
import com.bssapp.Utilities.DataBaseUtility;
import com.bssapp.Utilities.RunBatchFileUtility;

@Listeners(com.bssapp.Utilities.ExtentReportUtility.class)

public class BaseClass {
	FileReader reader;
	Properties p;
	private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
	public String envUrlPropertiesPath=".//Configurations//EnvironmentURL.properties";
	public String credetialsPropertiesPath=".//Configurations//Credentials.properties";
	public String url;
	public String env;
	public SoftAssert assertSoft;
	public Page_Home home;
	public Page_BrowseCatalogs browseCatalogs;
	public Page_Cart cart;
	public Page_PO PO;
	public Page_Purchasing purchasing;
	public Page_ManageOrders orders;
	public Page_POHeaderUpdate header;
	public Page_POEditLine editLine;
	public Page_POPrintwDetails details;
	public Page_SupplierPO supplierPO;
	public Page_SupplierPortal supplierPortal;
	public Page_Receiving receive;
	public Page_AccountPayable accountPayable;
	public Page_InvoiceMaintenance invoiceMaintenance;
	public Page_Favorites fav;
    public Page_REQ req;
	public Page_Requisitions requisitions;
	public Page_REQHeaderUpdate reqHeader;
	public Page_APInvoice apInvoice;
	public Page_SupplierInvoice supplierInvoice;
	public Page_Notes notes;
	public Page_OrderGuide pageOg;
    public  Logger logger;
    public Page_Attachments attachments;
    public String fileName="birchStreet-logo-1.png";
    public Page_SOI soi;
	String userName;
    String password;
	public WebDriver driver;
	public ActionsClass ActionsClass ;
	public UserDefinedMethods UserDefinedMethods ;
	public Actions action;
	public BaseClass BaseClass;
	public DataBaseUtility DataBaseUtility;
	public TakesScreenshot ts;
	
	public RunBatchFileUtility RunBatchFileUtility;
	@BeforeClass
	@Parameters({"os","browser"})
	public void setup(@Optional("windows")String os,@Optional("chrome") String br) throws IOException, URISyntaxException, InterruptedException {
		
		logger = Logger.getLogger("BSSAutomation");
		PropertyConfigurator.configure("log4j.properties");
		env=getProperty(envUrlPropertiesPath,"env");
		System.out.println(env);
		url=getProperty(envUrlPropertiesPath,"url");
		System.out.println(url);
		
		if(getProperty(envUrlPropertiesPath,"execution_env").equalsIgnoreCase("remote"))
		{
			
			
		RunBatchFileUtility=new RunBatchFileUtility();
		RunBatchFileUtility.runHub();
        Thread.sleep(2000);
		RunBatchFileUtility.runLocalNode();
		
			 Thread.sleep(4000);
			DesiredCapabilities capabilities=new DesiredCapabilities();
			 // Add the headless argument
			
			
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WINDOWS);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
				
			}
			else if (os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox": capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser"); return;
			}
			if(getProperty(envUrlPropertiesPath,"mode").equalsIgnoreCase("headless")) {
				ChromeOptions options = new ChromeOptions();
		        options.addArguments("--headless");
		        options.addArguments("--disable-gpu");
		        capabilities.merge(options);
				}
			driver=new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(),capabilities);
		}
		
				
		if(getProperty(envUrlPropertiesPath,"execution_env").equalsIgnoreCase("local"))
		{

			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver(); break;
			case "firefox": driver=new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return;
			}
		}
		setDriver(driver);
		driver.get(url);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15l));
		assertSoft=new SoftAssert();
		 getUserNameAndPassword();
		 ActionsClass=new ActionsClass(driver);
		 UserDefinedMethods=new UserDefinedMethods(driver);
		 action = new Actions(driver);
		 BaseClass=new BaseClass();
		
		
	}
	
	
	public  String getProperty(String path,String property) throws IOException {	

		reader=new FileReader(path);
		p=new Properties();
		p.load(reader);
		String prop=p.getProperty(property.toLowerCase());
		return prop;
	}

public void getUserNameAndPassword() throws IOException {

	String userName=getProperty(credetialsPropertiesPath,"user_name");
	System.out.println(userName);
	this.userName=userName;
	String password=getProperty(credetialsPropertiesPath,"password");
	System.out.println(password);
	this.password=password;
}


	public  void logIn(String subId) throws IOException {

		Page_LogIn p=new Page_LogIn(driver);
		p.inputLogInId(userName);
		logger.info("Enter User Name");
		p.inputPassword(password);
		logger.info("Enter Password");
		p.inputSubscriberId(subId);
		logger.info("Enter Subscriber Id");
		p.clickOnLogInBtn();
		logger.info("Click on Log In Button");
		ActionsClass.waitForPageLoad(20);
		logger.info("Wait For Page Load");
	}
	public  void switchToCompany(String CompanyId) {
		
		ActionsClass.waitForElementClickable(home.icon_changeCompany, 10);
		logger.info("Wait for Change Company Icon for Clickable");
		home.clickonChangeCompanyIcon();
		logger.info("Click on Chgnage Company Icon");
		ActionsClass.switchToFrame("_dlgOpenerIframe1");
		logger.info("Switch to Frame");
		home.clickonChangeCompanyIdRadiobtn();
		logger.info("Click on Change Company Radio Button");
		home.inputCompanyId(CompanyId);
		logger.info("Enter Company Id");
		home.clickonOKbtn();
		logger.info("Click on OK Button");
		ActionsClass.switchtoDefaultContent();
		logger.info("Switch to Default Content");
		ActionsClass.waitForPageLoad(20);
		logger.info("Wait for Page load");

	}

	public  void connectDB () throws ClassNotFoundException, IOException, SQLException {

		DataBaseUtility=new DataBaseUtility();
		DataBaseUtility.ConnectDB();


	}


	public ResultSet executeQuery (String queryString) throws ClassNotFoundException, IOException, SQLException {
		DataBaseUtility=new DataBaseUtility();
		ResultSet result=DataBaseUtility.executeQuery(queryString);

		return result;
	}
	public  void closeDB() throws SQLException  {
		DataBaseUtility=new DataBaseUtility();
		DataBaseUtility.closeDatabaseConnection();
	}
	
	
	

	public   String getAttachmentFilePath() {
		return Paths.get(".//src/test/resources//"+fileName+"").toAbsolutePath().toString();
	}

	 public static WebDriver getDriver() {
	        return driverThreadLocal.get();
	    }

	    public static void setDriver(WebDriver driver) {
	        driverThreadLocal.set(driver);
	    }
	@AfterClass
	public void tearDown() throws IOException, InterruptedException {
		
		driver.quit();
	
		if(getProperty(envUrlPropertiesPath,"execution_env").equalsIgnoreCase("remote")) {
			RunBatchFileUtility=new RunBatchFileUtility();
			RunBatchFileUtility.closeGrid();
		}
		
		
	}

}
//2024.02--automation02---Created This Base Class,added Methods---aurehman---10/10/2024
//2024.03 - TCM-3032- Add Methods - ntripathi - 14Oct2024
//2024.04--TCM-3035--Added Objects of Pages--aurehman---15/10/2024
//2024.05---TCM-3035--Added Screen Shot Method--aurehman
//2024.6 - TCM-3086 -  Add logger for login and switchToCompany method - ntripathi - 7thNovemebr2024