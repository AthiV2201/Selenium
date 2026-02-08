package Automate;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class case2 {

	WebDriver driver;
	@BeforeMethod
	public void startup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://automationexercise.com/");
		driver.manage().window().maximize();
		
	}
	
	@Test
	public void axel() throws InterruptedException, IOException {
        WebElement recommendedTitle = driver.findElement(By.xpath("//h2[contains(text(),'recommended items')]"));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView({block:'center'})", recommendedTitle);
		Thread.sleep(2500);
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(recommendedTitle));
		
		Actions act = new Actions(driver);
		
		WebElement carousel = driver.findElement(By.cssSelector("#recommended-item-carousel a[data-product-id='1']"));
		js.executeScript("arguments[0].click();", carousel);
		Thread.sleep(3500);
		act.moveToElement(carousel).perform();
		
		WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#recommended-item-carousel a[data-product-id='1']")));
		
		WebDriverWait wait2 = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.attributeContains(By.className("modal-dialog"), "class", "modal-dialog"));
		
		WebElement viewCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("View Cart")));
		js.executeScript("arguments[0].scrollIntoView(true);", viewCart);
		js.executeScript("arguments[0].click();", viewCart);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("./snaps/screenshot.png"));
		FileHandler.copy(screenshotFile, screenshotFile);
		
		Thread.sleep(2500);	
	}
	
	@AfterMethod
	public void scrap() {
		driver.quit();
	}
}
