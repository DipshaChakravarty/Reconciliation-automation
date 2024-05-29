package qa.com.recon.testing;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import qa.com.recon.base.TestBase;
import qa.com.recon.logic.CARreport;
import qa.com.recon.logic.Loginrecon;
import qa.com.recon.logic.Rulesheetingestion;
import qa.com.recon.utility.TestUtil;

public class RulesheetTest extends TestBase {
	
	public RulesheetTest() throws CsvValidationException, IOException
	{
		super();
		urlconfig();
		//chooseafile();
		//chooseafile1();
		//chooseafile2();
	}
	Loginrecon login;
	Rulesheetingestion ruleing;
	CARreport carreport;
	
	@BeforeMethod
	public void setup() throws InterruptedException, CsvValidationException, IOException
	{
		
		intialization();
		login=new Loginrecon();
		ruleing=new Rulesheetingestion();
		carreport=new CARreport();
		login.login();
	}
	
//	@Test
//	public void CAR() throws InterruptedException, CsvValidationException, IOException, ClassNotFoundException, SQLException
//	{
//		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
//		TestUtil.Truncategbrtable();
//		ruleing.rulesheetupload();
//		Thread.sleep(1000);
//		System.out.println("now gbr will be ingested");
//		ruleing.gbringestion1();
//		carreport.downloadcar();//,"Success message didn't appear.");
//		carreport.CARcalculation();
//		Assert.assertTrue(carreport.CARcomparison(),"Data doesn't match to CARcalculation");
//	}
	
	@Test(priority=1)
	public void TC_Tc_256res1n2() throws InterruptedException
	{
		Assert.assertTrue(ruleing.Tc_256res1n2(),"TC got failed ");
	}
	@Test(priority=2)
	public void Tc_257res1n2() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(ruleing.Tc_257res1n2(),"TC got failed it didn't match expected dropdown options");
	}
	@Test(priority=3)
	public void TC_258res1n3() throws InterruptedException, InvalidFormatException
	{
		Assert.assertTrue(ruleing.TC_258res1n3(),"TC got failed it didn't match expected placeholder value or not able to select date");
	}
	@Test(priority=4)
	public void TC_258res4() throws InterruptedException, InvalidFormatException
	{
		Assert.assertTrue(ruleing.TC_258res4(),"TC got failed it allows to paste the date");
		//Assert.assertTrue(ruleing.TC_258res4_1(),"TC got failed it didn't disable to date");
	}
	@Test(priority=5)
	public void TC_259res1n3() throws InterruptedException, InvalidFormatException
	{
		Assert.assertTrue(ruleing.TC_259res1n3(),"TC got failed it didn't match expected placeholder value or not able to select date");
	}
