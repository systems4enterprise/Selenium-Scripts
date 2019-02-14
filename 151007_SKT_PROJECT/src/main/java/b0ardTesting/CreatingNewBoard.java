package b0ardTesting;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;
public class CreatingNewBoard {
	
	//private static String baseURL = "http://zabegan-001-site31.itempurl.com";
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
		Thread.sleep(5000);
	}

	@Test
	public void createBoard() throws InterruptedException {
		
		logIn();
		WebElement element  = driver.findElement(By.cssSelector("a.dropdown-toggle > span"));
		assertEquals(CreatingUser.FirstName + " " + CreatingUser.LastName, element.getText());
		
		driver.findElement(By.cssSelector(".icon-plus3")).click();
		Thread.sleep(5000);
		
		driver.findElement(By.cssSelector("#board-name")).clear();
		driver.findElement(By.cssSelector("#board-name")).sendKeys(CreatingUser.LastName); 
		driver.findElement(By.cssSelector("#modal-add-board > div:nth-child(1) > div:nth-child(1) > form:nth-child(1) > div:nth-child(3) > button:nth-child(2)")).click();
		Thread.sleep(5000);
		
		
		List<WebElement> list = new LinkedList<WebElement>();
		list = driver.findElements(By.cssSelector("div#mCSB_2_container > li"));
		//System.out.println(list.size());
		for(int i = 0; i < list.size(); i++){
			//System.out.println(list.get(i));
		}
		WebElement last = list.get(list.size()-1);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", last);
		last.findElement(By.cssSelector("a:first-child")).click();
		
		//element = driver.findElement(By.cssSelector("#listBoards > li:last-child > a"));
		//assertEquals(CreatingUser.LastName, element.getText());
		//System.out.println(CreatingUser.user_extern + " has created a new board named: " + CreatingUser.LastName);
		
		
		//driver.findElement(By.linkText(CreatingUser.LastName)).click();
		//element = driver.findElement (By.cssSelector("div.navbar:nth-child(2) > h3:nth-child(2)"));
		//assertEquals(CreatingUser.LastName, element.getText());
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#kanbanTab")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#kanbanTab > a > span")).click();
		Thread.sleep(5000);
		List<WebElement> lista = new LinkedList<WebElement>();
		lista = driver.findElements(By.cssSelector("div#mCSB_2_container > li"));
		//System.out.println(lista.size());
		for(int i = 0; i < lista.size(); i++){
			//System.out.println(lista.get(i));
		}
		WebElement lasta = lista.get(lista.size()-1);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", lasta);
		//lasta.findElement(By.cssSelector("a:first-child")).click();
		driver.findElement(By.cssSelector("#mCSB_2_container > li:last-child > div")).click();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("body > ul > li:nth-child(1)")).click(); 
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#board-edit-name")).clear();
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#board-edit-name")).sendKeys(CreatingUser.FirstName + " " + CreatingUser.LastName);
		Thread.sleep(5000);
		System.out.println(CreatingUser.user_extern + " has created a new board named: " + CreatingUser.FirstName + " " + CreatingUser.LastName);
		//assertEquals(CreatingUser.LastName, element.getText());
		driver.findElement(By.cssSelector("#modal-edit-board > div > div > form > div.modal-footer > button.btn.btn-sm.btn-info.btn-add-new")).click();
		Thread.sleep(5000); 
		
	
		driver.findElement(By.cssSelector("#mCSB_2_container > li:last-child > a")).click();
		Thread.sleep(5000);
		//driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
		String browserUrl = driver.getCurrentUrl();
		System.out.println("Your browser URL is " + browserUrl);
		Thread.sleep(5000);
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
		
		//brise tabla
		driver.findElement(By.cssSelector("#mCSB_2_container > li:last-child > div")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//li[2]/span")).click();
		Thread.sleep(5000);	
		driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
		driver.get(browserUrl);
		Thread.sleep(5000);	
		System.out.println("B0ard is deleted");
		
	}
	
	@After
	public void dispose(){
		driver.quit();
	}

}
