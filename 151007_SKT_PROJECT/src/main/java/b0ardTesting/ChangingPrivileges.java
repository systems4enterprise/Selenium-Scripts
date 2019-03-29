package b0ardTesting;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ChangingPrivileges {

	//private static String baseURL = "http://zabegan-001-site31.itempurl.com";
	WebDriver driver;
	
	@Before
	public void setUp() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.get(LoggingIn.baseURL);
	
		CreatingUser.user_extern = "Selenium";
		CreatingUser.password = "123456";
		CreatingUser.FirstName = "Aleksandar";
		CreatingUser.LastName = "Gjorgievski";
		driver.findElement(By.id("Username")).clear();
		driver.findElement(By.id("Username")).sendKeys(CreatingUser.user_extern);
	
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys(CreatingUser.password);
		

		
		driver.findElement(By.name("button")).click();
		Thread.sleep(4000);
	}
	
	@Test
	public void changePrivileges() throws InterruptedException{

//		driver.findElement(By.linkText(CreatingUser.LastName)).click();
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("SeleniumBord11/03/2019 1587")).click();
		
		
		
		Thread.sleep(6000);
		
		driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a")).click();
		Thread.sleep(10000);
		
		
		List<WebElement> thead = driver.findElements(By.cssSelector("thead > tr > th"));
		int ticketTypeIndex = -1;
		for(int i = 0; i < thead.size(); i++){
			if(thead.get(i).getText().contains("CI TEST")){
				ticketTypeIndex = i;
				
				break;
			}
		}
		System.out.println("ticketTypeIndex: " + ticketTypeIndex);
		List<WebElement> lst = driver.findElements(By.cssSelector("tbody > tr"));
		System.out.println("User list size: " + lst.size());
		int ind = -1;
		System.out.println("LoggingIn.logged_in_user: " + CreatingUser.FirstName + " " + CreatingUser.LastName);
		for(int i = 0 ; i < lst.size(); i++){
			String user = lst.get(i).findElement(By.xpath(".//td")).getText();
			System.out.println(user);
			if(user.contains(CreatingUser.FirstName + " " + CreatingUser.LastName)){
				
				ind = i;
				break;
				
			}
		}
		
		ind++;
		
		System.out.println("Aleksandar se naoga na pozicija " + ind);
		
		//from all to none
		driver.findElement(By.cssSelector("tbody > tr:nth-child(" + ind + ") > td:nth-child(2)")).click(); 
		//driver.findElement(By.cssSelector("tbody > tr:nth-child(" + ind + ") > td:nth-child(" + ticketTypeIndex + ")")).click(); 
		//driver.findElement(By.cssSelector("tbody > tr:nth-child(" + ind + ") > td:nth-child(1)")).sendKeys(Keys.ENTER);

		
		
		Thread.sleep(4000);
		driver.findElement(By.xpath("//body/ul/li[4]")).click(); //edit privilegii
		//driver.findElement(By.cssSelector("div.div-privileges-list > ul > li:nth-child(1)")).click();
		
		
//		driver.findElement(By.cssSelector("tbody > tr:nth-child(" + ind + ") > td:nth-child(" + ticketTypeIndex + ")")).click(); 
//		Thread.sleep(1000);
//		driver.findElement(By.cssSelector("tbody > tr:nth-child(" + ind + ") > td:last-child(" + ticketTypeIndex + ")")).click(); 

		Thread.sleep(2000);
		
		
		String bgColor = driver.findElement(By.cssSelector("tbody > tr:nth-child(" + ind + ") > td:nth-child(2) > div")).getCssValue("background-color"); 
		System.out.println(bgColor);
//		String bgColor = driver.findElement(By.cssSelector("tbody > tr:nth-child(" + ind + ") > td:nth-child(" + ticketTypeIndex + ") > div")).getAttribute("style"); 
		//System.out.println(bgColor);
		String [] props = bgColor.split("; ");
		int i = 0;
		for(int j = 0; j < props.length; j++){
			if(props[j].contains("background"))
			{
				i = j;
				break;
			}
			
		}
	 	
		int splitter = props[i].indexOf(':');
		String subStr = props[i].substring(splitter+1, props[i].length());
		
		assertTrue(bgColor.equals("rgba(67, 160, 71, 1)"));
		System.out.println(CreatingUser.user_extern + " has successfully added editing privileges to his newly created ticket type: " + CreatingNewTicket.newTicketType);

	}
	
	@After
	public void dispose() throws InterruptedException{
		Thread.sleep(3000);
		driver.quit();
	}
}
