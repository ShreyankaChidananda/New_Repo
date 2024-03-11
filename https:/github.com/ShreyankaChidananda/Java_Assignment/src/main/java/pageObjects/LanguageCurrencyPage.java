package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.ReUseableMethods;

public class LanguageCurrencyPage extends ReUseableMethods {
	WebDriver driver;

	public LanguageCurrencyPage(WebDriver driver) {
		super(driver);
		// initialization of driver
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[text()='Currency']")
	WebElement currencyPointer;

	@FindBy(xpath = "//div[text()='Euro']")
	WebElement euro;

	public void waitForcurrencyPointerElement() {
		waiFforElementToAppear(currencyPointer);
	}

	public void clickOnCurrencyPointer() {
		clickOnElement(currencyPointer);
	}

	public void clickOnEuro() {
		clickOnElement(euro);
	}

}
