package week4.day2.assignment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailUniqueList {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
//		Set the system property and Launch the URL
		driver.get("https://erail.in/");
		Thread.sleep(5000);

//		Click the 'sort on date' checkbox
		driver.findElement(By.id("chkSelectDateOnly")).click();

//		clear and type in the from station text field
		WebElement fromStation = driver.findElement(By.id("txtStationFrom"));
		fromStation.clear();
		fromStation.sendKeys("MS");
		fromStation.sendKeys(Keys.ENTER);

//		clear and type in the to station text field
		WebElement toStation = driver.findElement(By.id("txtStationTo"));
		toStation.clear();
		toStation.sendKeys("MDU");
		toStation.sendKeys(Keys.ENTER);

//		Add a java sleep for 2 seconds
		Thread.sleep(2000);

//		Store all the train names in a list
		List<WebElement> trainList = driver.findElements(By.xpath("//div[@id='divTrainsList']//td[2]/a"));
		// for(WebElement x:trainList) {
		Thread.sleep(2000);

//		Get the size of it
		System.out.println("List size of trains " + trainList.size());

//		Add the list into a new Set
		Set<WebElement> train = new HashSet<WebElement>(trainList);

//		And print the size of it
		System.out.println("Set size of train " + train.size());
		
		Thread.sleep(2000);
		driver.close();

	}

}
