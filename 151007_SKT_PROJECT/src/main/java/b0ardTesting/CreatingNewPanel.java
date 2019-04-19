package b0ardTesting;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreatingNewPanel {

	//private static String baseURL = "http://zabegan-001-site31.itempurl.com";
	WebDriver driver;
	public static String panel_name = "CI Panel " + CreatingUser.LastName;
	
	@Before
	public void setUp() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.get(LoggingIn.baseURL);
		driver.findElement(By.id("Username")).clear();
		//driver.findElement(By.id("Username")).sendKeys(CreatingUser.user_extern);
		
		driver.findElement(By.id("Username")).sendKeys("Selenium");
		Thread.sleep(2000);
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys(CreatingUser.password);
		driver.findElement(By.id("Username")).clear();
		driver.findElement(By.id("Username")).sendKeys("Selenium");
		Thread.sleep(2000);
		driver.findElement(By.id("Username")).sendKeys(CreatingUser.user_extern);


		driver.findElement(By.name("button")).click();
		Thread.sleep(4000);
		
	}

	@Test
	public void createPanel() throws InterruptedException{
		
		WebDriverWait wait = new WebDriverWait(driver, 8000);
		String panel_name = "Panel name";
		//create a board first
		String boardName = CreatingUser.LastName;
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("pageContentDiv")));
		driver.findElement(By.id("pageContentDiv")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@type='button'])[2]")));
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("board-name")));
		Thread.sleep(2000);
		driver.findElement(By.id("board-name")).clear();
		driver.findElement(By.id("board-name")).sendKeys(boardName);
		driver.findElement(By.xpath("(//button[@type='button'])[5]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(boardName)));		
		
		
		driver.findElement(By.linkText(CreatingUser.LastName)).click();
		System.out.println(CreatingUser.LastName + " has entered his newly created board");
		Thread.sleep(5000);
		
		try{
			driver.findElement(By.cssSelector(".list-item-add-placeholder")).sendKeys(Keys.ENTER);
			driver.findElement(By.cssSelector(".list-item-add-placeholder")).click();
			
		}
		catch(Exception e) {
			
		}
		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).clear();
		driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys(panel_name);
		driver.findElement(By.cssSelector("input.list-item-add-save")).click();
		
		Thread.sleep(6000);
		//WebElement we = driver.findElement(By.cssSelector("#board > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > span"));
		WebElement we = driver.findElement(By.cssSelector("#board > div > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > p:nth-child(1)"));
		Thread.sleep(4000);
		assertEquals(panel_name, we.getText());
		System.out.println(CreatingUser.user_extern + " has created the panel " + panel_name);
		System.out.println("Over");
	}
	
	@After
	public void dispose(){
		driver.quit();
	}

}
