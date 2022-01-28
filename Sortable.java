package week4.day2.assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sortable {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
//		1. Launch https://www.snapdeal.com/
		driver.get("http://www.leafground.com/pages/sorttable.html");
		Thread.sleep(5000);
		List<String> list=new ArrayList<String>();
		List<WebElement> oddList = driver.findElements(By.xpath("//table[@id='table_id']//tr[@class='odd']/td[2]"));
		List<WebElement> evenList = driver.findElements(By.xpath("//table[@id='table_id']//tr[@class='even']/td[2]"));
		for(WebElement x:oddList) {
			list.add(x.getText());
		}
		for(WebElement y:evenList) {
			list.add(y.getText());
		}
		Collections.sort(list);
		System.out.println("Sorted List from code "+list);
		
		driver.findElement(By.xpath("//th[@class='sorting_asc']")).click();
		List<String> screenlist=new ArrayList<String>();
		List<WebElement> oddscreenList = driver.findElements(By.xpath("//table[@id='table_id']//tr[@class='odd']/td[2]"));
		List<WebElement> evenscreenList = driver.findElements(By.xpath("//table[@id='table_id']//tr[@class='even']/td[2]"));
		for(WebElement i:oddscreenList) {
			screenlist.add(i.getText());
			
			}
		for(WebElement j:evenscreenList) {
			screenlist.add(j.getText());
		}
		
		System.out.println("Sorted list from screen "+screenlist);
		
		if(list.equals(screenlist))
		{
			System.out.println("Both are same");
		}else {
			System.out.println("List order differs");
		}
		driver.close();
	}
	

}
