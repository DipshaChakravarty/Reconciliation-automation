package qa.com.recon.logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.com.recon.base.TestBase;
import qa.com.recon.utility.TestUtil;

public class ViewnEditRulesheet extends TestBase{

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
	By yesbtn=By.xpath("//button[contains(text(),'Yes')]");
	By Viewrrulesheetclick=By.xpath("/html/body/div[1]/aside/section/ul/li[1]/ul/li[2]/a");
	By savebtneditscreen=By.xpath("//button[@id='btnedit']");
	By errorboxeditscreen=By.xpath("/html/body/div[2]/div");
	
	By approverulesheetclick=By.xpath("//a[@href='/ViewRules']");
	By Yesbtneditscreen=By.xpath("//button[contains(text(),'Yes')]");
	By sweetalert=By.xpath("//body/div[4]");
	By Viewexistingrulebutton=By.xpath("//div[3]/button[1]");
	By Viewexistingrulescreentitle=By.xpath("//strong[contains(text(),'View Existing Rules')]");
	By ViewrulescreenDatetxtbx=By.xpath("//select[@id='dateSelection']");
	By Viewrulesheetclick=By.xpath("//a[@href='/ViewExistingRules']");
	By Fromdatetxtbx=By.xpath("//*[@id=\"FromDate\"]");
	By Todatetxtbx=By.id("ToDate");
	
	By calendarmonth=By.xpath("//select[@class='ui-datepicker-month']");
	By calendaryear=By.xpath("//select[@class='ui-datepicker-year']");
	By Inscompanysmartsearchtxtbx=By.xpath("//span/input[@class='select2-search__field' and @type='search']");
	By RTOapprovaltxtbx=By.xpath("//input[@placeholder=' Select RTO']");
	
	By RTOtextbxapproval=By.xpath("//span/ul[@class='select2-selection__rendered']");
	By clearbutton=By.xpath("//button[@class='btn btn-rounded btn-primary mr-1']");
	By searchtxtbx=By.xpath("//*[@id=\"ViewRules_filter\"]/label/input");
	By pageno1approve=By.xpath("//a[contains(text(),'1')]");
	By pageno2approve=By.xpath("//a[contains(text(),'2')]");
	By previouspageapprove=By.xpath("//*[@id=\"ViewRules_previous\"]");
	By nextpageapprove=By.xpath("//*[@id=\"ViewRules_next\"]");
	
	By Statustxtbx=By.id("status");
	WebDriverWait wait=new WebDriverWait(driver,30);
	
	public void rulesheetingestion(String pathofcsv) throws InterruptedException, ClassNotFoundException, SQLException {
		 	Thread.sleep(8000);
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
			driver.findElement(pushmenuicon).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
			driver.findElement(rulemasterclick).click();
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
		//	System.out.println("number is : " + number);
			
			List<String> rule_idlist=new ArrayList<String>();
			
			String dbURL = prop.getProperty("dburl");
			String username =  prop.getProperty("DBusename");
			String Password = prop.getProperty("DBPassword");
		//	System.out.println("dburl is : " +dbURL);
			//System.out.println("driver Loaded");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(dbURL, username, Password);
			Statement st = con.createStatement();
			ResultSet rs = null;
			
			for(int i =0; i<num;i++)
			{
			
				Thread.sleep(3000);
				WebElement rule_id=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[2]"));
				 //System.out.println( "string rule_id is " +rule_id.getText() );
				String rule=rule_id.getText();
				//int rule=Integer.parseInt(rule_id.getText());
				 String selectquery ="UPDATE Rule_Sheet_Master SET `Status` = '1',`Updated_on`= current_timestamp(), `Updated_by`='3' WHERE (`Rule_Reference` = '"+rule+"');";
				//System.out.println("query is : " + selectquery);
				PreparedStatement pst = con.prepareStatement(selectquery);
				pst.executeUpdate();
			
			}
				driver.navigate().refresh();

	}
	public int viewrulesheetclick() throws InterruptedException
	{
		Thread.sleep(8000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick));
		driver.findElement(rulemasterclick).click();
		