//	@Test(priority=6)
//	public void TC_259res4() throws InterruptedException, InvalidFormatException
//	{
//		ruleing.TC_259res4();//,"TC got failed it didn't match expected placeholder value or not able to select date");
//	}
	@Test(priority=7)
	public void TC_269_to_278() throws InterruptedException, InvalidFormatException, IOException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		ruleing.rulesheetingestion("rules_sheet2.csv");
		int num=ruleing.approverulesheetclick();
		boolean flag=ruleing.TC_269(1,"Company Name",num);
		if(flag==false) System.out.println("Company Name didn't match expected data");
		boolean flag1=ruleing.TC_269(2,"Main Product Name",num);
		if(flag1==false) System.out.println("Main Product Name didn't match expected data");
		boolean flag3=ruleing.TC_269(4,"Sub-Product Name",num);//,"TC got failed it didn't match expected data");
		if(flag3==false) System.out.println("Sub-Product Name didn't match expected data");
		boolean flag4=ruleing.TC_269(5,"Discount (From)",num);//,"TC got failed it didn't match expected data");
		if(flag4==false) System.out.println("Discount (From) didn't match expected data");
		boolean flag5=ruleing.TC_269(6,"Discount (To)",num);//,"TC got failed it didn't match expected data");
		if(flag5==false) System.out.println("Discount (To) didn't match expected data");
		boolean flag6=ruleing.TC_269(7,"Policy Type",num);
		if(flag6==false) System.out.println("Policy Type didn't match expected data");
		boolean flag7=ruleing.TC_269(8,"Product Type",num);
		if(flag7==false) System.out.println("Product Type didn't match expected data");
		boolean flag8=ruleing.TC_269(9,"Total IDV (From)",num);
		if(flag8==false) System.out.println("Total IDV (From) didn't match expected data");
		boolean flag9=ruleing.TC_269(10,"Total IDV (To)",num);
		if(flag9==false) System.out.println("Total IDV (To) didn't match expected data");
		
	}
	@Test(priority=8)
	public void TC_279_to_288() throws InterruptedException, InvalidFormatException, IOException
	{
		int num=ruleing.approverulesheetclick();
		boolean flag1=ruleing.TC_269(11,"Fuel Type",num);
		if(flag1==false) System.out.println("Fuel Type didn't match expected data");
		boolean flag2=ruleing.TC_269(12,"Manufacturing Year (From)",num);
		if(flag2==false) System.out.println("Manufacturing Year (From) didn't match expected data");
		boolean flag3=ruleing.TC_269(13,"Manufacturing Year (To)",num);
		if(flag3==false) System.out.println("Manufacturing Year (To) didn't match expected data");
		boolean flag4=ruleing.TC_269(14,"Insurance Company Branch Short Name",num);
		if(flag4==false) System.out.println("Insurance Company Branch Short Name didn't match expected data");
		boolean flag5=ruleing.TC_269(15,"Insurance Company City Name",num);
		if(flag5==false) System.out.println("Insurance Company City Name didn't match expected data");
		boolean flag6=ruleing.TC_269(16,"Insurance Company State Name",num);
		if(flag6==false) System.out.println("Insurance Company State Name didn't match expected data");
		boolean flag7=ruleing.TC_269(17,"Branch",num);
		if(flag7==false) System.out.println("Branch didn't match expected data");
		boolean flag8=ruleing.TC_269(18,"Make",num);
		if(flag8==false) System.out.println("Make didn't match expected data");
		boolean flag9=ruleing.TC_269(19,"Model",num);
		if(flag9==false) System.out.println("Model didn't match expected data");
		boolean flag10=ruleing.TC_269(20,"Variant Name",num);
		if(flag10==false) System.out.println("Variant Name didn't match expected data");
	}
	@Test(priority=9)
	public void TC_289_to_298() throws InterruptedException, InvalidFormatException, IOException
	{
		int num=ruleing.approverulesheetclick();
		boolean flag1=ruleing.TC_269(21,"Seating Capacity (From)",num);
		if(flag1==false) System.out.println("Seating Capacity (From) didn't match expected data");
		boolean flag2=ruleing.TC_269(22,"Seating Capacity (To)",num);
		if(flag2==false) System.out.println("Seating Capacity (To) didn't match expected data");
		boolean flag3=ruleing.TC_269(23,"CC (From)",num);
		if(flag3==false) System.out.println("CC (From) didn't match expected data");
		boolean flag4=ruleing.TC_269(24,"CC (To)",num);
		if(flag4==false) System.out.println("CC (To) didn't match expected data");
		boolean flag5=ruleing.TC_269(25,"NCB (From)",num);
		if(flag5==false) System.out.println("NCB (From) didn't match expected data");
		boolean flag6=ruleing.TC_269(26,"NCB (To)",num);
		if(flag6==false) System.out.println("NCB (To) didn't match expected data");
		boolean flag7=ruleing.TC_269(27,"Agent Code",num);
		if(flag7==false) System.out.println("Agent Code didn't match expected data");
		boolean flag8=ruleing.TC_269(28,"Inward No",num);
		if(flag8==false) System.out.println("Inward No didn't match expected data");
		boolean flag9=ruleing.TC_269(30,"RTO",num);
		if(flag9==false) System.out.println("RTO didn't match expected data");
		boolean flag9_1=ruleing.TC_269(29,"RTO State Code",num);
		if(flag9_1==false) System.out.println("TC_66.1 RTO State code didn't match expected data");
		boolean flag10=ruleing.TC_269(31,"Proposal Age (From)",num);
		if(flag10==false) System.out.println("Proposal Age (From) didn't match expected data");
	
	}
	@Test(priority=10)
	public void TC_299_to_309() throws InterruptedException, InvalidFormatException, IOException
	{
		int num=ruleing.approverulesheetclick();
		boolean flag1=ruleing.TC_269(32,"Proposal Age (To)",num);
		if(flag1==false) System.out.println("Proposal Age (To)didn't match expected data");
		boolean flag2=ruleing.TC_269(33,"PPT (From)",num);
		if(flag2==false) System.out.println("PPT (From) didn't match expected data");
		boolean flag3=ruleing.TC_269(34,"PPT (To)",num);
		if(flag3==false) System.out.println("PPT (To) didn't match expected data");
		boolean flag3_1=ruleing.TC_269(35,"Issue Month",num);
		if(flag3_1==false) System.out.println("TC_71 Issue Month didn't match expected data");
		boolean flag4=ruleing.TC_269(36,"Reference Issue Date (From)",num);
		if(flag4==false) System.out.println("Reference Issue Date (From) didn't match expected data");
		boolean flag5=ruleing.TC_269(37,"Reference Issue Date (To)",num);
		if(flag5==false) System.out.println("Reference Issue Date (To) didn't match expected data");
		boolean flag6=ruleing.TC_269(38,"OD Start Date (From)",num);
		if(flag6==false) System.out.println("OD Start Date (From) didn't match expected data");
		boolean flag7=ruleing.TC_269(39,"OD Start Date (To)",num);
		if(flag7==false) System.out.println("OD Start Date (To) didn't match expected data");
		boolean flag8=ruleing.TC_269(40,"TP Start Date (From)",num);
		if(flag8==false) System.out.println("TP Start Date (From)didn't match expected data");
		boolean flag9=ruleing.TC_269(41,"TP Start Date (To)",num);
		if(flag9==false) System.out.println("TP Start Date (To) didn't match expected data");
		boolean flag10=ruleing.TC_269(42,"Policy Tenure",num);	
		if(flag10==false) System.out.println("Policy Tenture didn't match expected data");
	}
	@Test(priority=11)
	public void TC_310_to_318() throws InterruptedException, InvalidFormatException, IOException
	{
		int num=ruleing.approverulesheetclick();
		boolean flag1=ruleing.TC_269(43,"Product Category",num);
		if(flag1==false) System.out.println("Product Category didn't match expected data");
		boolean flag3=ruleing.TC_269(44,"GVW (From)",num);
		if(flag3==false) System.out.println("GVW (From) didn't match expected data");
		boolean flag4=ruleing.TC_269(45,"GVW (To)",num);
		if(flag4==false) System.out.println("GVW (To) didn't match expected data");
		boolean flag5=ruleing.TC_269(46,"OD (From)",num);
		if(flag5==false) System.out.println("OD (From) didn't match expected data");
		boolean flag6=ruleing.TC_269(47,"OD (To)",num);
		if(flag6==false) System.out.println("OD (To) didn't match expected data");
		boolean flag7=ruleing.TC_269(48,"TP (From)",num);
		if(flag7==false) System.out.println("TP (From) didn't match expected data");
		boolean flag8=ruleing.TC_269(49,"TP (To)",num);
		if(flag8==false) System.out.println("TP (From) didn't match expected data");
		boolean flag9=ruleing.TC_269(50,"Net (From)",num);
		if(flag9==false) System.out.println("Net (From) didn't match expected data");
		boolean flag10=ruleing.TC_269(51,"Net (To)",num);
		if(flag10==false) System.out.println("Net (To) didn't match expected data");
	}
	@Test(priority=12)
	public void TC_319_to_328() throws InterruptedException, InvalidFormatException, IOException
	{
		int num=ruleing.approverulesheetclick();
		boolean flag1=ruleing.TC_269(52,"IMD Code",num);
		if(flag1==false) System.out.println("IMD Code didn't match expected data");
		boolean flag2=ruleing.TC_269(53,"Basis",num);
		if(flag2==false) System.out.println("Basis didn't match expected data");
		boolean flag3=ruleing.TC_269(54,"OD Total",num);
		if(flag3==false) System.out.println("OD Total didn't match expected data");
		boolean flag4=ruleing.TC_269(55,"TP Total",num);
		if(flag4==false) System.out.println("TP Total didn't match expected data");
		boolean flag5=ruleing.TC_269(56,"Fixed Value Total",num);
		if(flag5==false) System.out.println("Fixed Value Total didn't match expected data");
		boolean flag6=ruleing.TC_269(57,"Basis 1",num);
		if(flag6==false) System.out.println("Basis 1 didn't match expected data");
		boolean flag7=ruleing.TC_269(58,"OD Rate 1 (Commission %)",num);
		if(flag7==false) System.out.println("OD Rate 1 (Commission %) didn't match expected data");
		boolean flag8=ruleing.TC_269(59,"TP Rate 1 (Commission %)",num);
		if(flag8==false) System.out.println("TP Rate 1 (Commission %) didn't match expected data");
		boolean flag9=ruleing.TC_269(60,"Fixed Value 1",num);
		if(flag9==false) System.out.println("Fixed Value 1 didn't match expected data");
		boolean flag10=ruleing.TC_269(61,"Basis 2",num);
		if(flag10==false) System.out.println("Basis 2  didn't match expected data");
	}
	@Test(priority=13)
	public void TC_329_to_332() throws InterruptedException, InvalidFormatException, IOException, ClassNotFoundException, SQLException
	{
		int num=ruleing.approverulesheetclick();
		boolean flag1=ruleing.TC_269(62,"OD Rate 2 (Commission %)",num);
		if(flag1==false) System.out.println("OD Rate 2 (Commission %) didn't match expected data");
		boolean flag2=ruleing.TC_269(63,"TP Rate 2 (Commission %)",num);
		if(flag2==false) System.out.println("TP Rate 2 (Commission %)  didn't match expected data");
		boolean flag3=ruleing.TC_269(64,"Fixed Value 2",num);
		if(flag3==false) System.out.println("Fixed Value 2  didn't match expected data");
		boolean flag4=ruleing.TC_269(65,"Comment",num);
		if(flag4==false) System.out.println("Comment didn't match expected data");
	}
	@Test(priority=14)
	public void TC_270() throws InterruptedException, InvalidFormatException, IOException
	{
		int num=ruleing.approverulesheetclick();
		boolean flag=ruleing.TC_270(3,num);
		if(flag==false) System.out.println("Product Name didn't match expected data");
	}
