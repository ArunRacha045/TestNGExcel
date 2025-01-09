package TestCases;

import java.io.IOException;
import java.time.Duration;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Test.utility.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import objects.LoginPage;

public class LoginPageScenario {
	XSSFWorkbook wb;
	WebDriver driver;
	LoginPage loginPage;
	//LoginPage loginPage=new LoginPage(driver);
	 public void YourPage(WebDriver driver) {
	        this.driver = driver;
	    }
	 /* working code
	@BeforeClass()
	public void setUp()
	
	{
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		//driver= new FirefoxDriver();
		//driver= new EdgeDriver();
		//driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.get("https://demoqa.com/select-menu");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		loginPage=new LoginPage(driver);
		
	}*/
	 
	 @Parameters({"browser","url"})
	 @BeforeClass(alwaysRun = true)
		public void setUp(String browser, String url)
		{
		 //browser="chrome";
		 System.out.print("BeforeMethod: \t");
		 System.out.println(" Browser value :  "+browser);
		 if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}else
			if (browser.equalsIgnoreCase("firefox")) {
		
			  driver= new FirefoxDriver();
			} else
				if (browser.equalsIgnoreCase("edge")) {
							
			  driver= new EdgeDriver();
				}
		 driver.get(url);
			//driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			//driver.get("https://demoqa.com/select-menu");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			loginPage=new LoginPage(driver);
			
		}
	
	@DataProvider(name ="excel-data")
	public Object[][] excelDP1() throws IOException{
    	//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
    	/*Object[][] arrObj = getExcelData("Location of the excel file in your local system","Name
of the excel sheet where your data is placed");*/
		//create instance for TestUtil class
		TestUtil testUtil=new TestUtil();
	Object[][] arrObj = testUtil.getExcelData("C:\\Users\\shilp\\eclipse-workspace\\TestNGExcel\\DataSheet_1.xlsx","Valid");
	
    	return arrObj;
    
	}
/*private Object[][] getExcelData(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}
	*/

//Test case: Verify login
@Test(dataProvider="excel-data")
public void VerifyLogin(String UserName, String Password, String expected, String ex1, String ex2, String ex3)

{
	System.out.print("1");
	//LoginPage loginPage=new LoginPage(driver);
	loginPage.login(UserName, Password);
	System.out.println("System");
	/*driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("uname1");
	driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")).sendKeys("Pwd1");
	driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
	*/
	loginPage.LoginClick();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	String e = "Dashboard";
    loginPage.loginSuccess();
	//String actual=driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p")).getText();
  
   Assert.assertEquals(loginPage.loginSuccess(), e);
   loginPage.Logout();
   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));   


}

@DataProvider(name ="excel-data-invalid")
	public Object[][] excelDP() throws IOException{
    	//We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
    	/*Object[][] arrObj = getExcelData("Location of the excel file in your local system","Name
of the excel sheet where your data is placed");*/
	TestUtil testUtil=new TestUtil();
	Object[][] arrObj = testUtil.getExcelData("C:\\Users\\shilp\\eclipse-workspace\\TestNGExcel\\DataSheet_1.xlsx","Invalid");
	System.out.println(arrObj);
    	return arrObj;
    	
	}
//private Object[][] getExcelData(String string, String string2) {
	// TODO Auto-generated method stub
	//return null;



//Test case: Verify login with InValid Credentials
@Test(dataProvider="excel-data",dependsOnMethods = { "VerifyLogin" }, groups={"System","regression"})

public void  VerifyWithInValidLoginDetails(String UserName,String Password, String expected, String ex1,String ex2,String ex3)

{
	System.out.print("2");
	String[] myArray = {UserName, Password};
	// Using a for loop to iterate over the elements in the array
    for (int i = 0; i < myArray.length; i++) {
       // System.out.println("Parameter " + (i + 1) + ": " + myArray[i]);
    	System.out.println("myArray[i]" +myArray[i]);
    	
 // if (!(myArray[i].equalsIgnoreCase(""))) //!(myArray[i].isBlank())
  if (myArray[i]!="")
  {
	loginPage.login_userName(myArray[i]);
	loginPage.LoginClick();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.navigate().refresh();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
  }
	/*
	 * else { driver.navigate().refresh(); }
	 */
	 
  }
	
	// below is to add any value in the report not mandatory
	//Reporter.log("myArray[i] value is : "+myArray[i]+" ex1 :    "+ex1+"  ex2 :    "+ex2+" ex3 :    "+ex3);
	//Reporter.log("/n In side Invlid method");
//	System.out.println("myArray[i] value is : "+myArray[i]+" ex1 :    "+ex1+"  ex2 :    "+ex2+" ex3 :    "+ex3);
	//loginPage.LoginFail();
	 //Assert.assertEquals(loginPage.LoginFail(), "Invalid credentials");
	// below with error message assert
	//Assert.assertEquals(myArray[i],"arun", "------------Testing123 -------");
    }

