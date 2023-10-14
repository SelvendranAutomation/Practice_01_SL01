package com.qa.opencart.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductPage;
import com.qa.opencart.pages.TestBase;



public class HomePageTest {
	
	public WebDriver driver;
	public TestBase testBase;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public ProductPage productPage;

	
	@BeforeTest
	public void setUp(){
		testBase = new TestBase();
		prop = testBase.initProperty();
		driver = testBase.init(prop.getProperty("browser"));
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		driver.get(prop.getProperty("url"));
		
	}
	
	@Test(priority=1)
	public void verifyRegisterAndLogin(){
		loginPage.clickMyaccount();
		
		Assert.assertTrue(loginPage.verifyRegisterLink());
		Assert.assertTrue(loginPage.verifyLoginLinkink());
		loginPage.clickLoginLink();
		 
	}
	
	@Test(priority=2)
	public void verifyLoginTest(){
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		String title = homePage.getHomePageTitle();
		System.out.println("Home page title is: "+ title);
		Assert.assertEquals(title, "My Account");
		 
	}

	@Test(priority=3)
	public void verifyAddItemToCartUnderComponentsMenu(){
		homePage.clickComponentsMenu();
		homePage.clickMonitorsSubMenu();
		homePage.addItemToCart("Apple Cinema 30\"");
		productPage.fillFormForShoppingCartu();
		
		
		String productPageSuccessMessage = productPage.getSuccessMessage();
		System.out.println("productPageSuccessMessage page title is: "+ productPageSuccessMessage);
		Assert.assertTrue(productPageSuccessMessage.contains("You have added Apple Cinema 30\" to your shopping cart!")); 
	}

	@Test(priority=4)
	public void verifyShoppingCart(){
		List<String> ProductNameList = new ArrayList();
		homePage.clickShoppingCart();
		ProductNameList = productPage.getProductNames();
		
		System.out.println("productPageSuccessMessage page title is: "+ ProductNameList);
		Assert.assertTrue(ProductNameList.contains("Apple Cinema 30\""));
		 
	}	
	
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	
	
	

}
