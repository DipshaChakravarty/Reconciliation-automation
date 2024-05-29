package qa.com.recon.logic;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.datatransfer.StringSelection;
import com.mysql.cj.protocol.Resultset;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import qa.com.recon.base.TestBase;
import java.io.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

public class Rulesheetingestion extends TestBase  {

	By pushmenuicon=By.xpath("/html/body/div/header/div/a[1]");
	By rulemasterclick=By.xpath("//span[contains(text(),'Rule Master')]");
	
	By rulerefrenceheader=By.xpath("//th[contains(text(),'Rule Reference')]");
	By rulerefrencecells=By.xpath("//*[@id=\"ViewRules\"]/tbody/tr/td[2]");
	By Approvelabels=By.xpath("//*[@id='ViewRules']/tbody/tr/td/div/label");
	By Approvebtn=By.xpath("//thead/tr[1]/th[67]/button[1]");
	By Errorbox =By.xpath("/html/body/div[2]/div");
	By Viewbtn=By.xpath("//button[@id='btnview']");
	By Entriestxtbx=By.xpath("//*[@id=\"ViewRules_length\"]/label/select");
	By rulesrows=By.xpath("//*[@id='ViewRules']/tbody/tr");
   
	By gbruploadicon=By.xpath("//i[@class='fa fa-upload']");
	By UploadGBRclick=By.xpath("//a[@href='/GBRIngestions']");
	By GBRtitle=By.xpath("//strong[contains(text(),'GBR Ingestion')]");
	By UploadGBRtext=By.xpath("//b[contains(text(),'Upload GBR')]");
	By GBRdropdown=By.id("GbrType");
	By Choosefilebtn=By.id("Excelfilename");
	By Overwritecheckbox=By.xpath("//label[@for='OverwriteYN']");
	By yesbtn=By.xpath("//button[contains(text(),'Yes')]");
	By uploadbtn1=By.id("btnUpload");
	By errortitle=By.xpath("/html/body/div[2]/div/h2");
	
	//uplaod screen
	By UploadRulesLabel=By.xpath("//b[contains(text(),'Upload Rule Sheet')]");
	By ChooseFilebtn =By.xpath("//input[@type='file']");
	By uploadicon=By.xpath("/html/body/div/aside/section/ul/li[1]/a/i");
	By uploadrulesheetclick=By.xpath("//a[@href='/RuleSheetMasters']");
	By approverulesheetclick=By.xpath("//a[@href='/ViewRules']");
	By choosebtn=By.xpath("//input[@id='Excelfilename']");
	By uploadbtn=By.xpath("//button[@id=\"btnUpload\"][contains(text(),'Upload')]");
	By Clearbtn=By.xpath("//*[@id=\"frmrulesheet\"]/div/section/div/div/div/div[2]/div[2]/div[2]/button[2]");
	By DownloadFormetBtn = By.xpath("//*[@id=\"frmrulesheet\"]/div/section/div/div/div/div[2]/div[2]/div[3]/button[2]");
	By ViewExistingruleBtn = By.xpath("//body[1]/div[1]/div[2]/form[1]/div[1]/section[1]/div[1]/div[1]/div[1]/div[2]/div[2]/div[3]/button[1]");
	By ErrorMsg 			= By.xpath("//label[@id='lblerror']");
	
    WebDriverWait wait=new WebDriverWait(driver,30);
    CSVReader csvReader;
    String[] csvCell;
    String rule_refrence =null;
    String dbURL = prop.getProperty("dburl");
	String username =  prop.getProperty("DBusename");
	String Password = prop.getProperty("DBPassword");
	
