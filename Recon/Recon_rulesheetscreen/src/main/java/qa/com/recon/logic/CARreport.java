package qa.com.recon.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import qa.com.recon.base.TestBase;




public class CARreport extends TestBase {

	By caricon=By.xpath("//i[@class='fa fa-automobile']");
	By cardashboardclick=By.xpath("//a[@href='/CarDashboards']");
	By detailstab=By.xpath("//a[@id='Details-tab']");//a[contains(text(),'Details')]
	By fromdate=By.id("FromDate");
	By Todate=By.id("ToDate");
	By calendarmonth=By.xpath("//*[@id=\"ui-datepicker-div\"]/div[1]/div/select[1]");
	By calendaryear=By.xpath("//select[@data-handler='selectYear']");////*[@id="ui-datepicker-div"]/div[1]/div/select[2]
	By calendardates=By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr/td");
	By Viewbtn=By.id("btnViewDetailsReport");
	By entriesdropdown=By.xpath("//select[@name='PospListDataTable_length']");
	By downlodbtn=By.xpath("//*[@id=\"btnDownloadReport\"]");
	By columnread=By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr/td[3][contains(text(),'"+1+"')]");
	By ruleconditioncolumn=By.xpath("//*[@id=\"PospListDataTable\"]/tbody/tr/td[138][contains(text(),'6')]");
	By totalrow=By.xpath("//th[contains(text(),'Total :')]");
	By infobox=By.xpath("/html/body/div[3]/div");
	By errortitle=By.xpath("/html/body/div[2]/div/h2");
	
