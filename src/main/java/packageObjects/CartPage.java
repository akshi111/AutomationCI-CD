package packageObjects;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponents.AbstarctComponent;

public class CartPage extends AbstarctComponent {
	// Creating common methods to Abstract component and extending it in Page
	// classes

	WebDriver driver;

	@FindBy(css = ".totalRow button")
	public WebElement checkOutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> productTitles;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public Boolean verifyProductDisplay(String productName) {

		Boolean match = productTitles.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

//		public CheckOutPage goToCheckOut() {
//		    scrollToAndClick(checkOutEle);  // Now uses the updated method
//		    return new CheckOutPage(driver);
//				
//	}

	public CheckOutPage goToCheckOut() {
		try {
			// Attempt to scroll and click using JavaScript if necessary
			scrollToAndClick(checkOutEle);
		} catch (NoSuchElementException e) {
			System.out.println("No such element found. Please check the locator.");
		} catch (ElementClickInterceptedException e) {
			System.out.println("Element click intercepted. Trying JavaScript click as fallback.");
			// Fallback to JavaScript click if regular click fails
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", checkOutEle);
		}
		return new CheckOutPage(driver);
	}

	public void scrollToAndClick(WebElement element) {
		// Scroll the element into view
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		// Wait for the element to be clickable
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		} catch (NoSuchElementException e) {
			System.out.println("Element not found: " + e.getMessage());
			throw e; // Re-throw the exception to signal failure
		}
	}

}
