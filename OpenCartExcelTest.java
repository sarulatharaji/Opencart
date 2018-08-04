/**
 * 
 */
package BugSquashersPLP;


import org.junit.FixMethodOrder;

import java.io.File;
import java.io.FileInputStream;

import junit.framework.Assert;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.runners.MethodSorters;
/**
 * @author sarulr
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OpenCartExcelTest {
	static WebDriver driver;
	static FileInputStream fis;
	static XSSFWorkbook wb;
	static XSSFSheet sheet;
	static OpenCartValidation opencartvalidation=new OpenCartValidation();
	/**
	 * @throws java.lang.Exception
	 */
	
//	Loading the excel sheet, driver and website
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		File src=new File("D:\\PLP\\BugSquashers\\InputTestData.xlsx");
		
		fis=new FileInputStream(src); 

		wb=new XSSFWorkbook(fis);
		
		sheet=wb.getSheetAt(0);
		
		System.setProperty("webdriver.chrome.driver", "D:/PLP/chromedriver.exe");
		driver=new ChromeDriver();
		
		//Loading the opencart website
		driver.get("https://demo.opencart.com/");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		boolean continued=driver.findElement(By.xpath("//*[@id='content']/div/div/a")).isDisplayed();
				    if(continued==true)
				    {
				    	System.out.println("Continuing to logout");
				    }
				   else
					   System.out.println("Continue is not displayed");
		driver.close();
		System.out.println("Logged out");
	}
	
	@Test
	public void testMyAccount()
	{
		WebElement myAcc=driver.findElement(By.linkText("My Account"));
		myAcc.click();
		driver.findElement(By.partialLinkText("Log")).click();
	}
	
//	Testing user login
	@Test
	public void testLogin()
	{
		
		String email=sheet.getRow(1).getCell(1).getStringCellValue();
		driver.findElement(By.name("email")).sendKeys(email);
		
//		Entering password to login
		String password=sheet.getRow(2).getCell(1).getStringCellValue();
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys(password);
		
		driver.findElement(By.cssSelector("#content > div > div:nth-child(2) > div > form > input")).click();
		System.out.println("Logged in successfully");
	}
	
//	Testing change of password
	@Test
	public void testPassword()
	{
		 driver.findElement(By.xpath("//*[@id='content']/ul[1]/li[2]/a")).click();
		    String passwrd =sheet.getRow(9).getCell(1).getStringCellValue();
		    driver.findElement(By.xpath(".//*[@id='input-password']")).sendKeys(passwrd);
		
//			Re-entering password
	        String confirmPasswrd =sheet.getRow(10).getCell(1).getStringCellValue();
	        driver.findElement(By.xpath(".//*[@id='input-confirm']")).sendKeys(confirmPasswrd);
	        
//	        Verifying password
	        Assert.assertEquals(passwrd, confirmPasswrd);
	        driver.findElement(By.xpath(".//*[@id='content']/form/div/div[2]/input")).click();
	        System.out.println("Password has been updated successfully");

	}
	
//	Testing logout
	@Test
	public void testContinue() {
		driver.findElement(By.partialLinkText("Logo")).click();
		System.out.println("logging out");
	    
	}
	
}