	public WebDriverWait wait;
	public void datefilter() throws InterruptedException
	{
		driver.findElement(caricon).click();
		wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.presenceOfElementLocated(cardashboardclick));
		driver.findElement(cardashboardclick).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.presenceOfElementLocated(detailstab));
		driver.findElement(detailstab).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(fromdate));
		
		driver.findElement(fromdate).click();
		driver.findElement(calendarmonth).click();
		Select monthoptions=new Select(driver.findElement(calendarmonth));
		monthoptions.selectByIndex(1);
		
		Select yearoptions=new Select(driver.findElement(calendaryear));
		yearoptions.selectByIndex(1);
		
		driver.findElement(By.xpath("//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[1]/td/a[contains(text(),'1')]")).click();
		
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		 Date date = new Date();
//		 String expdate1= dateFormat.format(date);
//		 System.out.println(expdate1);
//		driver.findElement(Todate).sendKeys(expdate1);
//		String actualdate=driver.findElement(Todate).getText();
	}
	public boolean viewcarreport() throws InterruptedException
	{
		datefilter();
		driver.findElement(Viewbtn).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(totalrow));
		return driver.findElement(totalrow).isDisplayed();
	}
	public boolean downloadcar() throws InterruptedException
	{
		datefilter();
		Thread.sleep(2000);
		driver.findElement(downlodbtn).click();
		Thread.sleep(1000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(infobox));
		String msg=null;
		if(driver.findElement(infobox).isDisplayed()) {
		 msg=driver.findElement(infobox).getText();
		}else {
			System.out.println("errormsg is not displayed");
		}
		return msg.contains("Success");
	}
	
	
	 
	 public static String downloadedCAR()
	 {
	     File directory = new File( System.getProperty("user.dir")+ "\\downloadedexcel\\");
	     File[] files = directory.listFiles(File::isFile);
	     long lastModifiedTime = Long.MIN_VALUE;
	     File chosenFile = null;
	     String pathtobeused=null;

	     if (files != null)
	     {
	         for (File file : files)
	         {
	             if (file.lastModified() > lastModifiedTime)
	             {
	                 chosenFile = file;
	                 pathtobeused=chosenFile.getPath();
	                 lastModifiedTime = file.lastModified();
	                 
	                 
	             }
	         }
	     }
	 //  System.out.println("choosen file is: " + chosenFile + "lastmodifiedtime is : " + lastModifiedTime);
	     return pathtobeused;
	 }
	
	
	public  boolean CARcomparison() throws InterruptedException
	{
		 Workbook book1 = null;
		 Workbook book2 = null;
		 Sheet sheet=null;
		 Sheet sheet1=null;
		 String path=downloadedCAR();
		 System.out.println("path of system generated CAR excel : " + path);
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
			
			String inwardno1="";
			
			Map<String, Integer> map = new HashMap<String,Integer>(); //Create map
			Row row = sheet.getRow(0); //Get first row
			
			//following is boilerplate from the java doc
			short minColIx = row.getFirstCellNum(); //get the first column index for a row
			short maxColIx = row.getLastCellNum(); //get the last column index for a row
			for(short colIx=minColIx; colIx<maxColIx; colIx++) { //loop from first to last index
			Cell cell = row.getCell(colIx); //get the cell
			map.put(cell.getStringCellValue(),cell.getColumnIndex()); //add the cell contents (name of column) and cell index to the map
			}
			System.out.println("downloaded car row no " + sheet.getLastRowNum());	
	
				for(int i=0 ; i < sheet.getLastRowNum()-1 ; i++) {
					Row row1=sheet.getRow(i+1);
					int idxForColumn1= map.get("Inward_No");
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
					 
					 Cell inwardcell1=row1.getCell(idxForColumn1);
						
					if(inwardcell1!=null) {
						
						inwardcell1.setCellType(CellType.STRING);
						
						Row inwardcellrow=inwardcell1.getRow();
						
						Cell Rule_refcell1=inwardcellrow.getCell(idxForColumn2);
						Cell Rule_conditioncell1=inwardcellrow.getCell(idxForColumn3);
						Cell OD_totalcell1=inwardcellrow.getCell(idxForColumn4);
						Cell TP_totalcell1=inwardcellrow.getCell(idxForColumn5);
						Cell Fixed_value_totalcell1=inwardcellrow.getCell(idxForColumn6);
						Cell Total_Valuecell1=inwardcellrow.getCell(idxForColumn7);
						Cell OD_rate_1cell1=inwardcellrow.getCell(idxForColumn8);
						Cell TP_rate_1cell1=inwardcellrow.getCell(idxForColumn9);
						Cell Fixed_value_1cell1=inwardcellrow.getCell(idxForColumn10);
						Cell ValueAcell1=inwardcellrow.getCell(idxForColumn11);
						Cell OD_rate_2cell1=inwardcellrow.getCell(idxForColumn12);
						Cell TP_rate_2cell1=inwardcellrow.getCell(idxForColumn13);
						Cell Fixed_value_2cell1=inwardcellrow.getCell(idxForColumn14);
						Cell ValueBcell1=inwardcellrow.getCell(idxForColumn15);
						
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
						
						inwardno1=inwardcell1.getStringCellValue();
						//String rulecond1;
						//System.out.println("inwardsheet 1 is " + inwardno1);
						String ruleref1=Rule_refcell1.getStringCellValue();
						String rulecond1=Rule_conditioncell1.getStringCellValue();
						
						String odtotal1=OD_totalcell1.getStringCellValue();
						String tptotal1=TP_totalcell1.getStringCellValue();
						String fixedvaluetotal=Fixed_value_totalcell1.getStringCellValue();
						String totalvalue=Total_Valuecell1.getStringCellValue();
						String odrate1=OD_rate_1cell1.getStringCellValue();
						String tprate1=TP_rate_1cell1.getStringCellValue();
						String fixedvalue1=Fixed_value_1cell1.getStringCellValue();
						String valuea=ValueAcell1.getStringCellValue();
						String odrate2=OD_rate_2cell1.getStringCellValue();
						String tprate2=TP_rate_2cell1.getStringCellValue();
						String fixedvalue2=Fixed_value_2cell1.getStringCellValue();
						String valueb=ValueBcell1.getStringCellValue();
						
					
						MyObject rulesheet1=new MyObject();
						rulesheet1.inwardno=inwardno1;
						rulesheet1.rule_refrence=ruleref1;
						rulesheet1.rule_condition=rulecond1;
						rulesheet1.od_total=odtotal1;
						rulesheet1.tp_total=tptotal1;
						rulesheet1.fixed_value_total=fixedvaluetotal;
						rulesheet1.Total_Value=totalvalue;
						rulesheet1.OD_rate_1=odrate1;
						rulesheet1.TP_rate_1=tprate1;
						rulesheet1.Fixed_value_1=fixedvalue1;
						rulesheet1.ValueA=valuea;
						rulesheet1.OD_rate_2=odrate2;
						rulesheet1.TP_rate_2=tprate2;
						rulesheet1.Fixed_value_2=fixedvalue2;
						rulesheet1.ValueB=valueb;
						
						myObjList1.add(rulesheet1);
						
					}
					else {
						System.out.println("didn't find data in sheet1");
					}
			
				}
					System.out.println("calculated CAR row no" + sheet1.getPhysicalNumberOfRows());
					for(int j=0;j<sheet1.getPhysicalNumberOfRows()-1;j++)
					{
				
						Row row2 = sheet1.getRow(j+1);
						
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
						Cell inwardcell2=row2.getCell(0);
						Cell Rule_refcell2=row2.getCell(1);
						Cell Rule_conditioncell2=row2.getCell(2);
						Cell OD_totalcell2=row2.getCell(3);
						Cell TP_totalcell2=row2.getCell(4);
						Cell Fixed_value_totalcell2=row2.getCell(5);
						Cell Total_Valuecell2=row2.getCell(6);
						Cell OD_rate_1cell2=row2.getCell(7);
						Cell TP_rate_1cell2=row2.getCell(8);
						Cell Fixed_value_1cell2=row2.getCell(9);
						Cell ValueAcell2=row2.getCell(10);
						Cell OD_rate_2cell2=row2.getCell(11);
						Cell TP_rate_2cell2=row2.getCell(12);
						Cell Fixed_value_2cell2=row2.getCell(13);
						Cell ValueBcell2=row2.getCell(14);
						
						List<Cell> cellarray=new ArrayList<Cell>();
						
						
						if(inwardcell2!=null) {
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
							
							
							
							String inwardno2=inwardcell2.getStringCellValue();
							//System.out.println("inwardsheet 2 is " + inwardno2);
							String v=Rule_refcell2.getStringCellValue();
							String x=Rule_conditioncell2.getStringCellValue();
							if(v.contains(".") ) {
								 rule_ref2=v.split("\\.")[0];
							}
							else
							{
								rule_ref2=v;
							}
							if(x.contains(".")) {
								rule_cond2=x.split("\\.")[0];
							}
							else {
								rule_cond2=x;
							}
							//System.out.println("rule_ref2 is " + rule_ref2 + "rule_cond2 is : " + rule_cond2);
							String h=OD_totalcell2.getStringCellValue();
							if(h.contains("0.0"))
							{
								 od_total=h.split("\\.")[0];								
							}
							else{od_total=h;}
							String c=TP_totalcell2.getStringCellValue();
							if(c.contains("0.0"))
							{
								 tp_total=c.split("\\.")[0];								
							}
							else{tp_total=c;}
							String k=Fixed_value_totalcell2.getStringCellValue();
							if(k.contains("0.0"))
							{
								 fixed_value_total=k.split("\\.")[0];								
							}
							else{fixed_value_total=k;}
							String e=Total_Valuecell2.getStringCellValue();
							if(e.contains("0.0"))
							{
								 total_value=e.split("\\.")[0];								
							}
							else{total_value=e;}
							String w=OD_rate_1cell2.getStringCellValue();
							if(w.contains("0.0"))
							{
								 od_rate1=w.split("\\.")[0];								
							}
							else{od_rate1=w;}
							String q=TP_rate_1cell2.getStringCellValue();
							if(q.contains("0.0"))
							{
								 tp_rate1=q.split("\\.")[0];								
							}
							else{tp_rate1=q;}
							String v1=Fixed_value_1cell2.getStringCellValue();
							if(v1.contains("0.0"))
							{
								fixed_value1=v1.split("\\.")[0];								
							}
							else{fixed_value1=v1;}	
							String z=ValueAcell2.getStringCellValue();
							if(z.contains("0.0"))
							{
								valuea=z.split("\\.")[0];								
							}
							else{valuea=z;}
							String x1=OD_rate_2cell2.getStringCellValue();
							if(x1.contains("0.0"))
							{
								odrate2=x1.split("\\.")[0];								
							}
							else{odrate2=x1;}
							String b=TP_rate_2cell2.getStringCellValue();
							if(b.contains("0.0"))
							{
								tprate2=b.split("\\.")[0];								
							}
							else{tprate2=b;}
							String n=Fixed_value_2cell2.getStringCellValue();
							if(n.contains("0.0"))
							{
								fixedvalue2=n.split("\\.")[0];								
							}
							else{fixedvalue2=n;}
							String m=ValueBcell2.getStringCellValue();
							if(m.contains("0.0"))
							{
								valueb=m.split("\\.")[0];								
							}
							else{valueb=m;}							
							
							MyObject rulesheet2=new MyObject();
							rulesheet2.inwardno=inwardno2;
							rulesheet2.rule_refrence=rule_ref2;
							rulesheet2.rule_condition=rule_cond2;
							rulesheet2.od_total=od_total;
							rulesheet2.tp_total=tp_total;
							rulesheet2.fixed_value_total=fixed_value_total;
							rulesheet2.Total_Value=total_value;
							rulesheet2.OD_rate_1=od_rate1;
							rulesheet2.TP_rate_1=tp_rate1;
							rulesheet2.Fixed_value_1=fixed_value1;
							rulesheet2.ValueA=valuea;
							rulesheet2.OD_rate_2=odrate2;
							rulesheet2.TP_rate_2=tprate2;
							rulesheet2.Fixed_value_2=fixedvalue2;
							rulesheet2.ValueB=valueb;
						
							myObjList2.add(rulesheet2);
							
							
						}
						else {
							System.out.println("didn't find data in sheet2");
						}
						
					}
					
					boolean flag = false;
				//	System.out.println("mylist 2 is : " + myObjList2 +"myObjList2 " + myObjList2);
					
					 for (int d = 0; d < myObjList2.size(); d++) {
						// System.out.println("siz2 :" +myObjList2.size());
						  String inwardnosheet2=myObjList2.get(d).inwardno.toString();
						 // System.out.println("inwardnosheet2");
						  String rulerefsheet2=myObjList2.get(d).rule_refrence.toString();
						  String rulecondsheet2=myObjList2.get(d).rule_condition.toString();
						  String odtotalsheet2=myObjList2.get(d).od_total.toString();
						  String tptotalsheet2=myObjList2.get(d).tp_total.toString();
						  String fixedvaluetotalsheet2=myObjList2.get(d).fixed_value_total.toString();
						  String totalvaluesheet2=myObjList2.get(d).Total_Value.toString();
						  String odrate1sheet2=myObjList2.get(d).OD_rate_1.toString();
						  String tprate1sheet2=myObjList2.get(d).TP_rate_1.toString();
						  String fixedvalue1sheet2=myObjList2.get(d).Fixed_value_1.toString();
						  String valueasheet2=myObjList2.get(d).ValueA.toString();
						  String odrate2sheet2=myObjList2.get(d).OD_rate_2.toString();
						  String tprate2sheet2=myObjList2.get(d).TP_rate_2.toString();
						  String fixedvalue2sheet2=myObjList2.get(d).Fixed_value_2.toString();
						  String valuebsheet2=myObjList2.get(d).ValueB.toString();
						 
						  
						//  System.out.println(" sheet 2 data : " + inwardnosheet2 + ", "+rulerefsheet2  );
							
						for(int l=0; l <myObjList1.size() ;l++ ) {
						//	System.out.println("size1 :" +myObjList1.size());
							 String inwardnosheet1=myObjList1.get(l).inwardno.toString();
							  String rulerefsheet1=myObjList1.get(l).rule_refrence.toString();
							  String rulecondsheet1=myObjList1.get(l).rule_condition.toString();
							  String odtotalsheet1=myObjList1.get(l).od_total.toString();
							  String tptotalsheet1=myObjList1.get(l).tp_total.toString();
							  String fixedvaluetotalsheet1=myObjList1.get(l).fixed_value_total.toString();
							  String totalvaluesheet1=myObjList1.get(l).Total_Value.toString();
							  String odrate1sheet1=myObjList1.get(l).OD_rate_1.toString();
							  String tprate1sheet1=myObjList1.get(l).TP_rate_1.toString();
							  String fixedvalue1sheet1=myObjList1.get(l).Fixed_value_1.toString();
							  String valueasheet1=myObjList1.get(l).ValueA.toString();
							  String odrate2sheet1=myObjList1.get(l).OD_rate_2.toString();
							  String tprate2sheet1=myObjList1.get(l).TP_rate_2.toString();
							  String fixedvalue2sheet1=myObjList1.get(l).Fixed_value_2.toString();
							  String valuebsheet1=myObjList1.get(l).ValueB.toString();
							//  System.out.println(" sheet1  data : " + inwardnosheet1 + ", "+rulerefsheet1  );
							  
						  if (inwardnosheet2.equals(inwardnosheet1) && rulerefsheet2.equals(rulerefsheet1) && rulecondsheet2.equals(rulecondsheet1) && odtotalsheet2.equals(odtotalsheet1)){
							//&& tptotalsheet2.equals(tptotalsheet1) &&  fixedvaluetotalsheet2.equals(fixedvaluetotalsheet1) && totalvaluesheet2.equals(totalvaluesheet1)
							//&& odrate1sheet2.equals(odrate1sheet1) && tprate1sheet2.equals(tprate1sheet1) &&
							//  fixedvalue1sheet2.equals(fixedvalue1sheet1) && valueasheet2.equals(valueasheet1) && odrate2sheet2.equals(odrate2sheet1)
							 // && tprate2sheet2.equals(tprate2sheet1) && fixedvalue2sheet2.equals(fixedvalue2sheet1) && valuebsheet2.equals(valuebsheet1)) {
						  
							  flag=inwardnosheet2.equals(inwardnosheet1) && rulerefsheet2.equals(rulerefsheet1) && rulecondsheet2.equals(rulecondsheet1) && odtotalsheet2.equals(odtotalsheet1)
										&& tptotalsheet2.equals(tptotalsheet1) &&  fixedvaluetotalsheet2.equals(fixedvaluetotalsheet1) && totalvaluesheet2.equals(totalvaluesheet1)
										&& odrate1sheet2.equals(odrate1sheet1) && tprate1sheet2.equals(tprate1sheet1) &&
										  fixedvalue1sheet2.equals(fixedvalue1sheet1) && valueasheet2.equals(valueasheet1) && odrate2sheet2.equals(odrate2sheet1)
										  && tprate2sheet2.equals(tprate2sheet1) && fixedvalue2sheet2.equals(fixedvalue2sheet1) && valuebsheet2.equals(valuebsheet1);
							//  System.out.println("flag is " + flag);
							  System.out.println("If part sheet 2 data : " + inwardnosheet2 + ", "+rulerefsheet2 +  ","+ odtotalsheet2 +"," + rulecondsheet2+ tptotalsheet2 +fixedvaluetotalsheet2 + odrate1sheet2 + tprate1sheet2 +
									  fixedvalue1sheet2+valueasheet2 + odrate2sheet2+
									  tprate2sheet2 +fixedvalue2sheet2 + valuebsheet2 +"  sheet1 data: " + inwardnosheet1 +", "+ rulerefsheet1 +","+odtotalsheet1 +","+rulecondsheet1 + tptotalsheet1 + fixedvaluetotalsheet1 +odrate1sheet1 + tprate1sheet1
									  +fixedvalue1sheet1+valueasheet1 + odrate2sheet1 + tprate2sheet1 +fixedvalue2sheet1 + valuebsheet1);
							  System.out.println("Data matched");
							  break;
				            }
						  else {
						//   System.out.println("else part sheet 2 data : " + inwardnosheet2 + ", "+rulerefsheet2 +  ","+ odtotalsheet2 +"," + rulecondsheet1+" sheet1 data: " + inwardnosheet1 +", "+ rulerefsheet1 +","+odtotalsheet1);
						  }
						}
						}
										
				return 	flag;	
					  
			}
	
	
	public void CARcalculation() throws ClassNotFoundException, SQLException, InterruptedException, IOException
	{
		//List<String> ruleref=main1();
		 org.apache.poi.ss.usermodel.Workbook book = null;
		 Sheet sheet=null;
		 System.out.println("path of mapping sheet: " +path2);
			FileInputStream file = null;
			try {
					file = new FileInputStream(path2);
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
			
			
		//System.out.println(ruleref);
		String dbURL =prop.getProperty("dburl");
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
		int rowNumber =0;

		XSSFWorkbook workbook1 = new XSSFWorkbook();
		XSSFSheet Sheet1 = workbook1.createSheet("CAR");
		String CARpath;
		FileOutputStream fos = null;
//		System.out.println("last row number is : " +sheet.getLastRowNum());
//		System.out.println("physical row number is : " +sheet.getPhysicalNumberOfRows());
		
		String[] colheadings = { "Inward_No", "Rule_ref", "Rule_condition", "OD_total",
				"TP_total", "Fixed_value_total","Total_Value","OD_rate_1","TP_rate_1","Fixed_value_1","ValueA"
				,"OD_rate_2","TP_rate_2" ,"Fixed_value_2","ValueB"};
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
		
			String inward=sheet.getRow(i+1).getCell(0).toString();
			double rule_ref=sheet.getRow(i+1).getCell(1).getNumericCellValue();
			
			 long v = (long) rule_ref;
			
			 rowNumber=rowNumber+1;
				
				XSSFRow row2 = Sheet1.createRow(rowNumber);
				
				Cell inwardcell2=row2.createCell(0);
				Cell Rule_refcell2=row2.createCell(1);
				Cell Rule_conditioncell2=row2.createCell(2);
				Cell OD_totalcell2=row2.createCell(3);
				Cell TP_totalcell2=row2.createCell(4);
				Cell Fixed_value_totalcell2=row2.createCell(5);
				Cell Total_Valuecell2=row2.createCell(6);
				Cell OD_rate_1cell2=row2.createCell(7);
				Cell TP_rate_1cell2=row2.createCell(8);
				Cell Fixed_value_1cell2=row2.createCell(9);
				Cell ValueAcell2=row2.createCell(10);
				Cell OD_rate_2cell2=row2.createCell(11);
				Cell TP_rate_2cell2=row2.createCell(12);
				Cell Fixed_value_2cell2=row2.createCell(13);
				Cell ValueBcell2=row2.createCell(14);
			 
				if(v==0) {
				 
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
							
							CARpath=System.getProperty("user.dir") + "\\CARcalculation.xlsx";
							
							fos = new FileOutputStream(CARpath);
							
							workbook1.write(fos);
					}
			 else {
			
		//	System.out.println("value of v after if : " + v  + "inward value is : " + inward );
			selectquery = "SELECT * FROM Rule_Sheet_Master where Rule_Reference ='"+v+"' and Is_deleted=0 order by Updated_on desc;";
			gbrquery="select * from GBR_Master where Inward_no='"+inward+"' and GBR_Is_Deleted=0;";
		//	System.out.println("rs query is : " +  selectquery  +  "rs1 quesry is " +  gbrquery);
			 rs = st.executeQuery(selectquery);
			rs1 = st1.executeQuery(gbrquery);
			
		rs.next();
		rs1.next();
		double odvalue=rs1.getDouble("OD");
		double addonvalue=rs1.getDouble("ADD_ON");
		double tpvalue=rs1.getDouble("TP");
		double terrorismvalue=rs1.getDouble("terrorism");
		
		 double rule_id=rs.getDouble("id");
		 double odrate=rs.getDouble("OD_total");
		 double tprate=rs.getDouble("TP_total");
		 double fixvalue=rs.getDouble("Fixed_value_total");
		 double odrate1=rs.getDouble("OD_rate_1_commission_per");
		 double tprate1=rs.getDouble("TP_rate_1_commission_per");
		 double fixvalue1=rs.getDouble("Fixed_value_Basis_1");
		 double odrate2=rs.getDouble("OD_rate_2_commission_per");
		 double tprate2=rs.getDouble("TP_rate_2_commission_per");
		 double fixvalue2=rs.getDouble("Fixed_value_Basis_2");
		 
		 double OD_total=(odvalue+addonvalue)*odrate/100;
		
		 double TP_total=(tpvalue+terrorismvalue)*tprate/100;
	
		 double Total_value=OD_total + TP_total + fixvalue;
		
		 double OD_rate_1=(odvalue+addonvalue)*odrate1/100;
		
		 double TP_rate_1=(tpvalue+terrorismvalue)*tprate1/100;
		
		 double valueA=OD_rate_1 + TP_rate_1 + fixvalue1;
		
		 double OD_rate_2=(odvalue+addonvalue)*odrate2/100;
		
		 double TP_rate_2=(tpvalue+terrorismvalue)*tprate2/100;
		 double valueB=OD_rate_2 + TP_rate_2 + fixvalue2;
		
					inwardcell2.setCellValue(inward);
					Rule_refcell2.setCellValue(v);
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
					
					CARpath=System.getProperty("user.dir") + "\\CARcalculation.xlsx";
					
					fos = new FileOutputStream(CARpath);
					
					workbook1.write(fos);
					
				//	System.out.println("value of i is: " + i);
					
		}
		fos.close();
		
		
		}
		con.close();

}
	
	
		
}
 