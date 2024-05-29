package qa.com.recon.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mule.el.datetime.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import qa.com.recon.base.TestBase;
import qa.com.recon.utility.TestUtil;

public class CARreport extends TestBase {

	By caricon = By.xpath("//i[@class='fa fa-automobile']");
	By cardashboardclick = By.xpath("//a[@href='/CarDashboards']");
	By summarytab = By.xpath("//*[@id=\"Summary-tab\"]");
	By detailstab = By.xpath("//a[@id='Details-tab']");// a[contains(text(),'Details')]
	By fromdate = By.id("FromDate");
	By Todate = By.id("ToDate");
	By calendarmonth = By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/select[1]");
	By calendaryear = By.xpath("//select[@data-handler='selectYear']");//// *[@id="ui-datepicker-div"]/div[1]/div/select[2]
	By calendardates = By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr/td");
	By Viewbtn = By.id("btnViewDetailsReport");
	By entriesdropdown = By.xpath("//select[@name='PospListDataTable_length']");
	By downlodbtn = By.xpath("//*[@id=\"btnDownloadReport\"]");
	By columnread = By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr/td[3][contains(text(),'" + 1 + "')]");
	By ruleconditioncolumn = By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr/td[138][contains(text(),'6')]");
	By totalrow = By.xpath("//th[contains(text(),'Total :')]");
	By infobox = By.xpath("/html/body/div[3]/div");
	By errortitle = By.xpath("/html/body/div[2]/div/h2");
	By Clearbutton = By.xpath("//*[@id=\"Details\"]/div/div[3]/div/div/button[3]");
	By showentriesdrpdown = By.name("PospListDataTable_length");
	By Searchtxtbx = By.xpath("//*[@id=\"PospListDataTable_filter\"]/label/input");
	By Errorbox = By.xpath("/html/body/div[3]/div");

	public WebDriverWait wait;

	public void datefilter() throws InterruptedException {
		driver.findElement(caricon).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(detailstab));
		driver.findElement(detailstab).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(fromdate));

		driver.findElement(fromdate).click();
		driver.findElement(calendarmonth).click();
		Select monthoptions = new Select(driver.findElement(calendarmonth));
		monthoptions.selectByIndex(1);

		Select yearoptions = new Select(driver.findElement(calendaryear));
		yearoptions.selectByIndex(1);

		driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td/a[contains(text(),'1')]"))
				.click();

