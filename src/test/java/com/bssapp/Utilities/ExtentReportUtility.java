package com.bssapp.Utilities;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

//For email
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.bssapp.TestBase.BaseClass;



public class ExtentReportUtility extends TestListenerAdapter   {
	

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;
	private long startTime;
	String reportName ;
	
	 BaseClass b;
	@Override
	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
	    reportName = "Test-Report-" + timeStamp + ".html";
		String reportPath = System.getProperty("user.dir") + "/Reports/" + reportName;
	
		htmlReporter = new ExtentSparkReporter(reportPath);
         b=new BaseClass();
		String extentConfigPath = System.getProperty("user.dir")+"/extent-config.xml";

		try 
		{
			htmlReporter.loadXMLConfig(new File(extentConfigPath));
		} catch (IOException e) {
			System.err.println("Unable to load report configuration: " + e.getMessage());
		}

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		configureReporter();
		setSystemInfo();
	}
	
	private void setSystemInfo() {
        try {
            String hostName = InetAddress.getLocalHost().getHostName();
            extent.setSystemInfo("Host Name", hostName);
        } catch (UnknownHostException e) {
            System.err.println("Unable to get the Host Name: " + e.getMessage());
        }
        String env = null;
		try {
			env = b.getProperty(b.envUrlPropertiesPath,"env");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
     
        extent.setSystemInfo("Environment", env);
        extent.setSystemInfo("User", System.getProperty("user.name"));
    }

	  @Override
	    public void onTestStart(ITestResult result) {
	        startTime = System.currentTimeMillis();
	        logger = extent.createTest(result.getName());
	    }
	  
	private void configureReporter() {
		htmlReporter.config().setDocumentTitle("BSS Test Project");
		htmlReporter.config().setReportName("Functional Test Report");
		htmlReporter.config().setProtocol(Protocol.HTTPS);
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setTheme(Theme.DARK);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		logTestResult(result, Status.PASS);
	}

	@Override
	public void onTestFailure(ITestResult result) {
        WebDriver driver1 = b.getDriver();
		//logger = extent.createTest(result.getName());
	//	logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		logTestResult(result, Status.FAIL);
		String failureMessage = result.getThrowable().getMessage();
	    logger.log(Status.FAIL, "Failure reason: " + failureMessage);
	  
	    		
		File screenshotFile=null;
		try {
			screenshotFile = new File(captureScreen(driver1,result.getName()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (screenshotFile.exists()) {
			try {
				logger.fail("ScreenShot below: " + logger.addScreenCaptureFromPath(captureScreen(driver1,result.getName())));
			} catch (Exception e) {
				logger.log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
			}
		} else {
			logger.log(Status.WARNING, "ScreenShot not found for test: " + result.getName());
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		
		logTestResult(result, Status.SKIP);
	}
	
	private void logTestResult(ITestResult result, Status status) {
        long duration = System.currentTimeMillis() - startTime;
        logger.log(status, MarkupHelper.createLabel(result.getName(), getColor(status)));
        logger.log(Status.INFO, "Execution time: " + duration + " ms");
    }
 
    private ExtentColor getColor(Status status) {
        switch (status) {
            case PASS: return ExtentColor.GREEN;
            case FAIL: return ExtentColor.RED;
            case SKIP: return ExtentColor.ORANGE;
            default: return ExtentColor.BLACK;
        }
    }

	@Override
	public void onFinish(ITestContext testContext) {
		
		logger = extent.createTest("Summary Report");
        logger.info("Total Tests: " + testContext.getAllTestMethods().length);
        logger.info("Passed Tests: " + testContext.getPassedTests().size());
        logger.info("Failed Tests: " + testContext.getFailedTests().size());
        logger.info("Skipped Tests: " + testContext.getSkippedTests().size());
		extent.flush();
		//To send email with attachment
			//	sendEmail("aurehman@birchstreet.net","dtspzlzrxypmrgns","akushwaha@birchstreet.net");
				
			}
			
			
			//User defined method for sending email..
			public void sendEmail(String senderEmail,String senderPassword,String recipientEmail)
			{
				// SMTP server properties
		        Properties properties = new Properties();
		        properties.put("mail.smtp.auth", "true");
		        properties.put("mail.smtp.starttls.enable", "true");
		        properties.put("mail.smtp.host", "smtp.office365.com");
		        properties.put("mail.smtp.port", "587");

		        // Create a Session object
		        Session session = Session.getInstance(properties, new Authenticator() {
		           protected PasswordAuthentication getPasswordAuthentication() {
		                return new PasswordAuthentication(senderEmail, senderPassword);
		            }
		        });

		        try {
		            // Create a MimeMessage object
		            Message message = new MimeMessage(session);

		            // Set the sender and recipient addresses
		            message.setFrom(new InternetAddress(senderEmail));
		            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
		         
		            // Set the subject
		            message.setSubject("Test Report with attachment");

		            // Create a MimeMultipart object
		            Multipart multipart = new MimeMultipart();

		            // Attach the file
		            String filePath = ".\\Reports\\"+reportName;
		            String fileName = reportName;

		            MimeBodyPart attachmentPart = new MimeBodyPart();
		            attachmentPart.attachFile(filePath);
		            attachmentPart.setFileName(fileName);

		            // Create a MimeBodyPart for the text content
		            MimeBodyPart textPart = new MimeBodyPart();
		            textPart.setText("Please find the attached file.");

		            // Add the parts to the multipart
		            multipart.addBodyPart(textPart);
		            multipart.addBodyPart(attachmentPart);

		            // Set the content of the message
		            message.setContent(multipart);

		            // Send the message
		            Transport.send(message);
		            System.out.println("Email sent successfully!");

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		            
			}
			private String getLineNumber(ITestResult e) {
		        StackTraceElement[] stackTrace = e.getThrowable().getStackTrace();
		        if (stackTrace.length > 0) {
		        	
		            return String.valueOf(stackTrace[0].getLineNumber());
		        }
		        return "Unknown line number";
		    }
			
public  String captureScreen(WebDriver driver,String testName) throws IOException
{
	
	b.ts = (TakesScreenshot)driver;
	File source = b.ts.getScreenshotAs(OutputType.FILE);
	String targetFilePath=System.getProperty("user.dir") + "/Screenshots/" + testName+LocalTime.now().format(DateTimeFormatter.ofPattern("HH_mm_ss")) + ".png";
	File target = new File(targetFilePath);
	source.renameTo(target);
	logger.info("Screenshot taken: " + target.getAbsolutePath());
	return targetFilePath;
	
}

}


//2024.01 - TCM-3032 - Extent Reports - ntripathi - 16October2024
//2024.02---Modified ScreeenShots Method---aurehman--16thoctober
//2024.03 - TCM-3043 Add hostName,env,UseName - ntripathi - 17October2024
//2024.04---TCM-3046--added Report EmailCode, get line number of error code---aurehman---17th/oct/2024