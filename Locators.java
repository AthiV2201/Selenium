package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators {
       public static void main(String[] args) {
    	   ChromeDriver driver = new ChromeDriver();
   		driver.get("https://www.facebook.com/");
   		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("AthityaV124");
   		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("AthiV!24");
   		driver.findElement(By.xpath("//button[.='Log in']")).click();
	}
}
