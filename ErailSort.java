package week4.day2.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErailSort {

	public static void main(String[] args) throws InterruptedException {
//		Launch the URL - https://erail.in/
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://erail.in/");
		Thread.sleep(5000);
			
//		Uncheck the check box - sort on date
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
		Thread.sleep(2000);
		
//		Find all the train names using xpath and store it in a list
		List<WebElement> trainList = driver.findElements(By.xpath("//div[@id='divTrainsList']//td[2]/a"));

		List<String> list=new ArrayList<String>();
		for(WebElement x:trainList) {
//		 System.out.println(x.getText());
			list.add(x.getText());
			
			
		}
//		Use Java Collections sort to sort it and then print it		 
		
		Collections.sort(list);
		
		System.out.print(list);
		
		Thread.sleep(2000);
		driver.close();
	
	}

}
