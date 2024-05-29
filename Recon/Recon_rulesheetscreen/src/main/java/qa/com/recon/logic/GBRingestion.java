package qa.com.recon.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import qa.com.recon.utility.*;
import qa.com.recon.base.TestBase;

public class GBRingestion extends TestBase{
	
	By gbruploadicon=By.xpath("//i[@class='fa fa-upload']");
	By UploadGBRclick=By.xpath("//a[@href='/GBRIngestions']");
	By GBRtitle=By.xpath("//strong[contains(text(),'GBR Ingestion')]");
	By UploadGBRtext=By.xpath("//b[contains(text(),'Upload GBR')]");
	By GBRdropdown=By.id("GbrType");
	By Choosefilebtn=By.id("Excelfilename");
	By uploadbtn=By.id("btnUpload");
	By Clearbtnlinktetxt=By.linkText("Clear");
	By Clearbtn=By.xpath("//*[@id=\"frmGBR\"]/div/section/div/div/div/div[2]/div[2]/div[5]/button[2]");
	By Overwritecheckbox=By.xpath("//label[@for='OverwriteYN']");
	By yesbtn=By.xpath("//button[contains(text(),'Yes')]");
	By Errorbox=By.xpath("/html/body/div[2]/div");
	By errortitle=By.xpath("/html/body/div[2]/div/h2");
	By Overwritecheckboxyn=By.id("OverwriteYN");
	By Nofilechosenerror=By.xpath("//*[@id=\"lblerror\"]/li");
	By Areyousurepopupmsg=By.xpath("/html/body/div[4]/p");
	By Areyousurepopupyesbtn=By.xpath("//button[contains(text(),'Yes')]");
	By Areyousurepopupcancelbtn=By.xpath("//button[contains(text(),'Cancel')]");
	By Errortable=By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr");
	By Areyopusurepopup=By.xpath("/html/body/div[3]");
	By Griderrormsg=By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr/td[3]");
	
	public WebDriverWait wait;
	
	String GBR_test_sheet="\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\GBR data csvfile.csv";
	String GBR_test_sheet2="\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\GBR data demo 10 JUNE (1) (1).xlsx";
	String GBR_test_sheet3="\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\GBR data xls.xls";
	
	public  boolean gbringestion1(String GBR_test_sheet) throws InterruptedException
	{
		
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
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
		return successmsg.contains("success");
		
	}
	
	public boolean Tc_381() {
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
	public boolean Tc_382() throws InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		//System.out.println("row number is " + TestUtil.sheet.getLastRowNum());
		String exptitle=TestUtil.sheet.getRow(1).getCell(0).getStringCellValue();
		//System.out.println("exp title" + exptitle);
//		driver.findElement(gbruploadicon).click();
//		wait=new WebDriverWait(driver,30);
//		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
//		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		String actualtitle=driver.findElement(GBRtitle).getText();
		//System.out.println();
		return exptitle.equals(actualtitle);
		
	}

	public boolean Tc_383res1() throws InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String expoption1=TestUtil.sheet.getRow(3).getCell(0).getStringCellValue();
		String expoption2=TestUtil.sheet.getRow(4).getCell(0).getStringCellValue();
		List<String> expoptions=new ArrayList<String>();
		expoptions.add(expoption1);
		expoptions.add(expoption2);
		//res1
		Select drpdwn=new Select(driver.findElement(GBRdropdown));
		List<WebElement> dropdown=drpdwn.getOptions();
		String options=null;
		boolean flag=false;
		for(WebElement d:dropdown) {
			options=d.getText();
			System.out.println("gbr type options are : " + options);
			flag=options.equals(expoptions); 
		}
	
