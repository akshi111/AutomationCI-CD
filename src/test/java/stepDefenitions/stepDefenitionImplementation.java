package stepDefenitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import packageObjects.CartPage;
import packageObjects.CheckOutPage;
import packageObjects.ConfirmationPage;
import packageObjects.LogInPage;
import packageObjects.ProductCatalog;
import testComponents.BaseTest;

public class stepDefenitionImplementation extends BaseTest {
	
	public LogInPage loginPage; 
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;
	
	@Given("I have landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		logInPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password is (.+)$")
	public void logged_in_username_and_password(String username, String password) 
	{
		productCatalog = logInPage.logInApplication(username,password);
	}
	
	@When("^I add (.+) to Cart$")
	public void add_product_to_cart(String productName) throws InterruptedException 
	{
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}
	//OR 	@When("^Checkout (.+) and submit the order$")
	@And("^Checkout (.+) and submit the order$")
	public void checkout_submit_orders(String productName)
	{
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.SelectCountry("india");
		confirmationPage = checkOutPage.submitOrder();
	}
		
	@Then("{string} message displayed on ConfirmationPage")
	public void message_displayed_on_confirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getconfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) 
	{
		Assert.assertEquals(string, logInPage.geterrorMessage());
		driver.close();

	}
	
	
}
