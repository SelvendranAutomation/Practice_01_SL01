package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.JavascriptExecutor;



public class HomePage extends TestBase{

	@FindBy(linkText = "Edit your account information")
	WebElement editAccountInfot;	
	
	
	@FindBy(linkText = "Components")
	WebElement componentsMenu;
	
	@FindBy(xpath = "//a[contains(text(),'Monitors')]")
	WebElement monitorsSubMenu;
	
	@FindBy(xpath = "//a[@title='Shopping Cart']")
	WebElement shoppingCart;
	
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getHomePageTitle(){
		return driver.getTitle();
	}
	


	public void clickMonitorsSubMenu(){
		monitorsSubMenu.click();
	}
	


	
	public void clickComponentsMenu(){
		componentsMenu.click();
	}
	

	public void addItemToCart(String itemName){
		driver.findElement(By.xpath("//a[text()='"+itemName+"']/../../following-sibling::div/button[1]")).click();
	}
	
	public void clickShoppingCart(){
 		JavascriptExecutor js = ((JavascriptExecutor) driver);
 		js.executeScript("arguments[0].scrollIntoView();", shoppingCart);
		shoppingCart.click();
	}
	
	
}