/*	driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[1]/div/div[2]/input")).sendKeys("Admin");
	driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[2]/div/div[2]/input")).sendKeys("admin123");
	driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button")).click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	//boolean actual= driver.findElement(By.xpath("//span[@class='oxd-topbar-header-breadcrumb']/h6")).isDisplayed();
	String actual= driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]/span/h6")).getText();
	//*[@id="app"]/div[1]/div[1]/header/div[1]/div[1]/span/h6
	Assert.assertEquals(actual, "Dashboard");
*/	
    @Test()
    public void verifyTableScenario()
    {
    	System.out.print("3");
    	// Open the web page
       // driver.get("https://demo.guru99.com/test/web-table-element.php");
    	
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        // Locate the table using its ID, class, or XPath
        WebElement table = driver.findElement(By.xpath("//*[@id=\"leftcontainer\"]/table"));
        Assert.assertTrue(table.isDisplayed(), "---table is displayed----");
if (table.isDisplayed()){ 
	System.out.print("--run the table scenario---");
	// Get all rows from the table
        java.util.List<WebElement> rows = table.findElements(By.tagName("tr"));
        System.out.print(rows.size());

        // Iterate through each row
        for (WebElement row : rows) {
            // Get all columns in the current row
            java.util.List<WebElement> columns = row.findElements(By.tagName("td"));
            
            // Iterate through each column
            for (WebElement column : columns) {
                // Extract and print the text content of each cell
            	String cellValue=column.getText();

             System.out.print(cellValue + "\t");
            	
              if (cellValue.equals("NCC")) {
            	  
            	  System.out.print(cellValue+"-----is found----");
              }
              if(cellValue.equals("IDFC Bank")) {
            	  
            	  System.out.print(cellValue+"----is found----");
            	  
              }
         if(cellValue.equals("22")) {
            	  
            	  System.out.print(cellValue+"----is found----");
          }
              // System.out.print(columns.size());
            }
      System.out.println(); // Move to the next line after each row
        }
}
}

@Test()
public void verifyDropdown() {
	
	System.out.print("4");
	// Open the web page
    driver.get("https://demoqa.com/select-menu");
	
	//---working starts here code--------
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	//WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"selectOne\"]/div"));
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
	WebElement dropdown=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"selectOne\"]/div/div[1]/div[1]")));
	  
  dropdown.click();
  //working xpath
 //WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"react-select-3-option-0-0\"]")));
 //below 2 are working xpaths:
  //WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,' css-yt9ioa-option') and contains(text(),'Dr.')]")));
 WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"selectOne\"]/div[2]/div/div/div[2]/div[contains(text(),'Mr.')]")));

  option.click();
  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));    
   // By optionLocator = By.xpath("//div[@class='css-yk16xz-control']");
   // WebElement optionElement = new WebDriverWait(driver, Duration.ofSeconds(60)).until(ExpectedConditions.elementToBeClickable(optionLocator));
  //.. WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='css-yk16xz-control' and text()='Dr.']")));
  // WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='css-2613qy-menu' and text()='Dr.']")));
   
   // System.out.println("check in click");
  
   // System.out.println("check in function");
   // Select select=new Select(optionElement);
  // select.selectByVisibleText("Dr.");
   // String SelectedOption=select.getFirstSelectedOption().getText();
   // System.out.println("check out function");
	//System.out.println("Selected option is: " +SelectedOption);
	
}
    
@Test()
public void VerifyModalClass() {
	
	System.out.print("5");
	driver.get("https://demoqa.com/modal-dialogs");
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	driver.manage().window().maximize();
	driver.findElement(By.id("showSmallModal")).click();
	//--driver.switchTo().activeElement().findElement(By.className("modal-content")).click();
	
	// Explicitly wait for the modal to be present
  //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
 //   WebElement modal = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='modal-dialog modal-sm']//div[@class='modal-content']//div[@class='modal-header']//div[@class='modal-title h4' and text()='Small Modal']")));
    //modal.click();
	String modalText=driver.findElement(By.id("closeSmallModal")).getText();
	System.out.println("text: "+modalText);
    driver.findElement(By.id("closeSmallModal")).click();
   // WebElement close=driver.findElement(By.id("closeSmallModal"));
    //close.click();
    By modalLocator = By.id("showLargeModal");
    WebElement modalElement = null;
    
    if (driver.findElements(modalLocator).size()>0)
    {

    	modalElement = driver.findElement(modalLocator);
    	if(modalElement.isDisplayed()){
    		System.out.print("succ");
    		String text=modalElement.getText();
    		Assert.assertEquals(text, "Large modal", "---modalText---");
    	} 

    }
    else {

    	driver.findElement(By.id("showLargeModal")).click();
    	driver.findElement(By.id("closeLargeModal")).click();


    }
  Reporter.log("Modal class is pass");
}

@Test()
public void verifyNewTab() {
	System.out.print("6 \n");
	driver.get("https://demoqa.com/browser-windows");
	// Get the main window 
	String MainWin=driver.getWindowHandle();
	System.out.print("main window: "+MainWin+"\n");
	driver.findElement(By.id("tabButton")).click();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	loginPage.SwitchToNewTab();
	
	/*	
	// Get the window handles to switch between tabs
    Set<String> windowHandles = driver.getWindowHandles();

    // Switch to the new tab
    for (String windowHandle : windowHandles) {
        if (!windowHandle.equals(driver.getWindowHandle())) {
            driver.switchTo().window(windowHandle);
              break;
        }
              
    }*/
    String NewTabTitle = driver.getTitle();
    System.out.println("new tab title : " +NewTabTitle );
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    String NewTabText=driver.findElement(By.xpath("//*[@id=\"sampleHeading\"]")).getText();
    System.out.println("new tab text : " +NewTabText );
    driver.close();
    
	driver.switchTo().window(MainWin);
	String mainWinText=driver.getTitle();
	System.out.println("main win text: " +mainWinText);
	
}

@AfterClass(alwaysRun = true)
public void tearDown() throws IOException
{
	 try {
         if (wb != null) {
             wb.close();
         }
     } catch (Exception e) {
         System.out.println("Error closing workbook: " + e.getMessage());
     }
	 System.out.print("AfterClass: \t");
	driver.quit();
	
}
}