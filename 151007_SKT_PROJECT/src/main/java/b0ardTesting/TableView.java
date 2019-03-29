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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

//import com.sun.org.apache.bcel.internal.generic.Select;

import org.junit.Test;

public class TableView {
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
	public void test() throws InterruptedException {
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
		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.nav-item-submenu:nth-child(3) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)")).click();
		Thread.sleep(2000);

		driver.findElement(By.cssSelector("button.btn.btn-green")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.form-group")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.form-group")).sendKeys("Num" + date1 + (int)(Math.random()*12345));
		Thread.sleep(2000);
		driver.findElement(By.name("btn-add-newIcon")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("btn-add-newIcon")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.entry:nth-child(3)")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("btnSaveChangedIcon")).click();
		Thread.sleep(2000);
		String browserUrl = driver.getCurrentUrl();
		driver.findElement(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(4) > button:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.checkbox:nth-child(2) > label:nth-child(1) > input:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.form-control:nth-child(3)")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.form-control:nth-child(3)")).sendKeys("Бруто");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#btnSaveCompType")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.checkbox:nth-child(2) > label:nth-child(1) > input:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.modal-body:nth-child(1) > div:nth-child(2) > input:nth-child(3)")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.modal-body:nth-child(1) > div:nth-child(2) > input:nth-child(3)")).sendKeys("Нето");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#btnSaveCompType")).click();
		Thread.sleep(2000);
		driver.get(browserUrl);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#bodyCardTypesTable > tr:nth-child(1) > td:nth-child(5) > button:nth-child(1)")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("tbody.tableWidth > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.context-menu-item:nth-child(4)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#kanbanTab")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.nav-item:last-child")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".list-item-add-placeholder > span:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#txtNewBoardListName")).sendKeys("test");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".list-item-add-save")).click();
		Thread.sleep(2000);
		for(int k=0;k<3;k++){
		driver.findElement(By.cssSelector("div.btn > span:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".dropup > div:nth-child(2) > ul:nth-child(2) > li:nth-child(2)")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("txtAddNewTicketTitle")).clear();
		Thread.sleep(2000);
		driver.findElement(By.name("txtAddNewTicketTitle")).sendKeys("Tiket " + (int)(Math.random()*12345));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		}
		driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(2) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).sendKeys("30000");
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).sendKeys("20000");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".glyphicon-option-horizontal")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".glyphicon-option-horizontal")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(2) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		WebElement element1  = driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > span:nth-child(1)"));
		String plata1 = element1.getText();
		System.out.println(plata1);
		
		//close the ticket 
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		
		
		driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(2) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).sendKeys("30000");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).sendKeys("20000");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".glyphicon-option-horizontal")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".glyphicon-option-horizontal")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div.list-item-card-outer:nth-child(3) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).sendKeys("50000");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).clear();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("li.kanban-board-list-group-item:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(2)")).sendKeys("30000");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(".glyphicon-option-horizontal")).click();
		Thread.sleep(2000);
		
		//close the ticket 
		driver.findElement(By.cssSelector("#dropdownEditCardMenu")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click();
		Thread.sleep(2000);
				
//		driver.findElement(By.cssSelector(".glyphicon-option-horizontal")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.cssSelector("#listEditCard > li:nth-child(1) > a:nth-child(1)")).click();
//		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#btnGridView")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button.btn:nth-child(4)")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("label.radio-inline:nth-child(1) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		//tuka asert za da sporedi
		driver.findElement(By.cssSelector("label.radio-inline:nth-child(5) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		//tuka sert za da sporedi
		driver.findElement(By.cssSelector("label.radio-inline:nth-child(6) > div:nth-child(1)")).click();
		Thread.sleep(2000);
		//tuka asert za da sporedi
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
	}

}
