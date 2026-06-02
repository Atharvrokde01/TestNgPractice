package testDemoOne;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	public void steUp() {

		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.navigate().to("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}

	@Test(priority = 1)
	public void logintest() {

		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.id("add-to-cart-sauce-labs-backpack")));
	}

	@Test(priority = 2)
	public void verifyLogin() {

		String actual_title = driver.getTitle();
		String expected_title = "Swag Labs";

		Assert.assertEquals(actual_title, expected_title);
	}

	@Test(priority = 3)
	public void verifyCartFunctinality() {

		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

		WebElement cartBadge = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.className("shopping_cart_badge")));

		cartBadge.click();

		String actual_text = wait.until(
				ExpectedConditions.visibilityOfElementLocated(
						By.className("title"))).getText();

		String expected_text = "Your Cart";

		Assert.assertEquals(actual_text, expected_text);
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}
}