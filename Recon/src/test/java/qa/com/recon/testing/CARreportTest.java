package qa.com.recon.testing;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.*;

import com.opencsv.exceptions.CsvValidationException;

import qa.com.recon.base.TestBase;
import qa.com.recon.logic.CARreport;
import qa.com.recon.logic.GBRingestion;
import qa.com.recon.logic.Loginrecon;
import qa.com.recon.logic.Rulesheetingestion;
import qa.com.recon.logic.ViewnEditRulesheet;
import qa.com.recon.utility.TestUtil;

public class CARreportTest extends TestBase{

	
	public CARreportTest()
	{
		super();
	}
	
	Loginrecon login=new Loginrecon();
	CARreport carreport=new CARreport();
	ViewnEditRulesheet viewrule;
	Rulesheetingestion ruleing;
	GBRingestion gbring=new GBRingestion();
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		intialization();
		viewrule=new ViewnEditRulesheet();
		ruleing=new Rulesheetingestion();
		login.login();
	}
	@Test
	public void ViewCAR()
	{
		//Assert.assertTrue(carreport.viewcarreport(),"TC got failed as totalrow is not visible");
	}
	@Test
	public void DownloadCAR() throws InterruptedException
	{
//		Assert.assertTrue(carreport.downloadcar(),"TC got failed as successmsg is not visible");
	}
	@Test
	public void TC_339() throws InterruptedException
	{
		Assert.assertTrue(carreport.tc_339(),"TC got failed as tabs are not clickable.");
	}
	@Test
	public void TC_340res1() throws InterruptedException
	{
		Assert.assertTrue(carreport.tc_340res1(),"TC got failed as this fields are not displayed.");
	}
	@Test(priority=1)
	public void TC_340res2() throws InterruptedException, ClassNotFoundException, SQLException
	{
		//TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		TestUtil.Truncategbrtable();
		//viewrule.rulesheetingestion("sme_rule_sheet.csv");
		gbring.gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\GBRALL_SME_Dataset1.xlsx");
		Assert.assertTrue(carreport.tc_340res2(),"TC got failed as this fields are not displayed.");
	}
	
	@Test
	public void TC_341() throws InterruptedException, ClassNotFoundException, SQLException
	{
		Assert.assertTrue(carreport.Tc_341(),"TC got failed as from date is not functioning as expected.");
	}
	@Test
	public void TC_342() throws InterruptedException, ClassNotFoundException, SQLException
	{
		Assert.assertTrue(carreport.Tc_342(),"TC got failed as To date is not functioning as expected.");
	}
	@Test
	public void Tc_343() throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException
	{
//		TestUtil.Truncategbrtable();
//		gbring.gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\GBRALL_SME_Dataset1.xlsx");
		Assert.assertTrue(carreport.Tc_343(),"TC got failed as To date is not functioning as expected.");
		Assert.assertTrue(carreport.tc_343res8(),"TC got failed as fields are not matching");
	}
	@Test
	public void Tc_345() throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException
	{
		TestUtil.Truncategbrtable();
		gbring.gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\GBRALL_SME_Dataset1.xlsx");
		Assert.assertTrue(carreport.Tc_345(),"TC got failed as view button's functioning is not as expected.");
	}
	@Test
	public void Tc_346() throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException
	{
		Assert.assertTrue(carreport.Tc_346(),"TC got failed as clear button's functioning is not as expected.");
	}
	@Test
	public void Tc_357() throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException
	{
		TestUtil.Truncategbrtable();
		gbring.gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\GBRALL_SME_Dataset1.xlsx");
		Assert.assertTrue(carreport.TC_357(),"TC got failed as od_addon_total is not as expected");
		Assert.assertTrue(carreport.Tc_357res4("OD+ADDON","OD","ADD_ON"),"Tc got failed as");
	}
	
	@Test
	public void Tc_358() throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException
	{
//		TestUtil.Truncategbrtable();
//		gbring.gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\GBRALL_SME_Dataset1.xlsx");
		Assert.assertTrue(carreport.Tc_358(),"TC got failed as od_addon_total is not as expected");
		Assert.assertTrue(carreport.Tc_357res4("TP+Terrorism","TP","terrorism"),"Tc got failed as");
	}
	@Test
	public void Tc_359() throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException, IOException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("sme_rule_sheet.csv");
		boolean flag=carreport.tc_359res4("noofemployee",1,2);
		if(flag==false)System.out.println("Tc got failed as Basis data didn't matchin view report");
		boolean flag1=carreport.tc_359res1(1,"Basis");
		if(flag1==false)System.out.println("Tc got failed as Basis data didn't match in downloaded report");
		boolean flag2=carreport.tc_359res4("Total_Value",5,2);
		if(flag2==false)System.out.println("Tc got failed as Basis_1_W data didn't match in view report");
		boolean flag3=carreport.tc_359res1(5,"Basis_1_W");
		if(flag3==false)System.out.println("Tc got failed as Basis_1_W data didn't match in downloaded report");
		boolean flag4=carreport.tc_359res4("Value_A",6,2);
		if(flag4==false)System.out.println("Tc got failed as Basis_2_B data didn't match in view report");
		boolean flag5=carreport.tc_359res1(6,"Basis_2_B");
		if(flag5==false)System.out.println("Tc got failed as Basis_2_B data didn't match in downloaded report");
		
	}
	@Test
	public void Tc_360() throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException, IOException
	{
		boolean flag=carreport.tc_360res4("noofemployee",2,3);
		if(flag==false)System.out.println("Tc got failed as Basis data didn't matchin view report");
		boolean flag1=carreport.tc_360res1(2,"OD_Rate");
		if(flag1==false)System.out.println("Tc got failed as Basis data didn't match in downloaded report");
		boolean flag2=carreport.tc_360res4("noofemployee",3,4);
		if(flag2==false)System.out.println("Tc got failed as Basis_1_W data didn't match in view report");
		boolean flag3=carreport.tc_360res1(3,"TP_Rate");
		if(flag3==false)System.out.println("Tc got failed as Basis_1_W data didn't match in downloaded report");
		boolean flag4=carreport.tc_360res4("noofemployee",4,5);
		if(flag4==false)System.out.println("Tc got failed as Basis_2_B data didn't match in view report");
		boolean flag5=carreport.tc_360res1(4,"Fixed_Rate_Value");
		if(flag5==false)System.out.println("Tc got failed as Basis_2_B data didn't match in downloaded report");
		
	}
	@Test
	public void TC_374() throws ClassNotFoundException, InvalidFormatException, InterruptedException, SQLException, IOException
	{
		boolean flag1=carreport.tc_359res4("Comment",7,1);
		if(flag1==false)System.out.println("Tc got failed as Basis_2_B data didn't match in view report");
		boolean flag=carreport.tc_359res1(7,"Comment");
		if(flag==false)System.out.println("Tc got failed as Basis data didn't match in downloaded report");
		
	}
	
	@Test
	public void Tc_361_to_3713res1() throws InterruptedException, CsvValidationException, IOException, ClassNotFoundException, SQLException, InvalidFormatException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		TestUtil.Truncategbrtable();
		viewrule.rulesheetingestion("sme_rule_sheet.csv");
		Thread.sleep(1000);
		gbring.gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\GBRALL_SME_Dataset1.xlsx");
		carreport.smegbrdownloadcar();//,"Success message didn't appear.");
		carreport.CARcalculation();
		Assert.assertTrue(carreport.CARcomparison(),"Data doesn't match to CARcalculation");
		Assert.assertTrue(carreport.tc_361_371res4(),"Tc_361_371res4 got fail as data didn't match as expected in view CAR");
			
	}

	@Test
	public void Tc_376() throws ClassNotFoundException, InterruptedException, SQLException, InvalidFormatException, IOException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		TestUtil.Truncategbrtable();
		viewrule.rulesheetingestion("sme_rule_sheet.csv");
		Thread.sleep(1000);
		gbring.gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\GBRALL_SME_Dataset1.xlsx");
		gbring.gbringestion1(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\GBRALL_SME_Dataset2.xlsx");
		Assert.assertTrue(carreport.Tc_376res1(),"Tc got failed as user_id doesn't appear as expected.");
	}
	@Test
	public void Tc_377() throws ClassNotFoundException, InterruptedException, SQLException, InvalidFormatException, IOException
	{
		Assert.assertTrue(carreport.Tc_377(),"Tc got failed as user_id doesn't appear as expected.");
	}
	@AfterMethod
	public void tearup()
	{
		driver.quit();
	}
	
}
