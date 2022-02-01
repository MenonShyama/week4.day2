package week4.day2.assignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Draggable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("https://jqueryui.com/draggable/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		WebElement frameXpath = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(frameXpath);
		Actions builder=new Actions(driver);
		WebElement object = driver.findElement(By.id("draggable"));
		builder.dragAndDropBy(object, 50, 20).perform();
		System.out.println("Drag and Drop success");
		driver.close();

	}

}
