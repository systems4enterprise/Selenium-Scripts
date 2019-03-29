package b0ardTesting;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TicketTupesTesting {
	
WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new FirefoxDriver();
	}
	
	public void logIn() throws InterruptedException {
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
		Thread.sleep(3000);
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
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#modal-add-board > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(3) > button:nth-child(2)")).click();
		Thread.sleep(4000);
		List<WebElement> list = new LinkedList<WebElement>();
		list = driver.findElements(By.cssSelector("div#mCSB_2_container > li"));
		//System.out.println(list.size());
		//for(int i = 0; i < list.size(); i++){
			//System.out.println(list.get(i));.list-item-add-placeholder
		//}
		WebElement last = list.get(list.size()-1);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", last);
		last.findElement(By.cssSelector("a:first-child")).click();
		Thread.sleep(4000);
		//driver.findElement(By.cssSelector("#mCSB_2_container > li:last-child")).click();
		driver.findElement(By.cssSelector("#addPoolListButtonContainer > span > span")).click();
		Thread.sleep(4000);
		String browserUrl = driver.getCurrentUrl();
		driver.findElement(By.cssSelector("#txtNewBoardListName")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys("SeleniumTest");
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(".list-item-add-save")).click();
		Thread.sleep(4000);
		driver.get(browserUrl);
		Thread.sleep(4000);
		//driver.findElement(By.cssSelector("#txtNewBoardListName")).click();
		//driver.findElement(By.cssSelector("#new-list-form > div > input")).click();
		driver.findElement(By.cssSelector("div.btn-add-ticket > span:nth-child(1)")).click();
		Thread.sleep(4000);
		
		List<WebElement> listTicketTypes = new LinkedList<WebElement>();
		WebElement we = driver.findElement(By.cssSelector("#board > div:first-child > div > div:last-child"));
		listTicketTypes = we.findElements(By.cssSelector("div.dropdown-menu.open > ul.dropdown-menu.inner > li"));
		//listTicketTypes.stream().forEach(m -> System.out.println(m.findElement(By.cssSelector("a > span.text")).getText()));
		//System.out.println("List ticket types size:" + listTicketTypes.size());
		for(int i = 0; i < listTicketTypes.size(); i++){
			//System.out.println(listTicketTypes.get(i).findElement(By.cssSelector("a > span.text")).getText());
		}
		if(listTicketTypes.size() > 1){
			listTicketTypes.get(listTicketTypes.size() - 1).findElement(By.cssSelector("a")).click();
			}
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input.form-group")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input.form-group")).sendKeys("Test" + (int)(Math.random()*12345));
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("h3")).click();
		
	}
	
	@Test
	public void test() throws InterruptedException {
		logIn();
		WebDriverWait wait = new WebDriverWait(driver, 8000);

		createTicket();	
		//vadenje na vreme od sistem
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		Date date = new Date();
		String date1= dateFormat.format(date);
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.nav-item-submenu:nth-child(3)")));
		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3)")).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='pageContentDiv']/div/div/div/div/ul/li[3]/ul/li[3]/a/span")));
		Thread.sleep(8000);
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='pageContentDiv']/div/div/div/div/ul/li[3]/ul/li[1]/a/span")));

		driver.findElement(By.xpath("//div[@id='pageContentDiv']/div/div/div/div/ul/li[3]/ul/li[1]/a/span")).click();
		
		driver.findElement(By.cssSelector("button.btn.btn-green")).sendKeys(Keys.RETURN);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.form-group")));
		driver.findElement(By.cssSelector("input.form-group")).clear();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input.form-group")).sendKeys("TicketTypes" + date1 + (int)(Math.random()*12345));
		Thread.sleep(4000);
		driver.findElement(By.name("btn-add-newIcon")).click();
		Thread.sleep(4000);
		driver.findElement(By.name("btn-add-newIcon")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.entry:nth-child(3)")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("btnSaveChangedIcon")).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(5)")));
		driver.findElement(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(5)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("tbody.tableWidth > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
		Thread.sleep(4000);
		String ticketTypeNameString = driver.findElement(By.cssSelector("thead.tableWidth > tr:nth-child(1) > th:nth-child(2)")).getText();
		System.out.println("ticketTypeNameString: " + ticketTypeNameString);
		driver.findElement(By.cssSelector("li.context-menu-item:nth-child(4) > span:nth-child(1)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#kanbanTab")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li.nav-item:last-child")));

		driver.findElement(By.cssSelector("li.nav-item:last-child")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.btn > span:nth-child(1)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(".dropup > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input.form-group")).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.form-group")));
		driver.findElement(By.cssSelector("input.form-group")).sendKeys("Test" + (int)(Math.random()*12345));
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(3000);
		//driver.findElement(By.cssSelector("div.list-item-card-outer:last-child")).click();
		//Thread.sleep(3000);
		//driver.findElement(By.cssSelector(".spnDeadline")).click();
		//Thread.sleep(3000);
		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3)")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@id='pageContentDiv']/div/div/div/div/ul/li[3]/ul/li[1]/a/span")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(8) > button:nth-child(1)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("button.btn-sm:nth-child(2)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#kanbanTab")).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.nav-item:last-child")));

		driver.findElement(By.cssSelector("li.nav-item:last-child")).click();
		Thread.sleep(4000);
		String browserUrl = driver.getCurrentUrl();
		Thread.sleep(4000);
		//driver.findElement(By.cssSelector("div.list-item-card-outer:last-child")).click();
		//Thread.sleep(4000);
		//11tocka
		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3)")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@id='pageContentDiv']/div/div/div/div/ul/li[3]/ul/li[1]/a/span")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(".btn-default")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(8) > button:nth-child(1)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("button.btn:nth-child(2)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(".btn-default")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(4) > button:nth-child(1)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.checkbox:nth-child(1) > label:nth-child(1) > input:nth-child(1)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input.form-control:nth-child(3)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input.form-control:nth-child(3)")).sendKeys("Text input");
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#btnSaveCompType")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.checkbox:nth-child(2) > label:nth-child(1) > input:nth-child(1)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.modal-body:nth-child(1) > div:nth-child(2) > input:nth-child(3)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.modal-body:nth-child(1) > div:nth-child(2) > input:nth-child(3)")).sendKeys("Numeric input");
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#btnSaveCompType")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.checkbox:nth-child(3) > label:nth-child(1) > input:nth-child(1)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.modal-body:nth-child(1) > div:nth-child(2) > input:nth-child(3)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.modal-body:nth-child(1) > div:nth-child(2) > input:nth-child(3)")).sendKeys("textarea");
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#btnSaveCompType")).click();
		Thread.sleep(4000);
		//driver.findElement(By.cssSelector("")).click();
		//Thread.sleep(4000);
		//driver.findElement(By.cssSelector("")).click();
		//15
		driver.get(browserUrl);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.btn > span:nth-child(1)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(".dropup > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2)")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input.form-group")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input.form-group")).sendKeys("Test" + (int)(Math.random()*12345));
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.list-item-card-outer:last-child")).click();
		Thread.sleep(6000);
		
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > textarea:nth-child(2)")).sendKeys(Keys.RETURN);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > textarea:nth-child(2)")).sendKeys("Tekst1");
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(3) > div:nth-child(1) > div:nth-child(2) > textarea:nth-child(2)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(3) > div:nth-child(1) > div:nth-child(2) > textarea:nth-child(2)")).click();
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(3) > div:nth-child(1) > div:nth-child(2) > textarea:nth-child(2)")).sendKeys("Tekst2");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("ul.nav:nth-child(5) > li:nth-child(2) > a:nth-child(1)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3)")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@id='pageContentDiv']/div/div/div/div/ul/li[3]/ul/li[1]/a/span")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(4) > button:nth-child(1)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.form-group:nth-child(1) > div:nth-child(2) > div:nth-child(2) > a:nth-child(2)")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#btnDeleteCompType")).click();
		Thread.sleep(3000);
		driver.get(browserUrl);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(3)")).click();
		Thread.sleep(4000);
		List <WebElement> deletedTicketFieldElementsList = driver.findElements(By.xpath("//span[contains(text(),'Tekst1')]"));
		System.out.println("deletedTicketFieldElementsList size: " + deletedTicketFieldElementsList.size());
		assertTrue(deletedTicketFieldElementsList.size() == 1);
		
		//edit Text input 1
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > textarea:nth-child(2)")));
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > textarea:nth-child(2)")).clear();
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > textarea:nth-child(2)")).sendKeys("Edited text 1");
		Thread.sleep(2000);
		
		//edit ticket title
		driver.findElement(By.cssSelector("#hCardTitle")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.row:nth-child(5) > div:nth-child(2) > input:nth-child(2)")));
		driver.findElement(By.cssSelector("div.row:nth-child(5) > div:nth-child(2) > input:nth-child(2)")).clear();
		driver.findElement(By.cssSelector("div.row:nth-child(5) > div:nth-child(2) > input:nth-child(2)")).sendKeys("Edited title");
		driver.findElement(By.cssSelector("div.row:nth-child(5) > div:nth-child(2) > input:nth-child(2)")).sendKeys(Keys.RETURN);
		Thread.sleep(4000);
		
		//edit numeric input
		driver.findElement(By.cssSelector("input.txtComponent")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.txtComponent")));
		driver.findElement(By.cssSelector("input.txtComponent")).clear();
		driver.findElement(By.cssSelector("input.txtComponent")).sendKeys("23");
		Thread.sleep(2000);	
		driver.findElement(By.cssSelector("#hCardTitle")).click();
		
		
		//refresh the page
		driver.navigate().refresh();
		
		//open the ticket
		driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.row:nth-child(5) > div:nth-child(2) > input:nth-child(2)")));

		//assert that the ticket was edited
		String ticketTitle = driver.findElement(By.cssSelector("#hCardTitle")).getText();
		System.out.println("ticketTitle: " + ticketTitle);
		assertTrue(ticketTitle.equals("Edited title"));
		String textInput = driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)")).getText();
		System.out.println("textInput: " + textInput);
		assertTrue(textInput.equals("Edited text 1"));
		String numericInput = driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)")).getText();
		System.out.println("numericInput: " + numericInput);
		assertTrue(numericInput.equals("23"));
		
		//refresh the page
		driver.navigate().refresh();
		
		//create ticket
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.btn")));

		driver.findElement(By.cssSelector("div.btn")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".dropup > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1)")));
		driver.findElement(By.cssSelector(".dropup > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(1)")).click();
				
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input.form-group")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input.form-group")).sendKeys("Test" + (int)(Math.random()*12345));
		Thread.sleep(4000);
		
		//assert that the deleted element is not visible
		List <WebElement> numericInputElement = driver.findElements(By.cssSelector("li.kanban-board-list-group-item:nth-child(2) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)"));
		System.out.println("numericInputElement size: " + numericInputElement.size());
		
		
		
	}

}
