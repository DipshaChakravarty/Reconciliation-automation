package qa.com.recon.testing;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.BeforeMethod;

import qa.com.recon.base.TestBase;
import qa.com.recon.logic.CARreport;
import qa.com.recon.logic.Loginrecon;

public class CARreportTest extends TestBase{

	
	public CARreportTest()
	{
		super();
	}
	
	Loginrecon login=new Loginrecon();
	CARreport carreport=new CARreport();
	@BeforeMethod
	public void setup() throws InterruptedException
	{
		intialization();
		login.login();
	}
	@Test
	public void ViewCAR() throws Exception
	{
		Assert.assertTrue(carreport.viewcarreport(),"TC got failed as totalrow is not visible");
	}
	@Test
	public void DownloadCAR() throws InterruptedException
	{
		Assert.assertTrue(carreport.downloadcar(),"TC got failed as successmsg is not visible");
	}
	
	
	
	@AfterMethod
	public void tearup()
	{
		driver.quit();
	}
	
}
