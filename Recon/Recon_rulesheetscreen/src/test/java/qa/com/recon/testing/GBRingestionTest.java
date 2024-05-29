package qa.com.recon.testing;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

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
	//	urlconfig();
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
	public void tc383() throws InterruptedException, InvalidFormatException
	{
		Assert.assertTrue(gbring.Tc_381(),"TC_381 failed ");
		
		
	}
	@Test
	public void tc384() throws InvalidFormatException
	{
		Assert.assertTrue(gbring.Tc_382(),"TC 382 failed as title is not as expected");
	}
	@Test
	public void tc385() throws InvalidFormatException
	{
		Assert.assertTrue(gbring.Tc_383res1(),"TC 383res1 failed as options are not as expected");
		Assert.assertTrue(gbring.Tc_383res2(),"TC 383res2 failed as default selection is not as expected");
		Assert.assertTrue(gbring.Tc_383res3(),"TC 383res3 failed as doesn't allow to select another option");
	}
	
	
	@Test
	public void tc386() throws InterruptedException, InvalidFormatException
	{
		gbring.TC_384res1();
		gbring.TC_384res2();
	}
	
	
	@Test
	public void tc387res1() throws InterruptedException, InvalidFormatException
	{
		gbring.TC_387res1();
		
		
	}
	@Test
	public void tc387res4n6() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_387res4n6(),"TC got failed as message is not as expected");
		
	}
	@Test
	public void tc387res8part1() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_387res8part1(),"TC got failed as message is not as expected nor the YES button is displayed");
	}
	@Test
	public void tc387res8part2() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_387res8part2(),"TC got failed as it should display error table.");
	}
	@Test
	public void tc387res9() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_387res9(),"TC got failed as it shouln't display popup after clicking on cancel");
	}
	@Test
	public void tc387res10() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_387res10(),"TC got failed as it didn't shown expected message.");
	}
	
	@Test
	public void Tc388res1() throws InvalidFormatException, InterruptedException
	{
		Assert.assertTrue(gbring.TC_388res1(),"TC got  failed as error message doesn't matched");
		
		
	}
	@Test
	public void tc388res2() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_388res2(), "tc got failed as error message doesn't matched");
	}
	@Test
	public void TC_388res3() throws InterruptedException, InvalidFormatException
	{
		 Assert.assertTrue(gbring.TC_388res3(), "tc got failed as error message doesn't matched");
	}
	
	
	@Test
	public void Tc391res1() throws InterruptedException
	{
	    Assert.assertFalse(gbring.TC_391res1(),"Tc got failed as 3 elements needed to clear after clicking on clear");
	}
	
	@Test
	public void gbringestion() throws InterruptedException
	{
		
		//gbring.gbringestion1();
	}
	
	
	@AfterMethod
	public void tearup()
	{
		driver.quit();
	}
	
	
}
