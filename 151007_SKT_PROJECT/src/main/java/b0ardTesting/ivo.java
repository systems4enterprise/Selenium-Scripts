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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;

public class ivo {
WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new FirefoxDriver();
	}
	
	public void logIn() throws InterruptedException {
		driver.get(LoggingIn.baseURL);
		CreatingUser.user_extern = "ane123";
		CreatingUser.password = "123456";
		CreatingUser.FirstName = "Ane";
		CreatingUser.LastName = "Kuzmanovska";
		
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
		
		//driver.findElement(By.cssSelector("#boardsDiv > label > button")).click();
		//Thread.sleep(3000);
		//vadenje na vreme od sistem
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
				Date date = new Date();
				String date1= dateFormat.format(date);
		//System.out.println(date1);
		//driver.findElement(By.id("board-name")).clear();
		//driver.findElement(By.id("board-name")).sendKeys("SeleniumBord" + date1 + (int)(Math.random()*12345));
		//Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#modal_default > div > div > form > div.modal-footer > button.btn.btn-sm.btn-info.btn-add-new")).click();
		//Thread.sleep(2000);
		List<WebElement> list = new LinkedList<WebElement>();
		list = driver.findElements(By.cssSelector("div#mCSB_2_container > li"));
		//System.out.println(list.size());
		//for(int i = 0; i < list.size(); i++){
			//System.out.println(list.get(i));
		//}
		WebElement last = list.get(list.size()-1);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", last);
		last.findElement(By.cssSelector("a:first-child")).click();
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#mCSB_2_container > li:last-child")).click();
		//driver.findElement(By.cssSelector("#addPoolListButtonContainer > span > span")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#txtNewBoardListName")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys("SeleniumTest");
		//Thread.sleep(2000);
		
		//driver.findElement(By.cssSelector("#new-list-form > div > input")).click();
		//Thread.sleep(2000);
		int counter = 258;
		while(counter>0)
		{
		//driver.findElement(By.cssSelector("#txtNewBoardListName")).click();
		//driver.findElement(By.cssSelector("#new-list-form > div > input")).click();
		driver.findElement(By.cssSelector("div.btn-add-ticket > span:nth-child(1)")).click();
		Thread.sleep(2000);
		
		List<WebElement> listTicketTypes = new LinkedList<WebElement>();
		WebElement we = driver.findElement(By.cssSelector("#board > div:first-child > div > div:last-child"));
		listTicketTypes = we.findElements(By.cssSelector("div.dropdown-menu.open > ul.dropdown-menu.inner > li"));
		//listTicketTypes.stream().forEach(m -> System.out.println(m.findElement(By.cssSelector("a > span.text")).getText()));
		System.out.println("List ticket types size:" + listTicketTypes.size());
		for(int i = 0; i < listTicketTypes.size(); i++){
			//System.out.println(listTicketTypes.get(i).findElement(By.cssSelector("a > span.text")).getText());
		}
		if(listTicketTypes.size() > 1){
			listTicketTypes.get(listTicketTypes.size() - 1).findElement(By.cssSelector("a")).click();
			}
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.form-group")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.form-group")).sendKeys("Test" + (int)(Math.random()*12345));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		System.out.println("Counter: " + counter + ".....................");
		counter--;}
	}
	
	@Test
	public void ivo() throws InterruptedException{
		logIn();
		createTicket();	
		
	}}