package packageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstarctComponent;

public class LogInPage extends AbstarctComponent {
	// Creating Page object Classes for Login Screen and migrate the test
	WebDriver driver;

	public LogInPage(WebDriver driver) {

		super(driver);
		// initialization
		this.driver = driver;
		// this refers to the current class driver.
		PageFactory.initElements(driver, this);

	}

	// driver.findElement(By.id("userEmail")) - WebElement
	// By.id("userEmail") - it's return type is By
	// WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Implementing Action methods for Page factory web elements to implement logic

	// PageFactory
	@FindBy(id = "userEmail")
	WebElement Email;

	@FindBy(id = "userPassword")
	WebElement Password;

	@FindBy(id = "login")
	WebElement Submit;

	// .ng-tns-c4-21.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	// @FindBy(css="div[role='alert']")

	@FindBy(css = "[class*='flyInOut']")
	WebElement ErrorMessage;

	public ProductCatalog logInApplication(String email, String password) {

		Email.sendKeys(email);
		Password.sendKeys(password);
		Submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;

	}

	public String geterrorMessage() {
		waitForWebElementToAppear(ErrorMessage);
		return ErrorMessage.getText();
	}

	public void goTo() {

		driver.get("https://rahulshettyacademy.com/client");

	}

}
