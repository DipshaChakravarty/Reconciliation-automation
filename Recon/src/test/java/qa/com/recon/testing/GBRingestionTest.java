package qa.com.recon.testing;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import java.sql.SQLException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.*;

import qa.com.recon.base.TestBase;
import qa.com.recon.logic.GBRingestion;
import qa.com.recon.logic.Loginrecon;

public class GBRingestionTest extends TestBase{

	public GBRingestionTest()
	{
		super();
		//urlconfig();
	}
	GBRingestion gbring=new GBRingestion();
	Loginrecon login=new Loginrecon();
	
	
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		intialization();
		login.login();
	}
	@Test
	public void Tc_385() throws InterruptedException, InvalidFormatException
	{
		Assert.assertTrue(gbring.Tc_385(),"TC_381 failed");
		
		
	}
	@Test
	public void Tc_386() throws InvalidFormatException
	{
		Assert.assertTrue(gbring.Tc_386(),"TC 382 failed as title is not as expected");
	}
	@Test
	public void Tc_387res1_2_3() throws InvalidFormatException
	{
		Assert.assertTrue(gbring.Tc_387res1(),"TC 383res1 failed as options are not as expected");
		Assert.assertTrue(gbring.Tc_387res2(),"TC 383res2 failed as default selection is not as expected");
		Assert.assertTrue(gbring.Tc_387res3(),"TC 383res3 failed as doesn't allow to select another option");
	}
	
	
	@Test
	public void TC_388res1_2() throws InterruptedException, InvalidFormatException
	{
		Assert.assertTrue(gbring.TC_388res1(),"TC_388res1 got failed");
		Assert.assertTrue(gbring.TC_388res2(),"TC_388res2 got failed");
	}
	@Test
	public void TC_388res3() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
		Assert.assertTrue(gbring.TC_388res3(),"TC_388res3 got failed");
	}
	
	@Test
	public void TC_389res1() throws InterruptedException, InvalidFormatException
	{
		Assert.assertTrue(gbring.TC_389res1(),"TC_389res1 got failed");
	}
	@Test
	public void TC_389res2_3() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_389res2(),"TC_389res2n3 got failed as ");
		
	}
	@Test
	public void TC_389res4n6() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_389res4n6(),"TC_389res4n6 got failed as ");
	}
	@Test
	public void TC_389res8part1() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_389res8part1(),"TC got failed as message is not as expected nor the YES button is displayed");
	}
	@Test
	public void TC_389res8part2() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_389res8part2(),"TC got failed as it should display error table.");
	}
	@Test
	public void TC_389res9() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_389res9(),"TC got failed as it shouln't display popup after clicking on cancel");
	}
	@Test
	public void TC_389res10() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_389res10(),"TC got failed as it didn't shown expected message.");
	}
	
	@Test
	public void TC_390res1() throws InvalidFormatException, InterruptedException
	{
		Assert.assertTrue(gbring.TC_390res1(),"TC got  failed as error message doesn't matched");
		
		
	}
	@Test
	public void TC_390res2() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_390res2(), "tc got failed as error message doesn't matched");
	}
	@Test
	public void TC_390res3() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_390res3(), "tc got failed as error message doesn't matched");
	}
	
	@Test
	public void TC_393res1() throws InterruptedException
	{
	    Assert.assertFalse(gbring.TC_393res1(),"Tc got failed as 3 elements needed to clear after clicking on clear");
	}
	
	@Test
	public void TC_394() throws InterruptedException, InvalidFormatException
	{
	   Assert.assertFalse(gbring.TC_394(),"Tc got failed as web data and excel data didn't match.");
	}
	@Test
	public void TC_389res11() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
			//Assert.assertFalse(gbring.TC_389res11(),"Tc got failed as data matched.");
	}
	@Test
	public void tc_396_402() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
	  Assert.assertTrue(gbring.tc_396_402(),"Tc got failed as valid gbr didn't get ingest.");
	}
	@Test
	public void tc_403() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
	  Assert.assertTrue(gbring.tc_403(),"Tc got failed as valid gbr didn't get ingest.");
	}
	
	String gbr_lifepath=System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\TrimRows_GBR_Life.xlsx";