		return flag ;
	}
	public boolean Tc_383res2() throws InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String expselection=TestUtil.sheet.getRow(3).getCell(0).getStringCellValue();
		//res2
				String defaultselection=driver.findElement(GBRdropdown).getAttribute("value");
				System.out.println("default selection is " + defaultselection);
				
		return defaultselection.equals(expselection);
	}
	public boolean Tc_383res3() throws InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String expselection=TestUtil.sheet.getRow(4).getCell(0).getStringCellValue();
		//res3
		Select drpdwn=new Select(driver.findElement(GBRdropdown));
				drpdwn.selectByValue("LIFE");
				String selectionres=driver.findElement(GBRdropdown).getAttribute("value");
				System.out.println("after selecting life value is " + selectionres);
					
		return selectionres.equals(expselection);
	}
	public void TC_384res1() throws InterruptedException
	{
		//gbringestion1(GBR_test_sheet);
		
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
		
		gbringestion1(fil.getPath());
		}
		
		
	}
	public boolean TC_384res2() throws InterruptedException
	{
		String path=System.getProperty("user.dir");
	
		driver.findElement(gbruploadicon).click();
		 wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		try {
		driver.findElement(Choosefilebtn).sendKeys(path + GBR_test_sheet +path + GBR_test_sheet2 );
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
	public void TC_384res3()
	{
		//dbconnection
		
		
		
	//	SELECT * FROM db_Recon.GBR_Master  where Inward_No='222' ;
		
	}
	
	
	public boolean TC_387res1() throws InterruptedException
	{
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Nofilechosenerror));
		boolean flag = driver.findElement(Nofilechosenerror).isDisplayed();
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +GBR_test_sheet);
		driver.findElement(Overwritecheckbox).click();
		Thread.sleep(1000);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		boolean flag1 = driver.findElement(yesbtn).isDisplayed();
		
		return flag && flag1;
	}
	public boolean TC_387res2() throws InterruptedException
	{
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +GBR_test_sheet);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String successmsg=driver.findElement(errortitle).getText();
		return successmsg.contains("information");
		
	}
	//Value Error In GBR Excel..
	public boolean TC_387res3() throws InterruptedException
	{
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +GBR_test_sheet);
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(Errorbox));
		String successmsg=driver.findElement(errortitle).getText();
		return successmsg.contains("information");
	}
	
	public boolean TC_387res4n6() throws InterruptedException, InvalidFormatException
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
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR Life -  Dataset 1_16June.xlsx");
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
	
	public boolean TC_387res8part1() throws InterruptedException, InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String popupmsg=TestUtil.sheet.getRow(2).getCell(1).getStringCellValue();
		
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +GBR_test_sheet);
		driver.findElement(uploadbtn).click();
		String errormsg=driver.findElement(Areyousurepopupmsg).getText();
		WebElement yesbtn=driver.findElement(Areyousurepopupyesbtn);
		
		return popupmsg.equals(errormsg) && yesbtn.isDisplayed();
		
	}
	public boolean TC_387res8part2() throws InvalidFormatException
	{
		
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +GBR_test_sheet);
		driver.findElement(uploadbtn).click();
		driver.findElement(Areyousurepopupyesbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Errortable));
		
		return driver.findElement(Errortable).isDisplayed();
		
	}
	
	public boolean TC_387res9()
	{
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +GBR_test_sheet);
		driver.findElement(uploadbtn).click();
		driver.findElement(Areyousurepopupcancelbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		boolean flag=driver.findElement(GBRtitle).isDisplayed();
		
		return flag;
		
	}
	public boolean  TC_387res10() throws InterruptedException
	{
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		Select se=new Select(driver.findElement(GBRdropdown));
		se.selectByIndex(1);
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR Life -  Dataset 1_16June.xlsx");
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
	
	public boolean TC_388res1() throws InterruptedException, InvalidFormatException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String errormsgexp=TestUtil.sheet.getRow(3).getCell(1).getStringCellValue();
		
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\GBR dupplicateinwardno.xlsx");
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
	
	public boolean TC_388res2() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData();
		TestUtil.sheet = TestUtil.book.getSheet("GBR ingestion");
		String errormsgexp=TestUtil.sheet.getRow(4).getCell(1).getStringCellValue();
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\docs\\gbr doc\\GBR dupplicateinwardno.xlsx");
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
	public boolean TC_388res3() throws InvalidFormatException, InterruptedException
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
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR Life -  Dataset 1_16June.xlsx");
		driver.findElement(uploadbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(yesbtn));
		Thread.sleep(1000);
		driver.findElement(yesbtn).click();
		Thread.sleep(1000);
		String acterrormsg=driver.findElement(Griderrormsg).getText();
		
		return errormsgexp.equals(acterrormsg);
	}
	public boolean TC_391res1() throws InterruptedException
	{
		String path=System.getProperty("user.dir");
		driver.findElement(gbruploadicon).click();
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(UploadGBRclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(GBRtitle));
		Select se=new Select(driver.findElement(GBRdropdown));
		se.selectByIndex(1);
		String drpdwntextbefore=driver.findElement(GBRdropdown).getAttribute("value");
		driver.findElement(Choosefilebtn).sendKeys(path +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR Life -  Dataset 1_16June.xlsx");
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
	public void TC_391res2() throws InterruptedException
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
	
	
	
	
}
 