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
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mysql.cj.protocol.Resultset;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import qa.com.recon.base.TestBase;
import qa.com.recon.utility.TestUtil;

import java.io.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;

public class Rulesheetingestion extends TestBase  {

	By pushmenuicon=By.xpath("/html/body/div[1]/header/div/a[1]");
	By rulemasterclick=By.xpath("//span[contains(text(),'Rule Master')]");
	 By uploadicon=By.xpath("/html/body/div/aside/section/ul/li[1]/a/i");
	By uploadrulesheetclick=By.xpath("//a[@href='/RuleSheetMasters']");
	By choosebtn=By.xpath("//input[@id='Excelfilename']");
	By uploadbtn=By.xpath("//button[@id=\"btnUpload\"][contains(text(),'Upload')]");
	By Clearbtn=By.xpath("//*[@id=\"frmrulesheet\"]/div/section/div/div/div/div[2]/div[2]/div[2]/button[2]");
	By rulerefrenceheader=By.xpath("//th[contains(text(),'Rule Reference')]");
	By rulerefrencecells=By.xpath("//*[@id=\"ViewRules\"]/tbody/tr/td[2]");
	By Approvelabels=By.xpath("//*[@id='ViewRules']/tbody/tr/td/div/label");
	By Approvebtn=By.xpath("//thead/tr[1]/th[67]/button[1]");
	By Errorbox =By.xpath("/html/body/div[2]/div");
	By Viewbtn=By.xpath("//button[@id='btnview']");
	By Entriestxtbx=By.xpath("//*[@id=\"ViewRules_length\"]/label/select");
	By rulesrows=By.xpath("//*[@id='ViewRules']/tbody/tr");
	By dashboardheader=By.xpath("//h2[contains(text(),'Dashboard')]");
	
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
	
	By approverulescreentitle=By.xpath("//*[@id=\"ViewRulesform\"]/div/section/div/div[1]/strong");
	By approverulesheetclick=By.xpath("//a[@href='/ViewRules']");
	By Datetxtboxapprove=By.name("DateSelect");
	By Fromdatetxtboxapprove=By.id("FromDate");
	By Todatetxtboxapprove=By.id("ToDate");
	By calendaryearapprove=By.xpath("//select[@data-handler='selectYear']");
	By calendarmonthapprove=By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/select[1]");
	By pageno1approve=By.xpath("//a[contains(text(),'1')]");
	By pageno2approve=By.xpath("//a[contains(text(),'2')]");
	By previouspageapprove=By.xpath("//*[@id=\"ViewRules_previous\"]");
	By nextpageapprove=By.xpath("//*[@id=\"ViewRules_next\"]");
	By Insccompanyapprovaltxtbx=By.id("select2-Company-container");
	By  Inscompanysmartsearchtxtbx=By.xpath("//span[@class='select2-search select2-search--dropdown']/input");
	By Mainproductapprovaltxtbx=By.id("select2-MainProduct-container");
	By Productapprovaltxtbx=By.id("select2-Product-container");
	By RTOapprovaltxtbx=By.xpath("//input[@placeholder=' Select RTO']");
	
	By RTOtextbxapproval=By.xpath("//span/ul[@class='select2-selection__rendered']");
	By clearbuttonapproval=By.xpath("//button[@class='btn btn-rounded btn-primary mt-4'][2]");
	By searchtxtbxapproval=By.xpath("//*[@id=\"ViewRules_filter\"]/label/input");
	
