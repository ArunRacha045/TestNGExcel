package objects;

import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		
	}
	//Locators xpaths
	
	By username=By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input");
	By password=By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input");
	By loginButton=By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");
	By loginSuccessMessg = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6");
	By loginFailMesg = By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p");
	By logoutDropDown=By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[2]/ul/li/span/p");
	By logout=By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[2]/ul/li/ul/li[4]/a");
	
	public void LoginClick() {
		driver.findElement(loginButton).click();
		
	}
	
	public String loginSuccess()
	{
	return	driver.findElement(loginSuccessMessg).getText();	
	}
	
	public String LoginFail() {
		
		return driver.findElement(loginFailMesg).getText();
		
	}
	public void login(String uname, String pass) {
		
		//if(uname!="null")
		driver.findElement(username).sendKeys(uname);
		
		driver.findElement(password).sendKeys(pass);
		
		// TODO Auto-generated method stub
		
	}
	
public void login_userName(String uname) {
	 
	driver.findElement(username).sendKeys(uname);
	
}

	
	public void Logout() {
		
		driver.findElement(logoutDropDown).click();
		driver.findElement(logout).click();
		
	}
	
	public void SwitchToNewTab()
	{
		 Set<String> windowHandles = driver.getWindowHandles();

		    // Switch to the new tab
		    for (String windowHandle : windowHandles) {
		        if (!windowHandle.equals(driver.getWindowHandle())) {
		            driver.switchTo().window(windowHandle);
		              break;
		        }
		              
		    }
	}

}
