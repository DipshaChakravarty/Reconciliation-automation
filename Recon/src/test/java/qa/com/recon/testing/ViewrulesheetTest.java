package qa.com.recon.testing;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.opencsv.exceptions.CsvValidationException;

import qa.com.recon.base.TestBase;
import qa.com.recon.logic.CARreport;
import qa.com.recon.logic.Loginrecon;
import qa.com.recon.logic.Rulesheetingestion;
import qa.com.recon.logic.ViewnEditRulesheet;
import qa.com.recon.utility.TestUtil;

public class ViewrulesheetTest extends TestBase {

	public ViewrulesheetTest() throws CsvValidationException, IOException
	{
		super();
		//urlconfig();
	}
	Loginrecon login;
	ViewnEditRulesheet viewrule;
	
	@BeforeMethod
	public void setup() throws InterruptedException, CsvValidationException, IOException
	{
		
		intialization();
		login=new Loginrecon();
		viewrule=new ViewnEditRulesheet();
		login.login();
	}
	
	@Test(priority=1)
	public void TC_38to47() throws InterruptedException, InvalidFormatException, IOException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		int num=viewrule.viewrulesheetclick();
		boolean flag=viewrule.TC_36(1,"Company Name",num);
		if(flag==false) System.out.println("TC_38 Company Name didn't match expected data");
		boolean flag1=viewrule.TC_36(2,"Main Product Name",num);
		if(flag1==false) System.out.println("TC_39 Main Product Name didn't match expected data");
		boolean flag3=viewrule.TC_36(4,"Sub-Product Name",num);
		if(flag3==false) System.out.println("TC_41 Sub-Product Name didn't match expected data");
		boolean flag4=viewrule.TC_36(5,"Discount (From)",num);
		if(flag4==false) System.out.println("TC_42 Discount (From) didn't match expected data");
		boolean flag5=viewrule.TC_36(6,"Discount (To)",num);
		if(flag5==false) System.out.println("TC_43 Discount (To) didn't match expected data");
		boolean flag6=viewrule.TC_36(7,"Policy Type",num);
		if(flag6==false) System.out.println("TC_44 Policy Type didn't match expected data");
		boolean flag7=viewrule.TC_36(8,"Product Type",num);
		if(flag7==false) System.out.println("TC_45 Product Type didn't match expected data");
		boolean flag8=viewrule.TC_36(9,"Total IDV (From)",num);
		if(flag8==false) System.out.println("TC_46 Total IDV (From) didn't match expected data");
		boolean flag9=viewrule.TC_36(10,"Total IDV (To)",num);
		if(flag9==false) System.out.println("TC_47 Total IDV (To) didn't match expected data");
		
	}
	@Test(priority=2)
	public void TC_48to57() throws InterruptedException, InvalidFormatException, IOException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		int num=viewrule.viewrulesheetclick();
		boolean flag1=viewrule.TC_36(11,"Fuel Type",num);
		if(flag1==false) System.out.println("TC_48 Fuel Type didn't match expected data");
		boolean flag2=viewrule.TC_36(12,"Manufacturing Year (From)",num);
		if(flag2==false) System.out.println("TC_49 Manufacturing Year (From) didn't match expected data");
		boolean flag3=viewrule.TC_36(13,"Manufacturing Year (To)",num);
		if(flag3==false) System.out.println("TC_50 Manufacturing Year (To) didn't match expected data");
		boolean flag4=viewrule.TC_36(14,"Insurance Company Branch Short Name",num);
		if(flag4==false) System.out.println("TC_51 Insurance Company Branch Short Name didn't match expected data");
		boolean flag5=viewrule.TC_36(15,"Insurance Company City Name",num);
		if(flag5==false) System.out.println("TC_52 Insurance Company City Name didn't match expected data");
		boolean flag6=viewrule.TC_36(16,"Insurance Company State Name",num);
		if(flag6==false) System.out.println("TC_53 Insurance Company State Name didn't match expected data");
		boolean flag7=viewrule.TC_36(17,"Branch",num);
		if(flag7==false) System.out.println("TC_54 Branch didn't match expected data");
		boolean flag8=viewrule.TC_36(18,"Make",num);
		if(flag8==false) System.out.println("TC_55 Make didn't match expected data");
		boolean flag9=viewrule.TC_36(19,"Model",num);
		if(flag9==false) System.out.println("TC_56 Model didn't match expected data");
		boolean flag10=viewrule.TC_36(20,"Variant Name",num);
		if(flag10==false) System.out.println("TC_57 Variant Name didn't match expected data");
	}
	@Test
	public void TC_58to67() throws InterruptedException, InvalidFormatException, IOException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		int num=viewrule.viewrulesheetclick();
		boolean flag1=viewrule.TC_36(21,"Seating Capacity (From)",num);
		if(flag1==false) System.out.println("TC_58 Seating Capacity (From) didn't match expected data");
		boolean flag2=viewrule.TC_36(22,"Seating Capacity (To)",num);
		if(flag2==false) System.out.println("TC_59 Seating Capacity (To) didn't match expected data");
		boolean flag3=viewrule.TC_36(23,"CC (From)",num);
		if(flag3==false) System.out.println("TC_60 CC (From) didn't match expected data");
		boolean flag4=viewrule.TC_36(24,"CC (To)",num);
		if(flag4==false) System.out.println("TC_61 CC (To) didn't match expected data");
		boolean flag5=viewrule.TC_36(25,"NCB (From)",num);
		if(flag5==false) System.out.println("TC_62 NCB (From) didn't match expected data");
		boolean flag6=viewrule.TC_36(26,"NCB (To)",num);
		if(flag6==false) System.out.println("TC_63 NCB (To) didn't match expected data");
		boolean flag7=viewrule.TC_36(27,"Agent Code",num);
		if(flag7==false) System.out.println("TC_64 Agent Code didn't match expected data");
		boolean flag8=viewrule.TC_36(28,"Inward No",num);
		if(flag8==false) System.out.println("TC_65 Inward No didn't match expected data");
		boolean flag9=viewrule.TC_36(30,"RTO",num);
		if(flag9==false) System.out.println("TC_66 RTO didn't match expected data");
		boolean flag9_1=viewrule.TC_36(29,"RTO State Code",num);
		if(flag9_1==false) System.out.println("TC_66.1 RTO State code didn't match expected data");
		boolean flag10=viewrule.TC_36(31,"Proposal Age (From)",num);
		if(flag10==false) System.out.println("TC_67 Proposal Age (From) didn't match expected data");
	}
	@Test(priority=3)
	public void TC_68_to_78() throws InterruptedException, InvalidFormatException, IOException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		int num=viewrule.viewrulesheetclick();
		boolean flag1=viewrule.TC_36(32,"Proposal Age (To)",num);
		if(flag1==false) System.out.println("TC_68 Proposal Age (To)didn't match expected data");
		boolean flag2=viewrule.TC_36(33,"PPT (From)",num);
		if(flag2==false) System.out.println("TC_69 PPT (From) didn't match expected data");
		boolean flag3=viewrule.TC_36(34,"PPT (To)",num);
		if(flag3==false) System.out.println("TC_70 PPT (To) didn't match expected data");
		boolean flag3_1=viewrule.TC_36(35,"Issue Month",num);
		if(flag3_1==false) System.out.println("TC_71 Issue Month didn't match expected data");
		boolean flag4=viewrule.TC_36(36,"Reference Issue Date (From)",num);
		if(flag4==false) System.out.println("TC_72 Reference Issue Date (From) didn't match expected data");
		boolean flag5=viewrule.TC_36(37,"Reference Issue Date (To)",num);
		if(flag5==false) System.out.println("TC_73 Reference Issue Date (To) didn't match expected data");
		boolean flag6=viewrule.TC_36(38,"OD Start Date (From)",num);
		if(flag6==false) System.out.println("TC_74 OD Start Date (From) didn't match expected data");
		boolean flag7=viewrule.TC_36(39,"OD Start Date (To)",num);
		if(flag7==false) System.out.println("TC_75 OD Start Date (To) didn't match expected data");
		boolean flag8=viewrule.TC_36(40,"TP Start Date (From)",num);
		if(flag8==false) System.out.println("TC_76 TP Start Date (From)didn't match expected data");
		boolean flag9=viewrule.TC_36(41,"TP Start Date (To)",num);
		if(flag9==false) System.out.println("TC_77 TP Start Date (To) didn't match expected data");
		boolean flag10=viewrule.TC_36(42,"Policy Tenure",num);	
		if(flag10==false) System.out.println("TC_78 Policy Tenture didn't match expected data");
	}
	@Test(priority=4)
	public void TC_79_to_87() throws InterruptedException, InvalidFormatException, IOException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		int num=viewrule.viewrulesheetclick();
		boolean flag1=viewrule.TC_36(43,"Product Category",num);
		if(flag1==false) System.out.println("TC_79 Product Category didn't match expected data");
		boolean flag3=viewrule.TC_36(44,"GVW (From)",num);
		if(flag3==false) System.out.println("TC_80 GVW (From) didn't match expected data");
		boolean flag4=viewrule.TC_36(45,"GVW (To)",num);
		if(flag4==false) System.out.println("TC_81 GVW (To) didn't match expected data");
		boolean flag5=viewrule.TC_36(46,"OD (From)",num);
		if(flag5==false) System.out.println("TC_82 OD (From) didn't match expected data");
		boolean flag6=viewrule.TC_36(47,"OD (To)",num);
		if(flag6==false) System.out.println("TC_83 OD (To) didn't match expected data");
		boolean flag7=viewrule.TC_36(48,"TP (From)",num);
		if(flag7==false) System.out.println("TC_84 TP (From) didn't match expected data");
		boolean flag8=viewrule.TC_36(49,"TP (To)",num);
		if(flag8==false) System.out.println("TC_85 TP (From) didn't match expected data");
		boolean flag9=viewrule.TC_36(50,"Net (From)",num);
		if(flag9==false) System.out.println("TC_86 Net (From) didn't match expected data");
		boolean flag10=viewrule.TC_36(51,"Net (To)",num);
		if(flag10==false) System.out.println("TC_87 Net (To) didn't match expected data");
	}
	@Test(priority=5)
	public void TC_88_to_97() throws InterruptedException, InvalidFormatException, IOException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		int num=viewrule.viewrulesheetclick();
		boolean flag1=viewrule.TC_36(52,"IMD Code",num);
		if(flag1==false) System.out.println("TC_88 IMD Code didn't match expected data");
		boolean flag2=viewrule.TC_36(53,"Basis",num);
		if(flag2==false) System.out.println("TC_89 Basis didn't match expected data");
		boolean flag3=viewrule.TC_36(54,"OD Total",num);
		if(flag3==false) System.out.println("TC_90 OD Total didn't match expected data");
		boolean flag4=viewrule.TC_36(55,"TP Total",num);
		if(flag4==false) System.out.println("TC_91 TP Total didn't match expected data");
		boolean flag5=viewrule.TC_36(56,"Fixed Value Total",num);
		if(flag5==false) System.out.println("TC_92 Fixed Value Total didn't match expected data");
		boolean flag6=viewrule.TC_36(57,"Basis 1",num);
		if(flag6==false) System.out.println("TC_93 Basis 1 didn't match expected data");
		boolean flag7=viewrule.TC_36(58,"OD Rate 1 (Commission %)",num);
		if(flag7==false) System.out.println("TC_94 OD Rate 1 (Commission %) didn't match expected data");
		boolean flag8=viewrule.TC_36(59,"TP Rate 1 (Commission %)",num);
		if(flag8==false) System.out.println("TC_95 TP Rate 1 (Commission %) didn't match expected data");
		boolean flag9=viewrule.TC_36(60,"Fixed Value 1",num);
		if(flag9==false) System.out.println("TC_96 Fixed Value 1 didn't match expected data");
		boolean flag10=viewrule.TC_36(61,"Basis 2",num);
		if(flag10==false) System.out.println("TC_97 Basis 2  didn't match expected data");
	}
	@Test(priority=6)
	public void TC_98_to_101() throws InterruptedException, InvalidFormatException, IOException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		int num=viewrule.viewrulesheetclick();
		boolean flag1=viewrule.TC_36(62,"OD Rate 2 (Commission %)",num);
		if(flag1==false) System.out.println("TC_98 OD Rate 2 (Commission %) didn't match expected data");
		boolean flag2=viewrule.TC_36(63,"TP Rate 2 (Commission %)",num);
		if(flag2==false) System.out.println("TC_99 TP Rate 2 (Commission %)  didn't match expected data");
		boolean flag3=viewrule.TC_36(64,"Fixed Value 2",num);
		if(flag3==false) System.out.println("TC_100 Fixed Value 2  didn't match expected data");
		boolean flag4=viewrule.TC_36(65,"Comment",num);
		if(flag4==false) System.out.println("TC_101 Comment didn't match expected data");
	}
	@Test(priority=7)
	public void TC_40() throws InterruptedException, InvalidFormatException, IOException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		int num=viewrule.viewrulesheetclick();
		boolean flag=viewrule.TC_39(3,num);
		if(flag==false) System.out.println("TC_39 Product Name didn't match expected data");
	}
	
	@Test(priority=8)
	public void Tc_113_to_130res1_2_4() throws ClassNotFoundException, InterruptedException, SQLException, IOException, InvalidFormatException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		Assert.assertTrue(viewrule.Tc_113(),"Edit rule screen is not visible.");
		boolean flag=viewrule.Editscreendatamatch(1, "companyname");
		boolean flag_=viewrule.emptyfield("companyname");
		if(flag==false && flag_==false) System.out.println("TC_113 companyname didn't match expected data and error message is not displayed for empty value");
		
		boolean flag1=viewrule.Editscreendatamatch(2, "mainproductname");
		boolean flag_1=viewrule.emptyfield("mainproductname");
		if(flag1==false && flag_1==false) System.out.println("TC_114 mainproductname didn't match expected data and error message is not displayed for empty value");
		
		boolean flag2=viewrule.Editscreendatamatch(3, "productname");
		boolean flag_2=viewrule.emptyfield("productname");
		if(flag2==false && flag_2==false) System.out.println("TC_115 productname didn't match expected data and error message is not displayed for empty value");
		
		boolean flag3=viewrule.Editscreendatamatch(4, "subproductname");
		boolean flag_3=viewrule.emptyfield("subproductname");
		if(flag3==false && flag_3==false) System.out.println("TC_116 subproductname didn't match expected data and error message is not displayed for empty value");
		
		boolean flag4=viewrule.Editscreendatamatch(5, "discountfrom");
		boolean flag_4=viewrule.emptyfield("discountfrom");
		if(flag4==false && flag_4==false) System.out.println("TC_117 discountfrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag5=viewrule.Editscreendatamatch(6, "discountto");
		boolean flag_5=viewrule.emptyfield("discountto");
		if(flag5==false && flag_5==false) System.out.println("TC_118 discountto didn't match expected data and error message is not displayed for empty value");
		
		boolean flag6=viewrule.Editscreendatamatch(7, "policytype");
		boolean flag_6=viewrule.emptyfield("policytype");
		if(flag6==false && flag_6==false) System.out.println("TC_119 policytype didn't match expected data and error message is not displayed for empty value");
		
		boolean flag7=viewrule.Editscreendatamatch(8, "producttype");
		boolean flag_7=viewrule.emptyfield("producttype");
		if(flag7==false && flag_7==false) System.out.println("TC_120 producttype didn't match expected data and error message is not displayed for empty value");
	
		boolean flag8=viewrule.Editscreendatamatch(9, "totalidvfrom");
		boolean flag_8=viewrule.emptyfield("totalidvfrom");
		if(flag8==false && flag_8==false) System.out.println("TC_121 totalidvfrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag9=viewrule.Editscreendatamatch(10, "totalidvto");
		boolean flag_9=viewrule.emptyfield("totalidvto");
		if(flag9==false && flag_9==false) System.out.println("TC_122 totalidvto didn't match expected data and error message is not displayed for empty value");
	
		boolean flag10=viewrule.Editscreendatamatch(11, "fualtype");
		boolean flag_10=viewrule.emptyfield("fualtype");
		if(flag10==false && flag_10==false) System.out.println("TC_123 fueltype didn't match expected data and error message is not displayed for empty value");
	
		boolean flag11=viewrule.Editscreendatamatch(12, "manufacturingyearfrom");
		boolean flag_11=viewrule.emptyfield("manufacturingyearfrom");
		if(flag11==false && flag_11==false) System.out.println("TC_124 manufacturingyearfrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag12=viewrule.Editscreendatamatch(13, "manufacturingyearto");
		boolean flag_12=viewrule.emptyfield("manufacturingyearto");
		if(flag12==false && flag_12==false) System.out.println("TC_125 manufacturingyearto didn't match expected data and error message is not displayed for empty value");
	
		boolean flag13=viewrule.Editscreendatamatch(14, "insurancecompanybranchname");
		boolean flag_13=viewrule.emptyfield("insurancecompanybranchname");
		if(flag13==false && flag_13==false) System.out.println("TC_126 insurancecompanybranchname didn't match expected data and error message is not displayed for empty value");
	
		boolean flag14=viewrule.Editscreendatamatch(15, "insurancecompanycityname");
		boolean flag_14=viewrule.emptyfield("insurancecompanycityname");
		if(flag14==false && flag_14==false) System.out.println("TC_127 insurancecompanycityname didn't match expected data and error message is not displayed for empty value");
	
		boolean flag15=viewrule.Editscreendatamatch(16, "insurancecompanystatename");
		boolean flag_15=viewrule.emptyfield("insurancecompanystatename");
		if(flag15==false && flag_15==false) System.out.println("TC_128 insurancecompanystatename didn't match expected data and error message is not displayed for empty value");
		
		boolean flag16=viewrule.Editscreendatamatch(17, "branch");
		boolean flag_16=viewrule.emptyfield("branch");
		if(flag16==false && flag_16==false) System.out.println("TC_129 branch didn't match expected data and error message is not displayed for empty value");
		
		boolean flag17=viewrule.Editscreendatamatch(18, "make");
		boolean flag_17=viewrule.emptyfield("make");
		if(flag17==false && flag_17==false) System.out.println("TC_130 make didn't match expected data and error message is not displayed for empty value");
	
	}
	@Test(priority=9)
	public void Tc_130_to_148res1_2_4() throws ClassNotFoundException, InterruptedException, SQLException, IOException, InvalidFormatException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		Assert.assertTrue(viewrule.Tc_113(),"Edit rule screen is not visible.");
		boolean flag=viewrule.Editscreendatamatch(19, "model");
		boolean flag_=viewrule.emptyfield("model");
		if(flag==false && flag_==false) System.out.println("TC_131 model didn't match expected data and error message is not displayed for empty value");
		
		boolean flag1=viewrule.Editscreendatamatch(20, "variantname");
		boolean flag_1=viewrule.emptyfield("variantname");
		if(flag1==false && flag_1==false) System.out.println("TC_132 variantname didn't match expected data and error message is not displayed for empty value");
		
		boolean flag2=viewrule.Editscreendatamatch(21, "seatingcapacityfrom");
		boolean flag_2=viewrule.emptyfield("seatingcapacityfrom");
		if(flag2==false && flag_2==false) System.out.println("TC_133 seatingcapacityfrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag3=viewrule.Editscreendatamatch(22, "Seating_capacity_to");
		boolean flag_3=viewrule.emptyfield("Seating_capacity_to");
		if(flag3==false && flag_3==false) System.out.println("TC_134 Seating_capacity_to didn't match expected data and error message is not displayed for empty value");
		
		boolean flag4=viewrule.Editscreendatamatch(23, "ccfrom");
		boolean flag_4=viewrule.emptyfield("ccfrom");
		if(flag4==false && flag_4==false) System.out.println("TC_135 ccfrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag5=viewrule.Editscreendatamatch(24, "ccto");
		boolean flag_5=viewrule.emptyfield("ccto");
		if(flag5==false && flag_5==false) System.out.println("TC_136 ccto didn't match expected data and error message is not displayed for empty value");
		
		boolean flag6=viewrule.Editscreendatamatch(25, "ncbfrom");
		boolean flag_6=viewrule.emptyfield("ncbfrom");
		if(flag6==false && flag_6==false) System.out.println("TC_137 ncbfrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag7=viewrule.Editscreendatamatch(26, "ncbto");
		boolean flag_7=viewrule.emptyfield("ncbto");
		if(flag7==false && flag_7==false) System.out.println("TC_138 ncbto didn't match expected data and error message is not displayed for empty value");
	
		boolean flag8=viewrule.Editscreendatamatch(27, "pospcode");
		boolean flag_8=viewrule.emptyfield("pospcode");
		if(flag8==false && flag_8==false) System.out.println("TC_139 AgentCode didn't match expected data and error message is not displayed for empty value");
		
		boolean flag9=viewrule.Editscreendatamatch(28, "inwardno");
		boolean flag_9=viewrule.emptyfield("inwardno");
		if(flag9==false && flag_9==false) System.out.println("TC_140 inwardno didn't match expected data and error message is not displayed for empty value");
	
		boolean flag10=viewrule.Editscreendatamatch(30, "rto");
		boolean flag_10=viewrule.emptyfield("rto");
		if(flag10==false && flag_10==false) System.out.println("TC_141 rto didn't match expected data and error message is not displayed for empty value");
	
		boolean flag11=viewrule.Editscreendatamatch(31, "proposalagefrom");
		boolean flag_11=viewrule.emptyfield("proposalagefrom");
		if(flag11==false && flag_11==false) System.out.println("TC_142 proposalagefrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag12=viewrule.Editscreendatamatch(32, "proposalageto");
		boolean flag_12=viewrule.emptyfield("proposalageto");
		if(flag12==false && flag_12==false) System.out.println("TC_143 proposalageto didn't match expected data and error message is not displayed for empty value");
	
		boolean flag13=viewrule.Editscreendatamatch(33, "pptfrom");
		boolean flag_13=viewrule.emptyfield("pptfrom");
		if(flag13==false && flag_13==false) System.out.println("TC_144 pptfrom didn't match expected data and error message is not displayed for empty value");
	
		boolean flag14=viewrule.Editscreendatamatch(34, "pptto");
		boolean flag_14=viewrule.emptyfield("pptto");
		if(flag14==false && flag_14==false) System.out.println("TC_145 pptto didn't match expected data and error message is not displayed for empty value");
	
		boolean flag15=viewrule.Editscreendatamatch(35, "issuemonth");
		boolean flag_15=viewrule.emptyfield("issuemonth");
		if(flag15==false && flag_15==false) System.out.println("TC_146 issuemonth didn't match expected data and error message is not displayed for empty value");
		
		boolean flag16=viewrule.Editscreendatamatch(36, "issuedatefrom");
		boolean flag_16=viewrule.emptyfield("issuedatefrom");
		if(flag16==false && flag_16==false) System.out.println("TC_147 refrence_issuedatefrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag17=viewrule.Editscreendatamatch(37, "isssuedateto");
		boolean flag_17=viewrule.emptyfield("isssuedateto");
		if(flag17==false && flag_17==false) System.out.println("TC_148 refrence_isssuedateto didn't match expected data and error message is not displayed for empty value");
	
	}
	@Test(priority=10)
	public void TC149_to153res1_2_4() throws ClassNotFoundException, InterruptedException, SQLException, IOException, InvalidFormatException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		Assert.assertTrue(viewrule.Tc_113(),"Edit rule screen is not visible.");
		boolean flag=viewrule.Editscreendatamatchname(38, "OD_Start_Date_From");
		boolean flag_=viewrule.emptyfieldname("OD_Start_Date_From");
		if(flag==false && flag_==false) System.out.println("TC_131 model didn't match expected data and error message is not displayed for empty value");
		
		boolean flag1=viewrule.Editscreendatamatchname(39, "OD_Start_Date_To");
		boolean flag_1=viewrule.emptyfieldname("OD_Start_Date_To");
		if(flag1==false && flag_1==false) System.out.println("TC_132 variantname didn't match expected data and error message is not displayed for empty value");
		
		boolean flag2=viewrule.Editscreendatamatchname(40, "TP_Start_date_from");
		boolean flag_2=viewrule.emptyfieldname("TP_Start_date_from");
		if(flag2==false && flag_2==false) System.out.println("TC_133 seatingcapacityfrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag3=viewrule.Editscreendatamatchname(41, "TP_Start_date_to");
		boolean flag_3=viewrule.emptyfieldname("TP_Start_date_to");
		if(flag3==false && flag_3==false) System.out.println("TC_134 Seating_capacity_to didn't match expected data and error message is not displayed for empty value");
		
		boolean flag4=viewrule.Editscreendatamatchname(42, "Policy_tenure");
		boolean flag_4=viewrule.emptyfieldname("Policy_tenure");
		if(flag4==false && flag_4==false) System.out.println("TC_135 ccfrom didn't match expected data and error message is not displayed for empty value");
		
	}
	
	
	
	
	@Test(priority=11)
	public void Tc_154_to_171res1_2_4() throws ClassNotFoundException, InterruptedException, SQLException, IOException, InvalidFormatException
	{
		Assert.assertTrue(viewrule.Tc_113(),"Edit rule screen is not visible.");
		boolean flag=viewrule.Editscreendatamatch(43, "productcategory");
		boolean flag_=viewrule.emptyfield("productcategory");
		if(flag==false && flag_==false) System.out.println("TC_154 productcategory didn't match expected data and error message is not displayed for empty value");
		
		boolean flag1=viewrule.Editscreendatamatch(44, "gvwfrom");
		boolean flag_1=viewrule.emptyfield("gvwfrom");
		if(flag1==false && flag_1==false) System.out.println("TC_155 gvwfrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag2=viewrule.Editscreendatamatch(45, "GVW_to");
		boolean flag_2=viewrule.emptyfield("GVW_to");
		if(flag2==false && flag_2==false) System.out.println("TC_156 GVW_to didn't match expected data and error message is not displayed for empty value");
		
		boolean flag3=viewrule.Editscreendatamatch(46, "odfrom");
		boolean flag_3=viewrule.emptyfield("odfrom");
		if(flag3==false && flag_3==false) System.out.println("TC_157 odfrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag4=viewrule.Editscreendatamatch(47, "odto");
		boolean flag_4=viewrule.emptyfield("odto");
		if(flag4==false && flag_4==false) System.out.println("TC_158 odto didn't match expected data and error message is not displayed for empty value");
		
		boolean flag5=viewrule.Editscreendatamatch(48, "tpfrom");
		boolean flag_5=viewrule.emptyfield("tpfrom");
		if(flag5==false && flag_5==false) System.out.println("TC_159 tpfrom didn't match expected data and error message is not displayed for empty value");
		
		boolean flag6=viewrule.Editscreendatamatch(49, "tpto");
		boolean flag_6=viewrule.emptyfield("tpto");
		if(flag6==false && flag_6==false) System.out.println("TC_160 tpto didn't match expected data and error message is not displayed for empty value");
		
		boolean flag7=viewrule.Editscreendatamatch(50, "netfrom");
		boolean flag_7=viewrule.emptyfield("netfrom");
		if(flag7==false && flag_7==false) System.out.println("TC_161 netfrom didn't match expected data and error message is not displayed for empty value");
	
		boolean flag8=viewrule.Editscreendatamatch(51, "netto");
		boolean flag_8=viewrule.emptyfield("netto");
		if(flag8==false && flag_8==false) System.out.println("TC_162 netto didn't match expected data and error message is not displayed for empty value");
		
		boolean flag9=viewrule.Editscreendatamatch(52, "imdcode");
		boolean flag_9=viewrule.emptyfield("imdcode");
		if(flag9==false && flag_9==false) System.out.println("TC_163 imdcode didn't match expected data and error message is not displayed for empty value");
	
		boolean flag10=viewrule.Editscreendatamatch(53, "basis");
		boolean flag_10=viewrule.emptyfield("basis");
		if(flag10==false && flag_10==false) System.out.println("TC_164 basis didn't match expected data and error message is not displayed for empty value");
	
		boolean flag11=viewrule.Editscreendatamatch(54, "odtotal");
		boolean flag_11=viewrule.emptyfield("odtotal");
		if(flag11==false && flag_11==false) System.out.println("TC_165 odtotal didn't match expected data and error message is not displayed for empty value");
		
		boolean flag12=viewrule.Editscreendatamatch(55, "tptotal");
		boolean flag_12=viewrule.emptyfield("tptotal");
		if(flag12==false && flag_12==false) System.out.println("TC_166 tptotal didn't match expected data and error message is not displayed for empty value");
	
		boolean flag13=viewrule.Editscreendatamatch(56, "fixedvaluetotal");
		boolean flag_13=viewrule.emptyfield("fixedvaluetotal");
		if(flag13==false && flag_13==false) System.out.println("TC_167 fixedvaluetotal didn't match expected data and error message is not displayed for empty value");
	
		boolean flag14=viewrule.Editscreendatamatch(57, "basisw");
		boolean flag_14=viewrule.emptyfield("basisw");
		if(flag14==false && flag_14==false) System.out.println("TC_168 basisw didn't match expected data and error message is not displayed for empty value");
	
		boolean flag15=viewrule.Editscreendatamatch(58, "odrate1commissionper");
		boolean flag_15=viewrule.emptyfield("odrate1commissionper");
		if(flag15==false && flag_15==false) System.out.println("TC_169 odrate1commissionper didn't match expected data and error message is not displayed for empty value");
		
		boolean flag16=viewrule.Editscreendatamatch(59, "tprate1commissionper");
		boolean flag_16=viewrule.emptyfield("tprate1commissionper");
		if(flag16==false && flag_16==false) System.out.println("TC_170 tprate1commissionper didn't match expected data and error message is not displayed for empty value");
		
		boolean flag17=viewrule.Editscreendatamatch(60, "fixedvaluebasis1");
		boolean flag_17=viewrule.emptyfield("fixedvaluebasis1");
		if(flag17==false && flag_17==false) System.out.println("TC_171 fixedvaluebasis1 didn't match expected data and error message is not displayed for empty value");
	
	}
	@Test(priority=12)
	public void Tc_172_to_176res1_2_4() throws ClassNotFoundException, InterruptedException, SQLException, IOException, InvalidFormatException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		Assert.assertTrue(viewrule.Tc_113(),"Edit rule screen is not visible.");
		boolean flag=viewrule.Editscreendatamatch(61, "basis2");
		boolean flag_=viewrule.emptyfield("basis2");
		if(flag==false && flag_==false) System.out.println("TC_172 basis2 didn't match expected data and error message is not displayed for empty value");
		
		boolean flag1=viewrule.Editscreendatamatch(62, "odrate2commissionper");
		boolean flag_1=viewrule.emptyfield("odrate2commissionper");
		if(flag1==false && flag_1==false) System.out.println("TC_173 odrate2commissionper didn't match expected data and error message is not displayed for empty value");
		
		boolean flag2=viewrule.Editscreendatamatch(63, "tprate2commissionper");
		boolean flag_2=viewrule.emptyfield("tprate2commissionper");
		if(flag2==false && flag_2==false) System.out.println("TC_174 tprate2commissionper didn't match expected data and error message is not displayed for empty value");
		
		boolean flag3=viewrule.Editscreendatamatch(64, "fixedvaluebasis2");
		boolean flag_3=viewrule.emptyfield("fixedvaluebasis2");
		if(flag3==false && flag_3==false) System.out.println("TC_175 fixedvaluebasis2 didn't match expected data and error message is not displayed for empty value");
		
		boolean flag4=viewrule.Editscreendatamatch(65, "Comment");
		boolean flag_4=viewrule.emptyfield("Comment");
		if(flag4==false && flag_4==false) System.out.println("TC_176 Comment didn't match expected data and error message is not displayed for empty value");
		
		boolean flag5=viewrule.Editscreendatamatch(29, "RTO_State_Code");
		boolean flag_5=viewrule.emptyfield("RTO_State_Code");
		if(flag5==false && flag_5==false) System.out.println("TC_141.1 RTO_State_Code didn't match expected data and error message is not displayed for empty value");
	}
	
	
	@Test(priority=13)
	public void tc_113res3() throws InvalidFormatException, ClassNotFoundException, InterruptedException, SQLException, IOException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		viewrule.Mastermismatcherror();	
	}
	@Test(priority=14)
	public void tc_23() throws InterruptedException
	{
		Assert.assertTrue(viewrule.TC_23(),"Tc_23 res1 got failed cursor value is not as expected");
	}
	@Test(priority=15)
	public void Tc_241res1_2() throws InterruptedException, InvalidFormatException
	{
		Assert.assertTrue(viewrule.TC_24(),"Tc_24 got failed options didn't match");
	}
	
	@Test(priority=16)
	public void Tc_24res3() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException, IOException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
	   Assert.assertTrue(viewrule.TC24res3(),"Tc_24 4 got failed options didn't match");
	}
	@Test(priority=17)
	public void TC25res1_3_4() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException, IOException
	{
		 Assert.assertTrue(viewrule.TC_25res1n3(),"Tc_25res1n3 got failed it doesn't allow to select date");
		 Assert.assertTrue(viewrule.TC_25res4(),"Tc_25res 4 got failed it allows to type a date.");
	}
	@Test(priority=18)
	public void TC26res1_3_4() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException, IOException
	{
		 Assert.assertTrue(viewrule.TC_26res1n3(),"Tc_26res1n3 got failed it doesn't allow to select date");
		 Assert.assertTrue(viewrule.TC_26res4(),"Tc_26res4 got failed it allows to type a date.");
	}
	@Test(priority=19)
	public void Tc_28_29_30res1() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(viewrule.TC_28res1(),"Tc got failed as dropdown selection is not working");
	}
	@Test(priority=20)
	public void Tc_28_29_30res2n4() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(viewrule.TC_28res2(),"Tc got failed as dropdown selection is not showing expected result");
	}
	
	@Test(priority=21)
	public void Tc_28_29_30res5() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(viewrule.TC_28res5(),"Tc got failed as dropdown selection is not showing expected result");
		
	}
	@Test(priority=22)
	public void Tc_28_29_30res3() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(viewrule.TC_28res3(),"Tc got failed as dropdown selection is not showing expected result");
	}
	@Test(priority=23)
	public void TC_31res1() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(viewrule.TC_31res1(),"Tc got failed as RTO dropdown is unable to select value");
		
	}
	@Test(priority=24)
	public void Tc_31res2n4() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(viewrule.TC_31res2(),"Tc got failed as dropdown selection is not showing expected result");
	}
	@Test(priority=25)
	public void TC_31res6() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(viewrule.TC_31res6(),"Tc got failed as dropdown selection is not showing expected result");
	}
	
	@Test(priority=26)
	public void TC_33res1n2() throws InterruptedException, IOException, InvalidFormatException
	{
		Assert.assertTrue(viewrule.TC_33res1n2(),"Tc got failed as clear button doesn't work as expected");
	}
	
	@Test(priority=27)
	public void Tc_34() throws InterruptedException, IOException, InvalidFormatException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		Assert.assertTrue(viewrule.Tc_34(),"Tc got failed as search txtbox is not trimming entered text");
	}
	@Test(priority=28)
	public void TC_27res1_2_3() throws InterruptedException, InvalidFormatException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		Assert.assertTrue(viewrule.TC_27res4(),"TC_27res4 got failed as it's not showing expected data.");
	}
	@Test(priority=29)
	public void TC_37() throws ClassNotFoundException, SQLException, InterruptedException, IOException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		int num=viewrule.viewrulesheetclick();
		boolean flag=viewrule.TC_37(num);
		if(flag==false) System.out.println("TC_37 Rule refrence didn't match expected data");
	}
	@Test(priority=30)
	public void TC_36() throws ClassNotFoundException, SQLException, InterruptedException, IOException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		int num=viewrule.viewrulesheetclick();
		boolean flag=viewrule.Tc_36actual(num);
		if(flag==false) System.out.println("TC_36 rule_condition didn't match expected data");
	}
	
	@Test(priority=31)
	public void TC_32() throws IOException, InterruptedException
	{
		int num=viewrule.viewrulesheetclick();
		viewrule.Tc_32();
	}
	@Test(priority=16)
	public void Tc_107() throws InterruptedException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rule_sheet3.csv");
		viewrule.viewrulesheetclick();
		Assert.assertTrue(viewrule.TC_336(),"Tc got failed as changing on page number does not change data.");
	}
	@Test(priority=17)
	public void Tc_108() throws InterruptedException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rule_sheet3.csv");
		viewrule.viewrulesheetclick();
		Assert.assertTrue(viewrule.TC_337(),"Tc got failed as changing on page number does not change data.");
	}
	@Test(priority=18)
	public void Tc_109() throws InterruptedException, ClassNotFoundException, SQLException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rule_sheet3.csv");
		viewrule.viewrulesheetclick();
		Assert.assertTrue(viewrule.TC_338(),"Tc got failed as changing on page number does not change data.");
	}
	@Test
	public void TC_delete() throws ClassNotFoundException, InterruptedException, SQLException, IOException, InvalidFormatException
	{
		TestUtil.Truncatetabledynamic(prop.getProperty("truncatequery"),prop.getProperty("selectquery"));
		viewrule.rulesheetingestion("rules_sheet2.csv");
		viewrule.deleteclick();
	}
	
	
	@AfterMethod
	public void tearup()
	{
		driver.quit();
	}

	
}
