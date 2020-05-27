package com.cogmento.automation.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cogmento.automation.base.TestBase;
import com.cogmento.automation.listener.AllureListener;
import com.cogmento.automation.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
@Epic("Login Flow")
@Slf4j
@Listeners({AllureListener.class})
public class LoginPageTest extends TestBase {
	LoginPage loginPage;

	public LoginPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		loginPage = new LoginPage(driver);	
	}

	@Test(priority = 1)
	@Story("Login Flow")
	@Description("Test Login Page Title")
	public void loginPageTitleTest() {
		String title = loginPage.validateLoginPageTitle();
		log.info("Title of login Page is {}",title);
		Assert.assertEquals(title, "Cogmento CRM");
	}

	@Test(priority = 2)
	@Story("Login Flow")
	@Description("Test Login Functionality")
	public void loginTest() throws InterruptedException {
		log.info("Get the username and Password");
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}