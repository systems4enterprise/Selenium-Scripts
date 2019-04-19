package b0ardTesting;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

//import com.sun.org.apache.bcel.internal.generic.Select;

public class B0ardZero {
	WebDriver driver;
	public static String baseURL = "http://test.b0ard.work";
	@Before
	public void setUp() throws InterruptedException {
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
	
	@Test
	public void test() throws InterruptedException{
		logIn();
		
		WebDriverWait wait = new WebDriverWait(driver, 8000);
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
		//System.out.println(list.size());
		//for(int i = 0; i < list.size(); i++){
			//System.out.println(list.get(i));.list-item-add-placeholder
		//}
		WebElement last = list.get(list.size()-1);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", last);
		last.findElement(By.cssSelector("a:first-child")).click();
		Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#mCSB_2_container > li:last-child")).click();
		driver.findElement(By.cssSelector(".list-item-add-placeholder")).click();
		Thread.sleep(2000);
		String browserUrl = driver.getCurrentUrl();
		driver.findElement(By.cssSelector("#txtNewBoardListName")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys("To Do");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".list-item-add-save")).click();
		Thread.sleep(2000);
		String id = driver.findElement(By.cssSelector("#board > div:nth-child(1)")).getAttribute("id");
		System.out.println(id);
		driver.findElement(By.cssSelector(".list-item-add-placeholder")).click();
		Thread.sleep(2000);
		//String browserUrl = driver.getCurrentUrl();
		driver.findElement(By.cssSelector("#txtNewBoardListName")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys("In progress");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".list-item-add-save")).click();
		Thread.sleep(2000);
		String id1 = driver.findElement(By.cssSelector("#board > div:nth-child(2)")).getAttribute("id");
		System.out.println(id1);
		driver.findElement(By.xpath("//*[@id="+id1+"]/div/div[1]/div/div[2]/button[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id="+id1+"]/div/div[1]/div/div[2]/span/div/button/span[1]")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[@id="+id1+"]/div/div[1]/div/div[2]/span/div/div/ul/li[2]/a/span[1]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector(".list-item-add-placeholder")).click();
		Thread.sleep(2000);
		//String browserUrl = driver.getCurrentUrl();
		driver.findElement(By.cssSelector("#txtNewBoardListName")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys("Done");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".list-item-add-save")).click();
		Thread.sleep(2000);
		String id2 = driver.findElement(By.cssSelector("#board > div:nth-child(3)")).getAttribute("id");
		System.out.println(id2);
		driver.findElement(By.xpath("//*[@id="+id2+"]/div/div[1]/div/div[2]/button[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id="+id2+"]/div/div[1]/div/div[2]/span/div/button/span[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id="+id2+"]/div/div[1]/div/div[2]/span/div/div/ul/li[3]/a")).click();
		Thread.sleep(2000);
		driver.get(browserUrl);
		Thread.sleep(2000);
		
		
		//driver.findElement(By.cssSelector("#407 > div.list-item-content > div.list-item-header.ui-sortable-handle > div.row > div.col-sm-3 > button[name=\"btnChangeBoardZeroMapping\"]")).click();
		//driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
		//driver.findElement(By.xpath("//div[@id='407']/div/div/div/div[2]/span/div/div/ul/li[2]/a/span")).click();
		//new Select(driver.findElement(By.xpath("(//select[@id='BoardZeroList'])[2]"))).selectByVisibleText("InProgress");
		//driver.findElement(By.cssSelector("#408 > div.list-item-content > div.list-item-header.ui-sortable-handle > div.row > div.col-sm-3 > button[name=\"btnChangeBoardZeroMapping\"]")).click();
		//driver.findElement(By.xpath("(//button[@type='button'])[10]")).click();
		//driver.findElement(By.xpath("//div[@id='408']/div/div/div/div[2]/span/div/div/ul/li[3]/a/span")).click();
		List<WebElement> listkolona = new LinkedList<WebElement>();
		//listkolona = driver.findElements(By.className("sortable ui-sortable"));
		listkolona = driver.findElements(By.cssSelector("#board > div"));
		System.out.println(listkolona.size());
		
		
		
		Thread.sleep(2000);
	   // columnID = "#"+id1+" > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > button:nth-child(1)";
		//driver.findElement(By.cssSelector(columnID)).click();
	  //  System.out.println(columnID);
		//driver.findElement(By.xpath("//div[@id='555'/div/div[1]/div/div[2]/button.btn.btn-sm.btn-remove")).click();
	   // driver.findElement(By.xpath("//*[@id="+id1+"]/div/div[1]/div/div[2]/button[1]")).click();

		//for(int i = 0; i < listkolona.size()-2; i++){
			//System.out.println(listkolona.get(i));
			
			
			//Thread.sleep(2000);
			//driver.findElement(By.cssSelector("button.btn.dropdown-toggle.btn-default")).click();
			//Thread.sleep(2000);
			//driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(1) > li:nth-child(1+i)")).click();
			//Thread.sleep(2000);
			
		//}
		//WebElement last1 = listkolona.get(listkolona.size()-1);
		//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", last1);
		//last1.findElement(By.cssSelector("a:first-child")).click();
		//Thread.sleep(2000);
		
		
		
		
		
		driver.findElement(By.cssSelector("span.glyphicon.glyphicon-plus")).click();
		Thread.sleep(2000);
	
		driver.findElement(By.cssSelector(".dropup > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("txtAddNewTicketTitle")).clear();
		Thread.sleep(2000);
		String a = ("Tiket" + (int)(Math.random()*12345));
		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys(a);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		String b = ("Tiket" + (int)(Math.random()*12345));
		String c = ("Tiket" + (int)(Math.random()*12345));
		
		Thread.sleep(6000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.glyphicon.glyphicon-plus")));		

		driver.findElement(By.cssSelector("span.glyphicon.glyphicon-plus")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".dropup > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("txtAddNewTicketTitle")).clear();
		Thread.sleep(2000);
		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys(b);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("span.glyphicon.glyphicon-plus")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".dropup > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("txtAddNewTicketTitle")).clear();
		Thread.sleep(2000);
		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys(c);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		System.out.println("To Do tiket " + a);
		System.out.println("InProgress tiket " + b);
		System.out.println("Done " + c);
		
		//pravi tiket vo vtora kolona
		//driver.findElement(By.xpath("(//div[@onclick='OpenAddTicketDropdown($(this));'])[2]")).click();
		//driver.findElement(By.cssSelector(".dropup > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2)")).click();Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#mCSB_2_container > div[name=\"divPoolList\"] > div[name=\"divAddNewTicket\"] > div.list-item-card > div.list-item-card-details > div.row > div.col-md-10 > input[name=\"txtAddNewTicketTitle\"]")).clear();Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#mCSB_2_container > div[name=\"divPoolList\"] > div[name=\"divAddNewTicket\"] > div.list-item-card > div.list-item-card-details > div.row > div.col-md-10 > input[name=\"txtAddNewTicketTitle\"]")).sendKeys(b);Thread.sleep(2000);
		//driver.findElement(By.cssSelector("h3")).click();
		//Thread.sleep(2000);
	
		//pravi tiket vo treta kolona
		//driver.findElement(By.xpath("(//div[@onclick='OpenAddTicketDropdown($(this));'])[3]")).click();
		//driver.findElement(By.cssSelector(".dropup > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2)")).click();Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#mCSB_3_container > div[name=\"divPoolList\"] > div[name=\"divAddNewTicket\"] > div.list-item-card > div.list-item-card-details > div.row > div.col-md-10 > input[name=\"txtAddNewTicketTitle\"]")).clear();Thread.sleep(2000);
		//driver.findElement(By.cssSelector("#mCSB_3_container > div[name=\"divPoolList\"] > div[name=\"divAddNewTicket\"] > div.list-item-card > div.list-item-card-details > div.row > div.col-md-10 > input[name=\"txtAddNewTicketTitle\"]")).sendKeys(c);Thread.sleep(2000);
		//driver.findElement(By.cssSelector("h3")).click();
		//Thread.sleep(2000);

		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(1)")).click();
		Thread.sleep(2000);
		
		//WebElement element = driver.findElement(By.cssSelector("div.list-item-card-outer:last-child"));
		//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		
		//WebElement element1  = driver.findElement(By.name("To Do"));
		//Thread.sleep(2000);
		//assertEquals("To Do", element1.getText());
		 
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).sendKeys(a);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.navbar:nth-child(2) > ul:nth-child(2) > li:nth-child(2) > button:nth-child(1)")).click();
		Thread.sleep(2000);
		WebElement elementa  = driver.findElement(By.cssSelector(".col-md-10 > span:nth-child(2)"));
		assertEquals(a, elementa.getText());
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).sendKeys(b);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.navbar:nth-child(2) > ul:nth-child(2) > li:nth-child(2) > button:nth-child(1)")).click();
		Thread.sleep(2000);
		WebElement elementb  = driver.findElement(By.cssSelector(".col-md-10 > span:nth-child(2)"));
		System.out.println("b" + b);
		System.out.println("elementb.getText()" + elementb.getText());
		assertEquals(b, elementb.getText());
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).sendKeys(c);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.navbar:nth-child(2) > ul:nth-child(2) > li:nth-child(2) > button:nth-child(1)")).click();
		Thread.sleep(2000);
		WebElement elementc  = driver.findElement(By.cssSelector(".col-md-10 > span:nth-child(2)"));
		assertEquals(c, elementc.getText());
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#kanbanTab")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.nav-item:last-child")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(2) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".spnCardStatus")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(2) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".spnCardStatus")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.open:nth-child(1) > div:nth-child(2) > ul:nth-child(2) > li:nth-child(3) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).sendKeys(a);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.navbar:nth-child(2) > ul:nth-child(2) > li:nth-child(2) > button:nth-child(1)")).click();
		Thread.sleep(2000);
		WebElement elementa1  = driver.findElement(By.cssSelector(".col-md-10 > span:nth-child(2)"));
		assertEquals(a, elementa1.getText());
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).sendKeys(b);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.navbar:nth-child(2) > ul:nth-child(2) > li:nth-child(2) > button:nth-child(1)")).click();
		Thread.sleep(2000);
		WebElement elementb2  = driver.findElement(By.cssSelector(".col-md-10 > span:nth-child(2)"));
		assertEquals(b, elementb2.getText());
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#searchZeroTermHeader")).sendKeys(c);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.navbar:nth-child(2) > ul:nth-child(2) > li:nth-child(2) > button:nth-child(1)")).click();
		Thread.sleep(2000);
		WebElement elementc3  = driver.findElement(By.cssSelector(".col-md-10 > span:nth-child(2)"));
		assertEquals(c, elementc3.getText());
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		
		System.out.println("Over");
		
	}

}
