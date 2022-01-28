package week4.day2.assignment;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws InterruptedException, IOException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//		1.Load the uRL https://www.amazon.in/
		driver.get("https://www.amazon.in/");
		Thread.sleep(5000);
		WebElement serachTab = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
//		2.search as oneplus 9 pro 		
		serachTab.sendKeys("oneplus 9 pro");
		serachTab.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
//			3.Get the price of the first product
		WebElement itemPrice = driver.findElement(By.xpath("//span[@class='a-price-whole']"));
		String itemFinalPrice = itemPrice.getText();
		String replaceAll = itemFinalPrice.replaceAll("[^1-9]", "");
		int total = Integer.parseInt(replaceAll);
		System.out.println("Price of first resulting phone: " + total);
//			4. Print the number of customer ratings for the first displayed product

		WebElement noOfRating = driver
				.findElement(By.xpath("//div[@class='a-section']/div[@class='sg-row']/div[2]//div[2]/div/span[2]"));
		String noOfRatings = noOfRating.getText();
		System.out.println("No of ratings: " + noOfRatings);

//		5. Mouse Hover on the stars

		WebElement findElement = driver.findElement(By.xpath("//div/span/span/a/i"));
		findElement.click();

		Thread.sleep(1000);
//			6. Get the percentage of ratings for the 5 star.
		WebElement rating = driver
				.findElement(By.xpath("(//table[@id='histogramTable']//tr//a[@class='a-link-normal'])[2]"));
		String percentage = rating.getText();
		System.out.println("Percentage of 5 star "+percentage);
		// System.out.println(driver.findElement(By.xpath("//div[@class='a-fixed-left-grid']/div[@class='a-fixed-left-grid-inner']//span")).getText());
//			7. Click the first text link of the first image
		driver.findElement(By.xpath("//div[@class='a-section']//div[@class='sg-col-inner']//div//h2/a")).click();
		Thread.sleep(2000);
//			8. Take a screen shot of the product displayed
		String parentWindow = driver.getWindowHandle();
		Set<String> windowsHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowsHandles);
		String childWindow = windows.get(1);
		driver.switchTo().window(childWindow);
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./Assignment/mobile.png");
		FileUtils.copyFile(source, destination);
//			9. Click 'Add to Cart' button
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
//			10. Get the cart subtotal and verify if it is correct.
		Thread.sleep(2000);
		// driver.switchTo().alert();

		driver.findElement(
				By.xpath("//span[@id='attach-sidesheet-view-cart-button-announce']/preceding-sibling::input")).click();

		WebElement cartTotal = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']//span"));
		String text = cartTotal.getText();
		String replaceAll2 = text.replaceAll("[^1-9]", "");
		int parseInt = Integer.parseInt(replaceAll2);

		System.out.println("Cart total: " + parseInt);

		if (total == parseInt) {
			System.out.println("Both Price are same");
		} else {
			System.out.println("Failed");
		}
		driver.close();

	}

}
