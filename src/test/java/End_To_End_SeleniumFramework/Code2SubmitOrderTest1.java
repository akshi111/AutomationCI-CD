package End_To_End_SeleniumFramework;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import packageObjects.CartPage;
import packageObjects.CheckOutPage;
import packageObjects.ConfirmationPage;
import packageObjects.ProductCatalog;
import testComponents.BaseTest;

public class Code2SubmitOrderTest1 extends BaseTest {

	@Test
	public void submitOrder() throws InterruptedException, IOException {
		String productName = "ZARA COAT 3";
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
