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

public class Code9ConcurrencyProblemImplementThreadLocalClassToAvoidSyncIssuesInTest extends BaseTest {

	@Test(groups = { "ErrorHandling" })
	public void logInPageErrorValidations() {

		// Log in to the application
		logInPage.logInApplication("ramastha159@gmail.com", "Akshi@101");
		Assert.assertEquals("Incorrect email password.", logInPage.geterrorMessage());

		// Wait for the error message to appear (adjust the XPath as per your
		// application)
//	    WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-label = 'Incorrect email or password']")));
//	    
//	    // Assert the error message
//	    String actualErrorMessage = logInPage.geterrorMessage();
//	    AssertJUnit.assertEquals("Incorrect email or password", actualErrorMessage);
	}

	@Test
	public void productCatalogErrorValidations() throws InterruptedException, IOException {
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = logInPage.logInApplication("itc@gmail.com", "Dummy@123");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
