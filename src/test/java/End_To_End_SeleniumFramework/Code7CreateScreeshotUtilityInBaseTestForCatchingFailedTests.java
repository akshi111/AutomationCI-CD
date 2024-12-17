package End_To_End_SeleniumFramework;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

public class Code7CreateScreeshotUtilityInBaseTestForCatchingFailedTests extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		ProductCatalog productCatalog = logInPage.logInApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("product"));

		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.SelectCountry("india");

		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String confirmMessage = confirmationPage.getconfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistory() throws InterruptedException {
		ProductCatalog productCatalog = logInPage.logInApplication("ramastha159@gmail.com", "Akshi@1001");
		OrderPage ordersPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
	}

	public String Screenshot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// to run test with two different data sets and to run two sets we need to
		// create two dimensional data array which accepts multiple sets
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "/src/test/java/Data/PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

//	@DataProvider
//	public Object[][] getData()  {
//	 return new Object[][] {{"ramastha159@gmail.com","Akshi@1001","ZARA COAT 3"}, {"itc@gmail.com","Dummy@123","ADIDAS ORIGINAL"}} 	 
//   }

//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email","ramastha159@gmail.com");
//	map.put("password", "Akshi@1001");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email","itc@gmail.com");
//	map1.put("password", "Dummy@123");
//	map1.put("product", "ADIDAS ORIGINAL");

}