	WebDriverWait wait=new WebDriverWait(driver,30);
   
	
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
////		driver.navigate().refresh();
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
			String rule=rule_id.getText();
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
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox)) ;
		String successmsg = driver.findElement(errortitle).getText();
		return successmsg.contains("success");

	}
	
	public boolean Tc_256res1n2() throws InterruptedException
	{	
		Thread.sleep(10000);
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(dashboardheader));
		Thread.sleep(1000);
		String beforehover=driver.findElement(dashboardheader).getCssValue("cursor");
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick);
		builder.moveToElement(driver.findElement(approverulesheetclick));
		Thread.sleep(2000);
		String afterhover=driver.findElement(approverulesheetclick).getCssValue("cursor");
		driver.findElement(approverulesheetclick).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(approverulescreentitle));
	//	System.out.println("flag" + driver.findElement(approverulescreentitle).isDisplayed());
		return !afterhover.equals(beforehover) && afterhover.equals("pointer") && driver.findElement(approverulescreentitle).isDisplayed();
	}
	public boolean Tc_257res1n2() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String expselectedoption=TestUtil.sheet1.getRow(13).getCell(0).toString();
		String dropdownvalueexp=null;
		Thread.sleep(10000);
	//	wait.until(ExpectedConditions.presenceOfElementLocated(pushmenuicon));
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(approverulescreentitle));
		Select select=new Select(driver.findElement(Datetxtboxapprove)); 
	
		List <WebElement> option = select.getOptions();
		String options=null; 
	      int size = option.size();
	      boolean flag=false;
	      List<String> expdropdownvalue=new ArrayList<String>();
	      List<String> actualdropdownvalue=new ArrayList<String>();
	      for(int i = 0; i < 3 ; i++){
			  dropdownvalueexp=TestUtil.sheet1.getRow(i+13).getCell(0).toString();
			  expdropdownvalue.add(dropdownvalueexp);
	      }
			  for(int j =0; j<size ; j++){
	          options = option.get(j).getText();
	         actualdropdownvalue.add(options);
			 } 
