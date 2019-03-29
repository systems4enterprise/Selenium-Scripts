package b0ardTesting;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteBoardsAndTickets {
	
	public static WebDriver driver;	
	public static String baseUrl = "http://test.b0ard.work//";
	
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();		
	}
	
	@Test
	public void test() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 8000);
		
		
		//logiranje na user1 na bordot
		driver.get(baseUrl);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Password")));		
		driver.findElement(By.id("Username")).clear();
		driver.findElement(By.id("Username")).sendKeys("Selenium");
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.name("button")).click();
		Thread.sleep(2000);
		
		List <WebElement> boardNumber = driver.findElements(By.className("glyphicon-option-horizontal"));
		
		for(int i=1; i< boardNumber.size() ; i++){
			driver.findElement(By.cssSelector("#mCSB_2_container > li:nth-child(2) > a:nth-child(1)")).click(); //klikni na vtor bord
			Thread.sleep(2000);
			try{
				driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > button:nth-child(1)")).click();
				Thread.sleep(2000);
				driver.findElement(By.cssSelector("#deleteTicketModal > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(3) > button:nth-child(2)")).click();
				Thread.sleep(2000);
				
				
			}
			catch (Exception e) {
				
			}
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div.dropdown:nth-child(1) > a:nth-child(1)")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("#mCSB_2_container > li:nth-child(2) > div:nth-child(2) > a:nth-child(1)")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//li[2]/span")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
			Thread.sleep(2000);
			
			
		}
		
	}
	
	@AfterClass
	public static void afterClass() {
		//closes the browser
		driver.quit();
	}
}