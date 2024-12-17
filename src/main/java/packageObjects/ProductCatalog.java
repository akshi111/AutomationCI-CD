package packageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstarctComponent;

public class ProductCatalog extends AbstarctComponent {

	// String productName = "ZARA COAT 3";

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {

		super(driver);
		// initialization
		this.driver = driver;
		// this refers to the current class driver.
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".offset-md-0")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".offset-md-0");
	By addToCart = By.cssSelector(".card-body button:last-of-type"); // Add to cart
	By toastMessage = By.cssSelector("#toast-container"); // Loads after clikcing add to cart

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;

	}

	/*
	 * public void addProductToCart(String productName) throws InterruptedException
	 * {
	 * 
	 * // WebElement prod = getProductByName(productName);
	 * prod.findElement(addToCart).click(); waitForElementToAppear(toastMessage);
	 * waitForElementToDissappear(spinner);
	 * 
	 * }
	 */

	public boolean addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		// Proceed with adding product to cart if found
		Thread.sleep(5000);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDissappear(spinner);
		return true; // Indicate success
	}
}
