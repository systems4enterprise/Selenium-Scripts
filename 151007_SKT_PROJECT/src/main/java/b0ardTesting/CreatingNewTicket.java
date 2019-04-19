package b0ardTesting;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatingNewTicket {
	
	private static String baseURL = "test.b0ard.work";
	public static String newTicketType = "CI Test" + CreatingUser.LastName;
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
		//driver.findElement(By.cssSelector("#navbar-mobile > ul > li:nth-child(1) > a")).click();
		Thread.sleep(2000);
	}
	
	public void createBoard() throws InterruptedException {
		WebElement element  = driver.findElement(By.cssSelector("a.dropdown-toggle > span"));
		assertEquals(CreatingUser.FirstName + " " + CreatingUser.LastName, element.getText());
		
		driver.findElement(By.cssSelector("#boardsDiv > label > button")).click();
		Thread.sleep(2000);
		//vadenje na vreme od sistem
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
				Date date = new Date();
				String date1= dateFormat.format(date);

		driver.findElement(By.id("board-name")).clear();
		driver.findElement(By.id("board-name")).sendKeys("SeleniumBord" + date1 + (int)(Math.random()*123456));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#modal_default > div > div > form > div.modal-footer > button.btn.btn-sm.btn-info.btn-add-new")).click();
		Thread.sleep(2000);
		List<WebElement> list = new LinkedList<WebElement>();
		list = driver.findElements(By.cssSelector("div#mCSB_2_container > li"));
		WebElement last = list.get(list.size()-1);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", last);
		last.findElement(By.cssSelector("a:first-child")).click();
		Thread.sleep(2000);
		
	}

	@Test
	public void createTicket() throws InterruptedException{
		//createTicket();
		//createBoard();	
		WebDriverWait wait = new WebDriverWait(driver, 8000);
		//user1 kreira nov bord
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		Date date = new Date();
		String date1= dateFormat.format(date);
		
		//create new board
		String boardName = "SeleniumTicketType" + date1 + (int)(Math.random()*123456);
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
		driver.findElement(By.linkText(boardName)).click();
		Thread.sleep(2000);
				
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
//		Date date = new Date();
//		String date1= dateFormat.format(date);
//		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3)")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector(".btn-green")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("input.form-group")).clear();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("input.form-group")).sendKeys("SeleniumTicketType" + date1 + (int)(Math.random()*123456));
//		Thread.sleep(2000);
//		driver.findElement(By.name("btn-add-newIcon")).click();
//		Thread.sleep(2000);
		
		//driver.findElement(By.name("btn-add-newIcon")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.name("div.entry:nth-child(3) > div:nth-child(2) > i:nth-child(1)")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.name("#btnSaveChangedIcon")).click();
		//Thread.sleep(2000);
		
//		driver.findElement(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(5) > button:nth-child(1)")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("tbody.tableWidth > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("tbody.tableWidth > tr:nth-child(1) > td:nth-child(2) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(4)")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("#kanbanTab > a:nth-child(1)")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("li.nav-item:last-child")).click(); 
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector(".glyphicon-plus")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys("Test");
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector(".list-item-add-save")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("div.btn")).click();
//		Thread.sleep(2000);
//		List<WebElement> listTicketTypes = new LinkedList<WebElement>();
//		WebElement we = driver.findElement(By.cssSelector("#board > div:first-child > div > div:last-child"));
//		listTicketTypes = we.findElements(By.cssSelector("div.dropdown-menu.open > ul.dropdown-menu.inner > li"));
//		//listTicketTypes.stream().forEach(m -> System.out.println(m.findElement(By.cssSelector("a > span.text")).getText()));
//		System.out.println("List ticket types size:" + listTicketTypes.size());
//		for(int i = 0; i < listTicketTypes.size(); i++){
//			System.out.println(listTicketTypes.get(i).findElement(By.cssSelector("a > span.text")).getText());
//		}
//		if(listTicketTypes.size() > 1){
//			listTicketTypes.get(listTicketTypes.size() - (listTicketTypes.size() -1 )).findElement(By.cssSelector("a")).click();
//			}
//		driver.findElement(By.name("txtAddNewTicketTitle")).clear();
//		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys("test" + (int)(Math.random()*123456));
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("div.navbar:nth-child(2) > h3:nth-child(2)")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("div.list-item-card-outer:last-child")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector(".spnDeadline")).click();
//		Thread.sleep(4000);
//		
//		
//		driver.findElement(By.cssSelector(".btn-green")).click();
//		Thread.sleep(4000);
//		driver.findElement(By.cssSelector(".btn-green")).click();
//		Thread.sleep(4000);
//		driver.findElement(By.cssSelector(".btn-green")).click();
//		Thread.sleep(4000);
//		driver.findElement(By.cssSelector(".btn-green")).click();
//		Thread.sleep(4000);
//		driver.findElement(By.cssSelector(".btn-green")).click();
//		Thread.sleep(4000);

		
		//odi vo priviledges
		Actions actions = new Actions(driver);
		WebElement menu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(menu);
		WebElement subMenu = driver.findElement(By.cssSelector("#pageContentDiv > div.sidebar.sidebar-main > div > div > div > ul > li:nth-child(5) > a"));
		actions.moveToElement(subMenu);
		actions.click().build().perform();
		Thread.sleep(2000);
		
		//stavi privilegija edit na tiketot
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.privilegesCircle.context-menu-two")));
		driver.findElement(By.cssSelector("div.privilegesCircle.context-menu-two")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/ul/li")));
		driver.findElement(By.xpath("//body/ul/li[4]")).click();		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.privilegesCircle.context-menu-two")));
		driver.findElement(By.xpath("//table[@id='privilegesTable']/tbody/tr[2]/td[2]/div")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[5]/span")));
		driver.findElement(By.xpath("//li[4]/span")).click();
		Thread.sleep(2000);
		
		//nazad na noviot bord
		driver.get(baseURL);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(boardName)));
	
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
		//driver.findElement(By.xpath("(//input[@type='text'])[6]")).clear();
		//driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys("Ticket type"); //ticketType
		driver.findElement(By.cssSelector(".dropup > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1)")).click();
		Thread.sleep(4000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='text'])[6]")));		
		//driver.findElement(By.xpath("(//input[@type='text'])[6]")).sendKeys(Keys.RETURN);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("txtAddNewTicketTitle")));
		driver.findElement(By.name("txtAddNewTicketTitle")).clear();
		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys("Title");
		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys(Keys.RETURN);
		Thread.sleep(2000);
		
		//otvori tiket
		driver.findElement(By.cssSelector("div.row:nth-child(3)")).click();
		Thread.sleep(2000);
		
		//zapisi vo checklist
		driver.findElement(By.cssSelector("ul.nav:nth-child(5) > li:nth-child(1) > a:nth-child(1)")).click();
		driver.findElement(By.id("checklistitem-content")).clear();
		driver.findElement(By.id("checklistitem-content")).sendKeys("1");
		driver.findElement(By.id("checklistitem-content")).sendKeys(Keys.RETURN);
		driver.findElement(By.id("checklistitem-content")).clear();
		driver.findElement(By.id("checklistitem-content")).sendKeys("2");
		driver.findElement(By.id("checklistitem-content")).sendKeys(Keys.RETURN);
		Thread.sleep(2000);
		
		//proveri dali koga ke preckrtas nesto od listata se popolnuva progress bar
		driver.findElement(By.cssSelector("li.list-group-item:nth-child(1) > label:nth-child(1) > input:nth-child(1)")).click();//preckrtaj
		Thread.sleep(2000);
		String progress = driver.findElement(By.cssSelector(".progress-bar")).getAttribute("style").toString();
		System.out.println("Progress bar: " + progress);
		assertTrue(progress.contains("width: 50%"));
		driver.findElement(By.cssSelector("li.list-group-item:nth-child(1) > label:nth-child(1) > input:nth-child(1)")).click();//otckrtaj
		progress = driver.findElement(By.cssSelector(".progress-bar")).getAttribute("style").toString();
		System.out.println("Progress bar: " + progress);
		assertTrue(progress.contains("width: 0%"));
		
		//proveri dali mozes da prikacis file
		driver.findElement(By.linkText("Attachments")).click();
		Thread.sleep(2000);
		List <WebElement> uploadElement = driver.findElements(By.cssSelector("#attachment-well > strong"));
		assertTrue(uploadElement.size() == 1);
		System.out.println("uploadElementWrite size:" + uploadElement.size());
	
		//tagni user vo komentar
		driver.findElement(By.xpath("//*[@id='divTicketBody']/div/ul/li[3]/a")).sendKeys(Keys.RETURN);
		driver.findElement(By.id("kanban-board-comment-field")).clear();
		driver.findElement(By.id("kanban-board-comment-field")).sendKeys("@");		
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#at-view-64 > ul:nth-child(2) > li:nth-child(1)")).click();
		driver.findElement(By.id("comment")).click();
		Thread.sleep(2000);
		
		//tagni tiket
		driver.findElement(By.id("kanban-board-comment-field")).clear();
		driver.findElement(By.id("kanban-board-comment-field")).sendKeys("#");
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#at-view-35 > ul > li:nth-child(1)")).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("comment")));		
		driver.findElement(By.id("comment")).click();

		
		//zadavanje deadline na ticket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.spnDeadline")));
		Thread.sleep(2000);
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
		
		//assign user
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#assignNewUser")));
		driver.findElement(By.cssSelector("#assignNewUser")).click();			
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#assignNewUser > span:nth-child(2) > div:nth-child(1) > button:nth-child(1)")));
		driver.findElement(By.cssSelector("#assignNewUser > span:nth-child(2) > div:nth-child(1) > button:nth-child(1)")).click(); //assign user
		Thread.sleep(2000);
		
		//assign watcher
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")));
		driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")).click(); //click +
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#assignNewWatcher > span:nth-child(1)")));
		driver.findElement(By.cssSelector("#assignNewWatcher > span:nth-child(1)")).click(); //click the button
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#assignNewWatcher > span:nth-child(2) > div:nth-child(1) > button:nth-child(1)")));
		driver.findElement(By.cssSelector("#assignNewWatcher > span:nth-child(2) > div:nth-child(1) > button:nth-child(1)")).click(); //click the button for assigning
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")));
		driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")).click(); //choose user
		driver.findElement(By.cssSelector("#assignNewUser")).click(); //click assign user again, to save the change of assigning the watcher
		Thread.sleep(2000);
				
		//close the ticket
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#dropdownEditCardMenu")));
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click(); //dropdown for closing the ticket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")));
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click(); //choose user
		
		//logout
		driver.get(baseURL);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("b.caret")));
		driver.findElement(By.cssSelector("b.caret")).click();
		driver.findElement(By.linkText("LogOut")).click();
		driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
		
		System.out.println("Over");
		
	}
	
	@After
	public void dispose(){
		//driver.quit();
	}

}
