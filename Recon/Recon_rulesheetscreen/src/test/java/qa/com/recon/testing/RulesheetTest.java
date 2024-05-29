package qa.com.recon.testing;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import qa.com.recon.base.TestBase;
import qa.com.recon.logic.CARreport;
import qa.com.recon.logic.Loginrecon;
import qa.com.recon.logic.Rulesheetingestion;

public class RulesheetTest extends TestBase {
	
	public RulesheetTest() throws CsvValidationException, IOException
	{
		super();
		urlconfig();
		/*
		 * chooseafile(); chooseafile1(); chooseafile2();
		 */
		
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
	
	
	@Test
	public void Sit1_2_3_7_22_23() throws Exception
	{
		Assert.assertTrue(ruleing.Upload_Rule_sheet(),"Testcase is fail");    

	}
	@Test
	public void Sit_8_9_10_16_11() throws Exception
	{
		Assert.assertTrue(ruleing.Check_Choose_Button(),"Testcase is fail");    

	}

	@Test
	public void Sit_4_13_14() throws Exception
	{
		Assert.assertTrue(ruleing.Valid_Dublicate(),"Testcase is fail");    
	}
	@Test
	public void StartEditPage() throws Exception{
		ruleing.tc_expe();
	}

	@AfterMethod
	public void tearup()
	{
		driver.quit();
	}

}
