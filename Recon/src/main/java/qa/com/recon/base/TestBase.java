package qa.com.recon.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import qa.com.recon.utility.TestUtil;

public class TestBase {

	public static Properties prop ;
	 
	public TestBase() {
		
		try {

			 prop = new Properties();
			String configpath= System.getProperty("user.dir")+"\\src\\main\\java\\qa\\com\\recon\\logic\\config.properties";
		//	URL url11 = getClass().getResource("config.properties");
			FileInputStream ip = new FileInputStream(configpath);

			prop.load(ip);

		} catch (IOException e) {
			// System.out.println("Exception raised in io... " + e + count);
		} catch (Exception e) {

			// System.out.println("Main exception raised ... " +e + count);
		}
		
	}

	public static String url;
	public static void urlconfig() {
		url = JOptionPane.showInputDialog(null, "Enter Url");
		
	}
	 	
	    public static String path=null;
	    public static String path1=null;
	    public static String path2=null;
	    public static List<String> data=null;
	   
		public static File selectedFile = null;
		public static File selectedFile1 = null;
		public static File selectedFile2 = null;
		
	    public void chooseafile() throws CsvValidationException, IOException {
		
	    JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			selectedFile = chooser.getSelectedFile();
			path = selectedFile.getPath();
			System.out.println(selectedFile + " is selected." + path + " path to be used");
			}
	    }
		public void chooseafile1() throws CsvValidationException, IOException {
			
		    JFileChooser chooser = new JFileChooser();
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				selectedFile1 = chooser.getSelectedFile();
				path1 = selectedFile1.getPath();
				System.out.println(selectedFile1 + " is selected." + path1 + " path to be used");
			}

	    }public void chooseafile2() throws CsvValidationException, IOException {
			
		    JFileChooser chooser = new JFileChooser();
			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				selectedFile2 = chooser.getSelectedFile();
				path2 = selectedFile2.getPath();
				System.out.println(selectedFile2 + " is selected." + path2 + " path to be used");
			}

	    }

		public static WebDriver driver;
		public static void intialization() {
		 
		String path = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", path + "\\Driver\\chromedriver.exe");
		
		 HashMap<String, Object> prefs = new HashMap<String, Object>();
		 prefs.put("download.default_directory",System.getProperty("user.dir") +"\\downloadedexcel\\");   
		 ChromeOptions  options = new ChromeOptions();
		 options.setExperimentalOption("prefs", prefs);
		
		 
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.Page_Load_Timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.Implicit_Wait, TimeUnit.SECONDS);

		try {
			

			// System.out.println("Where is the link?");
		//	System.out.println("about to load the poroperty --> " + url);
			driver.get("http://ec2-3-111-148-158.ap-south-1.compute.amazonaws.com:90/");

		} catch (Exception e) {
			System.out.println("exception in loading --> " + e);
		}
	}

}
