package End_To_End_SeleniumFramework;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import packageObjects.CartPage;
import packageObjects.ProductCatalog;
import testComponents.BaseTest;
import testComponents.Retry;

public class Code10_IRetryAnalyzerToRerunTheFlakyFailedSeleniumTestsInFramework extends BaseTest {
	// Testng5 for this code to run

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void logInPage() {

		// Log in to the application
		logInPage.logInApplication("ramastha159@gmail.com", "Akshi@101");
		Assert.assertEquals("Incorrect email  password.", logInPage.geterrorMessage());

	}

	@Test
	public void productCatalog() throws InterruptedException, IOException {
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = logInPage.logInApplication("itc@gmail.com", "Dummy@123");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
