package qa.com.recon.logic;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.com.recon.utility.*;
import qa.com.recon.base.TestBase;

public class GBRingestion extends TestBase{
	
	By pushmenuicon=By.xpath("/html/body/div[1]/header/div/a[1]");
	By gbruploadicon=By.xpath("//i[@class='fa fa-upload']");
	By UploadGBRclick=By.xpath("//a[@href='/GBRIngestions']");
	By GBRtitle=By.xpath("//strong[contains(text(),'GBR Ingestion')]");
	By UploadGBRtext=By.xpath("//b[contains(text(),'Upload GBR')]");
	By GBRdropdown=By.id("GbrType");
	By Choosefilebtn=By.id("Excelfilename");
	By uploadbtn=By.id("btnUpload");
	By Clearbtnlinktetxt=By.linkText("Clear");
	
	By Clearbtn=By.xpath("//*[@id=\"frmGBR\"]/div/section/div/div/div/div[2]/div[2]/div[4]/button[2]");
	By Overwritecheckbox=By.xpath("//label[@for='OverwriteYN']");
	By yesbtn=By.xpath("//button[contains(text(),'Yes')]");
	By Errorbox=By.xpath("/html/body/div[2]/div");
	By errortitle=By.xpath("/html/body/div[2]/div/h2");
	By Overwritecheckboxyn=By.id("OverwriteYN");
	By Nofilechosenerror=By.xpath("//*[@id=\"lblerror\"]/li");
	By Areyousurepopupmsg=By.xpath("//p[contains(text(),'Are you')]");
	By Areyousurepopupyesbtn=By.xpath("//button[contains(text(),'Yes')]");
	By Areyousurepopupcancelbtn=By.xpath("//button[contains(text(),'Cancel')]");
	By Errortable=By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr");
	By Areyopusurepopup=By.xpath("/html/body/div[3]");
	By Griderrormsg=By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr/td[3]");
	By errorreportdownloadbtn=By.xpath("//span[contains(text(),'Excel')]");
	By Entriestxtbx=By.xpath("//label/select");
	By tdvalues=By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr[2]/td[2]");
	CARreport carreport=new CARreport();
	
	public WebDriverWait wait;
	
	//String GBR_test_sheet="\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\GBR data csvfile.csv";
	String GBR_test_sheet2="\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\GBR data demo 10 JUNE (1) (1).xlsx";
	String GBR_test_sheet3="\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\GBR data xls.xls";
	
	public  boolean gbringestion1(String GBR_test_sheet) throws InterruptedException
	{
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		
		if(GBR_test_sheet.contains("Life")) {
			System.out.println("GBR life found.");
		Select se=new Select(driver.findElement(GBRdropdown));
		se.selectByIndex(1);
		}
		else {
			System.out.println("GBR_GI found.");
		}
		
		
		driver.findElement(Choosefilebtn).sendKeys(GBR_test_sheet);
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String successmsg=driver.findElement(errortitle).getText();
	//	System.out.println("Message after uploading file is: " + successmsg);
		return successmsg.contains("Success");
		
	}
	
