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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class done_proekt {
	 public static WebDriver driver;
	 public static String baseUrl;
	 public static String email = "done_test_435@zoho.com";
	 public static String password = "TestSKT1!";
	 public static String mailBody = "Testing mail body content";
	
	 
	 @Before
     public void setUp() throws Exception {
        baseUrl = "https://www.zoho.com/mail/";
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
     
    }
	@Test
	public void test() throws InterruptedException {
		 driver.get(baseUrl);
	        Thread.sleep(2000);
	       
	        //navigating to login page
	        driver.findElement(By.cssSelector("a.zgh-login")).click();
	        Thread.sleep(1000);
	       
	        //sending login details
	        driver.findElement(By.id("lid")).clear();
	        driver.findElement(By.id("lid")).sendKeys(email);
	        driver.findElement(By.id("pwd")).clear();
	        driver.findElement(By.id("pwd")).sendKeys(password);
	       
	        driver.findElement(By.id("signin_submit")).click();
	        System.out.println("Logged in");
	        Thread.sleep(2000);
	       
	        //checking if user is successfully logged in
	        driver.findElement(By.cssSelector(".zmUserInfo")).click();
	        Thread.sleep(1000);
	        assertEquals(driver.findElement(By.cssSelector(".zmUserDtl>p")).getText(),email);
	        System.out.println("The user is succesfully logged in");
	      
	        draftingEmail();
	        sendingEmail();
	       openingSentEmails();
	       deleteEmail();
	       
	    }
	   
	   
	    public void draftingEmail() throws InterruptedException{
	    	
	    	Thread.sleep(3000);
	    	String browserUrl = driver.getCurrentUrl();
	       driver.findElement(By.cssSelector(".zmNewBtn.zm_compose")).click();
	       Thread.sleep(1000);
	       driver.findElement(By.cssSelector("input.zm_sgst")).clear();
	       driver.findElement(By.cssSelector("input.zm_sgst")).sendKeys("done_test_435@zoho.com");// koj se praka meilot
	       
		   	Thread.sleep(1000);
	       driver.findElement(By.cssSelector("#zmsub_Cmp1")).click();
	       driver.findElement(By.cssSelector("#zmsub_Cmp1")).clear();
	        driver.findElement(By.cssSelector("#zmsub_Cmp1")).sendKeys("Ova e test subject");
	        Thread.sleep(2000);
	        driver.findElement(By.cssSelector(".zmCnew > div:nth-child(1) > div:nth-child(1) > ul:nth-child(1) > li:nth-child(1) > b:nth-child(1) > span:nth-child(2) > span:nth-child(1)")).click();
	        Thread.sleep(2000);
	        driver.get(browserUrl);
	        Thread.sleep(1000);
	        
	        driver.findElement(By.cssSelector("div.zmTreeNode[title=\"Drafts\"]>div")).click();
	        

	        Thread.sleep(4000);
	      // System.out.println(driver.findElement(By.cssSelector(".SC_mclst > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) ")).getText());
	        assertEquals(driver.findElement(By.cssSelector(".SC_mclst > div:nth-child(1) > div:nth-child(2)> div:nth-child(2)")).getText(),"Ova e test subject");
	        System.out.println("The email draft has been saved");
	    }

	   
	    public void sendingEmail() throws InterruptedException{
	    	
	    	driver.findElement(By.cssSelector(".SC_mclst > div:nth-child(1)")).click();
	    	Thread.sleep(2000);
	    	
	    	driver.findElement(By.cssSelector(".SCBtn > b:nth-child(1) > span:nth-child(2)")).click();
	    	Thread.sleep(3000);
	    	driver.findElement(By.cssSelector("div.zmTreeNode[title=\"Sent\"]>div")).click();
	    	Thread.sleep(3000);
	    	String browserUrl = driver.getCurrentUrl();
	    	driver.get(browserUrl);
	    	assertEquals(driver.findElement(By.cssSelector(".SC_mclst > div:nth-child(1) > div:nth-child(2)> div:nth-child(2)")).getText(),"Ova e test subject");
		    System.out.println("Email has been sent at: " + driver.findElement(By.cssSelector(".SC_mclst > div:nth-child(1) > div:nth-child(5)")).getText());
		    Thread.sleep(3000);
	    }
	   
	   
	    public void openingSentEmails() throws InterruptedException{
	    	driver.findElement(By.cssSelector(".SC_mclst > div:nth-child(1) > div:nth-child(5)")).click();
	    	 Thread.sleep(6000);
	    	 WebElement zohoMailText = driver.findElement(By.cssSelector(".jsConTent > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > p:nth-child(1) > span:nth-child(1)"));
	    	 System.out.println(zohoMailText.getText());
	    	 //p::first-line	Selects the first line of every <p> element
	    	 assertEquals(zohoMailText.getText(),"Sent using Zoho Mail");
	    
	    }
	   
	   
	    
	    public void deleteEmail() throws InterruptedException{
    	//String vreme = driver.findElement(By.cssSelector(".SC_mclst > div:nth-child(1) > div:nth-child(5)")).getText();
	    	 //System.out.println(vreme);
	    	Thread.sleep(2000);
	    	driver.findElement(By.cssSelector("div.zmLChk")).click();
	    	Thread.sleep(2000);
	    	String browserUrl = driver.getCurrentUrl();
	    	driver.findElement(By.cssSelector("i.msi-trash")).click();	
	    	driver.get(browserUrl);
	    	System.out.println("Email is deleted");
	    }	
	    
	}
