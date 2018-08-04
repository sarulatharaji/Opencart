/**
 * 
 */
package BugSquashersPLP;

/**
 * @author sarulr
 *
 */
public class OpenCartValidation {
	
//	Validation for page title
	public boolean validateTitle(String gotTitle,String actualTitle)
	{
		if(gotTitle.equals(actualTitle))
			return true;
		else
			return false;
	}
	
//	Validation for password
	public boolean validatePassword(String password)
	{
		String pat="[A-Za-z]{4,20}";
		if(password.matches(pat))
			return true;
		else
			return false;
	}
	
//	Validation for names
	public boolean validateName(String name)
	{

		String pat="[A-Za-z]{1,32}";
		if(name.matches(pat))
			return true;
		else
			return false;
	}
	
//	Validation for address
	public static boolean Validataddress(String address)
	{
		String pat="[A-Za-z]{3,128}";
		if(address.matches(pat))
				return true;
		else
				return false;
    }
	
//	Validation for mobile number
	public static boolean validateNumber(String mobNum)
	{
		String pat="[0-9]{1,10}";
		if(mobNum.matches(pat))
			return true;
		else
			return false;
		
	}
	
}
