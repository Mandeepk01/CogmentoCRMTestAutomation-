package com.cogmento.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.cogmento.automation.base.TestBase;

public class LoginPage extends TestBase {

	WebDriver driver;

	By userNameObj = By.name("email");
	By passWordObj = By.name("password");
	By loginBtnObj = By.xpath("(//*[contains(text(),'Login')])[1]");

	public LoginPage(WebDriver driver) {

		this.driver = driver;

	}

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public void login(String userName, String Password) {
		driver.findElement(userNameObj).sendKeys(userName);
		driver.findElement(passWordObj).sendKeys(userName);
		driver.findElement(loginBtnObj).click();
	}

}
