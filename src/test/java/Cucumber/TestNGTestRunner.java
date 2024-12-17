package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="C:\\Users\\Akshitha\\Desktop\\NOTE\\Manual_AutomationNotes\\SeleniumTest\\SeleniumFramework\\src\\test\\java\\Cucumber",glue="stepDefenitions",
monochrome=true,tags="@Regression",plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests
{
		
}
