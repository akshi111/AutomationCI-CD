package End_To_End_SeleniumFramework;

import org.testng.Assert;
import org.testng.annotations.Test;

import testComponents.BaseTest;

public class Code3ErrorValidations extends BaseTest {

	@Test
	public void submitOrder() {
		String productName = "ZARA COAT 3";
		logInPage.logInApplication("ramastha159@gmail.com", "Akshi11");
		// div[@aria-label = 'Incorrect email or password']
//		.ng-tns-c4-21.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		Assert.assertEquals("Incorrect email or password", logInPage.geterrorMessage());

	}

}
