package com.harukero.hanabi.client.place;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {

	private WebDriver driver;
	private String baseUrl;
	// private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	// private String closeAlertAndGetItsText() {
	// try {
	// Alert alert = driver.switchTo().alert();
	// if (acceptNextAlert) {
	// alert.accept();
	// } else {
	// alert.dismiss();
	// }
	// return alert.getText();
	// } finally {
	// acceptNextAlert = true;
	// }
	// }

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@Before
	public void setUp() throws Exception {
		// On instancie notre driver, et on configure notre temps d'attente
		System.setProperty("webdriver.gecko.driver", "d:\\Harukero\\webDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		baseUrl = "http://127.0.0.1:8888/index.html";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}

	// @Test
	public void testSelenium() throws Exception {
		// On se connecte au site
		driver.get(baseUrl);
		isElementPresent(By.id("patate"));
		/*
		 * // On se rend page 1
		 * driver.findElement(By.id("contentForm:pageText")).clear();
		 * driver.findElement(By.id("contentForm:pageText")).sendKeys("2");
		 * driver.findElement(By.id("contentForm:nextPage")).click(); // On est
		 * page 2, on va page 3
		 * driver.findElement(By.id("contentForm:page3Button")).click(); // On
		 * sélectionne notre prochaine page dans la liste new
		 * Select(driver.findElement(By.id("contentForm:pageList_input"))).
		 * selectByVisibleText("1");
		 * driver.findElement(By.id("contentForm:nextPageButton")).click(); //
		 * On est de retour page 1, on passe en anglais
		 * driver.findElement(By.id("headerForm:english_button")).click();
		 *
		 * // Et on recommence le même enchainement
		 */
	}
}
