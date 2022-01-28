package week4.day2.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nyka {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		// Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");
		Thread.sleep(2000);

		// Mouseover on Brands and Search L'Oreal Paris
		// Click L'Oreal Paris
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("//div[@id='headerMenu']/div/ul[2]//a"))).perform();
		// Check the title contains L'Oreal Paris(Hint-GetTitle)
		driver.findElement(By.xpath("//div[@class='ss-wrapper']//div[7]/a")).click();
		String title = driver.getTitle();
		if (title.contains("L'Oreal Paris")) {
			System.out.println("Clicked L'Oreal brand");
		} else {
			System.out.println("Click failed");
		}

		// Click sort By and select customer top rated
		driver.findElement(By.xpath("//span[@class='sort-name']")).click();

		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		Thread.sleep(2000);
		// Click Category and click Hair->Click haircare->Shampoo

		driver.findElement(By.xpath("//span[text()='Category']")).click();
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		// Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		Thread.sleep(2000);
		// 00000 8)check whether the Filter is applied with Shampoo
		// driver.findElement(By.xpath("//div[@id='filters-listing']/div")).
		// Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[@id='product-list-wrap']//a")).click();
		String parentWindow = driver.getWindowHandle();
		Set<String> windowsHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowsHandles);
		String childWindow = windows.get(1);
		driver.switchTo().window(childWindow);
		// GO to the new window and select size as 175ml
		WebElement dropDown = driver.findElement(By.xpath("//select[1]"));
		Select dd = new Select(dropDown);
		dd.selectByVisibleText("175ml");
		// Print the MRP of the product
		System.out.println("MRP of the selected product is:"
				+ driver.findElement(By.xpath("//div[@class='css-1d5wdox']/span[2]")).getText());

		// Click on ADD to BAG
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		Thread.sleep(5000);

		// Go to Shopping Bag
		driver.findElement(By.xpath("//div[@id='header_id']/div[2]/div/div[2]/div[2]/button")).click();
		Thread.sleep(5000);

		// Print the Grand Total amount
		WebDriver frame = driver.switchTo().frame(driver.findElement(By.className("css-acpm4k")));
		Thread.sleep(5000);
		WebElement element = driver.findElement(By.xpath("//div[@class='first-col']/div"));
		String grandTotal = element.getText();
		System.out.println(grandTotal);

		Thread.sleep(5000);

		// 15) Click Proceed
		driver.findElement(By.xpath("//div[@class='second-col']/button")).click();
		driver.switchTo().defaultContent();
		// 16) Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();

		// 17) Check if this grand total is the same in step 14

		String text = driver.findElement(By.xpath("//div[@class='value']/span")).getText();
		if (grandTotal.equals(text)) {
			System.out.println("Product price and grand total are same" + text);
		} else {
			System.out.println("Product price and grand total are not same" + text);
		}
		// 18) Close all windows

		driver.quit();

	}

}
