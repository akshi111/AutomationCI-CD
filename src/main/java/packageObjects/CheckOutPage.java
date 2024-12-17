package packageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponents.AbstarctComponent;

public class CheckOutPage extends AbstarctComponent {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountry;

	@FindBy(css = ".action__submit")
	WebElement placeOrder;

	By results = By.cssSelector(".ta-results");

	public void SelectCountry(String countryName) {

		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();

	}

//	public ConfirmationPage submitOrder() {
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//	    wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
//	    placeOrder.click();
//	    return new ConfirmationPage(driver);
//	}

	public ConfirmationPage submitOrder() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// Wait until the placeOrder button is clickable
		wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
		// Scroll the button into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrder);
		// Use JavaScript to click the button
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", placeOrder);

		return new ConfirmationPage(driver);
	}

}