			driver.findElement(Viewrrulesheetclick).click();
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
			//System.out.println("number is : " + number);
		return num;
	}
	
	public boolean TC_36(int columnindex, String key,int num) throws IOException, InterruptedException
	{
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\rules_sheet2.csv"));
		String line;String[] cols = null ;
		String headerLine = br.readLine();
		
		List<String> expecteddata=new ArrayList<String>();
		
		while ((line = br.readLine()) != null) {
		     cols = line.split(",");
		     expecteddata.add(cols[columnindex]); 
		}
		// System.out.println( "Expected data is: " + expecteddata);
		 
	
				List<String> actualdatalist=new ArrayList<String>();
				List<String> actualdatalisttrimmed=new ArrayList<String>();
			
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
			System.out.println("actual company list data is:" + actualdatalisttrimmed);
		
		 
		 List<String> expdatalistremaining=new ArrayList<String>();
			List<String> actualdatalisttrimmedremaining=new ArrayList<String>();
		 
		 
			 for (int d = 0; d < expecteddata.size(); d++) {
					  String expdata=expecteddata.get(d).toString();
					  
						for(int l=0; l <actualdatalist.size() ;l++ ) {
								 String actuldata=actualdatalisttrimmed.get(l).toString();
								 
								 if (actuldata.equals(expdata)){
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
				
				System.out.println("Data didn't match for the values of "+key+" expected data is: " + union + " actual data diaplayed is:" + unionactual);
				 boolean flag=union.equals(unionactual);
				Thread.sleep(1000);
			 
			 return flag;
	}
	public boolean TC_39(int columnindex,int num) throws IOException, InterruptedException
	{
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\rules_sheet2.csv"));
		String line;String[] cols = null ;
		String headerLine = br.readLine();
		
		List<String> expecteddata=new ArrayList<String>();
		
		while ((line = br.readLine()) != null) {
		     cols = line.split(",");
		     expecteddata.add(cols[columnindex]); 
		}
		// System.out.println( "Expected data is: " + expecteddata);
		 
	
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
			//System.out.println("actual company list data is:" + actualdatalisttrimmed);
		
		 
		 List<String> expdatalistremaining=new ArrayList<String>();
			List<String> actualdatalisttrimmedremaining=new ArrayList<String>();
		 
			 for (int d = 0; d < expecteddata.size(); d++) {
					  String expdata=expecteddata.get(d).toString();
					  
						for(int l=0; l <actualdatalist.size() ;l++ ) {
								 String actuldata=actualdatalisttrimmed.get(l).toString();
								 
								 if (actuldata.equals(expdata)){
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
	
	//By companynametxtbxedit=By.id("companyname");
	//mainproductname //productname // subproductname // discountfrom //discountto //policytype //producttype //totalidvfrom
	//totalidvto  //fualtype  //manufacturingyearfrom  //manufacturingyearto  //insurancecompanybranchname  //insurancecompanycityname
	//insurancecompanystatename  //branch  //make	//model  //variantname  //seatingcapacityfrom  //Seating_capacity_to
	//ccfrom  //ccto  //ncbfrom //ncbto   //pospcode  //inwardno  //rto  //proposalagefrom  //proposalageto  //pptfrom  //pptto  //issuemonth
	//issuedatefrom  //isssuedateto  //name : OD_Start_Date_From //name : OD_Start_Date_To  //name:TP_Start_date_from //name:TP_Start_date_to
	//name:Policy_tenure //productcategory  //gvwfrom //GVW_to  //odfrom  //odto  //tpfrom //tpto //netfrom  //netto //imdcode
	//basis //odtotal //tptotal //fixedvaluetotal //basisw //odrate1commissionper //tprate1commissionper //fixedvaluebasis1 //basis2 
	//odrate2commissionper //tprate2commissionper //fixedvaluebasis2 //Comment //RTO_State_Code
		
	public boolean Tc_113() throws InterruptedException, SQLException, ClassNotFoundException, IOException
	{
		Thread.sleep(8000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick));
		driver.findElement(rulemasterclick).click();
		driver.findElement(Viewrrulesheetclick).click();
		Thread.sleep(1000);
	
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
			int rule_id = 0;
			String Rule_ref;
			Rule_ref=cols[0];
				
				 String selectquery ="SELECT * FROM Rule_Sheet_Master where Rule_Reference='"+Rule_ref+"';";
				 rs = st.executeQuery(selectquery);
				 rs.next();
				 rule_id=rs.getInt("id");
				
				 String trimmeddata = null;
				 WebElement expele = null;
				 String rowno = null;
				 for (int d = 0; d < expecteddata.size(); d++) {
						  String expdata=expecteddata.get(d).toString();
						  	
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
									 Thread.sleep(3000);
									 break;
							  	}
							  	else {
							  	}
						  }
				 }
				 
		 driver.findElement(By.xpath("//*[@id=\"ViewRules\"]/tbody/tr"+rowno+"/td[69]/div/a[@id='ToEdit_"+rule_id+"']")).click();
		 Thread.sleep(1000);
		 boolean flag=driver.findElement(By.xpath("//h4[contains(text(),'Edit Rule')]")).isDisplayed();
		 Thread.sleep(1000);
		 return flag;
		 
	}
	public String[] csvdataread() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\rules_sheet2.csv"));
		String line;String[] cols = null ;
		String headerLine = br.readLine();
		
		while ((line = br.readLine()) != null) {
	     cols = line.split(",");
	    
		}
		return cols;
	}
	
	public boolean Editscreendatamatch(int index,String key) throws IOException {
		String expdata[]=csvdataread();
		String expvalue=expdata[index];
		 WebElement txtbx=driver.findElement(By.id(""+key+""));
		 String actualvalue= txtbx.getAttribute("value");
		
		 System.out.println("data to be match: " + actualvalue +"exp data is "+ expvalue );
		 return actualvalue.equals(expvalue) ;
		 }
	public boolean Editscreendatamatchname(int index,String key) throws IOException {
		String expdata[]=csvdataread();
		String expvalue=expdata[index];
		 WebElement txtbx=driver.findElement(By.name(""+key+""));
		 String actualvalue= txtbx.getAttribute("value");
		
		 System.out.println("data to be match: " + actualvalue +"exp data is "+ expvalue );
		 return actualvalue.equals(expvalue) ;
		 }
	
	public boolean emptyfield(String key) throws InterruptedException, InvalidFormatException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String experrmsg=TestUtil.sheet1.getRow(10).getCell(1).toString();
		
		 WebElement txtbx=driver.findElement(By.id(""+key+""));
		 String actualvalue= txtbx.getAttribute("value");
		 txtbx.clear();
		 driver.findElement(savebtneditscreen).click();
		 Thread.sleep(1000);
		 String msg= driver.findElement(errorboxeditscreen).getText();
		 String acterrormsg=msg.split("Warning")[1];
		 txtbx.sendKeys(actualvalue);
		 Thread.sleep(2000);
		 return acterrormsg.equals(experrmsg);
		 
	}
	public boolean emptyfieldname(String key) throws InterruptedException, InvalidFormatException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String experrmsg=TestUtil.sheet1.getRow(10).getCell(1).toString();
		
		 WebElement txtbx=driver.findElement(By.name(""+key+""));
		 String actualvalue= txtbx.getAttribute("value");
		 txtbx.clear();
		 driver.findElement(savebtneditscreen).click();
		 Thread.sleep(1000);
		 String msg= driver.findElement(errorboxeditscreen).getText();
		 String acterrormsg=msg.split("Warning")[1];
		 txtbx.sendKeys(actualvalue);
		 Thread.sleep(2000);
		 return acterrormsg.equals(experrmsg);
		 
	}
	
	public boolean Mastermismatcherror() throws InvalidFormatException, InterruptedException, ClassNotFoundException, SQLException, IOException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(1);
		
		
		 Thread.sleep(8000);
			driver.findElement(pushmenuicon).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick));
			driver.findElement(rulemasterclick).click();
			
				driver.findElement(Viewrrulesheetclick).click();
				Thread.sleep(1000);
				
				Select se = new Select(driver.findElement(Entriestxtbx));
				se.selectByIndex(3);
				String data=driver.findElement(By.xpath("//*[@id=\"ViewRules_info\"]")).getText();
				Thread.sleep(1000);
				String count[]=data.split("of ");
				String datacount[]=count[1].split(" entries");
				String number=datacount[0];
				int num=Integer.parseInt(number);

				String dbURL = prop.getProperty("dburl");
				System.out.println("db url is : " + dbURL);
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
				while ((line = br.readLine()) != null) {
			     cols = line.split(",");
			     expecteddata.add(cols[0]); 
				}
				int rule_id = 0;
				String Rule_ref;
				Rule_ref=cols[0];
					
					 String selectquery ="SELECT * FROM Rule_Sheet_Master where Rule_Reference='"+Rule_ref+"';";
					System.out.println("query: " + selectquery);
					 rs = st.executeQuery(selectquery);
					 rs.next();
					 rule_id=rs.getInt("id");
					
					 String trimmeddata = null;
					 WebElement expele = null;
					 String rowno = null;
					 for (int d = 0; d < expecteddata.size(); d++) {
							  String expdata=expecteddata.get(d).toString();
							  	
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
										 Thread.sleep(3000);
										 break;
								  	}
								  	else {
								  	}
							  }
					 }
					 
			 driver.findElement(By.xpath("//*[@id=\"ViewRules\"]/tbody/tr"+rowno+"/td[69]/div/a[@id='ToEdit_"+rule_id+"']")).click();
			 Thread.sleep(1000);
			 boolean flag1 = false;
			 for(int i = 0 ; i <= TestUtil.sheet1.getPhysicalNumberOfRows()-1; i++) 
			 {
				 String value=TestUtil.sheet1.getRow(i+1).getCell(2).toString();
				 String columnnnameexp=TestUtil.sheet1.getRow(i+1).getCell(1).toString();
				 String Actualvalue=TestUtil.sheet1.getRow(i+1).getCell(3).toString();
				 String driverid=TestUtil.sheet1.getRow(i+1).getCell(5).toString();
				 String errormessage=TestUtil.sheet1.getRow(i+1).getCell(4).toString();

				 if(driverid.equals("name"))
				 {
					 driverid=TestUtil.sheet1.getRow(i+1).getCell(6).toString();
					 if(value.equals("NO VALIDATION")) {
						 //String valuefromui=txtbx.getText();
					 }else {
						 driver.findElement(By.name(driverid)).clear();
						 Thread.sleep(1000);
						 driver.findElement(By.name(driverid)).sendKeys(value);
						 Thread.sleep(500);
						 driver.findElement(savebtneditscreen).click();
						
						 Thread.sleep(1000);
						 driver.findElement(Yesbtneditscreen).click();
						 Thread.sleep(2000);
						// String columnnameactual=driver.findElement(By.xpath("//tbody/tr/td[count(//th[contains(text(),'Error Column Name')]/preceding-sibling::th)+1]")).getText();
						// String firstcolumn=columnnameactual.trim();
						 String Errordiscactual=driver.findElement(By.xpath("//tbody/tr/td[count(//th[contains(text(),'Error Description')]/preceding-sibling::th)+1]")).getText();
						 
						// boolean flag=firstcolumn.equals(columnnnameexp.trim());
						// System.out.println(flag + "value of flag" + "actual value is:" +firstcolumn + "exp is:" + columnnnameexp.trim());
						Thread.sleep(300);
						 driver.findElement(By.xpath("//span[@class='close-jq-toast-single']")).click();
						 Thread.sleep(500);
						 driver.findElement(By.xpath("//button[@class='close']")).click();
						 Thread.sleep(500);
						 if(errormessage.equals("NO VALIDATION"))
						 {
							 flag1=false;
						 }
						 else {
							
							 flag1=Errordiscactual.trim().equals(errormessage+columnnnameexp.trim());
							 System.out.println("expected error msgs is: " + errormessage+columnnnameexp.trim()+ "Actual errormsg is: "+ Errordiscactual.trim());
							 System.out.println("flag1 is " +flag1);
							 if(flag1==false) {
									System.out.println("Res3 got failed for field:"  +columnnnameexp);
									driver.findElement(By.name(driverid)).clear();
									 Thread.sleep(1000);
									 driver.findElement(By.name(driverid)).sendKeys(Actualvalue);
									 Thread.sleep(500);
								}
							 else {
								 driver.findElement(By.name(driverid)).clear();
								 Thread.sleep(1000);
								 driver.findElement(By.name(driverid)).sendKeys(Actualvalue);
								 Thread.sleep(500);
							 }
						 }
						 
					 }
					 
				 }else {
					  driverid=TestUtil.sheet1.getRow(i+1).getCell(5).toString();
					 
				 if(value.equals("NO VALIDATION")) {
					 //String valuefromui=txtbx.getText();
				 }else {
					 driver.findElement(By.id(driverid)).clear();
					 Thread.sleep(1000);
					 driver.findElement(By.id(driverid)).sendKeys(value);
					 Thread.sleep(500);
					 driver.findElement(savebtneditscreen).click();
					// wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/div[4]")));
					 Thread.sleep(1000);
					 driver.findElement(Yesbtneditscreen).click();
					 Thread.sleep(2000);
					// String columnnameactual=driver.findElement(By.xpath("//tbody/tr/td[count(//th[contains(text(),'Error Column Name')]/preceding-sibling::th)+1]")).getText();
					// String firstcolumn=columnnameactual.trim();
					 String Errordiscactual=driver.findElement(By.xpath("//tbody/tr/td[count(//th[contains(text(),'Error Description')]/preceding-sibling::th)+1]")).getText();
					 
					// boolean flag=firstcolumn.equals(columnnnameexp.trim());
					// System.out.println(flag + "value of flag" + "actual value is:" +firstcolumn + "exp is:" + columnnnameexp.trim());
					Thread.sleep(300);
					 driver.findElement(By.xpath("//span[@class='close-jq-toast-single']")).click();
					 Thread.sleep(500);
					 driver.findElement(By.xpath("//button[@class='close']")).click();
					 Thread.sleep(500);
					 driver.findElement(By.id(driverid)).clear();
					 Thread.sleep(1000);
					 driver.findElement(By.id(driverid)).sendKeys(Actualvalue);
					 Thread.sleep(500);
					 if(errormessage.equals("NO VALIDATION"))
					 {
						 flag1=false;
					 }
					 else {
						
						 flag1=Errordiscactual.trim().equals(errormessage+columnnnameexp.trim());
						 System.out.println("Expected errormsg is: " + errormessage+columnnnameexp.trim()+ " Actual errormsg is: "+ Errordiscactual.trim());
						// System.out.println("flag1 is " +flag1);
						 if(flag1==false) {
								System.out.println("Res3 got failed for field:"  +columnnnameexp);	
							}
						 else {
							// System.out.println("tc got pass");
						 }
					 }
					 
				 }
			 }
			 }
		return flag1;			
	}
	
	public boolean TC_23() throws InterruptedException
	{
		Thread.sleep(8000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(uploadrulesheetclick)).click();
		
	
		Actions act=new Actions(driver);
		act.moveToElement(driver.findElement(choosebtn));
		String pointercursor=driver.findElement(choosebtn).getCssValue("cursor");
		Thread.sleep(1000);
		act.moveToElement(driver.findElement(Viewexistingrulebutton));
		String handcusror=driver.findElement(Viewexistingrulebutton).getCssValue("cursor");
		//System.out.println("epected cursor value is " + handcusror + "other element value is " + pointercursor);
		Thread.sleep(500);
		driver.findElement(Viewexistingrulebutton).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewexistingrulescreentitle));
		return !pointercursor.equals(handcusror) && driver.findElement(Viewexistingrulescreentitle).isDisplayed();
	}
	
	public boolean TC_24() throws InvalidFormatException, InterruptedException
	{
		Thread.sleep(8000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String expfirstoption=TestUtil.sheet1.getRow(13).getCell(1).toString();
		List<String> expoptionsnamelist=new ArrayList<String>();
		for(int i=0 ; i<= 2; i++)
		{
			 String expoptionsname=TestUtil.sheet1.getRow(i+13).getCell(1).toString();
			 expoptionsnamelist.add(expoptionsname);
		}
		
		Select se=new Select(driver.findElement(ViewrulescreenDatetxtbx));
		List<WebElement> optionselements=se.getOptions();
		List<String> optionsnamelist=new ArrayList<String>();
		String optionsname="";
		String firstoption=se.getFirstSelectedOption().getText();
		
		for(WebElement ele:optionselements)
		{
			optionsname=ele.getText();
			optionsnamelist.add(optionsname);
		}
		
		return expoptionsnamelist.equals(optionsnamelist) && expfirstoption.equals(firstoption);
	}
	
	public boolean TC24res3() throws ClassNotFoundException, SQLException, IOException, InterruptedException
	{
		Thread.sleep(8000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\rules_sheet2.csv"));
		String line;String[] cols = null ;
		String headerLine = br.readLine();

		while ((line = br.readLine()) != null) {
		     cols = line.split(",");
			}
			int rule_id = 0;
			String Rule_ref=cols[0];
		
		String dbURL = prop.getProperty("dburl");
		String username =  prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs = null;
		
		 String selectquery ="SELECT * FROM Rule_Sheet_Master where Rule_Reference='"+Rule_ref+"';";
		 rs = st.executeQuery(selectquery);
		 rs.next();
		 Date entrydatedate=rs.getDate("Created_on");
		 System.out.println("entry date is : " + entrydatedate);
		int month= entrydatedate.getMonth();
		int year=entrydatedate.getYear();
		
		 	long millis=System.currentTimeMillis();  
		    java.sql.Date date = new java.sql.Date(millis);       
		    System.out.println(date);   
		    
		    driver.findElement(Fromdatetxtbx).click();
		    Thread.sleep(1000);

		   driver.findElement(By.xpath("//a[contains(text(),'"+entrydatedate.getDate()+"')]")).click();
		   Thread.sleep(1000);
		   driver.findElement(Todatetxtbx).click();
		   Thread.sleep(1000);
		   driver.findElement(By.xpath("//a[contains(text(),'"+entrydatedate.getDate()+"')]")).click();
		   Thread.sleep(1000);
		   driver.findElement(By.id("btnview")).click();
		   Thread.sleep(1000);
		   String rulerefactual=driver.findElement(By.xpath("//tbody/tr[5]/td[count(//*[@id='ViewRules_wrapper']/div[4]/div[2]/div[1]/div/table/thead/tr/"
		   		+ "th[contains(text(),'Company Name')]/preceding-sibling::th)]")).getText();
		  // System.out.println("exp rule ref is :" +Rule_ref + " rulerefactual.trim() is :" +  rulerefactual.trim());
		   boolean flag=rulerefactual.trim().equals(Rule_ref);
		   
		   //System.out.println(flag);
		   
		    return entrydatedate.equals(date) && flag;
	}
	public boolean TC_25res1n3() throws InterruptedException, InvalidFormatException {
		
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String expplaceholder=TestUtil.sheet1.getRow(16).getCell(0).toString();
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		String actualplaceholder=driver.findElement(Fromdatetxtbx).getAttribute("placeholder");
		
		driver.findElement(Fromdatetxtbx).click();
		Select monthoptions=new Select(driver.findElement(calendarmonth));
		monthoptions.selectByVisibleText("Jul");
		
		Select yearoptions=new Select(driver.findElement(calendaryear));
		yearoptions.selectByVisibleText("2022");
		driver.findElement(By.xpath("//a[contains(text(),'13')]")).click();
	
		Thread.sleep(2000);
		return expplaceholder.equals(actualplaceholder.trim()) && !driver.findElement(Fromdatetxtbx).getAttribute("value").isEmpty();
	}
	public boolean TC_25res4() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
		driver.findElement(Fromdatetxtbx).sendKeys("13-Jul-2022");
		Thread.sleep(2000);
		String emptyvalue=driver.findElement(Fromdatetxtbx).getAttribute("value");
		boolean flag1=emptyvalue.isEmpty();
		driver.navigate().refresh();
	return flag1;
	}
	public boolean TC_26res1n3() throws InterruptedException, InvalidFormatException {
		
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String expplaceholder=TestUtil.sheet1.getRow(16).getCell(0).toString();
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		String actualplaceholder=driver.findElement(Todatetxtbx).getAttribute("placeholder");
		
		driver.findElement(Todatetxtbx).click();
		Select monthoptions=new Select(driver.findElement(calendarmonth));
		monthoptions.selectByVisibleText("Jul");
		
		Select yearoptions=new Select(driver.findElement(calendaryear));
		yearoptions.selectByVisibleText("2022");
		driver.findElement(By.xpath("//a[contains(text(),'13')]")).click();
	
		Thread.sleep(2000);
		return expplaceholder.equals(actualplaceholder.trim()) && !driver.findElement(Todatetxtbx).getAttribute("value").isEmpty();
	}
	public boolean TC_26res4() throws InterruptedException
	{
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
		driver.findElement(Todatetxtbx).sendKeys("13-Jul-2022");
		Thread.sleep(2000);
		String emptyvalue=driver.findElement(Todatetxtbx).getAttribute("value");
		boolean flag1=emptyvalue.isEmpty();
		driver.navigate().refresh();
	return flag1;
	}
	
	
	public boolean TC_28res1() throws InterruptedException, IOException, InvalidFormatException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
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
	
	public boolean TC_28res2() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
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
	public boolean TC_28res5() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String errormsg=TestUtil.sheet1.getRow(9).getCell(1).getStringCellValue();
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
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
	public boolean TC_28res3() throws InterruptedException, InvalidFormatException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
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
	
	public boolean TC_31res1() throws InterruptedException, IOException, InvalidFormatException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String expresult=TestUtil.sheet1.getRow(17).getCell(0).getStringCellValue();
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
		driver.findElement(RTOtextbxapproval).click();
		WebElement companytobeselected=driver.findElement(By.xpath("//*[@id='select2-Rto-results']/li[1]"));
		companytobeselected.click();
		Thread.sleep(1000);		
		String actualresult=driver.findElement(By.xpath("//span/ul[@class='select2-selection__rendered']/li[1]")).getText();	
		System.out.println("results are: " + expresult + actualresult);
		return actualresult.contains(expresult);
	}
	public boolean TC_31res2() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
			String texttosend=TestUtil.sheet1.getRow(1).getCell(7).getStringCellValue();
		//	System.out.println("text to send is: " +texttosend);
	
			Thread.sleep(1000);
			Select se=new Select(driver.findElement(By.id("Rto")));
			se.selectByVisibleText(texttosend);
			Thread.sleep(500);
		String actualresult=driver.findElement(By.xpath("//span/ul[@class='select2-selection__rendered']/li[1]")).getText();
		return actualresult.contains(texttosend);
	}
	public boolean TC_31res6() throws InvalidFormatException, InterruptedException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
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
	
	public boolean TC_33res1n2() throws InterruptedException, InvalidFormatException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
	
		Thread.sleep(10000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		//from date selection
		driver.findElement(Fromdatetxtbx).click();
		Select monthoptions=new Select(driver.findElement(calendarmonth));
		monthoptions.selectByVisibleText("Jul");
		
		Select yearoptions=new Select(driver.findElement(calendaryear));
		yearoptions.selectByVisibleText("2022");
		driver.findElement(By.xpath("//a[contains(text(),'13')]")).click();
		//to date selection
		Thread.sleep(500);
		driver.findElement(Todatetxtbx).click();
		Select monthoptionsto=new Select(driver.findElement(calendarmonth));
		monthoptionsto.selectByVisibleText("Jul");
		
		Select yearoptionsto=new Select(driver.findElement(calendaryear));
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
		driver.findElement(Viewexistingrulescreentitle).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//i[@class='fa fa-arrows-rotate']")).click();
		String bgcolor=driver.findElement(clearbutton).getCssValue("background-color");
		//System.out.println("colors are " + bgcolor.equals("rgba(239, 59, 133, 1)"));
		Thread.sleep(1000);
		boolean flag=driver.findElement(Fromdatetxtbx).getAttribute("value").isEmpty();
		boolean flag1=driver.findElement(Todatetxtbx).getAttribute("value").isEmpty();
		boolean flag2=driver.findElement(By.xpath("//span/ul[@class='select2-selection__rendered']/li[1]")).getText().isEmpty();
		boolean flag3=driver.findElement(By.id("select2-Company-container")).getText().contains("Select");
		boolean flag4=driver.findElement(By.id("select2-MainProduct-container")).getText().contains("Select");
		boolean flag5=driver.findElement(By.id("select2-Product-container")).getText().contains("Select");
		
		return flag && flag1 && flag2 && flag3 && flag4 && flag5 && driver.findElement(Fromdatetxtbx).isDisplayed() && 
				driver.findElement(Todatetxtbx).isDisplayed() &&
				driver.findElement(By.id("select2-Company-container")).isDisplayed() && 
				driver.findElement(By.id("select2-MainProduct-container")).isDisplayed() &&
				driver.findElement(By.id("select2-Product-container")).isDisplayed() && 
				driver.findElement(By.id("btnview")).isDisplayed() && 
				driver.findElement(clearbutton).isDisplayed() && 
				driver.findElement(By.xpath("//table[@role='grid']")).isDisplayed() &&
				driver.findElement(By.xpath("//button[@class='btn btn-primary buttons-excel buttons-html5']")).isDisplayed()
				&& driver.findElement(Viewexistingrulescreentitle).isDisplayed();
	}
	public boolean Tc_34() throws InvalidFormatException, InterruptedException, ClassNotFoundException, SQLException
	{
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		Thread.sleep(1000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
		boolean flag=false;
		for(int i = 0 ; i <= 1 ; i++) {
			String texttosend=TestUtil.sheet1.getRow(i+1).getCell(9).getStringCellValue();
			driver.findElement(searchtxtbx).sendKeys(texttosend);
			
			WebElement expele=driver.findElement(By.xpath("//tbody/tr[1]/td[count(//*[@id='ViewRules_wrapper']/"
					+ "div[4]/div[2]/div[1]/div/table/thead/tr/th[contains(text(),'Company Name')]/preceding-sibling::th)+1]"));
			String companylistactual=expele.getText();
			String trimmeddata=companylistactual.trim();
			flag=trimmeddata.contains(texttosend);
			System.out.println("flag is " + flag);
			driver.findElement(searchtxtbx).clear();
			Thread.sleep(1000);
			
		}
		
		return flag;
	}
	public boolean TC_27res1n2() throws InvalidFormatException, InterruptedException
	{
		Thread.sleep(8000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String expfirstoption=TestUtil.sheet1.getRow(13).getCell(2).toString();
		List<String> expoptionsnamelist=new ArrayList<String>();
		for(int i=0 ; i<= 2; i++)
		{
			 String expoptionsname=TestUtil.sheet1.getRow(i+13).getCell(2).toString();
			 expoptionsnamelist.add(expoptionsname);
		}
		
		Select se=new Select(driver.findElement(Statustxtbx));
		List<WebElement> optionselements=se.getOptions();
		List<String> optionsnamelist=new ArrayList<String>();
		String optionsname="";
		String firstoption=se.getFirstSelectedOption().getText();
		se.selectByVisibleText(firstoption);
		boolean flag=false;
		for(WebElement ele:optionselements)
		{
			optionsname=ele.getText();
			optionsnamelist.add(optionsname);
			se.selectByVisibleText(optionsname);
			String selectedvalue=se.getFirstSelectedOption().getText();
			flag=selectedvalue.equals(optionsname);
		}
		
		return expoptionsnamelist.equals(optionsnamelist) && expfirstoption.equals(firstoption) && flag;
	}
	
	public boolean TC_27res4() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
		Thread.sleep(8000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(uploadrulesheetclick)).click();//
		driver.findElement(choosebtn).sendKeys(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\rules_sheet2.csv");
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
		} else {
			System.out.println("Rule sheet didn't get ingest to the system.");
		}
		
		Thread.sleep(8000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
		
		TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String expfirstoption=TestUtil.sheet1.getRow(13).getCell(2).toString();
		List<String> expoptionsnamelist=new ArrayList<String>();
		for(int i=0 ; i<= 2; i++)
		{
			 String expoptionsname=TestUtil.sheet1.getRow(i+13).getCell(2).toString();
			 expoptionsnamelist.add(expoptionsname);
		}
		
		Select se=new Select(driver.findElement(Statustxtbx));
		List<WebElement> optionselements=se.getOptions();
		List<String> optionsnamelist=new ArrayList<String>();
		String optionsname="";
		String firstoption=se.getFirstSelectedOption().getText();
		se.selectByVisibleText(firstoption);
		boolean flag=false;
		for(WebElement ele:optionselements)
		{
			optionsname=ele.getText();
			optionsnamelist.add(optionsname);
		}
		
		se.selectByVisibleText("Pending for approval");
		driver.findElement(Viewbtn).click();
		Thread.sleep(500);
		WebElement statuselement=driver.findElement(By.xpath("//tbody/tr/td[count(//*[@id='ViewRules_wrapper']/div[4]/div[2]/div[1]/div/"
				+ "table/thead/tr/th[contains(text(),'Status')]/preceding-sibling::th)+1]"));
		String actualdatatext=statuselement.getText();
		 String expdatatext=TestUtil.sheet1.getRow(15).getCell(2).toString();
	//	System.out.println("text :" +statuselement.getText() );
		boolean flag5=actualdatatext.equals(expdatatext);
		
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
		driver.findElement(rulemasterclick).click();
		driver.findElement(approverulesheetclick).click();
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(Viewbtn));
		Select se4 = new Select(driver.findElement(Entriestxtbx));
		se4.selectByIndex(3);
		//String confirmmsg = null;
		
		String data=driver.findElement(By.xpath("//*[@id=\"ViewRules_info\"]")).getText();
		Thread.sleep(1000);
		String count[]=data.split("of ");
		String datacount[]=count[1].split(" entries");
		String number=datacount[0];
		int num=Integer.parseInt(number);
	//	System.out.println("number is : " + number);
		
		List<String> rule_idlist=new ArrayList<String>();
		
		String dbURL = prop.getProperty("dburl");
		String username =  prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");
	//	System.out.println("dburl is : " +dbURL);
		//System.out.println("driver Loaded");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs = null;
		
		for(int i =0; i<num;i++)
		{
		
			Thread.sleep(3000);
			WebElement rule_id=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[2]"));
			 //System.out.println( "string rule_id is " +rule_id.getText() );
			int rule=Integer.parseInt(rule_id.getText());
			 String selectquery ="UPDATE Rule_Sheet_Master SET `Status` = '1',`Updated_on`= current_timestamp(), `Updated_by`='3' WHERE (`Rule_Reference` = '"+rule+"');";
			//System.out.println("query is : " + selectquery);
			PreparedStatement pst = con.prepareStatement(selectquery);
			pst.executeUpdate();
		
		}
			driver.navigate().refresh();
			driver.findElement(pushmenuicon).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick)).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(Viewrulesheetclick)).click();
			String actualdatatext1 = null;
			for(int i=0 ; i<=1 ; i++) {
				String selectoption=optionsnamelist.get(i).toString();
				Select se1=new Select(driver.findElement(Statustxtbx));
				se1.selectByVisibleText(selectoption);
				
			//	System.out.println("got another option :" + selectoption);
				driver.findElement(Viewbtn).click();
				Thread.sleep(500);
				WebElement statuselement1=driver.findElement(By.xpath("//tbody/tr/td[count(//*[@id='ViewRules_wrapper']/div[4]/div[2]/div[1]/div/"
						+ "table/thead/tr/th[contains(text(),'Status')]/preceding-sibling::th)+1]"));
				actualdatatext1=statuselement1.getText();
				//System.out.println("text :" +statuselement1.getText() );
				
			}
			String expdatatext1=TestUtil.sheet1.getRow(14).getCell(2).toString();
			boolean flag6=actualdatatext1.equals(expdatatext1);
	return flag5 && flag6;
	}
	public boolean TC_37(int num) throws IOException, InterruptedException
	{
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\rules_sheet2.csv"));
		String line;String[] cols = null ;
		String headerLine = br.readLine();
		List<String> expecteddata=new ArrayList<String>();
		
		while ((line = br.readLine()) != null) {
		     cols = line.split(",");
		     expecteddata.add(cols[0]); 
		}
		        List<String> actualdatalist=new ArrayList<String>();
				List<String> actualdatalisttrimmed=new ArrayList<String>();
			
			for(int i =0; i<num;i++)
			{
				Thread.sleep(3000);
				WebElement expele=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[count(//*[@id='ViewRules_wrapper']/"
						+ "div[4]/div[2]/div[1]/div/table/thead/tr/th[contains(text(),'Company Name')]/preceding-sibling::th)]"));
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
								 
								 if (actuldata.equals(expdata)){
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
				
				System.out.println("Data didn't match for the values of Rule Reference expected data is: " + union + " actual data diaplayed is:" + unionactual);
				 boolean flag=union.equals(unionactual);
				Thread.sleep(1000);
			 
			 return flag;
	}
	public boolean Tc_36actual(int num) throws IOException, InterruptedException, ClassNotFoundException, SQLException
	{
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\rules_sheet2.csv"));
		String line;String[] cols = null ;
		String headerLine = br.readLine();
		List<String> expecteddata=new ArrayList<String>();
		
		while ((line = br.readLine()) != null) {
		     cols = line.split(",");
		     expecteddata.add(cols[0]); 
		}
		        List<String> actualdatalist=new ArrayList<String>();
				List<String> actualdatalisttrimmed=new ArrayList<String>();
				List<String> rule_idlist=new ArrayList<String>();
				String dbURL = prop.getProperty("dburl");
				String username =  prop.getProperty("DBusename");
				String Password = prop.getProperty("DBPassword");
			//	System.out.println("dburl is : " +dbURL);
				//System.out.println("driver Loaded");
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(dbURL, username, Password);
				Statement st = con.createStatement();
				ResultSet rs = null;
				
			for(int i =0; i<num;i++)
			{
				Thread.sleep(3000);
				WebElement expele=driver.findElement(By.xpath("//tbody/tr["+(i+1)+"]/td[count(//*[@id='ViewRules_wrapper']/"
						+ "div[4]/div[2]/div[1]/div/table/thead/tr/th[contains(text(),'Company Name')]/preceding-sibling::th)-1]"));
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
					  String selectquery ="SELECT * FROM Rule_Sheet_Master where Rule_Reference='"+expdata+"';";
						 rs = st.executeQuery(selectquery);
						 rs.next();
						 int rule_id=rs.getInt("id");
						// System.out.println("id is : " + rule_id);
						 rule_idlist.add(Integer.toString(rule_id));
					//	 System.out.println("rule id list is:" + rule_idlist);
						 
						for(int l=0; l <actualdatalist.size() ;l++ ) {
								 String actuldata=actualdatalisttrimmed.get(l).toString();
							
								 if (actuldata.equals(expdata)){
									// System.out.println("data matched" + actuldata  +" " + expdata );
									 actualdatalisttrimmedremaining.add(actuldata);
									 break;
									 
								 }
								 else {
								//	 System.out.println("data doesn't match for " + actuldata  +" " + expdata );
								 }
							}
			 }
			 
				List<String> unionactual = new ArrayList<String>(actualdatalisttrimmed);
				unionactual.addAll(rule_idlist);
				List<String> union1actual = new ArrayList<String>(actualdatalisttrimmed);
				union1actual.retainAll(rule_idlist);
				unionactual.removeAll(union1actual);
				
				System.out.println("Data didn't match for the values of expected data is: " + unionactual );
				 boolean flag=union1actual.equals(rule_idlist);
				Thread.sleep(1000);
			 
			 return flag;
	}
	
	public void Tc_32() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Rule_sheet doc\\rules_sheet2.csv"));
		String line;String[] cols = null ;
		String headerLine = br.readLine();
		List<String> expecteddata=new ArrayList<String>();
		line = br.readLine();
		String expdata=line.replace(",", " ");
		System.out.println("line data is :" + expdata);
		
		String firststrowdata=driver.findElement(By.xpath("//table/tbody/tr[1]")).getText();
		System.out.println("actdata1 is :" + firststrowdata);
		System.out.println("flag is : "+ expdata.contains(firststrowdata));
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
	public boolean deleteclick() throws InterruptedException, SQLException, ClassNotFoundException, IOException, InvalidFormatException
	{
		Thread.sleep(8000);
		driver.findElement(pushmenuicon).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(rulemasterclick));
		driver.findElement(rulemasterclick).click();
		driver.findElement(Viewrrulesheetclick).click();
		Thread.sleep(1000);
	
			Select se = new Select(driver.findElement(Entriestxtbx));
			se.selectByIndex(4);
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
			int rule_id = 0;
			String Rule_ref;
			Rule_ref=cols[0];
				
				 String selectquery ="SELECT * FROM Rule_Sheet_Master where Rule_Reference='"+Rule_ref+"';";
				 rs = st.executeQuery(selectquery);
				 rs.next();
				 rule_id=rs.getInt("id");
				
				 String trimmeddata = null;
				 WebElement expele = null;
				 String rowno = null;
				 for (int d = 0; d < expecteddata.size(); d++) {
						  String expdata=expecteddata.get(d).toString();
						  	
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
									 Thread.sleep(3000);
									 break;
							  	}
							  	else {
							  	}
						  }
				 }
				 
		 driver.findElement(By.xpath("//*[@id=\"ViewRules\"]/tbody/tr"+rowno+"/td[69]/div/a[@id='IsRejected_"+rule_id+" ']")).click();
		 Thread.sleep(1000);
		 boolean flag=driver.findElement(yesbtn).isDisplayed();
		 String message=driver.findElement(By.tagName("h5")).getText();
		 TestUtil.getTestData1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
			TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
			String expmsg=TestUtil.sheet1.getRow(16).getCell(1).getStringCellValue();
		 System.out.println("message is :" + message);
		 boolean flag1=expmsg.equals(message);
		 driver.findElement(yesbtn).click();
		 boolean flag2=driver.findElement(By.xpath("//tbody/tr"+rowno+"/td[count(//*[@id=\"ViewRules_wrapper\"]/div[4]/div[2]/div[1]/div/"
				  + "table/thead/tr/th[contains(text(),'Rule Reference')]/preceding-sibling::th)+1]")).isDisplayed();
		 Thread.sleep(2000);

		 driver.findElement(By.xpath("//*[@id=\"ViewRules\"]/tbody/tr/td[69]/div/a[@id='IsRejected_1 ']")).click();
		 driver.findElement(By.xpath("//button[contains(text(),'Cancel')]")).click();
		 Thread.sleep(1000);
		 boolean flag3=driver.findElement(By.xpath("//tbody/tr[1]/td[count(//*[@id='ViewRules_wrapper']/div[4]/div[2]/div[1]/div/table/"
		 		+ "thead/tr/th[contains(text(),'Rule Reference')]/preceding-sibling::th)+1]")).isDisplayed();
		 // System.out.println("flag " +flag+"flag1" +flag1+ "flag2" +flag2 +"flag3"+flag3);
		 Thread.sleep(1000); 
		 return flag && flag1 && !flag2 && flag3;
	}
	
	
}
