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

public class TicketTypePriviledgesTest {
	
	public static WebDriver driver;	
	public static String board = "http://test.b0ard.work//";
	String user1 = "ane123";	
	String password1 = "123456";
	String random = Integer.toString((int)(Math.random() * 100000 + 1));  
	String boardName = "Board " + random;
	String ticketType = "Ticket type " + random;
	String ticketTitle = "nov tiket " + random;

	
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("b.caret")));		
				
		//user1 kreira nov bord
		driver.get(board);
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

///////////// glaven del //////////////////////////////////////////////////////////////////////
		
		//odi vo priviledges
		Actions actions = new Actions(driver);
		WebElement menu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(menu);
		WebElement subMenu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);
		
		
		//stavi privilegija None na tiketot
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.privilegesCircle.context-menu-two")));
		driver.findElement(By.cssSelector("div.privilegesCircle.context-menu-two")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/ul/li")));
		driver.findElement(By.xpath("//body/ul/li")).click();
		Thread.sleep(2000);
		

		//odi vo bordot
	    driver.get(board);
	    Thread.sleep(2000);	
	    driver.findElement(By.linkText(boardName)).click();		
  		Thread.sleep(2000);
  		
  		//proveri dali go nema  		
  		List <WebElement> ticketWebElements = driver.findElements(By.cssSelector(".tags"));		
  		System.out.println(ticketWebElements.size());
  		assertTrue(ticketWebElements.size() == 0);
  		
  		//odi vo priviledges
		actions = new Actions(driver);
		menu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(menu);
		subMenu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);
		
		//stavi privilegija read na tiketot
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.privilegesCircle.context-menu-two")));
		driver.findElement(By.cssSelector("div.privilegesCircle.context-menu-two")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/ul/li")));
		driver.findElement(By.xpath("//body/ul/li[2]")).click();
		Thread.sleep(2000);
		
		//odi vo bordot
	    driver.get(board);
	    Thread.sleep(2000);	
	    driver.findElement(By.linkText(boardName)).click();		
  		Thread.sleep(2000);
  		
  		//otvori tiket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tags")));
		driver.findElement(By.cssSelector(".tags")).click(); //otvori tiket
		Thread.sleep(2000);
  		
		//proveri dali e klikabilno menuvanje na imeto
		driver.findElement(By.cssSelector("#hCardTitle")).click();			 
		Thread.sleep(2000);
		List <WebElement> inputForEditingTicketName = driver.findElements(By.cssSelector("div.row:nth-child(5) > div:nth-child(2) > input:nth-child(2)"));        
		System.out.println("inputForEditingTicketName size: " + inputForEditingTicketName.size());
		assertTrue(inputForEditingTicketName.size() == 0);
		
		//proveri dali nema input za zapisuvanje vo checklist
		List <WebElement> checklistInputElement = driver.findElements(By.cssSelector("#checklistitem-content"));
		assertTrue(checklistInputElement.size() == 0);
		
		//osiguraj se deka nema opcija za prikacuvanje dokumenti
		driver.findElement(By.xpath("//*[@id='divTicketBody']/div/ul/li[2]/a")).sendKeys(Keys.RETURN); //stisni kopce za attachment
		List <WebElement> attachmentUploadElement = driver.findElements(By.cssSelector("#attachment-well > strong"));
		assertTrue(attachmentUploadElement.size() == 0);
		System.out.println("attachmentUploadElement size:" + attachmentUploadElement.size());
		
		//osiguraj se deka nema opcija za komentiranje
		driver.findElement(By.xpath("//*[@id='divTicketBody']/div/ul/li[3]/a")).sendKeys(Keys.RETURN);
		List <WebElement> commentButtonElement = driver.findElements(By.cssSelector("#comment"));
		assertTrue(commentButtonElement.size() == 0);
		System.out.println("commentButtonElement size:" + commentButtonElement.size());
		 
		//probaj da smenis datum
		driver.findElement(By.xpath("//*[@id='ticketHeader']/div[2]/div[2]/h6/span[2]")).sendKeys(Keys.RETURN); //stisni kopce za datum edit
		List <WebElement> editDeadlineElement = driver.findElements(By.cssSelector("#ticketHeader > div.row > div:nth-child(2) > h6 > span.form-group.has-feedback > input"));
		assertTrue(editDeadlineElement.size() == 0);
		System.out.println("editDeadlineElement size:" + editDeadlineElement.size());
		 
		
		//probaj da smenis status
		driver.findElement(By.xpath("//*[@id='ticketHeader']/div[2]/div[2]/h6/span[1]")).sendKeys(Keys.RETURN); //stisni kopce za datum edit"
		List <WebElement> changeTicketStatusElement = driver.findElements(By.cssSelector("#ticketHeader > div.row > div:nth-child(2) > h6 > span.form-group.has-feedback > input"));
		assertTrue(changeTicketStatusElement.size() == 0);
		System.out.println("changeTicketStatusElement size:" + changeTicketStatusElement.size());
		
		//odi vo bordot
	    driver.get(board);
	    Thread.sleep(2000);	
	    driver.findElement(By.linkText(boardName)).click();		
  		Thread.sleep(2000);
		  
  	//write privilegii
		//odi vo priviledges
		actions = new Actions(driver);
		menu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(menu);
		subMenu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);
		
		//stavi privilegija write na tiketot
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.privilegesCircle.context-menu-two")));
		driver.findElement(By.cssSelector("div.privilegesCircle.context-menu-two")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/ul/li")));
		driver.findElement(By.xpath("//body/ul/li[3]")).click();
		Thread.sleep(2000);
		
		//odi vo bordot
	    driver.get(board);
	    Thread.sleep(2000);	
	    driver.findElement(By.linkText(boardName)).click();		
  		Thread.sleep(2000);
  		
  		//otvori tiket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tags")));
		driver.findElement(By.cssSelector(".tags")).click(); //otvori tiket
		Thread.sleep(2000);
		
		//zapisi vo checklist
		driver.findElement(By.cssSelector("ul.nav:nth-child(5) > li:nth-child(1) > a:nth-child(1)")).click();
		driver.findElement(By.id("checklistitem-content")).clear();
		driver.findElement(By.id("checklistitem-content")).sendKeys("1");
		driver.findElement(By.id("checklistitem-content")).sendKeys(Keys.RETURN);
		Thread.sleep(2000);
		
		//proveri dali mozes da prikacis file
		driver.findElement(By.linkText("Attachments")).click();
		Thread.sleep(2000);
		List <WebElement> uploadElement = driver.findElements(By.cssSelector("#attachment-well > strong"));
		assertTrue(uploadElement.size() == 1);
		System.out.println("uploadElementWrite size:" + uploadElement.size());
		
		
		//probaj da komentiras
		driver.findElement(By.xpath("//*[@id='divTicketBody']/div/ul/li[3]/a")).sendKeys(Keys.RETURN);
		driver.findElement(By.id("kanban-board-comment-field")).clear();
		driver.findElement(By.id("kanban-board-comment-field")).sendKeys("a");
		driver.findElement(By.id("comment")).click();
		Thread.sleep(2000);
		
		//proveri dali ima meni so opcii za kreiranje child ticket and stuff
		List <WebElement> ticketMenuElement = driver.findElements(By.cssSelector("i.glyphicon.glyphicon-option-horizontal"));
		System.out.println("ticketMenuElement size: " + ticketMenuElement.size());
		assertTrue(ticketMenuElement.size() == 0);
		  
  	//edit privilegii
		
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
		
		//stavi privilegija edit na tiketot
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.privilegesCircle.context-menu-two")));
		driver.findElement(By.cssSelector("div.privilegesCircle.context-menu-two")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/ul/li")));
		driver.findElement(By.xpath("//body/ul/li[4]")).click();
		Thread.sleep(2000);
		
		//odi vo bordot
	    driver.get(board);
	    Thread.sleep(2000);	
	    driver.findElement(By.linkText(boardName)).click();		
  		Thread.sleep(2000);
  		
  		//otvori tiket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tags")));
		driver.findElement(By.cssSelector(".tags")).click(); //otvori tiket
		Thread.sleep(2000);
		
		//zapisi vo checklist
		driver.findElement(By.cssSelector("ul.nav:nth-child(5) > li:nth-child(1) > a:nth-child(1)")).click();
		driver.findElement(By.id("checklistitem-content")).clear();
		driver.findElement(By.id("checklistitem-content")).sendKeys("1");
		driver.findElement(By.id("checklistitem-content")).sendKeys(Keys.RETURN);
		Thread.sleep(2000);
		
		//proveri dali mozes da prikacis file
		driver.findElement(By.linkText("Attachments")).click();
		Thread.sleep(2000);
		List <WebElement> uploadElementEdit = driver.findElements(By.cssSelector("#attachment-well > strong"));
		assertTrue(uploadElementEdit.size() == 1);
		System.out.println("uploadElementEdit size:" + uploadElementEdit.size());
		
		
		//probaj da komentiras
		driver.findElement(By.xpath("//*[@id='divTicketBody']/div/ul/li[3]/a")).sendKeys(Keys.RETURN);
		driver.findElement(By.id("kanban-board-comment-field")).clear();
		driver.findElement(By.id("kanban-board-comment-field")).sendKeys("a");
		driver.findElement(By.id("comment")).click();
		Thread.sleep(2000);	
		
		List <WebElement> ticketMenuElementEdit = driver.findElements(By.cssSelector("i.glyphicon.glyphicon-option-horizontal"));
		System.out.println("ticketMenuElementEdit size: " + ticketMenuElementEdit.size());
		assertTrue(ticketMenuElementEdit.size() == 1);
		
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
		
		//stavi privilegija edit na tiketot
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.privilegesCircle.context-menu-two")));
		driver.findElement(By.cssSelector("div.privilegesCircle.context-menu-two")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/ul/li")));
		driver.findElement(By.xpath("//body/ul/li[4]")).click();
		Thread.sleep(2000);
  		
  		
//brisenje na nov tiket i bord//////////////////////////////////////////////////////////////////		
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