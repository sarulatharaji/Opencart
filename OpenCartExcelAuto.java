package BugSquashersPLP;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
public class OpenCartExcelAuto{

		public static void main(String[] args) throws InterruptedException, IOException {
//			 TODO Auto-generated method stub
//			Connecting with file
			File src=new File("D:\\PLP\\BugSquashers\\Testdata.xlsx");
		
			FileInputStream fis =new FileInputStream(src); 

			XSSFWorkbook wb=new XSSFWorkbook(fis);
			
			XSSFSheet sheet=wb.getSheetAt(0);
			
			System.setProperty("webdriver.chrome.driver", "D:/PLP/chromedriver.exe");
			int n=sheet.getLastRowNum();
			
	    	for(int i=1;i<=n;i++)
	  		{
			WebDriver driver=new ChromeDriver();
			
			//Loading the opencart website
			driver.get("https://demo.opencart.com/");
//			Clicking My account page
			WebElement myAcc=driver.findElement(By.linkText("My Account"));
			myAcc.click();
			
//			Selecting register from My account
			driver.findElement(By.partialLinkText("Log")).click();
			
//			Getting page title and verifying
			OpenCartValidation opencartvalidation=new OpenCartValidation();
			
			String logintitle=driver.getTitle();
			String actualTitle="Account Login";
			
//			Verifying page title
			if(opencartvalidation.validateTitle(logintitle,actualTitle))
					System.out.println("Title "+logintitle+" matches the actual one");
			else
					System.out.println("Title is not verified");
			
			//Logging details to page
			WebElement mailID=driver.findElement(By.name("email"));
			mailID.clear();
			String email=sheet.getRow(i).getCell(0).getStringCellValue();
			mailID.sendKeys(email); 
			
//			Entering password to login
			WebElement passw=driver.findElement(By.xpath("//*[@id='input-password']"));
			passw.clear();
			String password=sheet.getRow(i).getCell(1).getStringCellValue();
			passw.sendKeys(password); 
			
			driver.findElement(By.cssSelector("#content > div > div:nth-child(2) > div > form > input")).click();
			
//			Logged into My account page
//			Getting page title and verifying
			
			String myAccountTitle=driver.getTitle();
			String expectedTitle="My Account";
			
//			Verifying title
			if(opencartvalidation.validateTitle(myAccountTitle,expectedTitle))
					System.out.println("Title "+myAccountTitle+" matches the expected one");
			else
					System.out.println("Title is not verified");
			
//			Clicking edit your contents
			driver.findElement(By.linkText("Edit your account information")).click();

//		    Retrieving first name
		    WebElement fname=driver.findElement(By.xpath("//*[@id='input-firstname']"));
		    fname.clear();
		    String firstName=sheet.getRow(i).getCell(2).getStringCellValue();
		    fname.sendKeys(firstName);
		    
//		    Retrieving last name
		    WebElement lname=driver.findElement(By.cssSelector("#input-lastname"));
		    lname.clear();
		    String lastName=sheet.getRow(i).getCell(3).getStringCellValue();
		    lname.sendKeys(lastName);  

//		    Retrieving email
		    driver.findElement(By.id("input-email")).clear();
		    String mail=sheet.getRow(i).getCell(0).getStringCellValue();
		    driver.findElement(By.id("input-email")).sendKeys(mail);
		    
//		    Retrieving mobile number
		    WebElement mobNo=driver.findElement(By.name("telephone"));
		    mobNo.clear();
		    String mobileNum=sheet.getRow(i).getCell(4).getStringCellValue();
		    mobNo.sendKeys(mobileNum);

//		    Validating mobile number
		    if(OpenCartValidation.validateNumber(mobileNum))
		    {
		    	System.out.println("Valid mobile number");	    
		    }
		    else
		    {
		    	System.out.println("Enter 10 numbers and valid code");
		    }
		    
		    driver.findElement(By.xpath("//*[@id='content']/form/div/div[2]/input")).click();

//	    	Changing Password
		    driver.findElement(By.xpath("//*[@id='content']/ul[1]/li[2]/a")).click();
		    String passwrd =sheet.getRow(i).getCell(1).getStringCellValue();
		    driver.findElement(By.xpath(".//*[@id='input-password']")).sendKeys(passwrd);
		
//			Re-entering password
	        String confirmPasswrd =sheet.getRow(i).getCell(1).getStringCellValue();
	        driver.findElement(By.xpath(".//*[@id='input-confirm']")).sendKeys(confirmPasswrd);
	        
//	        Verifying password
	        if(opencartvalidation.validatePassword(passwrd))
	        {
	        	System.out.println("Password matches given length");
	        }
	        else
	        {
	        	System.out.println("Password should be of 4 to 20 characters");
	        }
	        
	        driver.findElement(By.xpath(".//*[@id='content']/form/div/div[2]/input")).click();
	        
//	        Verifying page content
	        if(driver.getPageSource().contains("Your password has been successfully updated."))
	        {
	     	   System.out.println("The text 'Your password has been successfully updated.' is present");
	        }
	  
//	        Modify address
	        driver.findElement(By.xpath("//*[@id='content']/ul[1]/li[3]/a")).click();
	        driver.findElement(By.cssSelector("#content > div > div.pull-right > a")).click();
	        
//	       Filling details in address page
	        String firstNam=sheet.getRow(i).getCell(2).getStringCellValue();
	        driver.findElement(By.id("input-firstname")).sendKeys(firstNam);
			
//			Validating firstName
			if(opencartvalidation.validateName(firstNam))
			{
				System.out.println("First name is verified");
			}
			else
			{
				System.out.println("First name is not verified");
			}

//			Retrieving lastName
			String lastNam=sheet.getRow(i).getCell(3).getStringCellValue();
			driver.findElement(By.id("input-lastname")).sendKeys(lastNam);
			
			
//			Verifying lastName
			if(opencartvalidation.validateName(lastNam))
			{
				System.out.println("Last name is verified");
			}
			else
			{
				System.out.println("Last name is not verified");
			}
			
//			Filling company details
			String company=sheet.getRow(i).getCell(5).getStringCellValue();
			driver.findElement(By.name("company")).sendKeys(company);
			
//			Retrieving address 1
			String address1 =sheet.getRow(i).getCell(6).getStringCellValue();
			driver.findElement(By.name("address_1")).sendKeys(address1);

//			Verifying address1		
			if(OpenCartValidation.Validataddress(address1))
			{
				System.out.println("Address1 is verified");
			}
			else
			{
				System.out.println("Address1 is not verified");
			}
			
//			Retrieving address 2
			String address2 =sheet.getRow(i).getCell(7).getStringCellValue();
			driver.findElement(By.name("address_2")).sendKeys(address2);
			
//			Verifying address2
			if(OpenCartValidation.Validataddress(address2))
			{
				System.out.println("Address2 is verified");
			}
			else
			{
				System.out.println("Address2 is not verified");
			}
			
//			Retrieving city
			String city =sheet.getRow(i).getCell(8).getStringCellValue();
			driver.findElement(By.id("input-city")).sendKeys(city);
			
//			Retrieving postcode
			String postcode =sheet.getRow(i).getCell(9).getStringCellValue();
			driver.findElement(By.id("input-postcode")).sendKeys(postcode);
			
			Select fromCountry= new Select(driver.findElement(By.id("input-country")));
			fromCountry.selectByIndex(106);
		    Thread.sleep(1000);
			
//			Verifying country
		    WebElement Country = fromCountry.getFirstSelectedOption();
			String country = Country.getText();
			
			if(country.equals("India"))
				System.out.println("India is selected");
			else
				System.out.println("Other country is selected");
			
//			Choosing state
			Select fromState= new Select(driver.findElement(By.id("input-zone")));
			fromState.selectByVisibleText("Kerala");
			
			WebElement State = fromState.getFirstSelectedOption();
			String state = State.getText();
			
//			Verifying chosen state
			if(state.equals("Kerala"))
				System.out.println("Kerala state selected");
			else
				System.out.println("Other state selected"); 
		    
//			Verifying continue button is displayed 
			boolean clicking= driver.findElement(By.xpath(".//*[@id='content']/form/fieldset/div[10]/div/label[2]")).isDisplayed();
		    if(clicking==true)
		    {
		    	System.out.println("Continue is displayed");
		    }
		   else
			   System.out.println("Continue is not displayed");
		   
		
		    driver.findElement(By.xpath(".//*[@id='content']/form/div/div[2]/input")).click();
		  
		    
//		    Modify wish list
		    driver.findElement(By.xpath("//*[@id='content']/div[2]/div[1]/a")).click();
		    
//		    Clicking modify your wishlist
		    
			driver.findElement(By.linkText("Modify your wish list")).click();
			Thread.sleep(1500);
			
//			Adding element present to cart
			driver.findElement(By.xpath("//*[@id='content']/div[1]/table/tbody/tr[1]/td[6]/button")).click();
			Thread.sleep(2500);
			
//			Clicking continue
			driver.findElement(By.cssSelector("#content > div.buttons.clearfix > div > a")).click();

//			
			driver.findElement(By.xpath("//*[@id='account-account']/ul/li[2]/a")).click();
			
//			Verifying continue button in logout page
			driver.findElement(By.partialLinkText("Logo")).click();
			
//			Validating logout page title 
			String logout=driver.getTitle();
			String actuallogTitle="Account Logout";
			if(opencartvalidation.validateTitle(logout, actuallogTitle))
			{
				System.out.println(logout+" page matches expected one");
			}
			else
			{
				System.out.println("Title mismatching");
			}
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
		}

}
