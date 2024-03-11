package TestComponents;

import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Utility.ReUseableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;
import pageObjects.LanguageCurrencyPage;

public class BaseTest {
	protected WebDriver driver;
	public HomePage homepage;
	public ReUseableMethods reUseMethods;
	public LanguageCurrencyPage languageCurrencyPage;
	private static ExtentSparkReporter sparkReporter;
	private static ExtentReports extentReports;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fls = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Resources//GlobalData.properties");
		prop.load(fls);
		String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod
	public HomePage launchWebpage() throws IOException {
		driver = initializeDriver();
		homepage = new HomePage(driver);
		homepage.navigateToWebPage();
		return homepage;

	}

	@AfterMethod

	public void tearDown() {
		driver.close();

	}

	@BeforeTest
	public static ExtentReports config() {

		String path = System.getProperty("user.dir") + "//reports//report.html";
		extentReports = new ExtentReports();
		sparkReporter = new ExtentSparkReporter(path);
		extentReports.attachReporter(sparkReporter);

		sparkReporter.config().setReportName("Selenium Automation Result");
		sparkReporter.config().setDocumentTitle("Test Results");
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '(zzz')'");
		sparkReporter.config().setEncoding("UTF-8");

		return extentReports;
	}

	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File source = scrShot.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";

	}

}