	public boolean rulesheetupload() throws InterruptedException, CsvValidationException, IOException, SQLException, ClassNotFoundException {
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(uploadrulesheetclick)).click();
		driver.findElement(choosebtn).sendKeys(path);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(2000);
		driver.findElement(yesbtn).click();
		Thread.sleep(2000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String message = driver.findElement(Errorbox).getText();
		
		System.out.println("Message after uploading file is : " + message);

		if (message.contains("Success")) {
			driver.navigate().refresh();
			Thread.sleep(1000);
			driver.findElement(pushmenuicon).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
			driver.findElement(rulemasterclick).click();
		} else {
			System.out.println("Rule sheet didn't get ingest to the system.");
		}
////	driver.navigate().refresh();
//		Thread.sleep(10000);
//		driver.findElement(pushmenuicon).click();
//		Thread.sleep(1000);
	//	wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick));
		
//		driver.findElement(rulemasterclick).click();
//		Thread.sleep(1000);
//		//wait.until(ExpectedConditions.presenceOfElementLocated(approverulesheetclick));
		
		driver.findElement(approverulesheetclick).click();
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(Viewbtn));
		Select se = new Select(driver.findElement(Entriestxtbx));
		se.selectByIndex(3);
		//String confirmmsg = null;
		
		String data=driver.findElement(By.xpath("//*[@id=\"ViewRules_info\"]")).getText();
		Thread.sleep(1000);
		String count[]=data.split("of ");
		String datacount[]=count[1].split(" entries");
		String number=datacount[0];
		int num=Integer.parseInt(number);
		System.out.println("number is : " + number);
		
		List<String> rule_idlist=new ArrayList<String>();
		
//		//System.out.println("rule_id is : " + rule_idlist);
		//Properties prop=new Properties();
//		Properties prop = new Properties();
//			
//			
//				String url11 = System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\logic\\config.properties";
//			FileInputStream ip = new FileInputStream(url11);
//
//			prop.load(ip);

		String dbURL = prop.getProperty("dburl");
		String username =  prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");
		System.out.println("dburl is : " +dbURL);
		System.out.println("driver Loaded");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs = null;
		
		
		for(int i =0; i<num;i++)
		{
		
			Thread.sleep(3000);
			WebElement rule_id=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[2]"));
			 System.out.println( "string rule_id is " +rule_id.getText() );
			int rule=Integer.parseInt(rule_id.getText());
			 String selectquery ="UPDATE Rule_Sheet_Master SET `Status` = '1',`Updated_on`= current_timestamp(), `Updated_by`='3' WHERE (`Rule_Reference` = '"+rule+"');";
			System.out.println("query is : " + selectquery);
			PreparedStatement pst = con.prepareStatement(selectquery);
			pst.executeUpdate();
		
		}
			driver.navigate().refresh();

