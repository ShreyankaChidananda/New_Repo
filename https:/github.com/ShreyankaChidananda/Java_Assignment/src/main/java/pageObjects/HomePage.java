package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.ReUseableMethods;

public class HomePage extends ReUseableMethods {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		// initialization of driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(@class,'cursor-pointer text-[14px]')]")
	WebElement searchLanguageCurrencyCursorPointer;

	@FindBy(xpath = "//input[@placeholder=\"Search destination\"]")
	WebElement searchDestination;

	@FindBy(xpath = "//SECTION[contains(.,'LocalRegionalGlobal ThailandCountriesThailandData') and contains(.,'GB Valid')]/DIV/DIV[3]/DIV[2]")
	WebElement thailandSecondPlan;

	public void navigateToWebPage() {

		driver.get("https://www.betterroaming.com/plans/esim-thailand/");

	}

	public void clickOnsearchLanguageCurrencyCursorPointer() {
		clickOnElement(searchLanguageCurrencyCursorPointer);
	}

	public void enterInputToSearchDestination(String destination) {
		enterInput(searchDestination, destination);
	}

	public boolean verifyPlanDetails(String planDetail) {

		List<WebElement> listOfPlandetails = thailandSecondPlan.findElements(By.tagName("p"));

		waiFforElementsToAppear(listOfPlandetails);
		boolean ifpresent = verifyTextdispalyed(planDetail, listOfPlandetails);
		return ifpresent;

	}

}
