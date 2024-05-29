package qa.com.recon.logic;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.com.recon.base.TestBase;
import qa.com.recon.utility.TestUtil;

public class Loginrecon extends TestBase{

	By mobiletextbx=By.id("mobileno");
	By otpbtn=By.id("btnotp");
	By otptxtbx=By.id("txtotp");
	By Verifybtn=By.id("btnverify");
	
	public void login() throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(mobiletextbx).sendKeys("6355758381");
		Thread.sleep(1000);
		driver.findElement(otpbtn).click();
		Thread.sleep(10000);
		String otp=TestUtil.getotp();
		
		driver.findElement(otptxtbx).sendKeys(otp);
		Thread.sleep(1000);
		driver.findElement(Verifybtn).click();
		
	}
	
	
	
}
