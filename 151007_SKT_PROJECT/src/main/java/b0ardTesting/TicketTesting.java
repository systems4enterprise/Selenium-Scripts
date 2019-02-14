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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

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
		//System.out.println(list.size());
		//for(int i = 0; i < list.size(); i++){
			//System.out.println(list.get(i));.list-item-add-placeholder
		//}
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
		//listTicketTypes.stream().forEach(m -> System.out.println(m.findElement(By.cssSelector("a > span.text")).getText()));
		//System.out.println("List ticket types size:" + listTicketTypes.size());
		for(int i = 0; i < listTicketTypes.size(); i++){
			//System.out.println(listTicketTypes.get(i).findElement(By.cssSelector("a > span.text")).getText());
		}
		if(listTicketTypes.size() > 1){
			listTicketTypes.get(listTicketTypes.size() - 1).findElement(By.cssSelector("a")).click();
			}
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.form-group")).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input.form-group")).sendKeys("Test" + (int)(Math.random()*12345));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("h3")).click();
		Thread.sleep(2000);
		
		
	}
	@Test
	public void test() throws InterruptedException {
		logIn();
		createTicket();
		//createBoard();	
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy ");
		Date date = new Date();
		String date1= dateFormat.format(date);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div.list-item-card-outer:last-child")).click();
		Thread.sleep(2000);
		
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
		
	}

}