//	@Test(priority=15)
//	public void TC_333() throws InterruptedException, InvalidFormatException, IOException, ClassNotFoundException, SQLException
//	{
//		ruleing.Tc_333();
//	}
	@Test(priority=16)
	public void Tc_336() throws InterruptedException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		ruleing.rulesheetingestion("rule_sheet3.csv");
		Assert.assertTrue(ruleing.TC_336(),"Tc got failed as changing on page number does not change data.");
	}
	@Test(priority=17)
	public void Tc_337() throws InterruptedException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		ruleing.rulesheetingestion("rule_sheet3.csv");
		Assert.assertTrue(ruleing.TC_337(),"Tc got failed as changing on page number does not change data.");
	}
	@Test(priority=18)
	public void Tc_338() throws InterruptedException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		ruleing.rulesheetingestion("rule_sheet3.csv");
		Assert.assertTrue(ruleing.TC_338(),"Tc got failed as changing on page number does not change data.");
	}
	@Test(priority=19)
	public void Tc_260_261_262res1() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(ruleing.TC_260res1(),"Tc got failed as dropdown selection is not working");
	}
	@Test(priority=20)
	public void Tc_260_261_262res2n4() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(ruleing.TC_260res2(),"Tc got failed as dropdown selection is not showing expected result");
	}
	
	@Test(priority=21)
	public void Tc_260_261_262res5() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(ruleing.TC_260res5(),"Tc got failed as dropdown selection is not showing expected result");
		
	}
	@Test(priority=22)
	public void Tc_260_261_262res3() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(ruleing.TC_260res3(),"Tc got failed as dropdown selection is not showing expected result");
	}
	@Test(priority=23)
	public void TC_263res1() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(ruleing.TC_263res1(),"Tc got failed as RTO dropdown is unable to select value");
		
	}
	@Test(priority=24)
	public void Tc_263res2n4() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(ruleing.TC_263res2(),"Tc got failed as dropdown selection is not showing expected result");
	}
	@Test(priority=25)
	public void TC_263res6() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(ruleing.TC_263res6(),"Tc got failed as dropdown selection is not showing expected result");
	}
	@Test(priority=26)
	public void TC_265res1n2() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(ruleing.TC_265res1n2(),"Tc got failed as clear button doesn't work as expected");
	}

	@Test(priority=27)
	public void Tc_266() throws InterruptedException, IOException, InvalidFormatException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		ruleing.rulesheetingestion("rules_sheet2.csv");
		Assert.assertTrue(ruleing.Tc_266(),"Tc got failed as search txtbox is not trimming entered text");
	}
	
	@AfterMethod
	public void tearup()
	{
		driver.quit();
	}

}
