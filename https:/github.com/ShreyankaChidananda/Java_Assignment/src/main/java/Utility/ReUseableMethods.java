package Utility;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ReUseableMethods {

	WebDriver driver;

	public ReUseableMethods(WebDriver driver) {
		this.driver = driver;
	}

	public void waiFforElementToAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waiFforElementsToAppear(List<WebElement> element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));

	}

	public void waiFforElementToDisAppear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(element));

	}

	public void clickOnElement(WebElement element) {

		element.click();
	}

	public void enterInput(WebElement element, String text) {
		element.sendKeys(text, Keys.ENTER);

	}

	public boolean verifyTextdispalyed(String s, List<WebElement> element) {

		Boolean match = element.stream().anyMatch(product -> product.getText().equals(s));
		return match;

	}
}