	public boolean Tc_385() {
		driver.findElement(gbruploadicon).click();
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		WebElement Gbrtitle=driver.findElement(GBRtitle);
		WebElement choosefilebtn=driver.findElement(Choosefilebtn);
		WebElement overwritecheckbox=driver.findElement(Overwritecheckbox);
		WebElement clearbtn=driver.findElement(Clearbtn);
		WebElement Uploadbtn=driver.findElement(uploadbtn);
		WebElement uploadGBRtext=driver.findElement(UploadGBRtext);
		WebElement Gbrdropdown=driver.findElement(GBRdropdown);
			
		return Gbrtitle.isDisplayed() && choosefilebtn.isDisplayed() && overwritecheckbox.isDisplayed() && clearbtn.isDisplayed()
				&& Uploadbtn.isDisplayed() && uploadGBRtext.isDisplayed() && Gbrdropdown.isDisplayed();
		
//		WebElement title=driver.findElement(GBRtitle);
//		if(title.isDisplayed()) {
//			int xcord = title.getLocation().getX();
//			System.out.println("Element's Position from left side Is "+xcord +" pixels.");
//			int ycord = title.getLocation().getY();
//			System.out.println("Element's Position from left side Is "+ycord +" pixels.");
//		}
		
	}
	public boolean Tc_386() throws InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		//System.out.println("row number is " + TestUtil.sheet.getLastRowNum());
		String exptitle=TestUtil.sheet.getRow(1).getCell(0).getStringCellValue();
		//System.out.println("exp title" + exptitle);
		driver.findElement(gbruploadicon).click();
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		String actualtitle=driver.findElement(GBRtitle).getText();
		//System.out.println();
		return exptitle.equals(actualtitle);
		
	}

	public boolean Tc_387res1() throws InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String expoption1=TestUtil.sheet.getRow(3).getCell(0).getStringCellValue();
		String expoption2=TestUtil.sheet.getRow(4).getCell(0).getStringCellValue();
		List<String> expoptions=new ArrayList<String>();
		List<String> actoptions=new ArrayList<String>();
		expoptions.add(expoption1);
		expoptions.add(expoption2);
		//res1
		System.out.println("expoptions are" + expoptions);
		driver.findElement(gbruploadicon).click();
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		Select drpdwn=new Select(driver.findElement(GBRdropdown));
		List<WebElement> dropdown=drpdwn.getOptions();
		String options=null;
		boolean flag=false;
		for(WebElement d:dropdown) {
			options=d.getText();
			System.out.println("gbr type options are : " + options);
			actoptions.add(options);
			
		}
		flag=actoptions.equals(expoptions); 
	
		return flag ;
	}
	public boolean Tc_387res2() throws InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String expselection=TestUtil.sheet.getRow(3).getCell(0).getStringCellValue();
		//res2
				String defaultselection=driver.findElement(GBRdropdown).getAttribute("value");
				System.out.println("default selection is " + defaultselection);
				
		return defaultselection.equals(expselection);
	}
	public boolean Tc_387res3() throws InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String expselection=TestUtil.sheet.getRow(4).getCell(0).getStringCellValue();
		//res3
		Select drpdwn=new Select(driver.findElement(GBRdropdown));
				drpdwn.selectByVisibleText("LIFE");
				String selectionres=driver.findElement(GBRdropdown).getText();
				System.out.println("after selecting life value is " + selectionres);
					
		return selectionres.contains(expselection);
	}
	public boolean TC_388res1() throws InterruptedException
	{
		//gbringestion1(GBR_test_sheet);
		boolean flag=false;
		String PATH=System.getProperty("user.dir");
		String FILE_NAME="\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\";
		
		List<String> Filename = new ArrayList<String>();
		File[] files = new File(PATH + FILE_NAME).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 
		for (File file : files)
		{
		    if (file.isFile())
		    {
		    			Filename.add(file.getName());
		    }
		}
		
		for(int i = 0 ; i <=Filename.size()-1; i++ )
		{
		File fil = new File(PATH + FILE_NAME + Filename.get(i));
		System.out.println("filename is ==> " + fil.getName());
		
		flag=gbringestion1(fil.getPath());
		}
		
		return flag;
		
	}
	public boolean TC_388res2() throws InterruptedException
	{
		String path=System.getProperty("user.dir");
	
		driver.findElement(gbruploadicon).click();
		 wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		try {
		driver.findElement(Choosefilebtn).sendKeys(path + GBR_test_sheet2 +path + GBR_test_sheet3 );
		}
		catch(InvalidArgumentException e) 
		{
		  	System.out.println("Test case got passes as it throws an exception while sending 2 files.");
		}
		
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
//		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
//		Thread.sleep(1000);
//		driver.findElement(yesbtn).click();
//		Thread.sleep(1000);
		driver.findElement(Nofilechosenerror);
		
		return driver.findElement(Nofilechosenerror).isDisplayed();
		
	}
	public boolean TC_388res3() throws ClassNotFoundException, SQLException, InvalidFormatException, InterruptedException
	{
		gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\GBR data demo 10 JUNE (1) (1).xlsx");
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\GBR data demo 10 JUNE (1) (1).xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheet("Sheet1");
		String expinward=TestUtil.sheet1.getRow(1).getCell(0).toString();
		String accurateinward=expinward.split("\\.")[0];
		
		String dbURL = prop.getProperty("dburl");
		String username =  prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");
		System.out.println("dburl is : " +dbURL);
		System.out.println("driver Loaded");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs = null;
		boolean flag=false;
		try {
		String selectquery ="SELECT * FROM GBR_Master where Rule_Reference='"+accurateinward+"';";
		//System.out.println("selectquery " + selectquery);
		 rs = st.executeQuery(selectquery);
		 rs.next();
		 int actualinward=rs.getInt("Inward_No");
		}
		catch(Exception e)
		{
			flag=true;
			System.out.println("Null pointer exception raised and handled, hence tc got passed");
		}
	return flag;
	}
	
	
	public boolean TC_389res1() throws InterruptedException
	{
		driver.findElement(gbruploadicon).click();
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Nofilechosenerror));
		boolean flag = driver.findElement(Nofilechosenerror).isDisplayed();
		String path=System.getProperty("user.dir");
		
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +GBR_test_sheet2);
		
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		boolean flag1 = driver.findElement(yesbtn).isDisplayed();
		
		return flag && flag1;
	}
	public boolean TC_389res2() throws InterruptedException
	{
		//Thread.sleep(6000);
		driver.findElement(gbruploadicon).click();
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick));
		driver.findElement(UploadGBRclick).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		Thread.sleep(2000);
	
		String path=System.getProperty("user.dir");
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR_GI_Duplicate_inward_overwrite.xlsx");
		
		driver.findElement(uploadbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		driver.findElement(yesbtn).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String successmsg=driver.findElement(errortitle).getText();
		return !successmsg.contains("Success");
		
	}
	//Value Error In GBR Excel..
	public boolean TC_389res3() throws InterruptedException
	{
		
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +GBR_test_sheet2);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String successmsg=driver.findElement(errortitle).getText();
	
		return successmsg.contains("Success");
			
	}
	
	public boolean TC_389res4n6() throws InterruptedException, InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String successmsg=TestUtil.sheet.getRow(1).getCell(1).getStringCellValue();
		
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		Select se=new Select(driver.findElement(GBRdropdown));
		se.selectByIndex(1);
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR Life -  Dataset 1.xlsx");
		Thread.sleep(1000);
		String exptext=driver.findElement(Choosefilebtn).getAttribute("value");
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String successmsgui=driver.findElement(Errorbox).getText();
		String[] msg=successmsgui.split("Success");		
		System.out.println("success message is  " + msg[1]);	
		Thread.sleep(1000);
		String actualtext=driver.findElement(Choosefilebtn).getText();	
		//System.out.println("actual text from choose btn is " + actualtext + "earlier the text was : " + exptext);
		return !exptext.equals(actualtext) && successmsg.equals(msg[1])  ;
	}
	
	public boolean TC_389res8part1() throws InterruptedException, InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String popupmsg=TestUtil.sheet.getRow(2).getCell(1).getStringCellValue();
		
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +GBR_test_sheet2);
		driver.findElement(uploadbtn).click();
		Thread.sleep(1000);
		String errormsg=driver.findElement(Areyousurepopupmsg).getText();
		//System.out.println("error msg is " + errormsg );
		WebElement yesbtn=driver.findElement(Areyousurepopupyesbtn);
		
		return popupmsg.equals(errormsg) && yesbtn.isDisplayed();
		
	}
	public boolean TC_389res8part2() throws InvalidFormatException, InterruptedException
	{
		
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR_GI_Duplicate_inward_overwrite.xlsx");
		driver.findElement(uploadbtn).click();
		Thread.sleep(1000);
		driver.findElement(Areyousurepopupyesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errortable));
		
		return driver.findElement(Errortable).isDisplayed();
		
	}
	
	public boolean TC_389res9()
	{
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +GBR_test_sheet2);
		driver.findElement(uploadbtn).click();
		driver.findElement(Areyousurepopupcancelbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		boolean flag=driver.findElement(GBRtitle).isDisplayed();
		
		return flag;
		
	}
	public boolean  TC_389res10() throws InterruptedException
	{
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		Select se=new Select(driver.findElement(GBRdropdown));
		se.selectByIndex(1);
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR Life -  Dataset 1.xlsx");
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String successmsg=driver.findElement(Errorbox).getText();
		return successmsg.contains("Success");
	}
	
	public boolean TC_390res1() throws InterruptedException, InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String errormsgexp=TestUtil.sheet.getRow(3).getCell(1).getStringCellValue();
		
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR_GI_Duplicate_inward_overwrite.xlsx");
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String Errormsgact=driver.findElement(Errorbox).getText();
		String[] actmsg=Errormsgact.split("Information");
		System.out.println("actual message is  : " + actmsg[1]);
		
		return errormsgexp.equals(actmsg[1]);
	}
	
	public boolean TC_390res2() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String errormsgexp=TestUtil.sheet.getRow(4).getCell(1).getStringCellValue();
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR_GI_Duplicate_inward_overwrite.xlsx");
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		String acterrormsg=driver.findElement(Griderrormsg).getText();
		
		return errormsgexp.equals(acterrormsg);
		
	}
	public boolean TC_390res3() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String errormsgexp=TestUtil.sheet.getRow(5).getCell(1).getStringCellValue();
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		Select se=new Select(driver.findElement(GBRdropdown));
		se.selectByIndex(1);
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR Life -  Dataset 1.xlsx");
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		String acterrormsg=driver.findElement(Griderrormsg).getText();
		return errormsgexp.equals(acterrormsg);
	}
	public boolean TC_393res1() throws InterruptedException
	{
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		 wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		Select se=new Select(driver.findElement(GBRdropdown));
		se.selectByIndex(1);
		String drpdwntextbefore=driver.findElement(GBRdropdown).getAttribute("value");
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR Life -  Dataset 1.xlsx");
		String expchoosentext=driver.findElement(Choosefilebtn).getAttribute("value");
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(Clearbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		String drpdwntextafter=driver.findElement(GBRdropdown).getAttribute("value");
		String filechoosentextactual=driver.findElement(Choosefilebtn).getAttribute("value");
		boolean flag=driver.findElement(Overwritecheckbox).isSelected();
		
		return expchoosentext.equals(filechoosentextactual) && drpdwntextbefore.equals(drpdwntextafter) && flag;
	
	}
	public void TC_393res2() throws InterruptedException
	{
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		Select se=new Select(driver.findElement(GBRdropdown));
		se.selectByIndex(1);

		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\GBR dupplicateinwardno.xlsx");
	
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(Clearbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		return;
	}
	public boolean TC_394() throws InterruptedException, InvalidFormatException
	{
		
		String path1=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path1+"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBRLIFE_Dataset1_ddipsha.xlsx");
		//driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String message=driver.findElement(errortitle).getText();
		System.out.println("message is " + message);
		Select se = new Select(driver.findElement(Entriestxtbx));
		se.selectByIndex(3);
		
		driver.findElement(errorreportdownloadbtn).click();
		Thread.sleep(1000);
		
		String data=driver.findElement(By.xpath("//*[@id=\"PospListDataTable_info\"]")).getText();
		Thread.sleep(1000);
		String count[]=data.split("of ");
		String datacount[]=count[1].split(" entries");
		String number=datacount[0];
		int num=Integer.parseInt(number);
	//	System.out.println("number is : " + number);
		
		int Rowno;String valueerror;String discription;
		boolean flag=false;
		for(int i =0; i<num;i++)
		{
			Thread.sleep(3000);
			WebElement rownoui=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[1]"));
			WebElement columnerror=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[2]"));
			WebElement errordisc=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[3]"));
		//	 System.out.println("data from ui:" + "rowno. : " +rownoui.getText()  + "column error : " + columnerror.getText()+" discription : " + errordisc.getText());
			  Rowno=Integer.parseInt(rownoui.getText());
			 valueerror=columnerror.getText();
			 discription=errordisc.getText();
			 
			 String modifiedexcelpath=TestUtil.downloadexcel();
				TestUtil.getTestData1(modifiedexcelpath);
				TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
				int rowno=(int) TestUtil.sheet1.getRow(i+2).getCell(0).getNumericCellValue();
				String errorcolumn=TestUtil.sheet1.getRow(i+2).getCell(1).toString();
				String disccolumn=TestUtil.sheet1.getRow(i+2).getCell(2).toString();
				// System.out.println("Data from downloaded excel:"+ "rowno. : " +rowno  + "column error : " + errorcolumn+" discription : " + disccolumn);
			 
			 flag=rownoui.equals(rowno) && 	columnerror.equals(errorcolumn)  && errordisc.equals(disccolumn) ; 
		}
		
		return flag;
	}
	
	
	public void TC_389res11() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
		gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR data demo 10 JUNE (1) (1).xlsx");
		
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR data demo 10 JUNE (1) (1).xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
	
		Map<String, Integer> map = new HashMap<String,Integer>(); //Create map
		Row row = TestUtil.sheet1.getRow(0); //Get first row
		
		//following is boilerplate from the java doc
		short minColIx = row.getFirstCellNum(); //get the first column index for a row
		short maxColIx = row.getLastCellNum(); //get the last column index for a row
		for(short colIx=minColIx; colIx<maxColIx; colIx++) { //loop from first to last index
		Cell cell = row.getCell(colIx); //get the cell
		map.put(cell.getStringCellValue(),cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
		}
		boolean flag=true;
		for(int i=0 ; i < TestUtil.sheet1.getLastRowNum()-1 ; i++) {
			Row row1=TestUtil.sheet1.getRow(i+1);
			int idxForColumn1= map.get("Policy_Tenure");
			int idxForColumn2= map.get("Inward_No");
			Cell Rule_refcell1=row1.getCell(idxForColumn1);
			Cell inwardcell=row1.getCell(idxForColumn2);
			Rule_refcell1.setCellType(CellType.STRING);	
			inwardcell.setCellType(CellType.STRING);
			String policy_tenure=Rule_refcell1.getStringCellValue();
			
			String inward=inwardcell.getStringCellValue();
			System.out.println("pritnting tenure value" + policy_tenure + "inwardno is : " + inward);
			
			carreport.downloadcar();
			String path=TestUtil.downloadexcel();
			TestUtil.getTestData1(path);
			TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
			Row rowsheet2=TestUtil.sheet1.getRow(i+1);
			int idxForColumnsheet2= map.get("Policy_Tenure");
//			Cell Rule_refcell1=row1.getCell(idxForColumn1);
//			
//			 for (int d = 0; d < myObjList2.size(); d++) {
//					// System.out.println("siz2 :" +myObjList2.size());
//					  String inwardnosheet2=myObjList2.get(d).inwardno.toString();
//						for(int l=0; l <myObjList1.size() ;l++ ) {
//							//	System.out.println("size1 :" +myObjList1.size());
//								 String inwardnosheet1=myObjList1.get(l).inwardno.toString();
//								 
//								 if (inwardnosheet2.equals(inwardnosheet1) {
//									 System.out.println("Data matched");
//									  break;
//								 }
//			String dbURL = prop.getProperty("dburl");
//			String username =prop.getProperty("DBusename");
//			String Password =  prop.getProperty("DBPassword");
//
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con = DriverManager.getConnection(dbURL, username, Password);
//			
//			Statement st = con.createStatement();
//			String  gbrquery="select * from db_ReconDev.GBR_Master where Inward_no='"+inward+"' and GBR_Is_Deleted=0;";
//			ResultSet rs = st.executeQuery(gbrquery);
//			rs.next();
//			String Tenure=rs.getString("Policy_Tenure");
//			flag=policy_tenure.equals(Tenure);
	//	}
			// }
		
//	return flag;
	
			 }
		}
	
	public boolean tc_396_402() throws InterruptedException, ClassNotFoundException, SQLException
	{
		boolean flag=gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR_ALL_positivevalueentries.xlsx");
		if(flag==true) {
		TestUtil.Truncategbrtable();
		}
		return flag;
	}
	public boolean tc_403() throws InterruptedException, ClassNotFoundException, SQLException
	{
		boolean flag=gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBRLife_positiveentries.xlsx");
		if(flag==true) {
		TestUtil.Truncategbrtable();
		}
		return flag;
	}
	public boolean tc_407(String GBR_life_path) throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException
	{
		boolean flag=gbringestion1(GBR_life_path);
		boolean flag1=false;
		if(flag==true) {
		TestUtil.getTestData1(GBR_life_path);
		
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		Map<String, Integer> map = new HashMap<String,Integer>(); //Create map
		Row row = TestUtil.sheet1.getRow(0); //Get first row
		
		//following is boilerplate from the java doc
		short minColIx = row.getFirstCellNum(); //get the first column index for a row
		short maxColIx = row.getLastCellNum(); //get the last column index for a row
		for(short colIx=minColIx; colIx<maxColIx; colIx++) { //loop from first to last index
		Cell cell = row.getCell(colIx); //get the cell
		map.put(cell.getStringCellValue(),cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
		}
		String inward = null;
		List<String> inwardnolist=new ArrayList<String>();
		List<String> inwardnolistdb=new ArrayList<String>();
		
		for(int i=0 ; i < TestUtil.sheet1.getLastRowNum() ; i++) {
			Row row1=TestUtil.sheet1.getRow(i+1);
			int idxForColumn2= map.get("inwardno");
			Cell inwardcell=row1.getCell(idxForColumn2);
			inwardcell.setCellType(CellType.STRING);
			 inward=inwardcell.getStringCellValue();
		if(inward.equals(""))
		{
			int k=i;
			k++;
			inwardnolist.remove(inward);
		}else {
			i=i;
			inwardnolist.add(inward);
		}
	//	System.out.println("from excel inward numbers are:" + inwardnolist);
		}
				String dbURL = prop.getProperty("dburl");
				String username =prop.getProperty("DBusename");
				String Password =  prop.getProperty("DBPassword");
			
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(dbURL, username, Password);
				Statement st = con.createStatement();
				String  gbrquery="SELECT * FROM db_ReconDev.GBR_Master;";
				ResultSet rs1 = st.executeQuery(gbrquery);
				while(rs1.next()) {
					String inwarddb=rs1.getString("Inward_No");
					inwardnolistdb.add(inwarddb);
					//System.out.println("list is " + inwardnolistdb);
				}
			
				List<String> union = new ArrayList<String>(inwardnolist);
				union.addAll(inwardnolistdb);
				List<String> union1 = new ArrayList<String>(inwardnolist);
				union1.retainAll(inwardnolistdb);
				union.removeAll(union1);
				System.out.println("Data is missing in database of inward no ==> " + union);
				 flag1=union.isEmpty();
				 System.out.println("flag1" + flag1);
			con.close();	
			
		TestUtil.Truncategbrtable();
		}
		else {
			System.out.println("GBR didn't get ingest.");
		}
		return flag1 ;
	}
	
	public boolean TC_391res1(String gbr_path) throws InterruptedException, InvalidFormatException {
		
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		
		if(gbr_path.contains("Life")) {
			System.out.println("GBR life found.");
		Select se=new Select(driver.findElement(GBRdropdown));
		se.selectByIndex(1);
		}
		else {
			System.out.println("GBR life not found.");
		}
				
		driver.findElement(Choosefilebtn).sendKeys(path+gbr_path);
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String errormsg=driver.findElement(Errorbox).getText();
		String[] actmsg=errormsg.split("Information");
		System.out.println("actual message is  : " + actmsg[1]);
		
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String errormsgexp=TestUtil.sheet.getRow(3).getCell(1).getStringCellValue();
		return actmsg[1].equals(errormsgexp);
		
	}
	
		public boolean tc_391res2_3(String gbr_path) throws InterruptedException, InvalidFormatException
		{	
			boolean flag=TC_391res1(gbr_path);
			TestUtil.getTestData();
			TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
			int rownoexp;
			String errcolumnnameexp=null , errordiscexp=null, gbrdataexp=null;
			
			if(gbr_path.contains("Life")) {
				 rownoexp=(int) TestUtil.sheet.getRow(1).getCell(3).getNumericCellValue();
				 errcolumnnameexp=TestUtil.sheet.getRow(2).getCell(3).getStringCellValue();
				 errordiscexp=TestUtil.sheet.getRow(3).getCell(3).getStringCellValue();
				 gbrdataexp=TestUtil.sheet.getRow(4).getCell(3).getStringCellValue();
			}else {
				 rownoexp=(int) TestUtil.sheet.getRow(1).getCell(2).getNumericCellValue();
				 errcolumnnameexp=TestUtil.sheet.getRow(2).getCell(2).getStringCellValue();
				 errordiscexp=TestUtil.sheet.getRow(3).getCell(2).getStringCellValue();
				 gbrdataexp=TestUtil.sheet.getRow(4).getCell(2).getStringCellValue();
			}
		//	System.out.println("errorcolumnname is " + errcolumnnameexp  +"errordiscexp" + errordiscexp);
			int Rowno = 0;
			String valueerror = null,discription = null;
			String gbrdataactual = null;
			if(flag==false) {
			String data=driver.findElement(By.xpath("//*[@id=\"PospListDataTable_info\"]")).getText();
			Thread.sleep(1000);
			String count[]=data.split("of ");
			String datacount[]=count[1].split(" entries");
			String number=datacount[0];
			int num=Integer.parseInt(number);
			//System.out.println("number is : " + number);
			WebElement gbrdata=driver.findElement(By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr"));
			gbrdataactual=gbrdata.getText();
			// System.out.println("gbr data" + gbrdataactual );
			
			for(int i =0; i<num;i++)
			{
				Thread.sleep(3000);
				WebElement rownoui=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[1]"));
				WebElement columnerror=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[2]"));
				WebElement errordisc=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[3]"));
			//	 System.out.println("data from ui:" + "rowno. : " +rownoui.getText()  + "column error : " + columnerror.getText()+" discription : " + errordisc.getText());
				Rowno=Integer.parseInt(rownoui.getText());
				 valueerror=columnerror.getText();
				 discription=errordisc.getText();
				// System.out.println("rowno is " + Rowno + " " + valueerror + " " + discription);
				 
			}
		System.out.println("expected result is : " + (Rowno==rownoexp) + errcolumnnameexp.equals(valueerror.trim()) + errordiscexp.equals(discription) + gbrdataactual.contains(gbrdataexp));
			
		}
		return Rowno==rownoexp && errcolumnnameexp.equals(valueerror.trim()) && errordiscexp.equals(discription) && gbrdataactual.contains(gbrdataexp);
		}
		
		public boolean tc_392res1_2_3_5(String path,String path1) throws ClassNotFoundException, SQLException, InterruptedException, InvalidFormatException
		{
			TestUtil.Truncategbrtable();
			boolean flag=gbringestion1(path);
			if(flag==true) {
				gbringestion1(path1);
			}
			else {
				System.out.println("First gbr doesn't get ingest.");
			}
			TestUtil.getTestData1(path1);
			TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
			String inward=TestUtil.sheet1.getRow(4).getCell(2).getStringCellValue();
			String statenameexp=null;
			if(path1.contains("Life")) {
			 statenameexp=TestUtil.sheet1.getRow(4).getCell(5).getStringCellValue();
			}else {
			 statenameexp=TestUtil.sheet1.getRow(4).getCell(6).getStringCellValue();
			}
			String dbURL = prop.getProperty("dburl");
			String username =prop.getProperty("DBusename");
			String Password =  prop.getProperty("DBPassword");

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbURL, username, Password);
			
			Statement st = con.createStatement();
			String  gbrquery="select * from GBR_Master where Inward_no='"+inward+"' and GBR_Is_Deleted=0;";
			ResultSet rs = st.executeQuery(gbrquery);
			rs.next();
			String statename_actual=rs.getString("State_Name");
			String Inward_No_actual=rs.getString("Inward_No");
			//System.out.println("expected data : " +statenameexp + "actual data from db" + statename_actual);
			return statenameexp.equals(statename_actual) && Inward_No_actual.equals(inward);
			
		}
		public boolean tc_392res4(String path,String path1) throws ClassNotFoundException, SQLException, InterruptedException, InvalidFormatException
		{
			TestUtil.Truncategbrtable();
			boolean flag=gbringestion1(path);
			String value_error_actual=null;
			if(flag==true) {
				boolean flag1=gbringestion1(path1);
				if(flag1==false) {
						WebElement errordisc=driver.findElement(By.xpath("//tbody/tr/td[3]"));
						value_error_actual=errordisc.getText();
				}
			}
			else {
				System.out.println("First gbr doesn't get ingest.");
			}
			TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
			TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
			String valueerror_msg=TestUtil.sheet1.getRow(6).getCell(1).getStringCellValue();
			
			String statenameexp=null;String statename_actual=null;
			if(value_error_actual.equals(valueerror_msg)) {
			
			TestUtil.getTestData1(path1);
			TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
			String inward=TestUtil.sheet1.getRow(4).getCell(2).getStringCellValue();
			
			if(path1.contains("Life")) {
			 statenameexp=TestUtil.sheet1.getRow(4).getCell(5).getStringCellValue();
			}else {
			 statenameexp=TestUtil.sheet1.getRow(4).getCell(6).getStringCellValue();
			}
			String dbURL = prop.getProperty("dburl");
			String username =prop.getProperty("DBusename");
			String Password =  prop.getProperty("DBPassword");

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbURL, username, Password);
			Statement st = con.createStatement();
			String  gbrquery="select * from GBR_Master where Inward_no='"+inward+"' and GBR_Is_Deleted=0;";
			ResultSet rs = st.executeQuery(gbrquery);
			rs.next();
			statename_actual=rs.getString("State_Name");
			String Inward_No_actual=rs.getString("Inward_No");
			}else {
				System.out.println("value_error doesn't match.");
			}
	//		System.out.println("expected data : " +statenameexp + "actual data from db" + statename_actual);
			return !statenameexp.equals(statename_actual);
		}
		
	public boolean tc_392res6(String path,String path1) throws InvalidFormatException, InterruptedException, ClassNotFoundException, SQLException
		{
		TestUtil.Truncategbrtable();
		boolean flag=gbringestion1(path);
		String value_error_actual=null;
		if(flag==true) {
			boolean flag1=gbringestion1(path1);
			if(flag1==false) {
					WebElement errordisc=driver.findElement(By.xpath("//tbody/tr/td[3]"));
					value_error_actual=errordisc.getText();
			}
		}
		else {
			System.out.println("First gbr doesn't get ingest.");
		}
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String valueerror_msg=TestUtil.sheet1.getRow(4).getCell(1).getStringCellValue();
		
		String statenameexp=null;String statename_actual=null;
	
		if(value_error_actual.equals(valueerror_msg)) {
		TestUtil.getTestData1(path1);
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String inward=TestUtil.sheet1.getRow(4).getCell(2).getStringCellValue();
		
		if(path1.contains("Life")) {
		 statenameexp=TestUtil.sheet1.getRow(4).getCell(5).getStringCellValue();
		}else {
		 statenameexp=TestUtil.sheet1.getRow(4).getCell(6).getStringCellValue();
		}
		String dbURL = prop.getProperty("dburl");
		String username =prop.getProperty("DBusename");
		String Password =  prop.getProperty("DBPassword");

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		String  gbrquery="select * from GBR_Master where Inward_no='"+inward+"' and GBR_Is_Deleted=0;";
		ResultSet rs = st.executeQuery(gbrquery);
		rs.next();
		statename_actual=rs.getString("State_Name");
		String Inward_No_actual=rs.getString("Inward_No");
		}else {
			System.out.println("value_error doesn't match.");
		}
		System.out.println("expected data : " +statenameexp + "actual data from db" + statename_actual);
		return !statenameexp.equals(statename_actual);
		}
	
	public boolean TC_406res2(String gbr_path) throws InvalidFormatException, InterruptedException
	{
		driver.findElement(gbruploadicon).click();
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		
		if(gbr_path.contains("Life")) {
			System.out.println("GBR life found.");
		Select se=new Select(driver.findElement(GBRdropdown));
		se.selectByIndex(1);
		}
		else {
			System.out.println("GBR_GI found.");
		}
		
		driver.findElement(Choosefilebtn).sendKeys(gbr_path);
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String successmsg=driver.findElement(Errorbox).getText();
		System.out.println("Message after uploading file is: " + successmsg);
		
		
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String valueerror_msg=TestUtil.sheet1.getRow(8).getCell(1).getStringCellValue();
		
		return successmsg.equals(valueerror_msg);
	}
	public boolean TC_406res3(String gbr_path) throws InvalidFormatException, InterruptedException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncategbrtable();
		boolean flag=gbringestion1(gbr_path);
		String actual_value_error=null;
		if(flag==false) {
			Thread.sleep(3000);
			WebElement errordisc=driver.findElement(By.xpath("//tbody/tr/td[3]"));
			actual_value_error=errordisc.getText();
		}else {
			System.out.println("GBR ingested successfully which was not expected.");
		}
		
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String valueerror_msg=TestUtil.sheet1.getRow(7).getCell(1).getStringCellValue();
		
		return actual_value_error.equals(valueerror_msg);
	}
				
}
 