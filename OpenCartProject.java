package BugSquashersPLP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author sarulr
 *
 */
public class OpenCartProject {

	public static void main(String[] args) throws InterruptedException {
//		 TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "D:/PLP/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		
//		Loading the opencart website
		driver.get("https://demo.opencart.com/");
		
//		Clicking My account page
		WebElement myAcc=driver.findElement(By.linkText("My Account"));
		myAcc.click();
		
//		Selecting register from My account
		driver.findElement(By.partialLinkText("Log")).click();
		
//		Getting page title and verifying
		OpenCartValidation opencartvalidation=new OpenCartValidation();
		
		String logintitle=driver.getTitle();
		String actualTitle="Account Login";
		
//		Verifying page title
		if(opencartvalidation.validateTitle(logintitle,actualTitle))
				System.out.println("Title "+logintitle+" matches the actual one");
		else
				System.out.println("Title is not verified");
		
		//Logging details to page
		driver.findElement(By.name("email")).sendKeys("sarulatharaji@gmail.com");
		
//		Entering password to login
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys("sararuja");
		
		driver.findElement(By.cssSelector("#content > div > div:nth-child(2) > div > form > input")).click();
		
//		Logged into My account page
//		Getting page title and verifying
		
		String myAccountTitle=driver.getTitle();
		String expectedTitle="My Account";
		
//		Verifying title
		if(opencartvalidation.validateTitle(myAccountTitle,expectedTitle))
				System.out.println("Title "+myAccountTitle+" matches the expected one");
		else
				System.out.println("Title is not verified");
		
//		Clicking edit your contents
		driver.findElement(By.linkText("Edit your account information")).click();
		
	    WebElement fname=driver.findElement(By.xpath("//*[@id='input-firstname']"));
	    fname.clear();
	    fname.sendKeys("jahnavi");
	    WebElement lname=driver.findElement(By.cssSelector("#input-lastname"));
	    lname.clear();
	    lname.sendKeys("Kanthamneni");  

	    
	    driver.findElement(By.id("input-email")).clear();
	    driver.findElement(By.id("input-email")).sendKeys("sarulatharaji@gmail.com");
	    
	    WebElement mobNo=driver.findElement(By.name("telephone"));
	    mobNo.clear();
	    mobNo.sendKeys("8974568125");

//	    Validating mobile number
		String mobNum=mobNo.getAttribute("value");
	    if(OpenCartValidation.validateNumber(mobNum))
	    {
	    	System.out.println("Valid mobile number");	    
	    }
	    else
	    {
	    	System.out.println("Enter 10 numbers and valid code");
	    }
	    driver.findElement(By.xpath("//*[@id='content']/form/div/div[2]/input")).click();

//    	Password
	    driver.findElement(By.xpath("//*[@id='content']/ul[1]/li[2]/a")).click();
	    driver.findElement(By.xpath(".//*[@id='input-password']")).sendKeys("sararuja");
        WebElement pass = driver.findElement(By.xpath(".//*[@id='input-password']"));
		String password = pass.getAttribute("value");

//		Re-entering password
        driver.findElement(By.xpath(".//*[@id='input-confirm']")).sendKeys("sararuja");
        
//        Verifying password
        if(opencartvalidation.validatePassword(password))
        {
        	System.out.println("Password matches given length");
        }
        else
        {
        	System.out.println("Password should be of 4 to 20 characters");
        }
        
        driver.findElement(By.xpath(".//*[@id='content']/form/div/div[2]/input")).click();
        
//        Verifying page content
        if(driver.getPageSource().contains("Your password has been successfully updated."))
        {
     	   System.out.println("The text 'Your password has been successfully updated.' is present");
        }
  
//        Modify address
        driver.findElement(By.xpath("//*[@id='content']/ul[1]/li[3]/a")).click();
        driver.findElement(By.cssSelector("#content > div > div.pull-right > a")).click();
        
//        Filling details in address page
        driver.findElement(By.id("input-firstname")).sendKeys("swathi");
		WebElement first = driver.findElement(By.name("firstname"));
		String firstName = first.getAttribute("value");
		
//		Validating firstName
		if(opencartvalidation.validateName(firstName))
		{
			System.out.println("First name is verified");
		}
		else
		{
			System.out.println("First name is not verified");
		}

		driver.findElement(By.id("input-lastname")).sendKeys("kushi");
		
		WebElement last = driver.findElement(By.name("lastname"));
		String lastName=last.getAttribute("value");
		
//		Verifying lastName
		if(opencartvalidation.validateName(lastName))
		{
			System.out.println("Last name is verified");
		}
		else
		{
			System.out.println("Last name is not verified");
		}
		
//		Filling company details
		driver.findElement(By.name("company")).sendKeys("capgemini");
		driver.findElement(By.name("address_1")).sendKeys("2-24/a, aganampudi");

//		Verifying address1
		String address1 = first.getAttribute ("value");
		if(OpenCartValidation.Validataddress(address1))
		{
			System.out.println("Address1 is verified");
		}
		else
		{
			System.out.println("Address1 is not verified");
		}
		driver.findElement(By.name("address_2")).sendKeys("Gajuwaka");
		String address2 = first.getAttribute ("value");
		
//		Verifying address2
		if(OpenCartValidation.Validataddress(address2))
		{
			System.out.println("Address2 is verified");
		}
		else
		{
			System.out.println("Address2 is not verified");
		}
		driver.findElement(By.id("input-city")).sendKeys("visakhapatnam");
		driver.findElement(By.id("input-postcode")).sendKeys("530046");
		
		Select fromCountry= new Select(driver.findElement(By.id("input-country")));
		fromCountry.selectByIndex(106);
	    Thread.sleep(1000);
		
//		Verifying country
	    WebElement Country = fromCountry.getFirstSelectedOption();
		String country = Country.getText();
		
		if(country.equals("India"))
			System.out.println("India is selected");
		else
			System.out.println("Other country is selected");
		
//		Choosing state
		Select fromState= new Select(driver.findElement(By.id("input-zone")));
		fromState.selectByVisibleText("Kerala");
		
		WebElement State = fromState.getFirstSelectedOption();
		String state = State.getText();
		
//		Verifying chosen state
		if(state.equals("Kerala"))
			System.out.println("Bihar state selected");
		else
			System.out.println("Other state selected"); 
	    
//		Verifying continue button is displayed 
		boolean clicking= driver.findElement(By.xpath(".//*[@id='content']/form/fieldset/div[10]/div/label[2]")).isDisplayed();
	    if(clicking==true)
	    {
	    	System.out.println("Continue is displayed");
	    }
	   else
		   System.out.println("Continue is not displayed");
	   
	
	    driver.findElement(By.xpath(".//*[@id='content']/form/div/div[2]/input")).click();
	  
	    
//	    Modify wish list
	    driver.findElement(By.xpath("//*[@id='content']/div[2]/div[1]/a")).click();
	    
//	    Clicking modify your wishlist
	    
		driver.findElement(By.linkText("Modify your wish list")).click();
		Thread.sleep(1500);
		
//		Adding element present to cart
		driver.findElement(By.xpath("//*[@id='content']/div[1]/table/tbody/tr[1]/td[6]/button")).click();
		Thread.sleep(2500);
		
//		Clicking continue
		driver.findElement(By.cssSelector("#content > div.buttons.clearfix > div > a")).click();

//		
		driver.findElement(By.xpath("//*[@id='account-account']/ul/li[2]/a")).click();
		
		//Verifying continue button in logout page
		driver.findElement(By.partialLinkText("Logo")).click();
		
		//Validating logout page title 
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
				    	System.out.println("Continue is displayed");
				    }
				   else
					   System.out.println("Continue is not displayed");
		
		driver.close();
		System.out.println("Logged out");
	}

}
