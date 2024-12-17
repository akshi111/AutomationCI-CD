package End_To_End_SeleniumFramework;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import packageObjects.CartPage;
import packageObjects.CheckOutPage;
import packageObjects.ConfirmationPage;
import packageObjects.OrderPage;
import packageObjects.ProductCatalog;
import testComponents.BaseTest;

public class Code4ImplementingParameterizationIntoTestsWithTestNGDataProvider extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(String email, String password, String productName)
			throws InterruptedException, IOException {
		ProductCatalog productCatalog = logInPage.logInApplication(email, password);

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

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistory() {
		ProductCatalog productCatalog = logInPage.logInApplication("ramastha159@gmail.com", "Akshi@1001");
		OrderPage ordersPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() {
		// to run test with two different data sets and to run two sets we need to
		// create two dimensional data array which accepts multiple sets
		return new Object[][] { { "ramastha159@gmail.com", "Akshi@1001", "ZARA COAT 3" },
				{ "itc@gmail.com", "Dummy@123", "ADIDAS ORIGINAL" } };

	}

}