		return message.contains("Success");
	}

	public boolean gbringestion1() throws InterruptedException {
		
		wait.until(ExpectedConditions.presenceOfElementLocated(gbruploadicon));
		driver.findElement(gbruploadicon).click();
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		if(path1.contains("Life")) {
			System.out.println("GBR life found.");
		Select se=new Select(driver.findElement(GBRdropdown));
		se.selectByIndex(1);
		}
		else {
			System.out.println("GBR life not found.");
		}
		
		driver.findElement(Choosefilebtn).sendKeys(path1);
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String successmsg = driver.findElement(errortitle).getText();
		return successmsg.contains("success");

	}
	
	public boolean Upload_Rule_sheet() throws InterruptedException {
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(uploadrulesheetclick)).click();
		Boolean UploadLabel   		= driver.findElement(UploadRulesLabel).isDisplayed();
		Boolean ChooseFileBtn 		= driver.findElement(ChooseFilebtn).isDisplayed();
		Boolean UploadFileBtn 		= driver.findElement(uploadbtn).isDisplayed();
		Boolean ClearBtn      		= driver.findElement(Clearbtn).isDisplayed();
		Boolean DownloadformetBtn	= driver.findElement(DownloadFormetBtn).isDisplayed();
		Boolean ViewRulesBtn 		= driver.findElement(ViewExistingruleBtn).isDisplayed();
		
		driver.findElement(uploadbtn).click();
		Thread.sleep(2000);
		Boolean errorlable = driver.findElement(ErrorMsg).isDisplayed();
		Thread.sleep(2000);
		driver.findElement(Clearbtn).click();
		Thread.sleep(2000);
		Boolean errorlableafter = driver.findElement(ErrorMsg).isDisplayed();
		Thread.sleep(2000);
		driver.findElement(ViewExistingruleBtn).click();
		Thread.sleep(2000);
		boolean Viewexistingrule = driver.findElement(By.xpath("//strong[contains(text(),'View Existing Rules')]")).isDisplayed();
		
		return UploadLabel && ChooseFileBtn && UploadFileBtn && ClearBtn && errorlableafter != true &&
			 DownloadformetBtn && ViewRulesBtn && errorlable && Viewexistingrule; 
	} 
	
	public boolean Check_Choose_Button() throws Exception {
		String csvfile = ".csv";
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(uploadrulesheetclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ChooseFilebtn));
		String PathValidForValid = System.getProperty("user.dir")+"\\DiffrenetFilesTest\\Rule sheet.csv";
		driver.findElement(ChooseFilebtn).sendKeys(PathValidForValid);
		String FileAvailable = driver.findElement(ChooseFilebtn).getAttribute("value");
		System.out.println(FileAvailable);
		Boolean CheckFileAvailable = FileAvailable.isEmpty();
		Thread.sleep(4000);
		String FileAvailableAccept = driver.findElement(ChooseFilebtn).getAttribute("accept");
		System.out.println(FileAvailableAccept);
	    Thread.sleep(2000);
	    String PathValidForInvalid = System.getProperty("user.dir")+"\\DiffrenetFilesTest\\InvalidRulesheet.csv";
	    driver.findElement(ChooseFilebtn).sendKeys(PathValidForInvalid);
	    driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
	    Thread.sleep(2000);
	    String FileAvailableAfter = driver.findElement(ChooseFilebtn).getAttribute("value");
	    Boolean CheckFileAvailableAfter = FileAvailableAfter.isEmpty();
	    System.out.println(CheckFileAvailableAfter);
	    
	    boolean CheckAccept = FileAvailableAccept.equals(csvfile);
	    return CheckFileAvailable == false && CheckAccept && CheckFileAvailableAfter;
	}
	
	public boolean Check_Button() throws Exception {
		String csvfile = ".csv";
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(uploadrulesheetclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ChooseFilebtn));
		String PathValidForValid = System.getProperty("user.dir")+"\\DiffrenetFilesTest\\Rule sheet.csv";
		driver.findElement(ChooseFilebtn).sendKeys(PathValidForValid);
		String FileAvailable = driver.findElement(ChooseFilebtn).getAttribute("value");
		System.out.println(FileAvailable);
		Boolean CheckFileAvailable = FileAvailable.isEmpty();
		Thread.sleep(4000);
		String FileAvailableAccept = driver.findElement(ChooseFilebtn).getAttribute("accept");
		System.out.println(FileAvailableAccept);
	    Thread.sleep(2000);
	    String PathValidForInvalid = System.getProperty("user.dir")+"\\DiffrenetFilesTest\\InvalidRulesheet.csv";
	    driver.findElement(ChooseFilebtn).sendKeys(PathValidForInvalid);
	    driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
	    Thread.sleep(2000);
	    String FileAvailableAfter = driver.findElement(ChooseFilebtn).getAttribute("value");
	    Boolean CheckFileAvailableAfter = FileAvailableAfter.isEmpty();
	    System.out.println(CheckFileAvailableAfter);
	    
	    boolean CheckAccept = FileAvailableAccept.equals(csvfile);
	    return CheckFileAvailable == false && CheckAccept && CheckFileAvailableAfter;
	}
	
	public boolean Valid_Dublicate() throws Exception {
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(uploadrulesheetclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ChooseFilebtn));
		String PathForValid = System.getProperty("user.dir")+"\\DiffrenetFilesTest\\Rule sheet.csv";
		driver.findElement(ChooseFilebtn).sendKeys(PathForValid);
	    driver.findElement(uploadbtn).click();
	    Thread.sleep(2000);
	    driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		Boolean CheckDuplication = driver.findElement(By.xpath("//h2[contains(text(),'Success')]")).isDisplayed();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(ViewExistingruleBtn)).click();
		Thread.sleep(2000);
		boolean CheckisDisplayed = driver.findElement(By.xpath("//td[contains(text(),'Automation @ Mohit')]")).isDisplayed();
		Thread.sleep(2000);
		driver.findElement(pushmenuicon).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(uploadrulesheetclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ChooseFilebtn));
		driver.findElement(ChooseFilebtn).sendKeys(PathForValid);
	    driver.findElement(uploadbtn).click();
	    Thread.sleep(2000);
	    driver.findElement(yesbtn).click();
	    Thread.sleep(3000);
	    Boolean CheckDuplications = driver.findElement(By.xpath("//td[contains(text(),'Rule already exists in rule sheet Master')]")).isDisplayed();
	    System.out.println(CheckDuplication);
	    Thread.sleep(3000);
		
		System.out.println("dburl is : " +dbURL);
		System.out.println("driver Loaded");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		String selectquery ="DELETE FROM `db_ReconDev`.`Rule_Sheet_Master` WHERE (`Comment` = 'Automation @ Mohit');";
		System.out.println("query is : " + selectquery);
		PreparedStatement pst = con.prepareStatement(selectquery);
		pst.executeUpdate();
		return CheckisDisplayed && CheckDuplication && CheckDuplications;
		
	}
	
	public Boolean GetEditRuleScreen() throws Exception {
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(uploadrulesheetclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(ChooseFilebtn));
		String PathForValid = System.getProperty("user.dir")+"\\DiffrenetFilesTest\\RulesheetforEdit.csv";
		driver.findElement(ChooseFilebtn).sendKeys(PathForValid);
	    driver.findElement(uploadbtn).click();
	    Thread.sleep(2000);
	    driver.findElement(yesbtn).click();
		Thread.sleep(2000);
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs = null;
		/*
		 * String selectquery
		 * ="UPDATE `db_ReconDev`.`Rule_Sheet_Master` SET `Status` = '1',`Updated_on`= current_timestamp(), `Updated_by`='3' WHERE Comment = 'Edit @ Automation';"
		 * ; PreparedStatement pst = con.prepareStatement(selectquery);
		 * pst.executeUpdate();
		 */
		String getid 	=  "Select id from `db_ReconDev`.`Rule_Sheet_Master` WHERE Comment = 'Edit @ Automation'";
		rs=st.executeQuery(getid);
		int idtobeused=0;
		while(rs.next()) {
			idtobeused=rs.getInt("id");
			System.out.println("query is : " + rs.getInt("id"));
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(ViewExistingruleBtn)).click();
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"ViewRules\"]/tbody/tr[1]/td[68]/div/a[@id='ToEdit_"+idtobeused+"']")).click();

		boolean CheckLable = driver.findElement(By.xpath("//label[contains(text(),'Rule condition :')]")).isDisplayed();
		String SelectQueryForDelete ="DELETE FROM `db_ReconDev`.`Rule_Sheet_Master` WHERE (`Comment` = 'Edit @ Automation');";
		System.out.println("query is : " + SelectQueryForDelete);
		PreparedStatement pst2 = con.prepareStatement(SelectQueryForDelete);
		pst2.executeUpdate();
		
		return CheckLable;
		}
	
	public void tc_expe() throws InterruptedException
    {
        Thread.sleep(2000);
        driver.findElement(pushmenuicon).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
        driver.findElement(By.xpath("/html/body/div[1]/aside/section/ul/li[1]/ul/li[2]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"ViewRules\"]/tbody/tr[1]/td[68]/div/a[@id='ToEdit_1']")).click();
        Thread.sleep(4000);
        System.out.println("clicked on ele");
    }
 
}
