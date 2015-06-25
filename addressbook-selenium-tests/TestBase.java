package com.example.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class TestBase {

	private static WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeTest
	public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	  //  baseUrl = "http://localhost/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	@AfterTest
	public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	protected void createGroup(GroupObject groupObject) {
		openMainPage();
		goToGroupListPage();
		initGroupCreation();
		fillGroupForm(groupObject);
		submitGroupCreation();
		returnToGroupListPage();
	}

	private void returnToGroupListPage() {
		driver.findElement(By.linkText("group page")).click();
	    assertEquals("Groups | Address Book", driver.getTitle());
	    try {
	        assertTrue(isElementPresent(By.xpath("(//form[@action='/addressbookv4.1.4/group.php'])[2]")));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	}

	private void submitGroupCreation() {
		driver.findElement(By.name("submit")).click();
	    assertEquals("Groups | Address Book", driver.getTitle());
	}

	private void fillGroupForm(GroupObject groupObject) {
		type("group_name", groupObject.name);
	    type("group_header", groupObject.header);
	    type("group_footer", groupObject.footer);
	}

	private void type(String locator, String name) {
		driver.findElement(By.name(locator)).clear();
	    driver.findElement(By.name(locator)).sendKeys(name);
	}

	private void initGroupCreation() {
		driver.findElement(By.name("new")).click();
	    assertEquals("Groups | Address Book", driver.getTitle());
	}

	private void goToGroupListPage() {
		driver.findElement(By.linkText("groups")).click();
	    assertEquals("Groups | Address Book", driver.getTitle());
	}

    public  void openMainPage() {
		
	    driver.get("http://localhost/addressbookv4.1.4/");
	}

	

	private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }

}