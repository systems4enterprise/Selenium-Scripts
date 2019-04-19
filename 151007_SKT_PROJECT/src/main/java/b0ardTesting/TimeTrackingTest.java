package b0ardTesting;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class TimeTrackingTest {
	
	public static WebDriver driver;	
	public static String board = "http://test.b0ard.work//";
	String user1 = "ane123";	
	String password1 = "123456";
	String random = Integer.toString((int)(Math.random() * 100000 + 1));  
	WebElement element;
	String boardName = "Board " + random;
	String ticketType = "Ticket type " + random;
	String ticketTitle = "nov tiket " + random;
	//Actions action = new Actions(driver);
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();		
	}
	
	@Test
	public void test() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 8000);
				
		//logiranje na user1 na bordot
		driver.get(board);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Password")));		
		driver.findElement(By.id("Username")).clear();
		driver.findElement(By.id("Username")).sendKeys(user1);
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys(password1);
		driver.findElement(By.name("button")).click();
			
		//user1 kreira nov bord
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
		
		//nazad na noviot bord
		driver.get(board);
		Thread.sleep(2000);		
		driver.findElement(By.linkText(boardName)).click();				
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.list-item-add-placeholder.add-board-list-span")));		
		
		//otvori time tracking
				Actions actions = new Actions(driver);
				WebElement menu = driver.findElement(By.cssSelector(".navigation > li:nth-child(4) > a:nth-child(1)"));
				actions.moveToElement(menu);
				WebElement subMenu = driver.findElement(By.cssSelector(".navigation > li:nth-child(4) > a:nth-child(1) > span:nth-child(2)"));
				actions.moveToElement(subMenu);
				actions.click().build().perform();
				Thread.sleep(2000);
				
				//ako ima drugi tiketi, submit time
				try{
					while(true){
						driver.findElement(By.cssSelector(".fa-angle-double-right")).click(); // >>
						Thread.sleep(2000);
						driver.findElement(By.cssSelector(".btn-info")).click();
						Thread.sleep(2000);	
					}
					

				}
				catch(Exception e){
					
				}
				
				//nazad na noviot bord
				driver.get(board);
				Thread.sleep(2000);		
				driver.findElement(By.linkText(boardName)).click();				
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.list-item-add-placeholder.add-board-list-span")));		
				
				
		
		//kreiranje nova kolona vo bordot
		driver.findElement(By.cssSelector("span.list-item-add-placeholder.add-board-list-span")).click();
		driver.findElement(By.id("txtNewBoardListName")).clear();
		driver.findElement(By.id("txtNewBoardListName")).sendKeys("user1");
		driver.findElement(By.cssSelector("input.list-item-add-save")).click();
		
		//kreiranje tiket i zadavanje ticket type 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.glyphicon.glyphicon-plus")));		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("span.glyphicon.glyphicon-plus")).click(); //klikni kopce za dodavanje nov ticket
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='text'])[6]")));		
		driver.findElement(By.xpath("(//input[@type='text'])[6]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys("Ticket type"); //ticketType
		Thread.sleep(4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='text'])[6]")));		
		driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys(Keys.RETURN);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("txtAddNewTicketTitle")));
		driver.findElement(By.name("txtAddNewTicketTitle")).clear();
		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys(ticketTitle);
		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys(Keys.RETURN);
		Thread.sleep(2000);

		
		//otvori time tracking
		actions = new Actions(driver);
		menu = driver.findElement(By.cssSelector(".navigation > li:nth-child(4) > a:nth-child(1)"));
		actions.moveToElement(menu);
		subMenu = driver.findElement(By.cssSelector(".navigation > li:nth-child(4) > a:nth-child(1) > span:nth-child(2)"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);
		
		
		//proveri dali go ima tiketot
		WebElement ticketInTimeTracking = driver.findElement(By.cssSelector("#submit-time-container > div > div:nth-child(1) > div > div.trackingScroll > div > div > div.list-item-card-outer.col-md-8.col-sm-8.divSubmittedCardDetails > div > span.list-item-card-title"));
		String ticketInTimeTrackingTitle = ticketInTimeTracking.getAttribute("title").toString();
		System.out.println("Ticket title in time tracking: " + ticketInTimeTrackingTitle);
		//assertTrue(ticketInTimeTrackingTitle.equals(ticketTitle));
		
		//nazad na noviot bord
		driver.get(board);
		Thread.sleep(2000);		
		driver.findElement(By.linkText(boardName)).click();				
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.list-item-add-placeholder.add-board-list-span")));		
		
		
		//otvori tiker
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tags")));
		driver.findElement(By.cssSelector(".tags")).click(); //otvori tiket
		Thread.sleep(8000); //drzi go tiketot otvoren 8 sekundi
		
		
		//odi vo bordot
	    driver.get(board);
	    Thread.sleep(2000);	
	    
	  //nazad na noviot bord
  		driver.get(board);
  		Thread.sleep(2000);		
  		driver.findElement(By.linkText(boardName)).click();				
  		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.list-item-add-placeholder.add-board-list-span")));	
		
		//otvori time tracking
		actions = new Actions(driver);
		menu = driver.findElement(By.cssSelector(".navigation > li:nth-child(4) > a:nth-child(1)"));
		actions.moveToElement(menu);
		subMenu = driver.findElement(By.cssSelector(".navigation > li:nth-child(4) > a:nth-child(1) > span:nth-child(2)"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);
		
		//proveri go vremeto na tiketot
		WebElement ticketTime = driver.findElement(By.cssSelector("#submit-time-container > div > div:nth-child(1) > div > div.trackingScroll > div > div > div.col-md-2.col-sm-2.text-center.trackingTicketTime > p:nth-child(2)"));
		String ticketTimeString = ticketTime.getText();
		System.out.println("Time of the ticket: " + ticketTimeString);
		assertFalse(ticketTimeString.equals("00:00:00"));
		
		//submit time
		driver.findElement(By.cssSelector(".fa-angle-double-right")).click(); // >>
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".btn-info")).click();
		Thread.sleep(2000);	
		
		//odi vo report
		driver.findElement(By.cssSelector("#btnReports")).click();
		Thread.sleep(4000);	
		
		//pisi go imeto na tiketot vo search
		driver.findElement(By.cssSelector("#timeLogsTable_filter > label > input")).clear();
		driver.findElement(By.cssSelector("#timeLogsTable_filter > label > input")).sendKeys(ticketTitle);
		Thread.sleep(2000);	
		
		//proveri dali vremeto vo reports e isto so vremeto sto si go zacuval
		WebElement ticketTimeInReports = driver.findElement(By.cssSelector("#timeLogsTable > tbody > tr > td:nth-child(3)"));
		String ticketTimeInReportsString = ticketTimeInReports.getText();
		System.out.println("Vreme zacuvano vo reports: " + ticketTimeInReportsString);
		assertFalse(ticketTimeInReportsString.equals(ticketTimeString));

		//odi vo bordot
	    driver.get(board);
	    Thread.sleep(2000);	
	    driver.findElement(By.linkText(boardName)).click();		
  		Thread.sleep(2000);
  		
  		//izbrisi go tiketot
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.list-item-card-outer:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > button:nth-child(1)")));
		Thread.sleep(2000);
  		driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > button:nth-child(1) > span:nth-child(1)")).click();
  		Thread.sleep(2000);
  		driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
  		Thread.sleep(2000);
  		
  		//izbrisi go noviot bord 
	    driver.get(board);
		Thread.sleep(2000);
		WebElement deleteCreatedBoardButton = driver.findElement(By.cssSelector("a[data-name='" +boardName + "']"));
		deleteCreatedBoardButton.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[2]/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
		Thread.sleep(2000);
  		
	    
	    //user 1 logout
		driver.get(board);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("b.caret")));
		driver.findElement(By.cssSelector("b.caret")).click();
		driver.findElement(By.linkText("LogOut")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();		
			
		
		System.out.println("Over");
		
		
	}
	
	@AfterClass
	public static void afterClass() {
		//closes the browser
		driver.quit();
	}
}