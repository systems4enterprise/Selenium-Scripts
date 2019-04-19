package b0ardTesting;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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

import b0ardTesting.CreatingUser;


public class TicketTesting {
	WebDriver driver;
	@Before
	public void setUp() throws InterruptedException {
		driver = new FirefoxDriver();
		
	}
		public void logIn() throws InterruptedException {
			driver.get(LoggingIn.baseURL);
			CreatingUser.user_extern = "selenium";
			CreatingUser.password = "123456";
			CreatingUser.FirstName = "Aleksandar";
			CreatingUser.LastName = "Gjorgievski";
			
			driver.findElement(By.id("Username")).clear();
			driver.findElement(By.id("Username")).sendKeys(CreatingUser.user_extern);
			driver.findElement(By.id("Password")).clear();
			driver.findElement(By.id("Password")).sendKeys(CreatingUser.password);
			driver.findElement(By.name("button")).click();
			Thread.sleep(3000);
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
	
	public void createTicket() throws InterruptedException {
		WebElement element  = driver.findElement(By.cssSelector("a.dropdown-toggle > span"));
		assertEquals(CreatingUser.FirstName + " " + CreatingUser.LastName, element.getText());
		
		driver.findElement(By.cssSelector(".icon-plus3")).click();
		Thread.sleep(3000);
		//vadenje na vreme od sistem
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
				Date date = new Date();
				String date1= dateFormat.format(date);
		//System.out.println(date1);
		driver.findElement(By.id("board-name")).clear();
		driver.findElement(By.id("board-name")).sendKeys("SeleniumBord" + date1 + (int)(Math.random()*12345));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#modal-add-board > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(3) > button:nth-child(2)")).click();
		Thread.sleep(2000);
		List<WebElement> list = new LinkedList<WebElement>();
		list = driver.findElements(By.cssSelector("div#mCSB_2_container > li"));
		
		WebElement last = list.get(list.size()-1);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", last);
		last.findElement(By.cssSelector("a:first-child")).click();
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#mCSB_2_container > li:last-child")).click();
		driver.findElement(By.cssSelector("#addPoolListButtonContainer > span > span")).click();
		Thread.sleep(2000);
		String browserUrl = driver.getCurrentUrl();
		driver.findElement(By.cssSelector("#txtNewBoardListName")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys("SeleniumTest");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".list-item-add-save")).click();
		Thread.sleep(2000);
		driver.get(browserUrl);
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#txtNewBoardListName")).click();
		//driver.findElement(By.cssSelector("#new-list-form > div > input")).click();
		driver.findElement(By.cssSelector("div.btn-add-ticket > span:nth-child(1)")).click();
		Thread.sleep(2000);
		
		List<WebElement> listTicketTypes = new LinkedList<WebElement>();
		WebElement we = driver.findElement(By.cssSelector("#board > div:first-child > div > div:last-child"));
		listTicketTypes = we.findElements(By.cssSelector("div.dropdown-menu.open > ul.dropdown-menu.inner > li"));
		for(int i = 0; i < listTicketTypes.size(); i++){
			//System.out.println(listTicketTypes.get(i).findElement(By.cssSelector("a > span.text")).getText());
		}
		if(listTicketTypes.size() > 1){
			listTicketTypes.get(listTicketTypes.size() - 1).findElement(By.cssSelector("a")).click();
			}
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.form-group")).click();
		Thread.sleep(3000);
		String ticketName = "Test" + (int)(Math.random()*12345);
		driver.findElement(By.cssSelector("input.form-group")).sendKeys(ticketName);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		
		
	}
	@Test
	public void test() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 8000);

		logIn();
		createTicket();
		//createBoard();	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		Date date = new Date();
		String date1= dateFormat.format(date);
		Thread.sleep(8000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.list-item-card-outer:last-child")));
		driver.findElement(By.cssSelector("div.list-item-card-outer:last-child")).click();
		Thread.sleep(2000);
		
		//get ticket id
		String ticketInfoArray = driver.findElement(By.cssSelector("div.row:nth-child(5) > div:nth-child(1) > div:nth-child(1)")).getText();
		System.out.println("ticketInfoArray" + ticketInfoArray);
		String [] splitted = ticketInfoArray.split(" ");
		for(int i=0; i<splitted.length; i++) System.out.println(splitted[i]);
		String ticketId=splitted[splitted.length - 1];
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
		
		//save the ticket title
		String ticketName = driver.findElement(By.cssSelector("#hCardTitle")).getText();
		
		//assign deadline
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.spnDeadline")));
		driver.findElement(By.cssSelector("span.spnDeadline")).click();
		driver.findElement(By.name("deadlineDate")).clear();
			//denesen datum
		java.util.Date today = Calendar.getInstance().getTime();		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");		
		String deadline = format.format(today);
		String deadline2 = format2.format(today);
		System.out.println(deadline2);
		
		driver.findElement(By.name("deadlineDate")).sendKeys(deadline2);
		//driver.findElement(By.cssSelector("div.ticketDetails-main > div.row.tags")).click();
		Thread.sleep(2000);
		
		//add a tag
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".ticketDetails-main > div:nth-child(2) > span:nth-child(2)")));
		driver.findElement(By.cssSelector(".ticketDetails-main > div:nth-child(2) > span:nth-child(2)")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")));
		driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(2000);
				
		//close the ticket
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#dropdownEditCardMenu")));
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click(); //dropdown for closing the ticket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")));
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click(); //choose user
		
		//go to calendar view
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btnCalendarView")));
		driver.findElement(By.cssSelector("#btnCalendarView")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".fc-basicWeek-button")).click(); //week
		Thread.sleep(2000);
		
		//get the row of the table head
		WebElement row = driver.findElement(By.cssSelector("div.fc-widget-header > table:nth-child(1) > thead:nth-child(1) > tr:nth-child(1)"));
		List <WebElement> thElements = row.findElements(By.tagName("th"));
		int index = -1;
		for(int i=0; i<thElements.size(); i++){
			WebElement element = thElements.get(i);
			
			if(element.getAttribute("data-date").equals(deadline)){
				index=i;
			}	
		}		
		assertTrue(index>-1);
		index++;
		
		//najdi ja soodvetnata td na najdeniot th
		String selector = ".fc-content-skeleton > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(" + index + ")";
		WebElement calendarSlot = driver.findElement(By.cssSelector(selector));
		Thread.sleep(4000);
		calendarSlot.findElement(By.tagName("a")).click();
		Thread.sleep(5000);
		assertTrue(driver.findElement(By.cssSelector("#hCardTitle")).getText().equals(ticketName));
			
		//close the ticket
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#dropdownEditCardMenu")));
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click(); //dropdown for closing the ticket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")));
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click(); //choose user
		Thread.sleep(2000);
		
		
		//back to the board
		driver.findElement(By.cssSelector("#listBreadcrumbs > li:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		
		//otvori tiket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tags")));
		driver.findElement(By.cssSelector(".tags")).click(); //otvori tiket
		Thread.sleep(2000);
		
		//trgni datum
		driver.findElement(By.cssSelector(".spnDeadline")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.datepicker")).clear();
		driver.findElement(By.cssSelector(".datepicker-days > table:nth-child(1) > tfoot:nth-child(3) > tr:nth-child(2) > th:nth-child(1)")).click();
		Thread.sleep(2000);
		
		//close the ticket
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#dropdownEditCardMenu")));
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click(); //dropdown for closing the ticket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")));
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click(); //choose user
		Thread.sleep(2000);
		
		//go to calendar view
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#btnCalendarView")));
		driver.findElement(By.cssSelector("#btnCalendarView")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".fc-basicWeek-button")).click(); //week
		Thread.sleep(2000);
		
		//get the row of the table head
		row = driver.findElement(By.cssSelector("div.fc-widget-header > table:nth-child(1) > thead:nth-child(1) > tr:nth-child(1)"));
		thElements = row.findElements(By.tagName("th"));
		 index = -1;
		for(int i=0; i<thElements.size(); i++){
			WebElement element = thElements.get(i);
		
			if(element.getAttribute("data-date").equals(deadline)){
				index=i;
			}	
		}
		index++;
		
		//najdi ja soodvetnata td na najdeniot th
		selector = ".fc-content-skeleton > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(" + index + ")";
		calendarSlot = driver.findElement(By.cssSelector(selector));
		List <WebElement> link = calendarSlot.findElements(By.tagName("a"));
		Thread.sleep(5000);
		assertTrue(link.size()==0); //nema tiket
		
		//back to the board
		driver.findElement(By.cssSelector("#listBreadcrumbs > li:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		
		//add a new column
		driver.findElement(By.cssSelector(".list-item-add-placeholder")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys("New column");
		driver.findElement(By.cssSelector(".list-item-add-save")).click();
		Thread.sleep(2000);
		
		//get column id to use it to determine if the ticket is moved in that column later
//		WebElement secondColumn = driver.findElement(By.xpath("//div[@id='board']/div[2]"));

		//otvori tiket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".tags")));
		driver.findElement(By.cssSelector(".tags")).click(); //otvori tiket
		Thread.sleep(2000);
		
		//zapisi vo checklist
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector("ul.nav:nth-child(5) > li:nth-child(1) > a:nth-child(1)")).click();
		driver.findElement(By.id("checklistitem-content")).clear();
		driver.findElement(By.id("checklistitem-content")).sendKeys("1");
		driver.findElement(By.id("checklistitem-content")).sendKeys(Keys.RETURN);
		driver.findElement(By.id("checklistitem-content")).clear();
		driver.findElement(By.id("checklistitem-content")).sendKeys("2");
		driver.findElement(By.id("checklistitem-content")).sendKeys(Keys.RETURN);
		Thread.sleep(2000);
		
		//comment
		driver.findElement(By.xpath("//*[@id='divTicketBody']/div/ul/li[3]/a")).sendKeys(Keys.RETURN);
		driver.findElement(By.id("kanban-board-comment-field")).clear();
		driver.findElement(By.id("kanban-board-comment-field")).sendKeys("Comment");
		Thread.sleep(2000);
		driver.findElement(By.id("comment")).click();
		Thread.sleep(2000);
		
		//change status
		driver.findElement(By.cssSelector("span.spnCardStatus")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@id='ticketHeader']/div[2]/div[2]/h6/span[2]/div/div/ul/li[2]/a/span")).click();
		new Select(driver.findElement(By.id("Status"))).selectByVisibleText("New column");
		
		//close the ticket
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#dropdownEditCardMenu")));
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click(); //dropdown for closing the ticket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")));
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click(); //choose user
		Thread.sleep(2000);
		
		//proveri dali tiketot e premesten vo nova kolona	
		WebElement secondColumn1 = driver.findElement(By.xpath("//*[@id='board']/div[2]"));
		System.out.println(secondColumn1.getAttribute("id"));
		WebElement ticket = secondColumn1.findElement(By.xpath("./div/div[2]/div/div[1]/div/div/div[2]/span"));
		assertTrue(ticket.getText()!= null);
		System.out.println(ticket.getText());
						
		//open ticket
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("div.row.tags")).click();
		Thread.sleep(4000);
		
		//child ticket	
		driver.findElement(By.cssSelector("i.glyphicon.glyphicon-option-horizontal")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Add child ticket")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@id='listEditCard']/li[3]/span/div/div/ul/li[2]/a/span")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.tree-wrapper:nth-child(1)")).click();
		Thread.sleep(4000);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.txtComponent")).clear();
		driver.findElement(By.cssSelector("input.txtComponent")).sendKeys("2");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("textarea.txtComponent")).clear();
		driver.findElement(By.cssSelector("textarea.txtComponent")).sendKeys("2");
		Thread.sleep(2000);
		
		//list items
		driver.findElement(By.id("checklistitem-content")).clear();
		driver.findElement(By.id("checklistitem-content")).sendKeys("1");
		driver.findElement(By.id("checklistitem-content")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.id("checklistitem-content")).clear();
		driver.findElement(By.id("checklistitem-content")).sendKeys("2");
		driver.findElement(By.id("checklistitem-content")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);		

		//comments
		driver.findElement(By.linkText("Comments")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("kanban-board-comment-field")).clear();
		driver.findElement(By.id("kanban-board-comment-field")).sendKeys("aaa");
		Thread.sleep(2000);
		driver.findElement(By.id("comment")).click();
		Thread.sleep(2000);
		
		//assign user
		driver.findElement(By.cssSelector("#assignNewUser > span.list-item-add-placeholder")).click();
		driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		
//		//assign watcher
//		driver.findElement(By.cssSelector("#assignNewWatcher > span:nth-child(1)")).click();
//		Thread.sleep(4000);
//		driver.findElement(By.cssSelector("div.open:nth-child(1) > button:nth-child(1)")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")).click();
//		Thread.sleep(2000);
		
		//close the ticket
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#dropdownEditCardMenu")));
		
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click(); //dropdown for closing the ticket
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")));
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click(); //choose user
		Thread.sleep(2000);
		
		//open ticket
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.row.tags")).click();
		Thread.sleep(4000);
		
		//select child ticket
		driver.findElement(By.cssSelector("div.tree-wrapper:nth-child(1)")).click();
		Thread.sleep(2000);
		
		//delete the child ticket
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#dropdownEditCardMenu")));
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click(); //dropdown for closing the ticket
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(6) > a:nth-child(1)")).click(); //choose user
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#deleteTicketModal > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(3) > button:nth-child(2)")).click();
		Thread.sleep(2000);
				
		System.out.println("Over");
		
		/* Ace
		driver.findElement(By.cssSelector("#spnShowUsers")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#assignNewUser > span:nth-child(2) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		//(tuka treba so assert i da se narpav sporedba so site koi gi ima vo menito i da se odbere toa sto e isto)
		driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(6) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#spnShowWatchers")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#assignNewWatcher > span:nth-child(2) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		//(tuka treba so assert i da se narpav sporedba so site koi gi ima vo menito i da se odbere toa sto e isto)
		driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(6)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".ticketDetails-main > div:nth-child(2) > span:nth-child(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(1) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("ul.nav:nth-child(5) > li:nth-child(3)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#kanban-board-comment-field")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#kanban-board-comment-field")).sendKeys("Komentar Komentar Komentar Komentar Komentar Komentar Komentar ");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#comment")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click();
		String browserUrl = driver.getCurrentUrl();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".add-board-list-span")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys("SeleniumTest1");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".list-item-add-save")).click();
		Thread.sleep(2000);
		driver.get(browserUrl);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(1) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".spnCardStatus")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.row.tags")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(3)")).click();
		Thread.sleep(2000);//(namesto dole ova, ke treba da se napravi lista i da se odbira random)
		//driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1)")).click();
		//Thread.sleep(2000);
		driver.get(browserUrl);
		driver.findElement(By.cssSelector("div.row.tags")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.tree-wrapper:nth-child(1) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("ul.nav:nth-child(5) > li:nth-child(3)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#kanban-board-comment-field")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#kanban-board-comment-field")).sendKeys("Komentar Komentar Komentar Komentar Komentar Komentar Komentar ");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#comment")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.row.tags")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.tree-wrapper:nth-child(1) > div:nth-child(1) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(6)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#deleteTicketModal > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(3) > button:nth-child(2)")).click();
		Thread.sleep(2000);
		//brisenje
		driver.findElement(By.cssSelector("#kanbanTab")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#kanbanTab > a > span")).click();
		Thread.sleep(5000);
		
		List<WebElement> listaa = new LinkedList<WebElement>();
		listaa = driver.findElements(By.cssSelector("div#mCSB_2_container > li"));
		//System.out.println(listaa.size());
		for(int i = 0; i < listaa.size(); i++){
			//System.out.println(lista.get(i));
		}
		WebElement lastaa = listaa.get(listaa.size()-1);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", lastaa);
		driver.findElement(By.cssSelector("#mCSB_2_container > li:last-child > div")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[2]/span")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(".btn-success")).click();
		Thread.sleep(5000);
		
		*/
		
		
	}

}
