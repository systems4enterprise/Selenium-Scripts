package b0ardTesting;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatingNewTicket {
	
	//private static String baseURL = "http://zabegan-001-site31.itempurl.com";
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
		driver.findElement(By.cssSelector("#navbar-mobile > ul > li:nth-child(1) > a")).click();
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
		createTicket();
		createBoard();	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		Date date = new Date();
		String date1= dateFormat.format(date);
		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".btn-green")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.form-group")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.form-group")).sendKeys("SeleniumTicketType" + date1 + (int)(Math.random()*123456));
		Thread.sleep(2000);
		driver.findElement(By.name("btn-add-newIcon")).click();
		Thread.sleep(2000);
		//driver.findElement(By.name("btn-add-newIcon")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.name("div.entry:nth-child(3) > div:nth-child(2) > i:nth-child(1)")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.name("#btnSaveChangedIcon")).click();
		//Thread.sleep(2000);
		driver.findElement(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(5) > button:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("tbody.tableWidth > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("tbody.tableWidth > tr:nth-child(1) > td:nth-child(2) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(4)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#kanbanTab > a:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.nav-item:last-child")).click(); 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".glyphicon-plus")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys("Test");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".list-item-add-save")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.btn")).click();
		Thread.sleep(2000);
		List<WebElement> listTicketTypes = new LinkedList<WebElement>();
		WebElement we = driver.findElement(By.cssSelector("#board > div:first-child > div > div:last-child"));
		listTicketTypes = we.findElements(By.cssSelector("div.dropdown-menu.open > ul.dropdown-menu.inner > li"));
		//listTicketTypes.stream().forEach(m -> System.out.println(m.findElement(By.cssSelector("a > span.text")).getText()));
		System.out.println("List ticket types size:" + listTicketTypes.size());
		for(int i = 0; i < listTicketTypes.size(); i++){
			System.out.println(listTicketTypes.get(i).findElement(By.cssSelector("a > span.text")).getText());
		}
		if(listTicketTypes.size() > 1){
			listTicketTypes.get(listTicketTypes.size() - (listTicketTypes.size() -1 )).findElement(By.cssSelector("a")).click();
			}
		driver.findElement(By.name("txtAddNewTicketTitle")).clear();
		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys("test" + (int)(Math.random()*123456));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.navbar:nth-child(2) > h3:nth-child(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.list-item-card-outer:last-child")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".spnDeadline")).click();
		Thread.sleep(4000);
		
		
		driver.findElement(By.cssSelector(".btn-green")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(".btn-green")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(".btn-green")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(".btn-green")).click();
		Thread.sleep(4000);
		driver.findElement(By.cssSelector(".btn-green")).click();
		Thread.sleep(4000);

		
		
		
		
		
		
		
	}
	
	@After
	public void dispose(){
		//driver.quit();
	}

}
