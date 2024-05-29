package qa.com.recon.logic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.com.recon.base.TestBase;
import qa.com.recon.utility.TestUtil;

public class Loginrecon extends TestBase{

	By mobiletextbx=By.id("mobileno");
	By otpbtn=By.id("btnotp");
	By otptxtbx=By.id("txtotp");
	By Verifybtn=By.id("btnverify");
	
	public void login() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(mobiletextbx).sendKeys(prop.getProperty("mobile_no"));
		Thread.sleep(1000);
		driver.findElement(otpbtn).click();
		Thread.sleep(8000);
		String otp=TestUtil.getotp();
		
		driver.findElement(otptxtbx).sendKeys(otp);
		Thread.sleep(1000);
		driver.findElement(Verifybtn).click();
		
	}
//	public static void main(String args[]) throws InterruptedException
//	{
//		String path = System.getProperty("user.dir");
//		System.setProperty("webdriver.chrome.driver", path + "\\Driver\\chromedriver.exe");
//		 WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://ec2-3-111-148-158.ap-south-1.compute.amazonaws.com:90/");
//		Thread.sleep(2000);
//		driver.findElement(By.id("mobileno")).sendKeys("6355758381");
//		Thread.sleep(1000);
//		driver.findElement(By.id("btnotp")).click();
//		Thread.sleep(8000);
//		String otp=null;
//        String hostName = "smtp.gmail.com";//change it according to your mail
//        String username = "integration.dev@andapp.in";//username 
//        String password = "Andapp@2022";//prop.getProperty("gmail_password");
//        int messageCount;
//        int unreadMsgCount;
//        String emailSubject;
//        Message emailMessage;
//        Properties sysProps = System.getProperties();
//        sysProps.setProperty("mail.store.protocol", "imaps");
//             try {
//
//            Session session = Session.getInstance(sysProps, null);
//            Store store = session.getStore();
//            
//            store.connect(hostName, username, password);
//            Folder emailBox = store.getFolder("Inbox");
//
//            emailBox.open(Folder.READ_WRITE);
//
//            messageCount = emailBox.getMessageCount();
//            unreadMsgCount = emailBox.getUnreadMessageCount();
//
//            for(int i=messageCount; i>(messageCount-unreadMsgCount); i--)
//           {
//            	emailMessage = emailBox.getMessage(i);
//            	emailSubject = emailMessage.getSubject();
//                if(emailSubject.contains("Recon – OTP"))
//                {
//                    System.out.println("OTP mail found");
//                    String line;
//                    StringBuffer buffer = new StringBuffer();
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(emailMessage.getInputStream()));
//                    while ((line = reader.readLine()) != null) {
//                        buffer.append(line);
//                    }
//
//                    String[] message=buffer.toString().split("</b>");
//                    String splitedmssg=message[1];
//                    String[] otpspliitted=splitedmssg.split("<b>");
//                    otp=otpspliitted[1];
//                    
//                    System.out.println("Text found : "+ otp);
//                    emailMessage.setFlag(Flags.Flag.SEEN, true);
//                    break;
//                }
//
//                emailMessage.setFlag(Flags.Flag.SEEN, true);
//            }
//            emailBox.close(true);
//            store.close();
//            System.out.println("otp is " + otp);
//            Thread.sleep(1000);
//		driver.findElement(By.id("txtotp")).sendKeys(otp);
//		Thread.sleep(1000);
//		driver.findElement(By.id("btnverify")).click();
//		Thread.sleep(6000);
//             } catch (Exception mex) {
//            mex.printStackTrace();
//            System.out.println("OTP Not found ");
//            
//           
//	}
//	
//	}
	
}
