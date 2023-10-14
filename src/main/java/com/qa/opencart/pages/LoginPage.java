package com.qa.opencart.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

	// webelements and actions(methods) -- features

	// pagefactory pattern: for WebElements:

	
	@FindBy(linkText = "My Account")
	WebElement myaccount;	
	
	@FindBy(linkText = "Login")
	WebElement loginLink;

	@FindBy(linkText = "Register")
	WebElement registerLink;
	
	@FindBy(id = "input-email")
	WebElement username;
	
	
	@FindBy(id = "input-password")
	WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement loginBtn;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public HomePage login(String userName, String pwd) {
//		myaccount.click();
//		loginLink.click();
		username.sendKeys(userName);
		password.sendKeys(pwd);
		loginBtn.click();
//		JavascriptExecutor js = ((JavascriptExecutor) driver);
//		js.executeScript("arguments[0].click();", loginBtn);
		
		return new HomePage(driver);

	}

	public String getLoginPageTitle() {
		return driver.getTitle();
	}
	
	public void clickMyaccount(){
		myaccount.click();
	}

	
	public void clickLoginLink(){
		loginLink.click();
	}
	
	
	public boolean verifyRegisterLink(){
		return registerLink.isDisplayed();
	}
	
	
	public boolean verifyLoginLinkink(){
		return loginLink.isDisplayed();
	}
	
}
