package week4.day2.assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resizable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://jqueryui.com/resizable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		WebElement frameXpath = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frameXpath);
		Actions builder=new Actions(driver);
		WebElement source = driver.findElement(By.xpath("(//div[contains(@class,'ui-resizable-handle')])[3]"));
		builder.clickAndHold(source).moveByOffset(10, 50).release().perform();
		System.out.println("Resize success");
		driver.close();

	}

}