//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		 Date date = new Date();
//		 String expdate1= dateFormat.format(date);
//		 System.out.println(expdate1);
//		driver.findElement(Todate).sendKeys(expdate1);
//		String actualdate=driver.findElement(Todate).getText();
	}

	public boolean viewcarreport() throws InterruptedException {
		datefilter();
		driver.findElement(Viewbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(totalrow));
		return driver.findElement(totalrow).isDisplayed();
	}

	public void downloadcar() throws InterruptedException {
		datefilter();
		Thread.sleep(2000);
		driver.findElement(downlodbtn).click();
		Thread.sleep(1000);
		// wait.until(ExpectedConditions.presenceOfElementLocated(infobox));
//		String msg=null;
//		if(driver.findElement(infobox).isDisplayed()) {
//		 msg=driver.findElement(infobox).getText();
//		}else {
//			System.out.println("errormsg is not displayed");
//		}
//		return msg.contains("Success");
	}

	public static String downloadedCAR() {
		File directory = new File(System.getProperty("user.dir") + "\\downloadedexcel\\");
		File[] files = directory.listFiles(File::isFile);
		long lastModifiedTime = Long.MIN_VALUE;
		File chosenFile = null;
		String pathtobeused = null;

		if (files != null) {
			for (File file : files) {
				if (file.lastModified() > lastModifiedTime) {
					chosenFile = file;
					pathtobeused = chosenFile.getPath();
					lastModifiedTime = file.lastModified();
				}
			}
		}
		// System.out.println("choosen file is: " + chosenFile + "lastmodifiedtime is :
		// " + lastModifiedTime);
		return pathtobeused;
	}

	public boolean CARcomparison() throws InterruptedException {
		Workbook book1 = null;
		Workbook book2 = null;
		Sheet sheet = null;
		Sheet sheet1 = null;
		String path = downloadedCAR();
		//System.out.println("path of system generated CAR excel : " + path);
		FileInputStream file1 = null;
		FileInputStream file2 = null;

		try {
			file1 = new FileInputStream(path);
			file2 = new FileInputStream(System.getProperty("user.dir") + "\\CARcalculation.xlsx");
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book1 = WorkbookFactory.create(file1);
			book2 = WorkbookFactory.create(file2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<MyObject> myObjList1 = new ArrayList<MyObject>();
		List<MyObject> myObjList2 = new ArrayList<MyObject>();

		sheet = book1.getSheetAt(0);
		sheet1 = book2.getSheetAt(0);

		String inwardno1 = "";

		Map<String, Integer> map = new HashMap<String, Integer>(); // Create map
		Row row = sheet.getRow(0); // Get first row

		// following is boilerplate from the java doc
		short minColIx = row.getFirstCellNum(); // get the first column index for a row
		short maxColIx = row.getLastCellNum(); // get the last column index for a row
		for (short colIx = minColIx; colIx < maxColIx; colIx++) { // loop from first to last index
			Cell cell = row.getCell(colIx); // get the cell
			map.put(cell.getStringCellValue(), cell.getColumnIndex()); // add the cell contents (name of column) and
																		// cell index to the map
		}
		System.out.println("downloaded car row no " + sheet.getLastRowNum());

		for (int i = 0; i < sheet.getLastRowNum() - 1; i++) {
			Row row1 = sheet.getRow(i + 1);
			int idxForColumn1 = map.get("Inward_No");
			int idxForColumn2 = map.get("Rule_Reference");
			int idxForColumn3 = map.get("Rule_condition");
			int idxForColumn4 = map.get("OD_total");
			int idxForColumn5 = map.get("TP_total");
			int idxForColumn6 = map.get("Fixed_value_total");
			int idxForColumn7 = map.get("Total_Value");
			int idxForColumn8 = map.get("OD_rate_1");
			int idxForColumn9 = map.get("TP_rate_1");
			int idxForColumn10 = map.get("Fixed_value_1");
			int idxForColumn11 = map.get("ValueA");
			int idxForColumn12 = map.get("OD_rate_2");
			int idxForColumn13 = map.get("TP_rate_2");
			int idxForColumn14 = map.get("Fixed_value_2");
			int idxForColumn15 = map.get("ValueB");

			Cell inwardcell1 = row1.getCell(idxForColumn1);

			if (inwardcell1 != null) {

				inwardcell1.setCellType(CellType.STRING);

				Row inwardcellrow = inwardcell1.getRow();

				Cell Rule_refcell1 = inwardcellrow.getCell(idxForColumn2);
				Cell Rule_conditioncell1 = inwardcellrow.getCell(idxForColumn3);
				Cell OD_totalcell1 = inwardcellrow.getCell(idxForColumn4);
				Cell TP_totalcell1 = inwardcellrow.getCell(idxForColumn5);
				Cell Fixed_value_totalcell1 = inwardcellrow.getCell(idxForColumn6);
				Cell Total_Valuecell1 = inwardcellrow.getCell(idxForColumn7);
				Cell OD_rate_1cell1 = inwardcellrow.getCell(idxForColumn8);
				Cell TP_rate_1cell1 = inwardcellrow.getCell(idxForColumn9);
				Cell Fixed_value_1cell1 = inwardcellrow.getCell(idxForColumn10);
				Cell ValueAcell1 = inwardcellrow.getCell(idxForColumn11);
				Cell OD_rate_2cell1 = inwardcellrow.getCell(idxForColumn12);
				Cell TP_rate_2cell1 = inwardcellrow.getCell(idxForColumn13);
				Cell Fixed_value_2cell1 = inwardcellrow.getCell(idxForColumn14);
				Cell ValueBcell1 = inwardcellrow.getCell(idxForColumn15);

				Rule_refcell1.setCellType(CellType.STRING);
				Rule_conditioncell1.setCellType(CellType.STRING);
				OD_totalcell1.setCellType(CellType.STRING);
				TP_totalcell1.setCellType(CellType.STRING);
				Fixed_value_totalcell1.setCellType(CellType.STRING);
				Total_Valuecell1.setCellType(CellType.STRING);
				OD_rate_1cell1.setCellType(CellType.STRING);
				TP_rate_1cell1.setCellType(CellType.STRING);
				Fixed_value_1cell1.setCellType(CellType.STRING);
				ValueAcell1.setCellType(CellType.STRING);
				OD_rate_2cell1.setCellType(CellType.STRING);
				TP_rate_2cell1.setCellType(CellType.STRING);
				Fixed_value_2cell1.setCellType(CellType.STRING);
				ValueBcell1.setCellType(CellType.STRING);

				inwardno1 = inwardcell1.getStringCellValue();
				// String rulecond1;
				// System.out.println("inwardsheet 1 is " + inwardno1);
				String ruleref1 = Rule_refcell1.getStringCellValue();
				String rulecond1 = Rule_conditioncell1.getStringCellValue();

				String odtotal1 = OD_totalcell1.getStringCellValue();
				String tptotal1 = TP_totalcell1.getStringCellValue();
				String fixedvaluetotal = Fixed_value_totalcell1.getStringCellValue();
				String totalvalue = Total_Valuecell1.getStringCellValue();
				String odrate1 = OD_rate_1cell1.getStringCellValue();
				String tprate1 = TP_rate_1cell1.getStringCellValue();
				String fixedvalue1 = Fixed_value_1cell1.getStringCellValue();
				String valuea = ValueAcell1.getStringCellValue();
				String odrate2 = OD_rate_2cell1.getStringCellValue();
				String tprate2 = TP_rate_2cell1.getStringCellValue();
				String fixedvalue2 = Fixed_value_2cell1.getStringCellValue();
				String valueb = ValueBcell1.getStringCellValue();

				MyObject rulesheet1 = new MyObject();
				rulesheet1.inwardno = inwardno1;
				rulesheet1.rule_refrence = ruleref1;
				rulesheet1.rule_condition = rulecond1;
				rulesheet1.od_total = odtotal1;
				rulesheet1.tp_total = tptotal1;
				rulesheet1.fixed_value_total = fixedvaluetotal;
				rulesheet1.Total_Value = totalvalue;
				rulesheet1.OD_rate_1 = odrate1;
				rulesheet1.TP_rate_1 = tprate1;
				rulesheet1.Fixed_value_1 = fixedvalue1;
				rulesheet1.ValueA = valuea;
				rulesheet1.OD_rate_2 = odrate2;
				rulesheet1.TP_rate_2 = tprate2;
				rulesheet1.Fixed_value_2 = fixedvalue2;
				rulesheet1.ValueB = valueb;

				myObjList1.add(rulesheet1);

			} else {
				System.out.println("didn't find data in sheet1");
			}

		}
		System.out.println("calculated CAR row no" + sheet1.getPhysicalNumberOfRows());
		for (int j = 0; j < sheet1.getPhysicalNumberOfRows() - 1; j++) {

			Row row2 = sheet1.getRow(j + 1);

//						System.out.println(sheet1.getRow(0).getLastCellNum());
//						
//						for(int f=0;f<sheet1.getRow(0).getLastCellNum()-1;f++)
//						{
//							Cell cellsheet2=row2.getCell(f+1);
//							cellsheet2.setCellType(CellType.STRING);
//							cellvalue=cellsheet2.getStringCellValue();
//							MyObject rulesheet2=new MyObject();
//							rulesheet2.inwardno=cellvalue;
//							myObjList2.add(rulesheet2);
//						}	
			Cell inwardcell2 = row2.getCell(0);
			Cell Rule_refcell2 = row2.getCell(1);
			Cell Rule_conditioncell2 = row2.getCell(2);
			Cell OD_totalcell2 = row2.getCell(3);
			Cell TP_totalcell2 = row2.getCell(4);
			Cell Fixed_value_totalcell2 = row2.getCell(5);
			Cell Total_Valuecell2 = row2.getCell(6);
			Cell OD_rate_1cell2 = row2.getCell(7);
			Cell TP_rate_1cell2 = row2.getCell(8);
			Cell Fixed_value_1cell2 = row2.getCell(9);
			Cell ValueAcell2 = row2.getCell(10);
			Cell OD_rate_2cell2 = row2.getCell(11);
			Cell TP_rate_2cell2 = row2.getCell(12);
			Cell Fixed_value_2cell2 = row2.getCell(13);
			Cell ValueBcell2 = row2.getCell(14);

			List<Cell> cellarray = new ArrayList<Cell>();

			if (inwardcell2 != null) {
				inwardcell2.setCellType(CellType.STRING);
				Rule_refcell2.setCellType(CellType.STRING);
				Rule_conditioncell2.setCellType(CellType.STRING);
				OD_totalcell2.setCellType(CellType.STRING);
				TP_totalcell2.setCellType(CellType.STRING);
				Fixed_value_totalcell2.setCellType(CellType.STRING);
				Total_Valuecell2.setCellType(CellType.STRING);
				OD_rate_1cell2.setCellType(CellType.STRING);
				TP_rate_1cell2.setCellType(CellType.STRING);
				Fixed_value_1cell2.setCellType(CellType.STRING);
				ValueAcell2.setCellType(CellType.STRING);
				OD_rate_2cell2.setCellType(CellType.STRING);
				TP_rate_2cell2.setCellType(CellType.STRING);
				Fixed_value_2cell2.setCellType(CellType.STRING);
				ValueBcell2.setCellType(CellType.STRING);
				String rule_ref2 = null;
				String rule_cond2 = null;
				String od_total = null;
				String tp_total = null;
				String fixed_value_total = null;
				String total_value = null;
				String od_rate1 = null;
				String tp_rate1 = null;
				String fixed_value1 = null;
				String valuea = null;
				String odrate2 = null;
				String tprate2 = null;
				String fixedvalue2 = null;
				String valueb = null;

				String inwardno2 = inwardcell2.getStringCellValue();
				// System.out.println("inwardsheet 2 is " + inwardno2);
				String v = Rule_refcell2.getStringCellValue();
				String x = Rule_conditioncell2.getStringCellValue();
				if (v.contains(".")) {
					rule_ref2 = v.split("\\.")[0];
				} else {
					rule_ref2 = v;
				}
				if (x.contains(".")) {
					rule_cond2 = x.split("\\.")[0];
				} else {
					rule_cond2 = x;
				}
				// System.out.println("rule_ref2 is " + rule_ref2 + "rule_cond2 is : " +
				// rule_cond2);
				String h = OD_totalcell2.getStringCellValue();

				if (h.contains("0.0")) {

					od_total = h.split("\\.")[0];
				} else if (h.contains(".0")) {
					od_total = h.split("\\.")[0];
				} else {
					od_total = h;
				}
				String c = TP_totalcell2.getStringCellValue();
				if (c.contains("0.0")) {
					tp_total = c.split("\\.")[0];
				} else if (c.contains(".0")) {
					tp_total = c.split("\\.")[0];
				} else {
					tp_total = c;
				}
				String k = Fixed_value_totalcell2.getStringCellValue();
				if (k.contains("0.0")) {
					fixed_value_total = k.split("\\.")[0];
				} else if (k.contains(".0")) {
					fixed_value_total = k.split("\\.")[0];
				} else {
					fixed_value_total = k;
				}
				String e = Total_Valuecell2.getStringCellValue();
				if (e.contains("0.0")) {
					total_value = e.split("\\.")[0];
				} else if (e.contains(".0")) {
					total_value = e.split("\\.")[0];
				} else {
					total_value = e;
				}
				String w = OD_rate_1cell2.getStringCellValue();
				if (w.contains("0.0")) {
					od_rate1 = w.split("\\.")[0];
				} else if (w.contains(".0")) {
					od_rate1 = w.split("\\.")[0];
				} else {
					od_rate1 = w;
				}
				String q = TP_rate_1cell2.getStringCellValue();
				if (q.contains("0.0")) {
					tp_rate1 = q.split("\\.")[0];
				} else if (q.contains(".0")) {
					tp_rate1 = q.split("\\.")[0];
				} else {
					tp_rate1 = q;
				}
				String v1 = Fixed_value_1cell2.getStringCellValue();
				if (v1.contains("0.0")) {
					fixed_value1 = v1.split("\\.")[0];
				} else if (v1.contains(".0")) {
					fixed_value1 = v1.split("\\.")[0];
				} else {
					fixed_value1 = v1;
				}
				String z = ValueAcell2.getStringCellValue();
				if (z.contains("0.0")) {
					valuea = z.split("\\.")[0];
				} else if (z.contains(".0")) {
					valuea = z.split("\\.")[0];
				} else {
					valuea = z;
				}
				String x1 = OD_rate_2cell2.getStringCellValue();
				if (x1.contains("0.0")) {
					odrate2 = x1.split("\\.")[0];
				} else if (x1.contains(".0")) {
					odrate2 = x1.split("\\.")[0];
				} else {
					odrate2 = x1;
				}
				String b = TP_rate_2cell2.getStringCellValue();
				if (b.contains("0.0")) {
					tprate2 = b.split("\\.")[0];
				} else if (b.contains(".0")) {
					tprate2 = b.split("\\.")[0];
				} else {
					tprate2 = b;
				}
				String n = Fixed_value_2cell2.getStringCellValue();
				if (n.contains("0.0")) {
					fixedvalue2 = n.split("\\.")[0];
				} else if (n.contains(".0")) {
					fixedvalue2 = n.split("\\.")[0];
				} else {
					fixedvalue2 = n;
				}
				String m = ValueBcell2.getStringCellValue();
				if (m.contains("0.0")) {
					valueb = m.split("\\.")[0];
				} else if (m.contains(".0")) {
					valueb = m.split("\\.")[0];
				} else {
					valueb = m;
				}

				MyObject rulesheet2 = new MyObject();
				rulesheet2.inwardno = inwardno2;
				rulesheet2.rule_refrence = rule_ref2;
				rulesheet2.rule_condition = rule_cond2;
				rulesheet2.od_total = od_total;
				rulesheet2.tp_total = tp_total;
				rulesheet2.fixed_value_total = fixed_value_total;
				rulesheet2.Total_Value = total_value;
				rulesheet2.OD_rate_1 = od_rate1;
				rulesheet2.TP_rate_1 = tp_rate1;
				rulesheet2.Fixed_value_1 = fixed_value1;
				rulesheet2.ValueA = valuea;
				rulesheet2.OD_rate_2 = odrate2;
				rulesheet2.TP_rate_2 = tprate2;
				rulesheet2.Fixed_value_2 = fixedvalue2;
				rulesheet2.ValueB = valueb;

				myObjList2.add(rulesheet2);

			} else {
				System.out.println("didn't find data in sheet2");
			}

		}

		boolean flag = false;
		// System.out.println("mylist 2 is : " + myObjList2 +"myObjList2 " +
		// myObjList2);
		List<String> expdatalist = new ArrayList<String>();
		List<String> actualdatalist = new ArrayList<String>();
		for (int d = 0; d < myObjList2.size(); d++) {
			// System.out.println("siz2 :" +myObjList2.size());
			String inwardnosheet2 = myObjList2.get(d).inwardno.toString();
			// System.out.println("inwardnosheet2");
			String rulerefsheet2 = myObjList2.get(d).rule_refrence.toString();
			String rulecondsheet2 = myObjList2.get(d).rule_condition.toString();
			String odtotalsheet2 = myObjList2.get(d).od_total.toString();
			String tptotalsheet2 = myObjList2.get(d).tp_total.toString();
			String fixedvaluetotalsheet2 = myObjList2.get(d).fixed_value_total.toString();
			String totalvaluesheet2 = myObjList2.get(d).Total_Value.toString();
			String odrate1sheet2 = myObjList2.get(d).OD_rate_1.toString();
			String tprate1sheet2 = myObjList2.get(d).TP_rate_1.toString();
			String fixedvalue1sheet2 = myObjList2.get(d).Fixed_value_1.toString();
			String valueasheet2 = myObjList2.get(d).ValueA.toString();
			String odrate2sheet2 = myObjList2.get(d).OD_rate_2.toString();
			String tprate2sheet2 = myObjList2.get(d).TP_rate_2.toString();
			String fixedvalue2sheet2 = myObjList2.get(d).Fixed_value_2.toString();
			String valuebsheet2 = myObjList2.get(d).ValueB.toString();

			// System.out.println(" sheet 2 data : " + inwardnosheet2 + ", "+rulerefsheet2
			// );

			for (int l = 0; l < myObjList1.size(); l++) {
				// System.out.println("size1 :" +myObjList1.size());
				String inwardnosheet1 = myObjList1.get(l).inwardno.toString();
				String rulerefsheet1 = myObjList1.get(l).rule_refrence.toString();
				String rulecondsheet1 = myObjList1.get(l).rule_condition.toString();
				String odtotalsheet1 = myObjList1.get(l).od_total.toString();
				String tptotalsheet1 = myObjList1.get(l).tp_total.toString();
				String fixedvaluetotalsheet1 = myObjList1.get(l).fixed_value_total.toString();
				String totalvaluesheet1 = myObjList1.get(l).Total_Value.toString();
				String odrate1sheet1 = myObjList1.get(l).OD_rate_1.toString();
				String tprate1sheet1 = myObjList1.get(l).TP_rate_1.toString();
				String fixedvalue1sheet1 = myObjList1.get(l).Fixed_value_1.toString();
				String valueasheet1 = myObjList1.get(l).ValueA.toString();
				String odrate2sheet1 = myObjList1.get(l).OD_rate_2.toString();
				String tprate2sheet1 = myObjList1.get(l).TP_rate_2.toString();
				String fixedvalue2sheet1 = myObjList1.get(l).Fixed_value_2.toString();
				String valuebsheet1 = myObjList1.get(l).ValueB.toString();
				// System.out.println(" sheet1 data : " + inwardnosheet1 + ", "+rulerefsheet1 );
			
				if (inwardnosheet2.equals(inwardnosheet1) && rulerefsheet2.equals(rulerefsheet1)
						&& rulecondsheet2.equals(rulecondsheet1) && odtotalsheet2.equals(odtotalsheet1)
						&& tptotalsheet2.equals(tptotalsheet1) && fixedvaluetotalsheet2.equals(fixedvaluetotalsheet1)
						&& totalvaluesheet2.equals(totalvaluesheet1) && odrate1sheet2.equals(odrate1sheet1)
						&& tprate1sheet2.equals(tprate1sheet1) && fixedvalue1sheet2.equals(fixedvalue1sheet1)
						&& valueasheet2.equals(valueasheet1) && odrate2sheet2.equals(odrate2sheet1)
						&& tprate2sheet2.equals(tprate2sheet1) && fixedvalue2sheet2.equals(fixedvalue2sheet1)
						&& valuebsheet2.equals(valuebsheet1)) {

					flag = inwardnosheet2.equals(inwardnosheet1) && rulerefsheet2.equals(rulerefsheet1)
							&& rulecondsheet2.equals(rulecondsheet1) && odtotalsheet2.equals(odtotalsheet1)
							&& tptotalsheet2.equals(tptotalsheet1)
							&& fixedvaluetotalsheet2.equals(fixedvaluetotalsheet1)
							&& totalvaluesheet2.equals(totalvaluesheet1) && odrate1sheet2.equals(odrate1sheet1)
							&& tprate1sheet2.equals(tprate1sheet1) && fixedvalue1sheet2.equals(fixedvalue1sheet1)
							&& valueasheet2.equals(valueasheet1) && odrate2sheet2.equals(odrate2sheet1)
							&& tprate2sheet2.equals(tprate2sheet1) && fixedvalue2sheet2.equals(fixedvalue2sheet1)
							&& valuebsheet2.equals(valuebsheet1);
					// System.out.println("flag is " + flag);
					actualdatalist.add(inwardnosheet1);
					expdatalist.add(inwardnosheet2);

					System.out.println("If part sheet 2 data : " + inwardnosheet2 + ", " + rulerefsheet2 + ","
							+ odtotalsheet2 + "," + rulecondsheet2 + tptotalsheet2 + fixedvaluetotalsheet2
							+ odrate1sheet2 + tprate1sheet2 + fixedvalue1sheet2 + valueasheet2 + odrate2sheet2
							+ tprate2sheet2 + fixedvalue2sheet2 + valuebsheet2 + "  sheet1 data: " + inwardnosheet1
							+ ", " + rulerefsheet1 + "," + odtotalsheet1 + "," + rulecondsheet1 + tptotalsheet1
							+ fixedvaluetotalsheet1 + odrate1sheet1 + tprate1sheet1 + fixedvalue1sheet1 + valueasheet1
							+ odrate2sheet1 + tprate2sheet1 + fixedvalue2sheet1 + valuebsheet1);
					System.out.println("Data matched");

					break;
				} else {
//					System.out.println("else part sheet 2 data : " + inwardnosheet2 + ", " + rulerefsheet2 + ","
//							+ odtotalsheet2 + "," + rulecondsheet2 + tptotalsheet2 + fixedvaluetotalsheet2
//							+ odrate1sheet2 + tprate1sheet2 + fixedvalue1sheet2 + valueasheet2 + odrate2sheet2
//							+ tprate2sheet2 + fixedvalue2sheet2 + valuebsheet2 + "  sheet1 data: " + inwardnosheet1
//							+ ", " + rulerefsheet1 + "," + odtotalsheet1 + "," + rulecondsheet1 + tptotalsheet1
//							+ fixedvaluetotalsheet1 + odrate1sheet1 + tprate1sheet1 + fixedvalue1sheet1 + valueasheet1
//							+ odrate2sheet1 + tprate2sheet1 + fixedvalue2sheet1 + valuebsheet1);
				}
			}
		}

//					 List<String> union = new ArrayList<String>(actualdatalist);
//						union.addAll(expdatalist);
//						List<String> union1 = new ArrayList<String>(actualdatalist);
//						union1.retainAll(expdatalist);
//					//	System.out.println("1st union " +union + "union1 " + union1);
//						union.removeAll(union1);
//						
//							
//						System.out.println("union " +union + "union1 " + union1);
////						
//						List<String> unionactual = new ArrayList<String>(myObjList2);
//						unionactual.addAll(expdatalist);
//						List<String> union1actual = new ArrayList<String>(actualdatalisttrimmed);
//						union1actual.retainAll(expdatalist);
//						unionactual.removeAll(union1actual); 
//					 

		return flag;

	}

	public void CARcalculation() throws ClassNotFoundException, SQLException, InterruptedException, IOException {
		// List<String> ruleref=main1();
		org.apache.poi.ss.usermodel.Workbook book = null;
		Sheet sheet = null;
	//	System.out.println("path of mapping sheet: " + path2);
		FileInputStream file = null;
		try {
			file = new FileInputStream(System.getProperty("user.dir")+ "\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\mapping_sheet.xlsx");
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheetAt(0);

		// System.out.println(ruleref);
		String dbURL = prop.getProperty("dburl");
		String username = prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);

		Statement st = con.createStatement();
		Statement st1 = con.createStatement();
		String selectquery = null;
		String gbrquery = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		int rowNumber = 0;

		XSSFWorkbook workbook1 = new XSSFWorkbook();
		XSSFSheet Sheet1 = workbook1.createSheet("CAR");
		String CARpath;
		FileOutputStream fos = null;
//		System.out.println("last row number is : " +sheet.getLastRowNum());
//		System.out.println("physical row number is : " +sheet.getPhysicalNumberOfRows());

		String[] colheadings = { "Inward_No", "Rule_ref", "Rule_condition", "OD_total", "TP_total", "Fixed_value_total",
				"Total_Value", "OD_rate_1", "TP_rate_1", "Fixed_value_1", "ValueA", "OD_rate_2", "TP_rate_2",
				"Fixed_value_2", "ValueB" };
		CellStyle style = null;
		// Creating a font
		XSSFFont font = workbook1.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Arial");
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(true);
		font.setItalic(false);
		style = workbook1.createCellStyle();
		style.setFont(font);
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);

		Row headerRow = Sheet1.createRow(rowNumber);
		for (int s = 0; s < colheadings.length; s++) {
			Cell headercell6 = headerRow.createCell(s);
			headercell6.setCellValue(colheadings[s]);
			headercell6.setCellStyle(style);
			Sheet1.autoSizeColumn(s);
		}

		for (int i = 0; i < sheet.getLastRowNum(); i++) {

			String inward = sheet.getRow(i + 1).getCell(0).toString();
			String rule_ref = sheet.getRow(i + 1).getCell(1).toString();
			//System.out.println("inward is:" + inward);
			//System.out.println("rule_ref is:" + rule_ref);
			// long v = (long) rule_ref;

			rowNumber = rowNumber + 1;

			XSSFRow row2 = Sheet1.createRow(rowNumber);

			Cell inwardcell2 = row2.createCell(0);
			Cell Rule_refcell2 = row2.createCell(1);
			Cell Rule_conditioncell2 = row2.createCell(2);
			Cell OD_totalcell2 = row2.createCell(3);
			Cell TP_totalcell2 = row2.createCell(4);
			Cell Fixed_value_totalcell2 = row2.createCell(5);
			Cell Total_Valuecell2 = row2.createCell(6);
			Cell OD_rate_1cell2 = row2.createCell(7);
			Cell TP_rate_1cell2 = row2.createCell(8);
			Cell Fixed_value_1cell2 = row2.createCell(9);
			Cell ValueAcell2 = row2.createCell(10);
			Cell OD_rate_2cell2 = row2.createCell(11);
			Cell TP_rate_2cell2 = row2.createCell(12);
			Cell Fixed_value_2cell2 = row2.createCell(13);
			Cell ValueBcell2 = row2.createCell(14);

			if (rule_ref == "") {

				inwardcell2.setCellValue(inward);
				Rule_refcell2.setCellValue("");
				Rule_conditioncell2.setCellValue(0);
				OD_totalcell2.setCellValue(0);
				TP_totalcell2.setCellValue(0);
				Fixed_value_totalcell2.setCellValue(0);
				Total_Valuecell2.setCellValue(0);
				OD_rate_1cell2.setCellValue(0);
				TP_rate_1cell2.setCellValue(0);
				Fixed_value_1cell2.setCellValue(0);
				ValueAcell2.setCellValue(0);
				OD_rate_2cell2.setCellValue(0);
				TP_rate_2cell2.setCellValue(0);
				Fixed_value_2cell2.setCellValue(0);
				ValueBcell2.setCellValue(0);

				CARpath = System.getProperty("user.dir") + "\\CARcalculation.xlsx";

				fos = new FileOutputStream(CARpath);

				workbook1.write(fos);
			} else {

				// System.out.println("value of v after if : " + v + "inward value is : " +
				// inward );
				selectquery = "SELECT * FROM Rule_Sheet_Master where Rule_Reference ='" + rule_ref
						+ "' and Is_deleted=0 order by Updated_on desc;";
				gbrquery = "select * from GBR_Master where Inward_no='" + inward + "' and GBR_Is_Deleted=0;";
			//	System.out.println("rs query is : " + selectquery + "rs1 quesry is " + gbrquery);
				rs = st.executeQuery(selectquery);
				rs1 = st1.executeQuery(gbrquery);

				rs.next();
				rs1.next();
				double odvalue = rs1.getDouble("OD");
				double addonvalue = rs1.getDouble("ADD_ON");
				double tpvalue = rs1.getDouble("TP");
				double terrorismvalue = rs1.getDouble("terrorism");

				double rule_id = rs.getDouble("id");
				double odrate = rs.getDouble("OD_total");
				double tprate = rs.getDouble("TP_total");
				double fixvalue = rs.getDouble("Fixed_value_total");
				double odrate1 = rs.getDouble("OD_rate_1_commission_per");
				double tprate1 = rs.getDouble("TP_rate_1_commission_per");
				double fixvalue1 = rs.getDouble("Fixed_value_Basis_1");
				double odrate2 = rs.getDouble("OD_rate_2_commission_per");
				double tprate2 = rs.getDouble("TP_rate_2_commission_per");
				double fixvalue2 = rs.getDouble("Fixed_value_Basis_2");

				double OD_total = (odvalue + addonvalue) * odrate / 100;

				double TP_total = (tpvalue + terrorismvalue) * tprate / 100;

				double Total_value = OD_total + TP_total + fixvalue;

				double OD_rate_1 = (odvalue + addonvalue) * odrate1 / 100;

				double TP_rate_1 = (tpvalue + terrorismvalue) * tprate1 / 100;

				double valueA = OD_rate_1 + TP_rate_1 + fixvalue1;

				double OD_rate_2 = (odvalue + addonvalue) * odrate2 / 100;

				double TP_rate_2 = (tpvalue + terrorismvalue) * tprate2 / 100;
				double valueB = OD_rate_2 + TP_rate_2 + fixvalue2;

				inwardcell2.setCellValue(inward);
				Rule_refcell2.setCellValue(rule_ref);
				Rule_conditioncell2.setCellValue(rule_id);
				OD_totalcell2.setCellValue(OD_total);
				TP_totalcell2.setCellValue(TP_total);
				Fixed_value_totalcell2.setCellValue(fixvalue);
				Total_Valuecell2.setCellValue(Total_value);
				OD_rate_1cell2.setCellValue(OD_rate_1);
				TP_rate_1cell2.setCellValue(TP_rate_1);
				Fixed_value_1cell2.setCellValue(fixvalue1);
				ValueAcell2.setCellValue(valueA);
				OD_rate_2cell2.setCellValue(OD_rate_2);
				TP_rate_2cell2.setCellValue(TP_rate_2);
				Fixed_value_2cell2.setCellValue(fixvalue2);
				ValueBcell2.setCellValue(valueB);
				// System.out.println("clear and tested");
				CARpath = System.getProperty("user.dir") + "\\CARcalculation.xlsx";

				fos = new FileOutputStream(CARpath);

				workbook1.write(fos);

				// System.out.println("value of i is: " + i);

			}
			fos.close();
		}
		con.close();
	}

	public boolean tc_339() throws InterruptedException {

		driver.findElement(caricon).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(detailstab));
		driver.findElement(detailstab).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(fromdate));
		boolean flag = driver.findElement(fromdate).isDisplayed();
		Thread.sleep(1000);
		driver.findElement(summarytab).click();
		boolean flag1 = driver.findElement(By.id("btnViewSummaryReport")).isDisplayed();

		return flag && flag1;
	}

	public boolean tc_340res1() throws InterruptedException {
		driver.findElement(caricon).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		driver.findElement(detailstab).click();
		Thread.sleep(1000);
		return driver.findElement(fromdate).isDisplayed() && driver.findElement(Todate).isDisplayed()
				&& driver.findElement(Clearbutton).isDisplayed() && driver.findElement(Searchtxtbx).isDisplayed()
				&& driver.findElement(Viewbtn).isDisplayed() && driver.findElement(downlodbtn).isDisplayed()
				&& driver.findElement(showentriesdrpdown).isDisplayed();
	}

	By calendermonth = By.xpath("//select[@class='ui-datepicker-month']");
	By calenderyear = By.xpath("//select[@class='ui-datepicker-year']");

	public boolean tc_340res2() throws InterruptedException, ClassNotFoundException, SQLException {
		driver.findElement(caricon).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		driver.findElement(detailstab).click();

		String dbURL = prop.getProperty("dburl");
		String username = prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs = null;
		String selectquery = "SELECT * FROM GBR_Master;";
		rs = st.executeQuery(selectquery);
		rs.next();

		Date ref_date = rs.getDate("Reference_Issue_Date");
		int month = ref_date.getMonth();
		int day = ref_date.getDate();
		String monthstring = Integer.toString(month - 1);
		//System.out.println(month + ": month " + "String month is :" + monthstring);
		Thread.sleep(1000);
		// from date selection
		driver.findElement(fromdate).click();
		Thread.sleep(500);
		Select se = new Select(driver.findElement(calendermonth));
		se.selectByValue(monthstring);
		Thread.sleep(500);
		Select se1 = new Select(driver.findElement(calenderyear));
		se1.selectByValue("2022");
		Thread.sleep(500);

		driver.findElement(By.xpath("//tr[1]/td[@data-handler='selectDay']/a[contains(text(),'1')]")).click();
		Thread.sleep(500);

		// to date selection
		driver.findElement(Todate).click();
		Thread.sleep(500);
		Select se2 = new Select(driver.findElement(calendermonth));
		se2.selectByValue(monthstring);
		Thread.sleep(500);
		Select se3 = new Select(driver.findElement(calenderyear));
		se3.selectByValue("2022");
		Thread.sleep(500);

		driver.findElement(By.xpath("//tr[1]/td[@data-handler='selectDay']/a[contains(text(),'" + (day + 1) + "')]"))
				.click();
		Thread.sleep(500);

		driver.findElement(Viewbtn).click();
		Thread.sleep(5000);
		boolean flag = driver.findElement(By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr[1]/td[2]")).isDisplayed();

		return flag;

	}

	public boolean Tc_341() throws InterruptedException {
		driver.findElement(caricon).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		driver.findElement(detailstab).click();
		driver.findElement(fromdate).click();
		boolean flag = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]")).isDisplayed();
		String actdate = driver.findElement(fromdate).getAttribute("value");

		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
		String expdate = format2.format(date);

		boolean flag1 = actdate.equals(expdate);
		// System.out.println("actual date is :" +actdate + "exp date is :" + expdate);

		DateTime dtOrg = new DateTime(date);
		DateTime dtPlusOne = dtOrg.plusDays(1);
		// System.out.println("tomorrow's date is :" + dtPlusOne);
		int dateis = dtPlusOne.getDayOfMonth();
		// System.out.println("tomorrow's day is :" +dateis);

		driver.navigate().refresh();
		Thread.sleep(500);
		driver.findElement(fromdate).click();
		Thread.sleep(500);
		boolean flag2 = driver.findElement(
				By.xpath("//td[@class=' ui-datepicker-unselectable ui-state-disabled ']/span[contains(text(),'"
						+ (dateis + 1) + "')]"))
				.isSelected();
		Thread.sleep(500);
		// System.out.println("flag2 is : " + !flag2);
		driver.navigate().refresh();
		Thread.sleep(500);
		driver.findElement(fromdate).sendKeys("13-Jul-2022");
		Thread.sleep(2000);
		String emptyvalue = driver.findElement(fromdate).getAttribute("value");
		boolean flag3 = emptyvalue.equals(expdate);

		// System.out.println("flag3 is : " + flag3);

		driver.navigate().refresh();
		Thread.sleep(500);
		driver.findElement(fromdate).click();
		Thread.sleep(500);
		int monthint = dtOrg.getMonth();
		int datecurrent = dtOrg.getDayOfMonth() - 1;
		String monthstring = Integer.toString(monthint - 1);

		// System.out.println("today's month and day is :" + datecurrent + monthstring);

		Select se = new Select(driver.findElement(calendermonth));
		se.selectByValue(monthstring);
		Thread.sleep(500);
		Select se1 = new Select(driver.findElement(calenderyear));
		se1.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'" + datecurrent + "')]"))
				.click();
		Thread.sleep(1000);
		String actualdateselected = driver.findElement(fromdate).getAttribute("value");

		driver.findElement(fromdate).click();
		Thread.sleep(500);

		Select se2 = new Select(driver.findElement(calendermonth));
		se2.selectByValue(monthstring);
		Thread.sleep(500);
		Select se3 = new Select(driver.findElement(calenderyear));
		se3.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'" + (datecurrent - 1) + "')]"))
				.click();
		Thread.sleep(1000);
		String actualdatereselected = driver.findElement(fromdate).getAttribute("value");

		boolean flag4 = actualdateselected.equals(actualdatereselected);

		// System.out.println("flags are :" +flag + flag1 + flag2 + flag3 + !flag4);
		return flag && flag1 && !flag2 && flag3 && !flag4;
	}

	public boolean Tc_342() throws InterruptedException {
		driver.findElement(caricon).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		driver.findElement(detailstab).click();
		driver.findElement(Todate).click();
		boolean flag = driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]")).isDisplayed();
		String actdate = driver.findElement(Todate).getAttribute("value");

		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
		String expdate = format2.format(date);

		boolean flag1 = actdate.equals(expdate);
		// System.out.println("actual date is :" +actdate + "exp date is :" + expdate);

		DateTime dtOrg = new DateTime(date);
		// DateTime dtPlusOne = dtOrg.plusDays(1);
		// System.out.println("tomorrow's date is :" + dtPlusOne);
		int dateis = dtOrg.getDayOfMonth() - 1;
		System.out.println("tomorrow's day is :" + dateis);

		driver.navigate().refresh();
		Thread.sleep(500);
		driver.findElement(Todate).click();
		Thread.sleep(500);
		boolean flag2 = driver.findElement(By.xpath(
				"//td[@class=' ui-datepicker-unselectable ui-state-disabled ' or @class=' ui-datepicker-week-end ui-datepicker-unselectable ui-state-disabled ']/span[contains(text(),'"
						+ (dateis) + "')]"))
				.isSelected();
		Thread.sleep(500);
		System.out.println("flag2 is : " + flag2);
		driver.navigate().refresh();
		Thread.sleep(500);
		driver.findElement(Todate).sendKeys("13-Jul-2022");
		Thread.sleep(2000);
		String emptyvalue = driver.findElement(Todate).getAttribute("value");
		boolean flag3 = emptyvalue.equals(expdate);

		// System.out.println("flag3 is : " + flag3);

		driver.navigate().refresh();
		Thread.sleep(500);
		int monthint = dtOrg.getMonth();
		int datecurrent = dtOrg.getDayOfMonth();
		String monthstring = Integer.toString(monthint - 1);

		driver.findElement(fromdate).click();
		Thread.sleep(500);

		Select se2 = new Select(driver.findElement(calendermonth));
		se2.selectByValue(monthstring);
		Thread.sleep(500);
		Select se3 = new Select(driver.findElement(calenderyear));
		se3.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'" + (datecurrent - 1) + "')]"))
				.click();
		Thread.sleep(1000);

		driver.findElement(Todate).click();
		Thread.sleep(500);
		Select se11 = new Select(driver.findElement(calendermonth));
		se11.selectByValue(monthstring);
		Thread.sleep(500);
		Select se12 = new Select(driver.findElement(calenderyear));
		se12.selectByValue("2022");
		Thread.sleep(500);
		boolean flag6 = driver.findElement(By.xpath(
				"//td[@class=' ui-datepicker-unselectable ui-state-disabled ' or @class=' ui-datepicker-week-end ui-datepicker-unselectable ui-state-disabled ']/span[contains(text(),'"
						+ (datecurrent - 2) + "')]"))
				.isSelected();

		driver.findElement(Todate).click();
		Thread.sleep(500);

		Select se = new Select(driver.findElement(calendermonth));
		se.selectByValue(monthstring);
		Thread.sleep(500);
		Select se1 = new Select(driver.findElement(calenderyear));
		se1.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'" + datecurrent + "')]"))
				.click();
		Thread.sleep(1000);
		String actualdateselected = driver.findElement(Todate).getAttribute("value");

		driver.findElement(Todate).click();
		Thread.sleep(500);

		Select se4 = new Select(driver.findElement(calendermonth));
		se4.selectByValue(monthstring);
		Thread.sleep(500);
		Select se5 = new Select(driver.findElement(calenderyear));
		se5.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'" + (datecurrent - 1) + "')]"))
				.click();
		Thread.sleep(1000);
		String actualdatereselected = driver.findElement(Todate).getAttribute("value");

		boolean flag4 = actualdateselected.equals(actualdatereselected);

		driver.navigate().refresh();
		Thread.sleep(500);

		driver.findElement(fromdate).click();
		Thread.sleep(500);

		Select se6 = new Select(driver.findElement(calendermonth));
		se6.selectByValue(monthstring);
		Thread.sleep(500);
		Select se7 = new Select(driver.findElement(calenderyear));
		se7.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'1')]")).click();
		Thread.sleep(1000);

		driver.findElement(Todate).click();
		Thread.sleep(500);

		List<WebElement> elements = driver.findElements(By.xpath(
				"//td[@class=' ui-datepicker-unselectable ui-state-disabled ' or @class=' ui-datepicker-week-end ui-datepicker-unselectable ui-state-disabled ']/span"));
		boolean flag5 = true;
		for (WebElement ele : elements) {
			String disableddate = ele.getText();
			flag5 = ele.isSelected();
		}
		System.out.println("flags are :" + flag + flag1 + !flag2 + flag3 + !flag4 + flag5 + flag6);
		return flag && flag1 && !flag2 && flag3 && !flag4 && flag5 && flag6;
	}

	public boolean Tc_343() throws ClassNotFoundException, SQLException, InterruptedException, InvalidFormatException {
		TestUtil.getTestData1(
				System.getProperty("user.dir") + "\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String expmsgnorecord = TestUtil.sheet1.getRow(14).getCell(3).getStringCellValue();
		String expmsgsuccess = TestUtil.sheet1.getRow(15).getCell(3).getStringCellValue();

		driver.findElement(caricon).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		driver.findElement(detailstab).click();

		String dbURL = prop.getProperty("dburl");
		String username = prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs = null;
		String selectquery = "SELECT * FROM GBR_Master;";
		rs = st.executeQuery(selectquery);
		rs.next();

		Date ref_date = rs.getDate("Reference_Issue_Date");
		int month = ref_date.getMonth();
		int day = ref_date.getDate();
		String monthstring = Integer.toString(month);
	//	System.out.println(month + ": month " + "String month is :" + monthstring);
		Thread.sleep(1000);
		// from date selection
		driver.findElement(fromdate).click();
		Thread.sleep(500);
		Select se = new Select(driver.findElement(calendermonth));
		se.selectByValue(monthstring);
		Thread.sleep(500);
		Select se1 = new Select(driver.findElement(calenderyear));
		se1.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'1')]")).click();
		Thread.sleep(500);
		// to date selection
		driver.findElement(Todate).click();
		Thread.sleep(500);
		Select se2 = new Select(driver.findElement(calendermonth));
		se2.selectByValue(monthstring);
		Thread.sleep(500);
		Select se3 = new Select(driver.findElement(calenderyear));
		se3.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'" + (day + 1) + "')]")).click();
		Thread.sleep(500);

		boolean flag1 = driver.findElement(downlodbtn).isEnabled();

		driver.findElement(downlodbtn).click();
		Thread.sleep(1000);
		boolean flag = driver.findElement(Errorbox).isDisplayed();
		String successmsg = driver.findElement(Errorbox).getText();
	//	System.out.println("successmsg is :" + successmsg);
		boolean flag5 = expmsgsuccess.equals(successmsg);

		driver.navigate().refresh();
		boolean flag2 = driver.findElement(downlodbtn).isEnabled();
		driver.findElement(downlodbtn).click();
		String errormsg = driver.findElement(Errorbox).getText();
		System.out.println("errormsg is :" + errormsg);
		boolean flag4 = expmsgnorecord.equals(errormsg);
		System.out.println("flags are :" + flag1 + flag2);

		String path1 = downloadedCAR();
		boolean flag3 = path1.contains(".xls");
		return flag && flag3 && flag2 && flag1 && flag4 && flag5;
	}

	public boolean tc_343res8() throws InvalidFormatException {
		String path1 = downloadedCAR();
		TestUtil.getTestData1(path1);
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		Row Row = TestUtil.sheet1.getRow(0);

		TestUtil.getTestData1(System.getProperty("user.dir")
				+ "\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\sample_CAR_headers.xlsx");
		Sheet sheet = TestUtil.book1.getSheetAt(0);
		Row row = sheet.getRow(0);
		boolean flag = false;
		boolean flag1 = true;
		for (int i = 0; i <= TestUtil.sheet1.getRow(0).getLastCellNum() - 1; i++) {
			String actvalues = Row.getCell(i).getStringCellValue();

			String expvalues = row.getCell(i).getStringCellValue();
			// System.out.println("actvalues :" + actvalues + "Expected values are :" +
			// expvalues);
			flag = actvalues.equals(expvalues);
			if (flag == true) {
				// System.out.println("value is as expected");
			} else {
				flag1 = flag;
				System.out.println("flag1 :" + flag1);
				System.out.println("value :" + actvalues + "is different.");

			}
		}
		return flag1;
	}

	public boolean Tc_345() throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException {
		TestUtil.getTestData1(
				System.getProperty("user.dir") + "\\src\\main\\java\\qa\\com\\recon\\testdata\\CARtestdata.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
		String expmsgnorecord = TestUtil.sheet1.getRow(13).getCell(3).getStringCellValue();

		driver.findElement(caricon).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		driver.findElement(detailstab).click();
		// db connection
		String dbURL = prop.getProperty("dburl");
		String username = prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs = null;
		String selectquery = "SELECT * FROM GBR_Master;";
		rs = st.executeQuery(selectquery);
		rs.next();
		// get ref_issue_date
		Date ref_date = rs.getDate("Reference_Issue_Date");
		int month = ref_date.getMonth();
		int day = ref_date.getDate();
		String monthstring = Integer.toString(month);
	//	System.out.println(month + ": month " + "String month is :" + monthstring);
		Thread.sleep(1000);
		// from date selection
		driver.findElement(fromdate).click();
		Thread.sleep(500);
		Select se = new Select(driver.findElement(calendermonth));
		se.selectByValue(monthstring);
		Thread.sleep(500);
		Select se1 = new Select(driver.findElement(calenderyear));
		se1.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'1')]")).click();
		Thread.sleep(500);
		// to date selection
		driver.findElement(Todate).click();
		Thread.sleep(500);
		Select se2 = new Select(driver.findElement(calendermonth));
		se2.selectByValue(monthstring);
		Thread.sleep(500);
		Select se3 = new Select(driver.findElement(calenderyear));
		se3.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'" + (day + 1) + "')]")).click();
		Thread.sleep(500);

		boolean flag1 = driver.findElement(Viewbtn).isEnabled();

		driver.findElement(Viewbtn).click();
		boolean flag3 = driver.findElement(By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr[1]/td[2]")).isDisplayed();
		Thread.sleep(4000);

		driver.findElement(Clearbutton).click();
		Thread.sleep(1000);
		boolean flag2 = driver.findElement(Viewbtn).isEnabled();
		driver.findElement(Viewbtn).click();
		String errormsg = driver.findElement(Errorbox).getText();
		boolean flag4 = expmsgnorecord.equals(errormsg);

		return flag2 && flag1 && flag3 && flag4;
	}

	public boolean Tc_346() throws InterruptedException {
		driver.findElement(caricon).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		driver.findElement(detailstab).click();

		// from date selection
		driver.findElement(fromdate).click();
		Thread.sleep(500);
		Select se = new Select(driver.findElement(calendermonth));
		se.selectByValue("0");
		Thread.sleep(500);
		Select se1 = new Select(driver.findElement(calenderyear));
		se1.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'1')]")).click();
		Thread.sleep(500);
		String beforefromdatevalue = driver.findElement(fromdate).getAttribute("value");

		// to date selection
		driver.findElement(Todate).click();
		Thread.sleep(500);
		Select se2 = new Select(driver.findElement(calendermonth));
		se2.selectByValue("0");
		Thread.sleep(500);
		Select se3 = new Select(driver.findElement(calenderyear));
		se3.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'28')]")).click();
		Thread.sleep(500);
		String beforetodatevalue = driver.findElement(Todate).getAttribute("value");

		// clear button click
		boolean flag1 = driver.findElement(Clearbutton).isEnabled();
		driver.findElement(Clearbutton).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(Clearbutton));
		String afterfromdatevalue = driver.findElement(fromdate).getAttribute("value");
		String afetertodatevalue = driver.findElement(Todate).getAttribute("value");
		boolean flag2 = driver.findElement(Clearbutton).isEnabled();
		boolean flag = beforefromdatevalue.equals(afterfromdatevalue) && beforetodatevalue.equals(afetertodatevalue);

		return !flag && flag1 && flag2;
	}

	public List<Integer> gbrod_addontotal(String path, String firstcol, String seccol) throws InvalidFormatException {
		TestUtil.getTestData1(path);
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);

		Map<String, Integer> map = new HashMap<String, Integer>(); // Create map
		Row row = TestUtil.sheet1.getRow(0); // Get first row

		// following is boilerplate from the java doc
		short minColIx = row.getFirstCellNum(); // get the first column index for a row
		short maxColIx = row.getLastCellNum(); // get the last column index for a row
		for (short colIx = minColIx; colIx < maxColIx; colIx++) { // loop from first to last index
			Cell cell = row.getCell(colIx); // get the cell
			map.put(cell.getStringCellValue(), cell.getColumnIndex()); // add the cell contents (name of column) and
																		// cell index to the map
		}
		
		List<Integer> total = new ArrayList<Integer>();
		for (int i = 0; i < TestUtil.sheet1.getLastRowNum(); i++) {
			Row row1 = TestUtil.sheet1.getRow(i + 1);

			int idxForColumn1 = map.get(firstcol);
			int idxForColumn2 = map.get(seccol);
			// System.out.println("index for addon is :" + idxForColumn2);
			Cell cell1 = row1.getCell(idxForColumn1);
			Row odrow = cell1.getRow();
			int od = (int) odrow.getCell(idxForColumn1).getNumericCellValue();
			// System.out.println("od value is :" + od );

			Cell cell2 = row1.getCell(idxForColumn2);
			Row add_onrow = cell2.getRow();
			int add_on = (int) add_onrow.getCell(idxForColumn2).getNumericCellValue();
			// System.out.println("addon value is :" + add_on);

			int od_addon_total = od + add_on;
			// System.out.println("total is :" + od_addon_total);
			total.add(od_addon_total);
		}
		return total;
	}

	// List<Integer> caradd_ontotal(String path)
	public List<Integer> caradd_ontotal(String path, String Total) throws InvalidFormatException {
		TestUtil.getTestData1(path);
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);

		Map<String, Integer> map = new HashMap<String, Integer>(); // Create map
		Row row = TestUtil.sheet1.getRow(0); // Get first row

		// following is boilerplate from the java doc
		short minColIx = row.getFirstCellNum(); // get the first column index for a row
		short maxColIx = row.getLastCellNum(); // get the last column index for a row
		for (short colIx = minColIx; colIx < maxColIx; colIx++) { // loop from first to last index
			Cell cell = row.getCell(colIx); // get the cell
			map.put(cell.getStringCellValue(), cell.getColumnIndex()); // add the cell contents (name of column) and
																		// cell index to the map
		}
		// System.out.println("downloaded car row no " +
		// TestUtil.sheet1.getLastRowNum());
		List<Integer> total = new ArrayList<Integer>();
		for (int i = 0; i < TestUtil.sheet1.getLastRowNum() - 1; i++) {
			Row row1 = TestUtil.sheet1.getRow(i + 1);

			int idxForColumn1 = map.get(Total);
			Cell cell1 = row1.getCell(idxForColumn1);
			// System.out.println("index is :" + idxForColumn1);
			Row odrow = cell1.getRow();
			int od_addontotal = (int) odrow.getCell(idxForColumn1).getNumericCellValue();
			total.add(od_addontotal);
			// System.out.println("od_addontotal is:"+od_addontotal);

		}
		// System.out.println("total list is :" + total);
		return total;
	}

	public boolean smegbrdownloadcar() throws InterruptedException, ClassNotFoundException, SQLException {
		driver.findElement(caricon).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		driver.findElement(detailstab).click();

		String dbURL = prop.getProperty("dburl");
		String username = prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs = null;
		String selectquery = "SELECT * FROM GBR_Master;";
		rs = st.executeQuery(selectquery);
		rs.next();

		Date ref_date = rs.getDate("Reference_Issue_Date");
		int month = ref_date.getMonth();
		int day = ref_date.getDate();
		String monthstring = Integer.toString(month);
	//	System.out.println(month + ": month " + "String month is :" + monthstring);
		Thread.sleep(1000);
		// from date selection
		driver.findElement(fromdate).click();
		Thread.sleep(500);
		Select se = new Select(driver.findElement(calendermonth));
		se.selectByValue(monthstring);
		Thread.sleep(500);
		Select se1 = new Select(driver.findElement(calenderyear));
		se1.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'1')]")).click();
		Thread.sleep(500);
		// to date selection
		driver.findElement(Todate).click();
		Thread.sleep(500);
		Select se2 = new Select(driver.findElement(calendermonth));
		se2.selectByValue(monthstring);
		Thread.sleep(500);
		Select se3 = new Select(driver.findElement(calenderyear));
		se3.selectByValue("2022");
		Thread.sleep(500);
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'" + (day + 1) + "')]")).click();
		Thread.sleep(500);

		driver.findElement(downlodbtn).click();
		Thread.sleep(1000);
		String successmsg = driver.findElement(Errorbox).getText();
	//	System.out.println("successmsg is :" + successmsg);
		return successmsg.contains("Success");

	}

	By Entriestxtbx = By.xpath("//*[@id=\"PospListDataTable_length\"]/label/select");

	public boolean Tc_357res4(String key, String firstcol, String seccol)
			throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException {
		viewbuttonclick();

		Thread.sleep(5000);
		Select se11 = new Select(driver.findElement(Entriestxtbx));
		se11.selectByIndex(4);
		
		String data=driver.findElement(By.xpath("//*[@id=\"PospListDataTable_info\"]")).getText();
		Thread.sleep(1000);
		String count[]=data.split("of ");
		String datacount[]=count[1].split(" entries");
		String number=datacount[0];
		int num=Integer.parseInt(number);
		
		List<Integer> actdataod_add_on = new ArrayList<Integer>();

		for (int i = 0; i < num; i++) {
			WebElement expele = driver.findElement(By.xpath("//tbody/tr[" + (i + 1)
					+ "]/td[count(//*[@id='PospListDataTable_wrapper']/div[2]/div/div/div[1]/div/table/thead/tr/th[contains(text(),'"
					+ key + "')]/preceding-sibling::th)+1]"));//
			String od_add_on_total = null;

			if (expele.getAttribute("innerText") != null) {
				od_add_on_total = expele.getAttribute("innerText").trim();
				int od = Integer.parseInt(od_add_on_total);
				actdataod_add_on.add(od);
			} else {
				od_add_on_total = expele.getText().trim();
			}

		}

		List<Integer> expecteddata = gbrod_addontotal(
				System.getProperty("user.dir")
						+ "\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\GBRALL_SME_Dataset1.xlsx",
				firstcol, seccol);
		// System.out.println("actdata is :" + expecteddata);
		return actdataod_add_on.equals(expecteddata);
	}

	public boolean TC_357() throws InvalidFormatException, ClassNotFoundException, InterruptedException, SQLException {
		List<Integer> actdata = gbrod_addontotal(
				System.getProperty("user.dir")
						+ "\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\GBRALL_SME_Dataset1.xlsx",
				"OD", "ADD_ON");
		System.out.println("actdata is :" + actdata);

		boolean flag = smegbrdownloadcar();
		Thread.sleep(10000);
		List<Integer> expdata = null;
		if (flag == true) {
			String path1 = downloadedCAR();
			expdata = caradd_ontotal(path1, "OD_ADDON_Total");
			System.out.println("expdata is :" + expdata);
		}
		return actdata.equals(expdata);

	}

	public boolean Tc_358() throws ClassNotFoundException, InterruptedException, SQLException, InvalidFormatException {
		List<Integer> actdata = gbrod_addontotal(
				System.getProperty("user.dir")
						+ "\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\GBRALL_SME_Dataset1.xlsx",
				"TP", "terrorism");
		System.out.println("actdata is :" + actdata);

		boolean flag = smegbrdownloadcar();
		Thread.sleep(10000);
		List<Integer> expdata = null;
		if (flag == true) {
			String path1 = downloadedCAR();
			expdata = caradd_ontotal(path1, "TP_Terrorism_Total");
			System.out.println("expdata is :" + expdata);
		}
		return actdata.equals(expdata);
	}

	public boolean tc_359res4(String key,int cellvalue,int index) throws IOException, ClassNotFoundException, InterruptedException, SQLException, InvalidFormatException
	{
		viewbuttonclick();
	
		Thread.sleep(5000);
		Select se11 = new Select(driver.findElement(Entriestxtbx));
		se11.selectByIndex(4);
		List<String> actdataod_add_on = new ArrayList<String>();
		List<String> basislist = new ArrayList<String>();
		List<String> expbasislist = new ArrayList<String>();
		String data=driver.findElement(By.xpath("//*[@id=\"PospListDataTable_info\"]")).getText();
		Thread.sleep(1000);
		String count[]=data.split("of ");
		String datacount[]=count[1].split(" entries");
		String number=datacount[0];
		int num=Integer.parseInt(number);
		
	for (int i = 0; i < num; i++) {
		WebElement expele = driver.findElement(By.xpath("//tbody/tr[" + (i + 1)
				+ "]/td[count(//*[@id='PospListDataTable_wrapper']/div[2]/div/div/div[1]/div/table/thead/tr/th[contains(text(),'Rule_Reference')]/preceding-sibling::th)+1]"));
		String od_add_on_total = null;
		WebElement expele1 = driver.findElement(By.xpath("//tbody/tr[" + (i + 1)
				+ "]/td[count(//*[@id='PospListDataTable_wrapper']/div[2]/div/div/div[1]/div/table/thead/tr/th[contains(text(),'"+ key + "')]"
				+ "/preceding-sibling::th)+"+index+"]"));
		
		String basis = null;
		String basisexpvalue=null;
		if (expele.getAttribute("innerText") != null) {
			od_add_on_total = expele.getAttribute("innerText").trim();
			actdataod_add_on.add(od_add_on_total);
		} else {
			od_add_on_total = expele.getText().trim();
		}
		
		TestUtil.getTestData1(
				System.getProperty("user.dir") + "\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\sme_rulesheet_basis_mapping.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
	
		for(int j=0;j<=TestUtil.sheet1.getLastRowNum();j++) {
			String rule_ref = TestUtil.sheet1.getRow(j+1).getCell(0).getStringCellValue();
			Row row=TestUtil.sheet1.getRow(j+1);
			
			if(rule_ref.equals(od_add_on_total))
			{
				 basisexpvalue = TestUtil.sheet1.getRow(row.getRowNum()).getCell(cellvalue).getStringCellValue();
				//System.out.println("rule_ref is " + rule_ref + "respected basis from excel is :" + basisexpvalue);
				expbasislist.add(basisexpvalue);
				break;
			}
		}
			if (expele1.getAttribute("innerText") != null) {
				basis = expele1.getAttribute("innerText").trim();
				basislist.add(basis);
			} else {
				basis = expele1.getText().trim();
			}
	}
	//System.out.println("rule ref is " + actdataod_add_on + "basis list is " + basislist);

	return basislist.equals(expbasislist);
	}
	public boolean tc_359res1(int cellvalue,String key) throws ClassNotFoundException, InterruptedException, SQLException, InvalidFormatException {
			smegbrdownloadcar();
			Thread.sleep(2000);
			String path1 = downloadedCAR();
			
			Workbook book1 = null;
			Workbook book2 = null;
			Sheet sheet = null;
			Sheet sheet1 = null;
			
			FileInputStream file1 = null;
			FileInputStream file2 = null;

			try {
				file1 = new FileInputStream(path1);
				file2 = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\sme_rulesheet_basis_mapping.xlsx");
			}

			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				book1 = WorkbookFactory.create(file1);
				book2 = WorkbookFactory.create(file2);
			} catch (IOException e) {
				e.printStackTrace();
			}

			sheet = book1.getSheetAt(0);
			sheet1 = book2.getSheetAt(0);
			
			Map<String, Integer> map = new HashMap<String, Integer>(); // Create map
			Row row = sheet.getRow(0); // Get first row

			// following is boilerplate from the java doc
			short minColIx = row.getFirstCellNum(); // get the first column index for a row
			short maxColIx = row.getLastCellNum(); // get the last column index for a row
			for (short colIx = minColIx; colIx < maxColIx; colIx++) { // loop from first to last index
				Cell cell = row.getCell(colIx); // get the cell
				map.put(cell.getStringCellValue(), cell.getColumnIndex()); // add the cell contents (name of column) and
																			// cell index to the map
			}
			
			List<String> actbasislist = new ArrayList<String>();
			List<String> expbasislist = new ArrayList<String>();
			for (int i = 0; i < sheet.getLastRowNum()-1; i++) {
				Row row1 = sheet.getRow(i + 1);

				int idxForColumn1 = map.get("Rule_Reference");
				int idxForColumn2 = map.get(key);
				Cell cell1 = row1.getCell(idxForColumn1);
				Row odrow = cell1.getRow();
				String actrule_ref = odrow.getCell(idxForColumn1).getStringCellValue();
				String actbasis = odrow.getCell(idxForColumn2).getStringCellValue();
				actbasislist.add(actbasis);
				String basisexpvalue=null;
				
			for(int j=0;j<=sheet1.getLastRowNum();j++) {
				
				String rule_ref = sheet1.getRow(j+1).getCell(0).getStringCellValue();
				Row sheet1row=sheet1.getRow(j+1);
				
				if(rule_ref.equals(actrule_ref))
				{
					 basisexpvalue = sheet1.getRow(sheet1row.getRowNum()).getCell(cellvalue).getStringCellValue();
				//	System.out.println("rule_ref is " + rule_ref + "respected basis from excel is :" + basisexpvalue);
					expbasislist.add(basisexpvalue);
					break;
				}
			}
			}
			System.out.println("Explist :" + expbasislist + "Actual list :" + actbasislist);
			return actbasislist.equals(expbasislist);
	}
	public boolean tc_360res4(String key,int cellvalue,int index) throws IOException, ClassNotFoundException, InterruptedException, SQLException, InvalidFormatException
	{
		viewbuttonclick();
	
		Thread.sleep(5000);
		Select se11 = new Select(driver.findElement(Entriestxtbx));
		se11.selectByIndex(4);
		List<String> actdataod_add_on = new ArrayList<String>();
		List<String> basislist = new ArrayList<String>();
		List<String> expbasislist = new ArrayList<String>();
		String data=driver.findElement(By.xpath("//*[@id=\"PospListDataTable_info\"]")).getText();
		Thread.sleep(1000);
		String count[]=data.split("of ");
		String datacount[]=count[1].split(" entries");
		String number=datacount[0];
		int num=Integer.parseInt(number);
		
	for (int i = 0; i < num; i++) {
		WebElement expele = driver.findElement(By.xpath("//tbody/tr[" + (i + 1)
				+ "]/td[count(//*[@id='PospListDataTable_wrapper']/div[2]/div/div/div[1]/div/table/thead/tr/th[contains(text(),'Rule_Reference')]/preceding-sibling::th)+1]"));
		String od_add_on_total = null;
		WebElement expele1 = driver.findElement(By.xpath("//tbody/tr[" + (i + 1)
				+ "]/td[count(//*[@id='PospListDataTable_wrapper']/div[2]/div/div/div[1]/div/table/thead/tr/th[contains(text(),'"+ key + "')]"
				+ "/preceding-sibling::th)+"+index+"]"));
		
		String basis = null;
		int basisexpvalue;
		if (expele.getAttribute("innerText") != null) {
			od_add_on_total = expele.getAttribute("innerText").trim();
			actdataod_add_on.add(od_add_on_total);
		} else {
			od_add_on_total = expele.getText().trim();
		}
		
		TestUtil.getTestData1(
				System.getProperty("user.dir") + "\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\sme_rulesheet_basis_mapping.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
	
		for(int j=0;j<=TestUtil.sheet1.getLastRowNum();j++) {
			String rule_ref = TestUtil.sheet1.getRow(j+1).getCell(0).getStringCellValue();
			Row row=TestUtil.sheet1.getRow(j+1);
			
			if(rule_ref.equals(od_add_on_total))
			{
				 basisexpvalue = (int) TestUtil.sheet1.getRow(row.getRowNum()).getCell(cellvalue).getNumericCellValue();
				 String expbasisvalue=String.valueOf(basisexpvalue);
			//	System.out.println("rule_ref is " + rule_ref + "respected basis from excel is :" + expbasisvalue);
				expbasislist.add(expbasisvalue);
				break;
			}
		}
			if (expele1.getAttribute("innerText") != null) {
				basis = expele1.getAttribute("innerText").trim();
				basislist.add(basis);
			} else {
				basis = expele1.getText().trim();
			}
	}
	//System.out.println("rule ref is " + actdataod_add_on + "basis list is " + basislist);

	return basislist.equals(expbasislist);
	}
	public boolean tc_360res1(int cellvalue,String key) throws ClassNotFoundException, InterruptedException, SQLException, InvalidFormatException {
			smegbrdownloadcar();
			Thread.sleep(2000);
			String path1 = downloadedCAR();
			
			Workbook book1 = null;
			Workbook book2 = null;
			Sheet sheet = null;
			Sheet sheet1 = null;
			
			FileInputStream file1 = null;
			FileInputStream file2 = null;

			try {
				file1 = new FileInputStream(path1);
				file2 = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\sme_rulesheet_basis_mapping.xlsx");
			}

			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				book1 = WorkbookFactory.create(file1);
				book2 = WorkbookFactory.create(file2);
			} catch (IOException e) {
				e.printStackTrace();
			}

			sheet = book1.getSheetAt(0);
			sheet1 = book2.getSheetAt(0);
			
			Map<String, Integer> map = new HashMap<String, Integer>(); // Create map
			Row row = sheet.getRow(0); // Get first row

			// following is boilerplate from the java doc
			short minColIx = row.getFirstCellNum(); // get the first column index for a row
			short maxColIx = row.getLastCellNum(); // get the last column index for a row
			for (short colIx = minColIx; colIx < maxColIx; colIx++) { // loop from first to last index
				Cell cell = row.getCell(colIx); // get the cell
				map.put(cell.getStringCellValue(), cell.getColumnIndex()); // add the cell contents (name of column) and
																			// cell index to the map
			}
			
			List<String> actbasislist = new ArrayList<String>();
			List<String> expbasislist = new ArrayList<String>();
			for (int i = 0; i < sheet.getLastRowNum()-1; i++) {
				Row row1 = sheet.getRow(i + 1);

				int idxForColumn1 = map.get("Rule_Reference");
				int idxForColumn2 = map.get(key);
				Cell cell1 = row1.getCell(idxForColumn1);
				Row odrow = cell1.getRow();
				String actrule_ref = odrow.getCell(idxForColumn1).getStringCellValue();
				int actbasis = (int) odrow.getCell(idxForColumn2).getNumericCellValue();
				String actbasisvalue=String.valueOf(actbasis);
				actbasislist.add(actbasisvalue);
				int basisexpvalue;
				
			for(int j=0;j<=sheet1.getLastRowNum();j++) {
				
				String rule_ref = sheet1.getRow(j+1).getCell(0).getStringCellValue();
				Row sheet1row=sheet1.getRow(j+1);
				
				if(rule_ref.equals(actrule_ref))
				{
					 basisexpvalue = (int) sheet1.getRow(sheet1row.getRowNum()).getCell(cellvalue).getNumericCellValue();
					 String expbasisvalue=String.valueOf(basisexpvalue);
				//	System.out.println("rule_ref is " + rule_ref + "respected basis from excel is :" + expbasisvalue);
					expbasislist.add(expbasisvalue);
					break;
				}
			}
			}
			System.out.println("Explist :" + expbasislist + "Actuallist :" + actbasislist);
			return actbasislist.equals(expbasislist);
	}
	public void viewbuttonclick() throws InterruptedException, ClassNotFoundException, SQLException {
		driver.findElement(caricon).click();
		wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		driver.findElement(detailstab).click();
	
		String dbURL = prop.getProperty("dburl");
		String username = prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs = null;
		String selectquery = "SELECT * FROM GBR_Master;";
		rs = st.executeQuery(selectquery);
		rs.next();
	
		Date ref_date = rs.getDate("Reference_Issue_Date");
	
		int month = ref_date.getMonth();
		int day = ref_date.getDate();
		String monthstring = Integer.toString(month);
	
		Thread.sleep(1000);
		// from date selection
		driver.findElement(fromdate).click();
		Select se = new Select(driver.findElement(calendermonth));
		se.selectByValue(monthstring);
		Select se1 = new Select(driver.findElement(calenderyear));
		se1.selectByValue("2022");
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'1')]")).click();
		
		
		// to date selection
		driver.findElement(Todate).click();
		Select se2 = new Select(driver.findElement(calendermonth));
		se2.selectByValue(monthstring);
		Select se3 = new Select(driver.findElement(calenderyear));
		se3.selectByValue("2022");
		driver.findElement(By.xpath("//td[@data-handler='selectDay']/a[contains(text(),'" + (day + 1) + "')]")).click();
	
		driver.findElement(Viewbtn).click();
	}
	public boolean tc_361_371res4() throws InterruptedException, ClassNotFoundException, SQLException, InvalidFormatException {
		viewbuttonclick();
		Thread.sleep(5000);
		Select se11 = new Select(driver.findElement(Entriestxtbx));
		se11.selectByIndex(4);
		
		List<String> dataList=new ArrayList<String>();
		List<String> expbasislist = new ArrayList<String>();
		
		String data=driver.findElement(By.xpath("//*[@id=\"PospListDataTable_info\"]")).getText();
		Thread.sleep(1000);
		String count[]=data.split("of ");
		String datacount[]=count[1].split(" entries");
		String number=datacount[0];
		int num=Integer.parseInt(number);
		 boolean isStringContainsSpecialCharacter =false;
		first:	
			for(int j=0;j<14;j++) {
			
				if(j==4 || j==9)
				{
					continue first;
				}
				for (int i = 0; i < num; i++) {
			
					WebElement elements=driver.findElement(By.xpath("//tbody/tr[" + (i + 1) + "]/td[count(//*[@id='PospListDataTable_wrapper']/div[2]/div/div/div[1]/div/"
							+ "table/thead/tr/th[contains(text(),'OD_Total')]/preceding-sibling::th)+"+(j+1)+"]"));
					String text=elements.getAttribute("innerText").trim();
					//String inputString = "Alive*is*Awesome$";
			        Pattern pattern = Pattern.compile("[^a-z0-9. ]", Pattern.CASE_INSENSITIVE);
			        Matcher matcher = pattern.matcher(text);
			         isStringContainsSpecialCharacter = matcher.find();
					dataList.add(text);
			}
		}
		TestUtil.getTestData1(System.getProperty("user.dir") + "\\CARcalculation.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
			for(int l=0;l<=TestUtil.sheet1.getRow(0).getLastCellNum()-4 ;l++) {
				
				for(int k = 0 ; k <= TestUtil.sheet1.getLastRowNum()-1 ; k++) {
						
					double basisexpvalue = TestUtil.sheet1.getRow(k+1).getCell(l+3).getNumericCellValue();
						
						String expbasisvalue=String.valueOf(basisexpvalue);
						String fixedvalue2;
						if (expbasisvalue.contains("0.0")) {
							fixedvalue2 = expbasisvalue.split("\\.")[0];
						} else if (expbasisvalue.contains(".0")) {
							fixedvalue2 = expbasisvalue.split("\\.")[0];
						} else {
							fixedvalue2 = expbasisvalue;
						}
						expbasislist.add(fixedvalue2);
					
					}
			}
		 System.out.println("actual list  :" +dataList);
		 System.out.println("expected list  :" +expbasislist);
		 return dataList.equals(expbasislist) && !isStringContainsSpecialCharacter;
	}
	
	public  boolean Tc_376res1() throws SQLException, InterruptedException, ClassNotFoundException, InvalidFormatException
	{
		viewbuttonclick();
		Thread.sleep(5000);
		Select se11 = new Select(driver.findElement(Entriestxtbx));
		se11.selectByIndex(4);
		//get username from db
		String dbURL = prop.getProperty("dburl");
		String username = prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs1 = null;
		String selectquery1 = "select * from User_Master where mobile_no='"+prop.getProperty("mobile_no")+"';";
		rs1 = st.executeQuery(selectquery1);
		rs1.next();
		String user_name = rs1.getString("user_name");
		//total row number
		String data=driver.findElement(By.xpath("//*[@id=\"PospListDataTable_info\"]")).getText();
		Thread.sleep(1000);
		String count[]=data.split("of ");
		String datacount[]=count[1].split(" entries");
		String number=datacount[0];
		int num=Integer.parseInt(number);
		
		List<String> user_idlistfromdb=new ArrayList<String>();
		List<String> user_idlistfromviewreport=new ArrayList<String>();
		TestUtil.getTestData1(
				System.getProperty("user.dir") + "\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\sme_rulesheet_basis_mapping.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
	
		//get data from view report
		for (int i = 0; i < num; i++) {
			WebElement expele = driver.findElement(By.xpath("//tbody/tr[" + (i + 1)+ "]/td[count(//*[@id='PospListDataTable_wrapper']/div[2]/div/div/div[1]"
					+ "/div/table/thead/tr/th[contains(text(),'Rule_Reference')]/preceding-sibling::th)+1]"));
			WebElement expele1 = driver.findElement(By.xpath("//tbody/tr["+ (i + 1)+"]/td[count(//*[@id='PospListDataTable_wrapper']/div[2]/div/div/div[1]"
					+ "/div/table/thead/tr/th[contains(text(),'Rule_sheet_user_ID')]/preceding-sibling::th)+1]"));
			
			String rule_ref =expele.getAttribute("innerText") ;
			String rule_sheet_user_id =expele1.getAttribute("innerText") ;
			
			
			for(int j=0;j<=TestUtil.sheet1.getLastRowNum()-1;j++) {
				
				String rule_refexcel = TestUtil.sheet1.getRow(j+1).getCell(0).getStringCellValue();	
				
				if(rule_refexcel.equals(rule_ref))
				{
					user_idlistfromviewreport.add(rule_sheet_user_id);
					user_idlistfromdb.add(user_name);
					break;
				}	
			}
	}
		driver.navigate().refresh();
		
		smegbrdownloadcar();
		Thread.sleep(2000);
		String path1 = downloadedCAR();
		
		Workbook book1 = null;
		Sheet sheet = null;
		
		FileInputStream file1 = null;
		
		try {
			file1 = new FileInputStream(path1);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book1 = WorkbookFactory.create(file1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book1.getSheetAt(0);
		
		Map<String, Integer> map = new HashMap<String, Integer>(); // Create map
		Row row = sheet.getRow(0); // Get first row

		// following is boilerplate from the java doc
		short minColIx = row.getFirstCellNum(); // get the first column index for a row
		short maxColIx = row.getLastCellNum(); // get the last column index for a row
		for (short colIx = minColIx; colIx < maxColIx; colIx++) { // loop from first to last index
			Cell cell = row.getCell(colIx); // get the cell
			map.put(cell.getStringCellValue(), cell.getColumnIndex()); // add the cell contents (name of column) and
																		// cell index to the map
		}
		first:
		for (int i = 0; i < sheet.getLastRowNum()-1; i++) {
			Row row1 = sheet.getRow(i + 1);

			int idxForColumn1 = map.get("Rule_Reference");
			int idxForColumn2 = map.get("Rule_sheet_user_ID");
			Cell cell1 = row1.getCell(idxForColumn1);
			Row odrow = cell1.getRow();
			String actrule_ref = odrow.getCell(idxForColumn1).getStringCellValue();
			String actrule_user_id = odrow.getCell(idxForColumn2).getStringCellValue();
			if(actrule_ref.equals("")) {
				continue first;
			}
			
			for(int j=0;j<=TestUtil.sheet1.getLastRowNum()-1;j++) {
				String rule_refexcel = TestUtil.sheet1.getRow(j+1).getCell(0).getStringCellValue();	
				
				if(rule_refexcel.equals(actrule_ref))
				{
					user_idlistfromviewreport.add(actrule_user_id);
					user_idlistfromdb.add(user_name);
					break;
				}	
			}
			
			
		}
		return user_idlistfromviewreport.equals(user_idlistfromdb);
		
	}
	public boolean Tc_377() throws SQLException, InterruptedException, ClassNotFoundException, InvalidFormatException
	{
		viewbuttonclick();
		Thread.sleep(5000);
		Select se11 = new Select(driver.findElement(Entriestxtbx));
		se11.selectByIndex(4);
		//get username from db
		String dbURL = prop.getProperty("dburl");
		String username = prop.getProperty("DBusename");
		String Password = prop.getProperty("DBPassword");
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbURL, username, Password);
		Statement st = con.createStatement();
		ResultSet rs1 = null;
		String selectquery1 = "SELECT * FROM Rule_Sheet_Master;";
		rs1 = st.executeQuery(selectquery1);
		rs1.next();
		Date creation_date = rs1.getDate("Created_on");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");  
	    String strDate= formatter.format(creation_date);  
	    
	    System.out.println("strdate is:" + strDate);
	    
		//total row number
		String data=driver.findElement(By.xpath("//*[@id=\"PospListDataTable_info\"]")).getText();
		Thread.sleep(1000);
		String count[]=data.split("of ");
		String datacount[]=count[1].split(" entries");
		String number=datacount[0];
		int num=Integer.parseInt(number);
		
		List<String> user_idlistfromdb=new ArrayList<String>();
		List<String> user_idlistfromviewreport=new ArrayList<String>();
		TestUtil.getTestData1(
				System.getProperty("user.dir") + "\\src\\main\\java\\qa\\com\\recon\\docs\\Car ddoc\\sme_rulesheet_basis_mapping.xlsx");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
	
		//get data from view report
		for (int i = 0; i < num; i++) {
			WebElement expele = driver.findElement(By.xpath("//tbody/tr[" + (i + 1)+ "]/td[count(//*[@id='PospListDataTable_wrapper']/div[2]/div/div/div[1]"
					+ "/div/table/thead/tr/th[contains(text(),'Rule_Reference')]/preceding-sibling::th)+1]"));
			WebElement expele1 = driver.findElement(By.xpath("//tbody/tr["+ (i + 1)+"]/td[count(//*[@id='PospListDataTable_wrapper']/div[2]/div/div/div[1]"
					+ "/div/table/thead/tr/th[contains(text(),'Rule_sheet_date_created')]/preceding-sibling::th)+1]"));
			
			String rule_ref =expele.getAttribute("innerText") ;
			String rule_sheet_user_id =expele1.getAttribute("innerText") ;
			
			
			for(int j=0;j<=TestUtil.sheet1.getLastRowNum()-1;j++) {
				
				String rule_refexcel = TestUtil.sheet1.getRow(j+1).getCell(0).getStringCellValue();	
				
				if(rule_refexcel.equals(rule_ref))
				{
					user_idlistfromviewreport.add(rule_sheet_user_id);
					user_idlistfromdb.add(strDate);
					break;
				}	
			}
	}
		driver.navigate().refresh();
		
		smegbrdownloadcar();
		Thread.sleep(2000);
		String path1 = downloadedCAR();
		
		Workbook book1 = null;
		Sheet sheet = null;
		
		FileInputStream file1 = null;
		
		try {
			file1 = new FileInputStream(path1);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book1 = WorkbookFactory.create(file1);
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book1.getSheetAt(0);
		
		Map<String, Integer> map = new HashMap<String, Integer>(); // Create map
		Row row = sheet.getRow(0); // Get first row

		// following is boilerplate from the java doc
		short minColIx = row.getFirstCellNum(); // get the first column index for a row
		short maxColIx = row.getLastCellNum(); // get the last column index for a row
		for (short colIx = minColIx; colIx < maxColIx; colIx++) { // loop from first to last index
			Cell cell = row.getCell(colIx); // get the cell
			map.put(cell.getStringCellValue(), cell.getColumnIndex()); // add the cell contents (name of column) and
																		// cell index to the map
		}
		first:
		for (int i = 0; i < sheet.getLastRowNum()-1; i++) {
			Row row1 = sheet.getRow(i + 1);

			int idxForColumn1 = map.get("Rule_Reference");
			int idxForColumn2 = map.get("Rule_sheet_date_created");
			Cell cell1 = row1.getCell(idxForColumn1);
			Row odrow = cell1.getRow();
			String actrule_ref = odrow.getCell(idxForColumn1).getStringCellValue();
			String actrule_user_id = odrow.getCell(idxForColumn2).getStringCellValue();
			if(actrule_ref.equals("")) {
				continue first;
			}
			
			for(int j=0;j<=TestUtil.sheet1.getLastRowNum()-1;j++) {
				String rule_refexcel = TestUtil.sheet1.getRow(j+1).getCell(0).getStringCellValue();	
				
				if(rule_refexcel.equals(actrule_ref))
				{
					user_idlistfromviewreport.add(actrule_user_id);
					user_idlistfromdb.add(strDate);
					break;
				}	
			}
			
			
		}
		System.out.println("liosts are : " + user_idlistfromviewreport+user_idlistfromdb);
		return user_idlistfromviewreport.equals(user_idlistfromdb);
		
	}
	public void tc_378() throws ClassNotFoundException, InterruptedException, SQLException, InvalidFormatException
	{
//		smegbrdownloadcar();
		String path1 = downloadedCAR();
		TestUtil.getTestData1("C:\\Users\\Lenovo\\eclipse-workspace\\Recon\\downloadedexcel\\CAR_2022_08_25_12_01_29.xls");
		TestUtil.sheet1 = TestUtil.book1.getSheetAt(0);
//		
//		driver.navigate().refresh();
//		viewbuttonclick();
//		Thread.sleep(3000);
//		
//		String data=driver.findElement(By.xpath("//*[@id=\"PospListDataTable_info\"]")).getText();
//		Thread.sleep(1000);
//		String count[]=data.split("of ");
//		String datacount[]=count[1].split(" entries");
//		String number=datacount[0];
//		int num=Integer.parseInt(number);
//		
		List<String> totalvalueslist=new ArrayList<String>();
		List<String> exptotalvaluelist=new ArrayList<String>();
//		
		
		
		Map<String, Integer> map = new HashMap<String, Integer>(); // Create map
		Row row = TestUtil.sheet1.getRow(0); // Get first row

		// following is boilerplate from the java doc
		short minColIx = row.getFirstCellNum(); // get the first column index for a row
		short maxColIx = row.getLastCellNum(); // get the last column index for a row
		for (short colIx = minColIx; colIx < maxColIx; colIx++) { // loop from first to last index
			Cell cell = row.getCell(colIx); // get the cell
			map.put(cell.getStringCellValue(), cell.getColumnIndex()); // add the cell contents (name of column) and
																		// cell index to the map
		}
		
		String ODaddedvalue = null ,TP_totaladded = null,Fixed_value_totaladded = null,Total_Valueadded = null,OD_rate_1added = null,TP_rate_1added = null,Fixed_value_1added = null
		,ValueAadded = null,OD_rate_2added = null,TP_rate_2added = null,Fixed_value_2added = null,ValueBadded= "";
		first:
			
		for(int j=0;j <= TestUtil.sheet1.getLastRowNum()-1 ;j++) {
			Row row1 = TestUtil.sheet1.getRow(j + 1);
			int idxForColumn1 = map.get("OD_total");
			double OD_total = row1.getCell(idxForColumn1).getNumericCellValue();
			int idxForColumn2 = map.get("TP_total");
			double TP_total = row1.getCell(idxForColumn2).getNumericCellValue();
			int idxForColumn3 = map.get("Fixed_value_total");
			double Fixed_value_total = row1.getCell(idxForColumn3).getNumericCellValue();
			int idxForColumn4 = map.get("Total_Value");
			double Total_Value = row1.getCell(idxForColumn4).getNumericCellValue();
			int idxForColumn5 = map.get("OD_rate_1");
			double OD_rate_1 = row1.getCell(idxForColumn5).getNumericCellValue();
			int idxForColumn6 = map.get("TP_rate_1");
			double TP_rate_1 = row1.getCell(idxForColumn6).getNumericCellValue();
			int idxForColumn7 = map.get("Fixed_value_1");
			double Fixed_value_1 = row1.getCell(idxForColumn7).getNumericCellValue();
			int idxForColumn8 = map.get("ValueA");
			double ValueA = row1.getCell(idxForColumn8).getNumericCellValue();
			int idxForColumn9 = map.get("OD_rate_2");
			double OD_rate_2 = row1.getCell(idxForColumn9).getNumericCellValue();
			int idxForColumn10 = map.get("TP_rate_2");
			double TP_rate_2 = row1.getCell(idxForColumn10).getNumericCellValue();
			int idxForColumn11 = map.get("Fixed_value_2");
			double Fixed_value_2 = row1.getCell(idxForColumn11).getNumericCellValue();
			int idxForColumn12 = map.get("ValueB");
			double ValueB = row1.getCell(idxForColumn12).getNumericCellValue();
			
				double ODtotalvalue =0;ODtotalvalue=ODtotalvalue+OD_total;
				String z=String.valueOf(ODtotalvalue);
				if (z.contains("0.0")) {
					ODaddedvalue = z.split("\\.")[0];
				} else if (z.contains(".0")) {
					ODaddedvalue = z.split("\\.")[0];
				} else {
					ODaddedvalue = z;
				}
				double TPtotalvalue =0;TPtotalvalue=TPtotalvalue+TP_total;
				String x=String.valueOf(TPtotalvalue);
				if (x.contains("0.0")) {
					TP_totaladded = x.split("\\.")[0];
				} else if (x.contains(".0")) {
					TP_totaladded = x.split("\\.")[0];
				} else {
					TP_totaladded = x;
				}
				double fvtotalvalue =0;fvtotalvalue=fvtotalvalue+Fixed_value_total;
				Fixed_value_totaladded=String.valueOf(fvtotalvalue);
				
				double tvtotalvalue =0;tvtotalvalue=tvtotalvalue+Total_Value;
				Total_Valueadded=String.valueOf(tvtotalvalue);
			
				double or1totalvalue =0;or1totalvalue=or1totalvalue+OD_rate_1;
				OD_rate_1added=String.valueOf(or1totalvalue);
				double tp1totalvalue =0;tp1totalvalue=tp1totalvalue+TP_rate_1;
				TP_rate_1added=String.valueOf(tp1totalvalue);
				double fv1totalvalue =0;fv1totalvalue=fv1totalvalue+Fixed_value_1;
				Fixed_value_1added=String.valueOf(fv1totalvalue);
				double vatotalvalue =0;vatotalvalue=vatotalvalue+ValueA;
				ValueAadded=String.valueOf(vatotalvalue);
				double od2totalvalue =0;od2totalvalue=od2totalvalue+OD_rate_2;
				OD_rate_2added=String.valueOf(od2totalvalue);
				double tp2totalvalue =0;tp2totalvalue=tp2totalvalue+TP_rate_2;
				TP_rate_2added=String.valueOf(tp2totalvalue);
				double fv2totalvalue =0;fv2totalvalue=fv2totalvalue+Fixed_value_2;
				Fixed_value_2added=String.valueOf(fv2totalvalue);
				double vbtotalvalue =0;vbtotalvalue=vbtotalvalue+ValueB;
				ValueBadded=String.valueOf(vbtotalvalue);
				
		}
		exptotalvaluelist.add(ODaddedvalue);
		exptotalvaluelist.add(TP_totaladded);
		exptotalvaluelist.add(Fixed_value_totaladded);
		exptotalvaluelist.add(Total_Valueadded);
		exptotalvaluelist.add(OD_rate_1added);
		exptotalvaluelist.add(TP_rate_1added);
		exptotalvaluelist.add(Fixed_value_1added);
		exptotalvaluelist.add(ValueAadded);
		exptotalvaluelist.add(OD_rate_2added);
		exptotalvaluelist.add(TP_rate_2added);
		exptotalvaluelist.add(Fixed_value_2added);
		exptotalvaluelist.add(ValueBadded);
		
		
		System.out.println("list is :" + exptotalvaluelist);
		
		
//		second:
//		for(int i = 0 ; i <= 13 ; i++) {
//		WebElement totalelement =driver.findElement(By.xpath("//div[@class='dataTables_scrollFootInner']/table/tfoot/tr/th[1"+(i+24)+"]"));
//		
//		
//		if(i==4 && i==10) {
//			continue second;
//		}
//		else {
//		String total=totalelement.getAttribute("innertext");
//		totalvalueslist.add(total); 
//		}
//		//skip 128,133 
//		}
		
	}



}
