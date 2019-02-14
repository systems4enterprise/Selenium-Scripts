package b0ardTesting;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BitsScript1 {

	public static String baseURL = "https://bitsofinsurance.com";
	WebDriver driver;
	private int counter;
	
	public void setUp(){
		driver = new FirefoxDriver();
	}
	@Test
	public void open() throws InterruptedException {
		counter =314;
		while(counter>0){
			setUp();
			//driver.get(baseURL);
			//Thread.sleep(1000);
			//try{
				//driver.findElement(By.cssSelector("div.sumome-react-wysiwyg-component:nth-child(5) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)")).click();
			//}
			//catch(Exception e){
			//b}
			
			//za gasenje na pop up za sledenje na fb
			
			//try{
				//driver.findElement(By.id("heateor_ss_browser_msg_close")).click();
				//driver.findElement(By.xpath("//div[4]/div/div/div[2]")).click();
				//}
				//catch (Exception c){
				//}
			
			Random rd = new Random();
			int brojche = rd.nextInt(6);
			int brojchee =5;
			//brojche++;
			brojchee++;
			//Za izminuvanje na poslednite 4 vesti
			driver.get(baseURL + "/2019/02/07/agencija-za-mladi-i-sport-objavi-oglas-za-finansiranje-na-mladinski-nbo/");
			Thread.sleep(3000);
		
			//driver.findElement(By.cssSelector("#latest-content > div.latest-square:nth-child("+ brojchee + ")")).click();
			//Thread.sleep(5000);

			driver.findElement(By.cssSelector("img")).click();
			Thread.sleep(3000);
		
			//brojchee++;
		
			//driver.findElement(By.cssSelector("#latest-content > div.latest-square:nth-child("+ brojchee +")")).click();
			//Thread.sleep(1000);
			//driver.get(baseURL);
			driver.get(baseURL + "/2019/02/07/robi-telekabel-covekot-koj-napravi-cela-makedonija-da-progleda-sega-napravi-i-da-prozbori/");
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("img")).click();
			Thread.sleep(3000);
			
			//brojchee++;
		
			//driver.findElement(By.cssSelector("#latest-content > div.latest-square:nth-child("+ brojchee +")")).click();
			//Thread.sleep(5000);
			
			//driver.findElement(By.cssSelector("img")).click();
			//Thread.sleep(1000);
			//driver.get(baseURL);
			driver.get(baseURL + "/2019/02/07/novi-povolnosti-so-internet-banka-i-so-mbanka-za-fizicki-lica-na-komercijalna-banka/");
			brojchee++;
			
			//driver.findElement(By.cssSelector("#latest-content > div.latest-square:nth-child("+ brojchee +")")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.cssSelector("img")).click();
			Thread.sleep(3000);
		//	driver.get(baseURL);
			driver.get(baseURL + "/2019/02/07/novi-sredstva-za-zemjodelcite-koi-podignale-ovosni-i-lozovi-nasadi/");
			brojchee++;
			//driver.findElement(By.cssSelector("#latest-content > div.latest-square:nth-child("+ brojchee +")")).click();
			Thread.sleep(3000);		
			
			
			//Za naslovnata da ja otvara e ovoj kod
			
			//driver.findElement(By.cssSelector("span.gradient")).click();
			//Thread.sleep(1000);
			
			driver.findElement(By.cssSelector("img")).click();
			Thread.sleep(3000);
			
			
			driver.get(baseURL + "/2019/02/08/bregzit-dogovorot-otfrlen-sto-e-sledno/");
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("img")).click();
			Thread.sleep(3000);
			//da otvara po kategorii e ovoj kod
			
			//driver.findElement(By.cssSelector("#secondary-menu > li:nth-child("+ brojche +")")).click();
			//Thread.sleep(1000);
			//brojche++;
			//driver.findElement(By.cssSelector("img")).click();
			//Thread.sleep(1000);
			//driver.findElement(By.cssSelector("#secondary-menu > li:nth-child("+ brojche +")")).click();
			//Thread.sleep(20000);
			System.out.println("Counter: " + counter + ".....................");
			close();
			counter--;
		}
		
		
	}
	
	public void close(){
		driver.quit();

}}