//		System.out.println("first selected option is " +  select.getFirstSelectedOption().getText());
//		System.out.println("list exp " + expdropdownvalue+"list actual "+actualdropdownvalue);
//		System.out.println("Out of loop flag is" + expdropdownvalue.equals(actualdropdownvalue) +"2nd result" +select.getFirstSelectedOption().getText().equals(expselectedoption) );
	
	return expdropdownvalue.equals(actualdropdownvalue) && select.getFirstSelectedOption().getText().equals(expselectedoption) ;
	}
	public boolean TC_258res1n3() throws InterruptedException, InvalidFormatException {
		
			TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
			TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
			String expplaceholder=TestUtil.sheet1.getRow(16).getCell(0).toString();
			
			Thread.sleep(10000);
			driver.findElement(pushmenuicon).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
			driver.findElement(approverulesheetclick).click();
			String actualplaceholder=driver.findElement(Fromdatetxtboxapprove).getAttribute("placeholder");
			
			driver.findElement(Fromdatetxtboxapprove).click();
			Select monthoptions=new Select(driver.findElement(calendarmonthapprove));
			monthoptions.selectByVisibleText("Jul");
			
			Select yearoptions=new Select(driver.findElement(calendaryearapprove));
			yearoptions.selectByVisibleText("2022");
			driver.findElement(By.xpath("//a[contains(text(),'13')]")).click();
		
			Thread.sleep(2000);
			
			return expplaceholder.equals(actualplaceholder.trim()) && !driver.findElement(Fromdatetxtboxapprove).getAttribute("value").isEmpty();
	}
	public boolean TC_258res4() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		
		driver.findElement(Fromdatetxtboxapprove).sendKeys("13-Jul-2022");
		Thread.sleep(2000);
		String emptyvalue=driver.findElement(Fromdatetxtboxapprove).getAttribute("value");
		boolean flag1=emptyvalue.isEmpty();
		driver.navigate().refresh();
	return flag1;
	}
		
	public boolean TC_258res4_1() throws InterruptedException {
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		
		driver.findElement(Fromdatetxtboxapprove).click();
		Select monthoptions=new Select(driver.findElement(calendarmonthapprove));
		monthoptions.selectByVisibleText("Jul");
		
		Select yearoptions=new Select(driver.findElement(calendaryearapprove));
		yearoptions.selectByVisibleText("2022");
		driver.findElement(By.xpath("//a[contains(text(),'13')]")).click();
		
		driver.findElement(Todatetxtboxapprove).click();
		Select monthoptions1=new Select(driver.findElement(calendarmonthapprove));
		Select yearoptions1=new Select(driver.findElement(calendaryearapprove));
		monthoptions1.selectByVisibleText("Jul");
		yearoptions1.selectByVisibleText("2022");
		boolean flag =driver.findElement(By.xpath("//a[contains(text(),'12')]")).isEnabled();
		
		System.out.println("12th date is enabled" + flag);
		
		
		return flag;
	}
	
	public void rulesheetingestion(String pathofcsv) throws InterruptedException {
		 Thread.sleep(10000);
			driver.findElement(pushmenuicon).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(uploadrulesheetclick)).click();//
			driver.findElement(choosebtn).sendKeys(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\"+pathofcsv+"");
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
//				driver.findElement(pushmenuicon).click();
//				wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
//				driver.findElement(rulemasterclick).click();
			} else {
				System.out.println("Rule sheet didn't get ingest to the system.");
			}
	}
	public int approverulesheetclick() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(rulemasterclick).click();
		
			driver.findElement(approverulesheetclick).click();
			Thread.sleep(1000);
			//wait.until(ExpectedConditions.presenceOfElementLocated(Viewbtn));
			Select se = new Select(driver.findElement(Entriestxtbx));
			se.selectByIndex(3);
			String data=driver.findElement(By.xpath("//*[@id=\"ViewRules_info\"]")).getText();
			Thread.sleep(1000);
			String count[]=data.split("of ");
			String datacount[]=count[1].split(" entries");
			String number=datacount[0];
			int num=Integer.parseInt(number);
			System.out.println("number is : " + number);
		return num;
	}
	
	public boolean TC_269(int columnindex, String key,int num) throws IOException, InterruptedException
	{
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\rules_sheet2.csv"));
		String line;String[] cols = null ;
		String headerLine = br.readLine();
		
		List<String> expecteddata=new ArrayList<String>();
		
		while ((line = br.readLine()) != null) {
		     cols = line.split(",");
		     expecteddata.add(cols[columnindex]); 
		}
	//	 System.out.println( "Expected data is: " + expecteddata);
		 
	
				List<String> actualdatalist=new ArrayList<String>();
				List<String> actualdatalisttrimmed=new ArrayList<String>();
				List<String> expdatalistremaining=new ArrayList<String>();
				List<String> actualdatalisttrimmedremaining=new ArrayList<String>();
			for(int i =0; i<num;i++)
			{
				Thread.sleep(3000);
				WebElement expele=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[count(//*[@id=\"ViewRules_wrapper\"]/div[4]/div[2]/div[1]/div/"
						+ "table/thead/tr/th[contains(text(),'"+key+"')]/preceding-sibling::th)+1]"));
				String companylistactual=expele.getText();
				String trimmeddata=companylistactual.trim();
				actualdatalist.add(companylistactual);
				actualdatalisttrimmed.add(trimmeddata);
				// System.out.println( "string element data is " + element.getText() );
			}
		//	System.out.println("actual company list data is:" + actualdatalisttrimmed);
			 for (int d = 0; d < expecteddata.size(); d++) {
					  String expdata=expecteddata.get(d).toString();
					  
						for(int l=0; l <actualdatalist.size() ;l++ ) {
								 String actuldata=actualdatalisttrimmed.get(l).toString();
								 
								 if (expdata.equals(actuldata)){
									// System.out.println("data matched" + actuldata  +" " + expdata );
									 actualdatalisttrimmedremaining.add(actuldata);
									 expdatalistremaining.add(expdata);
									
									 break;
									 
								 }
								 else {
								//	 System.out.println("data doesn't match for " + actuldata  +" " + expdata );
								 }
							}
			 }
			 List<String> union = new ArrayList<String>(expecteddata);
				union.addAll(expdatalistremaining);
				List<String> union1 = new ArrayList<String>(expecteddata);
				union1.retainAll(expdatalistremaining);
				union.removeAll(union1);
				//System.out.println("union data" + union);
				
				List<String> unionactual = new ArrayList<String>(actualdatalisttrimmed);
				unionactual.addAll(actualdatalisttrimmedremaining);
				List<String> union1actual = new ArrayList<String>(actualdatalisttrimmed);
				union1actual.retainAll(actualdatalisttrimmedremaining);
				unionactual.removeAll(union1actual);
				//System.out.println("union data" + unionactual);
				
				System.out.println("Data didn't match for the values of expected data is: " + union + " actual data diaplayed is:" + unionactual);
				 boolean flag=union.equals(unionactual);
				Thread.sleep(1000);
			 return flag;
	}
	
	
	public void edit_rulesheetclick() throws InterruptedException
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
	public boolean TC_270(int columnindex,int num) throws IOException, InterruptedException
	{
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\rules_sheet2.csv"));
		String line;String[] cols = null ;
		String headerLine = br.readLine();
		
		List<String> expecteddata=new ArrayList<String>();
		
		while ((line = br.readLine()) != null) {
		     cols = line.split(",");
		     expecteddata.add(cols[columnindex]); 
		}
		 System.out.println( "Expected data is: " + expecteddata);
		 
	
				List<String> actualdatalist=new ArrayList<String>();
				List<String> actualdatalisttrimmed=new ArrayList<String>();
			
			for(int i =0; i<num;i++)
			{
				Thread.sleep(3000);
				WebElement expele=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[count(//*[@id=\"ViewRules_wrapper\"]/div[4]/div[2]/div[1]/div/"
						+ "table/thead/tr/th[contains(text(),'Product Name')]/preceding-sibling::th)]"));
				String companylistactual=expele.getText();
				String trimmeddata=companylistactual.trim();
				actualdatalist.add(companylistactual);
				actualdatalisttrimmed.add(trimmeddata);
				// System.out.println( "string element data is " + element.getText() );
			}
			System.out.println("actual company list data is:" + actualdatalisttrimmed);
			 List<String> expdatalistremaining=new ArrayList<String>();
				List<String> actualdatalisttrimmedremaining=new ArrayList<String>();
			 for (int d = 0; d < expecteddata.size(); d++) {
					  String expdata=expecteddata.get(d).toString();
					  
						for(int l=0; l <actualdatalist.size() ;l++ ) {
								 String actuldata=actualdatalisttrimmed.get(l).toString();
								 
								 if (expdata.equals(actuldata)){
									// System.out.println("data matched" + actuldata  +" " + expdata );
									 actualdatalisttrimmedremaining.add(actuldata);
									 expdatalistremaining.add(expdata);
									 break;
									 
								 }
								 else {
								//	 System.out.println("data doesn't match for " + actuldata  +" " + expdata );
								 }
							}
			 }
			 
			 List<String> union = new ArrayList<String>(expecteddata);
				union.addAll(expdatalistremaining);
				List<String> union1 = new ArrayList<String>(expecteddata);
				union1.retainAll(expdatalistremaining);
				union.removeAll(union1);
				
				
				List<String> unionactual = new ArrayList<String>(actualdatalisttrimmed);
				unionactual.addAll(actualdatalisttrimmedremaining);
				List<String> union1actual = new ArrayList<String>(actualdatalisttrimmed);
				union1actual.retainAll(actualdatalisttrimmedremaining);
				unionactual.removeAll(union1actual);
				
				System.out.println("Data didn't match for the values of expected data is: " + union + " actual data diaplayed is:" + unionactual);
				 boolean flag=union.equals(unionactual);
				Thread.sleep(1000);
			 return flag;
	}
	
	public void Tc_333() throws InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		
		Select se = new Select(driver.findElement(Entriestxtbx));
		se.selectByIndex(3);
		String data=driver.findElement(By.xpath("//*[@id=\"ViewRules_info\"]")).getText();
		Thread.sleep(1000);
		String count[]=data.split("of ");
		String datacount[]=count[1].split(" entries");
		String number=datacount[0];
		int num=Integer.parseInt(number);
	
		
		String dbURL = prop.getProperty("dburl");
		String username =  prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs = null;
		
		
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\rules_sheet2.csv"));
		String line;String[] cols = null ;
		String headerLine = br.readLine();
		
		List<String> expecteddata=new ArrayList<String>();
		List<String> actualdatalisttrimmed=new ArrayList<String>();
		List<Integer> rule_idlist=new ArrayList<Integer>();
		while ((line = br.readLine()) != null) {
		     cols = line.split(",");
		     expecteddata.add(cols[0]); 
		}
		 System.out.println( "Expected data is: " + expecteddata);
		
		int rule_id = 0;
		String Rule_ref;
		for(int i=0;i<expecteddata.size();i++)
		{
			 Rule_ref=expecteddata.get(i).toString();
			System.out.println("rule ref is " +Rule_ref);
			String selectquery ="SELECT * FROM Rule_Sheet_Master where Rule_Reference='"+Rule_ref+"';";
			System.out.println("selectquery " + selectquery);
			 rs = st.executeQuery(selectquery);
			 rs.next();
			 rule_id=rs.getInt("id");
			 rule_idlist.add(rule_id);
		
		}
		String trimmeddata;
		 WebElement expele = null;
		 String rowno = null;
		 for (int d = 0; d < expecteddata.size(); d++) {
			  String expdata=expecteddata.get(d).toString();
			  String rule_id1=rule_idlist.get(d).toString();
			for(int j =0;j<num;j++) {
			  expele=driver.findElement(By.xpath("//tbody/tr["+(j+1)+"]/td[count(//*[@id=\"ViewRules_wrapper\"]/div[4]/div[2]/div[1]/div/"
						+ "table/thead/tr/th[contains(text(),'Rule Reference')]/preceding-sibling::th)+1]"));
			 String companylistactual=expele.getText();
			  trimmeddata=companylistactual.trim();
			  actualdatalisttrimmed.add(trimmeddata);

							 if (expdata.equals(trimmeddata)){
								
								 String xpath=expele.toString();
								 String[] value=xpath.split("tbody/tr");
								  rowno=value[1].split("/td")[0];
								  System.out.println("row no is " + rowno );
								  driver.findElement(By.xpath("//tr"+rowno+"/td[68]/div/label[@for='Is_RuleCheck_"+rule_id1+"']")).click();	
								  Thread.sleep(3000);
								 break;
								
							 }
							 else {
							 }
					}
			 }
	}
	public boolean TC_336() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(rulemasterclick).click();
		
			driver.findElement(approverulesheetclick).click();
			Thread.sleep(1000);
			driver.findElement(pageno1approve).click();
				
			WebElement expele1=driver.findElement(By.xpath("//tbody/tr[1]/td[count(//*[@id=\"ViewRules_wrapper\"]/div[4]/div[2]/div[1]/div/"
						+ "table/thead/tr/th[contains(text(),'Rule Reference')]/preceding-sibling::th)+1]"));
				String rule_idonopage1=expele1.getText();
				driver.findElement(pageno2approve).click();
				Thread.sleep(1000);
				WebElement expele2=driver.findElement(By.xpath("//tbody/tr[1]/td[count(//*[@id=\"ViewRules_wrapper\"]/div[4]/div[2]/div[1]/div/"
						+ "table/thead/tr/th[contains(text(),'Rule Reference')]/preceding-sibling::th)+1]"));
				String rule_idonopage2=expele2.getText();
				System.out.println("page 1 id is:" +rule_idonopage1 + "page 2 id is : " +rule_idonopage2);
				
				return !rule_idonopage1.equals(rule_idonopage2);
	}
	
	public boolean TC_337() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(rulemasterclick).click();
		
			driver.findElement(approverulesheetclick).click();
			Thread.sleep(1000);
			
			Actions act=new Actions(driver);
			act.moveToElement(driver.findElement(previouspageapprove)).build().perform();
			String cursorvalue=driver.findElement(previouspageapprove).getCssValue("cursor");
			System.out.println("cursor value is : "+ cursorvalue);
			driver.findElement(pageno2approve).click();
			Thread.sleep(1000);
			WebElement expele1=driver.findElement(By.xpath("//tbody/tr[1]/td[count(//*[@id=\"ViewRules_wrapper\"]/div[4]/div[2]/div[1]/div/"
					+ "table/thead/tr/th[contains(text(),'Rule Reference')]/preceding-sibling::th)+1]"));
			String rule_idonopage1=expele1.getText();
			boolean flag1=driver.findElement(previouspageapprove).isEnabled();
			driver.findElement(previouspageapprove).click();
			WebElement expele2=driver.findElement(By.xpath("//tbody/tr[1]/td[count(//*[@id=\"ViewRules_wrapper\"]/div[4]/div[2]/div[1]/div/"
					+ "table/thead/tr/th[contains(text(),'Rule Reference')]/preceding-sibling::th)+1]"));
			String rule_idonopage2=expele2.getText();
			Thread.sleep(2000);
			
			return cursorvalue.equals("not-allowed")&& flag1 && !rule_idonopage1.equals(rule_idonopage2);
			
	}
	public boolean TC_338() throws InterruptedException
	{
		Actions act=new Actions(driver);
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(rulemasterclick).click();
		
			driver.findElement(approverulesheetclick).click();
			Thread.sleep(1000);
			
			act.moveToElement(driver.findElement(nextpageapprove)).build().perform();
			String cursorvalue=driver.findElement(nextpageapprove).getCssValue("cursor");
			
			driver.findElement(pageno2approve).click();
			Thread.sleep(1000);
			WebElement expele1=driver.findElement(By.xpath("//tbody/tr[1]/td[count(//*[@id=\"ViewRules_wrapper\"]/div[4]/div[2]/div[1]/div/"
					+ "table/thead/tr/th[contains(text(),'Rule Reference')]/preceding-sibling::th)+1]"));
			String rule_idonopage1=expele1.getText();
			
			driver.findElement(nextpageapprove).click();
			WebElement expele2=driver.findElement(By.xpath("//tbody/tr[1]/td[count(//*[@id=\"ViewRules_wrapper\"]/div[4]/div[2]/div[1]/div/"
					+ "table/thead/tr/th[contains(text(),'Rule Reference')]/preceding-sibling::th)+1]"));
			String rule_idonopage2=expele2.getText();
			Thread.sleep(2000);
			
			act.moveToElement(driver.findElement(nextpageapprove)).build().perform();
			String cursorvalue1=driver.findElement(nextpageapprove).getCssValue("cursor");
			
			return !cursorvalue.equals(cursorvalue1) && !rule_idonopage1.equals(rule_idonopage2);
	}
	public boolean TC_259res1n3() throws InterruptedException, InvalidFormatException {
		
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String expplaceholder=TestUtil.sheet1.getRow(16).getCell(0).toString();
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		String actualplaceholder=driver.findElement(Todatetxtboxapprove).getAttribute("placeholder");
		
		driver.findElement(Todatetxtboxapprove).click();
		Select monthoptions=new Select(driver.findElement(calendarmonthapprove));
		monthoptions.selectByVisibleText("Jul");
		
		Select yearoptions=new Select(driver.findElement(calendaryearapprove));
		yearoptions.selectByVisibleText("2022");
		driver.findElement(By.xpath("//a[contains(text(),'13')]")).click();
	
		Thread.sleep(2000);
		
		return expplaceholder.equals(actualplaceholder.trim()) && !driver.findElement(Todatetxtboxapprove).getAttribute("value").isEmpty();
	}
	public boolean TC_259res4() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		
		driver.findElement(Todatetxtboxapprove).sendKeys("13-Jul-2022");
		Thread.sleep(2000);
		String emptyvalue=driver.findElement(Todatetxtboxapprove).getAttribute("value");
		driver.navigate().refresh();
		//wait.until(ExpectedConditions.presenceOfElementLocated(Todatetxtboxapprove));
		Thread.sleep(1000);
		driver.findElement(Todatetxtboxapprove).click();
		Select monthoptions=new Select(driver.findElement(calendarmonthapprove));
		monthoptions.selectByVisibleText("Jul");
		
		Select yearoptions=new Select(driver.findElement(calendaryearapprove));
		yearoptions.selectByVisibleText("2022");
		driver.findElement(By.xpath("//a[contains(text(),'13')]")).click();
		
		driver.findElement(Fromdatetxtboxapprove).click();
		monthoptions.selectByVisibleText("Jul");
		yearoptions.selectByVisibleText("2022");
		boolean flag =driver.findElement(By.xpath("//a[contains(text(),'14')]")).isEnabled();
		System.out.println("14th date is enabled" + flag);
		
		
		return emptyvalue.isEmpty();
	}
	
	public boolean TC_260res1() throws InterruptedException, IOException, InvalidFormatException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		Thread.sleep(1000);
		
		boolean flag=false;
		for(int i = 0 ; i <= 2 ; i++) {
			String xpathname=TestUtil.sheet1.getRow(i+1).getCell(4).getStringCellValue();
				
		driver.findElement(By.id("select2-"+xpathname+"-container")).click();
		WebElement companytobeselected=driver.findElement(By.xpath("//ul[@id='select2-"+xpathname+"-results']/li[2]"));
		companytobeselected.click();
		Thread.sleep(2000);
		String expected=driver.findElement(By.id("select2-"+xpathname+"-container")).getText();
		flag=!expected.contains("Select");
		}
		return flag;
	}
	public boolean TC_260res2() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		Thread.sleep(1000);
		
		boolean flag=false;
		for(int i = 0 ; i <= 2 ; i++) {
			
			String xpathname=TestUtil.sheet1.getRow(i+1).getCell(4).getStringCellValue();
			String texttosend=TestUtil.sheet1.getRow(i+1).getCell(5).getStringCellValue();
		
			driver.findElement(By.id("select2-"+xpathname+"-container")).click();
	
			driver.findElement(Inscompanysmartsearchtxtbx).sendKeys(texttosend);
		Thread.sleep(500);
		
		String actualresult=driver.findElement(By.xpath("//*[@id=\"select2-"+xpathname+"-results\"]/li[1]")).getText();
		flag=actualresult.contains(texttosend);
		}
		return flag;
	}
	public boolean TC_260res5() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String errormsg=TestUtil.sheet1.getRow(9).getCell(1).getStringCellValue();
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		Thread.sleep(1000);
		
		String actualresult=null;
		
		for(int i = 0 ; i <= 2 ; i++) {
			
		String xpathname=TestUtil.sheet1.getRow(i+1).getCell(4).getStringCellValue();
		System.out.println("xpath name to be added is : " + xpathname);
		driver.findElement(By.id("select2-"+xpathname+"-container")).click();
		driver.findElement(Inscompanysmartsearchtxtbx).sendKeys("fish");
		Thread.sleep(500);
		 actualresult=driver.findElement(By.xpath("//*[@id=\"select2-"+xpathname+"-results\"]/li[1]")).getText();
		}
		return actualresult.equals(errormsg);
	}
	public boolean TC_260res3() throws InterruptedException, InvalidFormatException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		Thread.sleep(1000);
		
		boolean flag=false;
		for(int i = 0 ; i <= 2 ; i++) {
			
			String xpathname=TestUtil.sheet1.getRow(i+1).getCell(4).getStringCellValue();
			String texttosend=TestUtil.sheet1.getRow(i+1).getCell(6).getStringCellValue();
		
			driver.findElement(By.id("select2-"+xpathname+"-container")).click();
	
			driver.findElement(Inscompanysmartsearchtxtbx).sendKeys(texttosend);
		Thread.sleep(1000);
		
		String actualresult=driver.findElement(Inscompanysmartsearchtxtbx).getAttribute("value");
		//System.out.println("actual result is " + actualresult );
		flag=actualresult.contains(texttosend);
		}
		return !flag;
	}
	//
	
	
	public boolean TC_263res1() throws InterruptedException, IOException, InvalidFormatException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String expresult=TestUtil.sheet1.getRow(17).getCell(0).getStringCellValue();
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		Thread.sleep(1000);		
		driver.findElement(RTOtextbxapproval).click();
		WebElement companytobeselected=driver.findElement(By.xpath("//*[@id='select2-Rto-results']/li[1]"));
		companytobeselected.click();
		Thread.sleep(1000);		
		String actualresult=driver.findElement(By.xpath("//span/ul[@class='select2-selection__rendered']/li[1]")).getText();	
		System.out.println("results are: " + expresult + actualresult);
		return actualresult.contains(expresult);
	}
	public boolean TC_263res2() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		Thread.sleep(1000);
			String texttosend=TestUtil.sheet1.getRow(1).getCell(7).getStringCellValue();
		//	System.out.println("text to send is: " +texttosend);
	
			Thread.sleep(1000);
			Select se=new Select(driver.findElement(By.id("Rto")));
			se.selectByVisibleText(texttosend);
			Thread.sleep(500);
		String actualresult=driver.findElement(By.xpath("//span/ul[@class='select2-selection__rendered']/li[1]")).getText();
		return actualresult.contains(texttosend);
	}
	public boolean TC_263res6() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		Thread.sleep(1000);
		List<String> rtolist=new ArrayList<String>();
		List<String> actuallist=new ArrayList<String>();
		String texttosend=null;
		for(int i=0;i<=4;i++) {
			 texttosend=TestUtil.sheet1.getRow(i+1).getCell(7).getStringCellValue();
			 rtolist.add(texttosend);
			System.out.println("text to send is: " +texttosend);
	
			Thread.sleep(1000);
			Select se=new Select(driver.findElement(By.id("Rto")));
			se.selectByVisibleText(texttosend);
			Thread.sleep(500);
			String actualresult=driver.findElement(By.xpath("//span/ul[@class='select2-selection__rendered']/li["+(i+1)+"]")).getText();
			actuallist.add(actualresult);
		}
		boolean flag=false;
		for(int j=0;j<=4;j++)
		{
			String actual=actuallist.get(j).toString();
			String expected=rtolist.get(j).toString();
			flag=actual.contains(expected);
		}
	
		return flag;
	}
	
	
	public boolean TC_265res1n2() throws InterruptedException, InvalidFormatException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
	
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		Thread.sleep(1000);
		//from date selection
		driver.findElement(Fromdatetxtboxapprove).click();
		Select monthoptions=new Select(driver.findElement(calendarmonthapprove));
		monthoptions.selectByVisibleText("Jul");
		
		Select yearoptions=new Select(driver.findElement(calendaryearapprove));
		yearoptions.selectByVisibleText("2022");
		driver.findElement(By.xpath("//a[contains(text(),'13')]")).click();
		//to date selection
		Thread.sleep(500);
		driver.findElement(Todatetxtboxapprove).click();
		Select monthoptionsto=new Select(driver.findElement(calendarmonthapprove));
		monthoptionsto.selectByVisibleText("Jul");
		
		Select yearoptionsto=new Select(driver.findElement(calendaryearapprove));
		yearoptionsto.selectByVisibleText("2022");
		driver.findElement(By.xpath("//a[contains(text(),'20')]")).click();
		//rto selection
		String rtotexttosend=TestUtil.sheet1.getRow(1).getCell(7).getStringCellValue();
		Select se=new Select(driver.findElement(By.id("Rto")));
		se.selectByVisibleText(rtotexttosend);
		
		//3 textboxes selection
		for(int i = 0 ; i <= 2 ; i++) {
			
			String xpathname=TestUtil.sheet1.getRow(i+1).getCell(4).getStringCellValue();
			String texttosend=TestUtil.sheet1.getRow(i+1).getCell(5).getStringCellValue();
		
			driver.findElement(By.id("select2-"+xpathname+"-container")).click();
			driver.findElement(Inscompanysmartsearchtxtbx).sendKeys(texttosend);
		}
		
		driver.findElement(clearbuttonapproval).click();
		String bgcolor=driver.findElement(clearbuttonapproval).getCssValue("background-color");
		//System.out.println("colors are " + bgcolor.equals("rgba(239, 59, 133, 1)"));
		Thread.sleep(1000);
		boolean flag=driver.findElement(Fromdatetxtboxapprove).getAttribute("value").isEmpty();
		boolean flag1=driver.findElement(Todatetxtboxapprove).getAttribute("value").isEmpty();
		boolean flag2=driver.findElement(By.xpath("//span/ul[@class='select2-selection__rendered']/li[1]")).getText().isEmpty();
		boolean flag3=driver.findElement(By.id("select2-Company-container")).getText().contains("Select");
		boolean flag4=driver.findElement(By.id("select2-MainProduct-container")).getText().contains("Select");
		boolean flag5=driver.findElement(By.id("select2-Product-container")).getText().contains("Select");
		
		return flag && flag1 && flag2 && flag3 && flag4 && flag5 && driver.findElement(Fromdatetxtboxapprove).isDisplayed() && 
				driver.findElement(Todatetxtboxapprove).isDisplayed() &&
				driver.findElement(By.id("select2-Company-container")).isDisplayed() && 
				driver.findElement(By.id("select2-MainProduct-container")).isDisplayed() &&
				driver.findElement(By.id("select2-Product-container")).isDisplayed() && 
				driver.findElement(By.id("btnview")).isDisplayed() && 
				driver.findElement(clearbuttonapproval).isDisplayed() && 
				driver.findElement(By.xpath("//table[@role='grid']")).isDisplayed() &&
				driver.findElement(By.xpath("//button[@class='btn btn-primary buttons-excel buttons-html5']")).isDisplayed()
				&& driver.findElement(By.xpath("//strong[contains(text(),'Rules Approval List')]")).isDisplayed();
	}
	public boolean Tc_266() throws InvalidFormatException, InterruptedException, ClassNotFoundException, SQLException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(approverulesheetclick).click();
		Thread.sleep(1000);
		boolean flag=false;
		for(int i = 0 ; i <= 1 ; i++) {
			String texttosend=TestUtil.sheet1.getRow(i+1).getCell(9).getStringCellValue();
			driver.findElement(searchtxtbxapproval).sendKeys(texttosend);
			
			WebElement expele=driver.findElement(By.xpath("//tbody/tr[1]/td[count(//*[@id='ViewRules_wrapper']/"
					+ "div[4]/div[2]/div[1]/div/table/thead/tr/th[contains(text(),'Company Name')]/preceding-sibling::th)+1]"));
			String companylistactual=expele.getText();
			String trimmeddata=companylistactual.trim();
			flag=trimmeddata.contains(texttosend);
			System.out.println("flag is " + flag);
			driver.findElement(searchtxtbxapproval).clear();
			
		}
		
		return flag;
	}
	
	
}
 