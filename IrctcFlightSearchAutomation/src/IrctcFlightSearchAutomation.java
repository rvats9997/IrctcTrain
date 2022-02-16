import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class IrctcFlightSearchAutomation {

	public static WebDriver driver;

	public static Properties properties = new Properties();

	public void browserSetup() throws Exception {

		FileInputStream fis = new FileInputStream(".\\src\\irctcflight.properties");
		properties.load(fis);

		String browser = properties.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else {
			throw new Exception("Browser name not Correct!!!");
		}

	}

	public static void main(String[] args) throws Exception {
		IrctcFlightSearchAutomation ifa = new IrctcFlightSearchAutomation();
		ifa.browserSetup();
		driver.get("https://www.air.irctc.co.in");
		driver.findElement(By.xpath("html/body/div/div/div/div[2]/div[1]/button[1]")).click();
		WebElement frombox = driver.findElement(By.id("stationfrom"));
		frombox.sendKeys("Hyd");
		driver.findElement(By.xpath("//div[text()='Hyderabad (HYD)']")).click();
		WebElement toBox = driver.findElement(By.id("stationTo"));
		toBox.sendKeys("Pune");
		driver.findElement(By.xpath("//div[text()='Pune (PNQ)']")).click();
		driver.findElement(By.xpath("//*[@id=\"originDate\"]")).click();
		driver.findElement(By.xpath("//span[@class='act active-red']")).click();
		driver.findElement(By.id("noOfpaxEtc")).click();

		WebElement d = driver.findElement(By.id("travelClass"));
		Select st = new Select(d);
		st.selectByIndex(1);
		driver.findElement(By.xpath("/html/body/app-root/app-index/div[3]/div[2]/div[2]/form/div[6]/button")).click();
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='right-searchbarbtm']"));
		System.out.println("Total flights available for today is " + list.size());
		System.out.println("Name of the available flights are:");
		for (WebElement webElement : list) {
			System.out.println(webElement
					.findElement(By.xpath(".//div[@class='right-searchbarbtm-in']/child::div/child::div[2]/b"))
					.getText()
					+ " "
					+ webElement
							.findElement(
									By.xpath(".//div[@class='right-searchbarbtm-in']/child::div/child::div[2]/span"))
							.getText());
		}

	}

}
