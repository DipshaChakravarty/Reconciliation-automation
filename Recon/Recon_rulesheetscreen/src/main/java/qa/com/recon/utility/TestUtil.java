package qa.com.recon.utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import qa.com.recon.base.TestBase;

public class TestUtil extends TestBase {
	public static long Page_Load_Timeout = 100;
	public static long Implicit_Wait=10;

	public static String getotp()
	{
		String otp=null;
        String hostName = "smtp.gmail.com";//change it according to your mail

        String username = prop.getProperty("gmail_username");//username 

        String password = prop.getProperty("gmail_password");

        int messageCount;

        int unreadMsgCount;

        String emailSubject;

        Message emailMessage;

        Properties sysProps = System.getProperties();

        sysProps.setProperty("mail.store.protocol", "imaps");
             try {

            Session session = Session.getInstance(sysProps, null);
          
            Store store = session.getStore();
           
            
            store.connect(hostName, username, password);
           
            Folder emailBox = store.getFolder("Inbox");

            emailBox.open(Folder.READ_WRITE);

            messageCount = emailBox.getMessageCount();

          

            unreadMsgCount = emailBox.getUnreadMessageCount();

            System.out.println("Unread Emails count:" + unreadMsgCount);
            	
           
            for(int i=messageCount; i>(messageCount-unreadMsgCount); i--)

            {

                emailMessage = emailBox.getMessage(i);

                emailSubject = emailMessage.getSubject();

                if(emailSubject.contains("Recon – OTP"))

                {

                    System.out.println("OTP mail found");

                    String line;

                    StringBuffer buffer = new StringBuffer();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(emailMessage.getInputStream()));

                    while ((line = reader.readLine()) != null) {

                        buffer.append(line);

                    }

                    String[] message=buffer.toString().split("</b>");
                    String splitedmssg=message[1];
                    String[] otpspliitted=splitedmssg.split("<b>");
                    otp=otpspliitted[1];
                    
                    System.out.println("Text found : "+ otp);
                    emailMessage.setFlag(Flags.Flag.SEEN, true);

                    break;

                }

                emailMessage.setFlag(Flags.Flag.SEEN, true);

            }

            emailBox.close(true);

            store.close();


        } catch (Exception mex) {

            mex.printStackTrace();

            System.out.println("OTP Not found ");

        }

       return otp;

    }
	public static Workbook book;
	public static Sheet sheet;
	public static String TESTDATA_SHEET_PATH="\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx";

	public static void getTestData() throws InvalidFormatException 
	{
		FileInputStream file = null;
		try {
			//	System.out.println(System.getProperty("user.dir"));
				file = new FileInputStream(System.getProperty("user.dir")+ TESTDATA_SHEET_PATH);
				
			//	System.out.println("file path is--> " + file);
			}
			
		 catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


}
