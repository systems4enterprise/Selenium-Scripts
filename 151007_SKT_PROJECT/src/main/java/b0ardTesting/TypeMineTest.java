package org.gradle;

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

public class TypeMineTest {
	
	public static WebDriver driver;	
	public static String baseUrl = "http://admintest.b0ard.work//";
	public static String board = "http://test.b0ard.work//";
	//String user1 = "user1" + Integer.toString((int)(Math.random() * 100000 + 1));
	//String user2 = "user2" + Integer.toString((int)(Math.random() * 100000 + 1));
	String user1 = "ane123";
	String user2 = "user13091993";
	String password1 = "123456";
	String password2 = "123456";
	String user1TicketId = "";
	JavascriptExecutor js = (JavascriptExecutor) driver;  
	WebElement element;
	String boardName = "Test Board " + Integer.toString((int)(Math.random() * 100000 + 1));
	String ticketType = "Type mine " + user1;
	String ticketTitle = "Ticket title";
	
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
		
	
		//odi vo bordot
		driver.findElement(By.linkText(boardName)).click();		
		Thread.sleep(2000);				
		
		
		
		//odi vo priviledges
		Actions actions = new Actions(driver);
		WebElement menu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(menu);
		WebElement subMenu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);
		
		//stavi privilegija mine na tiketot
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.privilegesCircle.context-menu-two")));
		driver.findElement(By.cssSelector("div.privilegesCircle.context-menu-two")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/ul/li")));
		driver.findElement(By.xpath("//body/ul/li[5]")).click();		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.privilegesCircle.context-menu-two")));
		driver.findElement(By.xpath("//table[@id='privilegesTable']/tbody/tr[2]/td[2]/div")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/ul/li")));
		driver.findElement(By.xpath("//li[5]/span")).click();
		Thread.sleep(2000);
		
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
		
		//user 1 logout
		driver.get(board);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("b.caret")));
		driver.findElement(By.cssSelector("b.caret")).click();
		driver.findElement(By.linkText("LogOut")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();		
		
		//user 2 login
		driver.get(board);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Password")));
		driver.findElement(By.id("Password")).clear();
		driver.findElement(By.id("Password")).sendKeys(password2); //password na user2
		driver.findElement(By.id("Username")).clear();
		driver.findElement(By.id("Username")).sendKeys(user2);//userid na vtor korisnik
		driver.findElement(By.name("button")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(boardName)));
			
		//odi vo bordot
		driver.findElement(By.linkText(boardName)).click();		
		Thread.sleep(2000);		
		
		//proveri dali go nema  		
  		List <WebElement> ticketWebElements = driver.findElements(By.cssSelector(".tags"));		
  		System.out.println(ticketWebElements.size());
  		assertTrue(ticketWebElements.size() == 0);
		
	//edit privilegija na kraj
 
		//odi vo bordot
	    driver.get(board);
	    Thread.sleep(2000);	
	    driver.findElement(By.linkText(boardName)).click();		
  		Thread.sleep(2000);
		  
		  
		//odi vo priviledges
		actions = new Actions(driver);
		menu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(menu);
		subMenu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);
		
		//stavi privilegija edit na tiketite za dvajcata korisnici
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.privilegesCircle.context-menu-two")));
		driver.findElement(By.cssSelector("div.privilegesCircle.context-menu-two")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/ul/li")));
		driver.findElement(By.xpath("//body/ul/li[4]")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.privilegesCircle.context-menu-two")));
		driver.findElement(By.xpath("//table[@id='privilegesTable']/tbody/tr[2]/td[2]/div")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/ul/li")));
		driver.findElement(By.xpath("//li[4]/span")).click();
		Thread.sleep(2000);
		
		//odi vo bordot
	    driver.get(board);
	    Thread.sleep(2000);	
	    driver.findElement(By.linkText(boardName)).click();		
  		Thread.sleep(2000);
  		
  		//izbrisi go tiketot
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.list-item-card-outer:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > button:nth-child(1)")));
		Thread.sleep(2000);

		
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
		
		//user2 logout
		driver.get(board);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("b.caret")).click();
		driver.findElement(By.linkText("LogOut")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		Thread.sleep(2000);		
		
	}
	
	@AfterClass
	public static void afterClass() {
		//closes the browser
		driver.quit();
	}
}