//	@Test
//	public void tc_407gbr_life() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
//	{
//	 Assert.assertTrue(gbring.tc_407(gbr_lifepath),"Tc got failed as valid gbr didn't get ingest.");
//	}
//	String gbr_GIpath=System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\testdata\\trim_rows_GBR_ALL.xlsx";
//	@Test
//	public void tc_407gbr_GI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
//	{
//	 Assert.assertTrue(gbring.tc_407(gbr_GIpath),"Tc got failed as valid gbr didn't get ingest.");
//	}
	String valueerrorGI="\\src\\main\\java\\qa\\com\\recon\\testdata\\value_error_gbr_All.xlsx";
	String valueerrorLife="\\src\\main\\java\\qa\\com\\recon\\testdata\\value_error_Life.xlsx";
	@Test
	public void TC_391res1gbr_GI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
		Assert.assertTrue(gbring.TC_391res1(valueerrorGI),"TC got failed it didn't match to expected error message.");
	}
	@Test
	public void TC_391res1gbr_Life() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
		Assert.assertTrue(gbring.TC_391res1(valueerrorLife),"TC got failed it didn't match to expected error message.");
	}
	@Test
	public void TC_391res2_3gbr_GI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
		Assert.assertTrue(gbring.tc_391res2_3(valueerrorGI),"TC got failed it didn't match to expected error message.");
	}
	@Test
	public void TC_391res2_3gbr_Life() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
		Assert.assertTrue(gbring.tc_391res2_3(valueerrorLife),"TC got failed it didn't match to expected error message.");
	}
	String syspath =System.getProperty("user.dir");
	String gbr_gi392path1=syspath + "\\src\\main\\java\\qa\\com\\recon\\testdata\\GBRALL_SME_Dataset1.xlsx";
	String gbr_gi392path2=syspath +"\\src\\main\\java\\qa\\com\\recon\\testdata\\overwrite_all.xlsx";
	String gbr_gi392path3=syspath +"\\src\\main\\java\\qa\\com\\recon\\testdata\\value_error_gbr_GI_overwrite.xlsx";
	String gbr_gi392path4=syspath +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR_GI_Duplicate_inward_overwrite.xlsx";
	String gbr_Li392path1=syspath +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR Life -  Dataset 1.xlsx";
	String gbr_Li392path2=syspath +"\\src\\main\\java\\qa\\com\\recon\\testdata\\overwrite_Life.xlsx";
	String gbr_Li392path3=syspath +"\\src\\main\\java\\qa\\com\\recon\\testdata\\valu_error_Life_overwrite.xlsx";
	String gbr_Li392path4=syspath +"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR_Life_duplicate_inward_overwrite.xlsx";
	@Test
	public void tc_392gbr_GI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
	Assert.assertTrue(gbring.tc_392res1_2_3_5(gbr_gi392path1,gbr_gi392path2),"TC got failed data did not get overwrite");
	}
	
	@Test
	public void tc_392gbr_LI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
		Assert.assertTrue(gbring.tc_392res1_2_3_5(gbr_Li392path1,gbr_Li392path2),"TC got failed data did not get overwrite");
	}
	@Test
	public void tc_392res4gbr_GI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
	Assert.assertTrue(gbring.tc_392res4(gbr_gi392path1,gbr_gi392path3),"TC got failed data overwritten inspite of having value error");
	}
	@Test
	public void tc_392res4gbr_LI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
	Assert.assertTrue(gbring.tc_392res4(gbr_Li392path1,gbr_Li392path3),"TC got failed data overwritten inspite of having value error");
	}
	
	@Test
	public void tc_392res6gbr_GI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
	Assert.assertTrue(gbring.tc_392res6(gbr_gi392path1,gbr_gi392path4),"TC got failed data overwritten inspite of having value error");
	}
	@Test
	public void tc_392res6gbr_LI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
	Assert.assertTrue(gbring.tc_392res6(gbr_Li392path1,gbr_Li392path4),"TC got failed data overwritten inspite of having value error");
	}

	@Test
	public void TC_406res2gbr_GI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
	Assert.assertTrue(gbring.TC_406res2(syspath+"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR_all_mand_column_error.xlsx"),"TC got failed as errormessage does not match");
	}
	@Test
	public void TC_406res2gbr_LI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
	Assert.assertTrue(gbring.TC_406res2(syspath+"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR_Life_mand_column_error.xlsx"),"TC got failed as errormessage does not match");
	}
	
	@Test
	public void TC_406res3gbr_GI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
	Assert.assertTrue(gbring.TC_406res3(syspath+"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR_GI_mand_data_error.xlsx"),"TC got failed as errormessage does not match");
	}
	@Test
	public void TC_406res3gbr_LI() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
	Assert.assertTrue(gbring.TC_406res3(syspath+"\\src\\main\\java\\qa\\com\\recon\\testdata\\GBR_Life_mand_data_error.xlsx"),"TC got failed as errormessage does not match");
	}
	@AfterMethod
	public void tearup()
	{
		driver.quit();
		
	}
	
	
}
