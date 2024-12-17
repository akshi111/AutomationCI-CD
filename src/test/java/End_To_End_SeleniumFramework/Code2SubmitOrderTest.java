package End_To_End_SeleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import packageObjects.CartPage;
import packageObjects.CheckOutPage;
import packageObjects.ConfirmationPage;
import packageObjects.LogInPage;
import packageObjects.ProductCatalog;

public class Code2SubmitOrderTest {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Implementing Action methods for Page factory web elements to implement logic
		LogInPage logInPage = new LogInPage(driver);
		logInPage.goTo();
		ProductCatalog productCatalog = logInPage.logInApplication("ramastha159@gmail.com", "Akshi@1001");

		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);

		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);

		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.SelectCountry("india");

		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.getconfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));

	}

}
