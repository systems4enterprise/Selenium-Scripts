package org.gradle;

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

public class TicketsWithUpcomingDeadlineTest {
	
	public static WebDriver driver;	
	public static String board = "http://test.b0ard.work//";
	String user1 = "ane123";	
	String password1 = "123456";
	String random = Integer.toString((int)(Math.random() * 100000 + 1));  
	WebElement element;
	String boardName = "Board " + random;
	String ticketType = "Ticket type " + random;
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
		
		/*
		//odi vo noviot bord	
		driver.findElement(By.linkText(boardName)).click();		
		Thread.sleep(2000);				
		
		//odi vo ticket types - on hover navigacija
		Actions action = new Actions(driver);
		WebElement configuration = driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3) > a:nth-child(1)"));
		action.moveToElement(configuration).moveToElement(driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1)"))).click().build().perform();
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button.btn.btn-green")));		

		
		//create new ticket type
		driver.findElement(By.cssSelector("button.btn.btn-green")).click();
		driver.findElement(By.cssSelector("input.form-group")).clear();
		driver.findElement(By.cssSelector("input.form-group")).sendKeys(ticketType);
		Thread.sleep(500);				 
		
		//odi vo priviledges
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(5) > button:nth-child(1)")));
		driver.findElement(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(5) > button:nth-child(1)")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("thead.tableWidth > tr:nth-child(1) > th:nth-child(1)")));		
		
		//add edit privileges to the ticket type na userot
		driver.findElement(By.xpath("//table[@id='privilegesTable']/tbody/tr/td[2]/div")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[4]/span")));
		driver.findElement(By.xpath("//li[4]/span")).click();
		Thread.sleep(500);
		*/
		
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
		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys("nov tiket");
		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys(Keys.RETURN);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tags")));
		driver.findElement(By.cssSelector(".tags")).click(); //otvori tiket
		Thread.sleep(2000);
		
		//otvori tiket
		driver.findElement(By.cssSelector("#assignNewUser > span.list-item-add-placeholder > span.glyphicon.glyphicon-plus")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#assignNewUser > span:nth-child(2) > div:nth-child(1) > button:nth-child(1)")));
			//addign user
		driver.findElement(By.cssSelector("#assignNewUser > span:nth-child(2) > div:nth-child(1) > button:nth-child(1)")).click();
		Thread.sleep(2000);
		WebElement element = driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
		driver.findElement(By.cssSelector("div.ticketDetails-main > div.row.tags")).click();
		Thread.sleep(2000);
		
		//zadavanje deadline na ticket
		driver.findElement(By.cssSelector("span.spnDeadline")).click();
		driver.findElement(By.name("deadlineDate")).clear();
			//denesen datum
		java.util.Date today = Calendar.getInstance().getTime();		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");		
		String deadline = format.format(today);
		String deadline2 = format2.format(today);
		driver.findElement(By.name("deadlineDate")).sendKeys(deadline2);
		//driver.findElement(By.cssSelector("div.ticketDetails-main > div.row.tags")).click();
		Thread.sleep(2000);		
	    
		//odi vo bordot
	    driver.get(board);
	    Thread.sleep(2000);	 		
  			
		//make sure deka ima tiket vo tickets with upcoming deadline
	    WebElement ticketWithUpcomingDeadline = driver.findElement(By.cssSelector(".col-md-8 > a:nth-child(1) > span:nth-child(1)"));
	    assertTrue(driver.findElements(By.cssSelector(".col-md-8 > a:nth-child(1) > span:nth-child(1)")).size() != 0 );
		
	    //odi nazad vo bordot
	    driver.get(board);
	    Thread.sleep(2000);	 		
  		driver.findElement(By.linkText(boardName)).click();		
  		Thread.sleep(2000);
  		
  		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tags")));
		driver.findElement(By.cssSelector(".tags")).click(); //otvori tiket
		Thread.sleep(2000);
		
		//otvori tiket
		driver.findElement(By.cssSelector("#assignNewUser > span.list-item-add-placeholder > span.glyphicon.glyphicon-plus")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#assignNewUser > span:nth-child(2) > div:nth-child(1) > button:nth-child(1)")));
			  		
  		//smeni go deadlne-ot za podaleku  	
		driver.findElement(By.cssSelector("span.spnDeadline")).click();
		driver.findElement(By.name("deadlineDate")).clear();
		
		  //calculate 10 days from now date
		 	// get a calendar instance, which defaults to "now"
	    Calendar calendar = Calendar.getInstance();	    	
	    	// add 32 days to the date/calendar
	    calendar.add(Calendar.DAY_OF_YEAR, 32);	    
	    	// now get 32 days from now
	    java.util.Date tenDaysFromNow = calendar.getTime();
	    	//format the date year-month-day
	    SimpleDateFormat format3 = new SimpleDateFormat("dd/MM/yyyy");	   
	    String   newDeadline       = format3.format(tenDaysFromNow);	    
			//zadadi deadline
		driver.findElement(By.name("deadlineDate")).sendKeys(newDeadline);
		//driver.findElement(By.cssSelector("div.ticketDetails-main > div.row.tags")).click();
		Thread.sleep(2000);	
		
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
		
		
	
		/*
		//izbrisi go novokreiraniot board
		driver.get(board);
		Thread.sleep(2000);
		WebElement deleteCreatedBoardButton = driver.findElement(By.cssSelector("a[data-name='" +boardName + "']"));
		deleteCreatedBoardButton.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[2]/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
		Thread.sleep(2000);
		*/
		
		
	}
	
	@AfterClass
	public static void afterClass() {
		//closes the browser
		driver.quit();
	}
}